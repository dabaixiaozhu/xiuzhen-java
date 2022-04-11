package com.jnshu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jnshu.mapper.ManageMapper;
import com.jnshu.mapper.ManageRoleMapper;
import com.jnshu.mapper.RoleMapper;
import com.jnshu.pojo.Manage;
import com.jnshu.pojo.ManageRole;
import com.jnshu.pojo.Message;


import com.jnshu.pojo.Role;
import com.jnshu.service.admin.ManageService;
import com.jnshu.util.PageResult;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author L
 * 后台账号管理
 */
@Service
public class ManageServiceImpl implements ManageService {
    Logger logger = LoggerFactory.getLogger(ManageServiceImpl.class);
    @Autowired
    private ManageMapper manageMapper;

    @Autowired
    private BCryptPasswordEncoder encoding;

    @Autowired
    private ManageRoleMapper manageRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 1.分页查询/重置
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResult<Manage> queryManageByPage(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<Manage> manages = this.manageMapper.select(null);


        PageInfo<Manage> pageInfo = new PageInfo<>(manages);
        PageResult<Manage> result = new PageResult<>(pageInfo.getPageNum(), pageInfo.getSize(), (int) pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
        return result;
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
    @Override
    public PageResult<Manage> quryManageByKey(Integer page, Integer rows, String id, String name, String pushTimeStart,String pushTimeEnd,String role) {
        // 转int为string
        Example example = new Example(Manage.class);
        Example.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotBlank(id)){
            criteria.andEqualTo("id",id);
        }

        if (StringUtils.isNotBlank(pushTimeStart) && StringUtils.isNotBlank(pushTimeEnd)){
            criteria.andBetween("updateAt",pushTimeStart,pushTimeEnd);
        }

        if (StringUtils.isNotBlank(role)){
            criteria.andEqualTo("role",role);
        }

        // 模糊查询的内容放后面
        if (StringUtils.isNotBlank(name)){
            criteria.andLike("name","%"+name+"%");
        }

        example.setOrderByClause("update_at desc");

        PageHelper.startPage(page,rows);
        List<Manage> messages = this.manageMapper.selectByExample(example);

        PageInfo<Manage> pageInfo = new PageInfo<>(messages);
        PageResult<Manage> result = new PageResult<>(pageInfo.getPageNum(), pageInfo.getSize(), (int) pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
        return result;
    }

    /**
     * 4.新增用户
     * @param name
     * @param role
     * @param password
     * @param manageId
     * @return
     */
    @Override
    public Integer upTwoTable(String name, String role, String password, Long manageId) {

        // 1.往tb_manage表中添加数据
        Manage manage = new Manage();
        manage.setName(name);
        manage.setPassword(encoding.encode(password));
        manage.setRole(role);

        long update = System.currentTimeMillis();
        manage.setUpdateAt(update);
        manage.setUpdateBy(manageId);
        manage.setCreatAt(update);
        manage.setCreatBy(manageId);
        manage.setUpdateName(this.manageMapper.selectByPrimaryKey(manageId).getName());
        manage.setCreatName(this.manageMapper.selectByPrimaryKey(manageId).getName());

        int result = this.manageMapper.insert(manage);

        // 2.获取新加入tb_manage表中的主键id
        Long resultId = manage.getId();
        Manage manage1 = this.manageMapper.selectByPrimaryKey(resultId);
        String role1 = manage1.getRole();
        Long idByRoleName = this.manageMapper.findIdByRoleName(role1);

        // 3.往tb_manage_role表中添加数据
        ManageRole manageRole = new ManageRole();
        manageRole.setManageId(resultId);
        manageRole.setRoleId(idByRoleName);
        this.manageRoleMapper.insert(manageRole);
        return result;
    }

    /**
     * 5.删除
     * @param id
     * @return
     */
    @Override
    public Integer deleteManage(Long id) {
        // 1.删除tb_manage中对应id的内容
        int result = this.manageMapper.deleteByPrimaryKey(id);

        // 2.删除tb_manage_role对应内容
        Example example = new Example(ManageRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("manageId",id);
        this.manageRoleMapper.deleteByExample(example);
        return result;
    }

    /**
     * 8.编辑
     * @param id
     * @param manageId
     * @param name
     * @param password
     * @param role
     * @return
     */
    @Override
    public Integer editManage(Long id, Long manageId, String name, String password, String role) {
        // 1.修改tb_manage表
        Manage manage = new Manage();
        manage.setId(id);
        manage.setName(name);
        manage.setPassword(encoding.encode(password));
        manage.setUpdateAt(System.currentTimeMillis());
        manage.setUpdateName(this.manageMapper.selectByPrimaryKey(manageId).getName());
        manage.setUpdateBy(manageId);
        manage.setRole(role);

        // 2.修改角色，也需要修图tb_manage_role表
        if (!role.equals(this.manageMapper.selectByPrimaryKey(id).getRole())){
            // 2.1根据角色名获取tb_role的主键id
            Example example = new Example(Role.class);
            Example.Criteria criteria = example.createCriteria();
            Example.Criteria name1 = criteria.andEqualTo("name", role);
            List<Role> roleList = this.roleMapper.selectByExample(example);

            if (roleList != null && roleList.size()>0){
                Role role1 = roleList.get(0);
                Long id1 = role1.getId();

                // 根据manageId查出tb_manage_role的主键
                Example example1 = new Example(ManageRole.class);
                Example.Criteria criteria1 = example1.createCriteria();
                criteria1.andEqualTo("manageId",id);
                List<ManageRole> manageRoles = this.manageRoleMapper.selectByExample(example1);
                if (manageRoles != null && manageRoles.size() > 0){
                    ManageRole manageRole = manageRoles.get(0);
                    Long id2 = manageRole.getId();

                    ManageRole manageRole1 = new ManageRole();
                    manageRole1.setId(id2);
                    manageRole1.setManageId(id);
                    manageRole1.setRoleId(id1);
                    this.manageRoleMapper.updateByPrimaryKeySelective(manageRole1);
                }
            }
        }
        int result = this.manageMapper.updateByPrimaryKeySelective(manage);
        return result;
    }
}
