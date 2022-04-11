package com.jnshu.mapper;


import com.jnshu.pojo.Permission;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

public interface PermissionMapper extends Mapper<Permission> {

    /*6.编辑/数据回显*/
    @Select("select parent_id from tb_permission where parent_id !=0;")
    Set<Long> selecByParentIdZero();
}