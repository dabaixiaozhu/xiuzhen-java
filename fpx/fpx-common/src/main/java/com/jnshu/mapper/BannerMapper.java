package com.jnshu.mapper;

import com.jnshu.pojo.Banner;
import com.jnshu.pojo.Notice;
import com.jnshu.util.PageResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import java.util.List;


/**
 * 创建人 G
 * @author admin
 */
@Repository
@Mapper
public interface BannerMapper  {

    int deleteByPrimaryKey(Long id);

    int insert(Banner record);

    Banner selectByPrimaryKey(Long id);

    List<Banner> selectAll();

    int updateByPrimaryKeySelective(Banner record);

    List<Banner> select();

    Banner selectById(Long courseId);

}