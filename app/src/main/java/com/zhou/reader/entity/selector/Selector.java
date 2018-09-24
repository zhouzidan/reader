package com.zhou.reader.entity.selector;

public class Selector {
    private int id;
    private String searchUrl;
    private String baseUrl;
    private SearchSelector search;
    private CatalogSelector catalog;
    private ContentSelector content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SearchSelector getSearch() {
        return search;
    }

    public void setSearch(SearchSelector search) {
        this.search = search;
    }

    public String getSearchUrl() {
        return searchUrl;
    }

    public void setSearchUrl(String searchUrl) {
        this.searchUrl = searchUrl;
    }

    public CatalogSelector getCatalog() {
        return catalog;
    }

    public void setCatalog(CatalogSelector catalog) {
        this.catalog = catalog;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public ContentSelector getContent() {
        return content;
    }

    public void setContent(ContentSelector content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Selector{" +
                "id=" + id +
                ", searchUrl='" + searchUrl + '\'' +
                ", baseUrl='" + baseUrl + '\'' +
                ", search=" + search +
                ", catalog=" + catalog +
                '}';
    }
}
