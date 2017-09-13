package com.lcl6.cn.basedialog.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

/**
 * Created by liancl on 2017/8/21
 */

public class CircleText extends android.support.v7.widget.AppCompatTextView implements Runnable{
    public CircleText(Context context) {
        super(context);
        init();
    }

    public CircleText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
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
//        paint.setColor((Color.parseColor("#009688")));


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
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avater);
//        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);//CLAMP  拉伸  REPEAT 重复  MIRROR  镜像
//        paint.setShader(bitmapShader);
//        canvas.drawRoundRect(10,10,300,300,20,20,paint);
//        canvas.drawCircle(100,100,100,paint);

        //混合着色
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.composites);
//        Shader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//
//        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.composited);
//        Shader shader2 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//
//        Shader composeShader = new ComposeShader(bitmapShader, shader2, PorterDuff.Mode.DST_OUT);
//        paint.setShader(composeShader);
//        canvas.drawCircle(300, 300, 300, paint);
//        canvas.drawRoundRect(10,10,300,300,20,20,paint);
//        canvas.drawCircle(100,100,100,paint);

        //

//        Bitmap rectBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.composites);
////        Paint paint1 = new Paint();
////        canvas.drawBitmap(rectBitmap,0,0,paint1);
//
//        Bitmap circleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.composited);
////        Paint paint2 = new Paint();
////        canvas.drawBitmap(circleBitmap,0,0,paint2);
//        int saved = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
//        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
//        canvas.drawBitmap(rectBitmap, 0, 0, paint); // 画方
//        paint.setXfermode(xfermode); // 设置 Xfermode
//        canvas.drawBitmap(circleBitmap, 0, 0, paint); // 画圆
//        paint.setXfermode(null); // 用完及时清除 Xfermode
//        canvas.restoreToCount(saved);

        //图形的轮廓设置效果
//        PathEffect dashPathEffect = new DashPathEffect(new float[]{10, 5}, 10);
//        paint.setPathEffect(dashPathEffect);
//        paint.setColor(Color.BLUE);
//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawCircle(300, 300, 300, paint);

        //把所有的直角变为拐角
//        Path path = new Path();
//        path.lineTo(300,300);
//        path.lineTo(400,300);
//        path.lineTo(400,500);
//        paint.setColor(Color.BLUE);
//        paint.setStrokeWidth(10);
//        paint.setStyle(Paint.Style.STROKE);
//        CornerPathEffect cornerPathEffect = new CornerPathEffect(20);
//        paint.setPathEffect(cornerPathEffect);
//        canvas.drawPath(path,paint);


//        //把线条随机的偏离
//        Path path = new Path();
//        path.lineTo(300,300);
//        path.lineTo(400,300);
//        path.lineTo(400,500);
//        paint.setColor(Color.BLUE);
//        paint.setStrokeWidth(10);
//        paint.setStyle(Paint.Style.STROKE);
//        PathEffect cornerPathEffect = new DiscretePathEffect(20,5);
//        paint.setPathEffect(cornerPathEffect);
//        canvas.drawPath(path,paint);

//        Path path = new Path();
//        path.lineTo(300,300);
//        path.lineTo(400,300);
//        path.lineTo(400,500);
//        paint.setColor(Color.BLUE);
//        paint.setStrokeWidth(10);
//        paint.setStyle(Paint.Style.STROKE);
//        PathEffect cornerPathEffect = new DashPathEffect(new float[]{20, 10, 5, 10}, 0);
//        paint.setPathEffect(cornerPathEffect);
//        canvas.drawPath(path,paint);

        //设置线头的形状
//        paint.setStrokeCap(Paint.Cap.SQUARE);
//        paint.setStrokeWidth(20);
//        paint.setColor(Color.parseColor("#ff0000"));
//        canvas.drawLine(0,0,200,200,paint);

        //设置拐角的形状
//        Path path = new Path();
//        path.lineTo(200,200);
//        path.lineTo(400,0);
//        paint.setColor(Color.parseColor("#ff0000"));
//        paint.setStrokeWidth(30);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeJoin(Paint.Join.ROUND);
//        canvas.drawPath(path,paint);

//        //给文字加层阴影 在文字下方附加效果
//        paint.setShadowLayer(10,10,10, Color.RED);
//        paint.setTextSize(60);
//        canvas.drawText("你好文字",80,300,paint);





////        //给图片加层阴影 在文字上方附加效果
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avater);
//        //SOLID  在外层产生一层阴影效果而不影响图像本身
//        //NORMAL 整个图片周围出项阴影效果
//        //OUTER  原本的图片变透明 外界产生一层阴影
//        //INNER  可以看见阴影在色块的内部，有种淡淡的浮雕感。
//        paint.setMaskFilter(new BlurMaskFilter(80, BlurMaskFilter.Blur.INNER));
//        setLayerType(LAYER_TYPE_SOFTWARE, null);
////        paint.setTextSize(60);
////        canvas.drawText("你好文字",80,300,paint);
//        canvas.drawBitmap(bitmap,100,100,paint);


//////        //给图片加层阴影 在文字上方附加效之浮雕效果
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avater);
//        //   float[] direction 光源的方向 , float ambient 环境光的强度（0-1）, float specular 炫光的系数, float blurRadius 应用光线的范围,
//        paint.setMaskFilter(new EmbossMaskFilter(new float[]{0,1,1},0.2f,60,20));
//        setLayerType(LAYER_TYPE_SOFTWARE, null);
////        paint.setTextSize(60);
////        canvas.drawText("你好文字",80,300,paint);
//        canvas.drawBitmap(bitmap,100,100,paint);


        //设置中间的横线
//        paint.setStrikeThruText(true);
        //设置下划线
        paint.setUnderlineText(true);
        paint.setTextSize(60);
        String text = getText().toString();
        int length = text.length();
        float advance = paint.getRunAdvance(text, 0, length, 0, length, false, length);
        int offsetX=100;
        int offsetY=200;
        canvas.drawText(text, offsetX, offsetY, paint);
        canvas.drawLine(offsetX + advance, offsetY - 50, offsetX + advance, offsetY + 10, paint);




    }

    @Override
    public void run() {
    }
}
