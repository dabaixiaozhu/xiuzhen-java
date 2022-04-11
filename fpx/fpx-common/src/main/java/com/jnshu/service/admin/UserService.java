package com.jnshu.service.admin;

import com.jnshu.pojo.User;
import com.jnshu.util.PageResult;

/**
 * @author L
 * 前台用户
 */
public interface UserService {

    /*1.分页查询/重置*/
    PageResult<User> queryUserByPage(Integer page, Integer rows);

    /*2.搜索*/
    PageResult<User> queryUserByKey(String id, Integer page, Integer rows, String username, String status);
}