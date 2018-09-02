package com.zhou.reader.search;

import com.zhou.reader.entity.Book;
import com.zhou.reader.entity.Catalog;
import com.zhou.reader.entity.SearchResult;
import com.zhou.reader.entity.selector.CatalogSelector;
import com.zhou.reader.entity.selector.SearchSelector;
import com.zhou.reader.http.HttpUtil;
import com.zhou.reader.http.BookSearchCallback;
import com.zhou.reader.util.SelectorManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookSearchUtil {

    public static void search(String keyword,BookSearchCallback searchCallback){
        String selectorUrl = SelectorManager.get().getSelectSearchUrl();
        String searchUrl = String.format(selectorUrl,keyword);
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
            Map<String,String> tags = new HashMap<>();
            for (Element tagElement : tagElements) {
                List<Element> nameAndValueElement = tagElement.children();
                if (nameAndValueElement != null && nameAndValueElement.size() == 2){
                    Element nameElement = nameAndValueElement.get(0);
                    Element valueElement = nameAndValueElement.get(1);
                    tags.put(nameElement.text(),valueElement.text());
                }else if (nameAndValueElement != null && nameAndValueElement.size() == 1){
                    Element nameElement = nameAndValueElement.get(0);
                    tags.put(nameElement.text(),null);
                }else {
                    tags.put(tagElement.text(),null);
                }
            }
            book.setTags(tags);
            books.add(book);
        }
        searchResult.setBooks(books);
        return searchResult;
    }

    public static List<Catalog> getCatalog(String url){
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
            catalog.setId(i);
            catalog.setTitle(element.text());
            catalog.setUrl(element.attr("href"));
            System.out.println(catalog.toString());
            catalogs.add(catalog);
        }
        return catalogs;
    }
}
