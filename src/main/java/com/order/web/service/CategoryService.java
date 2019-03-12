package com.order.web.service;


import com.order.web.pojo.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 所有目录
     * @return
     */
    List<Category> selectAllCategory();
    /**
     * 添加目录
     */
    int addCategory(Category category);
}
