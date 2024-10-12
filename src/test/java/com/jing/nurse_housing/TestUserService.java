package com.jing.nurse_housing;


import com.jing.pojo.User;
import com.jing.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserService {
    @Autowired
    UserService userService;

    @Test
    public void getAllUser() {
        List<User> usersService = userService.selectList(null);
        Assert.assertEquals(38, usersService.size());
    }





//
//    private MockMvc mockMvc;
//
//    @Before
//    public void setup() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }
}
