package com.zhou.reader.db;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class ReadRecord {
    @Id public long id ;
    public long localCatalogId ;
    public String bookName;
    public String catalogName;
    public long localBookId;
    public long updateTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLocalCatalogId() {
        return localCatalogId;
    }

    public void setLocalCatalogId(long localCatalogId) {
        this.localCatalogId = localCatalogId;
    }

    public long getLocalBookId() {
        return localBookId;
    }

    public void setLocalBookId(long localBookId) {
        this.localBookId = localBookId;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "ReadRecord{" +
                "id=" + id +
                ", localCatalogId=" + localCatalogId +
                ", bookName='" + bookName + '\'' +
                ", catalogName='" + catalogName + '\'' +
                ", localBookId=" + localBookId +
                ", updateTime=" + updateTime +
                '}';
    }
}
