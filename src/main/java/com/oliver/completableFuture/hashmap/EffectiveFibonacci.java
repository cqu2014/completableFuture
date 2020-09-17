package com.oliver.completableFuture.hashmap;

import org.springframework.util.StopWatch;

import java.math.BigInteger;
import java.util.HashMap;

/**
 * @author Oliver Wang
 * @description Hashmap 实现高效斐波那切数列
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2020/9/17
 * @since
 */
public class EffectiveFibonacci {

    private static final  HashMap<Integer,BigInteger> preValue = new HashMap<>();
    static {
        preValue.put(0,BigInteger.ZERO);
        preValue.put(1,BigInteger.ONE);
        preValue.put(2,BigInteger.ONE);
    }

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch("斐波那切数列执行时间统计");

        stopWatch.start("fibonacci(2)");
        System.out.println("fibonacci(2)="+fibonacci(2));
        stopWatch.stop();
        System.out.println(stopWatch.shortSummary());

        stopWatch.start("fibonacci(5)");
        System.out.println("fibonacci(5)="+fibonacci(5));
        stopWatch.stop();
        System.out.println(stopWatch.shortSummary());

        stopWatch.start("fibonacci(20)");
        System.out.println("fibonacci(20)="+fibonacci(20));
        stopWatch.stop();
        System.out.println(stopWatch.shortSummary());

        System.out.println(stopWatch.prettyPrint());
    }

    /**
     * 使用BigInteger防止溢出，利用HashMap存储前（n-1）项加快计算速度
     *
     * @param n
     * @return
     */
    private static BigInteger fibonacci(int n){
        return preValue.computeIfAbsent(n,(key)-> fibonacci(key-1).add(fibonacci(n-2)));
    }
}
