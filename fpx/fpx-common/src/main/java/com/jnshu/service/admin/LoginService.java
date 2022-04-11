package com.jnshu.service.admin;

public interface LoginService {
    /*2.验证登陆密码是否正确*/
    Long vertifyPassword(String name, String password);
}
