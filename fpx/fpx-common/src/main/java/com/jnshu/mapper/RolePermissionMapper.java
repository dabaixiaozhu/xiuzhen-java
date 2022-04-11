package com.jnshu.mapper;

import com.jnshu.pojo.RolePermission;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RolePermissionMapper extends Mapper<RolePermission> {

    @Select("select permissionId from tb_role_permission where roleId = #{roleId}")
    List<Long> findpermissionIdByRoleId(Long roleId);


}
