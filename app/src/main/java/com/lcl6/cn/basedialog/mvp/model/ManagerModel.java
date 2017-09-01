package com.lcl6.cn.basedialog.mvp.model;

/**
 * Created by liancl on 2017/9/1.
 */

public interface ManagerModel {
    void requestTabaoData(LoadCompleteListener listener);
    void setGlobal(LoadCompleteListener listener,String newUrl);
    void removeGlobal(LoadCompleteListener listener);
    void requestGuthub(LoadCompleteListener listener,String newUrl);
    void requestGank(LoadCompleteListener listener,String newUrl);
    void requestDouban(LoadCompleteListener listener,String newUrl);

    interface LoadCompleteListener{
        void onComplete(String successMessage);
        void onFail(String errorMessage);
    }

}
