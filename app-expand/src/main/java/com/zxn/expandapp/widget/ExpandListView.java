package com.zxn.expandapp.widget;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * ExpandListView
 * 一个可以和ScrollView嵌套的ListView,解决高度问题.
 */
public class ExpandListView extends ListView {
	public ExpandListView(Context context) {
		super(context);
	}

	public ExpandListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ExpandListView(Context context, AttributeSet attrs,
						  int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
