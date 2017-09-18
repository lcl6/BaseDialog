package com.lcl6.cn.basedialog.widget.anima;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by liancl on 2017/9/18.
 */

public class ObjectAnimateView extends android.support.v7.widget.AppCompatTextView {
    //进度条的进度
    float progress=0;
    //圆心x
    float centerX=20;
    //圆心y
    float centerY=20;
    //是否点击
    boolean isTouch=false;

    public ObjectAnimateView(Context context) {
        super(context);
        init();
    }
    public ObjectAnimateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ObjectAnimateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }
    // 创建 getter 方法
    public float getProgress() {
        return progress;
    }

    // 创建 setter 方法
    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);

        // 圆弧所在的椭圆对象
        RectF rectF = new RectF();
        rectF.left=centerX;
        rectF.right=200;
        rectF.top=centerY;
        rectF.bottom=200;

        Paint paintText = new Paint();
        paintText.setColor(Color.BLACK);
        paintText.setTextSize(30);
        //设置文字中间对齐
        paintText.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(progress+"%",100+centerX,100+centerY,paintText);
        canvas.drawArc(rectF,180,progress * 3.6f,false,paint);//startAngle 圆弧起始角度 sweepAngle 扫过的角度  useCenter 是否使用中心点  true画扇形
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        isTouch=!isTouch;
        if(isTouch){
            ObjectAnimator progress = ObjectAnimator.ofFloat(this, "progress", 0, 100);
            progress.start();
        }else {
            ObjectAnimator progress = ObjectAnimator.ofFloat(this, "progress", 100, 0);
            progress.start();
        }
        return super.onTouchEvent(event);

    }
}
