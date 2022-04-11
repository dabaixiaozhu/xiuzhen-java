package com.jnshu.controller;

import com.jnshu.pojo.Banner;
import com.jnshu.pojo.Notice;
import com.jnshu.pojo.UserCourse;
import com.jnshu.service.web.WebCourseService;
import com.jnshu.util.ResponseData;
import com.jnshu.util.ResponseDataUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 前台课程模块
 * 创建人 G
 * @author admin
 */
@RestController
@RequestMapping("/web/course")
public class WebCourseController {

    @Reference
    WebCourseService webCourseService;


    /**
     * 前台轮播图查询接口
     * 创建人 G
     * @return
     */
    @ResponseBody
    @GetMapping("/banner/fpx")
    public ResponseData select(){
        List<Banner> result = this.webCourseService.select();
        return ResponseDataUtil.buildSuccess(result);
    }


    /**
     * 前台课程展示接口
     * 创建人 G
     * @return
     */
    @ResponseBody
    @GetMapping("/fpx/nb/{user_id}")
    public ResponseData getById(@PathVariable(value = "user_id")Long userId,
                                @RequestParam(value = "id")Long id
                                ){
        UserCourse result = this.webCourseService.getById(userId,id);
        return ResponseDataUtil.buildSuccess(result);
    }


    /**
     * 前台课程搜索接口
     * 创建人 G
     * @return
     */
    @ResponseBody
    @GetMapping("/fpx/search/{user_id}")
    public ResponseData searchCourse(@PathVariable(value = "user_id")Long userId,
                                     @RequestParam(value = "grade")String grade,
                                     @RequestParam(value = "title")String title){
        List<UserCourse> result = this.webCourseService.searchCourse(userId, grade, title);
        return ResponseDataUtil.buildSuccess(result);
    }


    /**
     * 前台科目课程接口
     * 创建人 G
     * @return
     */
//    @ResponseBody
//    @GetMapping("/fpx/{user_id}")
//    public ResponseData selectById(@PathVariable(value = "user_id")Long userId,
//                                   @RequestParam(value = "grade")String grade
//                                   ){
//        List<UserCourse> result = this.webCourseService.selectById(userId,grade);
//        Map<String, List<UserCourse>> listMap = new HashMap<>();
//        for (UserCourse userCourse : result) {
//            List<UserCourse> tempList = listMap.get(userCourse.getSubject());
//            if (tempList == null) {
//                tempList = new ArrayList<>();
//                tempList.add(userCourse);
//                listMap.put(userCourse.getSubject(),tempList);
//            } else {
//                tempList.add(userCourse);
//            }
//        }
//        for (String subject : listMap.keySet()){
//        }
//            return ResponseDataUtil.buildSuccess(listMap);
//    }

    @ResponseBody
    @GetMapping("/fpx/{user_id}")
    public ResponseData selectById(@PathVariable(value = "user_id")Long userId,
                                   @RequestParam(value = "grade")String grade
    ){
        List<UserCourse> result = this.webCourseService.selectById(userId,grade);
        Map<String, List<UserCourse>> listMap = new HashMap<>();
        for (UserCourse userCourse : result) {
            List<UserCourse> tempList = listMap.get(userCourse.getSubject());
            if (tempList == null) {
                tempList = new ArrayList<>();
                tempList.add(userCourse);
                listMap.put(userCourse.getSubject(),tempList);
            } else {
                tempList.add(userCourse);
            }
        }
        Set<String> strings = listMap.keySet();
        List<Map> resultList = new ArrayList<>();
        for (String string : strings) {
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            map.put("subject",string);
            map.put("items",listMap.get(string));
            resultList.add(map);
        }
        return ResponseDataUtil.buildSuccess(resultList);
    }


    /**
     * 前台更多课程接口
     * 创建人 G
     * @return
     */
    @ResponseBody
    @GetMapping("/fpx/all/{user_id}")
    public ResponseData selectByIdAll(@PathVariable(value = "user_id")Long userId,
                                      @RequestParam(value = "grade")String grade,
                                      @RequestParam(value = "subject")String subject)
    {
        List<UserCourse> result = this.webCourseService.selectByIdAll(userId,grade,subject);
        return ResponseDataUtil.buildSuccess(result);
    }
}
