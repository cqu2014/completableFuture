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
 * @description 多线程并发执行事务
 * @created by IntelliJ IDEA 2020.02
 * @date Create at 2021/1/29
 * @since
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
        List<Future<String>> list = new ArrayList<Future<String>>();
        // 线程有返回值
        Future<String> future = executor1(rollBackLatch, mainThreadLatch, rollbackFlag);
        list.add(future);
        // 线程无返回值
        executor2(rollBackLatch, mainThreadLatch, rollbackFlag);
        // 主线程业务执行完毕 如果其他线程也执行完毕 且没有报异常 正在阻塞状态中 唤醒其他线程 提交所有的事务
        // 如果其他线程或者主线程报错 则不会进入if 会触发回滚
        if (!rollbackFlag.get()) {
            try {
                mainThreadLatch.await();
                rollBackLatch.countDown();
                for (Future<String> f : list)
                    if (!"success".equals(f.get()))
                        result = f.get() + "。";
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private void executor2(CountDownLatch rollBackLatch, CountDownLatch mainThreadLatch, AtomicBoolean rollbackFlag) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            if (rollbackFlag.get())
                return; // 如果其他线程已经报错 就停止线程
            // 设置一个事务
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
            TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
            try {
                // 业务处理开始
                TimeUnit.SECONDS.sleep(3);
                // 业务处理结束
                mainThreadLatch.countDown();
                rollBackLatch.await();// 线程等待
                if (rollbackFlag.get()) {
                    transactionManager.rollback(status);
                } else {
                    transactionManager.commit(status);
                }
            } catch (Exception e) {
                e.printStackTrace();
                // 如果出错了 就放开锁 让别的线程进入提交/回滚 本线程进行回滚
                rollbackFlag.set(true);
                rollBackLatch.countDown();
                mainThreadLatch.countDown();
                transactionManager.rollback(status);
            }
        });
    }

    private Future<String> executor1(CountDownLatch rollBackLatch, CountDownLatch mainThreadLatch, AtomicBoolean rollbackFlag) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<String> result = executor.submit(() -> {
            if (rollbackFlag.get())
                return "error"; // 如果其他线程已经报错 就停止线程
            // 设置一个事务
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
            TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
            try {
                // 业务处理开始
                // ..............
                // 业务处理结束
                mainThreadLatch.countDown();
                rollBackLatch.await();// 线程等待
                if (rollbackFlag.get()) {
                    transactionManager.rollback(status);
                } else {
                    transactionManager.commit(status);
                }
                return "success";
            } catch (Exception e) {
                e.printStackTrace();
                // 如果出错了 就放开锁 让别的线程进入提交/回滚 本线程进行回滚
                rollbackFlag.set(true);
                rollBackLatch.countDown();
                mainThreadLatch.countDown();
                transactionManager.rollback(status);
                return "操作失败：" + e.getMessage();
            }
        });
        // result.get()阻塞线程

        return result;
    }
}
