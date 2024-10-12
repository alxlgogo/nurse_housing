package com.jing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jing.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    @Select("select * from role where id in (SELECT  role_id from user INNER JOIN \n" +
            "user_role on user.id=user_role.user_id where user.user_name=#{user_name});")
    List<Role> selectRoleByUsername(@Param("user_name") String username);
}
