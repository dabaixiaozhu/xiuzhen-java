package com.jnshu.controller;

import com.jnshu.service.web.WebLoginService;
import com.jnshu.util.ResponseData;
import com.jnshu.util.ResponseDataUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author L
 * 登陆
 */
@RestController
@RequestMapping("/web/login")
public class WebLoginController {

    @Reference(retries = 1,timeout = 100000)
    private WebLoginService webLoginService;

    /**
     * 1.登陆
     * @param code
     * @return
     */
    @PostMapping("/fpx")
    public ResponseData weixinLogin(@RequestParam(value = "code")String code) throws Exception {
        Map<String,Object> result = this.webLoginService.weixinLogin(code);
        if (result != null){
            return ResponseDataUtil.buildSuccess(result);
        }
        return ResponseDataUtil.buildError();
    }
}
