package com.oliver.completableFuture.transaction.tool;

import cn.hutool.json.JSONUtil;
import com.oliver.completableFuture.entity.FsStudent;
import com.oliver.completableFuture.entity.FsStudentExample;
import com.oliver.completableFuture.entity.FsTeacher;
import com.oliver.completableFuture.entity.FsTeacherExample;
import com.oliver.completableFuture.mapper.FsStudentDao;
import com.oliver.completableFuture.mapper.FsTeacherDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TransactionManagerToolTest {
    @Autowired
    private TransactionManagerTool manager;
    @Autowired
    private FsStudentDao studentDao;
    @Autowired
    private FsTeacherDao teacherDao;
    @Autowired
    private ThreadPoolTaskExecutor dealWithTeacherExecutor;
    @Autowired
    private ThreadPoolTaskExecutor dealWithStudentExecutor;

    private final Logger log =LoggerFactory.getLogger(TransactionManagerToolTest.class);

    @Test
    public void managerTest() {
        List<TransactionManagerTool.transactionManagerDate<?,?>> managerDates = new ArrayList<>();
        // 处理教师信息
        FsTeacher teacherInfo = FsTeacher.teacher("王小二","18290406697", (byte) 2,"化学",1);
        managerDates.add(new TransactionManagerTool.transactionManagerDate<>(
                teacherDao,
                dealWithTeacherExecutor,
                teacher -> {
                    log.info("TeacherHandler with {} start with {}", Thread.currentThread().getName(),
                            JSONUtil.toJsonStr(teacher));
                    teacherDao.insertSelective(teacher);
                    FsTeacherExample teacherExample = new FsTeacherExample();
                    teacherExample.createCriteria().andIs_delEqualTo((byte) 0);
                    long number = teacherDao.countByExample(teacherExample);
                    log.info("Now we have {} teachers",number);
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    log.info("TeacherHandler complete. id = {}",teacher.getId());
                    return teacher.getId();
                }, teacherInfo));
        // 处理学生信息
        FsStudent studentInfo = FsStudent.student("咕噜咕噜","1002515","18390406698",2,"五年级(10)班");
        managerDates.add(new TransactionManagerTool.transactionManagerDate<>(
                studentDao,
                dealWithStudentExecutor,
                student ->{
                    log.info("dealWithStudentHandler with {} start with {}", Thread.currentThread().getName(),
                            JSONUtil.toJsonStr(student));
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    int number = studentDao.insertSelective(student);
                    FsStudentExample studentExample = new FsStudentExample();
                    studentExample.createCriteria().andIs_delEqualTo((byte) 0);
                    List<FsStudent> fsStudents = studentDao.selectByExample(studentExample);
                    log.info("We have students = {}",JSONUtil.toJsonStr(fsStudents));
                    log.info("dealWithStudentHandler complete. influence no = {}",number);
                    // int a = 9/0;
                    return null;
                },studentInfo)
        );
        manager.manager(managerDates,this::show,10*1000);
    }

    private void show(List<Future<?>> futureList){
        futureList.forEach(x->{
            try {
                log.info("futureList={}",x.get());
            } catch (InterruptedException | ExecutionException interruptedException) {
                interruptedException.printStackTrace();
            }
        });
    }
}