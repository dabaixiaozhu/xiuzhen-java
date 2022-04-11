package com.jnshu.service.impl;

import com.jnshu.mapper.ManageMapper;
import com.jnshu.pojo.Manage;


import com.jnshu.service.admin.ChangPasswordService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class ChangPasswordServiceImpl implements ChangPasswordService {

    @Autowired
    private ManageMapper manageMapper;

    @Autowired
    private BCryptPasswordEncoder encoding;

    /**
     * 1.修改密码
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @Override
    public Integer changPassword(Long id, String oldPassword, String newPassword) {
        Manage manage = this.manageMapper.selectByPrimaryKey(id);
        boolean matches = encoding.matches(oldPassword, manage.getPassword());
        if (matches){
            Manage manage1 = new Manage();
            manage1.setId(id);
            manage1.setPassword(encoding.encode(newPassword));
            int result = this.manageMapper.updateByPrimaryKeySelective(manage1);
            return result;
        }else {
            return 0;
        }
    }
}
