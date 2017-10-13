package com.lcl6.cn.basedialog.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;

import com.jakewharton.disklrucache.DiskLruCache;
import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.component.base.activity.BaseActivity;
import com.lcl6.cn.component.util.LruUtils;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.util.HashSet;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liancl on 2017/10/10.
 */

public class LruActivity  extends BaseActivity{


    @BindView(R.id.img_test)
    ImageView mLruImage;


    @BindView(R.id.img_click)
    ImageView mClickImage;

    LruCache mLruCache;//内存缓存
    DiskLruCache mDiskLruInstance;//磁盘缓存

    String mImgPath="http://img.my.csdn.net/uploads/201407/26/1406383299_1976.jpg";
    private HashSet<AsyncTask> mTaskCollection;


    public static void start(Context context) {
        Intent starter = new Intent(context, LruActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected int setLayoutId() {
        return R.layout.activity_lru;
    }
    @Override
    protected void initView() {
        onLoadSuccessStatus();
        mTaskCollection = new HashSet<>();
        mLruCache = LruUtils.initMemeryCache();
        mDiskLruInstance= LruUtils.getDiskLruInstance(getContext());
    }
    @Override
    protected void initData() {
        loadBitmap(mImgPath);
    }

    private void loadBitmap(final String mImgPath) {
        Bitmap bitmapFromMemery = LruUtils.getBitmapFromMemery(mLruCache, mImgPath);
        if(bitmapFromMemery==null){
            LruUtils.getDiskBitmapTask(mDiskLruInstance, mImgPath, new LruUtils.Listener() {
                @Override
                public void onSuccess(Bitmap bitmap) {
                    mLruImage.setImageBitmap(bitmap);
                }
                @Override
                public void onNoDiskCacha() {
                    initWorkTask();
                }
            });
        }else {
            mLruImage.setImageBitmap(bitmapFromMemery);
        }
    }

    private void initWorkTask() {
        Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Bitmap> e) throws Exception {
                DiskLruCache.Snapshot snapShot = null;
                FileInputStream fileInputStream = null;
                FileDescriptor fileDescriptor = null;
                Bitmap bitmap = null;
                String key = LruUtils.hashKeyForDisk(mImgPath);
                snapShot = mDiskLruInstance.get(key);
                if (snapShot == null) {
                    // 如果没有找到对应的缓存，则准备从网络上请求数据，并写入缓存
                    LruUtils.addDiskCache(mDiskLruInstance, mImgPath);
                    snapShot = mDiskLruInstance.get(key);
                }
                if (snapShot != null) {
                    fileInputStream = (FileInputStream) snapShot.getInputStream(0);
                    fileDescriptor = fileInputStream.getFD();
                }
                // 将缓存数据解析成Bitmap对象
                if (fileDescriptor != null) {
                    bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                }
                if (bitmap != null) {
                    LruUtils.addBitmapToMemeryCache(mLruCache, mImgPath, bitmap);
                }

                if (fileDescriptor == null && fileInputStream != null) {
                    fileInputStream.close();
                }
                e.onNext(bitmap);
                e.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(@NonNull Bitmap bitmap) throws Exception {
                        mLruImage.setImageBitmap(bitmap);
                    }
                });

    }

//
//    class BitmapWorkerTask extends AsyncTask<Object, Void, Bitmap> {
//        private String imageUrl;
//        DiskLruCache.Snapshot snapShot = null;
//        private FileInputStream fileInputStream;
//        private FileDescriptor fileDescriptor;
//        Bitmap bitmap = null;
//        @Override
//        protected Bitmap doInBackground(Object... params) {
//            imageUrl = (String) params[0];
//            String key = LruUtils.hashKeyForDisk(imageUrl);
//            try {
//                snapShot = mDiskLruInstance.get(key);
//                if (snapShot == null) {
//                    // 如果没有找到对应的缓存，则准备从网络上请求数据，并写入缓存
//                    LruUtils.addDiskCache(mDiskLruInstance,imageUrl);
//                    snapShot = mDiskLruInstance.get(key);
//                }
//                if (snapShot != null) {
//                    fileInputStream = (FileInputStream) snapShot.getInputStream(0);
//                    fileDescriptor = fileInputStream.getFD();
//                }
//                // 将缓存数据解析成Bitmap对象
//                if (fileDescriptor != null) {
//                    bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
//                }
//                if(bitmap!=null){
//                    LruUtils.addBitmapToMemeryCache(mLruCache,imageUrl,bitmap);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                return null;
//            }finally {
//                if (fileDescriptor == null && fileInputStream != null) {
//                    try {
//                        fileInputStream.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            return bitmap;
//        }
//
//        @Override
//        protected void onPostExecute(Bitmap bitmap) {
//            super.onPostExecute(bitmap);
//            mTaskCollection.remove(this);
//            mLruImage.setImageBitmap(bitmap);
//        }
//    }

    @OnClick({R.id.btn_click})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_click:
                Bitmap bitmapFromMemery = LruUtils.getBitmapFromMemery(mLruCache, mImgPath);
                if(bitmapFromMemery==null){
                    return;
                }
                mClickImage.setImageBitmap(bitmapFromMemery);
                break;
        }
    }

    public void cancleAllTask(){
        if(mTaskCollection!=null){
            for (AsyncTask bitmapWorkerTask : mTaskCollection) {
                bitmapWorkerTask.cancel(false);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancleAllTask();
    }

    @Override
    protected void onPause() {
        super.onPause();
        LruUtils.fluchCache(mDiskLruInstance);
    }
}
