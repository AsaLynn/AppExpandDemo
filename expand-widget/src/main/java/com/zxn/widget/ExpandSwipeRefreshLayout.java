package com.zxn.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * Created by zxn on 2020/3/19.
 */
public class ExpandSwipeRefreshLayout extends SwipeRefreshLayout {

    private boolean mRefreshEnabled = false;

    public ExpandSwipeRefreshLayout(@NonNull Context context) {
        super(context);
    }

    public ExpandSwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mRefreshEnabled && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    public void setRefreshEnabled(boolean enabled) {
        mRefreshEnabled = enabled;
    }

    public boolean isRefreshEnabled() {
        return mRefreshEnabled;
    }
}
