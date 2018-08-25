package com.zhou.reader.shelf;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhou.reader.R;
import com.zhou.reader.base.BaseFragment;

import java.util.List;

import butterknife.BindView;

public class ShelfFragment extends BaseFragment implements ShelfContact.View{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    ShelfPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shelf;
    }

    @Override
    protected void initView(View view) {
        view.setBackgroundColor(Color.WHITE);
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
//        recyclerView.setAdapter();
        presenter = new ShelfPresenter(this);
        presenter.loadShelfBooks();
    }

    @Override
    protected void initData(View view) {

    }

    @Override
    public void showBooks(List books) {

    }
}
