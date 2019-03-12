package com.order.web.modules.redis.service;

import com.order.web.modules.redis.pojo.RedisValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


@Service
public class RedisService {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public List<String> getAll(){
        List list = new ArrayList();
        Set keys = stringRedisTemplate.keys("*");
        Iterator iterator = keys.iterator();
        while (iterator.hasNext()){
            String key = (String)iterator.next();
            RedisValue redisValue = new RedisValue();
            redisValue.setKey(key);
            redisValue.setType(stringRedisTemplate.type(key));
            redisValue.setExpir(stringRedisTemplate.getExpire(key));
            redisValue.setValue(stringRedisTemplate.opsForValue().get(key));
            stringRedisTemplate.opsForValue().get(key);
            list.add(redisValue);
        }

        return list;
    }
}
