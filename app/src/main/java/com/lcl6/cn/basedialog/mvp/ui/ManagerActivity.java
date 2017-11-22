package com.lcl6.cn.basedialog.mvp.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialog.app.App;
import com.lcl6.cn.basedialog.di.component.AppComponent;
import com.lcl6.cn.basedialog.mvp.contract.ManagerContract;
import com.lcl6.cn.basedialog.mvp.presenter.ManagerPresenter;
import com.lcl6.cn.component.base.activity.BaseMvpActivity;
import com.lcl6.cn.component.net.UrlChangeListener;
import com.lcl6.cn.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import okhttp3.HttpUrl;

/**
 * Created by liancl on 2017/8/25.
 */

public class ManagerActivity extends BaseMvpActivity<ManagerPresenter> implements ManagerContract.View {

    @BindView(R.id.et_url1)
    EditText mUrl1;
    @BindView(R.id.et_url2)
    EditText mUrl2;
    @BindView(R.id.et_url3)
    EditText mUrl3;
    @BindView(R.id.et_global_url)
    EditText mGlobalUrl;


    private ChangeListener mListener;
    AppComponent mAppComponent;

    ManagerPresenter managerPresenter;

    public static void start(Context context) {
        Intent starter = new Intent(context, ManagerActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected ManagerPresenter getPresenter() {
        managerPresenter = new ManagerPresenter();
        return managerPresenter;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_manager;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mUrl1.setSelection(mUrl1.getText().toString().length());
        mAppComponent = App.getAppComponent();
    }

    @Override
    protected void initData() {
        onLoadSuccessStatus();
    }

    @Override
    protected void initViewListener() {
        super.initViewListener();
        initListener();
    }

    private void initListener() {
        this.mListener = new ChangeListener();
    }

    @OnClick({R.id.bt_request1, R.id.bt_request2, R.id.bt_request3})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_request1:
                managerPresenter.requestGuthub(mUrl1.getText().toString());
                break;
            case R.id.bt_request2:
                managerPresenter.requestGank(mUrl2.getText().toString());
                break;
            case R.id.bt_request3:
                managerPresenter.requestDouban(mUrl3.getText().toString());
                break;
        }
    }

    // 请求默认 BaseUrl，请求的接口没有配置 DomainHeader，所以只受全局 BaseUrl的影响
    public void btnRequestDefault(View view) {
        managerPresenter.requestTabaoData();
    }

    // 设置全局替换的 BaseUrl
    public void btnSetGlobalUrl(View view) {
        managerPresenter.setGlobal(mGlobalUrl.getText().toString().trim());
    }

    // 移除全局的 BaseUrl
    public void btnRmoveGlobalUrl(View view) {
        managerPresenter.removeGlobal();
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

    @Override
    public void requestTabaoData(String message) {
        showResult(message);
    }

    @Override
    public void setGlobal(String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void removeGlobal(String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void requestGuthub(String message) {
        showResult(message);
    }

    @Override
    public void requestGank(String message) {
        showResult(message);
    }

    @Override
    public void requestDouban(String message) {
        showResult(message);
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
