package com.order.web.controller;

import com.order.web.annotation.AuthToken;
import com.order.web.bean.ApiResult;
import com.order.web.pojo.User;
import com.order.web.service.LoginService;
import com.order.web.service.TokenService;
import com.order.web.service.UserService;
import com.order.web.util.ApiTools;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.DigestUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController()
@CrossOrigin
@RequestMapping("/api")
public class UserController {

    protected final Log logger = LogFactory.getLog(this.getClass());


    @Autowired
    UserService userService;
    @Autowired
    LoginService loginService;
    @Autowired
    TokenService tokenService;

    //获取用户信息
    @RequestMapping(value = "/user", method = {RequestMethod.GET})
   // @AuthToken
    public ApiResult getUserInfo(@RequestAttribute(required = false,value = "uuid") String uuid,
                                 @RequestParam(required = false,value = "username") String username){
        logger.error("UUid"+uuid);
        logger.error("UserName"+username);
        if(null == username){
            List<User> userList ;
            userList = userService.selectAllUser();
            return ApiTools.result(10000,"success", userList);
        }
        User user = userService.selectUserByName(username);
        return ApiTools.result(10000,"success", user);
    }




    //用户拥有的角色
    @RequestMapping(value = "/user/role",method = {RequestMethod.GET,RequestMethod.POST})
     @CrossOrigin
    //@AuthToken
    public ApiResult userRole(){
        List<User> userList=new ArrayList<>();
        User user1 = new User();user1.setUsername("user1");user1.setUuid("id1");
        User user2 = new User();user2.setUsername("user3");user1.setUuid("id2");
        User user3 = new User();user3.setUsername("user2");user1.setUuid("id3");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        return ApiTools.result(1000,"获取成功",userList);
    }
    //增加(注册）用户
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public ApiResult addUsers(@RequestBody User user){
        if (null == user.getUsername()){
            return ApiTools.result(10001,"用户名为空",null);
        }
        if(null != userService.selectUserByName(user.getUsername())){
            return ApiTools.result(10001,"该用户已存在",null);
        }
        userService.addUser(user);
        return ApiTools.result(1000,"添加成功",null);
    }
    //删除用户
    @RequestMapping(value = "/user",method = RequestMethod.DELETE)
    public ApiResult deleteUsers(@RequestParam String username){
        Map data = new HashMap();
        int count = userService.deleteByUsername(username);
        data.put("count",count);
        return ApiTools.result(1000,"成功删除"+count+"条",data);
    }

    //更改用户
    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public ApiResult updateUsers(@RequestBody User user){
        Map data = new HashMap();
        int count = userService.updateByUsername(user);
        data.put("count",count);
        return ApiTools.result(1000,"成功更新"+count+"条",data);
    }


    @RequestMapping(value = "/user/count",method = RequestMethod.GET)
    public ApiResult getAllUser(){
        int count = userService.selectAllUser().size();
        return ApiTools.result(1000,"success",count);
    }



    @RequestMapping(value = "/user/current",method = RequestMethod.GET)
    public ApiResult getCurrentUser(@RequestAttribute(required = false,value = "username") String username,
                                    @RequestAttribute(required = false,value = "uuid") String uuid,
                                    @CookieValue("access_token") String access_token){
//        User user = userService.selectUserByName(username);
        User user = tokenService.getUserInfo(access_token);
        return ApiTools.result(10000,"success", user);
    }


}
