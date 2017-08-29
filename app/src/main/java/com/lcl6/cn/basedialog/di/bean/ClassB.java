package com.lcl6.cn.basedialog.di.bean;

/**
 * Created by liancl on 2017/8/29.
 */

public class ClassB {
    private ClassA classA;
    private int a;
    public ClassB(ClassA classA, int a) {
        this.classA = classA;
        this.a = a;
    }
    public ClassA getClassA() {
        return classA;
    }

    public int getA() {
        return a;
    }
}
