package com.lcl6.cn.basedialog.keyboard;

import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialog.keyboard.utils.KeyboardUtil;
import com.lcl6.cn.utils.EdTextUtil;
import com.lcl6.cn.utils.KeyboardUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lcl6.cn.utils.Utils.getContext;

/**
 * Created by liancl on 2017/7/31.
 */

public class KeyBoardActivity extends AppCompatActivity {


    @BindView(R.id.ed_input)
    EditText mEdCarNum;
    @BindView(R.id.keyboard_view)
    KeyboardView mKeyboardView;
    private KeyboardUtil mKeyboardUtil;
    private boolean isClick;//判断是否点击

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);
        ButterKnife.bind(this);
        initKeyboard();
        initKeyboardListener();
    }

    private void initKeyboardListener() {
        mKeyboardUtil.setOnClickFinishListener(new KeyboardUtil.OnClickFinishListener() {
            @Override
            public void onClickFinish() {
                KeyboardUtils.hideSoftInput(mEdCarNum);
                mKeyboardUtil.hideKeyboard();
            }
        });

        mEdCarNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (isClick) {
                    mKeyboardUtil.hideKeyboard();
                    return;
                }
                if (hasFocus) {
                    mKeyboardUtil.showKeyboard();
                } else {
                    mKeyboardUtil.hideKeyboard();
                }
                KeyboardUtils.hideSoftInput(mEdCarNum);
            }
        });
        mEdCarNum.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isClick = false;
                if (mEdCarNum.hasFocus()) {
                    mKeyboardUtil.showKeyboard();
                    KeyboardUtils.hideSoftInput(mEdCarNum);
                }
                return false;
            }
        });
    }

    private void initKeyboard() {
        mKeyboardUtil = new KeyboardUtil(getContext(), mKeyboardView, mEdCarNum, KeyboardUtil.QWERTY);
        mEdCarNum.requestFocus();
        EdTextUtil.setEditTextFocus(KeyBoardActivity.this, mEdCarNum);
        mKeyboardUtil.hideKeyboard();
    }
}
