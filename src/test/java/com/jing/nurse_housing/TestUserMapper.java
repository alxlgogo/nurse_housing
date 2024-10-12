package com.jing.nurse_housing;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jing.mapper.UserMapper;
import com.jing.pojo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserMapper {
    @Autowired
    UserMapper userMapper;

    @Test
    public void selectOne() {
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("id", "1346942967537057794");
        User user = userMapper.selectOne(wrapper);
        Assert.assertEquals("1346942967537057794", user.getId()+"");
    }
}
