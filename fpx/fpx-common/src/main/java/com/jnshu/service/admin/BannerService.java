package com.jnshu.service.admin;

import com.jnshu.pojo.Banner;


import java.util.List;


/**
 * @author admin
 */
public interface BannerService {

    int deleteByPrimaryKey(Long id);

    Integer insert(String title,String url,Long courseId,Long manageId);

    Banner selectByPrimaryKey(Long id);

    List<Banner> selectAll();

    Integer updateByPrimaryKeySelective(Long id,String title,String url,Long courseId,Integer status,Long manageId);

    Banner selectById(Long courseId);
}
