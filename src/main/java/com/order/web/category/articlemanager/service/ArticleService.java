package com.order.web.category.articlemanager.service;

import com.order.web.pojo.Article;

import java.util.List;

public interface ArticleService {
    /**
     * 获取所有文章
     * @return
     */
    List<Article> getArticles();
    /**
     * Get category id
     * 文章博客
     */
    List<Article> getArticlesByCategory(String id);

    /**
     *
     * @param id
     * @param count
     * @return
     */
    List<Article> getArticlesByCategory(String id,int count);
}
