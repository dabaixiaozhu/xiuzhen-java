package com.jnshu.service.impl;


import com.jnshu.mapper.CourseUserMapper;
import com.jnshu.mapper.UserCourseMapper;
import com.jnshu.mapper.UserMapper;
import com.jnshu.pojo.CourseUser;
import com.jnshu.pojo.User;
import com.jnshu.pojo.UserCourse;
import com.jnshu.service.web.WebUserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author admin
 */
@Service
public class WebUserServiceImpl implements WebUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    UserCourseMapper userCourseMapper;

    @Autowired
    CourseUserMapper courseUserMapper;

    @Override
    public List<UserCourse> selectAll(Long userId) {
        return userCourseMapper.selectAll(userId);
    }

    @Override
    public List<UserCourse> getAll(Long userId) {
        return userCourseMapper.getAll(userId);
    }

    @Override
    public Integer insert(Long userId, Long courseId) {
        CourseUser courseUser = new CourseUser();
        courseUser.setUserId(userId);
        courseUser.setCourseId(courseId);
        courseUser.setPurchased(1);
        courseUser.setCollect(0);
        courseUser.setCreatAt(System.currentTimeMillis());

        int result = this.courseUserMapper.insert(courseUser);
        return result;
    }

    @Override
    public Integer updateByPrimaryKeySelective(Long userId, Long courseId) {
        CourseUser courseUser = new CourseUser();
        courseUser.setUserId(userId);
        courseUser.setCourseId(courseId);
        courseUser.setPurchased(1);
        courseUser.setCollect(0);

        int result = this.courseUserMapper.updateByPrimaryKeySelective(courseUser);
        return result;
    }

    @Override
    public int deleteByPrimaryKey(Long userId, Long courseId) {
        return courseUserMapper.deleteByPrimaryKey(userId,courseId);
    }

    @Override
    public CourseUser selectById(Long userId, Long courseId) {
        return courseUserMapper.selectById(userId,courseId);
    }

    @Override
    public Integer insertKey(Long userId, Long courseId) {
        CourseUser courseUser = new CourseUser();
        courseUser.setUserId(userId);
        courseUser.setCourseId(courseId);
        courseUser.setPurchased(0);
        courseUser.setCollect(1);
        courseUser.setCreatAt(System.currentTimeMillis());

        int result = this.courseUserMapper.insert(courseUser);
        return result;
    }

    @Override
    public Integer updateKey(Long userId, Long courseId) {
        CourseUser courseUser = new CourseUser();
        courseUser.setUserId(userId);
        courseUser.setCourseId(courseId);
        courseUser.setCollect(1);

        int result = this.courseUserMapper.updateByPrimaryKeySelective(courseUser);
        return result;
    }

    @Override
    public Integer selectKey(Long userId, Long courseId) {
        return courseUserMapper.selectKey(userId,courseId);
    }

    @Override
    public Integer selectByIdKey(Long id) {
        return userMapper.selectByIdKey(id);
    }

    @Override
    public Integer updateByIdKey(Long id, Integer score, Integer price) {
        User user = new User();
        user.setId(id);
        user.setScore(score);

        int result = this.userMapper.updateByIdKey(user);
        return result;
    }
}
