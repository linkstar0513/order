package com.order.web.category.articlemanager.controller;

import com.order.web.bean.ApiResult;
import com.order.web.mapper.ArticleMapper;
import com.order.web.pojo.ArticleExample;
import com.order.web.pojo.User;
import com.order.web.service.TokenService;
import com.order.web.util.ApiTools;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/article")
public class AritcleController {

    protected final Log logger = LogFactory.getLog(this.getClass());


    @Autowired
    TokenService tokenService;

    @Resource
    ArticleMapper atricleMapper;
    //获取当前创建的所有文章
    @RequestMapping("/list")
    public ApiResult getAllArticle(@CookieValue("access_token") String access){
        User user = tokenService.getUserInfo(access);
        logger.debug("当前用户名"+user.getUsername());
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria().andArticleauthorEqualTo(user.getUuid());
        return ApiTools.result(100000,"该用户全部文章列表",atricleMapper.selectByExample(articleExample));
    }
}
