package com.zhou.reader.widget;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.zhou.reader.R;

/**
 * 主页面上BottomNavigationView和ViewPager切换的联动
 */
public class MainNavigationItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
    private ViewPager viewPager;
    private FragmentActivity activity;

    public MainNavigationItemSelectedListener(FragmentActivity activity, ViewPager viewPager) {
        this.viewPager = viewPager;
        this.activity = activity;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_shelf:
                viewPager.setCurrentItem(0);
                activity.setTitle(R.string.title_shelf);
                return true;
//            case R.id.navigation_shop:
//                viewPager.setCurrentItem(1);
//                activity.setTitle(R.string.title_shop);
//                return true;
            case R.id.navigation_mine:
                viewPager.setCurrentItem(2);
                activity.setTitle(R.string.title_mine);
                return true;
        }
        return false;
    }

}
