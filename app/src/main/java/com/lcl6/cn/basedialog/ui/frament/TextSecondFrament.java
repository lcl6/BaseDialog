package com.lcl6.cn.basedialog.ui.frament;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialog.constant.Constant;
import com.lcl6.cn.component.base.frament.LazyFragment;
import com.lcl6.cn.component.util.MultiClickSubscribe;
import com.lcl6.cn.utils.ToastUtils;

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
    Observable<Integer> integerObservable;
    @Override
    protected int getAbsLayoutId() {
        return R.layout.textsecond_frament;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        integerObservable = Observable.create(new MultiClickSubscribe(mDoubleClickView));
        initDoubleClick();
    }

    @Override
    protected void initData(View view) {
        super.initData(view);
    }
    @OnClick({R.id.tv_content,R.id.tv_double_click})
    public void onCLick(View v){
        switch (v.getId()){
            case R.id.tv_content:
                ToastUtils.showShort("点击了");
                timeUser();
                break;
        }
    }

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

}
