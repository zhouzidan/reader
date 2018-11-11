package com.zhou.reader.ui.read;

import android.content.Intent;
import android.text.TextUtils;

import com.zhou.reader.App;
import com.zhou.reader.CONST;
import com.zhou.reader.db.Book;
import com.zhou.reader.db.BookDBManager;
import com.zhou.reader.db.Catalog;
import com.zhou.reader.db.CatalogDBManager;
import com.zhou.reader.db.ReadRecord;
import com.zhou.reader.db.ReadRecordDBManager;
import com.zhou.reader.util.AppExecutor;
import com.zhou.reader.util.JsonUtil;
import com.zhou.reader.util.ListUtil;

import java.util.ArrayList;
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
    public void loadData(Intent intent) {
        AppExecutor.get().diskIO().execute(() -> {
            if (intent != null) {
                long localBookId = intent.getLongExtra(CONST.EXTRA_BOOK_ID, 0);
                if (localBookId > 0) {
                    book = BookDBManager.get().findById(localBookId);
                }
                if (book == null) {
                    String bookJson = intent.getStringExtra(CONST.EXTRA_BOOK);
                    book = JsonUtil.fromJson(bookJson, Book.class);
                }

                String catalogListJson = intent.getStringExtra(CONST.EXTRA_CATALOGS);
                catalogs = JsonUtil.fromJsonToList(catalogListJson, Catalog.class);
                if (ListUtil.isEmpty(catalogs) && book != null && book.getId() > 0){
                    catalogs = CatalogDBManager.get().getAll(book.getId());
                }

                String selectedCatalogJson = intent.getStringExtra(CONST.EXTRA_CATALOG);
                currentCatalog = JsonUtil.fromJson(selectedCatalogJson, Catalog.class);
            }
            AppExecutor.get().mainThread().execute(() -> view.loadBookAndCatalogs());
        });

    }

    @Override
    public Book getBook() {
        return book;
    }

    @Override
    public List<Catalog> getCatalogs() {
        return catalogs;
    }

    // 根据阅读记录，获取当前正在阅读的章节信息
    public Catalog getCurrentCatalog(){
        if (currentCatalog == null){
            if (book.getId() > 0){
                ReadRecord readRecord = ReadRecordDBManager.get().getLeast(book.getId());
                currentCatalog = CatalogDBManager.get().findById(readRecord.getLocalCatalogId());
            }
            if (currentCatalog == null && catalogs.size() > 0){
                currentCatalog = catalogs.get(0);
            }
        }
        return currentCatalog;
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
        saveReadRecord(catalog);
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
        if (catalogs != null) {
            Catalog catalog = getCurrentCatalog();
            int index = catalogs.indexOf(catalog);
            if ((index - 1) >= 0) {
                lastCatalog = catalogs.get(index - 1);
            }
        }
        return lastCatalog;
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
