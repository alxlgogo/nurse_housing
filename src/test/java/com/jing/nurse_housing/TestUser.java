package com.jing.nurse_housing;

import com.jing.exception.UserExceptionHandler;
import com.jing.pojo.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestUser {
    @Test
    public void testUser() {
        try {
            User user = new User();
            user.setUserName("Lucy");
            user.setPassword("lucypassword001");
            user.setEmail("lucy@gmail.com");
            assertEquals("Lucy", user.getUserName());
            assertEquals("lucypassword001", user.getPassword());
            assertEquals("lucy@gmail.com", user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
