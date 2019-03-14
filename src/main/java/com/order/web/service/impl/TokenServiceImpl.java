package com.order.web.service.impl;

import com.order.web.mapper.UserMapper;
import com.order.web.pojo.User;
import com.order.web.service.TokenService;
import com.order.web.util.TokenGenerator;
import com.order.web.util.TokenUtil;
import com.order.web.util.impl.JwtTokenUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TokenServiceImpl implements TokenService {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Resource
    UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name = "jwtTokenUtils")
    private TokenGenerator tokenGenerator;

    @Override
    public String createToken(User user) {
        //生成access_token插入redis
        //String access_token = UUID.randomUUID().toString().replace("-","");
        String access_token = tokenGenerator.generate(user.getUsername());
        String uuid = user.getUuid();
        //stringRedisTemplate.opsForValue().set(access_token,uuid);
        stringRedisTemplate.opsForValue().set(access_token,uuid,600, TimeUnit.SECONDS);
        //redisTemplate.opsForValue().set(access_token,uuid,600, TimeUnit.SECONDS);
        //redisTemplate.opsForValue().set(uuid, user);
        logger.debug("Create token '"+access_token+"'for'"+uuid+"'");
        return access_token;
    }

    @Override
    public void loginOff(String token) {
        logger.debug("删除token key "+ token);
        stringRedisTemplate.delete(token);
    }

    @Override
    public User getUserInfo(String token) {
        User user=new User();

//        String uuid;
//        uuid = stringRedisTemplate.opsForValue().get(token);
//        logger.debug("Logout token is :" +token);
//        if (uuid==null){
//            user.setUsername("The User has logout");
//        } else {
//            user = userMapper.selectByPrimaryKey(uuid);
//        }
        String username = null;
        //username = JwtTokenUtils.getUsername(token);
        logger.debug("Logout token is :" +token);
        logger.debug("Logout token is :" +JwtTokenUtils.getUsername(token));
        if (username==null){
            user.setUsername("The User has logout");
        } else {
            user = userMapper.selectByUsername(username);
        }

        return user;
    }
}
