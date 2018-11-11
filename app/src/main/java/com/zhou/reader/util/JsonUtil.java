package com.zhou.reader.util;

import android.text.TextUtils;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonUtil {
    private static Moshi moshi = new Moshi.Builder().build();

    public static <T> String toJson(T t, Class<T> tClass) {
        JsonAdapter<T> jsonAdapter = moshi.adapter(tClass);
        return jsonAdapter.toJson(t);
    }

    public static <T> String toJson(List<T> tList, Class<T> tClass) {
        Type type = Types.newParameterizedType(List.class, tClass);
        JsonAdapter<List<T>> jsonAdapter = moshi.adapter(type);
        return jsonAdapter.toJson(tList);
    }

    public static <T> T fromJson(String json, Class<T> aClass) {
        if (!TextUtils.isEmpty(json)) {
            final JsonAdapter<T> jsonAdapter = moshi.adapter(aClass);
            try {
                return jsonAdapter.fromJson(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static <T> List<T> fromJsonToList(String json, Class<T> tClass) {
        if (!TextUtils.isEmpty(json)) {
            Type type = Types.newParameterizedType(List.class, tClass);
            final JsonAdapter<List<T>> jsonAdapter = moshi.adapter(type);
            try {
                return jsonAdapter.fromJson(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
