package com.lcl6.cn.basedialog.api;

import com.lcl6.cn.basedialog.bean.VideoInfo;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

import static com.lcl6.cn.component.net.RetrofitManager.DOMAIN_NAME_HEADER;

/**
 * Created by jess on 18/07/2017 17:01
 * Contact with jess.yan.effort@gmail.com
 */

public interface Api {
    String APP_DEFAULT_DOMAIN = "http://jessyan.me";

    String APP_GETHUB_DOMAIN = "https://api.github.com";
    String APP_GANK_DOMAIN = "http://gank.io";
    String APP_DOUBAN_DOMAIN = "https://api.douban.com";

    String GITHUB_DOMAIN_NAME = "github";
    String GANK_DOMAIN_NAME = "gank";
    String DOUBAN_DOMAIN_NAME = "douban";
    String VIDEO_DOMAIN_NAME = "video";
    /**视频的baseurl*/
     String NEWS_HOST = "http://c.3g.163.com/";
    /**
     * 获取视频列表
     * eg: http://c.3g.163.com/nc/video/list/V9LG4B3A0/n/10-10.html
     *
     * @param id  video ID
     * @param startPage 起始页码
     * @return
     */
    // 避免出现 HTTP 403 Forbidden，参考：http://stackoverflow.com/questions/13670692/403-forbidden-with-java-but-not-web-browser
    String AVOID_HTTP403_FORBIDDEN = "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11";

    @Headers({DOMAIN_NAME_HEADER + VIDEO_DOMAIN_NAME,AVOID_HTTP403_FORBIDDEN})
    @GET("nc/video/list/{id}/n/{startPage}-10.html")
    Observable<Map<String, List<VideoInfo>>> getVideoList(@Path("id") String id,
                                                          @Path("startPage") int startPage);


}
