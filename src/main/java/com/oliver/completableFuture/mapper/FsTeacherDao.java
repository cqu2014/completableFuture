package com.oliver.completableFuture.mapper;

import com.oliver.completableFuture.entity.FsTeacher;
import com.oliver.completableFuture.entity.FsTeacherExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * FsTeacherMapper继承基类
 */
@Mapper
@Repository
public interface FsTeacherDao extends MyBatisBaseDao<FsTeacher, Long, FsTeacherExample> {
}