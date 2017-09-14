package com.lcl6.cn.component.util;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * 防止多次点击
 * Created by liancl on 2017/9/14.
 */

public class MultiClickSubscribe implements ObservableOnSubscribe<Integer> ,View.OnClickListener{
    private final View mView;
    private List<ObservableEmitter<Integer>> mObservableList= new ArrayList<>();
    private int num=0;
    public MultiClickSubscribe(View view) {
        this.mView= view;

    }

    @Override
    public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
        mObservableList.add(e);
        mView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        for (ObservableEmitter<Integer> integerObservableEmitter : mObservableList) {
            integerObservableEmitter.onNext(num++);
        }
    }
}
