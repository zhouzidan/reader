package com.zhou.reader.shelf;

import com.zhou.reader.db.Book;

import java.util.List;

public class ShelfContact {
    public static interface View {
        void showBooks(List<Book> books);
    }

    public static interface Presenter{
        void loadShelfBooks();
    }
}
