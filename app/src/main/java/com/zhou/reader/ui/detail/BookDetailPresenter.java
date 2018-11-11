package com.zhou.reader.ui.detail;

import com.zhou.reader.db.Book;
import com.zhou.reader.db.BookDBManager;
import com.zhou.reader.db.Catalog;
import com.zhou.reader.db.CatalogDBManager;
import com.zhou.reader.db.ShelfDBManager;
import com.zhou.reader.ui.search.BookSearchUtil;
import com.zhou.reader.util.AppExecutor;

import java.util.List;

public class BookDetailPresenter implements BookDetailContract.Presenter {

    BookDetailContract.View view;

    public BookDetailPresenter(BookDetailContract.View view) {
        this.view = view;
    }


    @Override
    public void loadBookAndCatalog(Book book) {
        AppExecutor.get().networkIO().execute(() -> {
            List<Catalog> localCatalogList = null;
            if (book != null) {
                if (book.getId() > 0){
                    localCatalogList = CatalogDBManager.get().getAll(book.getId());
                }
                if (localCatalogList == null || localCatalogList.size() == 0) {
                    localCatalogList = BookSearchUtil.getCatalog(book);
                }
            }
            List<Catalog> finalLocalCatalogList = localCatalogList;
            AppExecutor.get().mainThread().execute(() -> view.loadBookAndCatalogs(book, finalLocalCatalogList));

        });
    }


    @Override
    public void loadBookShelfStatus(Book book) {
        boolean existInShelf = false;
        if (book != null) {
            Book lBook = ShelfDBManager.get().findOnShelfById(book.getId());
            existInShelf = lBook != null;
            if (existInShelf && book.getId() <= 0) {
                book.setId(lBook.getId());
            }
        }
        view.showShelfStatus(existInShelf);
    }

    @Override
    public void removeBookShelf(Book book) {
        ShelfDBManager.get().deleteById(book.getId());
        CatalogDBManager.get().deleteByBookId(book.getId());
        view.showShelfStatus(false);
    }

    @Override
    public void addBookToShelf(Book book, List<Catalog> catalogs) {
        if (book != null){
            book.onShelf = true;
            long localBookId = ShelfDBManager.get().save(book);
            for (Catalog catalog : catalogs) {
                catalog.setBookId(localBookId);
            }
            CatalogDBManager.get().save(catalogs);
        }
        view.showShelfStatus(true);
    }

    @Override
    public void saveBookToCache(Book book, List<Catalog> catalogs) {
        view.showLoading();
        if (book != null && book.getId() <= 0) {
            ShelfDBManager.get().save(book);
        }
        if (catalogs != null && catalogs.size() > 0) {
            for (Catalog catalog : catalogs) {
                if (catalog.getId() <= 0) {
                    CatalogDBManager.get().save(catalog);
                }
            }
        }
        view.hideLoading();
    }
}
