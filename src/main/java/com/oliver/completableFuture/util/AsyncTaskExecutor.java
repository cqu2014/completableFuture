package com.oliver.completableFuture.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author Oliver Wang
 * @description
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2020/8/7
 * @since
 */
@Slf4j
public class AsyncTaskExecutor {
    /**
     * 统计执行数量
     */
    private static ThreadLocal<Long> count = new ThreadLocal<>();

    private static Executor executor =  Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws InterruptedException {
        count.set(0L);
        CompletableFuture<Long> longCompletableFuture = CompletableFuture.supplyAsync(AsyncTaskExecutor::fetchPrice,executor);
            longCompletableFuture.thenAccept((result) -> count.set(count.get() + result))
                .exceptionally(throwable -> {
            log.info("longCompletableFuture {}",throwable.getLocalizedMessage());
            return null;
        });

        CompletableFuture<Long> longCompletableFuture2 = CompletableFuture.supplyAsync(AsyncTaskExecutor::fetchPrice,executor);
            longCompletableFuture2.thenAccept((result) -> count.set(count.get() + result))
                .exceptionally(throwable -> {
                    log.info("longCompletableFuture2 {}",throwable.getLocalizedMessage());
                    return null;
                });
        CompletableFuture<Void> allOf = CompletableFuture.allOf(longCompletableFuture, longCompletableFuture2);
        allOf.thenAccept(x -> log.info("result = {}",count.get())
        );
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);
    }

    static Long fetchPrice(){
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException ignored) {
        }
        /*if (Math.random() < 0.3){
            throw new RuntimeException("fetch price failed!");
        }*/
        return 10L;
    }
}
