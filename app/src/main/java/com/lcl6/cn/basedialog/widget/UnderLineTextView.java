package com.lcl6.cn.basedialog.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialog.constant.Constant;

/**
 * Created by liancl on 2017/9/12.
 */

public class UnderLineTextView extends AppCompatTextView {
    float mUnderLineHeight;

    public UnderLineTextView(Context context) {
        super(context);

    }

    public UnderLineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public UnderLineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.UnderLineTextView);
        mUnderLineHeight = typedArray.getFloat(R.styleable.UnderLineTextView_under_height, 3f);
        typedArray.recycle();
    }
    int textWidth;
    int textHeight;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        textWidth=  MeasureSpec.getSize(widthMeasureSpec);
        textHeight=  MeasureSpec.getSize(heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paintText = new Paint();
        paintText.setTextSize(60);
        canvas.drawText("你好",getMeasuredWidth() / 2,getMeasuredHeight()/2,paintText);

        Paint paintLine = new Paint();
        paintLine.setStrokeWidth(2);
        canvas.drawLine(0,mUnderLineHeight,textWidth,mUnderLineHeight,paintLine);

        Log.e(Constant.TAG, "textWidth: "+textWidth+"textHeight: "+textHeight );


    }
}
