package com.zhou.reader.ui.search;


import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.elvishew.xlog.XLog;
import com.zhou.reader.CONST;
import com.zhou.reader.R;
import com.zhou.reader.base.BaseActivity;
import com.zhou.reader.db.Book;
import com.zhou.reader.db.ShelfDBManager;
import com.zhou.reader.entity.SearchResult;
import com.zhou.reader.ui.detail.BookDetailActivity;
import com.zhou.reader.util.JsonUtil;
import com.zhou.reader.util.RecyclerViewUtil;
import com.zhou.reader.util.StringUtils;
import com.zhou.reader.widget.BookListAdapter;
import com.zhou.reader.widget.CallBack;
import com.zhou.reader.widget.RecyclerViewOnScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

public class SearchActivity extends BaseActivity implements SearchContract.View {

    private final String TAG = SearchActivity.class.getCanonicalName();
    private SearchContract.Presenter presenter;
    private BookListAdapter bookListAdapter;
    private RecyclerViewOnScrollListener mLoadMoreScrollListener;
    private List<Book> books = new ArrayList<>();

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private SearchView searchView;

    @BindView(R.id.tagLayout)
    TagContainerLayout tagLayout;

    @BindView(R.id.tag_layout)
    LinearLayout tagLinearLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initData() {
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mLoadMoreScrollListener = new RecyclerViewOnScrollListener();
        mLoadMoreScrollListener.setCallBack(loadMoreCallBack);
        recyclerView.addOnScrollListener(mLoadMoreScrollListener);
        presenter = new SearchPresenter(this);
        bookListAdapter = new BookListAdapter(this, books);
        bookListAdapter.setClickCallback(clickCallback);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(bookListAdapter);
        tagLayout.setOnTagClickListener(onTagClickListener);
        presenter.showHistory();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        //通过MenuItem得到SearchView
        searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("输入书名");
        searchView.onActionViewExpanded();
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onCreateOptionsMenu(menu);
    }


    /**
     * SearchView 控件内容变化的监听
     */
    private SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            presenter.search(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            if (TextUtils.isEmpty(newText)) {
                presenter.showHistory();
            }
            return false;
        }
    };

    @Override
    public void showData(SearchResult searchResult) {
        System.out.println(searchResult.getTotal() + "---" + searchResult.getBooks().size());
        recyclerView.setVisibility(View.VISIBLE);
        tagLinearLayout.setVisibility(View.GONE);
        if (StringUtils.isEqual(searchResult.getKeyword(), bookListAdapter.getKeyword())) {
            books.addAll(searchResult.getBooks());
        } else {
            books.clear();
            books.addAll(searchResult.getBooks());
        }
        bookListAdapter.setKeyword(searchResult.getKeyword());
        if (books.size() == searchResult.getBooks().size()) {
            bookListAdapter.notifyDataSetChanged();
        } else {
            int positionStart = books.size() - searchResult.getBooks().size();
            int itemCount = searchResult.getBooks().size();
            int visiablePosition = RecyclerViewUtil.findFirstVisibleItemPosition(recyclerView);
            visiablePosition = visiablePosition > 0 ? visiablePosition : positionStart;
            bookListAdapter.notifyItemRangeInserted(positionStart, itemCount);
            recyclerView.scrollToPosition(visiablePosition);
        }
    }

    @Override
    public void clearSearchResult() {
        books.clear();
        if (bookListAdapter.getItemCount() > 0) {
            bookListAdapter.notifyItemRemoved(0);
        }
    }


    @Override
    public void showHistory(List<String> searches) {
        XLog.e("搜索历史：" + searches.size()); // TODO
        recyclerView.setVisibility(View.GONE);
        tagLinearLayout.setVisibility(View.VISIBLE);
        tagLayout.setTags(searches);
    }

    private BookListAdapter.ClickCallback clickCallback = book -> {
        Intent intent = new Intent(SearchActivity.this, BookDetailActivity.class);
        Book tempBook = ShelfDBManager.get().find(book.title);
        if (tempBook != null) {
            book.setId(tempBook.getId());
        }
        String bookJson = JsonUtil.toJson(book, Book.class);
        intent.putExtra(CONST.EXTRA_BOOK, bookJson);
        startActivity(intent);
    };

    private TagView.OnTagClickListener onTagClickListener = new TagView.OnTagClickListener() {
        @Override
        public void onTagClick(int position, String text) {
            searchView.setQuery(text, true);
        }

        @Override
        public void onTagLongClick(int position, String text) {

        }

        @Override
        public void onTagCrossClick(int position) {

        }
    };

    // 滑动监听中的加载更多
    private CallBack loadMoreCallBack = new CallBack() {
        @Override
        public void call(Object o) {
            presenter.loadMore();
        }
    };

}
