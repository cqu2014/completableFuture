package com.oliver.completableFuture.forkjoin;

import java.util.concurrent.ForkJoinPool;

/**
 * @author Oliver Wang
 * @description
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2020/8/12
 * @since
 */
public class Calculator {
    public static void main(String[] args) {
        Long[] elements = new Long[10000];
        for (int i = 0; i < 10000; i++) {
            elements[i] = (long) (i + 1);
        }
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(ForkJoinPool.commonPool().invoke(new CalculatorTask(elements, 0, elements.length - 1)));
        ForkJoinPool.commonPool().shutdown();
    }
}
