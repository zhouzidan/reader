package com.zhou.reader.search;

import com.zhou.reader.entity.Search;
import com.zhou.reader.entity.SearchResult;
import com.zhou.reader.entity.selector.Selector;
import com.zhou.reader.http.HtmlCallback;
import com.zhou.reader.http.HttpUtil;
import com.zhou.reader.http.BookSearchCallback;
import com.zhou.reader.util.SelectorManager;

import java.util.List;

import static android.text.TextUtils.isEmpty;

public class SearchPresenter implements SearchContract.Presenter {

    SearchContract.View view;

    public SearchPresenter(SearchContract.View view) {
        this.view = view;
    }

    @Override
    public void search(String keyword) {
        if (isEmpty(keyword)){
            showHistory();
            return;
        }
        view.showLoading();
        SearchDBManager.get().save(keyword);
        BookSearchUtil.search(keyword,new BookSearchCallback() {
            @Override
            public void onSuccess(SearchResult searchResult) {
                view.showData(searchResult);
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
    public void showHistory(){
        view.showLoading();
        List<Search> searches = SearchDBManager.get().getAll();
        view.showData(searches);
        view.hideLoading();
    }
}
