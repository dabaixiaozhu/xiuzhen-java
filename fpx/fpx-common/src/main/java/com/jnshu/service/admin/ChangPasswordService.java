package com.jnshu.service.admin;

public interface ChangPasswordService {
    /*修改密码*/
    Integer changPassword(Long id, String oldPassword, String newPassword);
}
