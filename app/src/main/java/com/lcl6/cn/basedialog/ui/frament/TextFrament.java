package com.lcl6.cn.basedialog.ui.frament;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.component.base.frament.LazyFragment;

import butterknife.BindView;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by liancl on 2017/9/6.
 */

public class TextFrament extends LazyFragment {

    @BindView(R.id.tv_send)
    TextView mSend;

    public static TextFrament newInstance(PublishSubject<String> publishSubject ) {
        Bundle args = new Bundle();
        TextFrament fragment = new TextFrament();
//        args.putParcelable("",publishSubject);
//        args.putSerializable("publishsubject",publishSubject);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getAbsLayoutId() {
        return R.layout.frament_text;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
//        mPublishSubject.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(@NonNull String s) throws Exception {
//
//                    }
//                });
    }
}
