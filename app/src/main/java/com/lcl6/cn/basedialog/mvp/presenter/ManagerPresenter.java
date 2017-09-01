package com.lcl6.cn.basedialog.mvp.presenter;

import com.lcl6.cn.basedialog.mvp.contract.ManagerContract;
import com.lcl6.cn.basedialog.mvp.model.ManagerModel;
import com.lcl6.cn.basedialog.mvp.model.impl.ManagerModelImpl;
import com.lcl6.cn.component.base.mvp.presnenter.RxPresenter;

/**
 * Created by liancl on 2017/9/1.
 */

public class ManagerPresenter extends RxPresenter<ManagerContract.View> implements ManagerContract.Presenter {
    ManagerModelImpl managerModel;
    public ManagerPresenter() {
        managerModel = new ManagerModelImpl();
    }

    @Override
    public void requestTabaoData() {
        managerModel.requestTabaoData(new ManagerModel.LoadCompleteListener() {
            @Override
            public void onComplete(String successMessage) {
                mView.requestTabaoData(successMessage);
            }
            @Override
            public void onFail(String errorMessage) {
                mView.requestTabaoData(errorMessage);
            }
        });
    }

    @Override
    public void setGlobal(String newUrl) {
        managerModel.setGlobal(new ManagerModel.LoadCompleteListener() {
            @Override
            public void onComplete(String successMessage) {
                mView.setGlobal(successMessage);
            }

            @Override
            public void onFail(String errorMessage) {
                mView.setGlobal(errorMessage);
            }
        },newUrl);
    }

    @Override
    public void removeGlobal() {
        managerModel.requestTabaoData(new ManagerModel.LoadCompleteListener() {
            @Override
            public void onComplete(String successMessage) {
                mView.requestTabaoData(successMessage);
            }

            @Override
            public void onFail(String errorMessage) {
                mView.requestTabaoData(errorMessage);
            }
        });
    }

    @Override
    public void requestGuthub(String newUrl) {
        managerModel.requestGuthub(new ManagerModel.LoadCompleteListener() {
            @Override
            public void onComplete(String successMessage) {
                mView.requestGuthub(successMessage);
            }

            @Override
            public void onFail(String errorMessage) {
                mView.requestGuthub(errorMessage);
            }
        },newUrl);
    }

    @Override
    public void requestGank(String newUrl) {
        managerModel.requestGank(new ManagerModel.LoadCompleteListener() {
            @Override
            public void onComplete(String successMessage) {
                mView.requestGank(successMessage);
            }
            @Override
            public void onFail(String errorMessage) {
                mView.requestGank(errorMessage);
            }
        },newUrl);
    }

    @Override
    public void requestDouban(String newUrl) {
        managerModel.requestDouban(new ManagerModel.LoadCompleteListener() {
            @Override
            public void onComplete(String successMessage) {
                mView.requestDouban(successMessage);
            }
            @Override
            public void onFail(String errorMessage) {
                mView.requestDouban(errorMessage);
            }
        },newUrl);
    }
}
