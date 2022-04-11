package com.jnshu.controller;

import com.jnshu.pojo.CourseUser;
import com.jnshu.service.web.WebUserService;
import com.jnshu.util.ResponseData;
import com.jnshu.util.ResponseDataUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 前台支付模块
 * @author admin
 */
@RestController
@RequestMapping("/web/pay")
public class WebPayController {

    @Reference
    WebUserService webUserService;


    /**
     * 前台支付修改积分接口
     * 创建人 G
     * @return
     */
    @ResponseBody
    @PutMapping("/fpx/{id}")
    public ResponseData updateByIdKey(@PathVariable(value = "id")Long id,
                                      @RequestParam(value = "price")Integer price)
    {
        Integer a = this.webUserService.selectByIdKey(id);
        Integer x = a-price;
        if (x > 0){
            int score = x;
            Integer integer = this.webUserService.updateByIdKey(id, score, price);
            return ResponseDataUtil.buildSuccess();
        }else {
            return ResponseDataUtil.buildError();
        }
    }


    /**
     * 前台支付添加接口
     * 创建人 G
     * @return
     */
    @ResponseBody
    @PostMapping("/fpx")
    public ResponseData insert(@RequestParam(value = "user_id")Long userId,
                               @RequestParam(value = "course_id")Long courseId)
    {
        CourseUser x = this.webUserService.selectById(userId,courseId);
        if (x != null){
            Integer integer = webUserService.updateByPrimaryKeySelective(userId,courseId);
        }else {
            Integer integer = webUserService.insert(userId,courseId);
        }
        return ResponseDataUtil.buildSuccess();
    }

}
