package com.order.web.service.impl;

import com.order.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 根据用户名从数据库中获取用户名密码，角色权限信息
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    /**
     *
     */
    @Resource
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new JwtUser(userMapper.selectByUsername(username));
    }
}
