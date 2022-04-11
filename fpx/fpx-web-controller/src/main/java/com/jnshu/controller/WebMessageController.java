package com.jnshu.controller;

import com.jnshu.mapper.MessageMapper;
import com.jnshu.pojo.Message;
import com.jnshu.service.web.WebMessageService;
import com.jnshu.util.ResponseData;
import com.jnshu.util.ResponseDataUtil;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author L
 * 消息中心
 */
@RestController
@RequestMapping("/web/message")
public class WebMessageController {

    @Reference(retries = 1,timeout = 100000)
    private WebMessageService webMessageService;

    @Autowired
    private MessageMapper messageMapper;

    /**
     * 1.显示消息
     * @param id
     * @return
     */
    @GetMapping("/fpx")
    public ResponseData showMessage(@RequestParam(value = "id")Long id){
        List<Message> Messages = this.webMessageService.showMessage(id);
        return ResponseDataUtil.buildSuccess(Messages);
    }

    /**
     * 2.消息提示
     * @param id
     * @return
     */
    @PutMapping("/fpx")
    public ResponseData clickRead(
            @RequestParam(value = "id")Long id,
            @RequestParam(value = "user_id")Long userId
    ){
        Integer result = this.webMessageService.clickRead(id,userId);
        if (result == 1){
            return ResponseDataUtil.buildSuccess();
        }
        return ResponseDataUtil.buildError();
    }
}
