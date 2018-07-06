package com.example.lenovo.base.base;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.example.lenovo.base.utils.ActivityCollector;

import java.util.ArrayList;
import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2018/7/6 12:56
 */
public class BaseActivity extends AppCompatActivity {

    public static final int PERMISSION_REQUEST_CODE = 1;

    private static PermissionListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    public static void requestRuntimePermissions(String[] permisssions, PermissionListener permissionListener) {
        Activity topActivity = ActivityCollector.getTopActivity();
        if (null == topActivity) {
            return;
        }
        listener = permissionListener;
        List<String> permissionList = new ArrayList<>();

        //检查没有允许的权限
        for (String permission : permisssions) {
            if (ActivityCompat.checkSelfPermission(ActivityCollector.getTopActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }

        //申请权限
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(ActivityCollector.getTopActivity(), permissionList.toArray(new String[permissionList.size()]), PERMISSION_REQUEST_CODE);
        } else {
            //所有权限已允许，回调权限结果
            listener.onAllGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    //统计被允许的和被拒绝的权限

                    //被允许的权限
                    List<String> grantedPermissions = new ArrayList<>();
                    //被拒绝的权限
                    List<String> deniedPermissions = new ArrayList<>();

                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissions.add(permission);
                        } else {
                            grantedPermissions.add(permission);
                        }
                    }
                    //回调权限结果
                    if (deniedPermissions.isEmpty()) {
                        listener.onAllGranted();
                    } else {
                        listener.onGranted(grantedPermissions);
                        listener.onDenied(deniedPermissions);
                    }
                }
                break;
            default:
                break;
        }
    }

    interface PermissionListener {

        //所有权限已准许
        void onAllGranted();

        //权限已准许
        void onGranted(List<String> grantedPermissions);

        //权限被拒绝
        void onDenied(List<String> deniedPermissions);
    }

}
