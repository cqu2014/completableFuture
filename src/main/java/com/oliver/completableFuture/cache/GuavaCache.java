package com.oliver.completableFuture.cache;

import cn.hutool.core.lang.Console;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import javax.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Oliver Wang
 * @description
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2020/9/4
 * @since
 */
public class GuavaCache {
    private final static ThreadLocal<SimpleDateFormat>  simpleDate = ThreadLocal.withInitial(()-> new SimpleDateFormat("HH:mm:ss"));
    private final static LoadingCache<Integer,Integer> cache = CacheBuilder.newBuilder()
            // 可以同时写入缓存的线程数
            .concurrencyLevel(1)
            .initialCapacity(4)
            .maximumSize(100)
            // 是否需要统计缓存情况,该操作消耗一定的性能,生产环境应该去除
            .recordStats()
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .removalListener(notification -> {
                System.out.println(notification.getKey() + " " +
                        notification.getValue() + "已被移除,原因:" +
                        notification.getCause());
            })
            //build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
            .build(new DemoCacheLoader());

    public static void main(String[] args) {
        //模拟线程并发
        new Thread(() -> {
            try {
                for (int i = 0; i < 15; i++) {
                    Integer value = cache.get(i);
                    Console.log("{}@{}数据:{}",Thread.currentThread().getName(),simpleDate.get().format(new Date()),
                            value);
                    TimeUnit.SECONDS.sleep(2);
                }
            } catch (Exception ignored) {
            }
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(30);
                for (int i = 0; i < 10; i++) {
                    Integer value = cache.get(i);
                    Console.log("{}@{}数据:{}",Thread.currentThread().getName(),simpleDate.get().format(new Date()),
                            value);
                }
            } catch (Exception ignored) {
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Integer value = cache.get(i);
                    Console.log("{}@{}数据:{}",Thread.currentThread().getName(),simpleDate.get().format(new Date()),
                            value);
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (Exception ignored) {
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //缓存状态查看
        System.out.println(cache.stats().toString());
    }


    /**
     * 随机缓存加载,实际使用时应实现业务的缓存加载逻辑,例如从数据库获取数据
     */
    public static class DemoCacheLoader extends CacheLoader<Integer, Integer> {
        @Override
        public Integer load(@Nullable Integer key) throws Exception {
            Console.log("{}start加载数据，key={}",Thread.currentThread().getName(),key);
            TimeUnit.SECONDS.sleep(1);
            Random random = new Random();
            Console.log("{}加载数据完成，key={}",Thread.currentThread().getName(),key);
            return random.nextInt(10000);
        }
    }
}
