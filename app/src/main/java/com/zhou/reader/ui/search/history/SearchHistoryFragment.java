package com.zhou.reader.ui.search.history;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.zhou.reader.R;
import com.zhou.reader.base.BaseFragment;
import com.zhou.reader.db.Search;
import com.zhou.reader.db.SearchDBManager;
import com.zhou.reader.widget.CallBack;
import com.zhou.reader.widget.SearchHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchHistoryFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    SearchHistoryAdapter adapter;

    List<Search> searches = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recyclerview;
    }

    @Override
    protected void initView(View view) {
        setHasOptionsMenu(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        searches = SearchDBManager.get().getAll();
        adapter = new SearchHistoryAdapter(searches);
        recyclerView.setAdapter(adapter);
        adapter.setCallBack(longItemClick);
    }

    @Override
    protected void initData(View view) {
        searches.clear();
        searches.addAll(SearchDBManager.get().getAll());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.clear, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_clear) {
            clearHistoryConfirmDialog(null);
        }
        return super.onOptionsItemSelected(item);
    }

    private void clearHistoryConfirmDialog(String string) {
        String message = TextUtils.isEmpty(string) ? "是否清空所有的搜索记录？" : "是否删除所选记录";
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setMessage(message)
                .setPositiveButton(R.string.btn_ok, (dialog, which) -> {
                    if (TextUtils.isEmpty(string)) {
                        clear();
                    } else {
                        delete(string);
                    }
                })
                .setNegativeButton(R.string.btn_cancel, null)
                .create();
        alertDialog.show();
    }

    private CallBack<String> longItemClick = s -> clearHistoryConfirmDialog(s);

    private void delete(String str) {
        SearchDBManager.get().delete(str);
        initData(null);
    }

    private void clear() {
        SearchDBManager.get().clear();
        initData(null);
    }

}
