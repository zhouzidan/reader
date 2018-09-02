package com.zhou.reader.entity.selector;

public class CatalogSelector {
    private String selector = "body > div.wrapper > div.box_con > div#list > dl > dd > a";

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }


    @Override
    public String toString() {
        return "CatalogSelector{" +
                "selector='" + selector + '\'' +
                '}';
    }
}
