package com.lcl6.cn.component.net;

import android.text.TextUtils;
import android.util.Log;

import com.lcl6.cn.component.net.parser.DefaultUrlParser;
import com.lcl6.cn.component.net.parser.UrlParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liancl on 2017/8/25.
 */

public class RetrofitManager {
    private static final String TAG = "retrofitManager";

    private static final String DOMAIN_NAME = "Domain-Name";
    private static final String GLOBAL_DOMAIN_NAME = "com.lcl6.cn.component.net.globalDomainName";
    public static final String DOMAIN_NAME_HEADER = DOMAIN_NAME + ": ";
    private boolean isRun = true; //默认开始运行,可以随时停止运行,比如你在 App 启动后已经不需要在动态切换 baseurl 了
    private Map<String, HttpUrl> mDomainNameHub = new HashMap<>();
    private Interceptor mInterceptor;
    private static boolean DEPENDENCY_OKHTTP;
    private List<UrlChangeListener> mListeners = new ArrayList<>();
    private UrlParser mUrlParser;
//    private static RetrofitManager mInstance;

    static {
        boolean hasDependency;
        try {
            Class.forName("okhttp3.OkHttpClient");
            hasDependency = true;
        } catch (ClassNotFoundException e) {
            hasDependency = false;
        }
        DEPENDENCY_OKHTTP = hasDependency;
    }

    public RetrofitManager() {
        if (!DEPENDENCY_OKHTTP) { //使用本管理器必须依赖 Okhttp
            throw new IllegalStateException("Must be dependency Okhttp");
        }
        setUrlParser(new DefaultUrlParser());
        this.mInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                if (!isRun()) // 可以在 App 运行时,随时通过 setRun(false) 来结束本管理器的运行,
                    return chain.proceed(chain.request());
                return chain.proceed(processRequest(chain.request()));
            }
        };
    }


//    public static RetrofitManager getInstance() {
//        if (mInstance == null) {
//            synchronized (RetrofitManager.class) {
//                if (mInstance == null) {
//                    mInstance = new RetrofitManager();
//                }
//            }
//        }
//        return mInstance;
//    }


    /**
     * 将 {@link okhttp3.OkHttpClient.Builder} 传入,配置一些本管理器需要的参数
     */
    public OkHttpClient.Builder with(OkHttpClient.Builder builder) {
        return builder
                .addInterceptor(mInterceptor);
    }



    /**
     * 可自行实现 {@link UrlParser} 动态切换 Url 解析策略
     * @param parser
     */
    public void setUrlParser(UrlParser parser) {
        this.mUrlParser = parser;
    }
    /**
     * 对 {@link Request} 进行一些必要的加工
     *
     * @param request
     * @return
     */
    public Request processRequest(Request request) {

        Request.Builder newBuilder = request.newBuilder();

        String domainName = obtainDomainNameFromHeaders(request);

        HttpUrl httpUrl;

        // 如果有 header，获取 header 中配置的url，否则检查全局的 BaseUrl，未找到则为null
        if (!TextUtils.isEmpty(domainName)) {
            httpUrl = fetchDomain(domainName);
            newBuilder.removeHeader(DOMAIN_NAME);
        } else {
            httpUrl = fetchDomain(GLOBAL_DOMAIN_NAME);
        }

        if (null != httpUrl) {
            HttpUrl newUrl = mUrlParser.parseUrl(httpUrl, request.url());
            Log.d(RetrofitManager.TAG, "New Url is { " + newUrl.toString() + " } , Old Url is { " + request.url().toString() + " }");

            Object[] listeners = listenersToArray();
            if (listeners != null) {
                for (int i = 0; i < listeners.length; i++) {
                    ((UrlChangeListener) listeners[i]).onUrlChange(newUrl, request.url()); // 通知监听器此 Url 的 BaseUrl 已被改变
                }
            }
            return newBuilder
                    .url(newUrl)
                    .build();
        }
        return newBuilder.build();

    }

    /** * 管理器是否在运行 */
    public boolean isRun() {
        return this.isRun;
    }

    /** * 控制管理器是否运行,在每个域名地址都已经确定,不需要再动态更改时可设置为 false */
    public void setRun(boolean run) {
        this.isRun = run;
    }

    /**
     * 从 {@link Request#header(String)} 中取出 DomainName
     * @param request
     */
    private String obtainDomainNameFromHeaders(Request request) {
        List<String> headers = request.headers(DOMAIN_NAME);
        if (headers == null || headers.size() == 0)
            return null;
        if (headers.size() > 1)
            throw new IllegalArgumentException("Only one Domain-Name in the headers");
        return request.header(DOMAIN_NAME);
    }

    /**   * 取出对应 DomainName 的 Url */
    public HttpUrl fetchDomain(String domainName) {
        return mDomainNameHub.get(domainName);
    }

    private Object[] listenersToArray() {
        Object[] listeners = null;
        synchronized (mListeners) {
            if (mListeners.size() > 0) {
                listeners = mListeners.toArray();
            }
        }
        return listeners;
    }


    /**
     * 全局动态替换 BaseUrl，优先级： Header中配置的url > 全局配置的url
     * 除了作为备用的 BaseUrl ,当你项目中只有一个 BaseUrl ,但需要动态改变
     * 这种方式不用在每个接口方法上加 Header,也是个很好的选择
     */
    public void setGlobalDomain(String url) {
        synchronized (mDomainNameHub) {
            mDomainNameHub.put(GLOBAL_DOMAIN_NAME, UrlUtil.checkUrl(url));
        }
    }

    /**
     * 获取全局 BaseUrl
     */
    public HttpUrl getGlobalDomain() {
        return mDomainNameHub.get(GLOBAL_DOMAIN_NAME);
    }

    /**
     * 移除全局 BaseUrl
     */
    public void removeGlobalDomain() {
        synchronized (mDomainNameHub) {
            mDomainNameHub.remove(GLOBAL_DOMAIN_NAME);
        }
    }

    public void removeDomain(String domainName) {
        synchronized (mDomainNameHub) {
            mDomainNameHub.remove(domainName);
        }
    }

    public void clearAllDomain() {
        mDomainNameHub.clear();
    }

    public boolean haveDomain(String domainName) {
        return mDomainNameHub.containsKey(domainName);
    }

    public int domainSize() {
        return mDomainNameHub.size();
    }

    /**
     * 注册当 Url 的 BaseUrl 被改变时会被回调的监听器
     *
     * @param listener
     */
    public void registerUrlChangeListener(UrlChangeListener listener) {
        synchronized (mListeners) {
            mListeners.add(listener);
        }
    }

    /**
     * 注销当 Url 的 BaseUrl 被改变时会被回调的监听器
     *
     * @param listener
     */
    public void unregisterUrlChangeListener(UrlChangeListener listener) {
        synchronized (mListeners) {
            mListeners.remove(listener);
        }
    }

    /**
     * 存放 Domain 的映射关系
     */
    public void putDomain(String domainName, String domainUrl) {
        synchronized (mDomainNameHub) {
            mDomainNameHub.put(domainName, UrlUtil.checkUrl(domainUrl));
        }
    }
}
