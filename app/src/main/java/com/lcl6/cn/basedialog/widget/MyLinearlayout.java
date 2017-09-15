package com.lcl6.cn.basedialog.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by liancl on 2017/9/15.
 */

public class MyLinearlayout extends LinearLayout {
    public MyLinearlayout(Context context) {
        super(context);
    }

    public MyLinearlayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearlayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyLinearlayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        init(canvas);
    }

    private void init(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#ff00ff"));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(20,30,10,paint);
        canvas.drawCircle(50,60,10,paint);
        canvas.drawCircle(65,80,10,paint);
        canvas.drawCircle(87,90,10,paint);
        canvas.drawCircle(100,120,10,paint);

    }
}
