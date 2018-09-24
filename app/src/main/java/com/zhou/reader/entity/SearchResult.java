/**
 * Copyright 2018 bejson.com
 */
package com.zhou.reader.entity;

import com.zhou.reader.db.Book;

import java.util.List;

/**
 * Auto-generated: 2018-08-25 23:41:16
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class SearchResult {

    private List<Book> books;
    private int total;
    private boolean ok;

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public boolean getOk() {
        return ok;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "books=" + books +
                ", total=" + total +
                ", ok=" + ok +
                '}';
    }
}