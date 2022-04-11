package com.jnshu.service.web;

import com.jnshu.pojo.CourseUser;
import com.jnshu.pojo.UserCourse;

import java.util.List;

public interface WebUserService {

    List<UserCourse> selectAll(Long userId);
    //查询已购课程

    List<UserCourse> getAll(Long userId);
    //查询收藏课程

    Integer insert(Long userId,Long courseId);

    Integer updateByPrimaryKeySelective(Long userId,Long courseId);

    int deleteByPrimaryKey(Long userId,Long courseId);

    CourseUser selectById(Long userId, Long courseId);

    Integer insertKey(Long userId,Long courseId);

    Integer updateKey(Long userId,Long courseId);

    Integer selectKey(Long userId, Long courseId);

    Integer selectByIdKey(Long id);

    Integer updateByIdKey(Long id,Integer score,Integer price);
}
