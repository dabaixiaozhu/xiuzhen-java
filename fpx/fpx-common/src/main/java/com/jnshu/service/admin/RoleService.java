package com.jnshu.service.admin;

import com.jnshu.pojo.Role;
import com.jnshu.pojo.RoleCombine;
import com.jnshu.util.PageResult;

/**
 * @author L
 * 角色
 */
public interface RoleService {
    /*1.分页查询/重置*/
    PageResult<Role> queryRoleByPage(Integer page, Integer rows);

    /*2.新增角色/查询名称*/
    Integer addRoleFindByName(String name);

    /*3.新增角色*/
    Integer addRole(String username, Long[] ids, Long manageId);

    /*4.删除/删除查询*/
    Integer deleteRoleFindById(Long id);

    /*6.编辑/数据回显*/
    RoleCombine editRoleFindById(Long id);

    /*7.编辑*/
    Integer editRole(Long id, String username, Long[] ids, Long manageId);
}
