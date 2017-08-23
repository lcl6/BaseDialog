package com.lcl6.cn.basedialog.base.presnenter;

/**
 * Created by liancl on 2017/8/23.
 */

public abstract class BasePresenter<T> {

    T mView;
    public  void attachView(T view){
        mView=view;
    }
    public   void dechView(){
        this.mView=null;
    }
}
