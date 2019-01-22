package com.ying.administrator.masterappdemo.app;

import android.app.Application;
import android.util.Log;

import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        XGPushConfig.enableDebug(this,true);
        XGPushConfig.enableOtherPush(getApplicationContext(), true);
        XGPushConfig.setHuaweiDebug(true);
        XGPushConfig.setMiPushAppId(getApplicationContext(), "ed915472a691f");
        XGPushConfig.setMiPushAppKey(getApplicationContext(), "c10d44005770d36ea1822e36748fe9ce");
        XGPushConfig.setMzPushAppId(this, "ed915472a691f");
        XGPushConfig.setMzPushAppKey(this, "c10d44005770d36ea1822e36748fe9ce");
        XGPushManager.registerPush(this, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
//token在设备卸载重装的时候有可能会变
                Log.d("TPush", "注册成功，设备token为：" + data);
            }
            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
            }
        });
        XGPushManager.bindAccount(getApplicationContext(), "XINGE");
        XGPushManager.setTag(this,"XINGE");
    }
}
