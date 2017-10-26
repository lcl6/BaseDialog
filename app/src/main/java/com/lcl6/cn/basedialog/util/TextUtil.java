package com.lcl6.cn.basedialog.util;

import android.content.Context;
import android.widget.TextView;

/**
 * Created by liancl on 2017/10/23.
 */

public class TextUtil {
    public static String get(Context context){
        TextView textView = new TextView(context);
        textView.setText("你好");
        return textView.getText().toString();
    }
}
