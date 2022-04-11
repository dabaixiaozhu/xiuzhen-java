package com.jnshu.service.impl;

import com.jnshu.mapper.NoticeMapper;
import com.jnshu.pojo.Notice;
import com.jnshu.service.web.WebNoticeService;
import com.jnshu.util.PageResult;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author admin
 */
@Service
public class WebNoticeServiceImpl implements WebNoticeService {

    @Autowired
    NoticeMapper noticeMapper;


    @Override
    public List<Notice> select() {
        return noticeMapper.select();
    }
}
