package com.zxn.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.CycleInterpolator
import android.view.animation.TranslateAnimation
import androidx.appcompat.widget.AppCompatEditText

/**
 * Updated by zxn on 2020.10.14.
 */
@SuppressLint("UseCompatLoadingForDrawables")
class ExpandEditText @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyle: Int = android.R.attr.editTextStyle)
    : AppCompatEditText(context!!, attrs, defStyle),
        View.OnFocusChangeListener,
        TextWatcher {
    /**
     * 删除按钮的引用
     */
    private var mClearDrawable: Drawable? = null

    /**
     * 左侧图标
     */
    private var mIconDrawable: Drawable? = null

    init {
        // 获取ClearEditText的DrawableRight,假如没有设置我们就使用默认的图片
        mClearDrawable = compoundDrawables[2]
        if (mClearDrawable == null) {
            mClearDrawable = resources.getDrawable(R.drawable.icon_clear_normal)
        }
        mClearDrawable!!.setBounds(0, 0, mClearDrawable!!.intrinsicWidth, mClearDrawable!!.intrinsicHeight)
        //可以使用(xml中使用drawableLeft可以配置其他的图标.)
        mIconDrawable = compoundDrawables[0]
        if (mIconDrawable == null) {
            mIconDrawable = resources.getDrawable(R.drawable.icon_left)
        }
        mIconDrawable!!.setBounds(0, 0, mIconDrawable!!.intrinsicWidth, mIconDrawable!!.intrinsicHeight)

        compoundDrawablePadding = context?.let { dp2px(it, 10f) }!!

        // 默认设置隐藏图标
        //setClearIconVisible(false)
        // 设置焦点改变的监听
        onFocusChangeListener = this
        // 设置输入框里面内容发生改变的监听
        addTextChangedListener(this)

        manageClearButton()
    }

    /**
     * 传入显示的图标资源id
     *
     * @param id
     */
    fun setIconResource(id: Int) {
        mIconDrawable = resources.getDrawable(id)
        mIconDrawable.let {
            mIconDrawable!!.setBounds(0, 0, mIconDrawable!!.intrinsicWidth, mIconDrawable!!.intrinsicHeight)
            manageClearButton()
        }
    }

    fun manageClearButton() {
        if (this.text.toString() == "") removeClearButton() else addClearButton()
    }

    fun removeClearButton() {
        setCompoundDrawables(mIconDrawable, this.compoundDrawables[1], null, this.compoundDrawables[3])
    }

    fun addClearButton() {
        setCompoundDrawables(mIconDrawable, this.compoundDrawables[1], mClearDrawable, this.compoundDrawables[3])
    }

    /**
     * 控件是否有焦点
     */
    var isHasFoucs = false

    //		 * 因为我们不能直接给ClearEditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件 当我们按下的位置 在
    //	 * ClearEditText的宽度 - 图标到控件右边的间距 - 图标的宽度 和 ClearEditText的宽度 -
    //			* 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            if (compoundDrawables[2] != null) {
                val touchable = event.x > width - totalPaddingRight && event.x < width - paddingRight
                if (touchable) {
                    if (mListener == null) this.setText("") else mListener!!.onClick()
                }
            }
        }
        return super.onTouchEvent(event)
    }

    /**
     * 当ClearClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
     */
    override fun onFocusChange(v: View, hasFocus: Boolean) {
        isHasFoucs = hasFocus
        if (hasFocus) {
            setClearIconVisible(text!!.length > 0)
        } else {
            setClearIconVisible(false)
        }
    }

    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为ClearEditText绘制上去
     *
     * @param visible visible
     */
    fun setClearIconVisible(visible: Boolean) {
        val right: Drawable?
        right = if (visible) {
            mClearDrawable
        } else {
            null
        }
        setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], right, compoundDrawables[3])
    }

    /**
     * 当输入框里面内容发生变化的时候回调的方法
     */
    override fun onTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        if (isHasFoucs) {
            setClearIconVisible(s.length > 0)
        }
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun afterTextChanged(s: Editable) {}

    /**
     * 设置晃动动画
     */
    fun setShakeAnimation() {
        this.animation = shakeAnimation(5)
    }

    private var mListener: OnDrawableRightClickListener? = null
    fun setOnDrawableRightClickListener(listener: OnDrawableRightClickListener?) {
        mListener = listener
    }

    interface OnDrawableRightClickListener {
        fun onClick()
    }

    companion object {
        /**
         * 晃动动画
         *
         * @param counts 1秒钟晃动多少下
         * @return Animation
         */
        fun shakeAnimation(counts: Int): Animation {
            val translateAnimation: Animation = TranslateAnimation(0F, 10F, 0F, 0F)
            translateAnimation.interpolator = CycleInterpolator(counts.toFloat())
            translateAnimation.duration = 1000
            return translateAnimation
        }

        fun dp2px(context: Context, dpVal: Float): Int {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    dpVal, context.resources.displayMetrics).toInt()
        }
    }


}


//    public void setDeleteImage(int id) {
//        deleteImage = getResources().getDrawable(id);
//        deleteImage.setBounds(0, 0, deleteImage.getIntrinsicWidth(), deleteImage.getIntrinsicHeight());
//        manageClearButton();
//    }

/*@Override
public boolean onTouch(View v, MotionEvent event) {
    ClearableEditTextWithIcon et = ClearableEditTextWithIcon.this;
    if (et.getCompoundDrawables()[2] == null)
        return false;
    if (event.getAction() != MotionEvent.ACTION_UP)
        return false;
    if (event.getX() > et.getWidth() - et.getPaddingRight() - deleteImage.getIntrinsicWidth()) {
        et.setText("");
        ClearableEditTextWithIcon.this.removeClearButton();
    }
    return false;
}*/