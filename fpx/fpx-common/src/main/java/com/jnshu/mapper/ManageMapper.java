package com.jnshu.mapper;

import com.jnshu.pojo.Manage;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface ManageMapper extends Mapper<Manage> {
    /*6.编辑/数据回显*/
    /*6.1根据id获取角色*/
    @Select("select role from tb_manage where id=#{id}")
    String findRoleById(Long id);

    /*6.2根据用户id查询角色名称*/
    @Select("SELECT role from tb_manage WHERE id=#{id}")
    String findRoleNameByid(Long id);

    /*6.3根据角色名称查询角色id*/
    @Select("SELECT id from tb_role WHERE name=#{role}")
    Long findIdByRoleName(String role);

    /*6.4根据角色id查询权限集合*/
    @Select("SELECT permissionId from tb_role_permission WHERE roleId=#{id}")
    List<Long> findRolesById(Long id);

    /*根据name查询主键id*/
    @Select("SELECT id from tb_manage WHERE name=#{name}")
    List<Long> findIdByNames(String name);

}