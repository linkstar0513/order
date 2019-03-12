package com.order.web.service.impl;

import com.order.web.mapper.CategoryMapper;
import com.order.web.pojo.Category;
import com.order.web.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CagegoryServiceImpl implements CategoryService {
    @Resource
    CategoryMapper categoryMapper;

    @Override
    public List<Category> selectAllCategory() {
        return categoryMapper.selectAllCategory();
    }

    /**
     * 添加目录
     *
     * @param category
     */
    @Override
    public int addCategory(Category category) {
        return categoryMapper.insert(category);
    }
}
