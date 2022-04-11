package com.jnshu.controller;

import com.jnshu.mapper.UserMapper;
import com.jnshu.pojo.User;
import com.jnshu.pojo.UserSign;
import com.jnshu.service.web.WebSignService;
import com.jnshu.util.ResponseData;
import com.jnshu.util.ResponseDataUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author L
 * 签到
 */
@RestController
@RequestMapping("/web/sign")
public class WebSignController {

    @Reference
    private WebSignService webSignService;

    @Autowired
    private UserMapper userMapper;

    /**
     * 1.之前连续签到的天数
     * @param id
     * @param timestamp
     * @return
     */
    @GetMapping("/fpx")
    public ResponseData signBefore(
            @RequestParam(value = "id")Long id,
            @RequestParam(value = "timestamp")Long timestamp
    ){
        UserSign userSign = this.webSignService.signBefore(id,timestamp);
        return ResponseDataUtil.buildSuccess(userSign);
    }

    /**
     * 2.点击签到
     * @param id
     * @param timestamp
     * @return
     */
    @PutMapping("/fpx")
    public ResponseData signAfter(
            @RequestParam(value = "id")Long id,
            @RequestParam(value = "timestamp")Long timestamp
    ){
        UserSign userSign = this.webSignService.signAfter(id,timestamp);
        return ResponseDataUtil.buildSuccess(userSign);
    }

    /**
     * 3.之前连续签到的天数测试/每1分钟算一天
     * @param id
     * @param timestamp
     * @return
     */
    @GetMapping("/fpx/test")
    public ResponseData signBeforeTes(
            @RequestParam(value = "id")Long id,
            @RequestParam(value = "timestamp")Long timestamp
    ){
        UserSign userSign = this.webSignService.signBeforeTes(id,timestamp);
        return ResponseDataUtil.buildSuccess(userSign);
    }

    /**
     * 4.点击签到测试/每1分钟算一天
     * @param id
     * @param timestamp
     * @return
     */
    @PutMapping("/fpx/test")
    public ResponseData signAfterTes(
            @RequestParam(value = "id")Long id,
            @RequestParam(value = "timestamp")Long timestamp
    ){
        UserSign userSign = this.webSignService.signAfterTes(id,timestamp);
        return ResponseDataUtil.buildSuccess(userSign);
    }
}
