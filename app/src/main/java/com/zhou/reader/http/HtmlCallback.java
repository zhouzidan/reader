package com.zhou.reader.http;

import com.zhou.reader.util.AppExecutor;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public abstract class HtmlCallback extends ObjectHttpCallback<String> {

    public HtmlCallback() {
        super(String.class);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
//        super.onResponse(call, response);
        final String body = response.body().string();
        AppExecutor.get().mainThread().execute(new Runnable() {
            @Override
            public void run() {
                onSuccess(body);
                onFinish();
            }
        });
    }

    @Override
    public abstract void onSuccess(String s);

    @Override
    public abstract void onFail(Exception e);

    @Override
    public abstract void onFinish();
}
