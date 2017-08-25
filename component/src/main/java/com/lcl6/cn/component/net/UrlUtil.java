package com.lcl6.cn.component.net;

import okhttp3.HttpUrl;

/**
 * Created by liancl on 2017/8/25.
 */

public class UrlUtil {
    static HttpUrl checkUrl(String url) {
        HttpUrl parseUrl = HttpUrl.parse(url);
        if (null == parseUrl) {
            throw new InvalidUrlException(url);
        } else {
            return parseUrl;
        }
    }
}
