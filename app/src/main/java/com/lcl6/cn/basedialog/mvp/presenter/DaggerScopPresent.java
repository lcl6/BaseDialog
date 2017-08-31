package com.lcl6.cn.basedialog.mvp.presenter;

import com.lcl6.cn.basedialog.mvp.contract.DaggerScopContract;
import com.lcl6.cn.basedialog.mvp.model.DaggerScopModel;
import com.lcl6.cn.basedialog.mvp.model.impl.DaggerScopModelImpl;
import com.lcl6.cn.component.base.mvp.presnenter.RxPresenter;

import java.util.List;

/**
 * Created by liancl on 2017/8/31.
 */

public class DaggerScopPresent extends RxPresenter<DaggerScopContract.View> implements DaggerScopContract.Present{
    DaggerScopModelImpl daggerScopModel;
    public DaggerScopPresent() {
       daggerScopModel = new DaggerScopModelImpl();
    }

    @Override
    public void getData() {
        daggerScopModel.getData(new DaggerScopModel.LoadCompleteListener() {
            @Override
            public void onComplete(List<String> list) {
                mView.showContent(list);
            }
        });
    }
}
