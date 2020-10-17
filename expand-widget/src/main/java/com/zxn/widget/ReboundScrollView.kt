package com.zxn.widget

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.ScrollView

/**
 *  Updated by zxn on 2020.10.17.
 */
class ReboundScrollView : ScrollView {
    interface MyScrollListener {
        fun onMyScrollEvent(action: Int, y: Float)
    }

    private var listener: MyScrollListener? = null
    private var childView: View? = null
    private var canPullUp = false
    private var canPullDown = false
    private var havaMoved = false
    private var changeY = 0
    private val originalRect = Rect()
    private var startY = 0f
    override fun onFinishInflate() {
        super.onFinishInflate()
        Log.d(TAG, "onFinishInflate")
    }

    override fun fling(velocityY: Int) {
        super.fling(velocityY / 2)
    }

    override fun onOverScrolled(scrollX: Int, scrollY: Int, clampedX: Boolean,
                                clampedY: Boolean) {
        if (listener != null) {
            listener!!.onMyScrollEvent(-1, scrollY.toFloat())
        }
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        Log.d(TAG, "onlayout")
        if (childCount > 0) childView = getChildAt(0)
        if (childView == null) return
        originalRect[childView!!.left, childView!!.top, childView!!.right] = childView!!.bottom
    }

    constructor(context: Context?, attrs: AttributeSet?,
                defStyle: Int) : super(context, attrs, defStyle) {
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?) : super(context) {}

    /**
     * 在触摸事件中, 处理上拉和下拉的逻辑
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (childView == null) {
            return super.dispatchTouchEvent(ev)
        }
        when (val action = ev.action) {
            MotionEvent.ACTION_DOWN -> {
                canPullDown = isCanPullDown()
                canPullUp = isCanPullUp()
                startY = ev.y
                return super.dispatchTouchEvent(ev)
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                if (havaMoved) {
                    val anim = TranslateAnimation(0F, 0F,
                            childView!!.top.toFloat(), originalRect.top.toFloat())
                    anim.duration = ANIM_TIME.toLong()
                    childView!!.startAnimation(anim)
                    // 将标志位设回false
                    canPullDown = false
                    canPullUp = false
                    havaMoved = false
                    resetViewLayout()
                    if (listener != null) {
                        listener!!.onMyScrollEvent(action, changeY.toFloat())
                    }
                }
                return super.dispatchTouchEvent(ev)
            }
            MotionEvent.ACTION_MOVE -> {
                if (!canPullDown && !canPullUp) {
                    startY = ev.y
                    canPullDown = isCanPullDown()
                    canPullUp = isCanPullUp()
                    return super.dispatchTouchEvent(ev)
                }
                val nowY = ev.y
                val deltaY = (nowY - startY).toInt()
                changeY = deltaY
                val shouldMove = (canPullDown && deltaY > 0
                        || canPullUp && deltaY < 0 || canPullUp && canPullDown)
                if (shouldMove) {
                    val offset = (deltaY * MOVE_DELAY).toInt()
                    childView!!.layout(originalRect.left, originalRect.top + offset,
                            originalRect.right, originalRect.bottom + offset)
                    havaMoved = true
                }
                return super.dispatchTouchEvent(ev)
            }
            else -> {
                return super.dispatchTouchEvent(ev)
            }
        }

    }

    fun resetViewLayout() {
        childView!!.layout(originalRect.left, originalRect.top,
                originalRect.right, originalRect.bottom)
    }

    fun setListener(listener: MyScrollListener?) {
        this.listener = listener
    }

    /**
     * 判断是否滚动到顶部
     */
    private fun isCanPullDown(): Boolean {
        return (scrollY == 0
                || childView!!.height < height + scrollY)
    }

    /**
     * 判断是否滚动到底部
     */
    private fun isCanPullUp(): Boolean {
        return childView!!.height <= height + scrollY
    }

    companion object {
        private const val TAG = "ReboundScrollView"
        private const val MOVE_DELAY = 0.3f
        private const val ANIM_TIME = 300
    }
}