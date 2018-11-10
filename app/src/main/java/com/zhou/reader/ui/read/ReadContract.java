package com.zhou.reader.ui.read;

import com.zhou.reader.db.Catalog;
import com.zhou.reader.entity.TxtChapter;

import java.util.List;

public interface ReadContract{
    interface View  {
        void showCategory(List<Catalog> bookChapterList);
        void finishChapter();
        void errorChapter();
    }

    interface Presenter {
        void loadCategory(String bookId);
        void loadChapter(String bookId,List<TxtChapter> bookChapterList);
    }
}