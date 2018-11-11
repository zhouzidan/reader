package com.zhou.reader.db;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Book {
    @Id public long id;
    public String title;
    public String link;
    public String coverPic;
    public String desc;
    public String author ; // 作者
    public String type ; //类型
    public long updateTime ; // 更新时间
    public String leastCatalog ; //最新章节
    public boolean onShelf ; // 在书架上
    public String updateTo ; // 更新至
    public int defaultSource ; //默认来源 编号


    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public String getDesc() {
        return desc;
    }

    public String getAuthor() {
        return author;
    }

    public String getType() {
        return type;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public String getLeastCatalog() {
        return leastCatalog;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public void setLeastCatalog(String leastCatalog) {
        this.leastCatalog = leastCatalog;
    }

    public boolean isOnShelf() {
        return onShelf;
    }

    public void setOnShelf(boolean onShelf) {
        this.onShelf = onShelf;
    }

    public String getUpdateTo() {
        return updateTo;
    }

    public void setUpdateTo(String updateTo) {
        this.updateTo = updateTo;
    }

    public int getDefaultSource() {
        return defaultSource;
    }

    public void setDefaultSource(int defaultSource) {
        this.defaultSource = defaultSource;
    }
}
