package com.zhou.reader.util;

import android.support.v7.widget.RecyclerView;

public class RecyclerViewUtil {
    /**
     * 是否滑动的底部
     * @param recyclerView
     * @return
     */
    public static boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }
}
