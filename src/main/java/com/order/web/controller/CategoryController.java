package com.order.web.controller;


import com.order.web.bean.ApiResult;
import com.order.web.pojo.Category;
import com.order.web.service.CategoryService;
import com.order.web.util.ApiTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    CategoryService categoryService;


    @RequestMapping(value = "/category",method = RequestMethod.GET)
    public ApiResult getCategory()
    {
        List<Category> categoryList = categoryService.selectAllCategory();
        return ApiTools.result(0000,"success",categoryList);
    }
    @RequestMapping(value = "/category",method = RequestMethod.POST)
    public ApiResult insertCategory(@RequestBody Category category){

        return ApiTools.result(0000,"success",categoryService.addCategory(category));
    }

}
