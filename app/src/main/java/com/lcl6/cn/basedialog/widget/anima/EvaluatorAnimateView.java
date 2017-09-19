package com.lcl6.cn.basedialog.widget.anima;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lcl6.cn.basedialog.ui.activity.evaluator.HsvEvaluator;

/**
 * Created by liancl on 2017/9/19.
 */

public class EvaluatorAnimateView extends View {

    int color=0xffff0000;

    public EvaluatorAnimateView(Context context) {
        super(context);
    }

    public EvaluatorAnimateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EvaluatorAnimateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getColor());
        paint.setAntiAlias(true);
        canvas.drawCircle(100,100,100,paint);
    }

    boolean isTouch=false;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        isTouch=!isTouch;
        ObjectAnimator objectAnimator;
        if(isTouch){
           objectAnimator = ObjectAnimator.ofInt(this, "color", 0xffff0000, 0xff00ff00);
        }else {
           objectAnimator = ObjectAnimator.ofInt(this, "color", 0xff00ff00, 0xffff0000);
        }
        //设置色值
        objectAnimator.setEvaluator(new HsvEvaluator());
        objectAnimator.setDuration(500);
        objectAnimator.start();
        return super.onTouchEvent(event);
    }
}
