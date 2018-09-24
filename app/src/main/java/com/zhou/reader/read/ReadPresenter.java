package com.zhou.reader.read;

import com.zhou.reader.db.Book;
import com.zhou.reader.db.BookDBManager;
import com.zhou.reader.db.Catalog;
import com.zhou.reader.db.CatalogDBManager;

import java.util.List;

public class ReadPresenter implements ReadContact.Presenter {
    private ReadContact.View view;
    private Catalog currentCatalog;

    public ReadPresenter(ReadContact.View view) {
        this.view = view;
    }


    @Override
    public void loadBookAndCatalogs(long localBookId) {
        Book book = BookDBManager.get().findById(localBookId);
        List<Catalog> catalogs = CatalogDBManager.get().getAll(localBookId);
        view.loadBookAndCatalogs(book,catalogs);
    }

    @Override
    public void loadCurrentContent() {
        // 获取上次的阅读进度
    }

    @Override
    public void loadNextContent() {
        // 获取上次的阅读
    }

    @Override
    public void loadLastContent() {

    }

    @Override
    public void loadContent(Catalog catalog) {

    }
}
