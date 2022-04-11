package com.jnshu.service.web;

import com.jnshu.pojo.Banner;
import com.jnshu.pojo.UserCourse;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface WebCourseService {

    List<Banner> select();

    List<UserCourse> selectById(Long userId, String grade);

    List<UserCourse> selectByIdAll(Long userId, String grade, String subject);

    UserCourse getById(Long userId,Long id);

    List<UserCourse> searchCourse(Long userId, String grade, String title);
}
