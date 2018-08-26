package com.zhou.reader.search;

import com.zhou.reader.entity.Search;
import com.zhou.reader.entity.SearchResult;
import com.zhou.reader.http.HttpUtil;
import com.zhou.reader.http.ObjectHttpCallback;

import java.util.List;

import static android.text.TextUtils.isEmpty;

public class SearchPresenter implements SearchContract.Presenter {

    SearchContract.View view;

    public SearchPresenter(SearchContract.View view) {
        this.view = view;
    }

    @Override
    public void search(String string) {
        if (isEmpty(string)){
            showHistory();
            return;
        }
        view.showLoading();
        SearchDBManager.get().save(string);
        String url = "http://novel.juhe.im/search?keyword="+ string;
        HttpUtil.doGet(url, new ObjectHttpCallback<SearchResult>(SearchResult.class) {

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
