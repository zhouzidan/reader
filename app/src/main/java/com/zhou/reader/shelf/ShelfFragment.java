package com.zhou.reader.shelf;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.elvishew.xlog.XLog;
import com.zhou.reader.CONST;
import com.zhou.reader.R;
import com.zhou.reader.base.BaseFragment;
import com.zhou.reader.db.Book;
import com.zhou.reader.detail.BookDetailActivity;
import com.zhou.reader.search.SearchActivity;
import com.zhou.reader.widget.ShelfBookListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShelfFragment extends BaseFragment implements ShelfContact.View{

    private final int REQUEST_CODE_ADD_MORE_BOOK = 111;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.btn_add_more)
    Button addMoreBookBtn;

    ShelfPresenter presenter;
    List<Book> mBooks = new ArrayList<>();
    ShelfBookListAdapter bookListAdapter ;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shelf;
    }

    @Override
    protected void initView(View view) {
        initToolBar();
        view.setBackgroundColor(Color.WHITE);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        bookListAdapter = new ShelfBookListAdapter(getContext(), mBooks);
        bookListAdapter.registerAdapterDataObserver(adapterDataObserver);
        bookListAdapter.setClickCallback(clickCallback);
        recyclerView.setAdapter(bookListAdapter);
    }

    private void initToolBar() {
        setHasOptionsMenu(true);
    }

    @Override
    protected void initData(View view) {
        presenter = new ShelfPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadShelfBooks();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.shelf,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add){
            onAddMoreBook();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showBooks(List<Book> books) {
        mBooks.clear();
        mBooks.addAll(books);
        bookListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        bookListAdapter.unregisterAdapterDataObserver(adapterDataObserver);
    }

    private void changeEmptyViewVisiblity(){
        XLog.d("bookListAdapter.getItemCount:"+bookListAdapter.getItemCount());
        int visiblity = bookListAdapter.getItemCount() > 0 ? View.GONE : View.VISIBLE;
        addMoreBookBtn.setVisibility(visiblity);
    }

    // 书架上的数据变化监听，为空的时候，显示EmptyView
    private RecyclerView.AdapterDataObserver adapterDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            changeEmptyViewVisiblity();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            changeEmptyViewVisiblity();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            changeEmptyViewVisiblity();
        }
    };

    @OnClick(R.id.btn_add_more)
    public void onAddMoreBook(){
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        startActivityForResult(intent,REQUEST_CODE_ADD_MORE_BOOK);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_MORE_BOOK){
            presenter.loadShelfBooks();
        }
    }

    private ShelfBookListAdapter.ClickCallback clickCallback = new ShelfBookListAdapter.ClickCallback() {
        @Override
        public void onClick(Book book) {
            // 点击
            Intent intent = new Intent(getContext(), BookDetailActivity.class);
            intent.putExtra(CONST.EXTRA_BOOK_ID,book.id);
            startActivity(intent);
        }

        @Override
        public void onLongClick(Book book) {
            // 长按
        }
    };
}
