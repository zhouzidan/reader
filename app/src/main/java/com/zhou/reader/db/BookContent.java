package com.zhou.reader.db;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class BookContent {
    @Id public long id ;
    public String content;
    public long localCatalogId ;
    public long localBookId ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
