package com.zxn.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView

/**
 * 带有图片的TextView,图片和文字紧挨在一起的效果.
 *  Updated by zxn on 2020.10.17.
 */
class ExpandTextView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyle: Int = 0) : AppCompatTextView(context!!, attrs, defStyle) {
    private val mTextBound = Rect()
    private val mDrawableGravity = 0
    override fun onDraw(canvas: Canvas) {
        val drawables = compoundDrawables
        if (drawables != null) {
            val drawableLeft = drawables[0]
            val drawableRight = drawables[2]
            if (drawableLeft != null) {
                // 文本区域
                paint.getTextBounds(text.toString(), 0, text.length, mTextBound)
                val textWidth = mTextBound.width().toFloat()
                val drawablePadding = compoundDrawablePadding
                val drawableWidth = drawableLeft.intrinsicWidth
                val bodyWidth = textWidth + drawableWidth + drawablePadding
                val color = textColors.getColorForState(drawableState, 0)
                paint.color = color
                paint.textSize = textSize
                canvas.save()
                // 平移画布
                canvas.translate((width - bodyWidth) / 2, (height - drawableLeft.intrinsicHeight) / 2.toFloat())
                drawableLeft.draw(canvas)
                canvas.restore()

                // 绘制文本
                val baseline = (height - paint.fontMetrics.bottom + paint.fontMetrics.top) / 2 - paint.fontMetrics.top
                canvas.drawText(text.toString(), width - textWidth - (width - bodyWidth) / 2, baseline, paint)
            } else if (drawableRight != null) {
                // 文本区域
                paint.getTextBounds(text.toString(), 0, text.length, mTextBound)
                val textWidth = mTextBound.width().toFloat()
                val textHeight = mTextBound.height().toFloat()
                val drawablePadding = compoundDrawablePadding
                val drawableWidth = drawableRight.intrinsicWidth
                val color = textColors.getColorForState(drawableState, 0)
                paint.color = color
                paint.textSize = textSize
                val bodyWidth = textWidth + drawableWidth + drawablePadding
                when (mDrawableGravity) {
                    Gravity.LEFT -> {
                        // 平移画布
                        canvas.translate(0f, height / 2.toFloat())
                        // 绘制文本
                        canvas.drawText(text.toString(), 0f, textHeight / 2, paint)

                        // 绘制图片
                        val left = drawableRight.mutate()
                        canvas.translate(textWidth + drawablePadding, 0f)
                        left.draw(canvas)
                    }
                    Gravity.CENTER -> {
                        // 平移画布
                        canvas.translate((width - bodyWidth) / 2, height / 2.toFloat())
                        // 绘制文本
                        canvas.drawText(text.toString(), 0f, textHeight / 2, paint)

                        // 绘制图片
                        val center = (drawableRight as StateListDrawable).mutate()
                        canvas.translate(textWidth + drawablePadding, 0f)
                        center.draw(canvas)
                    }
                    Gravity.RIGHT -> {
                        // 平移画布
                        canvas.translate(width - bodyWidth, height / 2.toFloat())
                        // 绘制文本
                        canvas.drawText(text.toString(), 0f, textHeight / 2, paint)

                        // 绘制图片
                        val right = drawableRight.mutate()
                        canvas.translate(textWidth + drawablePadding, 0f)
                        right.draw(canvas)
                    }
                }
            } else {
                super.onDraw(canvas)
            }
        } else {
            super.onDraw(canvas)
        }
    }
}



//    public void setGravity(int gravity) {
//        this.mDrawableGravity = gravity;
//        invalidate();
//    }