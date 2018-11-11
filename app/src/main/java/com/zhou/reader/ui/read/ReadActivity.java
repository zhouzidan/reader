package com.zhou.reader.ui.read;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.elvishew.xlog.XLog;
import com.zhou.reader.CONST;
import com.zhou.reader.R;
import com.zhou.reader.util.JsonUtil;
import com.zhou.reader.widget.ReadAdapter;
import com.zhou.reader.base.BaseActivity;
import com.zhou.reader.db.Book;
import com.zhou.reader.db.Catalog;
import com.zhou.reader.ui.detail.CatalogAdapter;
import com.zhou.reader.ui.setting.ReadSettingManager;
import com.zhou.reader.ui.setting.ReadSettingsActivity;
import com.zhou.reader.ui.setting.ThemeManager;
import com.zhou.reader.util.StatusBarManager;

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

    @BindView(R.id.read_ll_bottom_menu)
    View bottomMenuView;

    @BindView(R.id.content_recyclerView)
    RecyclerView contentRecyclerView;

    @BindView(R.id.read_toolbar)
    Toolbar toolbar;

    @BindView(R.id.header_title_textView)
    TextView headerTitleTextView;

    CatalogAdapter catalogAdapter;
    ReadAdapter readAdapter;

    private ReadContact.Presenter presenter;

    public static void start(Context context, Book book, List<Catalog> catalogs, Catalog selectedCatalog) {
        Intent intent = new Intent(context, ReadActivity.class);
        Log.d(TAG, "onStartReadAction: " + book.toString());
        if (book != null && book.getId() > 0) {
            intent.putExtra(CONST.EXTRA_BOOK_ID, book.id);
        } else if (book != null) {
            String bookJson = JsonUtil.toJson(book, Book.class);
            String catalogListJson = JsonUtil.toJson(catalogs, Catalog.class);
            intent.putExtra(CONST.EXTRA_BOOK, bookJson);
            intent.putExtra(CONST.EXTRA_CATALOGS, catalogListJson);
        }
        if (selectedCatalog != null) {
            String jsonCatalog = JsonUtil.toJson(selectedCatalog, Catalog.class);
            intent.putExtra(CONST.EXTRA_CATALOG, jsonCatalog);
        }
        context.startActivity(intent);
    }

    // 打开目录
    @OnClick(R.id.read_tv_category)
    public void openCatalogPage() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @OnClick(R.id.read_tv_night_mode)
    public void onLightAction() {
        XLog.d("日间 - 夜间");
        boolean isNightMode = ReadSettingManager.getInstance().isNightMode();
        ReadSettingManager.getInstance().setNightMode(!isNightMode);
        readAdapter.notifyDataSetChanged();
        initTitleAndBottomColor();
    }

    @OnClick(R.id.read_tv_pre_chapter)
    public void onReadTvPreChapter() {
        XLog.d("上一章");
        presenter.loadLastContent();
    }

    @OnClick(R.id.read_tv_next_chapter)
    public void onReadTvNextChapter() {
        XLog.d("下一章");
        presenter.loadNextContent();
    }

    GestureDetector gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            XLog.d("点击-- onSingleTapConfirmed");
            int visibility = bottomMenuView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE;
            bottomMenuView.setVisibility(visibility);
            updateStatusBar(bottomMenuView.getVisibility() == View.VISIBLE);
            Catalog catalog = getCurrentCatalog(e);
            presenter.saveReadRecord(catalog);
            showCurrentCatalogTitle(catalog);
            return super.onSingleTapConfirmed(e);
        }

    });

    @Override
    protected int getLayoutId() {
        return R.layout.activity_read;
    }

    @Override
    protected void initData() {
        setSupportActionBar(toolbar);
        headerTitleTextView.setHeight(StatusBarManager.getStatusBarHeight());
        updateStatusBar(false);
        initCatalogPage();
        initReadRecyclerView();
        //禁止滑动展示DrawerLayout
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        //侧边打开后，返回键能够起作用
        drawerLayout.setFocusableInTouchMode(false);
        presenter = new ReadPresenter(this);
        presenter.loadData(getIntent());

    }

    // 初始化目录页面
    private void initCatalogPage() {
        mCatalogRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCatalogRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        catalogAdapter = new CatalogAdapter(this);
        mCatalogRecyclerView.setAdapter(catalogAdapter);
        catalogAdapter.setClickCallback(catalog -> {
            XLog.d(catalog.toString());
            presenter.loadContent(catalog);
            drawerLayout.closeDrawers();
        });
        int catalogPosition = 0;
        mCatalogRecyclerView.scrollToPosition(catalogPosition);
    }

    long lastItemTouchTime = 0;

    private void initReadRecyclerView() {
        contentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        readAdapter = new ReadAdapter(this);
        contentRecyclerView.setAdapter(readAdapter);
        contentRecyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                if (System.currentTimeMillis() - lastItemTouchTime > 100) {
                    Catalog catalog = getCurrentCatalog(e);
                    presenter.saveReadRecord(catalog);
                    showCurrentCatalogTitle(catalog);
                }
                lastItemTouchTime = System.currentTimeMillis();
                gestureDetector.onTouchEvent(e);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.read, menu);
        MenuItem settingMenu = menu.findItem(R.id.action_settings);
        View settingView = settingMenu.getActionView();
        settingView.setOnClickListener(v -> {
            Intent intent = new Intent(this, ReadSettingsActivity.class);
            startActivity(intent);
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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
    protected void onDestroy() {
        presenter.distachView();
        super.onDestroy();
    }


    @Override
    public void showBookContent(Catalog catalog) {
        int catalogPosition = catalogAdapter.getPosition(catalog);
        if (catalogPosition >= 0){
            catalogAdapter.notifyItemChanged(catalogPosition);
            mCatalogRecyclerView.scrollToPosition(catalogPosition);
        }
        int contentPosition = readAdapter.getPosition(catalog);
        if (contentPosition >= 0){
            contentRecyclerView.scrollToPosition(contentPosition);
            LinearLayoutManager mLayoutManager =
                    (LinearLayoutManager) contentRecyclerView.getLayoutManager();
            mLayoutManager.scrollToPositionWithOffset(contentPosition, 0);
        }
    }

    @Override
    public void loadBookAndCatalogs() {
        catalogAdapter.setCatalogs(presenter.getCatalogs());
        if (mCatalogRecyclerView != null && mCatalogRecyclerView.getAdapter() != null) {
            mCatalogRecyclerView.getAdapter().notifyDataSetChanged();
        }
        readAdapter.setCatalogs(presenter.getCatalogs());
        showBookContent(presenter.getCurrentCatalog());
    }

    @Override
    public void showCurrentCatalogTitle(Catalog catalog) {
        if (catalog != null) {
            toolbar.setTitle(catalog.title);
            headerTitleTextView.setText(catalog.title);
        }
    }

    private void updateStatusBar(boolean isShow) {
        if (isShow) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        headerTitleTextView.setVisibility(isShow ? View.GONE : View.VISIBLE);
        toolbar.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    private void initTitleAndBottomColor() { //TODO
        int backgroundColor = ThemeManager.getInstance().getHeadAndBottomBackgroundColor();

        toolbar.setBackgroundColor(backgroundColor);
        bottomMenuView.setBackgroundColor(backgroundColor);
        StatusBarManager.setStatusBar(getWindow(), backgroundColor);

        int contentBackgroundColor = ThemeManager.getInstance().getReadContentBackgroundColor();
        int contentTextColor = ThemeManager.getInstance().getReadContentTextColor();

        headerTitleTextView.setTextColor(contentTextColor);
        headerTitleTextView.setBackgroundColor(contentBackgroundColor);
    }

    private Catalog getCurrentCatalog(MotionEvent motionEvent) {
        View view = contentRecyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        int position = contentRecyclerView.getChildAdapterPosition(view);
        return readAdapter.getCatalogByPosition(position);
    }

    @Override
    protected void onStart() {
        super.onStart();
        readAdapter.notifyDataSetChanged();
        initTitleAndBottomColor();
    }
}
