package com.lcl6.cn.basedialog.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.component.base.activity.BaseActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liancl on 2017/12/25.
 */

public class TestNActivity extends BaseActivity {

    String path = "/storage/emulated/0/DCIM/Camera/OCR_Temp.jpg";

    @BindView(R.id.img_avatar)
    ImageView mImageView;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_test_n;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, TestNActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        onLoadSuccessStatus();
    }

    @OnClick(R.id.bt_open_photo)
    public void onCliclk(View view) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            File file = new File(path);
            Uri photoURI = FileProvider.getUriForFile(getContext(), "com.lcl6.cn.basedialog.fileprovider", file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(photoURI, "image/*");
        } else {
            Uri uri = Uri.parse("file://" + path);
            intent.setDataAndType(uri, "image/*");
        }
        intent.putExtra("crop", "true");
        intent.putExtra("outputX", 80);
        intent.putExtra("outputY", 80);
        intent.putExtra("return-data", true);
        this.startActivityForResult(intent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Bitmap photo = data.getParcelableExtra("data");
            mImageView.setImageBitmap(photo);
        }

    }
}
