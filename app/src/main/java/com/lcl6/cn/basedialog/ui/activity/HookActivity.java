package com.lcl6.cn.basedialog.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialog.util.HookListener;
import com.lcl6.cn.component.base.activity.BaseActivity;
import com.lcl6.cn.utils.ReflectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import butterknife.OnClick;

/**
 * Created by liancl on 2017/12/18.
 */

public class HookActivity extends BaseActivity {

    @Override
    protected int setLayoutId() {
        return R.layout.activty_hook;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        onLoadSuccessStatus();
    }


    public static void start(Context context) {
        Intent starter = new Intent(context, HookActivity.class);
        context.startActivity(starter);
    }

    @OnClick(R.id.id_hook)
    public void onClick(View view){
        hook(view);
        Map map = new HashMap();
        map.put("m", "手摸手");
        map.put("b", "mmp");
        view.setTag(R.id.id_hook, map);
    }

    private void hook(View view) {
        try {
            Class<?> aClass = ReflectUtils.getClassForName("android.view.View");
            Method method = aClass.getDeclaredMethod("getListenerInfo");
            method.setAccessible(true);
            Object listenerInfo = method.invoke(view);
            Class aClass1 = Class.forName("android.view.View$ListenerInfo");
            Field field = aClass1.getDeclaredField("mOnClickListener");
            field.set(listenerInfo, new HookListener((View.OnClickListener) field.get(listenerInfo)));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
