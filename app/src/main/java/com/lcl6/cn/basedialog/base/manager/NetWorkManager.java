package com.lcl6.cn.basedialog.base.manager;

import com.lcl6.cn.basedialog.api.OneApiService;
import com.lcl6.cn.basedialog.api.ThreeApiService;
import com.lcl6.cn.basedialog.api.TwoApiService;
import com.lcl6.cn.basedialog.app.App;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
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
    private OneApiService mOneApiService;
    private TwoApiService mTwoApiService;
    private ThreeApiService mThreeApiService;

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
                .build();

        this.mRetrofit = new Retrofit.Builder()
                .baseUrl(APP_DEFAULT_DOMAIN)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//使用rxjava
                .addConverterFactory(GsonConverterFactory.create())//使用Gson
                .client(mOkHttpClient)
                .build();

        this.mOneApiService = mRetrofit.create(OneApiService.class);
        this.mTwoApiService = mRetrofit.create(TwoApiService.class);
        this.mThreeApiService = mRetrofit.create(ThreeApiService.class);
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public OneApiService getOneApiService() {
        return mOneApiService;
    }

    public TwoApiService getTwoApiService() {
        return mTwoApiService;
    }

    public ThreeApiService getThreeApiService() {
        return mThreeApiService;
    }

}
