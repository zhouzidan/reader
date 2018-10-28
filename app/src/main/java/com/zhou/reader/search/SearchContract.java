package com.zhou.reader.search;

import com.zhou.reader.base.BaseView;
import com.zhou.reader.db.Book;
import com.zhou.reader.db.Search;
import com.zhou.reader.entity.SearchResult;

import java.util.List;

public class SearchContract {
    public static interface View extends BaseView {
        void showData(SearchResult searchResult);

        void clearSearchResult();

        void showData(List<String> searches);
    }

    public static interface Presenter{
        void search(String string);

        void showHistory();

        void save(Book book);
    }
}
