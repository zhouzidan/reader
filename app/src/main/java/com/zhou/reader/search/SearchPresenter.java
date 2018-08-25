package com.zhou.reader.search;

import com.zhou.reader.entity.SearchResult;
import com.zhou.reader.http.HttpUtil;
import com.zhou.reader.http.ObjectHttpCallback;

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

    public void showHistory(){
        view.showLoading();
        view.hideLoading();
    }
}
