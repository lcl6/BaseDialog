package com.lcl6.cn.basedialog.widget.anima.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialog.constant.Constant;

/**
 * 沿着x轴旋转的控件
 * Created by liancl on 2017/9/20.
 */

public class RoatXImageView extends View{

    Paint paint=  new Paint();

    Point point = new Point(30,30);
    Camera camera = new Camera();
    private float degree;
    ObjectAnimator degreeAnimator;
    ObjectAnimator centerdegreeAnimator;
    int centerdegree;


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

    public int getCenterdegree() {
        return centerdegree;
    }

    public void setCenterdegree(int centerdegree) {
        this.centerdegree = centerdegree;
        invalidate();
    }

    {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float newZ = - displayMetrics.density * 6;
        camera.setLocation(0, 0, newZ);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float newZ = - displayMetrics.density * 6;


        boolean isUseMatrix = true;//两种写法
        Matrix matrix = new Matrix();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
        int bwidth = bitmap.getWidth();
        int bheight = bitmap.getHeight();
        int width = bwidth / 2 + point.x;
        int height = bheight / 2 + point.y;
        //画书的上半页
        canvas.save();
        canvas.clipRect(point.x, point.y, point.x+bwidth,point.y+bheight/2);
        canvas.drawBitmap(bitmap, point.x, point.y, paint);
        canvas.restore();

        //画书的下半页
        canvas.save();
        camera.save();
        camera.setLocation(0, 0, newZ);

        camera.rotateX(getDegree());//旋转角度
        if (isUseMatrix) {
            matrix.reset();
            camera.getMatrix(matrix);
            matrix.postTranslate(width, height);//这个方法表示移回零点
            matrix.preTranslate(-width, -height);//这个表示移回原点
        } else {
            //把目标移回原来的位置
            canvas.translate(width, height);
            //注意 camera和canvas结合
            camera.applyToCanvas(canvas);
        }
        camera.restore();

        if (isUseMatrix) {
            canvas.concat(matrix);
        } else {
            canvas.translate(-width, -height);//把目标移到零点
        }
        canvas.clipRect( point.x, point.y+bheight/2, point.x+bwidth,point.y+bheight);
        canvas.drawBitmap(bitmap, point.x, point.y, paint);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        degreeAnimator= ObjectAnimator.ofFloat(this, "degree", 0, 90);
        degreeAnimator.setInterpolator(new LinearInterpolator());
        degreeAnimator.setDuration(2000);
        degreeAnimator.setRepeatMode(ValueAnimator.REVERSE);
        degreeAnimator.setRepeatCount(ValueAnimator.RESTART);
        degreeAnimator.start();


//        centerdegreeAnimator= ObjectAnimator.ofInt(this, "centerdegree", 0, 360);
//        centerdegreeAnimator.setInterpolator(new LinearInterpolator());
//        centerdegreeAnimator.setDuration(2000);
//        centerdegreeAnimator.setRepeatMode(ValueAnimator.REVERSE);
//        centerdegreeAnimator.setRepeatCount(ValueAnimator.INFINITE);
//        centerdegreeAnimator.start();
        Log.e(Constant.TAG, "RoatXImageView 的onTouchEvent: " );
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(Constant.TAG, "RoatXImageView 的dispatchTouchEvent: " );
        return super.dispatchTouchEvent(ev);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
