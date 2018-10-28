package com.zhou.reader.search.history;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhou.reader.R;
import com.zhou.reader.base.BaseFragment;

import butterknife.BindView;

public class SearchHistoryFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recyclerview;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData(View view) {

    }
}
