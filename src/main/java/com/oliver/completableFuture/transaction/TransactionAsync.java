package com.oliver.completableFuture.transaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Oliver Wang
 * @description 多并发执行事务的实例，支持有返回值与无返回值线程混用
 * @created by IntelliJ IDEA 2020.02
 * @date Create at 2021/1/29
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionAsync {
    private final PlatformTransactionManager transactionManager;

    public String transactionAsync(){
        String result = "";
        CountDownLatch rollBackLatch = new CountDownLatch(1);
        CountDownLatch mainThreadLatch = new CountDownLatch(2);
        AtomicBoolean rollbackFlag = new AtomicBoolean(false);
        List<Future<String>> list = new ArrayList<>();
        // 线程有返回值
        Future<String> future = transactionFuture(rollBackLatch, mainThreadLatch, rollbackFlag);
        list.add(future);
        // 线程无返回值
        withoutReturn(rollBackLatch, mainThreadLatch, rollbackFlag);
        // 主线程业务执行完毕 如果其他线程也执行完毕 且没有报异常 正在阻塞状态中 唤醒其他线程 提交所有的事务
        // 如果其他线程或者主线程报错 则不会进入if 会触发回滚
        if (!rollbackFlag.get()) {
            try {
                // 等待异步线程业务处理完成 countDown为0
                mainThreadLatch.await();
                // 释放事务处理计数器 使异步线程提交或回滚事务
                rollBackLatch.countDown();
                for (Future<String> f : list){
                    if ("success".equals(f.get())){
                        result = f.get() + ".";
                    }
                }

            } catch (InterruptedException | ExecutionException e) {
                log.info("transactionAsync exception:",e);
            }
        }
        return result;
    }

    /**
     * 有返回值的异步任务
     *
     * @param rollBackLatch
     * @param mainThreadLatch
     * @param rollbackFlag
     * @return
     */
    private Future<String> transactionFuture(CountDownLatch rollBackLatch,
                                             CountDownLatch mainThreadLatch, AtomicBoolean rollbackFlag) {
        ExecutorService executor = Executors.newCachedThreadPool();
        // submit 有返回值可捕获异常
        return executor.submit(() -> {
            // 如果其他线程已经报错 就停止线程
            if (rollbackFlag.get()){
                return "error";
            }
            // 声明一个事务
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            // 事物隔离级别，开启新事务，这样会比较安全些
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            // 获得事务状态
            TransactionStatus status = transactionManager.getTransaction(def);
            try {
                // 业务处理开始
                // ..............
                // 业务处理结束 释放住标记
                mainThreadLatch.countDown();
                // 主线程释放rollBackLatch 另外一个线程亦执行完毕
                rollBackLatch.await();
                if (rollbackFlag.get()) {
                    transactionManager.rollback(status);
                } else {
                    transactionManager.commit(status);
                }
                return "success";
            } catch (Exception e) {
                log.info("executor1 exception:",e);
                // 设置事物回滚标志
                rollbackFlag.set(true);
                // 通知另外一个线程 能够进行回滚
                rollBackLatch.countDown();
                // 释放主计数器
                mainThreadLatch.countDown();
                // 事务回滚
                transactionManager.rollback(status);
                return "操作失败：" + e.getMessage();
            }
        });
    }

    /**
     * 无返回值异步事物
     *
     * @param rollBackLatch
     * @param mainThreadLatch
     * @param rollbackFlag
     */
    private void withoutReturn(CountDownLatch rollBackLatch,
                               CountDownLatch mainThreadLatch, AtomicBoolean rollbackFlag) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // execute 无返回值无法捕获异常
        executorService.execute(() -> {
            if (rollbackFlag.get()){
                return;
            }
            // 声明一个事物
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            // 事物隔离级别，开启新事务，这样会比较安全些
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            // 获得事务状态
            TransactionStatus status = transactionManager.getTransaction(def);
            try {
                // 业务处理开始
                // .................
                // 业务处理结束
                mainThreadLatch.countDown();
                // 线程等待
                rollBackLatch.await();
                if (rollbackFlag.get()) {
                    transactionManager.rollback(status);
                } else {
                    transactionManager.commit(status);
                }
            } catch (Exception e) {
                e.printStackTrace();
                // 设置原子回滚标志
                rollbackFlag.set(true);
                // 通知另外一个线程 能够进行回滚
                rollBackLatch.countDown();
                // 释放主线程同步计数器
                mainThreadLatch.countDown();
                // 事务回滚
                transactionManager.rollback(status);
            }
        });
    }
}
