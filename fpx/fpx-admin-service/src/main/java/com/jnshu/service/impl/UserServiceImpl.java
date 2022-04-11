package com.jnshu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jnshu.mapper.UserMapper;
import com.jnshu.pojo.User;


import com.jnshu.service.admin.UserService;
import com.jnshu.util.PageResult;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
/**
 * @author L
 * 前台用户
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 1.分页查询/重置
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResult<User> queryUserByPage(Integer page, Integer rows){
        Example example = new Example(User.class);

        PageHelper.startPage(page,rows);
        List<User> users = this.userMapper.select(null);

        PageInfo<User> pageInfo = new PageInfo<>(users);
        PageResult<User> result = new PageResult<>(pageInfo.getPageNum(), pageInfo.getSize(), (int) pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
        return result;
    }

    /**
     * 2.搜索
     * @param id
     * @param page
     * @param rows
     * @param username
     * @param status
     * @return
     */
    @Override
    public PageResult<User> queryUserByKey(String id, Integer page, Integer rows, String username, String status) {
        // 转int为string
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotBlank(id)){
            criteria.andEqualTo("id",id);
        }

        if (StringUtils.isNotBlank(status)){
            criteria.andEqualTo("status",status);
        }

        // 模糊查询放最后
        if (StringUtils.isNotBlank(username)){
            criteria.andLike("nickname","%"+username+"%");
        }

        example.setOrderByClause("update_at desc");

        PageHelper.startPage(page,rows);
        List<User> users = this.userMapper.selectByExample(example);

        PageInfo<User> pageInfo = new PageInfo<>(users);
        PageResult<User> result = new PageResult<>(pageInfo.getPageNum(), pageInfo.getSize(), (int) pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
        return result;
    }
}
