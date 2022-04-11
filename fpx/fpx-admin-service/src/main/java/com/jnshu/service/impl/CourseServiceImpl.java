package com.jnshu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jnshu.mapper.CourseMapper;
import com.jnshu.mapper.ManageMapper;
import com.jnshu.pojo.Course;
import com.jnshu.pojo.Manage;
import com.jnshu.pojo.Message;
import com.jnshu.service.admin.CourseService;
import com.jnshu.util.PageResult;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;


import java.util.List;

/**
 * @author admin
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    private ManageMapper manageMapper;


    @Override
    public PageResult<Course> queryByPage(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<Course> courses = this.courseMapper.selectAll();

        PageInfo<Course> pageInfo = new PageInfo<>(courses);
        PageResult<Course> result = new PageResult<>(pageInfo.getPageNum(), pageInfo.getSize(), (int) pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
        return result;
    }

    @Override
    public PageResult<Course> queryByKey(Integer page, Integer rows, String title, String grade, Integer type, Integer status, Long creatTimeStart,Long creatTimeEnd) {
        PageHelper.startPage(page,rows);
        List<Course> courses = this.courseMapper.getAll(title, grade, type, status, creatTimeStart, creatTimeEnd);

        PageInfo<Course> pageInfo = new PageInfo<>(courses);
        PageResult<Course> result = new PageResult<>(pageInfo.getPageNum(), pageInfo.getSize(), (int) pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
        return result;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return courseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(String title,String grade,String subject,Integer type,Integer price,String img,String vedio,String content,Long manageId) {
        Course course = new Course();
        course.setTitle(title);
        course.setGrade(grade);
        course.setSubject(subject);
        course.setType(type);
        course.setPrice(price);
        course.setImg(img);
        course.setVedio(vedio);
        course.setContent(content);
        course.setStatus(0);

        long update = System.currentTimeMillis();
        course.setUpdateAt(update);
        course.setUpdateBy(manageId);
        course.setCreatAt(update);
        course.setCreatBy(manageId);
        course.setCreatName(this.manageMapper.selectByPrimaryKey(manageId).getName());
        course.setUpdateName(this.manageMapper.selectByPrimaryKey(manageId).getName());
        int result = this.courseMapper.insert(course);
        return result;
    }

    @Override
    public Course selectByPrimaryKey(Long id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public Course select(Long courseId) {
        return courseMapper.selectByPrimaryKey(courseId);
    }

    @Override
    public Integer updateByPrimaryKeySelective(Long id,String title,String grade,String subject,Integer type,Integer price,String img,String vedio,String content,Integer status,Long manageId) {
        Course course = new Course();
        course.setId(id);
        course.setTitle(title);
        course.setGrade(grade);
        course.setSubject(subject);
        course.setType(type);
        course.setPrice(price);
        course.setImg(img);
        course.setVedio(vedio);
        course.setContent(content);
        course.setStatus(status);
        course.setUpdateAt(System.currentTimeMillis());
        course.setUpdateBy(manageId);
        course.setUpdateName(this.manageMapper.selectByPrimaryKey(manageId).getName());
        int result = this.courseMapper.updateByPrimaryKeySelective(course);
        return result;
    }


}
