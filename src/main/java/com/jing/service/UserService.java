package com.jing.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jing.mapper.UserMapper;
import com.jing.pojo.Role;
import com.jing.pojo.User;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class UserService implements UserDetailsService {
    @Autowired
    private UserMapper UserMapper;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;

    public int insertUser(User user) {
        int insert = UserMapper.insert(user);
        return insert;
    }

    public int updateByID(User user) {
        int updateUser = UserMapper.updateById(user);
        return updateUser;
    }

    public User selectOne(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("user_name", username);
        User user = UserMapper.selectOne(wrapper);
        return user;
    }

//    public List<User> selectList(User user) {
//        List<User> users = UserMapper.selectList(null);
//        return users;
//    }

    public List<User> selectList(User user) {
        List<User> users = null;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (user != null && !StringUtils.isNullOrEmpty(user.getGender())) {
            queryWrapper.eq("gender", user.getGender());
            users = UserMapper.selectList(queryWrapper);
        } else {
            users = UserMapper.selectList(null);
        }
        return users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("user_name", username);
        User user = UserMapper.selectOne(wrapper);

//        List<Permission> permissions = permissionService.selectPerimissionByUsername(username);
//        StringBuffer stringBuffer = new StringBuffer();
//        for (int i = 0; i < permissions.size(); i++) {
//            Permission permission = permissions.get(i);
//            stringBuffer.append(permission.getPath());
//            if (i < permissions.size() - 1) {
//                stringBuffer.append(",");
//            }
//        }

        List<Role> roles = roleService.selectRoleByUsername(username);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < roles.size(); i++) {
            Role role = roles.get(i);
            stringBuffer.append("ROLE_" + role.getName());
            if (i < roles.size() - 1) {
                stringBuffer.append(",");
            }
        }
        if (user == null) {
            throw new UsernameNotFoundException("user is not exist");
        }
        List<GrantedAuthority> auths =
                AuthorityUtils.commaSeparatedStringToAuthorityList(stringBuffer.toString());

        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(), auths);
    }
}
