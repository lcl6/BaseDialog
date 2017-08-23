package com.lcl6.cn.basedialog.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialogl.BaseBottomDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liancl on 2017/6/8.
 */

public class CustomButtomDialog extends BaseBottomDialog {

    public CustomButtomDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void findView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getlayoutId() {
        return R.layout.dialog_custom_bottom;
    }
    @OnClick(R.id.tv_cesh)
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tv_cesh:
                dismiss();
                break;
        }
    }
}
