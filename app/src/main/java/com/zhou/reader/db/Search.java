package com.zhou.reader.db;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Search {
    @Id public long id;
    public String content;
    public long updateTime;

}
