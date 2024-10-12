package com.jing.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jing.mapper.UserRoleMapper;
import com.jing.pojo.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userRoleService")
public class UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    public int insertUserRole(UserRole userRole){
        int insert = userRoleMapper.insert(userRole);
        return insert;
    }

    public int updateUserRole(UserRole userRole,UpdateWrapper<UserRole> updateWrapper){
        int update = userRoleMapper.update(userRole, updateWrapper);
        return  update;
    }


}
