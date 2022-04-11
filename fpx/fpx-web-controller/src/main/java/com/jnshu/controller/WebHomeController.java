package com.jnshu.controller;

import com.jnshu.service.web.WebHomeService;
import com.jnshu.util.ResponseData;
import com.jnshu.util.ResponseDataUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author L
 * 首页
 */
@RestController
@RequestMapping("/web/font")
public class WebHomeController {

    @Reference(retries = 1,timeout = 100000)
    private WebHomeService webHomeService;


    /**
     * 1.消息提示总数
     * @param id
     * @return
     */
    @GetMapping("/fpx/{id}")
    public ResponseData findAllRead(@PathVariable(value = "id")Long id){
        Integer result = this.webHomeService.findAllRead(id);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("items",result);
        return ResponseDataUtil.buildSuccess(map);
    }

    /**
     * 2.选择年级弹出框
     * @param id
     * @param grade
     * @return
     */
    @PutMapping("/fpx")
    public ResponseData upUserByGrade(
            @RequestParam(value = "id")Long id,
            @RequestParam(value = "grade")Integer grade
    ){
        Integer result = this.webHomeService.upUserByGrade(id,grade);
        if (result == 1){
            return ResponseDataUtil.buildSuccess();
        }
        return ResponseDataUtil.buildError();
    }
}
