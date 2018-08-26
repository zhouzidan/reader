package com.zhou.reader.base.recyclerView;

import android.view.ViewGroup;

public class ViewHolderFactory {
    private static ViewHolderFactory instance;
    private ViewHolderFactory(){}
    public static ViewHolderFactory get(){
        if (instance == null){
            instance = new ViewHolderFactory();
        }
        return instance;
    }

    public BaseViewHolder createViewHolder(ViewGroup parent, int type){
        return null;
    }

}
