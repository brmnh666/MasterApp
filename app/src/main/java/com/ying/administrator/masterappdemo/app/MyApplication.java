package com.ying.administrator.masterappdemo.app;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.ying.administrator.masterappdemo.common.Config;

public class MyApplication extends Application {
    static {//static 代码段可以防止内存泄露
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                MaterialHeader header=new MaterialHeader(context);
                header.setPrimaryColors(Color.parseColor("#00000000"));
                header.setShowBezierWave(true);
                layout.setEnableHeaderTranslationContent(false);
                return header;//指定为经典Header，默认是 贝塞尔雷达Header
//                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate).setFinishDuration(0);
            }
        });
    }
    @Override
    public void onCreate() {
        super.onCreate();


        Log.d("====>","application启动了");
        // 主要是添加下面这句代码
        MultiDex.install(this);
        Utils.init(this);

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


        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true
        Bugly.init(this, "de9b3cd02b", true);


        UMConfigure.init(this,"5c74d740f1f55636a80003a6"
                ,"umeng", UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        //微信
        PlatformConfig.setWeixin("wxd22da3eb42259071", "dac072c98881a324665bfbaa7f7e7c76");
        //新浪
        PlatformConfig.setSinaWeibo("2062370017", "ed8cf0cbbdb756f002777c3f648e3818","http://sns.whalecloud.com");
        //QQ
        PlatformConfig.setQQZone("1108047427", "LLqcMP1nxKVS18gr");
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);


        // 安装tinker
        Beta.installTinker();
    }

}
