package com.jnshu.mapper;

import com.jnshu.pojo.Message;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface MessageMapper extends Mapper<Message> {

    /*4.新增消息*/


    /*定时器*/
    // 定时器-定时发送
    @Update("UPDATE tb_message SET `status`=1 WHERE `push_time`<#{timestamp} AND `status`=0")
    Integer updateByPushTime(Long timestamp);

    // 定时器-修改tb_user_message表的内容，返回所有小于当前时间戳的消息id
    @Select("SELECT `id` from tb_message WHERE `push_time`<#{time1} AND `status`=0")
    List<Long> findIdByPushTime(long time1);

}