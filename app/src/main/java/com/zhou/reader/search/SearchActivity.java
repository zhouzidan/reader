package com.zhou.reader.search;


import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.zhou.reader.R;
import com.zhou.reader.base.BaseActivity;
import com.zhou.reader.entity.Book;
import com.zhou.reader.entity.Search;
import com.zhou.reader.entity.SearchResult;
import com.zhou.reader.widget.BookListAdapter;
import com.zhou.reader.widget.FlowLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchActivity extends BaseActivity implements SearchContract.View{

    private SearchContract.Presenter presenter;
    private SearchHistoryAdapter historyAdapter ;
    private BookListAdapter bookListAdapter;
    private List<Book> books = new ArrayList<>();

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private SearchView searchView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initData() {
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        presenter = new SearchPresenter(this);
        bookListAdapter = new BookListAdapter(this,books);
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
        searchView.setOnCloseListener(onCloseListener);
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
            return false;
        }
    };

    private SearchView.OnCloseListener onCloseListener = new SearchView.OnCloseListener() {
        @Override
        public boolean onClose() {
            presenter.showHistory();
            return false;
        }
    };

    @Override
    public void showData(SearchResult searchResult) {
        System.out.println(searchResult.getTotal() + "---" + searchResult.getBooks().size());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (recyclerView.getAdapter() != bookListAdapter){
            recyclerView.setAdapter(bookListAdapter);
        }
        books.addAll(searchResult.getBooks());
        if (books.size() == searchResult.getBooks().size()){
            bookListAdapter.notifyDataSetChanged();
        }else {
            int positionStart = books.size() - searchResult.getBooks().size();
            int itemCount = searchResult.getBooks().size();
            bookListAdapter.notifyItemRangeInserted(positionStart,itemCount);
        }
    }

    @Override
    public void showData(List<Search> searches) {
        recyclerView.setLayoutManager(new FlowLayoutManager());
        historyAdapter = new SearchHistoryAdapter(searches);
        recyclerView.setAdapter(historyAdapter);
        historyAdapter.notifyDataSetChanged();
    }


}
