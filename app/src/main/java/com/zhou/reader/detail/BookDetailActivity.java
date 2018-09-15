package com.zhou.reader.detail;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.elvishew.xlog.XLog;
import com.zhou.reader.CONST;
import com.zhou.reader.GlideApp;
import com.zhou.reader.R;
import com.zhou.reader.base.BaseActivity;
import com.zhou.reader.entity.Book;
import com.zhou.reader.entity.Catalog;

import java.util.List;

import butterknife.BindView;

public class BookDetailActivity extends BaseActivity implements BookDetailContract.View, CatalogAdapter.ClickCallback {

    @BindView(R.id.img)
    ImageView imageView;

    @BindView(R.id.title)
    TextView titleTextView;

    @BindView(R.id.author)
    TextView authorTextView;
    @BindView(R.id.type)
    TextView typeTextView;
    @BindView(R.id.lastUpdateTime)
    TextView lastUpdateTimeTextView;

    @BindView(R.id.desc)
    TextView descTextView;

    @BindView(R.id.lastCatalogTextView)
    TextView lastCatalogTextView;

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
        titleTextView.setText(mBook.getTitle());
        authorTextView.setText(mBook.getAuthor());
        typeTextView.setText(mBook.getType());
        lastUpdateTimeTextView.setText(mBook.getUpdateTime() + "");
        descTextView.setText(mBook.getDesc());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        List<Catalog> catalogs = mBook.getCatalogs();
        mBook.getCatalogs().addAll(catalogs);
        mCatalogAdapter = new CatalogAdapter(this, mBook.getCatalogs());
        mCatalogAdapter.setClickCallback(this);
        mRecyclerView.setAdapter(mCatalogAdapter);

        presenter.loadCatalog(mBook);
    }

    @Override
    public void showData(List<Catalog> catalogs) {
        mBook.getCatalogs().clear();
        mBook.getCatalogs().addAll(catalogs);
        if (!catalogs.isEmpty()) {
            Catalog leastCatalog = catalogs.get(catalogs.size() - 1);
            lastCatalogTextView.setText(leastCatalog.getTitle());
        }
        mCatalogAdapter.notifyDataSetChanged();
    }

    //目录点击
    @Override
    public void call(Catalog catalog) {
        // TODO
        XLog.d(catalog.toString());
    }
}
