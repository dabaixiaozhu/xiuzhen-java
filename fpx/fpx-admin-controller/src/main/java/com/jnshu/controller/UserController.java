package com.jnshu.controller;

import com.jnshu.mapper.UserMapper;
import com.jnshu.pojo.User;
import com.jnshu.service.admin.UserService;
import com.jnshu.util.PageResult;
import com.jnshu.util.ResponseData;
import com.jnshu.util.ResponseDataUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author L
 * 前台用户
 */
@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Reference(retries = 1,timeout = 100000)
    private UserService userService;


    @Autowired
    private UserMapper userMapper;

    /**
     * 1.分页查询/重置
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/fpx")
    public ResponseData queryUserByPage(
            @RequestParam(value = "page", defaultValue = "1",required = false)Integer page,
            @RequestParam(value = "rows", defaultValue = "10",required = false)Integer rows
    ){
        PageResult<User> result = this.userService.queryUserByPage(page, rows);
        return ResponseDataUtil.buildSuccess(result);
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
    @GetMapping("/fpx/search")
    public ResponseData quryUserByKey(
            @RequestParam(value = "id",required = false)String id,
            @RequestParam(value = "page", defaultValue = "1",required = false)Integer page,
            @RequestParam(value = "rows", defaultValue = "10",required = false)Integer rows,
            @RequestParam(value = "nickname", required = false)String username,
            @RequestParam(value = "status", required = false)String status
    ){
        PageResult<User> result = this.userService.queryUserByKey(id, page, rows, username, status);
        return ResponseDataUtil.buildSuccess(result);
    }

    /**
     * 3.立即冻结/解冻
     * @param id
     * @return
     */
    @PutMapping("/fpx")
    public ResponseData coldInstant(@RequestParam(value = "id")Long id){
        User user = this.userMapper.selectByPrimaryKey(id);
        if (user.getStatus()==0){
            User user1 = new User();
            user1.setId(id);
            user1.setStatus(1);
            int i = this.userMapper.updateByPrimaryKeySelective(user1);
            if (i == 1){
                return ResponseDataUtil.buildSuccess();
            }else {
                return ResponseDataUtil.buildError();
            }
        }else {
            User user2 = new User();
            user2.setId(id);
            user2.setStatus(0);
            int i = this.userMapper.updateByPrimaryKeySelective(user2);
            if (i == 1){
                return ResponseDataUtil.buildSuccess();
            }else {
                return ResponseDataUtil.buildError();
            }
        }
    }
}
