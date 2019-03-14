package com.order.web.category.articlemanager.controller;

import com.order.web.bean.ApiResult;
import com.order.web.util.ApiTools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/article")
public class AritcleController {

    //获取当前创建的所有文章
    @RequestMapping("/list")
    public ApiResult getAllArticle(){


        return ApiTools.result();
    }
}
