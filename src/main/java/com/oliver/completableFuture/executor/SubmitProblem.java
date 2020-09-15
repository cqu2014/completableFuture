package com.oliver.completableFuture.executor;

import cn.hutool.core.lang.Console;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Oliver Wang
 * @description submit的坑
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2020/9/14
 * @since
 */
public class SubmitProblem {
    /**
     * 创建公平锁
     */
    private static final ReentrantLock reentrantLock = new ReentrantLock(true);

    /**
     * 获取公平读写锁
     */
    private static final ReentrantReadWriteLock  readWriteLock = new ReentrantReadWriteLock(true);
    ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 5; i++) {
            int index = i;
            executorService.submit(() -> divTask(100, index));
        }
        executorService.shutdown();
    }
    private static void divTask(int a, int b) {
        double result = a / b;
        Console.log("{}执行的结果为{}",Thread.currentThread().getName(),result);
    }
}
