package com.lcl6.cn.basedialog.widget.anima.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialog.constant.Constant;

/**
 * 沿着x轴旋转的控件
 * Created by liancl on 2017/9/20.
 */

public class RoatXImageView extends View{



    //Y轴方向旋转角度
    private float degreeY;
    //不变的那一半，Y轴方向旋转角度
    private float fixDegreeY;
    //Z轴方向（平面内）旋转的角度
    private float degreeZ;

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
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int x = centerX - bitmapWidth / 2;
        int y = centerY - bitmapHeight / 2;
//        //画变换的一半
//        //先旋转，再裁切，再使用camera执行3D动效,**然后保存camera效果**,最后再旋转回来
        canvas.save();
        camera.save();
        canvas.translate(centerX, centerY)    ;
        //旋转270
        canvas.rotate(-degreeZ);
        //旋转-45
        camera.rotateY(degreeY);
        camera.applyToCanvas(canvas);
        //计算裁切参数时清注意，此时的canvas的坐标系已经移动
        canvas.clipRect(0, -centerY, centerX, centerY);
        canvas.rotate(degreeZ);
        canvas.translate(-centerX, -centerY);
        camera.restore();
        canvas.drawBitmap(bitmap, x, y, paint);
        canvas.restore();
        //画不变换的另一半
        canvas.save();
        camera.save();
        canvas.translate(centerX, centerY);
        canvas.rotate(-degreeZ);
        //计算裁切参数时清注意，此时的canvas的坐标系已经移动
        canvas.clipRect(-centerX, -centerY, 0, centerY);
        //此时的canvas的坐标系已经旋转，所以这里是rotateY
        camera.rotateY(fixDegreeY);
        camera.applyToCanvas(canvas);
        canvas.rotate(degreeZ);
        canvas.translate(-centerX, -centerY);
        camera.restore();
        canvas.drawBitmap(bitmap, x, y, paint);
        canvas.restore();
        Log.e(Constant.TAG, "degreeY: "+degreeY);
        Log.e(Constant.TAG, "degreeZ: "+degreeZ);
        Log.e(Constant.TAG, "fixDegreeY: "+fixDegreeY);




        Paint p1 = new Paint();
        p1.setColor(Color.RED);

        Paint p2 = new Paint();
        p2.setColor(Color.BLUE);

        canvas.translate(200,0);
        canvas.rotate(30,100,100);
        canvas.drawRect(0, 100, 150, 150, p1);   // p1 是红色画笔
        canvas.drawRect(200, 200, 250, 250, p2);   // p2 是蓝色画笔



    }


    /**
     * 启动动画之前调用，把参数reset到初始状态
     */
    public void reset() {
        degreeY = 0;
        fixDegreeY = 0;
        degreeZ = 0;
    }

    @Keep
    public void setFixDegreeY(float fixDegreeY) {
        this.fixDegreeY = fixDegreeY;
        invalidate();
    }

    @Keep
    public void setDegreeY(float degreeY) {
        this.degreeY = degreeY;
        invalidate();
    }

    @Keep
    public void setDegreeZ(float degreeZ) {
        this.degreeZ = degreeZ;
        invalidate();
    }

}
