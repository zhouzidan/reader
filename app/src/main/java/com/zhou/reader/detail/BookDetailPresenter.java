package com.zhou.reader.detail;

import com.zhou.reader.entity.Book;
import com.zhou.reader.entity.Catalog;
import com.zhou.reader.search.BookSearchUtil;
import com.zhou.reader.util.AppExecutor;

import java.util.List;

public class BookDetailPresenter implements BookDetailContract.Presenter {

    BookDetailContract.View view;

    public BookDetailPresenter(BookDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void loadCatalog(Book book) {
        final String link = book.getLink();
        AppExecutor.get().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Catalog> catalogs = BookSearchUtil.getCatalog(link);
                AppExecutor.get().mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        view.showData(catalogs);
                    }
                });
            }
        });
    }
}
