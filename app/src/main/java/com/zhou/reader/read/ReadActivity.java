package com.zhou.reader.read;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.elvishew.xlog.XLog;
import com.zhou.reader.CONST;
import com.zhou.reader.R;
import com.zhou.reader.base.BaseActivity;
import com.zhou.reader.db.Book;
import com.zhou.reader.db.Catalog;
import com.zhou.reader.detail.CatalogAdapter;
import com.zhou.reader.db.BookContent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ReadActivity extends BaseActivity implements ReadContact.View {

    private static final String TAG = "ReadActivity";

    @BindView(R.id.rv_category)
    RecyclerView mCatalogRecyclerView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.read_sb_chapter_progress)
    SeekBar mSeekBar; // 当前章的阅读进度

    @BindView(R.id.read_ll_bottom_menu)
    View bottomMenuView;

    @BindView(R.id.content_read)
    TextView contentTextView;

    private Book mBook;

    private List<Catalog> catalogs = new ArrayList<>();

    private ReadContact.Presenter presenter;

    // 打开目录
    @OnClick(R.id.read_tv_category)
    public void openCatalogPage(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @OnClick(R.id.read_tv_download)
    public void onDownloadAction(){
        XLog.d("download");
    }

    @OnClick(R.id.read_tv_night_mode)
    public void onLightAction(){
        XLog.d("日间 - 夜间");
    }

    @OnClick(R.id.read_tv_setting)
    public void onSettingAction(){
        XLog.d("Setting");
    }

    @OnClick(R.id.read_tv_pre_chapter)
    public void onReadTvPreChapter(){
        XLog.d("上一章");
    }

    @OnClick(R.id.read_tv_next_chapter)
    public void onReadTvNextChapter(){
        XLog.d("下一章");
    }

    @OnClick(R.id.content_read)
    public void onClickContentRead(){
        int visibility = bottomMenuView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE;
        bottomMenuView.setVisibility(visibility);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_read;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null){
            long localBookId = intent.getLongExtra(CONST.EXTRA_BOOK_ID,0);
        }
        initCatalogPage();
        mSeekBar.setOnSeekBarChangeListener(mChapterSeenBarChange);
        //禁止滑动展示DrawerLayout
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        //侧边打开后，返回键能够起作用
        drawerLayout.setFocusableInTouchMode(false);
        presenter = new ReadPresenter(this);
        presenter.loadCurrentContent();
    }

    // 初始化目录页面
    private void initCatalogPage(){
        mCatalogRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCatalogRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        CatalogAdapter catalogAdapter = new CatalogAdapter(this,catalogs);
        mCatalogRecyclerView.setAdapter(catalogAdapter);
        catalogAdapter.setClickCallback(catalog -> {
            XLog.d(catalog.toString());
            presenter.loadContent(catalog);
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.read, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // 当前进度的变化事件
    public SeekBar.OnSeekBarChangeListener mChapterSeenBarChange = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            XLog.d("修改阅读进度");
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


    @Override
    public void showBookContent(BookContent content) {
        contentTextView.setText(content.content);
    }

    @Override
    public void loadBookAndCatalogs(Book book, List<Catalog> catalogs) {
        this.mBook = book;
        this.catalogs.clear();
        this.catalogs.addAll(catalogs);
    }

}
