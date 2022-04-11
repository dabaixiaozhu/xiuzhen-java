package com.jnshu.service.web;

import java.util.Map;

public interface WebLoginService {

    /*1.微信登陆*/
    Map<String,Object> weixinLogin(String code) throws Exception;
}
