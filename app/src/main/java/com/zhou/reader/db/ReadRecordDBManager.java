package com.zhou.reader.db;

import com.elvishew.xlog.XLog;
import com.zhou.reader.App;

import java.util.List;

import io.objectbox.Box;

public class ReadRecordDBManager {
    Box<ReadRecord> readRecordBox;
    private static ReadRecordDBManager recordDBManager;

    private ReadRecordDBManager() {
        readRecordBox = App.getBoxStore().boxFor(ReadRecord.class);
    }

    public static ReadRecordDBManager get() {
        if (recordDBManager == null) {
            recordDBManager = new ReadRecordDBManager();
        }
        return recordDBManager;
    }

    public ReadRecord getLeast(long localBookId) {
        ReadRecord result = readRecordBox.query()
                .equal(ReadRecord_.localBookId, localBookId)
                .orderDesc(ReadRecord_.updateTime)
                .build()
                .findFirst();
        if (result != null) {
            XLog.d(result);
            return result;
        } else {
            Catalog catalog = CatalogDBManager.get().getFirst(localBookId);
            ReadRecord readRecord = new ReadRecord();
            readRecord.localCatalogId = catalog.id;
            readRecord.localBookId = catalog.bookId;
            return readRecord;
        }
    }

    public void save(ReadRecord readRecord) {
        if (readRecord != null
                && readRecord.localBookId > 0
                && readRecord.localCatalogId > 0) {
            long id = readRecordBox.put(readRecord);
            readRecord.id = id;
        }
    }

    private void remove(long localBookId) {
        List<ReadRecord> readRecords = readRecordBox.find(ReadRecord_.localBookId, localBookId);
        readRecordBox.remove(readRecords);
    }

    public boolean getHasRead(long localCatalogId) {
        int size = readRecordBox.find(ReadRecord_.localCatalogId, localCatalogId).size();
        return size > 0;
    }
}
