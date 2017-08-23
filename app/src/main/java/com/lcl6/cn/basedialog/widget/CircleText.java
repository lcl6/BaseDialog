package com.lcl6.cn.basedialog.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by liancl on 2017/8/21
 */

public class CircleText extends android.support.v7.widget.AppCompatTextView {
    public CircleText(Context context) {
        super(context);
    }

    public CircleText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        canvas.drawCircle(100,100,20,paint);
    }
}
