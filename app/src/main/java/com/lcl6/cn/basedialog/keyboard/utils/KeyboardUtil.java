package com.lcl6.cn.basedialog.keyboard.utils;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.lcl6.cn.basedialog.R;


public class KeyboardUtil {
	private KeyboardView keyboardView;
	private Keyboard k1;// 字母键盘 -7
	private Keyboard k2;// 数字键盘 -8
	private Keyboard k3;// 各地简称 -9

	private static final int NUM_KEYBOARD_CODE = -7;
	private static final int LETTER_KEYBOARD_CODE = -8;
	private static final int ABBREVIATION_KEYBOARD_CODE = -9;

	private EditText ed;
	public static int QWERTY=1;

	private OnClickJinListener onClickJinListener;

	public void setOnClickFinishListener(OnClickFinishListener onClickFinishListener) {
		this.onClickFinishListener = onClickFinishListener;
	}

	private OnClickFinishListener onClickFinishListener;
	public KeyboardUtil(Context ctx, KeyboardView keyBoardView, EditText edit, int type) {

		this.ed = edit;
		k1 = new Keyboard(ctx, R.xml.qwerty);
		k2 = new Keyboard(ctx, R.xml.symbols);
		k3 = new Keyboard(ctx, R.xml.abbreviation);
		keyboardView = keyBoardView;
		switch (type){
			case 0:
				keyboardView.setKeyboard(k3);
				break;
			case 1:
				keyboardView.setKeyboard(k1);
				break;
			case 2:
				keyboardView.setKeyboard(k2);
				break;
		}

		keyboardView.setEnabled(true);
		keyboardView.setPreviewEnabled(true);
		keyboardView.setOnKeyboardActionListener(listener);
	}

	private OnKeyboardActionListener listener = new OnKeyboardActionListener() {
		@Override
		public void swipeUp() {
		}

		@Override
		public void swipeRight() {
		}

		@Override
		public void swipeLeft() {
		}

		@Override
		public void swipeDown() {
		}

		@Override
		public void onText(CharSequence text) {
		}

		@Override
		public void onRelease(int primaryCode) {
		}

		@Override
		public void onPress(int primaryCode) {
		}

		@Override
		public void onKey(int primaryCode, int[] keyCodes) {
			Editable editable = ed.getText();
			int start = editable.length();
			int index = ed.getSelectionStart();
			if (primaryCode == Keyboard.KEYCODE_CANCEL) {// 完成
				hideKeyboard();
				if(onClickFinishListener!=null){
					onClickFinishListener.onClickFinish();
				}
			} else if (primaryCode == Keyboard.KEYCODE_DELETE) {// 回退
				if (editable.length() > 0) {
					Log.d("","");
					if(index>0){
						editable.delete(index-1, index);
					}

				}
			} else if (primaryCode == NUM_KEYBOARD_CODE) {// 数字键盘切换
				keyboardView.setKeyboard(k2);
			} else if (primaryCode == LETTER_KEYBOARD_CODE) {// 字母键盘切换
				keyboardView.setKeyboard(k1);
				if(onClickJinListener!=null){
					onClickJinListener.onClickJin();
				}
			} else if (primaryCode == ABBREVIATION_KEYBOARD_CODE) {// 简称键盘切换
				keyboardView.setKeyboard(k3);

			} else if (primaryCode == 57419) { // go left
				if (start > 0) {
					ed.setSelection(start - 1);
				}
			} else if (primaryCode == 57421) { // go right
				if (start < ed.length()) {
					ed.setSelection(start + 1);
				}
			} else if (!((primaryCode >= 65 && primaryCode <= 90) || (primaryCode >= 48 && primaryCode <= 57))) {
				if (editable.length() >= 0) {
					//editable.clear();
					//start = ed.length();
					keyboardView.setKeyboard(k1);
					if(onClickJinListener!=null){
						onClickJinListener.onClickJin();
					}
				}
				editable.insert(index, Character.toString((char) primaryCode));
			} else {
				editable.insert(index, Character.toString((char) primaryCode));
			}
		}
	};

	public void showKeyboard() {
		int visibility = keyboardView.getVisibility();
		if (visibility == View.GONE || visibility == View.INVISIBLE) {
			keyboardView.setVisibility(View.VISIBLE);
		}
	}

	public void hideKeyboard() {
		int visibility = keyboardView.getVisibility();
		if (visibility == View.VISIBLE) {
			keyboardView.setVisibility(View.GONE);
		}
	}
	//点击京回调监听
	private interface OnClickJinListener{
		 void onClickJin();
	}
	//点击完成回调监听
	public interface OnClickFinishListener{
		 void onClickFinish();
	}
}
