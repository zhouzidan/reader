package com.zhou.reader.db;

import android.os.Parcel;
import android.os.Parcelable;

import com.zhou.reader.entity.Book;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class LBook implements Parcelable{
    @Id public long id;
    public String title;
    public String link;
    public String coverPic;
    public String desc;
    public String author ; // 作者
    public String type ; //类型
    public long updateTime ; // 更新时间
    public String leastCatalog ; //最新章节

    public LBook(Book book){
        id = book.getLocalBookId();
        title = book.getTitle();
        link = book.getLink();
        coverPic = book.getCoverPic();
        desc = book.getDesc();
        author = book.getAuthor();
        type = book.getType();
        updateTime = book.getUpdateTime();
        leastCatalog = book.getLeastCatalog();
    }

    public Book toBook(){
        Book book = new Book();
        book.setLocalBookId(getId());
        book.setTitle(getTitle());
        book.setType(getType());
        book.setLink(getLink());
        book.setCoverPic(getCoverPic());
        book.setAuthor(getAuthor());
        book.setUpdateTime(getUpdateTime());
        book.setDesc(getDesc());
        book.setLeastCatalog(getLeastCatalog());
        return book;
    }

    protected LBook(Parcel in) {
        id = in.readLong();
        title = in.readString();
        link = in.readString();
        coverPic = in.readString();
        desc = in.readString();
        author = in.readString();
        type = in.readString();
        updateTime = in.readLong();
        leastCatalog = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(link);
        dest.writeString(coverPic);
        dest.writeString(desc);
        dest.writeString(author);
        dest.writeString(type);
        dest.writeLong(updateTime);
        dest.writeString(leastCatalog);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LBook> CREATOR = new Creator<LBook>() {
        @Override
        public LBook createFromParcel(Parcel in) {
            return new LBook(in);
        }

        @Override
        public LBook[] newArray(int size) {
            return new LBook[size];
        }
    };

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public String getDesc() {
        return desc;
    }

    public String getAuthor() {
        return author;
    }

    public String getType() {
        return type;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public String getLeastCatalog() {
        return leastCatalog;
    }
}
