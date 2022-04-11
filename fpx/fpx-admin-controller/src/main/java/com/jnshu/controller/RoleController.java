package com.jnshu.controller;

import com.jnshu.mapper.ManageMapper;
import com.jnshu.mapper.RoleMapper;
import com.jnshu.pojo.Role;
import com.jnshu.pojo.RoleCombine;
import com.jnshu.service.admin.RoleService;
import com.jnshu.util.PageResult;
import com.jnshu.util.ResponseData;
import com.jnshu.util.ResponseDataUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author L
 * 角色
 */
@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Reference(retries = 1,timeout = 100000)
    private RoleService roleService;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ManageMapper manageMapper;

    /**
     * 1.查询/重置
     * @return
     */
    @GetMapping("/fpx")
    public ResponseData queryMessageByPage(){
        List<Role> select = this.roleMapper.select(null);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",select.size());
        for (Role role : select) {
            role.setCreatName(this.manageMapper.selectByPrimaryKey(role.getCreatBy()).getName());
        }
        map.put("roles",select);
        return ResponseDataUtil.buildSuccess(map);
    }


    /**
     * 2.新增角色/查询名称
     * @param name
     * @return
     */
    @GetMapping("/fpx/add")
    public ResponseData addRoleFindByName(@RequestParam(value = "name")String name){
        Integer integer = this.roleService.addRoleFindByName(name);
        if (integer == 1){
            return ResponseDataUtil.buildSuccess();
        }else {
            return ResponseDataUtil.buildError();
        }
    }

    /**
     * 3.新增角色
     * @param username
     * @param ids
     * @return
     */
    @PostMapping("/fpx")
    public ResponseData addRole(
            @RequestParam(value = "username")String username,
            @RequestParam(value = "ids")Long[] ids,
            @RequestParam(value = "manage_id")Long manageId
    ){
        Integer integer = this.roleService.addRole(username,ids,manageId);
        if (integer == 1){
            return ResponseDataUtil.buildSuccess();
        }else {
            return ResponseDataUtil.buildError();
        }
    }

    /**
     * 4.删除
     * @param id
     * @return
     */
    @DeleteMapping("/fpx/{id}")
    public ResponseData deleteRoleFindById(@PathVariable(value = "id")Long id){
        Integer integer = this.roleService.deleteRoleFindById(id);
        if (integer == 1){
            return ResponseDataUtil.buildSuccess();
        }else if (integer == 0){
            return ResponseDataUtil.buildBingdingError();
        }else {
            return ResponseDataUtil.buildError();
        }
    }

    /**
     * 5.编辑/数据回显
     * @param id
     * @return
     */
    @GetMapping("/fpx/edit/{id}")
    public ResponseData editRoleFindById(@PathVariable(value = "id")Long id){
        RoleCombine roleCombine = this.roleService.editRoleFindById(id);
        return ResponseDataUtil.buildSuccess(roleCombine);
    }

    /**
     * 7.编辑/数据保存
     * @param id
     * @return
     */
    @PutMapping("/fpx/edit")
    public ResponseData editRole(
            @RequestParam(value = "id")Long id,
            @RequestParam(value = "name")String username,
            @RequestParam(value = "ids")Long[] ids,
            @RequestParam(value = "manage_id")Long manageId
    ){
        Integer integer = this.roleService.editRole(id,username,ids,manageId);
        if (integer == 1){
            return ResponseDataUtil.buildSuccess();
        }else {
            return ResponseDataUtil.buildError();
        }
    }
}
