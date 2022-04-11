package com.jnshu.controller;

import com.jnshu.pojo.RoleCombine;
import com.jnshu.service.admin.WelcomeService;
import com.jnshu.util.ResponseData;
import com.jnshu.util.ResponseDataUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author L
 * 欢迎页
 */
@RestController
@RequestMapping("/admin/welcome")
public class WelcomeController {


    @Reference
    private WelcomeService welcomeService;

    /*1.欢迎页*/
    @GetMapping("/fpx/{id}")
    public ResponseData editRoleFindById(@PathVariable(value = "id")Long id){
        RoleCombine roleCombine = this.welcomeService.welcomeById(id);
        return ResponseDataUtil.buildSuccess(roleCombine);
    }
}
