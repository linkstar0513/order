package com.order.web.modules.redis.controller;


import com.order.web.bean.ApiResult;
import com.order.web.modules.redis.service.RedisService;
import com.order.web.util.ApiTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/redis")
public class RedisController {

    @Autowired
    RedisService redisService;
    @RequestMapping("/all")
    public ApiResult allRedis(){
        return ApiTools.result(1000,"mm", redisService.getAll());
    }
}
