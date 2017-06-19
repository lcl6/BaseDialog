package com.lcl6.cn.basedialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lcl6.cn.basedialog.dialog.CustomButtomDialog;
import com.lcl6.cn.basedialog.dialog.CustomDialog;
import com.lcl6.cn.basedialog.dialog.CustomLeftDialog;
import com.lcl6.cn.basedialog.dialog.CustomRightDialog;
import com.lcl6.cn.utils.LocationUtils;
import com.lcl6.cn.utils.Utils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String TAG="text";

    public Context getContext(){
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.init(getContext());
        ButterKnife.bind(this);

        String countryName = LocationUtils.getCountryName(24.13, 118.10);
        


    }

    @OnClick({R.id.tv_cumston,R.id.tv_bottom,R.id.tv_right,R.id.tv_left,R.id.tv_next})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tv_cumston:
                CustomDialog customDialog = new CustomDialog(getContext());
                customDialog.setData("你好，bug");
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
            case R.id.tv_left:
                CustomLeftDialog dialogLeft = new CustomLeftDialog(getContext());
                dialogLeft.show();
                break;
            case R.id.tv_next:
                SecondActivity.start(getContext());
                break;

        }
    }


}
