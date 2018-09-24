package com.zhou.reader.db;

import com.zhou.reader.App;

import java.util.List;

import io.objectbox.Box;

public class ShelfDBManager {
    Box<Book> lBookBox;
    private static ShelfDBManager shelfDBManager;
    private ShelfDBManager(){
        lBookBox = App.getBoxStore().boxFor(Book.class);
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
    public long save(Book book){
        if (book != null){
            long id = lBookBox.put(book);
            book.id = id;
        }
        return book.getId();
    }

    /**
     * 删除
     */
    public void delete(Book book){
        if (book != null && book.id > 0){
            lBookBox.remove(book.id);
        }
    }

    /**
     * 获取书架上的所有书籍 onshelf = true
     * @return
     */
    public List<Book> getAll(){
        return lBookBox
                .query()
                .equal(Book_.onShelf,true)
                .build()
                .find();
    }

    public Book findById(long localBookId) {
        return localBookId > 0 ? lBookBox.get(localBookId) : null;
    }

    public Book findByBookName(String title) {
        return lBookBox.query().equal(Book_.title,title).build().findFirst();
    }

    public void deleteById(long localBookId) {
        if (localBookId > 0){
            lBookBox.remove(localBookId);
        }
    }

    public Book findOnShelfById(long id) {
        return lBookBox.query().equal(Book_.id,id).equal(Book_.onShelf,true).build().findFirst();
    }
}
