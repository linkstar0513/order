package com.order.web.mapper;

import com.order.web.pojo.Category;

import java.util.List;

public interface CategoryMapper {
    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectAllCategory();
}