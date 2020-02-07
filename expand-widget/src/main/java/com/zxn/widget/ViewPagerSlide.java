package com.zxn.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * 可以动态控制是否能进行滑动.
 * Created by zxn on 2020/2/7.
 */
public class ViewPagerSlide extends ViewPager {

    //是否可以进行滑动
    private boolean isSlide = false;

    public ViewPagerSlide(@NonNull Context context) {
        super(context);
    }

    public ViewPagerSlide(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isSlide;
    }

    public void setSlide(boolean isSlide) {
        this.isSlide = isSlide;
    }
}
