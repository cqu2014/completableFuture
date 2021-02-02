package com.oliver.completableFuture.transaction.tool;

import cn.hutool.json.JSONUtil;
import com.oliver.completableFuture.mapper.MyBatisBaseDao;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Oliver Wang
 * @description 多线程事务管理器
 * @created by IntelliJ IDEA 2020.02
 * @date Create at 2021/2/2
 * @since
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionManagerTool {
    /**
     * transaction manager
     */
    private final PlatformTransactionManager transactionManager;

    /**
     * 多线程事务管理器
     *
     * @param managerDates
     * @param consumer
     */
    public void manager(List<transactionManagerDate<?,?>> managerDates,Consumer<List<Future<?>>> consumer){
        log.info("control start @ {}", LocalDateTime.now());
        long startTime = System.currentTimeMillis();
        // 同步计数器
        CountDownLatch rollBackLatch = new CountDownLatch(1);
        CountDownLatch mainThreadLatch = new CountDownLatch(managerDates.size());
        AtomicBoolean rollbackFlag = new AtomicBoolean(false);
        List<Future<?>> futureList = new ArrayList<>(managerDates.size());
        // ####################### 业务处理 ######################
        managerDates.forEach(x -> futureList.add(dealWithHandler(rollBackLatch,mainThreadLatch,rollbackFlag,x)));
        // 等待各个线程执行完毕 释放事务处理计数器
        if (!rollbackFlag.get()){
            try{
                // 等到另外3个线程执行完毕
                mainThreadLatch.await();
                rollBackLatch.countDown();
                // 无返回值主逻辑
                consumer.accept(futureList);
            }catch (Exception exception){
                log.info("control exception:",exception);
                throw new RuntimeException(exception.getMessage());
            }
        } else {
            throw new RuntimeException("整体任务异常");
        }
        log.info("事务管理器总耗时：{}秒",(System.currentTimeMillis()-startTime)/1000);
    }

    /**
     * 事务处理主逻辑
     *
     * @param rollBackLatch
     * @param mainThreadLatch
     * @param rollbackFlag
     * @param dateInfo
     * @param <T>
     * @param <R>
     * @return
     */
    private <T, R> Future<R> dealWithHandler(CountDownLatch rollBackLatch,CountDownLatch mainThreadLatch,
                                             AtomicBoolean rollbackFlag,transactionManagerDate<T, R> dateInfo){
        return dateInfo.getExecutor().submit(()->{
            if (rollbackFlag.get()){
                log.warn("dealWithHandler 提前结束：{}", JSONUtil.toJsonStr(dateInfo.getData()));
                throw new RuntimeException("异常标志触发终止");
            }
            // 设置新事物
            DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
            transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
            try{
                R result = dateInfo.getFunction().apply(dateInfo.getData());
                // 业务处理结束 开始处理事务
                transactionManager(rollBackLatch,mainThreadLatch,rollbackFlag,transactionStatus);
                return result;
            } catch (Exception exception){
                log.warn("dealWithHandler exception:",exception);
                // 设置异常标志 事务回滚
                rollback(rollBackLatch,mainThreadLatch,rollbackFlag,transactionStatus);
                throw new RuntimeException(exception.getMessage());
            }
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

    @Data
    @RequiredArgsConstructor
    protected static class transactionManagerDate<T, R> {
        private final MyBatisBaseDao<?,?,?> baseDao;
        private final ThreadPoolTaskExecutor executor;
        private final Function<T, R> function;
        private final T data;
    }
}
