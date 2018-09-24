package com.zhou.reader.db;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class ReadRecord {
    @Id public long id ;
    public long localCatalogId ;
    public long localBookId;
}
