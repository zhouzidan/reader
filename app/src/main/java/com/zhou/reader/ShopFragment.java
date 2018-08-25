package com.zhou.reader;

import android.graphics.Color;
import android.view.View;

import com.zhou.reader.base.BaseFragment;

public class ShopFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shelf;
    }

    @Override
    protected void initView(View view) {
        view.setBackgroundColor(Color.GRAY);
    }

    @Override
    protected void initData(View view) {

    }
}