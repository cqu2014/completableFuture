package com.oliver.completableFuture.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 
 * 未来学校学生信息表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class FsStudent implements Serializable {
    private Long id;

    private String student_name;

    private String student_no;

    /**
     * 联系方式
     */
    private String connection_info;

    /**
     * 入学时间
     */
    private LocalDateTime entry_time;

    /**
     * 年级:1-6
     */
    private Integer grade;

    /**
     * 班级
     */
    private String class_roon;

    private LocalDateTime last_update_time;

    private LocalDateTime add_time;

    /**
     * 0.正常, 1.已被删除
     */
    private Byte is_del;

    private static final long serialVersionUID = 1L;

    public static FsStudent student(String student_name,String student_no,String connection_info,
                                    Integer grade, String class_roon){
        FsStudent student = new FsStudent();
        student.setStudent_name(student_name);
        student.setStudent_no(student_no);
        student.setConnection_info(connection_info);
        // check
        student.setGrade(grade);
        student.setClass_roon(class_roon);
        return student;
    }
}