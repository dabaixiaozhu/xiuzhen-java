package com.jnshu.controller;

import com.jnshu.mapper.ManageMapper;
import com.jnshu.mapper.MessageMapper;
import com.jnshu.pojo.Message;
import com.jnshu.service.admin.MessageService;
import com.jnshu.util.PageResult;
import com.jnshu.util.ResponseData;
import com.jnshu.util.ResponseDataUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author L
 * 消息列表
 */
@RestController
@RequestMapping("/admin/message")
public class MessageController {

    @Reference(retries = 1,timeout = 100000)
    private MessageService messageService;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private ManageMapper manageMapper;

    /**
     * 1.分页查询/重置
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/fpx")
    public ResponseData queryMessageByPage(
            @RequestParam(value = "page", defaultValue = "1",required = false)Integer page,
            @RequestParam(value = "rows", defaultValue = "10",required = false)Integer rows
    ){
        PageResult<Message> result = this.messageService.queryMessageByPage(page, rows);
        return ResponseDataUtil.buildSuccess(result);
    }


    /**
     * 2.搜索
     * @param page
     * @param rows
     * @param pushName
     * @param status
     * @param pushTimeStart
     * @param pushTimeEnd
     * @param pushClass
     * @param name
     * @return
     */
    @GetMapping("/fpx/search")
    public ResponseData quryMessageByKey(
            @RequestParam(value = "page", defaultValue = "1",required = false)Integer page,
            @RequestParam(value = "rows", defaultValue = "10",required = false)Integer rows,
            @RequestParam(value = "push_name",required = false)String pushName,
            @RequestParam(value = "status", required = false)String status,
            @RequestParam(value = "push_time_start",required = false)String pushTimeStart,
            @RequestParam(value = "push_time_end",required = false)String pushTimeEnd,
            @RequestParam(value = "push_class", required = false)String pushClass,
            @RequestParam(value = "name",required = false)String name
    ){
        PageResult<Message> result = this.messageService.quryMessageByKey(page, rows, name, pushName, status, pushTimeStart,pushTimeEnd,pushClass);
        return ResponseDataUtil.buildSuccess(result);
    }

    /**
     * 3.查看
     * @param id
     * @return
     */
    @GetMapping("/fpx/{id}")
    public ResponseData quryMessageById(@PathVariable(value = "id")Long id){
        Message message = this.messageMapper.selectByPrimaryKey(id);
        return ResponseDataUtil.buildSuccess(message);
    }

    /**
     * 4.新增消息
     * @param name
     * @param pushStyle
     * @param pushTime
     * @param content
     * @param courseId
     * @return
     */
    @PostMapping("/fpx")
    public ResponseData addMessage(
            @RequestParam(value = "id")Long id,
            @RequestParam(value = "name")String name,
            @RequestParam(value = "push_style")Integer pushStyle,
            @RequestParam(value = "push_time")Long pushTime,
            @RequestParam(value = "content")String content,
            @RequestParam(value = "course_id")Long courseId
    ){
        Integer integer = this.messageService.addMessage(id, name, pushStyle, pushTime, content, courseId);
        if (integer == 1){
            return ResponseDataUtil.buildSuccess();
        }else if (integer ==2){
            return ResponseDataUtil.buildCourseError();
        }
        return ResponseDataUtil.buildError();
    }


    /**
     * 5.删除
     * @param id
     * @return
     */
    @DeleteMapping("/fpx")
    public ResponseData deleteMessage(@RequestParam(value = "id")Long id){
        Integer result = this.messageService.deleteMessage(id);
        if (result==1){
            return ResponseDataUtil.buildSuccess();
        }
        return ResponseDataUtil.buildError();
    }

    /**
     * 6.立即推送
     * @param id
     * @return
     */
    @PutMapping("/fpx")
    public ResponseData PutMessage(
            @RequestParam(value = "id")Long id,
            @RequestParam(value = "manage_id")Long manageId,
            @RequestParam(value = "push_time")Long pushTime
    ){
        Integer result = this.messageService.PutMessage(id,manageId,pushTime);
        if (result==1){
            return ResponseDataUtil.buildSuccess();
        }
        return ResponseDataUtil.buildError();
    }
}