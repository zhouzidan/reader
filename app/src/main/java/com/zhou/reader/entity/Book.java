/**
 * Copyright 2018 bejson.com
 */
package com.zhou.reader.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Book implements Parcelable {

    private String _id;
    private String title;
    private String link;
    private String coverPic;
    private String desc;
    private Map<String,String> tags;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.title);
        dest.writeString(this.link);
        dest.writeString(this.coverPic);
        dest.writeString(this.desc);
        dest.writeInt(this.tags.size());
        for (Map.Entry<String, String> entry : this.tags.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
    }

    public Book() {
    }

    protected Book(Parcel in) {
        this._id = in.readString();
        this.title = in.readString();
        this.link = in.readString();
        this.coverPic = in.readString();
        this.desc = in.readString();
        int tagsSize = in.readInt();
        this.tags = new HashMap<String, String>(tagsSize);
        for (int i = 0; i < tagsSize; i++) {
            String key = in.readString();
            String value = in.readString();
            this.tags.put(key, value);
        }
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
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