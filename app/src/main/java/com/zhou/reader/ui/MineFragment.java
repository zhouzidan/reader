package com.zhou.reader.ui;

import android.content.Intent;
import android.view.View;

import com.zhou.reader.R;
import com.zhou.reader.base.BaseFragment;
import com.zhou.reader.ui.read.history.ReadHistoryActivity;
import com.zhou.reader.ui.search.history.SearchHistoryActivity;
import com.zhou.reader.ui.setting.ReadSettingsActivity;

import butterknife.OnClick;

public class MineFragment extends BaseFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData(View view) {

    }

    @OnClick(R.id.tv_about)
    public void onActionGo2About() {
        Intent intent = new Intent(getContext(), AboutActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tv_read_setting)
    public void onActionGo2ReadSetting() {
        Intent intent = new Intent(getContext(), ReadSettingsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tv_search_history)
    public void onActionGo2SearchHistory() {
        Intent intent = new Intent(getContext(), SearchHistoryActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tv_read_history)
    public void onActionGo2ReadHistory() {
        Intent intent = new Intent(getContext(), ReadHistoryActivity.class);
        startActivity(intent);
    }

}
