package com.jnshu.mapper;

import com.jnshu.pojo.Notice;
import com.jnshu.util.PageResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 创建人 G
 * @author admin
 */
@Mapper
public interface NoticeMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Notice record);

    List<Notice> selectByPrimaryKey(Long id);

    List<Notice> selectAll();

    int updateByPrimaryKeySelective(Notice record);

    List<Notice> select();

}