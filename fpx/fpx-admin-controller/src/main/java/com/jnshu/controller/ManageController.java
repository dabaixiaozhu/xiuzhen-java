package com.jnshu.controller;

import com.jnshu.mapper.ManageMapper;
import com.jnshu.mapper.RoleMapper;
import com.jnshu.pojo.Manage;
import com.jnshu.pojo.Role;
import com.jnshu.service.admin.ManageService;
import com.jnshu.util.PageResult;
import com.jnshu.util.ResponseData;
import com.jnshu.util.ResponseDataUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author L
 * 后台账户
 */
@RestController
@RequestMapping("/admin/manage")
public class ManageController {
    Logger logger = LoggerFactory.getLogger(ManageController.class);

    @Reference(retries = 1,timeout = 100000)
    private ManageService manageService;

    @Autowired
    private ManageMapper manageMapper;


    /**
     * 1.分页查询/重置
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/fpx")
    public ResponseData queryManageByPage(
            @RequestParam(value = "page", defaultValue = "1",required = false)Integer page,
            @RequestParam(value = "rows", defaultValue = "10",required = false)Integer rows
    ){
        PageResult<Manage> result = this.manageService.queryManageByPage(page, rows);
        return ResponseDataUtil.buildSuccess(result);
    }


    /**
     * 2.搜索
     * @param page
     * @param rows
     * @param id
     * @param name
     * @param pushTimeStart
     * @param pushTimeEnd
     * @param role
     * @return
     */
    @GetMapping("/fpx/search")
    public ResponseData quryManageByKey(
            @RequestParam(value = "page", defaultValue = "1",required = false)Integer page,
            @RequestParam(value = "rows", defaultValue = "10",required = false)Integer rows,
            @RequestParam(value = "id", required = false)String id,
            @RequestParam(value = "name", required = false)String name,
            @RequestParam(value = "push_time_start",required = false)String pushTimeStart,
            @RequestParam(value = "push_time_end",required = false)String pushTimeEnd,
            @RequestParam(value = "role", required = false)String role
    ){
        PageResult<Manage> result = this.manageService.quryManageByKey(page, rows, id, name, pushTimeStart, pushTimeEnd,role);
        return ResponseDataUtil.buildSuccess(result);
    }

    /**
     * 3.新建/查询用户名是否存在
     * @param name
     * @return
     */
    @GetMapping("/fpx/add")
    public ResponseData addManageFindByName(@RequestParam(value = "name")String name){
        Example example = new Example(Manage.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",name);

        List<Manage> manages = manageMapper.selectByExample(example);
        if (manages !=null && manages.size()!=0){
            return ResponseDataUtil.buildError();
        }
        return ResponseDataUtil.buildSuccess();
    }


    /**
     * 4.新增用户
     * @param name
     * @param role
     * @param password
     * @return
     */
    @PostMapping("/fpx")
    public ResponseData addManage(
            @RequestParam(value = "name")String name,
            @RequestParam(value = "role")String role,
            @RequestParam(value = "password")String password,
            @RequestParam(value = "manage_id")Long manageId
    ){
        Integer integer = this.manageService.upTwoTable(name,role,password,manageId);
        if (integer == 1){
            return ResponseDataUtil.buildSuccess();
        }else {
            return ResponseDataUtil.buildError();
        }
    }

    /**
     * 5.删除
     * @param id
     * @return
     */
    @DeleteMapping("/fpx/{id}")
    public ResponseData deleteManage(@PathVariable(value = "id")Long id){
        Integer result = this.manageService.deleteManage(id);
        if (result == 1){
            return ResponseDataUtil.buildSuccess();
        }
        return ResponseDataUtil.buildError();
    }

    /**
     * 6.编辑/数据回显
     * @param id
     * @return
     */
    @GetMapping("/fpx/edit/{id}")
    public ResponseData editManageFindById(@PathVariable(value = "id")Long id){
        Manage manage = this.manageMapper.selectByPrimaryKey(id);
        return ResponseDataUtil.buildSuccess(manage);
    }


    /**
     * 8.编辑/数据更新
     * @param id
     * @return
     */
    @PutMapping("/fpx/edit")
    public ResponseData editManage(
            @RequestParam(value = "id")Long id,
            @RequestParam(value = "manage_id")Long manageId,
            @RequestParam(value = "name")String name,
            @RequestParam(value = "password")String password,
            @RequestParam(value = "role")String role
    ){
        Integer result = this.manageService.editManage(id,manageId,name,password,role);
        if (result==1){
            return ResponseDataUtil.buildSuccess();
        }
        return ResponseDataUtil.buildError();
    }
}
