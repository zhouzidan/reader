package com.zhou.reader.db;

import com.zhou.reader.App;

import java.util.List;

import io.objectbox.Box;

public class CatalogDBManager {
    Box<Catalog> lCatalogBox;
    private static CatalogDBManager catalogDBManager;
    private CatalogDBManager(){
        lCatalogBox = App.getBoxStore().boxFor(Catalog.class);
    }
    public static CatalogDBManager get(){
        if (catalogDBManager == null){
            catalogDBManager = new CatalogDBManager();
        }
        return catalogDBManager;
    }

    /**
     * 保存书籍到书架
     */
    public long save(Catalog catalog){
        if (catalog != null){
            long id = lCatalogBox.put(catalog);
            catalog.id = id;
        }
        return catalog.getId();
    }

    /**
     * 删除
     */
    public void delete(Catalog catalog){
        if (catalog != null && catalog.id > 0){
            lCatalogBox.remove(catalog.id);
        }
    }

    /**
     * 获取书架上的所有书籍 onshelf = true
     * @return
     */
    public List<Catalog> getAll(long localBookId){
        return lCatalogBox
                .query()
                .equal(Catalog_.bookId,localBookId)
                .build()
                .find();
    }

    public Catalog findById(long localCatalogId) {
        return localCatalogId > 0 ? lCatalogBox.get(localCatalogId) : null;
    }

    public Catalog findByBookName(String title) {
        return lCatalogBox.query().equal(Catalog_.title,title).build().findFirst();
    }

    public void deleteById(long localCatalogId) {
        lCatalogBox.remove(localCatalogId);
    }

    public void deleteByBookId(long localBookId){
        List<Catalog> catalogs = lCatalogBox.find(Catalog_.bookId,localBookId);
        lCatalogBox.remove(catalogs);
    }

    public void save(List<Catalog> catalogs) {
        if (catalogs != null && catalogs.size() > 0){
            lCatalogBox.put(catalogs);
        }
    }

    public Catalog getFirst(long localBookId) {
        return lCatalogBox
                .query()
                .equal(Catalog_.bookId,localBookId)
                .build()
                .findFirst();
    }

}
