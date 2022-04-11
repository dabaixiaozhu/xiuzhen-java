package com.jnshu.controller;

import com.jnshu.pojo.Course;
import com.jnshu.service.admin.CourseService;
import com.jnshu.util.*;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;





/**
 * 后台课程列表模块
 * @author G
 */
@Controller
@RequestMapping("/admin/course")
public class CourseController {

    @Reference
    CourseService courseService;


    /**
     * 后台课程查询/重置接口
     * 创建人 G
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/fpx")
    public ResponseData queryMessageByPage(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "rows", defaultValue = "10", required = false) Integer rows
    ) {
        PageResult<Course> result = this.courseService.queryByPage(page, rows);
        return ResponseDataUtil.buildSuccess(result);
    }


    /**
     * 后台课程搜索接口
     * 创建人 G
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/fpx/search")
    public ResponseData queryByKey(@RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
                                   @RequestParam(value = "rows", defaultValue = "10", required = false) Integer rows,
                                   @RequestParam(value = "title", required = false) String title,
                                   @RequestParam(value = "grade", required = false) String grade,
                                   @RequestParam(value = "type", required = false) Integer type,
                                   @RequestParam(value = "status", required = false) Integer status,
                                   @RequestParam(value = "creatTimeStart", required = false) Long creatTimeStart,
                                   @RequestParam(value = "creatTimeEnd", required = false) Long creatTimeEnd) {

        PageResult<Course> result = this.courseService.queryByKey(page, rows, title, grade, type, status, creatTimeStart, creatTimeEnd);
        return ResponseDataUtil.buildSuccess(result);
    }


    /**
     * 后台课程新增接口
     * 创建人 G
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/fpx")
    public ResponseData insert(@RequestParam(value = "title") String title,
                               @RequestParam(value = "grade") String grade,
                               @RequestParam(value = "subject") String subject,
                               @RequestParam(value = "type") Integer type,
                               @RequestParam(value = "price") Integer price,
                               @RequestParam(value = "img") String img,
                               @RequestParam(value = "vedio") String vedio,
                               @RequestParam(value = "content") String content,
                               @RequestParam(value = "manage_id") Long manageId) {

        Integer integer = this.courseService.insert(title, grade, subject, type, price, img, vedio, content, manageId);
        return ResponseDataUtil.buildSuccess();
    }


    /**
     * 后台课程删除接口
     * 创建人 G
     *
     * @return
     */
    @ResponseBody
    @DeleteMapping("/fpx/{id}")
    public ResponseData deleteByPrimaryKey(@PathVariable(value = "id") Long id) {
        courseService.deleteByPrimaryKey(id);
        return ResponseDataUtil.buildSuccess();
    }


    /**
     * 后台课程编辑/数据更新接口
     * 上下架同时使用此接口
     * 创建人 G
     *
     * @return
     */
    @ResponseBody
    @PutMapping("/fpx/{id}")
    public ResponseData updateByPrimaryKeySelective(@PathVariable(value = "id") Long id,
                                                    @RequestParam(value = "title", required = false) String title,
                                                    @RequestParam(value = "grade", required = false) String grade,
                                                    @RequestParam(value = "subject", required = false) String subject,
                                                    @RequestParam(value = "type", required = false) Integer type,
                                                    @RequestParam(value = "price", required = false) Integer price,
                                                    @RequestParam(value = "img", required = false) String img,
                                                    @RequestParam(value = "vedio", required = false) String vedio,
                                                    @RequestParam(value = "content", required = false) String content,
                                                    @RequestParam(value = "status", required = false) Integer status,
                                                    @RequestParam(value = "manage_id") Long manageId) {

        Integer integer = this.courseService.updateByPrimaryKeySelective(id, title, grade, subject, type, price, img, vedio, content, status, manageId);
        return ResponseDataUtil.buildSuccess();
    }


    /**
     * 后台课程编辑/数据回显接口
     * 创建人 G
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/fpx/{id}")
    public ResponseData selectByPrimaryKey(@PathVariable(value = "id") Long id) {
        Course course = courseService.selectByPrimaryKey(id);
        return ResponseDataUtil.buildSuccess(course);

    }


}