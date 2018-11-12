package com.zhou.reader.ui.search;

import com.zhou.reader.db.Catalog;
import com.zhou.reader.entity.selector.CatalogSelector;
import com.zhou.reader.http.ObjectHttpCallback;
import com.zhou.reader.util.SelectorManager;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public abstract class BookCatalogCallback extends ObjectHttpCallback<Catalog> {
    public BookCatalogCallback() {
        super(Catalog.class);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String body = response.body().string();
        CatalogSelector catalogSelector = SelectorManager.get().getSelectSelector().getCatalog();
//        final List<Catalog> catalogs = BookSearchUtil.getAllCatalogFromServer(body, catalogSelector);
//        for (Catalog catalog : catalogs) {
//            System.out.println(catalog.toString());
//        }
//        AppExecutor.get().mainThread().execute(new Runnable() {
//            @Override
//            public void run() {
//                onSuccess(null);
//                onFinish();
//            }
//        });
    }
}