package com.lcl6.cn.basedialog.widget.anima.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lcl6.cn.basedialog.R;

/**
 * 沿着x轴旋转的控件
 * Created by liancl on 2017/9/20.
 */

public class RoatXImageView extends View {

    Paint paint=  new Paint();

    Point point = new Point(30,30);
    Camera camera = new Camera();
    private float degree;

    public RoatXImageView(Context context) {
        super(context);
    }

    public RoatXImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RoatXImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public float getDegree() {
        return degree;
    }

    public void setDegree(float degree) {
        this.degree = degree;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
        int bwidth = bitmap.getWidth();
        int bheight = bitmap.getHeight();
        int width= bwidth/2+point.x;
        int height= bheight/2+point.y;
        camera.save();
        camera.rotateX(getDegree());
        canvas.translate(width,height);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.translate(-width,-height);
        canvas.drawBitmap(bitmap,point.x,point.y,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ObjectAnimator degreeAnimator = ObjectAnimator.ofFloat(this, "degree", 0, 360);
        degreeAnimator.setDuration(2000);
        degreeAnimator.start();
        return super.onTouchEvent(event);
    }
}
