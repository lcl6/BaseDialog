package com.lcl6.cn.basedialog.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialog.app.App;
import com.lcl6.cn.basedialog.constant.Constant;
import com.lcl6.cn.basedialog.di.bean.Car;
import com.lcl6.cn.basedialog.di.bean.ClassA;
import com.lcl6.cn.basedialog.di.bean.ClassB;
import com.lcl6.cn.basedialog.di.bean.Man;
import com.lcl6.cn.basedialog.di.component.ClassAComponent;
import com.lcl6.cn.basedialog.di.component.DaggerClassAComponent;
import com.lcl6.cn.basedialog.di.component.DaggerManComponent;
import com.lcl6.cn.basedialog.di.component.ManComponent;
import com.lcl6.cn.basedialog.di.component.SonComponent;
import com.lcl6.cn.basedialog.di.model.CarMoudule;
import com.lcl6.cn.basedialog.di.model.ModuleA;
import com.lcl6.cn.component.base.activity.BaseActivity;
import com.lcl6.cn.utils.ToastUtils;
import com.lcl6.cn.utils.anim.QMUIDirection;
import com.lcl6.cn.utils.anim.QMUIViewHelper;
import com.lcl6.cn.utils.statusbar.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liancl on 2017/8/29.
 */

public class DaggerActivity extends BaseActivity {
    @Inject
    ClassA classA;
    @Inject
    ClassB classB;
    @BindView(R.id.tv_float)
    TextView mFloatView;


//    @Inject
//    Car mCar;
        @Inject
    Provider<Car> carProvide;

    public static void start(Context context) {
        Intent starter = new Intent(context, DaggerActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_dagger;
    }
    @Override
    protected void initView() {
        ClassAComponent classAComponent = DaggerClassAComponent.builder()
                .appComponent(App.getAppComponent())
                .moduleA(new ModuleA(2, 3, this))
                .build();
//                .inject(this);
        classAComponent.inject(this);
        Activity activity = classAComponent.getActivity();
        Log.e(Constant.TAG, activity.getLocalClassName() );
        //测试单例
//        App.getClassAComponent().inject(App.getInstance());

        ManComponent build = DaggerManComponent.builder().carMoudule(new CarMoudule()).build();
        build.inject(new Man());
        SonComponent.Builder builder = build.sonComponent();

    }
    @Override
    protected void initData() {
        int a = classA.getA();
        int b = classA.getB();

        Log.e(Constant.TAG, "initData:a "+a );
        Log.e(Constant.TAG, "initData:b "+b);
        int a1 = classB.getA();
        ClassA classA = classB.getClassA();
        Log.e(Constant.TAG, "initData:a1 "+a1);
        int a2 = classA.getA();
        int b1 = classA.getB();
        Log.e(Constant.TAG, "initData:a2 "+a2 );
        Log.e(Constant.TAG, "initData:b2 "+b1);

        List<Car> list = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
//            list.add(mCar);
            list.add(carProvide.get());
        }
        for (Car car : list) {
            Log.e(Constant.TAG, car.toString());
        }
        onLoadSuccessStatus();
    }

    @OnClick({R.id.tv_status,R.id.tv_status_white,R.id.tv_status_height,R.id.tv_fade_in, R.id.tv_slide_in})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tv_status:
                QMUIStatusBarHelper.setStatusBarLightMode(this);
                break;
            case R.id.tv_status_white:
                QMUIStatusBarHelper.setStatusBarDarkMode(this);
                break;
            case R.id.tv_status_height:
                int statusbarHeight = QMUIStatusBarHelper.getStatusbarHeight(this);
                ToastUtils.showShort("状态栏的高度为："+statusbarHeight);
                break;
            case R.id.tv_fade_in:
                mFloatView.clearAnimation();
                AlphaAnimation alphaAnimation = mFloatView.getVisibility() == View.GONE ? QMUIViewHelper.fadeIn(mFloatView, 500, null, true) : QMUIViewHelper.fadeOut(mFloatView, 500, null, true);
                break;
            case R.id.tv_slide_in:
                mFloatView.clearAnimation();
                TranslateAnimation translateAnimation = mFloatView.getVisibility() == View.GONE ? QMUIViewHelper.slideIn(mFloatView, 500, null, true, QMUIDirection.BOTTOM_TO_TOP) : QMUIViewHelper.slideOut(mFloatView, 500, null, true, QMUIDirection.TOP_TO_BOTTOM);
                break;





        }
    }
}
