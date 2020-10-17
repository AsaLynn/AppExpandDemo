package com.zxn.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.CompoundButton
import androidx.appcompat.widget.AppCompatCheckBox

/**
 * Expand
 * 一个只有手动选中复选框后相应事件的CheckBox
 * Created by zxn on 2020/1/6.
 */
class ExpandCheckBox @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : AppCompatCheckBox(context!!, attrs, defStyleAttr) {
    private var mOnBoxCheckedChangeListener: OnBoxCheckedChangeListener? = null
    override fun getAccessibilityClassName(): CharSequence {
        return ExpandCheckBox::class.java.name
    }

    override fun setChecked(checked: Boolean) {
        super.setChecked(checked)
        if (null != mOnBoxCheckedChangeListener) {
            mOnBoxCheckedChangeListener!!.onBoxCheckedChanged(this, isChecked)
        }
    }

    /**
     * 该方法在代码中调用,改变选中状态,不会回调到OnBoxCheckedChangeListener
     *
     * @param checked 是否选中.
     */
    fun setSimpleChecked(checked: Boolean) {
        super.setChecked(checked)
    }

    fun setOnBoxCheckedChangeListener(listener: OnBoxCheckedChangeListener/*(CompoundButton?, Boolean) -> Unit*/) {
        mOnBoxCheckedChangeListener = listener
    }

    /**
     * Interface definition for a callback to be invoked when the checked state
     * of a compound button changed.
     */
    interface OnBoxCheckedChangeListener {
        /**
         * Called when the checked state of a compound button has changed.
         *
         * @param buttonView The compound button view whose state has changed.
         * @param isChecked  The new checked state of buttonView.
         */
        fun onBoxCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean)
    }

    companion object {
        private val LOG_TAG = ExpandCheckBox::class.java.simpleName
    }
}