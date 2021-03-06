package com.zhou.reader.shelf;

import com.elvishew.xlog.XLog;
import com.zhou.reader.db.Book;
import com.zhou.reader.entity.SearchResult;
import com.zhou.reader.http.HttpUtil;
import com.zhou.reader.http.ObjectHttpCallback;

import java.util.List;

public class ShelfModle {
    public static List<Book> getBooks(){
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
            public void onSuccess(SearchResult searchResult) {
                XLog.d(searchResult);
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
