package com.lcl6.cn.basedialog.ui.activity.evaluator;

import android.animation.TypeEvaluator;
import android.graphics.Color;

/**
 * Created by liancl on 2017/9/19.
 */

public class HsvEvaluator implements TypeEvaluator<Integer> {

    float[] startHsv =new float[3];
    float[] endHsv=new float[3];
    float[] outHsv=new float[3];

    @Override
    public Integer evaluate(float v, Integer startValue, Integer endValue) {
        //第一步 把argb转为hsv
        Color.colorToHSV(startValue,startHsv);
        Color.colorToHSV(endValue,endHsv);
        if(endHsv[0]-startHsv[0]>180){
            endHsv[0]-=360;
        }else if(endHsv[0]-startHsv[0]<-180){
            endHsv[0]+=360;
        }
        outHsv[0]=startHsv[0]+(endHsv[0]-startHsv[0])*v;

        if(outHsv[0]>360){
            outHsv[0]-=360;
        }else if(outHsv[0]<0) {
            outHsv[0]+=360;
        }
        outHsv[1]=startHsv[1]+(endHsv[1]-startHsv[1])*v;
        outHsv[2]=startHsv[2]+(endHsv[2]-startHsv[2])*v;
        //计算当前完成度对应的透明度
        int ahpa =startValue>>24+(int)((endValue>>24-startValue>>24)*v);
        //把hsv 转为色值
        return Color.HSVToColor(ahpa,outHsv);
    }
}
