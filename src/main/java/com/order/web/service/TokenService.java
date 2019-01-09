package com.order.web.service;


import com.order.web.pojo.User;

public interface TokenService {
    /**
     * 创建Token
     * @param username
     * @return
     */
    String createToken(User username);

    /**
     * 用户退出登录
     * @param token
     */
    void loginOff(String token);

    /**
     * 获取用户
     * @param token
     */
    User getUserInfo(String token);
}
