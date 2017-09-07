package com.lcl6.cn.basedialog.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.lcl6.cn.basedialog.R;

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
//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setColor(0xffff00ff);
//        paint.setStrokeWidth(10);
//        paint.setAntiAlias(true);
//        canvas.drawCircle(100,100,80,paint);

        Paint paint = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        canvas.drawBitmap(bitmap,0,0,paint);
    }
}
