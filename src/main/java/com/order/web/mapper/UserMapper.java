package com.order.web.mapper;

import com.order.web.pojo.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String uuid);

    int deleteByUsername(String username);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String uuid);
    User selectByUsername(String username);

    List<User> selectAllUser();

    int updateByPrimaryKeySelective(User record);
    int updateByUsername(User record);

    int updateByPrimaryKey(User record);
}