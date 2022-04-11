package com.jnshu.mapper;

import com.jnshu.pojo.Course;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * 创建人 G
 * @author admin
 */
@Repository
@Mapper
public interface CourseMapper  {

    int deleteByPrimaryKey(Long id);

    int insert(Course record);

    Course selectByPrimaryKey(Long id);

    List<Course> selectAll();

    int updateByPrimaryKeySelective(Course record);

    // 根据课程id查询年级
    @Select("select grade from tb_course where id =#{id}")
    String findGradeById(Long id);

    List<Course> getAll(@Param("title")String title,@Param("grade")String grade,@Param("type")Integer type,@Param("status")Integer status,@Param("creatTimeStart")Long creatTimeStart,@Param("creatTimeEnd")Long creatTimeEnd);
}