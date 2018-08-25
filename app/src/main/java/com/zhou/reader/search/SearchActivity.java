package com.zhou.reader.search;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.zhou.reader.R;
import com.zhou.reader.base.BaseActivity;

public class SearchActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_consider;
    }

    @Override
    protected void initData() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new SearchFragment();
        fragmentManager.beginTransaction().replace(R.id.fragmentLayout,fragment).commit();
    }
}
