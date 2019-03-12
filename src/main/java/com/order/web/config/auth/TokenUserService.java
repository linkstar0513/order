package com.order.web.config.auth;

import com.order.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class TokenUserService implements UserDetailsService {

    /**
     * 根据参数获取用户信息
     * @param token
     * @return
     * @throws UsernameNotFoundException
     */

    @Autowired
    TokenService tokenService;//从数据库中获取用户信息，或者redis。LDAP获取用户信息

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        return null;
    }
}
