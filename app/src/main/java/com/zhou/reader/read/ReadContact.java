package com.zhou.reader.read;

import com.zhou.reader.db.Book;
import com.zhou.reader.db.BookContent;
import com.zhou.reader.db.Catalog;

import java.util.List;

public class ReadContact {
    public static interface View {
        void showBookContent(BookContent content);

        void loadBookAndCatalogs(Book book,List<Catalog> catalogs);

    }

    public static interface Presenter{
        void loadBookAndCatalogs(long localBookId);
        void loadCurrentContent();
        void loadNextContent();
        void loadLastContent();
        public void loadContent(Catalog catalog);
    }
}
