package com.zxn.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 可以控制是否可以滚动的ScrollView,默认不可以滚动
 * Created by zxn on 2020/3/13.
 */
public class ExpandScrollView extends ScrollView {

    /**
     * 控制是否可以进行滑动
     */
    private boolean mScrollEnabled;

    public ExpandScrollView(Context context) {
        super(context);
    }

    public ExpandScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return mScrollEnabled && super.onTouchEvent(ev);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mScrollEnabled && super.onInterceptTouchEvent(ev);
    }

    public boolean isScrollEnabled() {
        return mScrollEnabled;
    }

    public void setScrollEnabled(boolean mScrollEnabled) {
        this.mScrollEnabled = mScrollEnabled;
    }
}
