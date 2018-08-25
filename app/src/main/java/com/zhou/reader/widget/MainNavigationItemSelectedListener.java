package com.zhou.reader.widget;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.zhou.reader.R;

/**
 * 主页面上BottomNavigationView和ViewPager切换的联动
 */
public class MainNavigationItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
    private ViewPager viewPager;

    public MainNavigationItemSelectedListener(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_shelf:
                viewPager.setCurrentItem(0);
                return true;
            case R.id.navigation_shop:
                viewPager.setCurrentItem(1);
                return true;
            case R.id.navigation_mine:
                viewPager.setCurrentItem(2);
                return true;
        }
        return false;
    }
}
