package com.lcl6.cn.basedialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lcl6.cn.basedialog.dialog.CustomButtomDialog;
import com.lcl6.cn.basedialog.dialog.CustomDialog;
import com.lcl6.cn.basedialog.dialog.CustomRightDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public Context getContext(){
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.tv_cumston,R.id.tv_bottom,R.id.tv_right})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tv_cumston:
                CustomDialog customDialog = new CustomDialog(getContext());
                customDialog.show();
                break;
            case R.id.tv_bottom:
                CustomButtomDialog dialogBottom = new CustomButtomDialog(getContext());
                dialogBottom.show();
                break;
            case R.id.tv_right:
                CustomRightDialog dialogRight = new CustomRightDialog(getContext());
                dialogRight.show();
                break;
        }
    }


}
