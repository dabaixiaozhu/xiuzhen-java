package com.jnshu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jnshu.mapper.ManageMapper;
import com.jnshu.mapper.PermissionMapper;
import com.jnshu.mapper.RoleMapper;
import com.jnshu.mapper.RolePermissionMapper;
import com.jnshu.pojo.*;

import com.jnshu.service.admin.RoleService;
import com.jnshu.util.PageResult;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private ManageMapper manageMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    /**
     * 1.分页查询/重置
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResult<Role> queryRoleByPage(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<Role> roles = this.roleMapper.select(null);

        PageInfo<Role> pageInfo = new PageInfo<>(roles);
        PageResult<Role> result = new PageResult<>(pageInfo.getPageNum(), pageInfo.getSize(), (int) pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
        return result;
    }

    /**
     * 2.新增角色/查询名称
     * @param name
     * @return
     */
    @Override
    public Integer addRoleFindByName(String name) {
        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",name);
        List<Role> roleList = this.roleMapper.selectByExample(example);
        if (roleList != null && roleList.size() >0){
            return 0;
        }else {
            return 1;
        }
    }

    /**
     * 3.新增角色
     * @param username
     * @param ids
     * @return
     */
    @Override
    public Integer addRole(String username, Long[] ids,Long manageId) {
        // 1.往tb_role表中插入数据
        Role role = new Role();
        role.setName(username);

        long update = System.currentTimeMillis();
        role.setUpdateAt(update);
        role.setUpdateBy(manageId);
        role.setCreatAt(update);
        role.setCreatBy(manageId);
        int insert = this.roleMapper.insert(role);

        if (insert == 1){
            Long id1 = role.getId();
            // 2.往tb_role_permission表中插入数据
            for (Long id : ids) {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(id1);
                rolePermission.setPermissionId(id);
                int insert1 = this.rolePermissionMapper.insert(rolePermission);
            }
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 4.删除/删除查询
     * @param id
     * @return
     */
    @Override
    public Integer deleteRoleFindById(Long id) {
        // 1.查询此角色下是否还绑定后台账号
        // 获取tb_role对应主键id的name
        Role role = this.roleMapper.selectByPrimaryKey(id);
        String roleName = role.getName();
        Example example = new Example(Manage.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("role",roleName);
        List<Manage> manages = this.manageMapper.selectByExample(example);
        if (manages.size()>0){
            // 还有此用户返回0
            return 0;
        }

        // 2.无绑定用户删除tb_role表和tb_role_permission表对应id内容
        // 2.1删除tb_role表对应id内容
        int result1 = this.roleMapper.deleteByPrimaryKey(id);

        // 2.2删除tb_role_permission表对应id内容
        Example example1 = new Example(RolePermission.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("roleId",id);
        int result2 = this.rolePermissionMapper.deleteByExample(example1);
        if (result1 == 1 && result2 > 0){
            return 1;
        }
        return 2;
    }

    /**
     * 6.编辑/数据回显
     * @param id
     * @return
     */
    @Override
    public RoleCombine editRoleFindById(Long id) {
        // 1.查出parent_id=0对应的内容
        Example example = new Example(Permission.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId",0);
        List<Permission> permissions = this.permissionMapper.selectByExample(example);

        // 2.parent_id不为0的set集合,set集合中包含拥有子集的权限id
        Set<Long> integers = this.permissionMapper.selecByParentIdZero();

        // 3.通过角色id，从tb_role表中获取角色名
        Role role = this.roleMapper.selectByPrimaryKey(id);
        String roleName = role.getName();

        // 4.获取当前角色拥有的权限id
        List<Long> rolePermissions = this.rolePermissionMapper.findpermissionIdByRoleId(id);

        // 5.获取当前角色拥有的父角色的内容
        List<Permission> permissionsResults = new ArrayList<>();
        for (Permission permission : permissions) {
            if (rolePermissions.contains(permission.getSid())){
                permissionsResults.add(permission);
            }
        }

        // 6.父子级
        // 7.父子级
        if (permissionsResults.size() != 0){
            for (Permission permission : permissionsResults) {
                if (integers.contains(permission.getSid())){
                    Example example3 = new Example(Permission.class);
                    Example.Criteria criteria3 = example3.createCriteria();
                    criteria3.andEqualTo("parentId",permission.getSid());
                    List<Permission> permissions1 = this.permissionMapper.selectByExample(example3);

                    List<Permission> resultPermission = new ArrayList<>();
                    for (Permission permission1 : permissions1) {
                        if (rolePermissions.contains(permission1.getSid())){
                            resultPermission.add(permission1);
                        }
                    }
                    permission.setRoleChildren(resultPermission);
                }
            }
        }

        // 8.返回RoleCombine对象
        RoleCombine roleCombine = new RoleCombine();
        roleCombine.setChildren(permissionsResults);
        roleCombine.setId(id);
        roleCombine.setRole(roleName);
        return roleCombine;
    }

    /**
     * 7.编辑
     * @param id
     * @param username
     * @param ids
     * @param manageId
     * @return
     */
    @Override
    public Integer editRole(Long id, String username, Long[] ids, Long manageId) {
        // 1.修改tb_manage表中role字段内容（角色名称）
        // 1.1根据角色id查询角色名称（未改变前的）
        Role role1 = this.roleMapper.selectByPrimaryKey(id);
        String name = role1.getName();
        // 1.2修改tb_manage表中role字段内容（角色名称）
        Example example = new Example(Manage.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("role",name);
        Manage manage = new Manage();
        manage.setRole(username);
        int result3 = this.manageMapper.updateByExampleSelective(manage, example);


        // 1.往tb_role表中新增数据
        Role role = new Role();
        role.setId(id);
        role.setName(username);
        role.setUpdateAt(System.currentTimeMillis());
        role.setUpdateBy(manageId);
        int result1 = this.roleMapper.updateByPrimaryKeySelective(role);

        // 2.删除tb_role_permission表中对应roleId的内容
        Example example1 = new Example(RolePermission.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("roleId",id);
        int result2 = this.rolePermissionMapper.deleteByExample(example1);

        // 3.往tb_role_permission表中添加对应数据
        for (Long aLong : ids) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(id);
            rolePermission.setPermissionId(aLong);
            int insert1 = this.rolePermissionMapper.insert(rolePermission);
        }

        if (result1 >= 0 && result2 >=0 && result3>=0){
            return 1;
        }
        return 0;
    }
}
