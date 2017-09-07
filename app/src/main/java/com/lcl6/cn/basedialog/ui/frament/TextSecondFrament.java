package com.lcl6.cn.basedialog.ui.frament;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.component.base.frament.LazyFragment;
import com.lcl6.cn.utils.ToastUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
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
    @Override
    protected int getAbsLayoutId() {
        return R.layout.textsecond_frament;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
    }

    @Override
    protected void initData(View view) {
        super.initData(view);
    }
    @OnClick(R.id.tv_content)
    public void onCLick(View v){
        switch (v.getId()){
            case R.id.tv_content:
                ToastUtils.showShort("点击了");
                timeUser();
                break;
        }
    }

    private void timeUser() {
        Observable.intervalRange(1,5,1000,1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long time) throws Exception {
                        mTime.setText(time+"");
                    }
                });
    }

}
