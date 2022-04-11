package com.jnshu.service.admin;

import com.jnshu.pojo.Manage;
import com.jnshu.util.PageResult;

/**
 * @author L
 * 后台账号管理
 */
public interface ManageService {

    /*1.分页查询/重置*/
    PageResult<Manage> queryManageByPage(Integer page, Integer rows);

    /*2.搜索*/
    PageResult<Manage> quryManageByKey(Integer page, Integer rows, String id, String name, String pushTimeStart,String pushTimeEnd,String role);

    /*4.新增消息*/
    Integer upTwoTable(String name, String role, String password, Long manageId);

    /*5.删除*/
    Integer deleteManage(Long id);

    /*8.编辑*/
    Integer editManage(Long id, Long manageId, String name, String password, String role);
}