package com.order.web.service;

import com.order.web.pojo.User;

import java.util.List;

public interface UserService {


    //删除用户
    int deleteByUsername(String username);
    //获取uuid的用户信息
    User selectUser(String uuid);
    //获取username（账号）的用户信息
    User selectUserByName(String username);

    // 添加用户
    int addUser(User user);
    //获取所有用户
    List<User> selectAllUser();
    // 更新用户信息
    int updateByUsername(User user);


}
