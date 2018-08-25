package com.zhou.reader.http;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

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
        onSuccess(jsonAdapter.fromJson(body));
        onFinish();
    }

    @Override
    public void onFailure(Call call, IOException e) {
        onFail(e);
        onFinish();
    }

    public abstract void onSuccess(T t);

    public abstract void onFail(Exception e);

    public abstract void onFinish();

}
