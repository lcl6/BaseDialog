package com.lcl6.cn.basedialog.mvp.contract;

import com.lcl.greendao.bean.DanmakuInfo;
import com.lcl6.cn.basedialog.bean.VideoInfo;
import com.lcl6.cn.component.base.mvp.presnenter.BasePresenter;
import com.lcl6.cn.component.base.mvp.view.BaseView;

import java.io.InputStream;

/**
 * Created by liancl on 2017/11/28.
 */

public interface DanmukuContract {
    interface View extends BaseView{

        /**
         * 获取Video数据
         * @param data 数据
         */
        void loadData(VideoInfo data);

        /**
         * 获取弹幕数据
         * @param inputStream 数据
         */
        void loadDanmakuData(InputStream inputStream);
    }

    interface Present extends BasePresenter<View>{
        /**
         * 添加一条弹幕到数据库
         * @param danmakuInfo
         */
        void addDanmaku(DanmakuInfo danmakuInfo);

        /**
         * 清空该视频所有缓存弹幕
         */
        void cleanDanmaku();
    }

}
