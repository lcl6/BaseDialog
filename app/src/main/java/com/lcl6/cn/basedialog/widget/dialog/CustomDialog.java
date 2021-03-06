package com.lcl6.cn.basedialog.widget.dialog;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialogl.BaseDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 * Created by liancl on 2017/6/8.
 */

public class CustomDialog extends BaseDialog {
    @BindView(R.id.tv_text)
    TextView mText;

    public CustomDialog(@NonNull Context context) {
        super(context);
    }
    @Override
    protected void findView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }

    public void setData(String data){
        mText.setText(data);
    }
    @Override
    protected int getlayoutId() {
        return R.layout.dialog_custom;
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
