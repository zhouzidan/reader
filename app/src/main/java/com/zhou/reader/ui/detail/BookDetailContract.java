package com.zhou.reader.ui.detail;

import com.zhou.reader.base.BaseView;
import com.zhou.reader.db.Book;
import com.zhou.reader.db.Catalog;

import java.util.List;

public class BookDetailContract {

    public static interface View extends BaseView {
        void showShelfStatus(boolean existInShelf);

        void loadBookAndCatalogs(Book book, List<Catalog> localCatalogList);
    }

    public static interface Presenter {

        void updateBookStatus(Book book);

        void loadBookAndCatalog(Book book);

        void loadBookShelfStatus(Book book);

        void removeBookShelf(Book book);

        void addBookToShelf(Book book, List<Catalog> catalogs);

        void saveBookToCache(Book book, List<Catalog> catalogs);
    }

}
