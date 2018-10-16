package com.zhou.reader.read;

import android.text.TextUtils;

import com.zhou.reader.db.Book;
import com.zhou.reader.db.BookDBManager;
import com.zhou.reader.db.Catalog;
import com.zhou.reader.db.CatalogDBManager;
import com.zhou.reader.db.ReadRecord;
import com.zhou.reader.db.ReadRecordDBManager;
import com.zhou.reader.util.AppExecutor;

import java.util.List;

public class ReadPresenter implements ReadContact.Presenter {
    private ReadContact.View view;
    private Catalog currentCatalog;
    private List<Catalog> catalogs;
    private Book book;


    public ReadPresenter(ReadContact.View view) {
        this.view = view;
    }


    @Override
    public void loadBookAndCatalogs(long localBookId) {
        book = BookDBManager.get().findById(localBookId);
        List<Catalog> catalogs = CatalogDBManager.get().getAll(localBookId);
        view.loadBookAndCatalogs(book, catalogs);
    }

    @Override
    public void loadCurrentContent(long localCatalogId) {
        Catalog catalog = getDefaultCatalog(localCatalogId);
        loadContent(catalog);
    }


    @Override
    public void loadNextContent() {
        this.currentCatalog.setHasRead(true);
        CatalogDBManager.get().save(this.currentCatalog);
        Catalog catalog = getNextCatalog();
        loadContent(catalog);
    }

    @Override
    public void loadLastContent() {
        Catalog catalog = getLastCatalog() ;
        if (catalog != null){
            loadContent(catalog);
        }else {
            view.showMessage("未找到上一章节");
        }

    }

    @Override
    public void loadContent(Catalog catalog) {
        this.currentCatalog = catalog;
        view.showCurrentCatalog(catalog);
        view.showLoading();
        AppExecutor.get().networkIO().execute(() -> {
            Catalog tempCatalog = CatalogDBManager.get().findById(catalog.id);
            if (tempCatalog != null && TextUtils.isEmpty(tempCatalog.getContent())){
                BookContentUtil.loadBookContent(tempCatalog);
                CatalogDBManager.get().save(tempCatalog);
                catalog.setContent(tempCatalog.getContent());
            }
            AppExecutor.get().mainThread().execute(() -> {
                view.showBookContent(tempCatalog);
                view.hideLoading();
            });
            preNextContent();
            preLastContent();
        });
    }

    @Override
    public void preNextContent() {
        // 获取上次的阅读
        Catalog nextCatalog = getNextCatalog();
        downloadAndSave(nextCatalog, false);
    }

    @Override
    public void preLastContent() {
        Catalog lastCatalog = getLastCatalog();
        downloadAndSave(lastCatalog, false);
    }

    private void downloadAndSave(Catalog catalog, boolean forceUpdate) {
        if (catalog != null) {
            if (TextUtils.isEmpty(catalog.getContent()) || forceUpdate){
                AppExecutor.get().networkIO().execute(() -> {
                    BookContentUtil.loadBookContent(catalog);
                    CatalogDBManager.get().save(catalog);
                });
            }
        }
    }

    private Catalog getNextCatalog(){
        Catalog nextCatalog = null;
        if (catalogs == null || catalogs.size() == 0) {
            catalogs = CatalogDBManager.get().getAll(book.getId());
        }
        if (catalogs != null) {
            int index = catalogs.indexOf(this.currentCatalog);
            if ((index + 1) < catalogs.size()) {
                nextCatalog = catalogs.get(index + 1);
            }
        }
        return nextCatalog;
    }

    private Catalog getLastCatalog(){
        Catalog lastCatalog = null;
        if (catalogs == null || catalogs.size() == 0) {
            catalogs = CatalogDBManager.get().getAll(book.getId());
        }
        if (catalogs != null) {
            int index = catalogs.indexOf(this.currentCatalog);
            if ((index - 1) >= 0) {
                lastCatalog = catalogs.get(index - 1);
            }
        }
        return lastCatalog;
    }

    private Catalog getDefaultCatalog(long localCatalogId){
        Catalog catalog = null;
        if (localCatalogId > 0){
            catalog = CatalogDBManager.get().findById(localCatalogId);
        }
        if (catalog == null){
            ReadRecord readRecord = ReadRecordDBManager.get().getLeast(book.getId());
            if (readRecord != null){
                catalog = CatalogDBManager.get().findById(readRecord.localCatalogId);
            }
        }
        if (catalog == null){
            catalog = CatalogDBManager.get().getFirst(book.getId());
        }
        return catalog;
    }

    public void saveReadRecord(){
        ReadRecord readRecord = new ReadRecord();
        readRecord.setLocalBookId(this.currentCatalog.getBookId());
        readRecord.setLocalCatalogId(this.currentCatalog.getId());
        ReadRecordDBManager.get().save(readRecord);
    }

    @Override
    public void distachView() {
        this.view = null;
    }
}
