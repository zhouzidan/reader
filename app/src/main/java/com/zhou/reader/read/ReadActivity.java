package com.zhou.reader.read;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

import com.elvishew.xlog.XLog;
import com.zhou.reader.R;
import com.zhou.reader.base.BaseActivity;
import com.zhou.reader.detail.CatalogAdapter;
import com.zhou.reader.entity.Catalog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ReadActivity extends BaseActivity {

    @BindView(R.id.rv_category)
    RecyclerView mCatalogRecyclerView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.read_sb_chapter_progress)
    SeekBar mSeekBar; // 当前章的阅读进度

    @BindView(R.id.read_ll_bottom_menu)
    View bottomMenuView;

    // 小说目录
    private List<Catalog> catalogList = new ArrayList<>();


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
        initCatalogPage();
        mSeekBar.setOnSeekBarChangeListener(mChapterSeenBarChange);
    }

    private void initCatalogPage(){
        mCatalogRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCatalogRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        CatalogAdapter catalogAdapter = new CatalogAdapter(this,catalogList);
        mCatalogRecyclerView.setAdapter(catalogAdapter);
        catalogAdapter.setClickCallback(catalog -> {
            XLog.d(catalog.toString());
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
}
