package com.oliver.completableFuture.transaction;

import cn.hutool.json.JSONUtil;
import com.oliver.completableFuture.entity.FsStudent;
import com.oliver.completableFuture.entity.FsStudentExample;
import com.oliver.completableFuture.entity.FsTeacher;
import com.oliver.completableFuture.entity.FsTeacherExample;
import com.oliver.completableFuture.mapper.FsStudentDao;
import com.oliver.completableFuture.mapper.FsTeacherDao;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author Oliver Wang
 * @description 利用Future实现多线程事务 =========失败的例子============
 * @created by IntelliJ IDEA 2020.02
 * @date Create at 2021/2/1
 * @since
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class FutureTransaction {
    private final FsTeacherDao teacherDao;
    private final FsStudentDao studentDao;
    private final ThreadPoolTaskExecutor dealWithTeacher;
    private final ThreadPoolTaskExecutor dealWithStudent;
    private final ThreadPoolTaskExecutor dealWithFibonacci;


    /**
     * 该执行完毕并不会回滚**
     * 大写的尴尬
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Transactional(rollbackFor = Exception.class)
    public void insertTransaction() throws ExecutionException, InterruptedException {
        log.info("insertTransaction start @: {}", LocalDateTime.now());
        long startTime = System.currentTimeMillis();
        FsTeacher teacher = FsTeacher.teacher("王二小","18290406696", (byte) 1,"物理",1);
        CompletableFuture<Integer> teacherCompletableFuture = CompletableFuture.supplyAsync(new TeacherHandler(teacher), dealWithTeacher);

        FsStudent student = FsStudent.student("咕噜咕噜","1002514","114",2,"五年级(10)班");
        CompletableFuture<Integer> studentCompletableFuture = CompletableFuture.supplyAsync(new StudentHandler(student), dealWithStudent);

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                throw new RuntimeException("异常");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "SUCCESS";
        }, dealWithFibonacci);

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "SUCCESS";
        }, dealWithFibonacci);

        log.info("{},{},{},{}",teacherCompletableFuture.get(),
                studentCompletableFuture.get(),stringCompletableFuture.get(),completableFuture.get());


        log.info("insertTransaction 总耗时：{}秒",(System.currentTimeMillis()-startTime)/1000);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    private class TeacherHandler implements Supplier<Integer>{
        private FsTeacher teacher;

        @SneakyThrows(InterruptedException.class)
        @Override
        public Integer get() {
            log.info("TeacherHandler deal with {}", JSONUtil.toJsonStr(teacher));
            int no = teacherDao.insertSelective(teacher);
            FsTeacherExample teacherExample = new FsTeacherExample();
            teacherExample.createCriteria().andIs_delEqualTo((byte) 0);
            long number = teacherDao.countByExample(teacherExample);
            log.info("Now we have {} teachers",number);
            TimeUnit.SECONDS.sleep(3);
            log.info("TeacherHandler complete.no={}",no);
            return no;
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    private class StudentHandler implements Supplier<Integer>{
        private FsStudent student;

        @SneakyThrows(InterruptedException.class)
        @Override
        public Integer get() {
            log.info("TeacherHandler deal with {}", JSONUtil.toJsonStr(student));
            int no = studentDao.insertSelective(student);
            TimeUnit.SECONDS.sleep(3);
            FsStudentExample studentExample = new FsStudentExample();
            studentExample.createCriteria().andIs_delEqualTo((byte) 0);
            List<FsStudent> fsStudents = studentDao.selectByExample(studentExample);
            log.info("We have students = {}",JSONUtil.toJsonStr(fsStudents));
            log.info("TeacherHandler complete.no={}",no);
            return no;
        }
    }
}
