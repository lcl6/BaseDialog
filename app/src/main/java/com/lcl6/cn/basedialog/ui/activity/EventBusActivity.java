package com.lcl6.cn.basedialog.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialog.ui.EventBusNextActivity;
import com.lcl6.cn.basedialog.util.OnClickEvent;
import com.lcl6.cn.component.base.activity.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liancl on 2018/5/11.
 */

public class EventBusActivity extends BaseActivity {

    @BindView(R.id.tv_next)
    TextView tvNext;

    public static void start(Context context) {
        Intent starter = new Intent(context, EventBusActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_event;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvNext.setOnClickListener(new OnClickEvent() {
            @Override
            public void singleClick(View v) {
                EventBusNextActivity.start(getContext());
            }
        });
    }
//
//
//    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
//    public void finich(ClickEvent event){
//        finish();
//    }


    @Override
    protected void initData() {
        onLoadSuccessStatus();
    }

}
