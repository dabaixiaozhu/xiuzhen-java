package com.jnshu.service.impl;

import com.jnshu.mapper.ManageMapper;
import com.jnshu.pojo.Manage;


import com.jnshu.service.admin.LoginService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private ManageMapper manageMapper;

    @Autowired
    private BCryptPasswordEncoder encoding;

    /**
     * 1.登陆验证
     * @param name
     * @param password
     * @return
     */
    @Override
    public Long vertifyPassword(String name, String password) {
        Example example = new Example(Manage.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",name);
        List<Manage> manages = this.manageMapper.selectByExample(example);
        Manage manage = manages.get(0);

        // 第一个参数为传入的参数，第二个参数为数据库中加密的内容
        boolean matches = encoding.matches(password,manage.getPassword());
        if (matches){
            return manage.getId();
        }
        return 0L;
    }
}
