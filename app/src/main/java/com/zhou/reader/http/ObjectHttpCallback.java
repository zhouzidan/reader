package com.zhou.reader.http;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.zhou.reader.util.AppExecutor;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class ObjectHttpCallback<T> implements Callback {

    private Class aClass;
    private Moshi moshi = new Moshi.Builder().build();


    public ObjectHttpCallback(Class aClass) {
        this.aClass = aClass;
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        String body = response.body().string();
        System.out.println(body);
        final JsonAdapter<T> jsonAdapter = moshi.adapter(aClass);
        final T t = jsonAdapter.fromJson(body);
        AppExecutor.get().mainThread().execute(new Runnable() {
            @Override
            public void run() {
                onSuccess(t);
                onFinish();
            }
        });

    }

    @Override
    public void onFailure(Call call, final IOException e) {
        AppExecutor.get().mainThread().execute(new Runnable() {
            @Override
            public void run() {
                onFail(e);
                onFinish();
            }
        });

    }

    public abstract void onSuccess(T t);

    public abstract void onFail(Exception e);

    public abstract void onFinish();

}
