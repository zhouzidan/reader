package com.zhou.reader.db;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class BookContent {
    @Id public long id ;
    public String content;
    public long localCatalogId ;
    public long localBookId ;
}
