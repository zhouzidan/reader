package com.zhou.reader.shelf;

import com.zhou.reader.URLCONST;
import com.zhou.reader.entity.M;
import com.zhou.reader.entity.SearchResult;
import com.zhou.reader.http.HttpUtil;
import com.zhou.reader.http.ObjectHttpCallback;

import java.util.List;

public class ShelfModle {
    public static List getBooks(){
        System.out.println("getBooks");

        return null;
    }

    /**
     * https://xiadd.github.io/zhuishushenqi/#/?id=获取搜索结果
     * http://novel.juhe.im/search?keyword=遮天
     * @param name
     */
    public static void searchBooks(String name){
        String url = "http://novel.juhe.im/search?keyword="+ name;
        System.out.println(url);
        HttpUtil.doGet(url, new ObjectHttpCallback<SearchResult>(SearchResult.class) {

            @Override
            public void onSuccess(SearchResult o) {
                System.out.println(o);
            }

            @Override
            public void onFail(Exception e) {

            }

            @Override
            public void onFinish() {

            }
        });
    }
}
