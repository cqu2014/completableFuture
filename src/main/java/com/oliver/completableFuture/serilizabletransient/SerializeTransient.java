package com.oliver.completableFuture.serilizabletransient;

import cn.hutool.json.JSONUtil;
import lombok.Data;

import java.io.*;

/**
 * @author Oliver Wang
 * @description
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2020/8/17
 * @since
 */
@Data
public class SerializeTransient implements Serializable {
    /**
     * 静态属性 不会被序列化
     */
    private static int uid_id = 12525;
    /**
     * transient 修饰的属性不会被序列化
     */
    private transient String idCard;
    /**
     * 普通属性被序列化
     */
    private String name;

    SerializeTransient(String idCard,String name){
        this.idCard = idCard;
        this.name = name;
    }

    public static void main(String[] args) {
        SerializeTransient serializeTransient = new SerializeTransient("123456","zhansgan");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:/test.txt"));
            ObjectInputStream ios = new  ObjectInputStream(new FileInputStream("D:/test.txt"))){
            oos.writeObject(serializeTransient);
            serializeTransient = (SerializeTransient) ios.readObject();
            System.out.println(serializeTransient);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
