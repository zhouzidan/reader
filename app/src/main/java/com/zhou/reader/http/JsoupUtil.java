package com.zhou.reader.http;

import java.io.IOException;

public class JsoupUtil {
    public static String getHtml(String url) throws IOException {
        HttpUtil.doGet(url, new HtmlCallback() {
            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onFail(Exception e) {

            }

            @Override
            public void onFinish() {

            }
        });
        return null;
    }
}
