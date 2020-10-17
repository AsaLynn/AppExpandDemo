package com.zxn.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView

/**可以控制是否可以滚动的ScrollView,默认不可以滚动
 *  Updated by zxn on 2020.10.17.
 */
class ExpandScrollView : ScrollView {
    /**
     * 控制是否可以进行滑动
     */
    var isScrollEnabled = false

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return isScrollEnabled && super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return isScrollEnabled && super.onInterceptTouchEvent(ev)
    }
}