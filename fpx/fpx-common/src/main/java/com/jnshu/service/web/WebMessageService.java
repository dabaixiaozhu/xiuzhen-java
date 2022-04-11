package com.jnshu.service.web;

import com.jnshu.pojo.Message;

import java.util.List;

public interface WebMessageService {

    /*1.显示消息*/
    List<Message> showMessage(Long id);

    /*2.点击后红点消失*/
    Integer clickRead(Long id,Long userId);
}
