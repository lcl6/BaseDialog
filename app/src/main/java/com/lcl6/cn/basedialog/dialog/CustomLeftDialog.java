package com.lcl6.cn.basedialog.dialog;

import android.content.Context;
import android.support.annotation.NonNull;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialogl.BaseLeftDialog;

/**
 *
 * Created by liancl on 2017/6/8.
 */

public class CustomLeftDialog extends BaseLeftDialog {
    public CustomLeftDialog(@NonNull Context context) {
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
