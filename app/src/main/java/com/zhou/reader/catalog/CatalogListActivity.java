package com.zhou.reader.catalog;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhou.reader.R;
import com.zhou.reader.base.BaseActivity;
import com.zhou.reader.db.Catalog;
import com.zhou.reader.detail.CatalogAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CatalogListActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private CatalogAdapter catalogAdapter;
    List<Catalog> catalogs = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_catalog_list;
    }

    @Override
    protected void initData() {
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        catalogAdapter = new CatalogAdapter(this,catalogs);
    }
}
