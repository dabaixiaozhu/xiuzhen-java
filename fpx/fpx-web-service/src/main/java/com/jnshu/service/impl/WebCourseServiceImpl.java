package com.jnshu.service.impl;

import com.jnshu.mapper.BannerMapper;
import com.jnshu.mapper.UserCourseMapper;
import com.jnshu.pojo.Banner;
import com.jnshu.pojo.UserCourse;
import com.jnshu.service.web.WebCourseService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author admin
 */
@Service
public class WebCourseServiceImpl implements WebCourseService {

    @Autowired
    BannerMapper bannerMapper;

    @Autowired
    UserCourseMapper userCourseMapper;

    @Override
    public List<Banner> select() {
        return bannerMapper.select();
    }

    @Override
    public List<UserCourse> selectById(Long userId, String grade) {
        return userCourseMapper.selectById(userId,grade);
    }

    @Override
    public List<UserCourse> selectByIdAll(Long userId, String grade, String subject) {
        return userCourseMapper.selectByIdAll(userId,grade,subject);
    }

    @Override
    public UserCourse getById(Long userId, Long id) {
        return userCourseMapper.getById(userId,id);
    }

    @Override
    public List<UserCourse> searchCourse(Long userId, String grade, String title) {
        List<UserCourse> result = userCourseMapper.searchCourse(userId,grade,'%' + title + '%');
        return result;
    }


}
