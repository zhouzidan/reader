package com.zhou.reader.ui.read;

import android.content.Intent;

import com.zhou.reader.base.BasePresenter;
import com.zhou.reader.base.BaseView;
import com.zhou.reader.db.Book;
import com.zhou.reader.db.Catalog;

import java.util.List;

public class ReadContact {
    public static interface View extends BaseView {
        void showBookContent(Catalog catalog);

        void loadBookAndCatalogs();

        void showCurrentCatalogTitle(Catalog catalog);

    }

    public static interface Presenter extends BasePresenter {
        void loadData(Intent intent);

        Book getBook();

        List<Catalog> getCatalogs();

        Catalog getCurrentCatalog();

        void loadNextContent();

        void loadLastContent();

        void loadContent(Catalog catalog);

        void saveReadRecord(Catalog catalog);
    }
}
