package com.lcl6.cn.component.net;

import okhttp3.HttpUrl;

/**
 * Created by liancl on 2017/8/25.
 */

public interface UrlChangeListener {
    /**
     * 当 Url 的 BaseUrl 被改变时回调
     * 调用时间是在接口请求服务器之前
     *
     * @param newUrl
     * @param oldUrl
     */
    void onUrlChange(HttpUrl newUrl, HttpUrl oldUrl);
}
