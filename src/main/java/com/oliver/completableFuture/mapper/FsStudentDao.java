package com.oliver.completableFuture.mapper;

import com.oliver.completableFuture.entity.FsStudent;
import com.oliver.completableFuture.entity.FsStudentExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * FsStudentDAO继承基类
 */
@Mapper
@Repository
public interface FsStudentDao extends MyBatisBaseDao<FsStudent, Long, FsStudentExample> {
}