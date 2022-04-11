package com.jnshu.mapper;

import com.jnshu.pojo.ManageRole;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ManageRoleMapper extends Mapper<ManageRole> {

    @Select("select * from tb_manage")
    List<ManageRole> selctAll();
}
