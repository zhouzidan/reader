package com.zhou.reader.shelf;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.elvishew.xlog.XLog;
import com.zhou.reader.CONST;
import com.zhou.reader.R;
import com.zhou.reader.base.BaseFragment;
import com.zhou.reader.db.LBook;
import com.zhou.reader.detail.BookDetailActivity;
import com.zhou.reader.entity.Book;
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
    List<LBook> mLBooks = new ArrayList<>();
    ShelfBookListAdapter bookListAdapter ;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shelf;
    }

    @Override
    protected void initView(View view) {
        view.setBackgroundColor(Color.WHITE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        bookListAdapter = new ShelfBookListAdapter(getContext(),mLBooks);
        bookListAdapter.registerAdapterDataObserver(adapterDataObserver);
        bookListAdapter.setClickCallback(clickCallback);
        recyclerView.setAdapter(bookListAdapter);
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
    public void showBooks(List<LBook> books) {
        mLBooks.clear();
        mLBooks.addAll(books);
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
        public void onClick(LBook lBook) {
            // 点击
            Intent intent = new Intent(getContext(), BookDetailActivity.class);
            Book book = lBook.toBook();
            intent.putExtra(CONST.EXTRA_DATA,book);
            startActivity(intent);
        }

        @Override
        public void onLongClick(LBook lBook) {
            // 长按
        }
    };
}
