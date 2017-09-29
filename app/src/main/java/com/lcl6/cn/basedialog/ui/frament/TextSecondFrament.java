package com.lcl6.cn.basedialog.ui.frament;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialog.constant.Constant;
import com.lcl6.cn.component.base.frament.LazyFragment;
import com.lcl6.cn.component.util.MultiClickSubscribe;
import com.lcl6.cn.utils.ToastUtils;
import com.lcl6.cn.utils.time.CountDownTimerUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 *
 * Created by liancl on 2017/9/6.
 */

public class TextSecondFrament extends LazyFragment {

    public static TextSecondFrament newInstance() {
        Bundle args = new Bundle();
        TextSecondFrament fragment = new TextSecondFrament();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_time)
    TextView mTime;
    @BindView(R.id.tv_double_click)
    TextView mDoubleClickView;
    @BindView(R.id.tv_click_time)
    TextView mTimerClickView;
    @BindView(R.id.tv_click_first)
    TextView mFirstView;

    Observable<Integer> integerObservable;

    CountDownTimer mCountDownTimer;
    PublishSubject<String> objectPublishSubject;
    @Override
    protected int getAbsLayoutId() {
        return R.layout.textsecond_frament;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        integerObservable = Observable.create(new MultiClickSubscribe(mDoubleClickView));
        initDoubleClick();
        objectPublishSubject = PublishSubject.create();
        initFirstThrottlr();

    }

    private void initFirstThrottlr() {

        objectPublishSubject.throttleFirst(1000,TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.e(Constant.TAG, "accept: "+s );
                        mFirstView.setText(s);
                    }
                });

    }

    @Override
    protected void initData(View view) {
        super.initData(view);
    }
    @OnClick({R.id.tv_content,R.id.tv_click_time,R.id.tv_click_first})
    public void onCLick(View v){
        switch (v.getId()){
            case R.id.tv_content:
                ToastUtils.showShort("点击了");
                timeUser();
                break;

            case R.id.tv_click_time:
                mCountDownTimer= CountDownTimerUtil.getTimer(5000, 1000,new CountDownTimerUtil.Listener() {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        mTimerClickView.setText((int)(millisUntilFinished/1000) + "s");
                    }

                    @Override
                    public void onFinish() {
                        mTimerClickView.setText("点击继续倒计时");
                    }
                });
                mCountDownTimer.start();
                break;
            case R.id.tv_click_first:
                isTouch=!isTouch;
                if(isTouch){
                    for (int i = 0; i < 10; i++) {
                        Log.e(Constant.TAG, "onCLick: "+i );
                        objectPublishSubject.onNext("点击一次"+i);
                    }
                }else {
                    objectPublishSubject.onNext("点击一次");
                }


                break;
        }
    }

    boolean isTouch;
    private void initDoubleClick() {
        Observable<Integer> debounce = integerObservable.debounce(1000, TimeUnit.MILLISECONDS);
        integerObservable.buffer(integerObservable.debounce(1000,TimeUnit.MILLISECONDS))
                .map(new Function<List<Integer>, Integer>() {
                    @Override
                    public Integer apply(@NonNull List<Integer> integers) throws Exception {
                        return integers.size();
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                Log.e(Constant.TAG,integer+"次数");
            }
        });


    }

    private void timeUser() {
        Observable.intervalRange(1,5,1000,1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long time) throws Exception {
                        ToastUtils.showShort("点击了");
                        mTime.setText(time+"");
                    }
                });
    }

    @Override
    public void onDestroy() {
        CountDownTimerUtil.destoryTimer(mCountDownTimer);
        super.onDestroy();

    }
}
