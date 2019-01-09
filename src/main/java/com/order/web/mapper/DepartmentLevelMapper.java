package com.order.web.mapper;

import com.order.web.pojo.DepartmentLevel;

public interface DepartmentLevelMapper {
    int insert(DepartmentLevel record);

    int insertSelective(DepartmentLevel record);
}