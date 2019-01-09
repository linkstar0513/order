package com.order.web.service;

import com.order.web.api.LoginResult;

public interface LoginService {
    LoginResult getLoginResult(String username, String password);
}
