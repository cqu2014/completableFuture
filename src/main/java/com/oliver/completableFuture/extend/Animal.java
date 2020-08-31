package com.oliver.completableFuture.extend;

import cn.hutool.core.lang.Console;
import lombok.Data;

/**
 * @author Oliver Wang
 * @description
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2020/8/31
 * @since
 */
@Data
public class Animal {
    private String name;
    private byte agent;
    private Integer ages;

    private void voice(){
        Console.log("Animal voice .......");
    }

    public void run(){
        Console.log("Animal run with the legs");
    }

    public void runAndVoice(){
        Console.log("Animal voice .......");
        run();
    }
}
