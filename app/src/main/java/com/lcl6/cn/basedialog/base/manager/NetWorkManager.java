package com.lcl6.cn.basedialog.base.manager;

import android.text.TextUtils;
import android.util.Log;

import com.dl7.player.utils.NetWorkUtils;
import com.lcl6.cn.basedialog.app.App;
import com.lcl6.cn.basedialog.constant.Constant;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.lcl6.cn.basedialog.api.Api.APP_DEFAULT_DOMAIN;

/**
 * Created by liancl on 2017/8/25.
 */

public class NetWorkManager {
//    private static NetWorkManager mInstance;
    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;

//    public static NetWorkManager getInstance() {
//        if (mInstance == null) {
//            synchronized (NetWorkManager.class) {
//                if (mInstance == null) {
//                    mInstance = new NetWorkManager();
//                }
//            }
//        }
//        return mInstance;
//    }
//设缓存有效期为1天
static final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;

    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    public NetWorkManager() {

        // 指定缓存路径,缓存大小100Mb
        Cache cache = new Cache(new File(App.getAppComponent().getContext().getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);

        this.mOkHttpClient = App.getAppComponent().getRetrofitManager().with(new OkHttpClient.Builder()
                .cache(cache)
                .retryOnConnectionFailure(true)
                .addInterceptor(sRewriteCacheControlInterceptor)
                .addNetworkInterceptor(sRewriteCacheControlInterceptor)) //RetrofitUrlManager 初始化
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(new RequestInterceptor())
                .build();

        this.mRetrofit = new Retrofit.Builder()
                .baseUrl(APP_DEFAULT_DOMAIN)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//使用rxjava
                .addConverterFactory(GsonConverterFactory.create())//使用Gson
                .client(getOkHttpClient())
                .build();
    }


    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private static final Interceptor sRewriteCacheControlInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtils.isNetworkAvailable(App.getAppComponent().getContext())) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                Log.e(Constant.TAG,"no network");
            }
            Response originalResponse = chain.proceed(request);

            if (NetWorkUtils.isNetworkAvailable(App.getAppComponent().getContext())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, " + CACHE_CONTROL_CACHE)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };




    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    private class RequestInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request oldRequest = chain.request();
            Request request = oldRequest.newBuilder()
//                    .headers(Headers.of(getHeaders()))//注入头信息
//                    .url(getCommonUrl(oldRequest))// 注入通用入参
                    .build();
            logRequest(request);
            return logResponse(chain.proceed(request));
        }
    }
    /** 获取头信息 */
    private Map<String, String> getHeaders() {
        HashMap<String, String> headersMap = new HashMap<>();
        return headersMap;
    }


    /**
     * 打印请求信息日志
     * @param request 请求
     */
    private void logRequest(Request request) {
        List<String> list = request.url().pathSegments();
        Headers headers = request.headers();

        Log.i(Constant.TAG, "[" +"headers"  + "] ---> " +headers.toString());
        Log.i(Constant.TAG, "[" + list.get(list.size() - 1) + "] ---> " + request.url().toString());
        Log.i(Constant.TAG, "[" + list.get(list.size() - 1) + "] ---> " + getRequestString(request));
    }

    /**
     * 获取请求字符串
     * @param request 请求
     */
    private String getRequestString(Request request){
        try {
            final Request copy = request.newBuilder().build();
            if (copy.body() == null){
                return "";
            }
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 打印返回数据日志
     * @param response 返回数据
     */
    private okhttp3.Response logResponse(okhttp3.Response response) {
        String log = "";
        try {
            log = response.body().string();
            try {
                List<String> list = response.request().url().pathSegments();
                logSegmentedLog(list.get(list.size() - 1), log);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 打印完需要将数据重新写入，因为response.body().string()执行一次以后会将数据清空
        response = response.newBuilder()
                .body(ResponseBody.create(response.body().contentType(), log))
                .build();
        return response;
    }
    /**
     * 打印分段日志
     * @param tag 标签
     * @param log 原始日志
     */
    private void logSegmentedLog(String tag, String log) {
        synchronized (NetWorkManager.class){
            if (TextUtils.isEmpty(log) || log.length() < 3000){
                Log.d(Constant.TAG, "[" + tag + "] <--- " + log);
                return;
            }
            int index = (int) Math.ceil(log.length() / 3000.0);
            for (int i = 0; i < index; i++){
                int start = i * 3000;
                int end = 3000 + i * 3000;
                if (end >= log.length()){
                    end = log.length();
                }
                Log.d(Constant.TAG, "[" + tag + "] <--- " + log.substring(start, end));
                if (end == log.length()){
                    return;
                }
            }
        }
    }

}
