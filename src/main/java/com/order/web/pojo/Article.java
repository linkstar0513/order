package com.order.web.pojo;

public class Article {
    private String id;

    private String articleid;

    private String articlename;

    private String articlevalue;

    private String articleimage;

    private String articleauthor;

    private String articlevistit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getArticleid() {
        return articleid;
    }

    public void setArticleid(String articleid) {
        this.articleid = articleid == null ? null : articleid.trim();
    }

    public String getArticlename() {
        return articlename;
    }

    public void setArticlename(String articlename) {
        this.articlename = articlename == null ? null : articlename.trim();
    }

    public String getArticlevalue() {
        return articlevalue;
    }

    public void setArticlevalue(String articlevalue) {
        this.articlevalue = articlevalue == null ? null : articlevalue.trim();
    }

    public String getArticleimage() {
        return articleimage;
    }

    public void setArticleimage(String articleimage) {
        this.articleimage = articleimage == null ? null : articleimage.trim();
    }

    public String getArticleauthor() {
        return articleauthor;
    }

    public void setArticleauthor(String articleauthor) {
        this.articleauthor = articleauthor == null ? null : articleauthor.trim();
    }

    public String getArticlevistit() {
        return articlevistit;
    }

    public void setArticlevistit(String articlevistit) {
        this.articlevistit = articlevistit == null ? null : articlevistit.trim();
    }
}