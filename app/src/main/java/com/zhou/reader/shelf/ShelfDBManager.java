package com.zhou.reader.shelf;

import com.zhou.reader.App;
import com.zhou.reader.db.LBook;
import com.zhou.reader.db.LBook_;

import java.util.List;

import io.objectbox.Box;

public class ShelfDBManager {
    Box<LBook> lBookBox;
    private static ShelfDBManager shelfDBManager;
    private ShelfDBManager(){
        lBookBox = App.getBoxStore().boxFor(LBook.class);
    }
    public static ShelfDBManager get(){
        if (shelfDBManager == null){
            shelfDBManager = new ShelfDBManager();
        }
        return shelfDBManager;
    }

    /**
     * 保存书籍到书架
     */
    public long save(LBook lBook){
        if (lBook != null && lBook.id <= 0){
            long id = lBookBox.put(lBook);
            lBook.id = id;
        }
        return lBook.getId();
    }

    /**
     * 删除
     */
    public void delete(LBook lBook){
        if (lBook != null && lBook.id > 0){
            lBookBox.remove(lBook.id);
        }
    }

    /**
     * 获取所有的搜索记录
     * @return
     */
    public List<LBook> getAll(){
        return lBookBox.getAll();
    }

    public LBook findById(long localBookId) {
        return localBookId > 0 ? lBookBox.get(localBookId) : null;
    }

    public LBook findByBookName(String title) {
        return lBookBox.query().equal(LBook_.title,title).build().findFirst();
    }

    public void deleteById(long localBookId) {
        lBookBox.remove(localBookId);
    }
}
