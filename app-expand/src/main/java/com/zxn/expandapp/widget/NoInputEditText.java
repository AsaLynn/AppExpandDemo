package com.zxn.expandapp.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.lang.reflect.Method;

/**
 * 一个屏蔽键盘输入的EditText.
 * Updated by zxn on 2019/11/14.
 */
public class NoInputEditText extends AppCompatEditText {
	boolean canPaste() {
		return false;
	}

	boolean canCut() {
		return false;
	}

	boolean canCopy() {
		return false;
	}

	boolean canSelectAllText() {
		return false;
	}

	boolean canSelectText() {
		return false;
	}

	boolean textCanBeSelected() {
		return false;
	}

	public NoInputEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		setLongClickable(false);
		setTextIsSelectable(false);
		setCustomSelectionActionModeCallback(new ActionMode.Callback() {
			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				return false;
			}

			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				return false;
			}

			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				return false;
			}

			@Override
			public void onDestroyActionMode(ActionMode mode) {

			}
		});
		
		if (android.os.Build.VERSION.SDK_INT <= 10) {
			setInputType(InputType.TYPE_NULL);
		} else {
			try {
				Class<EditText> cls = EditText.class;
				Method setSoftInputShownOnFocus;
				setSoftInputShownOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
				setSoftInputShownOnFocus.setAccessible(true);
				setSoftInputShownOnFocus.invoke(this, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		addTextChangedListener(new InputWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
				super.afterTextChanged(s);
				System.out.println(getText().toString());
				setSelection(getText().toString().length());
			}
		});

	}

	
	@Override
	public boolean onTextContextMenuItem(int id) {
		return true;
	}
	
	public class InputWatcher implements TextWatcher {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {

		}

		@Override
		public void afterTextChanged(Editable s) {

		}

	}

}
