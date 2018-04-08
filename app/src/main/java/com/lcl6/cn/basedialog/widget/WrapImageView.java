package com.lcl6.cn.basedialog.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.lcl6.cn.basedialog.R;

/**
 * Created by liancl on 2018/3/13.
 */

public class WrapImageView extends android.support.v7.widget.AppCompatImageView {
    private int progrssWidth=1;
    private int progrssHeight=1;

    public WrapImageView(Context context) {
        super(context);
    }

    public WrapImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public WrapImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WrapImageView);
//        progrssWidth= typedArray.getInteger(R.styleable.WrapImageView_wi_width, 1);
    }

    public void setProgressWidth(int progress){
        this.progrssWidth=progress;
    }

    public void setProgressHeight(int progress){
        this.progrssHeight=progress;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

//        int size = MeasureSpec.getSize(widthMeasureSpec);
//        widthMeasureSpec = MeasureSpec.makeMeasureSpec(size * progrssWidth, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();

        measuredWidth=progrssWidth*measuredWidth;
        measuredHeight=progrssHeight*measuredHeight;
        setMeasuredDimension(measuredWidth,measuredHeight);
    }
}
