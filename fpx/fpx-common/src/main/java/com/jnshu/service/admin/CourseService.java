package com.jnshu.service.admin;

import com.jnshu.pojo.Course;
import com.jnshu.util.PageResult;

import java.util.List;

/**
 * @author admin
 */
public interface CourseService {

    PageResult<Course> queryByPage(Integer page, Integer rows);

    PageResult<Course> queryByKey(Integer page, Integer rows, String title,String grade,Integer type, Integer status,Long creatTimeStart,Long creatTimeEnd);

    int deleteByPrimaryKey(Long id);

    Integer insert(String title,String grade,String subject,Integer type,Integer price,String img,String vedio,String content,Long manageId);

    Course selectByPrimaryKey(Long id);

    Course select(Long courseId);

    Integer updateByPrimaryKeySelective(Long id,String title,String grade,String subject,Integer type,Integer price,String img,String vedio,String content,Integer status,Long manageId);

}
