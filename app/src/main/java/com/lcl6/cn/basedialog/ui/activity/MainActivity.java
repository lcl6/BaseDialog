package com.lcl6.cn.basedialog.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialog.mvp.ui.DaggerScopActivity;
import com.lcl6.cn.basedialog.mvp.ui.ManagerActivity;
import com.lcl6.cn.basedialog.mvp.ui.MvpActivity;
import com.lcl6.cn.basedialog.mvp.ui.VideoActivity;
import com.lcl6.cn.basedialog.widget.dialog.CustomButtomDialog;
import com.lcl6.cn.basedialog.widget.dialog.CustomDialog;
import com.lcl6.cn.basedialog.widget.dialog.CustomLeftDialog;
import com.lcl6.cn.basedialog.widget.dialog.CustomRightDialog;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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


    String a=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        // 子线程更新UI
                        mTextCumston.setText("我 tm 妥妥地执行完毕");
                    }
                }
        ).start();


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initTextBack() {
        Flowable.interval(1, TimeUnit.MILLISECONDS)
                .onBackpressureDrop()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        Thread.sleep(1000);
                        Log.e("zhao", "onNext: " + aLong);
                    }
                });


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }


    @OnClick({R.id.tv_cumston,R.id.tv_bottom,R.id.tv_right,R.id.tv_left,R.id.tv_next,R.id.tv_jsoup,R.id.tv_mvp
            ,R.id.tv_widget,R.id.tv_retrofit,R.id.tv_dagger,R.id.tv_dagger_scop,R.id.tv_viewpage,R.id.tv_lru
    ,R.id.tv_video,R.id.tv_hook,R.id.tv_testn,R.id.tv_permission})
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
//                ToastUtils.showShortSafe("显示短图示");
                break;
            case R.id.tv_jsoup://跳转网络爬虫界面
                JsoupActivity.start(getContext());
                break;
            case R.id.tv_mvp://跳转mvp界面
                MvpActivity.start(getContext());
                break;
            case R.id.tv_widget://自定义界面
                WidgetActivity.start(getContext());
                break;
            case R.id.tv_retrofit://网络管理器
                ManagerActivity.start(getContext());
                break;
            case R.id.tv_dagger://dagger
                DaggerActivity.start(getContext());
                break;
            case R.id.tv_dagger_scop://dagger scop
                DaggerScopActivity.start(getContext());
                break;
            case R.id.tv_viewpage://测试viewpager
                ViewPagerActivity.start(getContext());
                break;
            case R.id.tv_lru://测试二级缓存
                LruActivity.start(getContext());
                break;
            case R.id.tv_video://测试视频弹幕
                VideoActivity.start(getContext());
                break;
            case R.id.tv_hook://测试hook
                HookActivity.start(getContext());
                break;
            case R.id.tv_testn://测试android7.0
                TestNActivity.start(getContext());
                break;
            case R.id.tv_permission://测试权限
                PermisisonActivity.start(getContext());
                break;
        }
    }





}
