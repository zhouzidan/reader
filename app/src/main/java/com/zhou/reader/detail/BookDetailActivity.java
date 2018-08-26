package com.zhou.reader.detail;

import android.widget.ImageView;
import android.widget.TextView;

import com.zhou.reader.R;
import com.zhou.reader.base.BaseActivity;

import butterknife.BindView;

public class BookDetailActivity extends BaseActivity {

    @BindView(R.id.img)
    ImageView imageView;

    @BindView(R.id.title)
    TextView titleTextView;

    @BindView(R.id.author)
    TextView authorTextView;

    @BindView(R.id.category)
    TextView categoryTextView;

    @BindView(R.id.totalCount)
    TextView totalCountTextView;

    @BindView(R.id.followerCount)
    TextView followerCountTextView;

    @BindView(R.id.retentionRatio)
    TextView retentionRatioTextView;

    @BindView(R.id.dayUpdateCount)
    TextView dayUpdateCountTextView;




    @Override
    protected int getLayoutId() {
        return R.layout.activity_book_detail;
    }

    @Override
    protected void initData() {

    }
}
