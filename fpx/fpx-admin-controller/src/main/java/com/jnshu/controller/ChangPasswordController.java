package com.jnshu.controller;

import com.jnshu.service.admin.ChangPasswordService;
import com.jnshu.util.ResponseData;
import com.jnshu.util.ResponseDataUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

/**
 * @author L
 * 修改密码
 */
@RestController
@RequestMapping("/admin/password")
public class ChangPasswordController {

    @Reference
    private ChangPasswordService changPasswordService;

    /**
     * 1.修改密码
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PutMapping("/fpx")
    public ResponseData changPassword(
            @RequestParam(value = "id")Long id,
            @RequestParam(value = "old_password")String oldPassword,
            @RequestParam(value = "new_password")String newPassword
    ){
        Integer result = this.changPasswordService.changPassword(id,oldPassword,newPassword);
        if (result==1){
            return ResponseDataUtil.buildSuccess();
        }
        return ResponseDataUtil.buildError();
    }
}
