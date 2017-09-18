package com.lcl6.cn.component.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lcl6.cn.utils.DensityUtils;

/**
 * 下角标
 * Created by liancl on 2017/8/4.
 */

public class TrigonView extends android.support.v7.widget.AppCompatTextView {
    //无参
    public TrigonView(Context context) {
        super(context);
    }

    //有参
    public TrigonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(Color.parseColor("#1777C9"));
        //实例化路径
        Path path = new Path();
        path.moveTo(DensityUtils.dp2px(getContext(),40), 0);// 此点为多边形的起点
        path.lineTo(0, DensityUtils.dp2px(getContext(),40));
        path.lineTo(DensityUtils.dp2px(getContext(),40), DensityUtils.dp2px(getContext(),40));
        path.close(); // 使这些点构成封闭的多边形
        canvas.drawPath(path, p);

    }
}
