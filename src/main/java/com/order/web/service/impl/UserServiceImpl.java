package com.order.web.service.impl;

import com.order.web.mapper.UserMapper;
import com.order.web.pojo.User;
import com.order.web.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final Log logger = LogFactory.getLog(this.getClass());
    @Resource
    UserMapper userMapper;

    @Override
    public int deleteByUsername(String username) {
        return userMapper.deleteByUsername(username);
    }

    @Override
    public User selectUser(String uuid) {
        return userMapper.selectByPrimaryKey(uuid) ;
    }

    @Override
    public User selectUserByName(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public int addUser(User user) {
        String uuid = UUID.randomUUID().toString();
        user.setUuid(uuid);
        return userMapper.insert(user);
    }

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public int updateByUsername(User user) {
        return userMapper.updateByUsername(user);
    }
}
