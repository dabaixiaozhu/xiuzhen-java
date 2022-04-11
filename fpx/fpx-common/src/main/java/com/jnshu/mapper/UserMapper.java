package com.jnshu.mapper;


import com.jnshu.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {

    @Select("SELECT score FROM tb_user WHERE id=#{id};")
    Integer selectByIdKey(Long id);

    @Update("UPDATE tb_user SET score = #{score} WHERE id=#{id};")
    Integer updateByIdKey(User record);
}