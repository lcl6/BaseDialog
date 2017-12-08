package com.lcl6.cn.component.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.LruCache;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * 图片缓存的工具类
 * Created by liancl on 2017/10/9.
 */

public class LruUtils {

    private static DiskLruCache mDiskCache;
    private static final int DISK_CACHE_SIZE = 1024 * 1024 * 10; // 10MB 磁盘缓存的大小为10M
    private static final String DISK_CACHE_SUBDIR = "bitmap"; // 这里我们缓存的类型是bitmap

    /**
     * 初始化内存缓存
     * */
    public static LruCache initMemeryCache(){
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 4;
        return new LruCache<String, Bitmap>(cacheSize) {
            @SuppressLint("NewApi")
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }

            @Override
            protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
                super.entryRemoved(evicted, key, oldValue, newValue);
            }
        };
    }

    /***
     * 获取磁盘缓存
     */
    public static DiskLruCache getDiskLruInstance(Context c){
        if(mDiskCache==null){
            initCache(c);
        }
        return mDiskCache;
    }

    private static void initCache(Context c) {
        try {
            File cacheDir = getDiskCacheDir(c, DISK_CACHE_SUBDIR);
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            mDiskCache = DiskLruCache.open(cacheDir, getAppVersion(c), 1, DISK_CACHE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @description 获得磁盘缓存的路径
     *              当SD卡存在或SD卡不可被移除的时，就用getExternalCacheDir()方法来获取缓存路径，否则就调用getCacheDir()方法来获取缓存路径。
     *              getExternalCacheDir()-> /sdcard/Android/data/<application
     *              package>/cache getCacheDir() -> /data/data/<application package>/cache
     * @param context
     * @param uniqueName 缓存文件夹名称，可以自定义。这里我们缓存的是bitmap，所以缓存文件夹名定义为：bitmap
     * @return
     */
    private static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }
    /**
     * @description 得到程序的版本号
     * 注意：每当版本号改变，缓存路径下存储的所有数据都会被清除掉
     *
     * @param context
     * @return
     */
    private static int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /***
     * 将图片保存到bitmap中
     * @param lruCache 内存缓存
     * @param key key
     * @param bitmap bitmap
     */
    public static void addBitmapToMemeryCache(LruCache lruCache, String key, Bitmap bitmap){
        if(getBitmapFromMemery(lruCache,key)==null){
            String newkey = hashKeyForDisk(key);
            lruCache.put(newkey,bitmap);
        }
    }

    /**
     *从内存中取bitmap
     */
    public static Bitmap getBitmapFromMemery(LruCache lruCache, String key){
        if(lruCache==null){
            return null;
        }
        String newKey = hashKeyForDisk(key);
        return (Bitmap) lruCache.get(newKey);
    }

    /**
     * 使用MD5算法对传入的key进行加密并返回。
     */
    public static String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }
    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 将缓存记录同步到journal文件中。
     */
    public static void fluchCache(DiskLruCache mDiskCache) {
        if (mDiskCache != null) {
            try {
                mDiskCache.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean writeStream(Bitmap b, OutputStream outputStream) {
        if(b==null){
            return false;
        }
        b.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        try {
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 获取磁盘图片  在activity中调用  防止内存泄漏
     * @param diskLruCache 磁盘缓存
     * @param imgKey 图片的key
     * @param listener 回调
     */
    public static void getDiskBitmapTask(final DiskLruCache diskLruCache, final String imgKey, final Listener listener){
        Observable<Bitmap> bitmapObservable = getDiskBitmapObservable(diskLruCache, imgKey);
        bitmapObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull Bitmap bitmap) {
                        listener.onSuccess(bitmap);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        listener.onNoDiskCacha();
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }


    /***
     * 获取bitmap observerable
     * @param diskLruCache 磁盘缓存
     * @param imgKey 图片的key
     */
    public static Observable<Bitmap>  getDiskBitmapObservable( final DiskLruCache diskLruCache, final String imgKey){
        return Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Bitmap> e) throws Exception {
                String key = hashKeyForDisk(imgKey); // 通过md5加密了这个URL，生成一个key
                Bitmap bitmap = null;
                DiskLruCache.Snapshot snapShot = diskLruCache.get(key);
                if (snapShot != null) {
                    InputStream is = snapShot.getInputStream(0);
                    bitmap = BitmapFactory.decodeStream(is);
                }
                if (bitmap == null) {
                    e.onError(new Throwable("bitmap is null "));
                    e.onComplete();
                    return;
                }
                e.onNext(bitmap);
                e.onComplete();
            }
        });
    }

    public interface Listener{
        void onSuccess(Bitmap bitmap);
        void onNoDiskCacha();
    }

    /**
     * 建立HTTP请求，并获取Bitmap对象。
     *            图片的URL地址
     * @return 解析后的Bitmap对象
     */
    public static boolean downloadUrlToStream(String urlString, OutputStream outputStream) {
        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            out = new BufferedOutputStream(outputStream, 8 * 1024);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 添加到磁盘中
     * @param mDiskLruInstance 磁盘缓存
     * @param imageUrl 图片url
     */
    public static void addDiskCache(DiskLruCache mDiskLruInstance,String imageUrl){
        DiskLruCache.Editor editor = null;
        OutputStream outputStream = null;
        try {
            String key = hashKeyForDisk(imageUrl);
            editor = mDiskLruInstance.edit(key);
            if (editor != null) {
                outputStream = editor.newOutputStream(0);
                if (LruUtils.downloadUrlToStream(imageUrl, outputStream)) {
                    editor.commit();
                } else {
                    editor.abort();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 添加到磁盘中
     * @param mDiskLruInstance 磁盘缓存
     * @param imageUrl 图片url
     */
    public static void addDiskCache(DiskLruCache mDiskLruInstance,String imageUrl,Bitmap  bitmap){
        DiskLruCache.Editor editor = null;
        OutputStream outputStream = null;
        try {
            String key = hashKeyForDisk(imageUrl);
            editor = mDiskLruInstance.edit(key);
            if (editor != null) {
                outputStream = editor.newOutputStream(0);
                if (writeStream(bitmap,outputStream)) {
                    editor.commit();
                } else {
                    editor.abort();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
