package com.lcl6.cn.basedialog.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

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

        //自定义员
//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setColor(0xffff00ff);
//        paint.setStrokeWidth(10);
//        paint.setAntiAlias(true);
//        canvas.drawCircle(100,100,80,paint);

        //自定义bitmap
//        Paint paint = new Paint();
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//        canvas.drawBitmap(bitmap,0,0,paint);

        //自定义矩形 并设置颜色
        paint.setColor((Color.parseColor("#009688")));


        //第一种写法
//        canvas.drawRect(30,30,100,200,paint);
//        第二种写法
//        RectF rect = new RectF();
//        rect.left=30;
//        rect.top=30;
//        rect.bottom=300;
//        rect.right=400;
//        canvas.drawRect(rect,paint);
        // 带圆角的矩形
//        canvas.drawRoundRect(rect,10,10,paint);
//       // 带圆角的矩形
//        canvas.drawRoundRect(30,30,300,300,10,10,paint);
//        paint.setColor((Color.parseColor("#129688")));
//        paint.setStrokeWidth(20);
//        canvas.drawLine(250,250,400,0,paint);
//
//        Paint paint2 = new Paint();
//        paint2.setColor((Color.parseColor("#129688")));
//        paint2.setStrokeWidth(20);
//        canvas.drawLine(400,0,650,250,paint2);

        //线性渐变
        //可以有多种位置
//        LinearGradient linearGradient = new LinearGradient(0, 0, 200, 200, new int[]{Color.BLACK, Color.GREEN, Color.BLUE}, null, Shader.TileMode.CLAMP);
////        LinearGradient linearGradient = new LinearGradient(0, 0, 200, 200, Color.parseColor("#E91E63"), Color.parseColor("#2196F3"), Shader.TileMode.MIRROR );
//        paint.setShader(linearGradient);
//        canvas.drawRect(0,0,200,200,paint);

        //辐射渐变
//        RadialGradient radialGradient = new RadialGradient(100, 100, 80, Color.RED,  Color.BLUE, Shader.TileMode.CLAMP);
//        paint.setShader(radialGradient);
//        canvas.drawCircle(100,100,100,paint);

        //扫描渐变
//        SweepGradient sweepGradient = new SweepGradient(100, 100, Color.parseColor("#E91E63"), Color.parseColor("#2196F3"));
//        paint.setShader(sweepGradient);
//        canvas.drawCircle(100,100,100,paint);

        //bitmap 着色
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.aaa);
//        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);//CLAMP  拉伸  REPEAT 重复  MIRROR  镜像
//        paint.setShader(bitmapShader);
//        canvas.drawRoundRect(10,10,300,300,10,10,paint);
//        canvas.drawCircle(100,100,100,paint);


    }
}
