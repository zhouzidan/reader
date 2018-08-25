package com.zhou.reader.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class SearchEntity {
    @Id public long id ;
    public String content;
    public long updateTime;
}
