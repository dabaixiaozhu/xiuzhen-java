package com.jnshu.mapper;

import com.jnshu.pojo.UserMessage;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMessageMapper extends Mapper<UserMessage> {

    @Select("SELECT `status` FROM tb_user_message WHERE `userId`=#{userId};")
    List<Integer> findStatusByid(Long userId);

    @Select("SELECT `messageId` FROM tb_user_message WHERE `userId`=#{userId};")
    List<Long> findmessageIdByid(Long userId);

    @Select("SELECT `status` FROM tb_user_message WHERE `messageId`=#{messageId} AND `userId`=#{userId};")
    List<Integer> findstatusBymessageId(Long messageId,Long userId);
}
