package com.ying.administrator.masterappdemo.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.blankj.utilcode.util.SPUtils;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.ying.administrator.masterappdemo.common.Config;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 主要是添加下面这句代码
        MultiDex.install(this);

        XGPushConfig.enableDebug(this,true);
        XGPushConfig.enableOtherPush(getApplicationContext(), true);
        XGPushConfig.setHuaweiDebug(true);
        XGPushConfig.setMiPushAppId(getApplicationContext(), "2882303761517939654");
        XGPushConfig.setMiPushAppKey(getApplicationContext(), "5151793996654");
        XGPushConfig.setMzPushAppId(this, "ed915472a691f");
        XGPushConfig.setMzPushAppKey(this, "c10d44005770d36ea1822e36748fe9ce");
        XGPushManager.registerPush(this, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {

//token在设备卸载重装的时候有可能会变
                Log.d("TPush", "注册成功，设备token为：" + data);

                Config.TOKEN= (String) data;
                //SPUtils spUtils = SPUtils.getInstance("token");
                //spUtils.put("Token", (String) data);
//
               // String token = spUtils.getString("Token");

               // Log.d("Token",token);
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
