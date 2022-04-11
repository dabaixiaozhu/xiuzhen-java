package com.jnshu.controller;

import com.jnshu.pojo.Banner;
import com.jnshu.pojo.Notice;
import com.jnshu.service.admin.NoticeService;
import com.jnshu.util.ResponseData;
import com.jnshu.util.ResponseDataUtil;
import org.apache.dubbo.config.annotation.Reference;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 后台公告模块
 * @author admin
 */
@Controller
@RequestMapping("/admin/notice")
public class NoticeController {

    @Reference
    NoticeService noticeService;


    /**
     * 后台公告查询接口
     * 创建人 G
     * @return
     */
    @ResponseBody
    @GetMapping("/fpx")
    public ResponseData selectAll(){
        Map<String,Object> map = new HashMap<String, Object>();
        List<Notice> list = this.noticeService.selectAll();
        int read = list.size();
        int fettle = list.stream().collect(Collectors.summingInt(Notice::getStatus));
        map.put("read",read);
        map.put("fettle",fettle);
        map.put("items",list);
        return ResponseDataUtil.buildSuccess(map);
        }


    /**
     * 后台公告新增接口
     * 创建人 G
     * @return
     */
    @ResponseBody
    @PostMapping("/fpx")
    public ResponseData insert(@RequestParam(value = "title")String title,
                               @RequestParam(value = "content")String content,
                               @RequestParam(value = "manage_id")Long manageId)
    {
        Integer integer = this.noticeService.insert(title, content,manageId);
        return ResponseDataUtil.buildSuccess();
    }

    /**
     * 后台公告编辑/数据更新接口
     * 上下架同时使用此接口
     * 创建人 G
     * @return
     */
    @ResponseBody
    @PutMapping("/fpx/{id}")
    public ResponseData updateByPrimaryKeySelective(@PathVariable(value = "id")Long id,
                                                    @RequestParam(value = "title",required = false)String title,
                                                    @RequestParam(value = "content",required = false)String content,
                                                    @RequestParam(value = "status",required = false)Integer status,
                                                    @RequestParam(value = "manage_id")Long manageId)
    {
            Integer integer = this.noticeService.updateByPrimaryKeySelective(id, title, content, status, manageId);
            return ResponseDataUtil.buildSuccess();
        }




    /**
     * 后台公告删除接口
     * 创建人 G
     * @return
     */
    @ResponseBody
    @DeleteMapping("/fpx/{id}")
    public ResponseData deleteByPrimaryKey(@PathVariable(value = "id")Long id){
        noticeService.deleteByPrimaryKey(id);
        return ResponseDataUtil.buildSuccess();
    }


    /**
     * 后台公告编辑/数据回显接口
     * 创建人 G
     * @return
     */
    @ResponseBody
    @GetMapping("/fpx/{id}")
    public ResponseData selectByPrimaryKey(@PathVariable(value = "id")Long id){
        List<Notice> notice = this.noticeService.selectByPrimaryKey(id);
        return ResponseDataUtil.buildSuccess(notice);
    }

}
