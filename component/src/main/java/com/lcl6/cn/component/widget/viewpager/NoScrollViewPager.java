package com.lcl6.cn.component.widget.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 禁止左右滑动的viewpager
 * Created by liancl on 2017/8/4.
 */

public class NoScrollViewPager extends ViewPager {

    /** 是否可以滑动，默认不行 */
    private boolean isScroll = false;

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置是否可以左右滑动
     * @param isScroll 是否可以滑动
     */
    public void setScroll(boolean isScroll) {
        this.isScroll = isScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isScroll){
            return super.onTouchEvent(ev);
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isScroll){
            return super.onInterceptTouchEvent(ev);
        }
        return false;
    }
}

