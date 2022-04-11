package com.jnshu.service.web;

import com.jnshu.pojo.UserSign;

public interface WebSignService {
    /*1.之前连续签到的天数*/
    UserSign signBefore(Long id, Long timestamp);

    /*2.点击签到*/
    UserSign signAfter(Long id, Long timestamp);

    /*3.之前连续签到的天数测试*/
    UserSign signBeforeTes(Long id, Long timestamp);

    /*4.点击签到测试/每90秒算一天*/
    UserSign signAfterTes(Long id, Long timestamp);
}
