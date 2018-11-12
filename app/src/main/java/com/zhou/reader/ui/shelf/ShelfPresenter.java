package com.zhou.reader.ui.shelf;

import com.zhou.reader.db.Book;
import com.zhou.reader.db.BookDBManager;
import com.zhou.reader.db.Catalog;
import com.zhou.reader.db.CatalogDBManager;
import com.zhou.reader.db.ShelfDBManager;
import com.zhou.reader.ui.search.BookSearchUtil;
import com.zhou.reader.util.AppExecutor;

import java.util.List;

public class ShelfPresenter implements ShelfContact.Presenter {
    private ShelfContact.View view;

    public ShelfPresenter(ShelfContact.View view) {
        this.view = view;
    }

    @Override
    public void loadShelfBooks() {
        List<Book> books = ShelfDBManager.get().getAll();
        view.showBooks(books);
    }

    public void removeBookFromShelf(Book book) {
        ShelfDBManager.get().delete(book);
        loadShelfBooks();
    }

    @Override
    public void refresh() {
        AppExecutor.get().networkIO().execute(()->{
            List<Book> books = ShelfDBManager.get().getAll();
            for (Book book : books) {
                List<Catalog> catalogsServer = BookSearchUtil.getAllCatalogFromServer(book);
                List<Catalog> catalogsLocal = CatalogDBManager.get().getAll(book.getId());
                catalogsServer.removeAll(catalogsLocal);
                if (catalogsServer.size() > 0){
                    book.setUpdateTime(System.currentTimeMillis());
                    book.setHasNew(true);
                    book.setLeastCatalog(catalogsServer.get(catalogsServer.size() - 1).title);
                    BookDBManager.get().save(book);
                    CatalogDBManager.get().save(catalogsServer);
                }
            }
            AppExecutor.get().mainThread().execute(()->{
                view.showBooks(books);
            });
        });
    }
}
