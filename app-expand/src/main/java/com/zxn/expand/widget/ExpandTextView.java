package com.zxn.expand.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;

import com.zxn.expand.R;


/**
 * Updated by zxn on 2019/6/29.
 */
public class ExpandTextView extends AppCompatTextView {

    private Rect mTextBound = new Rect();
    private int mGravity;

    public ExpandTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ExpandTextView);
        mGravity = a.getInt(R.styleable.ExpandTextView_drawable_gravity, 0);
        a.recycle();
    }

    public ExpandTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandTextView(Context context) {
        this(context, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        // 图片在左边的处理
        Drawable drawableRight = drawables[2];
        if (drawableRight != null) {
            // 文本区域
            getPaint().getTextBounds(getText().toString(), 0, getText().length(), mTextBound);
            float textWidth = mTextBound.width();
            float textHeight = mTextBound.height();
            int drawablePadding = getCompoundDrawablePadding();
            int drawableWidth = drawableRight.getIntrinsicWidth();

            int color = getTextColors().getColorForState(getDrawableState(), 0);
            getPaint().setColor(color);
            getPaint().setTextSize(getTextSize());
            float bodyWidth = textWidth + drawableWidth + drawablePadding;

            switch (mGravity) {
                case Gravity.LEFT:
                    // 平移画布
                    canvas.translate(0, getHeight() / 2);
                    // 绘制文本
                    canvas.drawText(getText().toString(), 0, textHeight / 2, getPaint());

                    // 绘制图片
                    Drawable left = drawableRight.mutate();

                    canvas.translate(textWidth + drawablePadding, 0);
                    left.draw(canvas);
                    break;
                case Gravity.CENTER:
                    // 平移画布
                    canvas.translate((getWidth() - bodyWidth) / 2, getHeight() / 2);
                    // 绘制文本
                    canvas.drawText(getText().toString(), 0, textHeight / 2, getPaint());

                    // 绘制图片
                    Drawable center = ((StateListDrawable) drawableRight).mutate();

                    canvas.translate(textWidth + drawablePadding, 0);
                    center.draw(canvas);
                    break;
                case Gravity.RIGHT:
                    // 平移画布
                    canvas.translate(getWidth() - bodyWidth, getHeight() / 2);
                    // 绘制文本
                    canvas.drawText(getText().toString(), 0, textHeight / 2, getPaint());

                    // 绘制图片
                    Drawable right = drawableRight.mutate();

                    canvas.translate(textWidth + drawablePadding, 0);
                    right.draw(canvas);
                    break;
                default:
                    break;
            }
        }

    }

    public void setGravity(int gravity) {
        this.mGravity = gravity;
        invalidate();
    }

}