package com.jnshu.controller;

import com.jnshu.mapper.UserCenterMapper;
import com.jnshu.mapper.UserMapper;
import com.jnshu.pojo.User;
import com.jnshu.pojo.UserCenter;
import com.jnshu.pojo.UserCourse;
import com.jnshu.service.web.WebUserService;
import com.jnshu.util.ResponseData;
import com.jnshu.util.ResponseDataUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * @author L
 * 个人中心
 * @author admin
 */
@RestController
@RequestMapping("/web/user")
public class WebUserController {

    @Reference
    private WebUserService webUserService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserCenterMapper userCenterMapper;


    /**
     * 1.个人中心
     * @param id
     * @return
     */
    @GetMapping("/fpx/{id}")
    public ResponseData editManageFindById(@PathVariable(value = "id")Long id){
        UserCenter userCenter = this.userCenterMapper.selectByPrimaryKey(id);
        return ResponseDataUtil.buildSuccess(userCenter);
    }


    /**
     * 2.前台个人中心已购课程接口
     * 创建人 G
     * @return
     */
    @ResponseBody
    @GetMapping("pay/fpx/{user_id}")
    public ResponseData selectAll(@PathVariable(value = "user_id")Long userId){
        List<UserCourse> result = this.webUserService.selectAll(userId);
        return ResponseDataUtil.buildSuccess(result);
    }

    /**
     * 3.前台个人中心收藏课程接口
     * 创建人 G
     * @return
     */
    @ResponseBody
    @GetMapping("/collect/fpx/{user_id}")
    public ResponseData getAll(@PathVariable(value = "user_id")Long userId){
        List<UserCourse> result = this.webUserService.getAll(userId);
        return ResponseDataUtil.buildSuccess(result);
    }

}
