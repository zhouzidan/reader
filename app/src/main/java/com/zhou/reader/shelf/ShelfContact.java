package com.zhou.reader.shelf;

import java.util.List;

public class ShelfContact {
    public static interface View {
        void showBooks(List books);
    }

    public static interface Presenter{
        void loadShelfBooks();
    }
}
