package com.zhou.reader.ui.read;

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
        view.showBookContent(catalog);
    }


    @Override
    public void loadNextContent() {
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
        view.showCurrentCatalogTitle(catalog);
        saveReadRecord();
        view.showLoading();
        AppExecutor.get().networkIO().execute(() -> {
            this.currentCatalog = CatalogDBManager.get().findById(catalog.id);
            if (this.currentCatalog != null && TextUtils.isEmpty(this.currentCatalog.getContent())){
                BookContentUtil.loadBookContent(this.currentCatalog);
                catalog.setContent(this.currentCatalog.getContent());
            }
            if (this.currentCatalog != null) {
                this.currentCatalog.setHasRead(true);
                CatalogDBManager.get().save(this.currentCatalog);
            }
            AppExecutor.get().mainThread().execute(() -> {
                view.showBookContent(this.currentCatalog);
                view.hideLoading();
            });
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
            Catalog catalog = getCurrentCatalog();
            int index = catalogs.indexOf(catalog);
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
            Catalog catalog = getCurrentCatalog();
            int index = catalogs.indexOf(catalog);
            if ((index - 1) >= 0) {
                lastCatalog = catalogs.get(index - 1);
            }
        }
        return lastCatalog;
    }

    // 根据阅读记录，获取当前正在阅读的章节信息
    private Catalog getCurrentCatalog(){
        ReadRecord readRecord = ReadRecordDBManager.get().getLeast(book.getId());
        return CatalogDBManager.get().findById(readRecord.getLocalCatalogId());
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
        readRecord.setUpdateTime(System.currentTimeMillis());
        ReadRecordDBManager.get().save(readRecord);
    }

    @Override
    public void saveReadRecord(Catalog catalog) {
        ReadRecord readRecord = new ReadRecord();
        readRecord.setLocalBookId(catalog.getBookId());
        readRecord.setLocalCatalogId(catalog.getId());
        readRecord.setUpdateTime(System.currentTimeMillis());
        ReadRecordDBManager.get().save(readRecord);
    }

    @Override
    public void distachView() {
        this.view = null;
    }
}
