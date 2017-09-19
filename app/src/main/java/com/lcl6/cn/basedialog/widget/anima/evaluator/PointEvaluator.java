package com.lcl6.cn.basedialog.widget.anima.evaluator;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * 点
 * Created by liancl on 2017/9/19.
 */

public class PointEvaluator implements TypeEvaluator<PointF>{

    PointF pointF = new PointF();

    @Override
    public PointF evaluate(float fration, PointF startValue, PointF endValue) {
        float x= startValue.x+(endValue.x-startValue.x)*fration;
        float y = startValue.y+(endValue.y-startValue.y)*fration;
        pointF.set(x,y);
        return pointF;
    }
}
