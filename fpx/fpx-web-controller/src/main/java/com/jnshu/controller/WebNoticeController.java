package com.jnshu.controller;

import com.jnshu.pojo.Notice;
import com.jnshu.service.web.WebNoticeService;
import com.jnshu.util.ResponseData;
import com.jnshu.util.ResponseDataUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 前台公告模块
 * @author admin
 */
@RestController
@RequestMapping("/web/notice")
public class WebNoticeController {

    @Reference
    WebNoticeService webNoticeService;


    /**
     * 前台公告查询接口
     * 创建人 G
     * @return
     */
    @ResponseBody
    @GetMapping("/fpx")
    public ResponseData select(){
        List<Notice> result = this.webNoticeService.select();
        return ResponseDataUtil.buildSuccess(result);
    }
}
