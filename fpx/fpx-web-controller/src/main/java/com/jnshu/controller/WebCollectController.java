package com.jnshu.controller;

import com.jnshu.pojo.CourseUser;
import com.jnshu.service.web.WebUserService;
import com.jnshu.util.ResponseData;
import com.jnshu.util.ResponseDataUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;



/**
 * 前台收藏模块
 * @author admin
 */
@RestController
@RequestMapping("/web/collect")
public class WebCollectController {

    @Reference
    WebUserService webUserService;


    /**
     * 前台收藏新增接口
     * 创建人 G
     * @return
     */
    @ResponseBody
    @PostMapping("/fpx")
    public ResponseData insertKey(@RequestParam(value = "user_id")Long userId,
                                  @RequestParam(value = "course_id")Long courseId)
    {
        CourseUser x = this.webUserService.selectById(userId,courseId);
        if (x != null){
            Integer integer = this.webUserService.updateKey(userId,courseId);
        }else {
            Integer integer = this.webUserService.insertKey(userId, courseId);
        }
        return ResponseDataUtil.buildSuccess();
    }


    /**
     * 前台收藏删除接口
     * 创建人 G
     * @return
     */
    @ResponseBody
    @DeleteMapping("/fpx")
    public ResponseData deleteByPrimaryKey(@RequestParam(value = "user_id")Long userId,
                                           @RequestParam(value = "course_id")Long courseId)
    {
        int x = this.webUserService.selectKey(userId,courseId);
        if (x != 0){
            Integer integer = this.webUserService.updateByPrimaryKeySelective(userId, courseId);
        }else {
            Integer integer = this.webUserService.deleteByPrimaryKey(userId,courseId);
        }
        return ResponseDataUtil.buildSuccess();
    }

}
