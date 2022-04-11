package com.jnshu.service.impl;

import com.jnshu.mapper.*;
import com.jnshu.pojo.Permission;
import com.jnshu.pojo.PermissionSon;
import com.jnshu.pojo.RoleCombine;
import com.jnshu.service.admin.WelcomeService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class WelcomeServiceImpl implements WelcomeService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private ManageMapper manageMapper;

    @Autowired
    private PermissionSonMapper permissionSonMapper;

    /**
     * 1.欢迎页
     * @param id
     * @return
     */
    @Override
    public RoleCombine welcomeById(Long id) {
        // 1.查出parent_id=0对应的内容
        Example example = new Example(Permission.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId",0);
        List<Permission> permissions = this.permissionMapper.selectByExample(example);

        // 2.parent_id不为0的set集合,set集合中包含对应的
        Set<Long> integers = this.permissionMapper.selecByParentIdZero();

        // 3.获取用户对应的权限id
        String roleName = this.manageMapper.findRoleNameByid(id);
        Long roleId = this.manageMapper.findIdByRoleName(roleName);
        // 根据roleId从tb_role_permission表中查询出对应permisionId
        List<Long> rolesId = this.manageMapper.findRolesById(roleId);

        // 4.获取用户拥有的父权限的内容
        List<Permission> permissionsResults = new ArrayList<>();
        for (Permission permission : permissions) {
            if (rolesId.contains(permission.getSid())){
                permissionsResults.add(permission);
            }
        }

        // 5.父子级
        if (permissionsResults.size() != 0){
            for (Permission permission : permissionsResults) {
                // 有子项目添加子项目
                if (integers.contains(permission.getSid())){
                    Example example3 = new Example(PermissionSon.class);
                    Example.Criteria criteria3 = example3.createCriteria();
                    criteria3.andEqualTo("parentId",permission.getSid());
                    List<PermissionSon> permissions1 = this.permissionSonMapper.selectByExample(example3);
                    int i = 1;
                    if (permissions1.size()>0){
                        for (PermissionSon permissionSon : permissions1) {
                            permissionSon.setSid(permission.getSid()+""+"-"+i);
                            i++;
                        }
                    }
                    permission.setWelcomeChildren(permissions1);
                }
                // 无子项目添加自己
                else {
                    Example example4 = new Example(PermissionSon.class);
                    Example.Criteria criteria4 = example4.createCriteria();
                    criteria4.andEqualTo("id",permission.getSid());
                    List<PermissionSon> permissionSons = this.permissionSonMapper.selectByExample(example4);
                    for (PermissionSon permissionSon : permissionSons) {
                        permissionSon.setSid(permissionSon.getId().toString());
                    }
                    permission.setWelcomeChildren(permissionSons);
                }
            }
        }

        // 6.返回RoleCombine对象
        RoleCombine roleCombine = new RoleCombine();
        roleCombine.setChildren(permissionsResults);
        roleCombine.setId(id);
        roleCombine.setRole(roleName);
        return roleCombine;
    }
}
