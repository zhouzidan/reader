package com.zhou.reader.http;

import com.zhou.reader.entity.SearchResult;
import com.zhou.reader.entity.selector.SearchSelector;
import com.zhou.reader.search.BookSearchUtil;
import com.zhou.reader.util.AppExecutor;
import com.zhou.reader.util.SelectorManager;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public abstract class BookSearchCallback extends ObjectHttpCallback<SearchResult> {
    public BookSearchCallback() {
        super(SearchResult.class);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String body = response.body().string();
        SearchSelector searchSelector = SelectorManager.get().getSelectSelector().getSearch();
        final SearchResult searchResult = BookSearchUtil.getSearchResult(body, searchSelector);
        AppExecutor.get().mainThread().execute(new Runnable() {
            @Override
            public void run() {
                onSuccess(searchResult);
                onFinish();
            }
        });
    }
}
