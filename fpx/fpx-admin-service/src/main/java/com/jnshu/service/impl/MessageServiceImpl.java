package com.jnshu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jnshu.mapper.*;
import com.jnshu.pojo.Cron;
import com.jnshu.pojo.Message;


import com.jnshu.pojo.User;
import com.jnshu.pojo.UserMessage;
import com.jnshu.service.admin.MessageService;
import com.jnshu.util.PageResult;
import jdk.net.SocketFlow;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author L
 * 消息列表
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private ManageMapper manageMapper;

    @Autowired
    private CronMapper cronMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserMessageMapper userMessageMapper;

    @Autowired
    private CourseMapper courseMapper;


    public Integer getGrade(String grade){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("初一",1);
        map.put("初二",2);
        map.put("初三",3);
        map.put("高一",4);
        map.put("高二",5);
        map.put("高三",6);

        Integer integer = map.get(grade);
        return integer;
    }


    /**
     * 1.分页查询/重置
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResult<Message> queryMessageByPage(Integer page, Integer rows) {
        Example example = new Example(Message.class);

        PageHelper.startPage(page,rows);
        List<Message> messages = this.messageMapper.select(null);

        PageInfo<Message> pageInfo = new PageInfo<>(messages);
        PageResult<Message> result = new PageResult<>(pageInfo.getPageNum(), pageInfo.getSize(), (int) pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
        return result;
    }


    /**
     * 2.搜索
     * @param page
     * @param rows
     * @param name
     * @param pushName
     * @param status
     * @param pushTimeStart
     * @param pushTimeEnd
     * @param pushClass
     * @return
     */
    @Override
    public PageResult<Message> quryMessageByKey(Integer page, Integer rows, String name, String pushName, String status, String pushTimeStart, String pushTimeEnd, String pushClass) {

        Example example = new Example(Message.class);
        Example.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotBlank(pushName)){
            criteria.andEqualTo("pushName",pushName);
        }

        if (StringUtils.isNotBlank(status)){
            criteria.andEqualTo("status", status);
        }

        if (StringUtils.isNotBlank(pushTimeStart) && StringUtils.isNotBlank(pushTimeEnd)){
            criteria.andBetween("pushTime",pushTimeStart,pushTimeEnd);
        }

        if (StringUtils.isNotBlank(pushClass)){
            criteria.andEqualTo("pushClass",pushClass);
        }

        if (StringUtils.isNotBlank(name)){
            criteria.andLike("name","%"+name+"%");
        }

        example.setOrderByClause("update_at desc");

        PageHelper.startPage(page,rows);
        List<Message> messages = this.messageMapper.selectByExample(example);

        PageInfo<Message> pageInfo = new PageInfo<>(messages);
        PageResult<Message> result = new PageResult<>(pageInfo.getPageNum(), pageInfo.getSize(), (int) pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
        return result;
    }

    /**
     * 4.新增消息
     * @param id
     * @param name
     * @param pushStyle
     * @param pushTime
     * @param content
     * @param courseId
     * @return
     */
    @Override
    public Integer addMessage(Long id,String name,Integer pushStyle,Long pushTime,String content,Long courseId) {
        String gradeById = this.courseMapper.findGradeById(courseId);
        if (gradeById == null){
            return 2;
        }
        // 1.往tb_message表中添加数据
        Message message = new Message();
        message.setName(name);
        message.setPushName(this.manageMapper.selectByPrimaryKey(id).getName());
        message.setPushStyle(pushStyle);
        message.setPushTime(pushTime);

        Integer grade = getGrade(gradeById);
        message.setPushClass(grade);
        if (pushStyle ==1 ){
            // 2.如果为定时推送，往tb_cron表中添加数据
            message.setStatus(0);
            Cron cron = new Cron();
            cron.setTimestamp(pushTime);
            this.cronMapper.insert(cron);
        }else {
            message.setStatus(1);
        }
        message.setCourseId(courseId);
        message.setContent(content);

        long update = System.currentTimeMillis();
        message.setUpdateAt(update);
        message.setUpdateBy(id);
        message.setCreatAt(update);
        message.setCreatBy(id);
        int insert = this.messageMapper.insert(message);

        // 3.往tb_tb_user_message表中添加数据
        // 只有立即推送才能保存到tb_user_message表中
        if (pushStyle== 0){
            Example example = new Example(User.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("userClass",gradeById);
            List<User> users = this.userMapper.selectByExample(example);

            for (User user : users) {
                UserMessage userMessage = new UserMessage();
                userMessage.setUserId(user.getId());
                userMessage.setStatus(1);
                userMessage.setMessageId(message.getId());
                this.userMessageMapper.insert(userMessage);
            }
        }
        return insert;
    }

    /**
     * 4.定时修改的内容：往tb_user_message表中添加对应内容
     * @param time1
     * @return
     */
    @Override
    public Integer addCronAll(long time1) {
        // 1.查询出tb_message表主键id
        List<Long> idByPushTime = this.messageMapper.findIdByPushTime(time1);
        for (Long aLong : idByPushTime) {
            // 2.每个id对应的年级
            Message message = this.messageMapper.selectByPrimaryKey(aLong);
            Integer pushClass = message.getPushClass();

            // 3.每个年级对应的多个用户
            Example example = new Example(User.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("userClass",pushClass);
            List<User> users = this.userMapper.selectByExample(example);

            for (User user : users) {
                UserMessage userMessage = new UserMessage();
                userMessage.setUserId(user.getId());
                userMessage.setStatus(0);
                userMessage.setMessageId(aLong);
                this.userMessageMapper.insert(userMessage);
            }
        }
        return null;
    }

    /**
     * 5.删除
     * @param id
     * @return
     */
    @Override
    public Integer deleteMessage(Long id) {
        // 1.删除tb_message表中对应id的数据
        int result = this.messageMapper.deleteByPrimaryKey(id);

        // 2.删除tb_user_message表中对应messageId的数据
        Example example = new Example(UserMessage.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("messageId",id);
        this.userMessageMapper.deleteByExample(example);

        return result;
    }

    /**
     * 6.立即推送
     * @param id
     * @param manageId
     * @param pushTime
     * @return
     */
    @Override
    public Integer PutMessage(Long id, Long manageId, Long pushTime) {
        // 1.修改tb_message表中对应id的内容
        Message message = new Message();
        message.setId(id);
        message.setPushName(this.manageMapper.selectByPrimaryKey(manageId).getName());
        message.setPushStyle(0);
        message.setPushTime(pushTime);
        message.setStatus(1);
        message.setUpdateAt(System.currentTimeMillis());
        message.setUpdateBy(manageId);
        int result = this.messageMapper.updateByPrimaryKeySelective(message);

        // 2.往tb_user_message表中添加对应用户的内容
        // 根据消息id，查出对应的年级
        Message message1 = this.messageMapper.selectByPrimaryKey(id);
        Integer pushClass = message1.getPushClass();
        // 根据查询出的年级，查询对应的前台用户id集
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userClass",pushClass);
        List<User> users = this.userMapper.selectByExample(example);
        // 有几个用户id，就要往tb_user_message表中插入几条数据
        for (User user : users) {
            UserMessage userMessage = new UserMessage();
            userMessage.setUserId(user.getId());
            userMessage.setStatus(1);
            userMessage.setMessageId(id);
            this.userMessageMapper.insert(userMessage);
        }
        return result;
    }
}
