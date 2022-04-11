package com.jnshu.service.impl;

import com.jnshu.mapper.MessageMapper;
import com.jnshu.mapper.UserMapper;
import com.jnshu.mapper.UserMessageMapper;
import com.jnshu.pojo.Message;
import com.jnshu.pojo.User;
import com.jnshu.pojo.UserMessage;
import com.jnshu.service.web.WebHomeService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class WebHomeServiceImpl implements WebHomeService {

    @Autowired
    private UserMessageMapper userMessageMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageMapper messageMapper;

    /**
     * 1.消息提示总数
     * @param id
     * @return
     */
    @Override
    public Integer findAllRead(Long id) {
        List<Integer> statusByid = this.userMessageMapper.findStatusByid(id);
        int sum = statusByid.stream().mapToInt(Integer::intValue).sum();
        return sum;
    }

    /**
     * 2.年级弹出框
     * @param id
     * @param grade
     * @return
     */
    @Override
    public Integer upUserByGrade(Long id, Integer grade) {
        // 1.更新tb_user表
        User user = new User();
        user.setId(id);
        user.setUserClass(grade);
        int result = this.userMapper.updateByPrimaryKeySelective(user);

        // 2.删除tb_user_message表原本用户对应的id
        Example example1 = new Example(UserMessage.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("userId",id);
        this.userMessageMapper.deleteByExample(example1);

        // 3.更新tb_user_message表
        Example example = new Example(Message.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("pushClass",grade);
        List<Message> messages = this.messageMapper.selectByExample(example);
        for (Message message : messages) {
            UserMessage userMessage = new UserMessage();
            userMessage.setUserId(id);
            userMessage.setMessageId(message.getId());
            userMessage.setStatus(1);
            this.userMessageMapper.insert(userMessage);
        }
        return result;
    }
}
