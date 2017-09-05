package com.lcl6.cn.component.base.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.lcl6.cn.component.R;
import com.lcl6.cn.component.widget.net.NetworkStateView;
import com.lcl6.cn.component.widget.title.TitleBar;
import com.lcl6.cn.utils.SdkUtil;
import com.lcl6.cn.utils.ToastUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 无mvp的基类
 * Created by liancl on 2017/9/1.
 */

public abstract class BaseActivity extends RxAppCompatActivity implements NetworkStateView.OnRefreshListener {

    private NetworkStateView mNetworkStateView;

    private Unbinder mUnbinder;
    private TitleBar mTitleBar;
    private ImageView mCollectView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeCreatView();
        setContentView(setLayoutId());
        afterCreat(savedInstanceState);
        mUnbinder = ButterKnife.bind(this);
        initView();
        initIntentData();
        initData();
        initViewListener();

    }

    /**
     * 初始化网络的状态
     */
    private void initNetWork() {
        mNetworkStateView.setOnRefreshListener(this);
        mNetworkStateView.showLoading();
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = getLayoutInflater().inflate(R.layout.activity_base, null);
        super.setContentView(view);
        FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.fl_contain);
        mNetworkStateView = (NetworkStateView) view.findViewById(R.id.nsv_state_view);
        mTitleBar= (TitleBar)view.findViewById(R.id.title_bar);
        View childView = LayoutInflater.from(this).inflate(layoutResID, null);
        frameLayout.addView(childView, 0);
        initNetWork();
        initTitle();
    }

    private void initTitle() {
        boolean isImmersive = false;
        if (SdkUtil.hasKitKat() && !SdkUtil.hasLollipop()) {
            isImmersive = true;
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
//                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        } else if (SdkUtil.hasLollipop()) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            isImmersive = true;
        }
        mTitleBar.setImmersive(isImmersive);
        mTitleBar.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
        mTitleBar.setLeftText("返回");
        mTitleBar.setLeftTextColor(Color.WHITE);
        mTitleBar.setLeftImageResource(R.drawable.ic_back);
        mTitleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleBar.setTitle("标题");
        mTitleBar.setTitleColor(Color.WHITE);
        mTitleBar.setSubTitleColor(Color.WHITE);
        mTitleBar.setDividerColor(Color.GRAY);

        mTitleBar.setActionTextColor(Color.WHITE);
        mCollectView = (ImageView) mTitleBar.addAction(new TitleBar.ImageAction(R.drawable.ic_net_error) {
            @Override
            public void performAction(View view) {
                ToastUtils.showShort("点击了s收藏");
                mCollectView.setImageResource(R.drawable.ic_refresh);
//                mTitleBar.setTitle(mIsSelected ? "文章详情\n朋友圈" : "帖子详情");
//                mIsSelected = !mIsSelected;
            }
        });

        mTitleBar.addAction(new TitleBar.TextAction("发布") {
            @Override
            public void performAction(View view) {
                ToastUtils.showShort("点击了发布");
            }
        });


    }

    public TitleBar getTitleBar(){
        return mTitleBar;
    }


    public Context getContext() {
        return this;
    }

    protected abstract int setLayoutId();

    protected void beforeCreatView() {
    }

    protected void afterCreat(Bundle savedInstanceState) {

    }

    protected abstract void initView();

    protected void initIntentData() {
    }

    protected abstract void initData();

    protected void initViewListener() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public void onRefresh() {

    }

    /**
     * 显示正在加载中
     */
    public void onLoadingStatus() {
        if (mNetworkStateView == null) {
            return;
        }
        mNetworkStateView.showLoading();
    }

    /**
     * 显示加载成功
     */
    public void onLoadSuccessStatus() {
        if (mNetworkStateView == null) {
            return;
        }
        mNetworkStateView.showSuccess();
    }

    /**
     * 显示错误状态
     */
    public void onLoadErrorStatus() {
        if (mNetworkStateView == null) {
            return;
        }
        mNetworkStateView.showError();
    }

    /**
     * 显示无数据状态
     */
    public void onLoadEmptyStatus() {
        if (mNetworkStateView == null) {
            return;
        }
        mNetworkStateView.showEmpty();
    }

    /**
     * 显示没有网络状态
     */
    public void onLoadNoNetStatus() {
        if (mNetworkStateView == null) {
            return;
        }
        mNetworkStateView.showNoNetwork();
    }

}
