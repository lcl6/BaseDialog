package com.lcl6.cn.basedialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lcl6.cn.basedialog.base.manager.NetWorkManager;
import com.lcl6.cn.component.net.RetrofitManager;
import com.lcl6.cn.component.net.UrlChangeListener;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.ResponseBody;

import static com.lcl6.cn.basedialog.api.Api.DOUBAN_DOMAIN_NAME;
import static com.lcl6.cn.basedialog.api.Api.GANK_DOMAIN_NAME;
import static com.lcl6.cn.basedialog.api.Api.GITHUB_DOMAIN_NAME;

/**
 * Created by liancl on 2017/8/25.
 */

public class ManagerActivity extends AppCompatActivity {

    @BindView(R.id.et_url1)
     EditText mUrl1;
    @BindView(R.id.et_url2)
     EditText mUrl2;
    @BindView(R.id.et_url3)
     EditText mUrl3;
    @BindView(R.id.et_global_url)
    EditText mGlobalUrl;

    private ProgressDialog mProgressDialog;
    private ChangeListener mListener;

    public static void start(Context context) {
        Intent starter = new Intent(context, ManagerActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        ButterKnife.bind(this);

        mProgressDialog = new ProgressDialog(this);
        mUrl1.setSelection(mUrl1.getText().toString().length());
        initListener();
    }

    private void initListener() {
        this.mListener = new ChangeListener();
        //如果有需要可以注册监听器,当一个 Url 的 BaseUrl 被新的 Url 替代,则会回调这个监听器,调用时间是在接口请求服务器之前
        RetrofitManager.getInstance().registerUrlChangeListener(mListener);
        //如果你已经确定了最终的 BaseUrl ,不需要在动态改变 BaseUrl ,请 RetrofitUrlManager.getInstance().setRun(false);



    }

    @OnClick({R.id.bt_request1, R.id.bt_request2,R.id.bt_request3})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_request1:
                HttpUrl httpUrl = RetrofitManager.getInstance().fetchDomain(GITHUB_DOMAIN_NAME);
                if (httpUrl == null || !httpUrl.toString().equals(mUrl1.getText().toString())) { //可以在 App 运行时随意切换某个接口的 BaseUrl
                    RetrofitManager.getInstance().putDomain(GITHUB_DOMAIN_NAME, mUrl1.getText().toString());
                }
                NetWorkManager
                        .getInstance()
                        .getOneApiService()
                        .getUsers(1, 10)
                        .compose(ManagerActivity.this.<ResponseBody>getDefaultTransformer())
                        .subscribe(getDefaultObserver());
                break;
            case R.id.bt_request2:
                HttpUrl httpUrl2 = RetrofitManager.getInstance().fetchDomain(GANK_DOMAIN_NAME);
                if (httpUrl2 == null || !httpUrl2.toString().equals(mUrl2.getText().toString())) { //可以在 App 运行时随意切换某个接口的 BaseUrl
                    RetrofitManager.getInstance().putDomain(GANK_DOMAIN_NAME, mUrl2.getText().toString());
                }
                NetWorkManager
                        .getInstance()
                        .getTwoApiService()
                        .getData(10, 1)
                        .compose(ManagerActivity.this.<ResponseBody>getDefaultTransformer())
                        .subscribe(getDefaultObserver());
                break;
            case R.id.bt_request3:

                HttpUrl httpUrl3 = RetrofitManager.getInstance().fetchDomain(DOUBAN_DOMAIN_NAME);
                if (httpUrl3 == null || !httpUrl3.toString().equals(mUrl3.getText().toString())) { //可以在 App 运行时随意切换某个接口的 BaseUrl
                    RetrofitManager.getInstance().putDomain(DOUBAN_DOMAIN_NAME, mUrl3.getText().toString());
                }
                NetWorkManager
                        .getInstance()
                        .getThreeApiService()
                        .getBook(1220562)
                        .compose(ManagerActivity.this.<ResponseBody>getDefaultTransformer())
                        .subscribe(getDefaultObserver());
                break;
        }
    }

    // 请求默认 BaseUrl，请求的接口没有配置 DomainHeader，所以只受全局 BaseUrl的影响
    public void btnRequestDefault(View view) {
        NetWorkManager
                .getInstance()
                .getOneApiService()
                .requestDefault()
                .compose(this.<ResponseBody>getDefaultTransformer())
                .subscribe(getDefaultObserver());
    }

    // 设置全局替换的 BaseUrl
    public void btnSetGlobalUrl(View view) {
        //当你项目中只有一个 BaseUrl ,但需要动态改变,全局 BaseUrl 显得非常方便
        HttpUrl httpUrl = RetrofitManager.getInstance().getGlobalDomain();
        if (null == httpUrl || !httpUrl.toString().equals(mGlobalUrl.getText().toString().trim()))
            RetrofitManager.getInstance().setGlobalDomain(mGlobalUrl.getText().toString().trim());

        Toast.makeText(getApplicationContext(), "全局替换baseUrl成功", Toast.LENGTH_SHORT).show();
    }

    // 移除全局的 BaseUrl
    public void btnRmoveGlobalUrl(View view) {
        //不想再使用全局 BaseUrl ,想用之前传入 Retrofit 的默认 BaseUrl ,就Remove
        RetrofitManager.getInstance().removeGlobalDomain();
        Toast.makeText(getApplicationContext(), "移除了全局baseUrl", Toast.LENGTH_SHORT).show();
    }


    private Observer<ResponseBody> getDefaultObserver() {
        return new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody response) {
                try {
                    String string = response.string();
                    Log.d("test", string);
                    showResult(string);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
                showResult(throwable.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
    }

    private void showResult(String result) {
        new AlertDialog.Builder(this)
                .setMessage(result)
                .setCancelable(true)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    private <T> ObservableTransformer<T, T> getDefaultTransformer() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                mProgressDialog.show();
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doAfterTerminate(new Action() {
                            @Override
                            public void run() throws Exception {
                                mProgressDialog.dismiss();
                            }
                        });
            }
        };
    }


    class ChangeListener implements UrlChangeListener {

        @Override
        public void onUrlChange(final HttpUrl newUrl, HttpUrl oldUrl) {
            Observable.just(1)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object o) throws Exception {
                            Toast.makeText(getApplicationContext(), "The newUrl is { " + newUrl.toString() + " }", Toast.LENGTH_LONG).show();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            throwable.printStackTrace();
                        }
                    });
        }
    }
}
