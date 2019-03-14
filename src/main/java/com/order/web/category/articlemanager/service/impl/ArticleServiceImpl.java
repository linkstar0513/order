package com.order.web.category.articlemanager.service.impl;

import com.order.web.mapper.AtricleMapper;
import com.order.web.pojo.Article;
import com.order.web.category.articlemanager.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    AtricleMapper atricleMapper;

    /**
     * 获取所有文章
     *
     * @return
     */
    @Override
    public List<Article> getArticles() {
        return null;
    }

    /**
     * Get category id
     * 文章博客
     *
     * @param id
     */
    @Override
    public List<Article> getArticlesByCategory(String id) {
        return null;
    }

    /**
     * @param id
     * @param count
     * @return
     */
    @Override
    public List<Article> getArticlesByCategory(String id, int count) {
        return null;
    }
}
