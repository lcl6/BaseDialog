package com.lcl6.cn.basedialog;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.lcl6.cn.basedialog.constant.Constant;
import com.lcl6.cn.component.base.activity.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by liancl on 2017/8/21
 */

public class WidgetActivity extends BaseActivity {

    @BindView(R.id.ed_search)
    EditText mEdSearch;
    PublishSubject<String> mPublishSubject;
    @BindView(R.id.tv_result)
    TextView tvResult;
    private CompositeDisposable mCompositeDisposable;

    public static void start(Context context) {
        Intent starter = new Intent(context, WidgetActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_widget;
    }

    @Override
    protected void initView() {
        onLoadSuccessStatus();

        mEdSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() <= 0) {
                    return;
                }
                startSearch(editable.toString());
            }
        });
        mPublishSubject = PublishSubject.create();
        mPublishSubject.debounce(200, TimeUnit.MILLISECONDS).filter(new Predicate<String>() {
            @Override
            public boolean test(@NonNull String s) throws Exception {
                return s.length() > 0;
            }
        }).switchMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull String query) throws Exception {
                return getSearchObervable(query);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<String>() {

                    @Override
                    public void onNext(String s) {
                        tvResult.setText(s);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e(Constant.TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        mCompositeDisposable = new CompositeDisposable();
        mCompositeDisposable.add(mCompositeDisposable);
    }

    private void startSearch(String query) {
        mPublishSubject.onNext(query);
    }

    private ObservableSource<String> getSearchObervable(final String query) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> observableEmitter) throws Exception {
                Log.d("SearchActivity", "开始请求，关键词为：" + query);
                try {
                    Thread.sleep(100 + (long) (Math.random() * 500));
                } catch (InterruptedException e) {
                    if (!observableEmitter.isDisposed()) {
                        observableEmitter.onError(e);
                    }
                }
                Log.d("SearchActivity", "结束请求，关键词为：" + query);
                observableEmitter.onNext("完成搜索，关键词为：" + query);
                observableEmitter.onComplete();

            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }

    }
}
