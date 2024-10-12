package com.jing.service;

import com.jing.mapper.RoleMapper;
import com.jing.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public List<Role> selectRoleByUsername(String username) {
        List<Role> roles = roleMapper.selectRoleByUsername(username);
        return roles;
    }
}
