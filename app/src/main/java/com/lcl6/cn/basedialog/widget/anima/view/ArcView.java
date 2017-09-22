package com.lcl6.cn.basedialog.widget.anima.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liancl on 2017/9/21.
 */

public class ArcView extends View {
    public ArcView(Context context) {
        super(context);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setColor(Color.BLUE);
        RectF rectF = new RectF();
        rectF.left=0;
        rectF.top=0;
        rectF.bottom=200;
        rectF.right=200;
        //useCenter true 表示连接中心 就是画扇形  false 表示画弧形
        canvas.drawArc(rectF,60,120,true,paint);
        canvas.drawArc(0,0,200,100,-60,-120,true,paint);
    }
}
