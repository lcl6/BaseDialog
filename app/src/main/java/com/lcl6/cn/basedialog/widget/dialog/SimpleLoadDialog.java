package com.lcl6.cn.basedialog.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.lcl6.cn.basedialog.R;

import java.lang.ref.WeakReference;

/**
 * Created by liancl on 2017/8/25.
 */

public class SimpleLoadDialog extends Handler {

    private Dialog load = null;

    private Context context;
    private boolean cancelable;
    private ProgressCancelListener mProgressCancelListener;
    private final WeakReference<Context> reference;


    public SimpleLoadDialog(Context context, ProgressCancelListener mProgressCancelListener,
                            boolean cancelable) {
        super();
        this.reference = new WeakReference<Context>(context);
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
    }

    private void create(){
        if (load == null) {
            context  = reference.get();

            load = new Dialog(context, R.style.loadstyle);
            View dialogView = LayoutInflater.from(context).inflate(
                    R.layout.custom_sload_layout, null);
            load.setCanceledOnTouchOutside(false);
            load.setCancelable(cancelable);
            load.setContentView(dialogView);
            load.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    if(mProgressCancelListener!=null)
                        mProgressCancelListener.onCancelProgress();
                }
            });
            Window dialogWindow = load.getWindow();
            dialogWindow.setGravity(Gravity.CENTER_VERTICAL
                    | Gravity.CENTER_HORIZONTAL);
        }
        if (!load.isShowing()&&context!=null) {
            load.show();
        }
    }


    public void show(){
        create();
    }


    public  void dismiss() {
        context  = reference.get();
        if (load != null&&load.isShowing()&&!((Activity) context).isFinishing()) {
            String name = Thread.currentThread().getName();
            load.dismiss();
            load = null;
        }
    }


    public interface ProgressCancelListener {
        void onCancelProgress();
    }
}
