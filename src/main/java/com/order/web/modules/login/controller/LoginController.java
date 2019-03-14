package com.order.web.modules.login.controller;

import com.order.web.api.LoginResult;
import com.order.web.bean.ApiResult;
import com.order.web.pojo.User;
import com.order.web.service.LoginService;
import com.order.web.service.TokenService;
import com.order.web.service.UserService;
import com.order.web.util.ApiTools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
@Api(value = "登录注销接口", tags = "登录注销模块")
@RestController
@RequestMapping("/api")
public class LoginController {
    protected final Log logger = LogFactory.getLog(this.getClass());


    @Autowired
    LoginService loginService;
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    @ApiOperation(value = "登录接口")
    @RequestMapping(value = "/login",method ={RequestMethod.GET, RequestMethod.POST})
    public ApiResult login(String username, String password ,
                           String redirectUrl, HttpServletResponse response){
        //验证请求格式的正确性(前端进行验证)
        if(null == username || null == password || null == response){
            return ApiTools.result(10001,"请求格式错误",null);
        }

        //获取用户名密码
        User user = userService.selectUserByName(username);
        if(user==null){
            return ApiTools.result(10001,"该用户不存在",null);
        }

        //根据password转换真实的password，再根据加盐处理成加密后的密文
        Boolean isVertify = password.equals(user.getPassword());//验证用户名密码
        //isVertify = true;
        if(isVertify){//登录成功
            String md5 = password;
            String access_token=tokenService.createToken(user);
            //客户端增减access_token
            Cookie cookie = new Cookie("access_token",access_token);
            cookie.setPath("/");
            response.addCookie(cookie);
            logger.debug("用户："+user.getUsername()+"登录成功");//用户登录日志

            if (null != redirectUrl){
                try {
                    response.sendRedirect(redirectUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return ApiTools.result(10000,"登录成功",user);
        }
        //登录失败
        return ApiTools.result(10001,"用户名密码错误",null);
    }

    @ApiOperation("注销接口")
    @RequestMapping(value = "/logout",method = {RequestMethod.POST,RequestMethod.GET})
    public ApiResult loginResult(@CookieValue("access_token") String access_token,
                                 @RequestAttribute(required = false,value = "username") String username,
                                 @RequestAttribute(required = false,value = "uuid") String uuid) {
        String token = access_token;

        if(token==null){
            return ApiTools.result(10001,"用户未登录",null);
        }
        User user = tokenService.getUserInfo(token);
        if(user==null){
            return ApiTools.result(10002,"用户已注销",null);
        }
        tokenService.loginOff(token);
        logger.debug("Token '"+token+"' is logout");//用户注销日志
        logger.debug("User '"+user.getUsername()+"' is logout");
        return ApiTools.result(10000,"用户注销成功",user);
    }
}
