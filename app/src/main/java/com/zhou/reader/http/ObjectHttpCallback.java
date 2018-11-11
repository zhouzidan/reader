package com.zhou.reader.http;

import com.zhou.reader.util.AppExecutor;
import com.zhou.reader.util.JsonUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class ObjectHttpCallback<T> implements Callback {

    private Class<T> aClass;

    public ObjectHttpCallback(Class<T> aClass) {
        this.aClass = aClass;
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        String body = response.body().string();
        System.out.println(body);
        final T t = JsonUtil.fromJson(body,aClass);
        AppExecutor.get().mainThread().execute(() -> {
            onSuccess(t);
            onFinish();
        });

    }

    @Override
    public void onFailure(Call call, final IOException e) {
        AppExecutor.get().mainThread().execute(() -> {
            onFail(e);
            onFinish();
        });

    }

    public abstract void onSuccess(T t);

    public abstract void onFail(Exception e);

    public abstract void onFinish();

}
