package com.order.web.service.impl;

import com.order.web.WebApplication;
import com.order.web.mapper.UserMapper;
import com.order.web.pojo.User;
import com.order.web.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class)

public class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void selectUser() {
    }

    @Test
    public void selectUserByName() {
        assertNotNull(userService.selectUserByName("link"));
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("linkstar0513@gmail.com");
        user.setPassword("admin");
        userService.addUser(user);
    }

    @Test
    public void selectAllUser() {
    }
}