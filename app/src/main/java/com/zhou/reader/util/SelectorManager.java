package com.zhou.reader.util;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.zhou.reader.App;
import com.zhou.reader.CONST;
import com.zhou.reader.entity.selector.Selector;
import com.zhou.reader.entity.selector.SelectorEnum;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class SelectorManager {
    private static SelectorManager INSTANCE;
    private SelectorManager(){}

    private List<Selector> selectors;

    public static SelectorManager get(){
        if (INSTANCE == null){
            INSTANCE = new SelectorManager();
        }
        return INSTANCE;
    }

    public void init() {
        try {
            Moshi moshi = new Moshi.Builder().build();
            String json = AssetsUtils.readAssetsTxt(App.getAppContext(), "Template.json");
            Type type = Types.newParameterizedType(List.class, Selector.class);
            final JsonAdapter<List<Selector>> jsonAdapter = moshi.adapter(type);
            selectors = jsonAdapter.fromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Selector getSelectSelector(){
//        int selectIndex = SPUtil.getInt(CONST.SELECTED_SELECTOR);
//        return selectors.get(selectIndex);
        return selectors.get(1);
    }

    public void setSelectSeletor(SelectorEnum selectorEnum){
        SPUtil.put(CONST.SELECTED_SELECTOR,selectorEnum.getValue());
    }

    public String getSelectSearchUrl(){
        Selector selector = getSelectSelector();
        return selector.getBaseUrl() + selector.getSearchUrl();
    }

    public String getBaseUrl(){
        return getSelectSelector().getBaseUrl();
    }


}
