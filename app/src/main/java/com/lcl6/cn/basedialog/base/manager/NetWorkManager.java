package com.lcl6.cn.basedialog.base.manager;

import android.text.TextUtils;
import android.util.Log;

import com.lcl6.cn.basedialog.app.App;
import com.lcl6.cn.basedialog.constant.Constant;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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


    public NetWorkManager() {
        this.mOkHttpClient = App.getAppComponent().getRetrofitManager().with(new OkHttpClient.Builder()) //RetrofitUrlManager 初始化
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
                    .headers(Headers.of(getHeaders()))//注入头信息
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
