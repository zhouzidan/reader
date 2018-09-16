package com.zhou.reader.shelf;

import com.zhou.reader.db.LBook;

import java.util.List;

public class ShelfContact {
    public static interface View {
        void showBooks(List<LBook> books);
    }

    public static interface Presenter{
        void loadShelfBooks();
    }
}
