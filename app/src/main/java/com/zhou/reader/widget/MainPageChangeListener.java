package com.zhou.reader.widget;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

/**
 * 主页面上BottomNavigationView和ViewPager切换的联动
 */
public class MainPageChangeListener extends ViewPager.SimpleOnPageChangeListener {
    BottomNavigationView navigation;

    public MainPageChangeListener(BottomNavigationView navigation) {
        this.navigation = navigation;
    }

    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
        int lastSelectId = navigation.getSelectedItemId();
        MenuItem lastMenuItem = navigation.getMenu().findItem(lastSelectId);
        lastMenuItem.setChecked(false);

        MenuItem currentMenuItem = navigation.getMenu().getItem(position);
        currentMenuItem.setChecked(true);

    }
}
