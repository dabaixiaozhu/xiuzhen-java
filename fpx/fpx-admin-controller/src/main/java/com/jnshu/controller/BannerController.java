package com.jnshu.controller;

import com.jnshu.pojo.Banner;
import com.jnshu.pojo.Course;
import com.jnshu.service.admin.BannerService;

import com.jnshu.service.admin.CourseService;
import com.jnshu.util.OssUtil;

import com.jnshu.util.ResponseData;
import com.jnshu.util.ResponseDataUtil;
import org.apache.commons.io.FileUtils;
import org.apache.dubbo.config.annotation.Reference;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 后台轮播图模块
 * @author admin
 */
@Controller
@RequestMapping("/admin/banner")
public class BannerController {
    Logger logger = LoggerFactory.getLogger(BannerController.class);

    @Reference
    BannerService bannerService;

    @Reference
    CourseService courseService;

    /**
     * 后台轮播图查询接口
     * 创建人 G
     * @return
     */
    @ResponseBody
    @GetMapping("/fpx")
    public ResponseData selectAll(){
        Map<String,Object> map = new HashMap<String, Object>();
        List<Banner> list = this.bannerService.selectAll();
        int read = list.size();
        int fettle = list.stream().collect(Collectors.summingInt(Banner::getStatus));
        map.put("read",read);
        map.put("fettle",fettle);
        map.put("items",list);
        return ResponseDataUtil.buildSuccess(map);
    }


    /**
     * 后台轮播图新增接口
     * 创建人 G
     * @return
     */
    @ResponseBody
    @PostMapping("/fpx")
    public ResponseData insert(@RequestParam(value = "title") String title,
                               @RequestParam(value = "url") String url,
                               @RequestParam(value = "course_id") Long courseId,
                               @RequestParam(value = "manage_id") Long manageId)
    {
        Course course = courseService.selectByPrimaryKey(courseId);
        if (course == null){
            return ResponseDataUtil.buildCourseError();
        }
        Banner banner = bannerService.selectById(courseId);
        if (banner != null) {
            return ResponseDataUtil.buildCourseBound();
        }
        Integer integer = this.bannerService.insert(title, url,courseId,manageId);
        return ResponseDataUtil.buildSuccess();
    }

    /**
     * 后台轮播图编辑/更新数据接口
     * 上下架同时使用此接口
     * 创建人 G
     * @return
     */
    @ResponseBody
    @PutMapping("/fpx/{id}")
    public ResponseData updateByPrimaryKeySelective(@PathVariable(value = "id")Long id,
                                                    @RequestParam(value = "title",required = false)String title,
                                                    @RequestParam(value = "url",required = false)String url,
                                                    @RequestParam(value = "course_id",required = false)Long courseId,
                                                    @RequestParam(value = "status",required = false)Integer status,
                                                    @RequestParam(value = "manage_id")Long manageId) {
        Course course = courseService.selectByPrimaryKey(courseId);
        if (course == null) {
            return ResponseDataUtil.buildCourseError();
        }
        Banner banner = bannerService.selectById(courseId);
        if (banner != null) {
            if (banner.getId().equals(id)){
                Integer integer = this.bannerService.updateByPrimaryKeySelective(id, title, url, courseId, status, manageId);
                return ResponseDataUtil.buildSuccess();
            }
                return ResponseDataUtil.buildCourseBound();
            }
        Integer integer = this.bannerService.updateByPrimaryKeySelective(id, title, url, courseId, status, manageId);
        return ResponseDataUtil.buildSuccess();
        }



    /**
     * 后台轮播图删除接口
     * 创建人 G
     * @return
     */
    @ResponseBody
    @DeleteMapping("/fpx/{id}")
    public ResponseData deleteByPrimaryKey(@PathVariable(value = "id")Long id){
        bannerService.deleteByPrimaryKey(id);
        return ResponseDataUtil.buildSuccess();
    }


    /**
     * 后台轮播图编辑/数据回显接口
     * 创建人 G
     * @return
     */
    @ResponseBody
    @GetMapping("/fpx/{id}")
    public ResponseData selectByPrimaryKey(@PathVariable(value = "id")Long id){
        Banner banner = this.bannerService.selectByPrimaryKey(id);
        return ResponseDataUtil.buildSuccess(banner);
    }


    /**
     * 图片(文件)上传接口
     * 创建人 G
     * @return
     */
    @ResponseBody
    @PostMapping("/fpx/url")
    public ResponseData upload(@RequestParam(value = "upload") MultipartFile upload,
                          HttpServletRequest request)throws IOException
    {
        Map<String,Object> map = new HashMap<String, Object>();

        // 将文件名存入域中
        String name = upload.getOriginalFilename();

        /*将MultipartFile转为File*/
        String path = request.getSession().getServletContext().getRealPath("/uploads/"+name);
        File file = new File(path);
        FileUtils.copyInputStreamToFile(upload.getInputStream(), file);

        /*上传文件*/
        String url2 = OssUtil.uploadImg(file);
        String url = OssUtil.getUrl(url2);
        logger.info("----------url:"+url);
        String[] split = url.split("[?]");
        String result = split[0];

        file.delete();
        map.put("url",result);

        return ResponseDataUtil.buildSuccess(map);
    }

}
