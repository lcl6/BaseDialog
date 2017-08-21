package com.lcl6.cn.basedialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by liancl on 2017/8/21
 */

public class WidgetActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);
    }
    public static void start(Context context) {
        Intent starter = new Intent(context, WidgetActivity.class);
        context.startActivity(starter);
    }
}
