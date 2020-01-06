package com.zxn.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;

/**
 * 一个只有手动选中复选框后相应事件的CheckBox
 * Created by zxn on 2020/1/6.
 */
public class ExpandCheckBox extends AppCompatCheckBox {
    private static final String LOG_TAG = ExpandCheckBox.class.getSimpleName();
    private OnBoxCheckedChangeListener mOnBoxCheckedChangeListener;

    public ExpandCheckBox(Context context) {
        this(context, null);
    }

    public ExpandCheckBox(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return ExpandCheckBox.class.getName();
    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);

        if (null != mOnBoxCheckedChangeListener) {
            mOnBoxCheckedChangeListener.onBoxCheckedChanged(this, isChecked());
        }
    }

    /**
     * 该方法在代码中调用,改变选中状态,不会回调到OnBoxCheckedChangeListener
     *
     * @param checked 是否选中.
     */
    public void setSimpleChecked(boolean checked) {
        super.setChecked(checked);
    }

    public void setOnBoxCheckedChangeListener(@Nullable OnBoxCheckedChangeListener listener) {
        mOnBoxCheckedChangeListener = listener;
    }

    /**
     * Interface definition for a callback to be invoked when the checked state
     * of a compound button changed.
     */
    public interface OnBoxCheckedChangeListener {
        /**
         * Called when the checked state of a compound button has changed.
         *
         * @param buttonView The compound button view whose state has changed.
         * @param isChecked  The new checked state of buttonView.
         */
        void onBoxCheckedChanged(CompoundButton buttonView, boolean isChecked);
    }
}
