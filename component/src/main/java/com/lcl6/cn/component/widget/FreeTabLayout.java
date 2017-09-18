package com.lcl6.cn.component.widget;

import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.lcl6.cn.utils.ReflectUtils;


/**
 * 可设置底线宽度的TabLayout
 */
public class FreeTabLayout extends TabLayout{
    public FreeTabLayout(Context context) {
        super(context);
    }

    public FreeTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FreeTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置底线左右Margin
     * @param leftDp 左边Margin
     * @param rightDp 右边Margin
     */
    public void setTabIndicatorMargin(int leftDp, int rightDp) {
        Class<?> cl =  ReflectUtils.getClassForName("android.support.design.widget.TabLayout");
        LinearLayout layout = null;
        try {
            layout = (LinearLayout) ReflectUtils.getFieldValue(cl, this, "mTabStrip");
        }catch (Exception e){
            e.printStackTrace();
        }

        if (layout == null){
            return;
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDp, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDp, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }
}
