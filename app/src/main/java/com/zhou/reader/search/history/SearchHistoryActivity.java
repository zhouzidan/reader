package com.zhou.reader.search.history;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.zhou.reader.R;
import com.zhou.reader.base.BaseActivity;

public class SearchHistoryActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_frame_layout;
    }

    @Override
    protected void initData() {
        initFragment();
    }

    private void initFragment() {
        SearchHistoryFragment fragment = (SearchHistoryFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (fragment == null) {
            fragment = new SearchHistoryFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.contentFrame, fragment);
            transaction.commit();
        }
    }

}
