package com.zhou.reader.ui.read;

import com.elvishew.xlog.XLog;
import com.zhou.reader.db.Catalog;
import com.zhou.reader.entity.selector.ContentSelector;
import com.zhou.reader.util.SelectorManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;

public class BookContentUtil {
    public static void loadBookContent(Catalog catalog){
        XLog.d(catalog.toString());
        ContentSelector selector = SelectorManager.get().getSelectSelector().getContent();
        XLog.d(SelectorManager.get().getBaseUrl());
        String url = SelectorManager.get().getBaseUrl() + catalog.getUrl();
        Document document = null;
        try {
            document = Jsoup.parse(new URL(url).openStream(),"GBK","");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element elements = document.getElementById(selector.getContent());
        catalog.setContent(elements.html());
    }
}
