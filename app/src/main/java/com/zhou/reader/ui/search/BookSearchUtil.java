package com.zhou.reader.ui.search;

import com.elvishew.xlog.XLog;
import com.zhou.reader.db.Book;
import com.zhou.reader.db.Catalog;
import com.zhou.reader.entity.SearchResult;
import com.zhou.reader.entity.selector.CatalogSelector;
import com.zhou.reader.entity.selector.SearchSelector;
import com.zhou.reader.http.HttpUtil;
import com.zhou.reader.http.BookSearchCallback;
import com.zhou.reader.util.DateUtil;
import com.zhou.reader.util.SelectorManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.text.TextUtils.isEmpty;

public class BookSearchUtil {

    public static void search(String keyword,BookSearchCallback searchCallback){
        String selectorUrl = SelectorManager.get().getSelectSearchUrl();
        String searchUrl = String.format(selectorUrl,keyword);
        if (searchCallback != null){
            searchCallback.setKeyword(keyword);
        }
        HttpUtil.doGet(searchUrl,searchCallback);
    }

    public static void getCatalog(String url,BookCatalogCallback bookCatalogCallback){
        HttpUtil.doGet(url,bookCatalogCallback);
    }


    public static SearchResult getSearchResult(String html,SearchSelector selector){
        SearchResult searchResult = new SearchResult();
        Document document = Jsoup.parse(html);
        Elements elements = document.select(selector.getItem());
        List<Book> books = new ArrayList<>();
        for (Element element : elements) {
            Book book = new Book();
            Element linkElement = element.selectFirst(selector.getLink());
            if (linkElement != null){
                String link = linkElement.attr("abs:href");
                book.setLink(link);
            }

            Element picElement = element.selectFirst(selector.getCoverPic());
            if (picElement != null){
                String coverPic = picElement.attr("abs:src");
                book.setCoverPic(coverPic);
            }

            Element titleElement = element.selectFirst(selector.getTitle());
            if (titleElement != null){
                book.setTitle(titleElement.text());
            }

            Element descElement = element.selectFirst(selector.getDesc());
            if (descElement != null){
                book.setDesc(descElement.text());
            }

            Elements tagElements = element.select(selector.getTag());
            for (Element tagElement : tagElements) {
                List<Element> nameAndValueElement = tagElement.children();
                if (nameAndValueElement != null && nameAndValueElement.size() == 2){
                    Element nameElement = nameAndValueElement.get(0);
                    Element valueElement = nameAndValueElement.get(1);
                    if (nameElement != null && valueElement != null){
                        String name = nameElement.text();
                        String value = valueElement.text();
                        pickDataForBook(book,name,value);
                    }
                }
            }
            books.add(book);
        }
        searchResult.setBooks(books);
        return searchResult;
    }

    private static final String[] NAME_AUTHOR = {"作者"};
    private static final String[] NAME_BOOK_TYPE = {"类型"};
    private static final String[] NAME_UPDATE_TIME = {"更新时间"};
    private static final String[] NAME_LAST_CATALOG = {"最新章节"};

    private static void pickDataForBook(Book book, String name, String value){
        if (book != null && !isEmpty(name) && !isEmpty(value)){
            XLog.d("name:"+name + " value:"+value);
            for (String authorName : NAME_AUTHOR) {
                if (name.contains(authorName)){
                    book.setAuthor(value);
                    return;
                }
            }
            for (String typeName : NAME_BOOK_TYPE) {
                if (name.contains(typeName)){
                    book.setType(value);
                    return;
                }
            }
            for (String updateName : NAME_UPDATE_TIME) {
                if (name.contains(updateName)){
                    book.setUpdateTime(DateUtil.string2Date(value,DateUtil.DateFormatYYYYMMDD).getTime());
                    return;
                }
            }
            for (String catalogName : NAME_LAST_CATALOG) {
                if (name.contains(catalogName)){
                    book.setLeastCatalog(value);
                    return;
                }
            }

        }
    }

    public static List<Catalog> getCatalog(Book book){
        String url = book.getLink();
        long localBookId = book.getId();
        CatalogSelector selector = SelectorManager.get().getSelectSelector().getCatalog();
        List<Catalog> catalogs = new ArrayList<>();
        Document document = null;
        try {
            document = Jsoup.parse(new URL(url).openStream(),"GBK","");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements = document.select(selector.getSelector());
        for (int i = 0; i < elements.size() ; i++) {
            Element element = elements.get(i);
            Catalog catalog = new Catalog();
            catalog.setIndex(i);
            catalog.setTitle(element.text());
            catalog.setUrl(element.attr("href"));
            catalog.setBookId(localBookId);
            catalogs.add(catalog);
        }
        return catalogs;
    }
}
