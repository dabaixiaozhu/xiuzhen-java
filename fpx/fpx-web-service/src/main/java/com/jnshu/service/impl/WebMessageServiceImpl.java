package com.jnshu.service.impl;

import com.jnshu.mapper.MessageMapper;
import com.jnshu.mapper.UserMessageMapper;
import com.jnshu.pojo.Message;
import com.jnshu.pojo.UserMessage;
import com.jnshu.service.web.WebMessageService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class WebMessageServiceImpl implements WebMessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserMessageMapper userMessageMapper;

    /**
     * 1.显示消息
     * @param id
     * @return
     */
    @Override
    public List<Message> showMessage(Long id) {

        // 1.获取当前用户所有messageId
        List<Long> longs = this.userMessageMapper.findmessageIdByid(id);

        // 2.根据对应的messageId查询出tb_message对应数据
        Example example = new Example(Message.class);
        Example.Criteria criteria = example.createCriteria();
        for (Long aLong : longs) {
            criteria.orEqualTo("id",aLong);
        }
        List<Message> messages = this.messageMapper.selectByExample(example);

        // 3.添加read字段
        for (Message message : messages) {
            List<Integer> integers = this.userMessageMapper.findstatusBymessageId(message.getId(), id);
            Integer integer = integers.get(0);
            message.setRead(integer);
        }
        return messages;
    }

    /**
     * 2.点击后红点消失
     * @param id
     * @return
     */
    @Override
    public Integer clickRead(Long id,Long userId) {
        Example example = new Example(UserMessage.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        criteria.andEqualTo("messageId",id);
        UserMessage userMessage = new UserMessage();
        userMessage.setStatus(0);
        int result = this.userMessageMapper.updateByExampleSelective(userMessage, example);
        return result;
    }
}
