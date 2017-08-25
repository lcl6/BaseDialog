package com.lcl6.cn.basedialog.base.manager.observel;

import android.content.Context;

import com.lcl6.cn.basedialog.widget.dialog.SimpleLoadDialog;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


/**
 * Created by liancl on 2017/8/25.
 */

public abstract class ProgressSubscriber<T> implements SimpleLoadDialog.ProgressCancelListener, Subscriber<T> {

    private SimpleLoadDialog dialogHandler;

    public ProgressSubscriber(Context context) {
        dialogHandler = new SimpleLoadDialog(context,this,true);
    }

    @Override
    public void onSubscribe(Subscription s) {
        onRxSubscribe(s);
    }

    @Override
    public void onNext(T t) {
        onRxNext(t);
    }

    @Override
    public void onError(Throwable t) {
        onRxError(t);
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
        onRxComplete();
    }

    @Override
    public void onCancelProgress() {
        dismissProgressDialog();
    }

    /**
     * 显示Dialog
     */
    public void showProgressDialog(){
        if (dialogHandler != null) {
//            dialogHandler.obtainMessage(SimpleLoadDialog.SHOW_PROGRESS_DIALOG).sendToTarget();
            dialogHandler.show();
        }
    }

    /**
     * 隐藏Dialog
     */
    private void dismissProgressDialog(){
        if (dialogHandler != null) {
//            dialogHandler.obtainMessage(SimpleLoadDialog.DISMISS_PROGRESS_DIALOG).sendToTarget();
            dialogHandler.dismiss();
            dialogHandler=null;
        }
    }

    protected abstract void onRxComplete();
    protected abstract void onRxNext(T value);
    protected abstract void onRxSubscribe(Subscription d);
    protected abstract void onRxError(Throwable e);

}
