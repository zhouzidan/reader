package com.zhou.reader.db;

import com.zhou.reader.App;

import java.util.List;

import io.objectbox.Box;

import static android.text.TextUtils.isEmpty;

public class SearchDBManager {
    Box<Search> searchBox;
    private static SearchDBManager searchDBManager;

    private SearchDBManager() {
        searchBox = App.getBoxStore().boxFor(Search.class);
    }

    public static SearchDBManager get() {
        if (searchDBManager == null) {
            searchDBManager = new SearchDBManager();
        }
        return searchDBManager;
    }


    /**
     * 保存搜索记录
     *
     * @param str
     */
    public void save(String str) {
        delete(str);
        if (!isEmpty(str) && searchBox != null) {
            Search search = new Search();
            search.content = str;
            search.updateTime = System.currentTimeMillis();
            searchBox.put(search);
        }
    }

    /**
     * 删除记录
     *
     * @param str
     */
    public void delete(String str) {
        if (!isEmpty(str) && searchBox != null) {
            List<Search> searches = searchBox.query().equal(Search_.content, str).build().find();
            for (Search search : searches) {
                searchBox.remove(search);
            }
        }
    }

    /**
     * 获取所有的搜索记录
     *
     * @return
     */
    public List<Search> getAll() {
        if (searchBox != null) {
            return searchBox.query().orderDesc(Search_.updateTime).build().find();
        }
        return null;
    }

    public void clear() {
        searchBox.removeAll();
    }

}
