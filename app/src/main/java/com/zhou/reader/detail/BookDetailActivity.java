package com.zhou.reader.detail;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhou.reader.CONST;
import com.zhou.reader.GlideApp;
import com.zhou.reader.R;
import com.zhou.reader.base.BaseActivity;
import com.zhou.reader.entity.Book;
import com.zhou.reader.entity.Catalog;
import com.zhou.reader.entity.selector.CatalogSelector;
import com.zhou.reader.entity.selector.Selector;
import com.zhou.reader.search.BookCatalogCallback;
import com.zhou.reader.search.BookSearchUtil;
import com.zhou.reader.util.AppExecutor;
import com.zhou.reader.util.SelectorManager;

import java.util.Map;

import butterknife.BindView;

public class BookDetailActivity extends BaseActivity {

    @BindView(R.id.img)
    ImageView imageView;

    @BindView(R.id.title)
    TextView titleTextView;

    @BindView(R.id.tag)
    TextView tagTextView;

    @BindView(R.id.desc)
    TextView descTextView;

    @BindView(R.id.recyclerView_catalog)
    RecyclerView recyclerView;

    private Book mBook;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_book_detail;
    }

    @Override
    protected void initData() {
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

        showCatalog();
    }


    private void showCatalog(){
        final String link = mBook.getLink();
        AppExecutor.get().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                BookSearchUtil.getCatalog(link);
            }
        });

        BookSearchUtil.getCatalog(mBook.getLink(), new BookCatalogCallback() {
            @Override
            public void onSuccess(Catalog catalog) {

            }

            @Override
            public void onFail(Exception e) {

            }

            @Override
            public void onFinish() {

            }
        });
    }
}
