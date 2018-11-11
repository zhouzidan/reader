package com.zhou.reader.ui.detail;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.elvishew.xlog.XLog;
import com.zhou.reader.CONST;
import com.zhou.reader.GlideApp;
import com.zhou.reader.R;
import com.zhou.reader.base.BaseActivity;
import com.zhou.reader.db.Book;
import com.zhou.reader.db.Catalog;
import com.zhou.reader.db.ShelfDBManager;
import com.zhou.reader.ui.read.ReadActivity;
import com.zhou.reader.util.DateUtil;
import com.zhou.reader.util.JsonUtil;
import com.zhou.reader.util.SafeToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BookDetailActivity extends BaseActivity implements BookDetailContract.View, CatalogAdapter.ClickCallback {

    private static final String TAG = "BookDetailActivity";
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
    List<Catalog> catalogs = new ArrayList<>();
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
        long localBookId = intent.getLongExtra(CONST.EXTRA_BOOK_ID, 0);
        mBook = ShelfDBManager.get().findById(localBookId);
        if (mBook == null) {
            String bookJson = intent.getStringExtra(CONST.EXTRA_BOOK);
            if (!TextUtils.isEmpty(bookJson)) {
                mBook = JsonUtil.fromJson(bookJson, Book.class);
            }
        }
        if (localBookId == 0 && mBook == null) {
            finish();
            return;
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mCatalogAdapter = new CatalogAdapter(this);
        mCatalogAdapter.setCatalogs(catalogs);
        mCatalogAdapter.setClickCallback(this);
        mRecyclerView.setAdapter(mCatalogAdapter);

        presenter.loadBookAndCatalog(mBook);

    }

    private void loadBookDetail() {
        if (mBook != null) {
            GlideApp.with(this)
                    .load(mBook.getCoverPic())
                    .centerCrop()
                    .into(imageView);
            titleTextView.setText(mBook.getTitle());
            authorTextView.setText(mBook.getAuthor());
            typeTextView.setText(mBook.getType());
            lastUpdateTimeTextView.setText(DateUtil.date2String(mBook.getUpdateTime()));
            descTextView.setText(mBook.getDesc());
        }
    }


    @Override
    public void showShelfStatus(boolean existInShelf) {
        this.existInShelf = existInShelf;
        modifyShelfTextView.setText(existInShelf ? R.string.remove_from_shelf : R.string.add_to_shelf);
    }

    @Override
    public void loadBookAndCatalogs(Book book, List<Catalog> localCatalogList) {
        this.mBook = book;
        loadBookDetail();
        presenter.loadBookShelfStatus(mBook);
        if (localCatalogList != null && !localCatalogList.isEmpty()) {
            this.catalogs.clear();
            this.catalogs.addAll(localCatalogList);
            Catalog leastCatalog = catalogs.get(catalogs.size() - 1);
            lastCatalogTextView.setText(leastCatalog.getTitle());
        }
        mCatalogAdapter.notifyDataSetChanged();

    }

    //目录点击
    @Override
    public void call(Catalog catalog) {
        XLog.d(catalog.toString());
        ReadActivity.start(this, mBook, catalogs, catalog);
    }

    // 加入到书架，或者移除
    @OnClick(R.id.tv_modify_shelf)
    public void onModifyShelfAction() {
        String toastText = null;
        if (existInShelf) {
            presenter.removeBookShelf(mBook);
            toastText = getString(R.string.remove_from_shelf) + " " + getString(R.string.action_success);
        } else {
            presenter.addBookToShelf(mBook, catalogs);
            toastText = getString(R.string.add_to_shelf) + " " + getString(R.string.action_success);
        }
        SafeToast.makeText(toastText).show();
    }

    @OnClick(R.id.tv_start_read)
    public void onStartReadAction() {
        Log.d(TAG, "onStartReadAction: " + mBook.toString());
        ReadActivity.start(this, mBook, catalogs, null);
    }

    @OnClick(R.id.tv_download_all)
    public void onDownloadAllAction() {
        presenter.addBookToShelf(mBook, catalogs);
        // TODO
    }


    @Override
    protected void onResume() {
        super.onResume();
        mCatalogAdapter.notifyDataSetChanged();
    }
}
