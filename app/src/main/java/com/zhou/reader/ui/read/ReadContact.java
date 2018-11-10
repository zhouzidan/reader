package com.zhou.reader.ui.read;

import com.zhou.reader.base.BasePresenter;
import com.zhou.reader.base.BaseView;
import com.zhou.reader.db.Book;
import com.zhou.reader.db.BookContent;
import com.zhou.reader.db.Catalog;

import java.util.List;

public class ReadContact {
    public static interface View extends BaseView {
        void showBookContent(Catalog catalog);

        void loadBookAndCatalogs(Book book,List<Catalog> catalogs);

        void showCurrentCatalogTitle(Catalog catalog);

    }

    public static interface Presenter extends BasePresenter{
        void loadBookAndCatalogs(long localBookId);
        void loadCurrentContent(long localCatalogId);
        void loadNextContent();
        void loadLastContent();
        public void loadContent(Catalog catalog);
        public void preNextContent();
        public void preLastContent();
        public void saveReadRecord();
        public void saveReadRecord(Catalog catalog);
    }
}
