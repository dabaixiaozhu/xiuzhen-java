package com.jnshu.service.web;

import com.jnshu.pojo.Notice;
import com.jnshu.util.PageResult;

import java.util.List;

/**
 * @author admin
 */
public interface WebNoticeService {

    List<Notice> select();
}
