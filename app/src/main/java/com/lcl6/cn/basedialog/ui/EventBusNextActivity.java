package com.lcl6.cn.basedialog.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialog.event.ClickEvent;
import com.lcl6.cn.basedialog.util.OnClickEvent;
import com.lcl6.cn.component.base.activity.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liancl on 2018/5/11.
 */

public class EventBusNextActivity extends BaseActivity {

    @BindView(R.id.tv_next)
    TextView tvNext;

    public static void start(Context context) {
        Intent starter = new Intent(context, EventBusNextActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_event_next;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvNext.setOnClickListener(new OnClickEvent() {
            @Override
            public void singleClick(View v) {

                finish();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void finich(ClickEvent event){
        com.blankj.utilcode.util.ToastUtils.showLong("接受到了");
//        finish();
    }

    @Override
    protected void initData() {
        onLoadSuccessStatus();
    }

}
