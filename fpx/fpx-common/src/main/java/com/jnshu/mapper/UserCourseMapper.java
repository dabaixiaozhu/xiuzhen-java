package com.jnshu.mapper;

import com.jnshu.pojo.UserCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author admin
 */
@Repository
@Mapper
public interface UserCourseMapper {

    List<UserCourse> selectAll(Long userId);
    //查询已购课程

    List<UserCourse> getAll(Long userId);
    //查询收藏课程

    List<UserCourse> selectById(@Param("userId")Long userId,@Param("grade") String grade);
    //科目课程

    List<UserCourse> selectByIdAll(@Param("userId")Long userId,@Param("grade") String grade,@Param("subject") String subject);
    //更多课程

    UserCourse getById(@Param("userId") Long userId,@Param("id")Long id);
    //课程展示

    List<UserCourse> searchCourse(@Param("userId") Long userId,@Param("grade") String grade,@Param("title") String title);
}
