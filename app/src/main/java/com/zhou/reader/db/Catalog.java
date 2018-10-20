package com.zhou.reader.db;

import android.os.Build;

import java.util.Arrays;
import java.util.Objects;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Catalog {
    @Id public long id ;
    public int index;
    public String title;
    public String url;
    public boolean hasRead = false;

    public long bookId;

    public String content;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isHasRead() {
        return hasRead;
    }

    public void setHasRead(boolean hasRead) {
        this.hasRead = hasRead;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Catalog catalog = (Catalog) o;
        return id == catalog.id &&
                bookId == catalog.bookId;
    }

    @Override
    public int hashCode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return Objects.hash(id, bookId);
        }else {
            return String.valueOf(id +"" + bookId).hashCode();
        }
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "id=" + id +
                ", index=" + index +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", hasRead=" + hasRead +
                ", bookId=" + bookId +
                ", content='" + ((content != null) ? content.substring(0,10) : "null") + '\'' +
                '}';
    }
}
