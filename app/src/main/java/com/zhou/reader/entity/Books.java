/**
 * Copyright 2018 bejson.com
 */
package com.zhou.reader.entity;

/**
 * Auto-generated: 2018-08-25 23:41:16
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Books {

    private String _id;
    private boolean hasCp;
    private String title;
    private String aliases;
    private String cat;
    private String author;
    private String site;
    private String cover;
    private String shortIntro;
    private String lastChapter;
    private double retentionRatio;
    private int banned;
    private boolean allowMonthly;
    private int latelyFollower;
    private long wordCount;
    private String contentType;
    private String superscript;
    private int sizetype;
    private Highlight highlight;

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_id() {
        return _id;
    }

    public void setHasCp(boolean hasCp) {
        this.hasCp = hasCp;
    }

    public boolean getHasCp() {
        return hasCp;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }

    public String getAliases() {
        return aliases;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getCat() {
        return cat;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSite() {
        return site;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCover() {
        return cover;
    }

    public void setShortIntro(String shortIntro) {
        this.shortIntro = shortIntro;
    }

    public String getShortIntro() {
        return shortIntro;
    }

    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }

    public String getLastChapter() {
        return lastChapter;
    }

    public void setRetentionRatio(double retentionRatio) {
        this.retentionRatio = retentionRatio;
    }

    public double getRetentionRatio() {
        return retentionRatio;
    }

    public void setBanned(int banned) {
        this.banned = banned;
    }

    public int getBanned() {
        return banned;
    }

    public void setAllowMonthly(boolean allowMonthly) {
        this.allowMonthly = allowMonthly;
    }

    public boolean getAllowMonthly() {
        return allowMonthly;
    }

    public void setLatelyFollower(int latelyFollower) {
        this.latelyFollower = latelyFollower;
    }

    public int getLatelyFollower() {
        return latelyFollower;
    }

    public void setWordCount(long wordCount) {
        this.wordCount = wordCount;
    }

    public long getWordCount() {
        return wordCount;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setSuperscript(String superscript) {
        this.superscript = superscript;
    }

    public String getSuperscript() {
        return superscript;
    }

    public void setSizetype(int sizetype) {
        this.sizetype = sizetype;
    }

    public int getSizetype() {
        return sizetype;
    }

    public void setHighlight(Highlight highlight) {
        this.highlight = highlight;
    }

    public Highlight getHighlight() {
        return highlight;
    }

    @Override
    public String toString() {
        return "Books{" +
                "_id='" + _id + '\'' +
                ", hasCp=" + hasCp +
                ", title='" + title + '\'' +
                ", aliases='" + aliases + '\'' +
                ", cat='" + cat + '\'' +
                ", author='" + author + '\'' +
                ", site='" + site + '\'' +
                ", cover='" + cover + '\'' +
                ", shortIntro='" + shortIntro + '\'' +
                ", lastChapter='" + lastChapter + '\'' +
                ", retentionRatio=" + retentionRatio +
                ", banned=" + banned +
                ", allowMonthly=" + allowMonthly +
                ", latelyFollower=" + latelyFollower +
                ", wordCount=" + wordCount +
                ", contentType='" + contentType + '\'' +
                ", superscript='" + superscript + '\'' +
                ", sizetype=" + sizetype +
                ", highlight=" + highlight +
                '}';
    }
}