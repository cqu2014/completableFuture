package com.oliver.completableFuture.extend;

import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Oliver Wang
 * @description
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2020/8/31
 * @since
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Fox extends Animal {
    private String foxSkip;

    public static void main(String[] args) {
        // extendsAttributes();
        // operatorAttribute();
        callMethod();
    }

    @Override
    public void run() {
        Console.log("The fox run is different from Animal");
    }

    /**
     * 1. 父类的private的属性和private方法均未被继承
     * 2. 但是，父类的属性Getter/Setter方法均被继承
     */
    private static void extendsAttributes(){
        Class<?> aClass;
        try {
            aClass = Class.forName("com.oliver.completableFuture.extend.Fox");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
        Field[] declaredFields = aClass.getDeclaredFields();
        Console.log(declaredFields);

        Method[] aClassMethods = aClass.getMethods();
        Console.log(aClassMethods);
    }

    /**
     * 1. Java创建子类实例时会创建父类实例吗？
     *   不会,创建一个对象时，JVM会在堆(heap)中给对象分配空间，
     *   这些空间用来存储当前对象实例属性以及父类的实例属性，
     *   会为父类分配堆内存，但是这块堆内存属于子类的堆内存(栈中只有子类引用)
     * 2. 各个子类有独立的父类属性（堆空间）
     */
    private static void operatorAttribute(){
        Fox fox = new Fox();
        fox.setAgent((byte) 1);
        fox.setAges(12);
        fox.setName("China fox");

        Fox otherFox = new Fox();
        otherFox.setAgent((byte) 2);
        otherFox.setAges(24);
        otherFox.setName("China another fox");

        Console.log(JSONUtil.toJsonStr(fox));
        Console.log(JSONUtil.toJsonStr(otherFox));
    }

    /**
     * 1. 父类public方法依赖其他公有方法时，当子类重写被依赖方法则子类继承的public方法会依赖自己重写的方法
     * 2. 子类重写父类方法则以重写后的为准
     */
    private static void callMethod(){
        Fox fox = new Fox();
        fox.run();
        fox.runAndVoice();
    }
}
