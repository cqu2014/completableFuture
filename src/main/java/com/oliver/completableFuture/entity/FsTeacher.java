package com.oliver.completableFuture.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 
 * 未来学习教师信息表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FsTeacher implements Serializable {
    private Long id;

    private String teacher_name;

    private String phone_no;

    /**
     * 职称 0-实习 1-讲师 2-副教授
     */
    private Byte position;

    /**
     * 擅长领域:语文 数学
     */
    private String expertise;

    /**
     * 入职时间
     */
    private LocalDateTime entry_time;

    /**
     * 教龄
     */
    private Integer teaching_age;

    private LocalDateTime last_update_time;

    private LocalDateTime add_time;

    /**
     * 0.正常, 1.已被删除
     */
    private Byte is_del;

    private static final long serialVersionUID = 1L;

    public static FsTeacher teacher(String teacher_name,String phone_no,Byte position,
                                    String expertise,Integer teaching_age){
        FsTeacher fsTeacher = new FsTeacher();
        fsTeacher.setTeacher_name(teacher_name);
        fsTeacher.setPhone_no(phone_no);
        fsTeacher.setPosition(position);
        fsTeacher.setExpertise(expertise);
        fsTeacher.setTeaching_age(teaching_age);
        return fsTeacher;
    }
}