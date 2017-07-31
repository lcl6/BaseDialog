package com.lcl6.cn.utils;

import android.app.Activity;
import android.text.InputType;
import android.view.WindowManager;
import android.widget.EditText;

import java.lang.reflect.Method;

/**
 *
 * Created by liancl on 2017/5/24.
 */

public class EdTextUtil {
    public static void setEditTextFocus(Activity activity, EditText editText){
        if (android.os.Build.VERSION.SDK_INT <= 16) {
            editText.setInputType(InputType.TYPE_NULL);
        } else {
            activity.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }
        try {
            Class<EditText> cls = EditText.class;
            Method[] methods = cls.getMethods();
            Method setShowSoftInputOnFocus = null;
            // 版本不同获取不同焦点
            for (int idx = 0; idx < methods.length; idx++) {
                if (methods[idx].getName().equals("setShowSoftInputOnFocus")) {
                    setShowSoftInputOnFocus = methods[idx];
                } else if (methods[idx].getName().equals(
                        "setSoftInputShownOnFocus")) {
                    setShowSoftInputOnFocus = methods[idx];
                }
            }
            if (setShowSoftInputOnFocus != null) {
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(editText, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
