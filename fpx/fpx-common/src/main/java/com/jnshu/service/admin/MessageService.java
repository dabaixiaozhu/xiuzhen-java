package com.jnshu.service.admin;

import com.jnshu.pojo.Message;
import com.jnshu.util.PageResult;

/**
 * @author L
 * 消息列表
 */
public interface MessageService {

    /*1.分页查询/重置*/
    PageResult<Message> queryMessageByPage(Integer page, Integer rows);

    /*2.搜索*/
    PageResult<Message> quryMessageByKey(Integer page, Integer rows, String name, String pushName, String status, String pushTimeStart, String pushTimeEnd, String pushClass);

    /*4.新增消息*/
    Integer addMessage(Long id, String name, Integer pushStyle, Long pushTime, String content, Long courseId);

    // 定时修改的内容：往tb_user_message表中添加对应内容
    Integer addCronAll(long time1);

    /*5.删除*/
    Integer deleteMessage(Long id);

    /*6.立即推送*/
    Integer PutMessage(Long id, Long manageId, Long pushTime);


}
