package com.oliver.completableFuture.threadlocal;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author Oliver Wang
 * @description
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2020/8/10
 * @since
 */
@Slf4j
public class WithoutThreadLocal {
    private String content;

    public String getContent(){
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static void main(String[] args) throws InterruptedException {
        WithoutThreadLocal withoutThreadLocal = new WithoutThreadLocal();
        IntStream.range(0,5).forEach(x->{
           Thread thread = new Thread(() -> {
               withoutThreadLocal.setContent(Thread.currentThread().getName()+"数据");
               System.out.println("---------------------------------------");
               System.out.println(Thread.currentThread().getName() + "------>" + withoutThreadLocal.getContent());
           });
           thread.setName("线程"+x);
           thread.start();
        });
    }
}
