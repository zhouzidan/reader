package com.zhou.reader.shelf;

import java.util.List;

public class ShelfPresenter implements ShelfContact.Presenter {
    private ShelfContact.View view;

    public ShelfPresenter(ShelfContact.View view) {
        this.view = view;
    }

    @Override
    public void loadShelfBooks() {
        ShelfModle.searchBooks("遮天");
//        view.showBooks(list);
    }
}
