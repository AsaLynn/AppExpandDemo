package com.zxn.widget

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import androidx.appcompat.R
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.TintTypedArray
import androidx.appcompat.widget.Toolbar

/**
 * Copyright(c) ${}YEAR} ZhuLi co.,Ltd All Rights Reserved.
 *
 * @className: ExpandToolbar$
 * @description: 标题居中,并且没有副标题的Toolbar,
 * @version: v0.0.1
 * @author: zxn < a href=" ">zhangxiaoning@17biyi.com</ a>
 * @date: 2020/10/17$ 15:39$
 * @updateUser: 更新者：
 * @updateDate: 2020/10/17$ 15:39$
 * @updateRemark: 更新说明：
 * @version: 1.0
 * */
@SuppressLint("RestrictedApi")
class ExpandToolbar : Toolbar {

    /**
     * 次构造函数
     */
    constructor(context: Context) : this(context, null)

    /**
     * 次构造函数,布局中初始化,无样式.
     */
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        onInitAttributeSet(attrs)
    }

    /**
     * //主构造函数,布局中初始化并且带有样式.
     */
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        onInitAttributeSet(attrs,defStyleAttr)
    }

    private fun onInitAttributeSet(attrs: AttributeSet?,defStyleAttr: Int = 0) {
        val a = TintTypedArray.obtainStyledAttributes(getContext(), attrs, R.styleable.Toolbar, defStyleAttr, 0)
        mTitleTextAppearance = a.getResourceId(R.styleable.Toolbar_titleTextAppearance, 0)
        if (a.hasValue(R.styleable.Toolbar_titleTextColor)) {
            setTitleTextColor(a.getColorStateList(R.styleable.Toolbar_titleTextColor))
        }
        a.recycle()
    }


    private var mTitleTextView: AppCompatTextView? = null
    private var mTitleTextAppearance = 0
    private var mTitleTextColor: ColorStateList? = null
    private var mTitleText: CharSequence? = null

    override fun setTitle(title: CharSequence?) {
        if (!TextUtils.isEmpty(title)) {
            if (mTitleTextView == null) {
                val context = context
                mTitleTextView = AppCompatTextView(context)
                mTitleTextView!!.setSingleLine()
                mTitleTextView!!.ellipsize = TextUtils.TruncateAt.END
                if (mTitleTextAppearance != 0) {
                    mTitleTextView!!.setTextAppearance(context, mTitleTextAppearance)
                }
                if (mTitleTextColor != null) {
                    mTitleTextView!!.setTextColor(mTitleTextColor)
                }
                addSystemView(mTitleTextView as View)
            }
            if (mTitleTextView != null) {
                mTitleTextView!!.text = title
            }
            mTitleText = title
        }
    }

    override fun setTitleTextColor(color: ColorStateList) {
        mTitleTextColor = color
        if (mTitleTextView != null) {
            mTitleTextView!!.setTextColor(color)
        }
    }

    private fun addSystemView(v: View) {
        val vlp = v.layoutParams
        val lp: LayoutParams
        lp = if (vlp == null) {
            generateDefaultLayoutParams()
        } else if (!checkLayoutParams(vlp)) {
            generateLayoutParams(vlp)
        } else {
            vlp as LayoutParams
        }
        lp.gravity = Gravity.CENTER
        addView(v, lp)
    }

    @Deprecated("该方法已废弃.")
    override fun setSubtitle(subtitle: CharSequence?) {
    }

    override fun setNavigationIcon(icon: Drawable?) {
        super.setNavigationIcon(icon)
    }
}


