package com.zhou.reader.entity.selector;

public class SearchSelector {
    private String item = null;
    private String title = null;
    private String link = null;
    private String coverPic = null;
    private String desc = null;
    private String tag = null;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "SearchSelector{" +
                "item='" + item + '\'' +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", coverPic='" + coverPic + '\'' +
                ", desc='" + desc + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
