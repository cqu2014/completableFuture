package com.oliver.completableFuture.transaction;

import cn.hutool.json.JSONUtil;
import com.oliver.completableFuture.entity.FsStudent;
import com.oliver.completableFuture.entity.FsStudentExample;
import com.oliver.completableFuture.entity.FsTeacher;
import com.oliver.completableFuture.entity.FsTeacherExample;
import com.oliver.completableFuture.mapper.FsStudentDao;
import com.oliver.completableFuture.mapper.FsTeacherDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Oliver Wang
 * @description 控制多线程事务
 * @created by IntelliJ IDEA 2020.02
 * @date Create at 2021/2/2
 * @since
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionControl {
    private final FsStudentDao studentDao;
    private final FsTeacherDao teacherDao;
    private final ThreadPoolTaskExecutor dealWithTeacherExecutor;
    private final ThreadPoolTaskExecutor dealWithStudentExecutor;
    private final ThreadPoolTaskExecutor dealWithFibonacciExecutor;
    private final PlatformTransactionManager transactionManager;

    private final  HashMap<Integer, BigInteger> preValue = new HashMap<>();

    @PostConstruct
    public void init(){
        preValue.put(0,BigInteger.ZERO);
        preValue.put(1,BigInteger.ONE);
        preValue.put(2,BigInteger.ONE);
    }

    public void control(){
        log.info("control start @ {}", LocalDateTime.now());
        long startTime = System.currentTimeMillis();
        // 异常回滚同步计数器
        CountDownLatch rollBackLatch = new CountDownLatch(1);
        // 主线程控制同步器
        CountDownLatch mainThreadLatch = new CountDownLatch(3);
        // 异常标志
        AtomicBoolean rollbackFlag = new AtomicBoolean(false);
        // insert 教师信息
        FsTeacher teacher = FsTeacher.teacher("王二小","18290406696", (byte) 1,"物理",1);
        Future<Long> longFuture = dealWithTeacher(teacher, rollBackLatch, mainThreadLatch, rollbackFlag);
        // insert 学生信息
        FsStudent student = FsStudent.student("咕噜咕噜","1002514","114",2,"五年级(10)班");
        studentHandler(student, rollBackLatch, mainThreadLatch, rollbackFlag);
        // 执行斐波那切数列计算
        Future<BigInteger> bigIntegerFuture = fibonacciHandler(23, rollBackLatch, mainThreadLatch, rollbackFlag);

        // 主线程业务执行完毕 如果其他线程也执行完毕 且没有报异常 正在阻塞状态中 唤醒其他线程 提交所有的事务
        if (!rollbackFlag.get()){
            try{
                // 等到另外3个线程执行完毕
                mainThreadLatch.await();
                // 释放事务处理计数器
                rollBackLatch.countDown();
                log.info("teacher id={},fibonacci={}",longFuture.get(),bigIntegerFuture.get());
                log.info("事务管理器总耗时：{}秒",(System.currentTimeMillis()-startTime)/1000);
            }catch (Exception exception){
                log.info("control exception:",exception);
                throw new RuntimeException(exception.getMessage());
            }
        } else {
            throw new RuntimeException("整体任务异常");
        }
    }

    /**
     * Fibonacci 处理逻辑
     * @param n
     * @param rollBackLatch
     * @param mainThreadLatch
     * @param rollbackFlag
     * @return
     */
    private Future<BigInteger> fibonacciHandler(int n, CountDownLatch rollBackLatch,
                                                CountDownLatch mainThreadLatch, AtomicBoolean rollbackFlag){
        return dealWithFibonacciExecutor.submit(()->{
            if (rollbackFlag.get()){
                log.warn("fibonacci 提前结束");
                return BigInteger.ZERO;
            }
            try{
                log.info("fibonacciHandler with {} start with {}", Thread.currentThread().getName(),n);
                BigInteger fibonacci = fibonacci(n);
                mainThreadLatch.countDown();
                // 等待主线程释放事务处理计数器 自定义回滚逻辑
                rollBackLatch.await(10,TimeUnit.SECONDS);
                log.info("fibonacciHandler get {} with input={}",fibonacci,n);
                return fibonacci;
            } catch (Exception exception){
                log.warn("dealWithStudentHandler exception:",exception);
                // 自定义Fibonacci计算回滚逻辑
                rollbackFlag.set(true);
                rollBackLatch.countDown();
                mainThreadLatch.countDown();
            }
            return BigInteger.ZERO;
        });
    }

    /***
     * 使用BigInteger防止溢出，利用HashMap存储前（n-1）项加快计算速度
     * @param n
     * @return
     */
    private BigInteger fibonacci(int n){
        if (n < 0){
            throw new RuntimeException("fibonacci input error: "+n);
        }
       return preValue.computeIfAbsent(n,(key)->
                fibonacci(key-1).add(fibonacci(key-2)));
    }

    /**
     * 处理学生信息
     *
     * @param student
     * @param rollBackLatch
     * @param mainThreadLatch
     * @param rollbackFlag
     */
    private void studentHandler(FsStudent student, CountDownLatch rollBackLatch,
                                CountDownLatch mainThreadLatch, AtomicBoolean rollbackFlag){
        dealWithStudentExecutor.execute(() -> {
            if (rollbackFlag.get()) {
                log.warn("dealWithStudentHandler 提前结束");
                return;
            }
            // 设置事务处理
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            TransactionStatus transactionStatus = transactionManager.getTransaction(def);
            try{
                log.info("dealWithStudentHandler with {} start with {}", Thread.currentThread().getName(),
                        JSONUtil.toJsonStr(student));
                TimeUnit.SECONDS.sleep(2);
                int number = studentDao.insertSelective(student);
                FsStudentExample studentExample = new FsStudentExample();
                studentExample.createCriteria().andIs_delEqualTo((byte) 0);
                List<FsStudent> fsStudents = studentDao.selectByExample(studentExample);
                log.info("We have students = {}",JSONUtil.toJsonStr(fsStudents));
                log.info("TeacherHandler complete.no={}",number);
                // 业务处理结束 处理事务开始
                transactionManager(rollBackLatch,mainThreadLatch,rollbackFlag,transactionStatus);
            }catch (Exception exception){
                log.warn("dealWithStudentHandler exception:",exception);
                rollback(rollBackLatch,mainThreadLatch,rollbackFlag,transactionStatus);
            }
        });
    }

    /**
     * 处理教师信息
     *
     * @param teacher
     * @return
     */
    private Future<Long> dealWithTeacher(FsTeacher teacher,CountDownLatch rollBackLatch,
                                         CountDownLatch mainThreadLatch,AtomicBoolean rollbackFlag){
        return dealWithTeacherExecutor.submit(()->{
            if (rollbackFlag.get()){
                log.warn("dealWithTeacher 提前结束");
                return -1L;
            }
            DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
            // 开启新事物 ??
            transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            // 获取事务status 进行事务提交或者回滚
            TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
            try{
                log.info("TeacherHandler with {} start with {}", Thread.currentThread().getName(),
                        JSONUtil.toJsonStr(teacher));
                teacherDao.insertSelective(teacher);
                FsTeacherExample teacherExample = new FsTeacherExample();
                teacherExample.createCriteria().andIs_delEqualTo((byte) 0);
                long number = teacherDao.countByExample(teacherExample);
                log.info("Now we have {} teachers",number);
                TimeUnit.SECONDS.sleep(3);
                log.info("TeacherHandler complete.no={}",teacher.getId());
                // 业务处理结束 处理事务开始
                transactionManager(rollBackLatch,mainThreadLatch,rollbackFlag,transactionStatus);
                return teacher.getId();
            } catch (Exception exception){
                log.warn("dealWithTeacher exception:",exception);
                rollback(rollBackLatch,mainThreadLatch,rollbackFlag,transactionStatus);
            }
            return -1L;
        });
    }

    /**
     * 事务回滚并设置异常标志
     *
     * @param rollBackLatch
     * @param mainThreadLatch
     * @param rollbackFlag
     * @param transactionStatus
     */
    private void rollback(CountDownLatch rollBackLatch, CountDownLatch mainThreadLatch,
                          AtomicBoolean rollbackFlag,TransactionStatus transactionStatus){
        rollbackFlag.set(true);
        rollBackLatch.countDown();
        mainThreadLatch.countDown();
        transactionManager.rollback(transactionStatus);
    }

    /**
     * 事务处理器
     *
     * @param rollBackLatch
     * @param mainThreadLatch
     * @param rollbackFlag
     * @param transactionStatus
     */
    private void transactionManager(CountDownLatch rollBackLatch, CountDownLatch mainThreadLatch,
                          AtomicBoolean rollbackFlag,TransactionStatus transactionStatus) throws InterruptedException {
        mainThreadLatch.countDown();
        rollBackLatch.await();
        if (rollbackFlag.get()) {
            // 异常回滚非本事务导致无需抛异常
            transactionManager.rollback(transactionStatus);
        } else {
            transactionManager.commit(transactionStatus);
        }
    }

}
