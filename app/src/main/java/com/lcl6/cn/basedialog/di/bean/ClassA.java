package com.lcl6.cn.basedialog.di.bean;

/**
 * Created by liancl on 2017/8/29.
 */

public class ClassA {
    private int a;
    private int b;
//    @Inject
//    public ClassA(@Named("a") int a,@Named("b") int b) {
//        this.a = a;
//        this.b = b;
//    }


    public ClassA(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }
}
