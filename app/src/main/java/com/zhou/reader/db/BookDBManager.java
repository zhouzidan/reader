package com.zhou.reader.db;

import com.zhou.reader.App;

import java.util.List;

import io.objectbox.Box;

public class BookDBManager {
    Box<Book> lBookBox;
    private static BookDBManager bookDBManager;
    private BookDBManager(){
        lBookBox = App.getBoxStore().boxFor(Book.class);
    }
    public static BookDBManager get(){
        if (bookDBManager == null){
            bookDBManager = new BookDBManager();
        }
        return bookDBManager;
    }

    /**
     * 保存书籍到书架
     */
    public long save(Book book){
        if (book != null && book.id <= 0){
            long id = lBookBox.put(book);
            book.setId(id);
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
    public List<Book> getAll(long localBookId){
        return lBookBox.find(Book_.id,localBookId);
    }

    public Book findById(long localBookId) {
        return localBookId > 0 ? lBookBox.get(localBookId) : null;
    }

    public Book findByBookName(String title) {
        return lBookBox.query().equal(Book_.title,title).build().findFirst();
    }

    public void deleteById(long localBookId) {
        lBookBox.remove(localBookId);
        CatalogDBManager.get().deleteByBookId(localBookId);
    }

    public Book getFirst(long localBookId) {
        return lBookBox
                .query()
                .equal(Book_.id,localBookId)
                .build()
                .findFirst();
    }
}
