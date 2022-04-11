package com.jnshu.service.admin;

import com.jnshu.pojo.Notice;

import java.util.List;

/**
 * @author admin
 */
public interface NoticeService {

    int deleteByPrimaryKey(Long id);

    Integer insert(String title,String content,Long manageId);

    List<Notice> selectByPrimaryKey(Long id);

    List<Notice> selectAll();

    Integer updateByPrimaryKeySelective(Long id,String title,String content,Integer status,Long manageId);


}
