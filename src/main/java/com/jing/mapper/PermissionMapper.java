package com.jing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jing.pojo.Permission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {
    @Select("select * from permission where id in(\n" +
            "select permission_id from role_permission where role_permission.role_id in\n" +
            "(\n" +
            "SELECT  role_id from user INNER JOIN \n" +
            "user_role on user.id=user_role.user_id where user_name=#{user_name}\n" +
            ")\n" +
            ");")
    List<Permission> selectPerimissionByUsername(@Param("user_name") String username);
}
