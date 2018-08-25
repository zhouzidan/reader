package com.zhou.reader.search;

import com.zhou.reader.base.BaseView;
import com.zhou.reader.entity.SearchResult;

public class SearchContract {
    public static interface View extends BaseView {
        void showData(SearchResult searchResult);
    }

    public static interface Presenter{
        void search(String string);
    }
}
