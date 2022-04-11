package com.jnshu.controller;


import com.jnshu.mapper.ManageMapper;
import com.jnshu.pojo.Manage;
import com.jnshu.service.admin.LoginService;
import com.jnshu.util.ResponseData;
import com.jnshu.util.ResponseDataUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;

/**
 * @author L
 * 登陆
 */
@RestController
@RequestMapping("/admin/login")
public class LoginController {

    @Reference(retries = 1,timeout = 100000)
    private LoginService loginService;

    @Autowired
    private ManageMapper manageMapper;

    /**
     * 1.登陆
     * @param name
     * @param password
     * @return
     */
    @PostMapping("/fpx")
    public ResponseData Login(
            @RequestParam(value = "name")String name,
            @RequestParam(value = "password")String password
    ){
        Example example = new Example(Manage.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",name);
        List<Manage> manages = this.manageMapper.selectByExample(example);
        if (manages == null || manages.size()==0){
            return ResponseDataUtil.buildUserError();
        }

        Long result =this.loginService.vertifyPassword(name,password);
        if (result != 0L){
            HashMap<String, Object> map = new HashMap<>();
            map.put("id",result);
            return ResponseDataUtil.buildSuccess(map);
        }
        return ResponseDataUtil.buildPasswordError();
    }
}
