package com.lcl6.cn.basedialog.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialogl.BaseRightDialog;

/**
 *
 * Created by liancl on 2017/6/8.
 */

public class CustomRightDialog extends BaseRightDialog {
    public CustomRightDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void findView() {
    }

    @Override
    protected int getlayoutId() {
        return R.layout.dialog_custom_right;
    }
}
