package com.zhou.reader.shelf;

import com.zhou.reader.db.Book;
import com.zhou.reader.db.ShelfDBManager;

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
}
