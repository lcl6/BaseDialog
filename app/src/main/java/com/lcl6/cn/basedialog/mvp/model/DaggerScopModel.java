package com.lcl6.cn.basedialog.mvp.model;

import java.util.List;

/**
 * Created by liancl on 2017/8/31.
 */

public interface DaggerScopModel {
    void getData(LoadCompleteListener listener);
    interface LoadCompleteListener{
        void onComplete(List<String> list);
    }
}
