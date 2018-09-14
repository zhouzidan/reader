package com.zhou.reader.detail;

import com.zhou.reader.base.BaseView;
import com.zhou.reader.entity.Book;
import com.zhou.reader.entity.Catalog;

import java.util.List;

public class BookDetailContract {

    public static interface View extends BaseView {
        void showData(List<Catalog> catalogs);
    }

    public static interface Presenter{
        void loadCatalog(Book book);

    }

}
