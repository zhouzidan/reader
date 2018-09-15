/**
 * Copyright 2018 bejson.com
 */
package com.zhou.reader.entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
public class Book implements Serializable {

    public long id;
    public String title;
    public String link;
    public String coverPic;
    public String desc;
    public String author ; // 作者
    public String type ; //类型
    public long updateTime ; // 更新时间
    public String leastCatalog ; //最新章节
    public List<Catalog> catalogs;

    public Book() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getLeastCatalog() {
        return leastCatalog;
    }

    public void setLeastCatalog(String leastCatalog) {
        this.leastCatalog = leastCatalog;
    }

    public List<Catalog> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(List<Catalog> catalogs) {
        this.catalogs = catalogs;
    }
}