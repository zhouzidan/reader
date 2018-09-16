package com.zhou.reader.entity;

import android.os.Parcel;
import android.os.Parcelable;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

public class Catalog implements Parcelable {
    public int index;
    public String title;
    public String url;
    public boolean hasRead = false;

    public Book book;


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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.index);
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeByte(this.hasRead ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.book, flags);
    }

    public Catalog() {
    }

    protected Catalog(Parcel in) {
        this.index = in.readInt();
        this.title = in.readString();
        this.url = in.readString();
        this.hasRead = in.readByte() != 0;
        this.book = in.readParcelable(Book.class.getClassLoader());
    }

    public static final Creator<Catalog> CREATOR = new Creator<Catalog>() {
        @Override
        public Catalog createFromParcel(Parcel source) {
            return new Catalog(source);
        }

        @Override
        public Catalog[] newArray(int size) {
            return new Catalog[size];
        }
    };
}
