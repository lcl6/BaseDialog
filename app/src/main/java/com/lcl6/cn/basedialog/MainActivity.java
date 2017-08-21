package com.lcl6.cn.basedialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcl6.cn.basedialog.dialog.CustomButtomDialog;
import com.lcl6.cn.basedialog.dialog.CustomDialog;
import com.lcl6.cn.basedialog.dialog.CustomLeftDialog;
import com.lcl6.cn.basedialog.dialog.CustomRightDialog;
import com.lcl6.cn.utils.ToastUtils;
import com.lcl6.cn.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String TAG="text";

    public Context getContext(){
        return this;
    }

    @BindView(R.id.lin_base)
    LinearLayout mLinBase;
    @BindView(R.id.tv_cumston)
    TextView mTextCumston;

    @BindView(R.id.tv_jsoup)
    TextView mTextJsoup;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.init(getContext());
        ButterKnife.bind(this);

        initListener();

    }

    private void initListener() {

//        mLinBase.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                return false;
//            }
//        });

//        mTextCumston.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.e(TAG, "onTouch: "+event.getAction() );
//                return true;
//            }
//        });
//        mTextCumston.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e(TAG, "onClick: " );
//            }
//        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }


    @OnClick({R.id.tv_cumston,R.id.tv_bottom,R.id.tv_right,R.id.tv_left,R.id.tv_next,R.id.tv_jsoup,R.id.tv_widget})
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
//                SecondActivity.start(getContext());
                ToastUtils.showShortSafe("显示短图示");
                break;
            case R.id.tv_jsoup://跳转网络爬虫界面
                JsoupActivity.start(getContext());
                break;
            case R.id.tv_widget://自定义界面
                WidgetActivity.start(getContext());
                break;

        }
    }





}
