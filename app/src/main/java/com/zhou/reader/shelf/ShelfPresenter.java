package com.zhou.reader.shelf;

import com.zhou.reader.db.LBook;
import com.zhou.reader.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class ShelfPresenter implements ShelfContact.Presenter {
    private ShelfContact.View view;

    public ShelfPresenter(ShelfContact.View view) {
        this.view = view;
    }

    @Override
    public void loadShelfBooks() {
        List<LBook> books = ShelfDBManager.get().getAll();
        view.showBooks(books);
    }
}
