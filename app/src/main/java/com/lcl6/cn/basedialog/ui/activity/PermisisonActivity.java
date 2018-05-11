package com.lcl6.cn.basedialog.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialog.constant.Constant;
import com.lcl6.cn.basedialog.util.DialogHelper;
import com.lcl6.cn.basedialog.util.OnClickEvent;
import com.lcl6.cn.basedialog.widget.ChoiceLayout;
import com.lcl6.cn.component.base.activity.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liancl on 2018/1/12.
 */

public class PermisisonActivity  extends BaseActivity{

    @BindView(R.id.tv_premission_list)
    TextView mTextPermission;

    @BindView(R.id.cl_tit)
    ChoiceLayout choiceLayout;
    @BindView(R.id.tv_click_double)
    TextView mTextClickDouble;



    public static void start(Context context) {
        Intent starter = new Intent(context, PermisisonActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_permission;
    }

    @Override
    protected void initView() {
        StringBuilder sb = new StringBuilder();
        List<String> permissions = PermissionUtils.getPermissions();
        for (String permission : permissions) {
            sb.append(permission.substring(permission.lastIndexOf('.') + 1)).append("\n");
        }
        mTextPermission.setText(sb.toString());

//        choiceLayout.

    }

    @Override
    protected void initData() {
        onLoadSuccessStatus();

        mTextClickDouble.setOnClickListener(new OnClickEvent() {
            @Override
            public void singleClick(View v) {
                Log.e(Constant.TAG, "singleClick: ");
            }
        });
    }


    @OnClick({R.id.tv_ask_photo,R.id.tv_ask_record,R.id.tv_open_setting})
    public void onClick(View view){

        switch (view.getId()){
            case R.id.tv_ask_photo :

                PermissionUtils.permission(PermissionConstants.CAMERA)
                        .rationale(new PermissionUtils.OnRationaleListener() {
                            @Override
                            public void rationale(final ShouldRequest shouldRequest) {
//                                shouldRequest.again(true);
                                DialogHelper.showRationaleDialog(shouldRequest);
                            }
                        })
                        .callback(new PermissionUtils.FullCallback() {
                            @Override
                            public void onGranted(List<String> permissionsGranted) {
//                                updateAboutPermission();
                            }

                            @Override
                            public void onDenied(List<String> permissionsDeniedForever,
                                                 List<String> permissionsDenied) {
//                                if (!permissionsDeniedForever.isEmpty()) {
//                                    DialogHelper.showOpenAppSettingDialog();
//                                }
//                                LogUtils.d(permissionsDeniedForever, permissionsDenied);
                            }
                        })
                        .request();


                break;
            case R.id.tv_ask_record :
                PermissionUtils.permission(PermissionConstants.CONTACTS)
                        .rationale(new PermissionUtils.OnRationaleListener() {
                            @Override
                            public void rationale(final ShouldRequest shouldRequest) {
                                DialogHelper.showRationaleDialog(shouldRequest);
                            }
                        })
                        .callback(new PermissionUtils.FullCallback() {
                            @Override
                            public void onGranted(List<String> permissionsGranted) {
                                Log.e(Constant.TAG, "onGranted: " );
                            }

                            @Override
                            public void onDenied(List<String> permissionsDeniedForever,
                                                 List<String> permissionsDenied) {
                                if (!permissionsDeniedForever.isEmpty()) {
                                    DialogHelper.showOpenAppSettingDialog();
                                }
                                LogUtils.d(permissionsDeniedForever, permissionsDenied);
                            }
                        })
                        .request();
                break;
            case R.id.tv_open_setting ://打开设置
                PermissionUtils.openAppSettings();
                break;
        }



    }

}
