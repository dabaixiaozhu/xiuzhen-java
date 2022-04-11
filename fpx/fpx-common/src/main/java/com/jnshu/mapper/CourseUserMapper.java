package com.jnshu.mapper;

import com.jnshu.pojo.CourseUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author admin
 */
@Repository
@Mapper
public interface CourseUserMapper {

    int updateByPrimaryKeySelective(CourseUser record);

    int deleteByPrimaryKey(@Param("userId")Long userId, @Param("courseId")Long courseId);

    int insert(CourseUser record);

    CourseUser selectById(@Param("userId")Long userId,@Param("courseId")Long courseId);

    Integer selectKey(@Param("userId")Long userId,@Param("courseId")Long courseId);
}
