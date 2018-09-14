package com.zhou.reader.detail;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhou.reader.CONST;
import com.zhou.reader.GlideApp;
import com.zhou.reader.R;
import com.zhou.reader.base.BaseActivity;
import com.zhou.reader.entity.Book;
import com.zhou.reader.entity.Catalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class BookDetailActivity extends BaseActivity implements BookDetailContract.View , CatalogAdapter.ClickCallback {

    @BindView(R.id.img)
    ImageView imageView;

    @BindView(R.id.title)
    TextView titleTextView;

    @BindView(R.id.tag)
    TextView tagTextView;

    @BindView(R.id.desc)
    TextView descTextView;

    @BindView(R.id.lastCatalogTextView)
    TextView lastCatalogTextView;

    @BindView(R.id.lastUpdateTextView)
    TextView lastUpdateTimeTextView;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private Book mBook;

    private BookDetailContract.Presenter presenter;

    private CatalogAdapter mCatalogAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_book_detail;
    }

    @Override
    protected void initData() {
        presenter = new BookDetailPresenter(this);
        Intent intent = getIntent();
        mBook = intent.getParcelableExtra(CONST.EXTRA_DATA);

        GlideApp.with(this)
                .load(mBook.getCoverPic())
                .centerCrop()
                .into(imageView);

        Map<String,String> map = mBook.getTags();
        StringBuilder stringBuilder = new StringBuilder();
        if (map != null && map.size() > 0){
            for (Map.Entry<String,String> entry : map.entrySet()){
                String tag = (entry.getKey() +" "+ entry.getValue());
                stringBuilder.append(tag).append("\n");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        tagTextView.setText(stringBuilder.toString());
        titleTextView.setText(mBook.getTitle());
        descTextView.setText(mBook.getDesc());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Catalog> catalogs = mBook.getCatalogs();
        if (catalogs == null){
            catalogs = new ArrayList<>();
            mBook.setCatalogs(catalogs);
        }
        mCatalogAdapter = new CatalogAdapter(this,catalogs);
        mCatalogAdapter.setClickCallback(this);
        mRecyclerView.setAdapter(mCatalogAdapter);

        presenter.loadCatalog(mBook);
    }

    @Override
    public void showData(List<Catalog> catalogs) {
        mBook.getCatalogs().clear();
        mBook.getCatalogs().addAll(catalogs);
        if (!catalogs.isEmpty()){
            Catalog leastCatalog = catalogs.get(catalogs.size() - 1);
            lastCatalogTextView.setText(leastCatalog.getTitle());
        }
        mCatalogAdapter.notifyDataSetChanged();
    }

    //目录点击
    @Override
    public void call(Catalog catalog) {
        // TODO
    }
}
