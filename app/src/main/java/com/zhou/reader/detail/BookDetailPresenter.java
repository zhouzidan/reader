package com.zhou.reader.detail;

import com.zhou.reader.db.LBook;
import com.zhou.reader.entity.Book;
import com.zhou.reader.entity.Catalog;
import com.zhou.reader.search.BookSearchUtil;
import com.zhou.reader.shelf.ShelfDBManager;
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

    @Override
    public void loadBookShelfStatus(Book book) {
        boolean existInShelf = false;
        if (book != null){
            LBook lBook = ShelfDBManager.get().findById(book.getLocalBookId());
            if (lBook == null){
                lBook = ShelfDBManager.get().findByBookName(book.getTitle());
            }
            existInShelf = lBook != null;
            if (existInShelf && book.getLocalBookId() <= 0){
                book.setLocalBookId(lBook.getId());
            }
        }
        view.showShelfStatus(existInShelf);
    }

    @Override
    public void removeBookShelf(Book book) {
        ShelfDBManager.get().deleteById(book.getLocalBookId());
        view.showShelfStatus(false);
    }

    @Override
    public void addBookToShelf(Book book) {
        LBook lBook = new LBook(book);
        lBook.id = 0 ;
        long localBookId = ShelfDBManager.get().save(lBook);
        book.setLocalBookId(localBookId);
        view.showShelfStatus(true);
    }
}
