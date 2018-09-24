package com.zhou.reader.db;

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
    public String toString() {
        return "Catalog{" +
                "id=" + id +
                ", index=" + index +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", hasRead=" + hasRead +
                ", bookId=" + bookId +
                ", content='" + content + '\'' +
                '}';
    }
}
