package com.oliver.completableFuture.interrupt;

import cn.hutool.core.lang.Console;

import java.util.concurrent.TimeUnit;

/**
 * @author Oliver Wang
 * @description
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2020/9/15
 * @since
 */
public class ThreadResponseInterrupt {

    public static void main(String[] args) throws InterruptedException {
       // startAndEndState();
       // runnable();
        waiting();
    }

    /**
     * NEW and TERMINATED 忽略中断
     */
    private static void startAndEndState() throws InterruptedException {
        Thread newThread = new Thread();
        System.out.println(newThread.getState());
        newThread.interrupt();
        System.out.println(newThread.isInterrupted());

        newThread.start();
        newThread.join();
        System.out.println(newThread.getState());
        newThread.interrupt();
        System.out.println(newThread.isInterrupted());
    }

    /**
     * RUNNABLE 状态线程仅仅设置 中断标志位
     */
    private static void runnable() throws InterruptedException {
        Thread interruptThread = new InterruptThread();
        interruptThread.start();

        System.out.println(interruptThread.getState());

        interruptThread.interrupt();
        // 主线程等待 myThread被中断之后打印其状态
        Thread.sleep(1000);
        System.out.println(interruptThread.isInterrupted());
        System.out.println(interruptThread.getState());
    }

    private static class InterruptThread extends Thread{
        @Override
        public void run() {
            while (true){
                if (Thread.currentThread().isInterrupted()){
                    Console.log("{}线程被终止",Thread.currentThread().getName());
                    break;
                }
            }
        }
    }

    /**
     * 中断 WAITING/TIMED_WAITING状态的线程会抛 异常
     */
    private static void waiting() throws InterruptedException {
        Thread waiting = new WaitingThread();
        waiting.start();

        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println(waiting.getState());
        waiting.interrupt();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println(waiting.isInterrupted());
    }
    private static class WaitingThread extends Thread{
        @Override
        public void run() {
            synchronized (this){
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("i am waiting but facing interruptexception now");
                }
            }
        }
    }
}
