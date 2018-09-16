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
import com.zhou.reader.read.ReadActivity;
import com.zhou.reader.util.DateUtil;
import com.zhou.reader.util.SafeToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.text.TextUtils.isEmpty;

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

    @BindView(R.id.tv_modify_shelf)
    TextView modifyShelfTextView;

    @BindView(R.id.tv_download_all)
    TextView downloadAllTextView;


    private Book mBook;
    private boolean existInShelf = false;

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
        if (mBook == null) {
            finish();
        }

        GlideApp.with(this)
                .load(mBook.getCoverPic())
                .centerCrop()
                .into(imageView);
        titleTextView.setText(mBook.getTitle());
        authorTextView.setText(mBook.getAuthor());
        typeTextView.setText(mBook.getType());
        lastUpdateTimeTextView.setText(DateUtil.date2String(mBook.getUpdateTime()));
        descTextView.setText(mBook.getDesc());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        List<Catalog> catalogs = mBook.getCatalogs();
        if (catalogs == null) {
            mBook.setCatalogs(new ArrayList<>());
        }
        mCatalogAdapter = new CatalogAdapter(this, mBook.getCatalogs());
        mCatalogAdapter.setClickCallback(this);
        mRecyclerView.setAdapter(mCatalogAdapter);

        presenter.loadBookShelfStatus(mBook);
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

    @Override
    public void showShelfStatus(boolean existInShelf) {
        this.existInShelf = existInShelf;
        modifyShelfTextView.setText(existInShelf ? R.string.remove_from_shelf : R.string.add_to_shelf);
    }

    //目录点击
    @Override
    public void call(Catalog catalog) {
        // TODO
        XLog.d(catalog.toString());
        Intent intent = new Intent(this, ReadActivity.class);
        intent.putExtra(CONST.EXTRA_DATA_CATALOG,catalog);
        startActivity(intent);
    }

    // 加入到书架，或者移除
    @OnClick(R.id.tv_modify_shelf)
    public void onModifyShelfAction(){
        String toastText = null;
        if (existInShelf){
            presenter.removeBookShelf(mBook);
            toastText = getString(R.string.remove_from_shelf) + " " + getString(R.string.action_success);
        }else {
            presenter.addBookToShelf(mBook);
            toastText = getString(R.string.add_to_shelf) + " " + getString(R.string.action_success);
        }
        SafeToast.makeText(toastText).show();
    }

    @OnClick(R.id.tv_start_read)
    public void onStartReadAction(){
        // TODO
    }

    @OnClick(R.id.tv_download_all)
    public void onDownloadAllAction(){
        // TODO
    }

}
