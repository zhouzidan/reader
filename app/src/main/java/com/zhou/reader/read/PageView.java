package com.zhou.reader.read;

import android.content.Context;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zhou.reader.read.animation.PageAnimation;

public class PageView extends View {

    private PageMode mPageMode = PageMode.SIMULATION;
    // 动画类
    private PageAnimation mPageAnim;

    // 唤醒菜单的区域
    private RectF mCenterRect = null;

    public PageView(Context context) {
        this(context,null);
    }

    public PageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }


}
