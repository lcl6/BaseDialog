package com.lcl6.cn.basedialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by liancl on 2017/6/19.
 */

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static void start(Context context){
        Intent intent = new Intent();
        intent.setClass(context,SecondActivity.class);
        context.startActivity(intent);
    }

}
