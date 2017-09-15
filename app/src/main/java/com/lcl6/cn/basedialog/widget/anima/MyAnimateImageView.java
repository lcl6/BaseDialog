package com.lcl6.cn.basedialog.widget.anima;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lcl6.cn.utils.ToastUtils;

/**
 * Created by liancl on 2017/9/15.
 */

public class MyAnimateImageView extends android.support.v7.widget.AppCompatImageView implements View.OnClickListener{
    public MyAnimateImageView(Context context) {
        super(context);
        setOnClickListener(this);
    }

    public MyAnimateImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    public MyAnimateImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnClickListener(this);
    }

    float  realX;
    float  realY;
    boolean isDrag;//是否在拖拽

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //相对屏幕的距离
        float x = event.getRawX();
        float y = event.getRawY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                realX=x;
                realY=y;
                isDrag=false;
                break;
            case MotionEvent.ACTION_MOVE:
                isDrag=true;
                float meX=x-realX+getX();
                float meY=y-realY+getY();
                setX(meX);
                setY(meY);
                realX=x;
                realY=y;
                break;
            case MotionEvent.ACTION_UP:
                isDrag=false;
                break;
        }
        return !isDrag;
    }


    @Override
    public void onClick(View view) {
        ToastUtils.showShort("你好哦");
    }
}
