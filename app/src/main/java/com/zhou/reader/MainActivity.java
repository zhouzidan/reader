package com.zhou.reader;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

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
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        navigation.setOnNavigationItemSelectedListener(new MainNavigationItemSelectedListener(viewPager));
        viewPager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager(),fragments));
        viewPager.addOnPageChangeListener(new MainPageChangeListener(navigation));
    }


}
