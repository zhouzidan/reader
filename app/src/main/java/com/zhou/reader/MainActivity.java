package com.zhou.reader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;

import com.zhou.reader.base.BaseActivity;
import com.zhou.reader.shelf.ShelfFragment;
import com.zhou.reader.shelf.ShelfModle;
import com.zhou.reader.widget.MainFragmentAdapter;
import com.zhou.reader.widget.MainNavigationItemSelectedListener;
import com.zhou.reader.widget.MainPageChangeListener;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.viewpager) ViewPager viewPager;

    @BindView(R.id.navigation) BottomNavigationView navigation ;

    private Fragment[] fragments = {
            new ShelfFragment(),
            new ShopFragment(),
            new MineFragment()
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        navigation.setOnNavigationItemSelectedListener(new MainNavigationItemSelectedListener(this,viewPager));
        viewPager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager(),fragments));
        viewPager.addOnPageChangeListener(new MainPageChangeListener(navigation));
        navigation.setSelectedItemId(R.id.navigation_shelf);
    }


}
