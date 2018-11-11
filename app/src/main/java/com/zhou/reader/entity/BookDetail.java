package com.zhou.reader.entity;

public class BookDetail {
    private String _id;

    private String author;

    private String cover;

    private String creater;

    private String longIntro;

    private String title;

    private String majorCate; //玄幻

    private String minorCate; //异界大陆

    private String majorCateV2; //玄幻

    private String minorCateV2; //异世大陆

    private boolean hasCopyright;

    private int buytype;

    private int sizetype;

    private String superscript;

    private int currency;

    private String contentType;

    private boolean _le;

    private boolean allowMonthly;

    private boolean allowVoucher;

    private boolean allowBeanVoucher;

    private boolean hasCp;

    private int postCount;

    private int latelyFollower; //追书人数

    private int followerCount;

    private int wordCount;//总字数

    private int serializeWordCount; //日更新字数

    private String retentionRatio; //读者留存率

    private String updated;

    private boolean isSerial;

    private int chaptersCount;

    private String lastChapter;

    private boolean advertRead;

    private String cat;

    private boolean donate;

    private boolean _gg;

    private boolean isForbidForFreeApp;

    private String discount;

    private boolean limit;

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_id() {
        return this._id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCover() {
        return this.cover;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getCreater() {
        return this.creater;
    }

    public void setLongIntro(String longIntro) {
        this.longIntro = longIntro;
    }

    public String getLongIntro() {
        return this.longIntro;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setMajorCate(String majorCate) {
        this.majorCate = majorCate;
    }

    public String getMajorCate() {
        return this.majorCate;
    }

    public void setMinorCate(String minorCate) {
        this.minorCate = minorCate;
    }

    public String getMinorCate() {
        return this.minorCate;
    }

    public void setMajorCateV2(String majorCateV2) {
        this.majorCateV2 = majorCateV2;
    }

    public String getMajorCateV2() {
        return this.majorCateV2;
    }

    public void setMinorCateV2(String minorCateV2) {
        this.minorCateV2 = minorCateV2;
    }

    public String getMinorCateV2() {
        return this.minorCateV2;
    }

    public void setHasCopyright(boolean hasCopyright) {
        this.hasCopyright = hasCopyright;
    }

    public boolean getHasCopyright() {
        return this.hasCopyright;
    }

    public void setBuytype(int buytype) {
        this.buytype = buytype;
    }

    public int getBuytype() {
        return this.buytype;
    }

    public void setSizetype(int sizetype) {
        this.sizetype = sizetype;
    }

    public int getSizetype() {
        return this.sizetype;
    }

    public void setSuperscript(String superscript) {
        this.superscript = superscript;
    }

    public String getSuperscript() {
        return this.superscript;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public int getCurrency() {
        return this.currency;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void set_le(boolean _le) {
        this._le = _le;
    }

    public boolean get_le() {
        return this._le;
    }

    public void setAllowMonthly(boolean allowMonthly) {
        this.allowMonthly = allowMonthly;
    }

    public boolean getAllowMonthly() {
        return this.allowMonthly;
    }

    public void setAllowVoucher(boolean allowVoucher) {
        this.allowVoucher = allowVoucher;
    }

    public boolean getAllowVoucher() {
        return this.allowVoucher;
    }

    public void setAllowBeanVoucher(boolean allowBeanVoucher) {
        this.allowBeanVoucher = allowBeanVoucher;
    }

    public boolean getAllowBeanVoucher() {
        return this.allowBeanVoucher;
    }

    public void setHasCp(boolean hasCp) {
        this.hasCp = hasCp;
    }

    public boolean getHasCp() {
        return this.hasCp;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public int getPostCount() {
        return this.postCount;
    }

    public void setLatelyFollower(int latelyFollower) {
        this.latelyFollower = latelyFollower;
    }

    public int getLatelyFollower() {
        return this.latelyFollower;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public int getFollowerCount() {
        return this.followerCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public int getWordCount() {
        return this.wordCount;
    }

    public void setSerializeWordCount(int serializeWordCount) {
        this.serializeWordCount = serializeWordCount;
    }

    public int getSerializeWordCount() {
        return this.serializeWordCount;
    }

    public void setRetentionRatio(String retentionRatio) {
        this.retentionRatio = retentionRatio;
    }

    public String getRetentionRatio() {
        return this.retentionRatio;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getUpdated() {
        return this.updated;
    }

    public void setIsSerial(boolean isSerial) {
        this.isSerial = isSerial;
    }

    public boolean getIsSerial() {
        return this.isSerial;
    }

    public void setChaptersCount(int chaptersCount) {
        this.chaptersCount = chaptersCount;
    }

    public int getChaptersCount() {
        return this.chaptersCount;
    }

    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }

    public String getLastChapter() {
        return this.lastChapter;
    }

    public void setAdvertRead(boolean advertRead) {
        this.advertRead = advertRead;
    }

    public boolean getAdvertRead() {
        return this.advertRead;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getCat() {
        return this.cat;
    }

    public void setDonate(boolean donate) {
        this.donate = donate;
    }

    public boolean getDonate() {
        return this.donate;
    }

    public void set_gg(boolean _gg) {
        this._gg = _gg;
    }

    public boolean get_gg() {
        return this._gg;
    }

    public void setIsForbidForFreeApp(boolean isForbidForFreeApp) {
        this.isForbidForFreeApp = isForbidForFreeApp;
    }

    public boolean getIsForbidForFreeApp() {
        return this.isForbidForFreeApp;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscount() {
        return this.discount;
    }

    public void setLimit(boolean limit) {
        this.limit = limit;
    }

    public boolean getLimit() {
        return this.limit;
    }

}