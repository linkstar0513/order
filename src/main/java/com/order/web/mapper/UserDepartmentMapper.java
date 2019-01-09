package com.order.web.mapper;

import com.order.web.pojo.UserDepartment;

public interface UserDepartmentMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserDepartment record);

    int insertSelective(UserDepartment record);

    UserDepartment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserDepartment record);

    int updateByPrimaryKey(UserDepartment record);
}