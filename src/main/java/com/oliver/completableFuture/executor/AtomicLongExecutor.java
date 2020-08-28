package com.oliver.completableFuture.executor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Oliver Wang
 * @description
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2020/8/7
 * @since
 */
@Slf4j
public class AtomicLongExecutor {
    /**
     * 统计执行数量
     */
    private static AtomicLong count = new AtomicLong(0);
    private static Executor executor =  Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Long> longCompletableFuture = CompletableFuture.supplyAsync(AtomicLongExecutor::fetchPrice,executor);
            longCompletableFuture.thenAccept((result) -> log.info("{}==>{}",Thread.currentThread().getName(),count.addAndGet(result)))
                .exceptionally(throwable -> {
            log.info("longCompletableFuture {}",throwable.getLocalizedMessage());
            return null;
        });

        CompletableFuture<Long> longCompletableFuture2 = CompletableFuture.supplyAsync(AtomicLongExecutor::fetchPrice,executor);
            longCompletableFuture2.thenAccept((result) -> log.info("{}==>{}",Thread.currentThread().getName(),count.addAndGet(result)))
                .exceptionally(throwable -> {
                    log.info("longCompletableFuture2 {}",throwable.getLocalizedMessage());
                    return null;
                });
       CompletableFuture.allOf(longCompletableFuture, longCompletableFuture2).thenAccept(x -> {
            try {
                log.info("result = {}",longCompletableFuture.get(1,TimeUnit.SECONDS)+longCompletableFuture2.get(1,TimeUnit.SECONDS));
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        TimeUnit.SECONDS.sleep(5);
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
