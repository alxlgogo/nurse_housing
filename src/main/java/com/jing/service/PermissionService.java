package com.jing.service;

import com.jing.mapper.PermissionMapper;
import com.jing.pojo.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("permissionService")
public class PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    public List<Permission> selectPerimissionByUsername(String username) {
        List<Permission> permissions = permissionMapper.selectPerimissionByUsername(username);
        return permissions;
    }

    public List<Permission>  selectList(){
        List<Permission> permissions = permissionMapper.selectList(null);
        return permissions;
    }


}
