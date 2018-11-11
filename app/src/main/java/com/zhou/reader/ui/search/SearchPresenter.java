package com.zhou.reader.ui.search;

import com.zhou.reader.db.Book;
import com.zhou.reader.db.BookDBManager;
import com.zhou.reader.db.Search;
import com.zhou.reader.db.SearchDBManager;
import com.zhou.reader.entity.SearchResult;
import com.zhou.reader.http.BookSearchCallback;
import com.zhou.reader.util.ListUtil;

import java.util.ArrayList;
import java.util.List;

import static android.text.TextUtils.isEmpty;

public class SearchPresenter implements SearchContract.Presenter {

    private int page = 1;
    private String keyword;

    SearchContract.View view;

    public SearchPresenter(SearchContract.View view) {
        this.view = view;
    }

    @Override
    public void search(String keyword) {
        this.keyword = keyword;
        this.page = 1;
        if (isEmpty(keyword)) {
            showHistory();
            return;
        }
        view.showLoading();
        SearchDBManager.get().save(keyword);
        BookSearchUtil.search(keyword, page, new BookSearchCallback() {
            @Override
            public void onSuccess(SearchResult searchResult) {
                if (searchResult == null || ListUtil.isEmpty(searchResult.getBooks())) {
                    view.showMessage("没有搜索到结果");
                    showHistory();
                } else {
                    view.showData(searchResult);
                }
            }

            @Override
            public void onFail(Exception e) {
                view.showError();
            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }

    @Override
    public void loadMore() {
        page++;
        BookSearchUtil.search(keyword, page, new BookSearchCallback() {
            @Override
            public void onSuccess(SearchResult searchResult) {
                if (searchResult == null || ListUtil.isEmpty(searchResult.getBooks())) {
                    view.showMessage("已经没有更多了");
                } else {
                    view.showMessage("已经完成更多加载");
                    view.showData(searchResult);
                }
            }

            @Override
            public void onFail(Exception e) {
                view.showError();
            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });

    }

    @Override
    public void showHistory() {
        List<Search> searches = SearchDBManager.get().getAll();
        List<String> keywords = new ArrayList<>();
        for (Search search : searches) {
            keywords.add(search.content);
        }
        view.showHistory(keywords);
    }

    @Override
    public void save(Book book) {
        BookDBManager.get().save(book);
    }
}
