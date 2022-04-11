package com.jnshu.service.impl;

import com.jnshu.mapper.ManageMapper;
import com.jnshu.mapper.NoticeMapper;
import com.jnshu.pojo.Banner;
import com.jnshu.pojo.Notice;


import com.jnshu.service.admin.NoticeService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author admin
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeMapper noticeMapper;

    @Autowired
    private ManageMapper manageMapper;


    @Override
    public int deleteByPrimaryKey(Long id) {
        return noticeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(String title,String content,Long manageId) {
        Notice notice = new Notice();
        notice.setTitle(title);
        notice.setContent(content);
        notice.setStatus(0);

        long update = System.currentTimeMillis();
        notice.setUpdateAt(update);
        notice.setUpdateBy(manageId);
        notice.setCreatAt(update);
        notice.setCreatBy(manageId);
        notice.setCreatName(this.manageMapper.selectByPrimaryKey(manageId).getName());
        notice.setUpdateName(this.manageMapper.selectByPrimaryKey(manageId).getName());

        int result = this.noticeMapper.insert(notice);

        return result;
    }

    @Override
    public List<Notice> selectByPrimaryKey(Long id) {
        return noticeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Notice> selectAll() {
        List<Notice> result = noticeMapper.selectAll();
        return result;
    }

    @Override
    public Integer updateByPrimaryKeySelective(Long id,String title,String content,Integer status,Long manageId) {
        Notice notice = new Notice();
        notice.setId(id);
        notice.setTitle(title);
        notice.setContent(content);
        notice.setStatus(status);
        notice.setUpdateAt(System.currentTimeMillis());
        notice.setUpdateBy(manageId);
        notice.setUpdateName(this.manageMapper.selectByPrimaryKey(manageId).getName());

        int result = this.noticeMapper.updateByPrimaryKeySelective(notice);
        return result;
    }



}



