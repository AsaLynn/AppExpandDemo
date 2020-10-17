package com.zxn.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**可以动态控制是否能进行滑动.
 *  Updated by zxn on 2020.10.17.
 */
class ViewPagerSlide : ViewPager {
    //是否可以进行滑动
    private var isSlide = false

    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return isSlide
    }

    fun setSlide(isSlide: Boolean) {
        this.isSlide = isSlide
    }
}