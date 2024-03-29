package com.oliver.completableFuture.threadlocal;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

/**
 * @author Oliver Wang
 * @description
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2020/8/10
 * @since
 */
@Slf4j
public class WithThreadLocal {
    /**
     *
     */
    private static final ThreadLocal<String> threadLocalContent = new ThreadLocal<>();

    public String getContent(){
        // 获取当前线程的局部
        return threadLocalContent.get();
    }

    public void setContent(String content) {
        // 绑定当前线程的局部变量
        threadLocalContent.set(content);
    }

    public static void main(String[] args) {
        WithThreadLocal withThreadLocal = new WithThreadLocal();
        for (int item = 0; item < 10; item++) {
            Thread thread = new Thread(() -> {
                withThreadLocal.setContent(Thread.currentThread().getName() + "数据");
                System.out.println(Thread.currentThread().getName() + "------>" + withThreadLocal.getContent());
            });
            thread.setName("线程" + item);
            thread.start();
        }
    }
}
