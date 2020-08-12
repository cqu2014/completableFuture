package com.oliver.completableFuture.forkjoin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * @author Oliver Wang
 * @description
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2020/8/12
 * @since
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Slf4j
public class CalculatorTask extends RecursiveTask<Long> {

    /**
     * 分割任务的阈值
     */
    private static int SUB_TASK_THRESHOLD = 10;
    private Long[] elements;
    private int start;
    private int end;

    @Override
    protected Long compute() {
        long sum = 0L;
        if (end - start <= SUB_TASK_THRESHOLD){
            log.info("任务数不超过{},线程{}直接计算",SUB_TASK_THRESHOLD,Thread.currentThread().getName());
            for (int i = start; i <= end ; i++) {
                sum += elements[i];
            }
            return sum;
        }
        // 分割任务
        log.info("任务个数超过{},需要拆分任务",SUB_TASK_THRESHOLD);
        int middle = (start + end) / 2;
        CalculatorTask leftTask = new CalculatorTask(elements,start,middle);
        CalculatorTask rightTask = new CalculatorTask(elements,middle+1,end);
        leftTask.fork();
        long rightResult = rightTask.compute();
        return rightResult + leftTask.join();
    }

}
