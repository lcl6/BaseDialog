package com.lcl6.cn.basedialog.base.manager.observel;

import com.lcl6.cn.utils.NetworkUtils;
import com.lcl6.cn.utils.ToastUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by liancl on 2017/8/25.
 */

public abstract class RxObservable<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {
        onRxSubscribe(d);
    }
    @Override
    public void onNext(T value) {
        try{
            onRxNext(value);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Override
    public void onError(Throwable e) {
        if(!NetworkUtils.isConnected()){
            ToastUtils.showShort("网络异常，请检查网络");
        }
        onRxError(e);
    }
    @Override
    public void onComplete() {

        onRxComplete();
    }
    protected abstract void onRxComplete();
    protected abstract void onRxNext(T value);
    protected abstract void onRxSubscribe(Disposable d);
    protected abstract void onRxError(Throwable e);

}
