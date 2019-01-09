package com.order.web.service.impl;

import com.order.web.api.LoginResult;
import com.order.web.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public LoginResult getLoginResult(String username, String password) {
        return new LoginResult(200, "success");
    }
}
