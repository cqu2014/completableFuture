package com.oliver.completableFuture.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Oliver Wang
 * @description 线程池配置类
 * @created by IntelliJ IDEA 2020.02
 * @date Create at 2021/2/1
 * @since
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    /**
     * 处理教师信息线程池
     *
     * @return
     */
    @Bean("dealWithTeacherExecutor")
    public ThreadPoolTaskExecutor dealWithTeacher(){
        ThreadPoolTaskExecutor executor = getExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(20);
        executor.setThreadNamePrefix("teacher-");
        executor.initialize();
        return executor;
    }

    /**
     * 处理学生信息线程池
     *
     * @return
     */
    @Bean("dealWithStudentExecutor")
    public ThreadPoolTaskExecutor dealWithStudent(){
        ThreadPoolTaskExecutor executor = getExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("student-");
        executor.initialize();
        return executor;
    }

    /**
     * 处理斐波那切数列线程池
     *
     * @return
     */
    @Bean("dealWithFibonacciExecutor")
    public ThreadPoolTaskExecutor dealWithFibonacci(){
        ThreadPoolTaskExecutor executor = getExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(20);
        executor.setThreadNamePrefix("fibonacci-");
        executor.initialize();
        return executor;
    }

    /**
     * ExecutorFactory
     * @return
     */
    private ThreadPoolTaskExecutor getExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setKeepAliveSeconds(100);
        executor.setQueueCapacity(10000);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //核心线程不销毁-core thread可以销毁
        executor.setAllowCoreThreadTimeOut(true);
        //shutdown被调用时等待线程执行完毕
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //最大等待时间
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }
}
