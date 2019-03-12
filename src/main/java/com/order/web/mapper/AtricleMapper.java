package com.order.web.mapper;

import com.order.web.pojo.Article;

public interface AtricleMapper {
    int insert(Article record);

    int insertSelective(Article record);
}