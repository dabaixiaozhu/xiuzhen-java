package com.jnshu.service.web;

public interface WebHomeService {

    /*1.消息提示总数*/
    Integer findAllRead(Long id);

    /*2.年级弹出框*/
    Integer upUserByGrade(Long id, Integer grade);
}