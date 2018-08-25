package com.zhou.reader.search;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.zhou.reader.R;
import com.zhou.reader.base.BaseFragment;
import com.zhou.reader.entity.SearchResult;

import butterknife.BindView;

public class SearchFragment extends BaseFragment implements SearchContract.View{

    private SearchContract.Presenter presenter;

    @BindView(R.id.search)
    SearchView searchView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initView(View view) {
        searchView.setQueryHint("输入书名");
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(queryTextListener);
        searchView.setOnCloseListener(onCloseListener);
    }

    @Override
    protected void initData(View view) {
        presenter = new SearchPresenter(this);
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
            presenter.search(null);
            return false;
        }
    };

    @Override
    public void showData(SearchResult searchResult) {

    }
}
