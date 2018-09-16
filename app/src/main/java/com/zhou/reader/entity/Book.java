/**
 * Copyright 2018 bejson.com
 */
package com.zhou.reader.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.zhou.reader.db.LBook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Book implements Parcelable {

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

    public long localBookId ; //本地数据库中的ID

    public Book() {
    }

    public Book(LBook lBook) {
        setLocalBookId(lBook.getId());
        setTitle(getTitle());
        setLink(getLink());
        setCoverPic(getCoverPic());
        setDesc(getDesc());
        setAuthor(getAuthor());
        setType(getType());
        setUpdateTime(getUpdateTime());
        setLeastCatalog(getLeastCatalog());
        catalogs = new ArrayList<>();
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

    public long getLocalBookId() {
        return localBookId;
    }

    public void setLocalBookId(long localBookId) {
        this.localBookId = localBookId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.title);
        dest.writeString(this.link);
        dest.writeString(this.coverPic);
        dest.writeString(this.desc);
        dest.writeString(this.author);
        dest.writeString(this.type);
        dest.writeLong(this.updateTime);
        dest.writeString(this.leastCatalog);
        dest.writeTypedList(this.catalogs);
    }

    protected Book(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        this.link = in.readString();
        this.coverPic = in.readString();
        this.desc = in.readString();
        this.author = in.readString();
        this.type = in.readString();
        this.updateTime = in.readLong();
        this.leastCatalog = in.readString();
        this.catalogs = in.createTypedArrayList(Catalog.CREATOR);
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}