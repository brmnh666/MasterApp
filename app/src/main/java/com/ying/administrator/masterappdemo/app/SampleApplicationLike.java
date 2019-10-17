package com.ying.administrator.masterappdemo.app;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
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
import com.tencent.bugly.beta.UpgradeInfo;
import com.tencent.bugly.beta.upgrade.UpgradeListener;
import com.tencent.bugly.beta.upgrade.UpgradeStateListener;
import com.tencent.tinker.entry.DefaultApplicationLike;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.UpgradeActivity;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.mvp.ui.activity.MainActivity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCardViewInflater;
import skin.support.constraint.app.SkinConstraintViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;

public class SampleApplicationLike extends DefaultApplicationLike {

    public static final String TAG = "Tinker.SampleApplicationLike";

    public SampleApplicationLike(Application application, int tinkerFlags,
                                 boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
                                 long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);

        // 安装tinker
        // TinkerManager.installTinker(this); 替换成下面Bugly提供的方法
        Beta.installTinker(this);


    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }
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
        Utils.init(getApplication());

        XGPushConfig.enableDebug(getApplication(),true);
        XGPushConfig.enableOtherPush(getApplication(), true);
        XGPushConfig.setHuaweiDebug(true);
        XGPushConfig.setMiPushAppId(getApplication(), "2882303761517939654");
        XGPushConfig.setMiPushAppKey(getApplication(), "5151793996654");
        XGPushConfig.setMzPushAppId(getApplication(), "ed915472a691f");
        XGPushConfig.setMzPushAppKey(getApplication(), "c10d44005770d36ea1822e36748fe9ce");
        XGPushManager.registerPush(getApplication(), new XGIOperateCallback() {
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
        XGPushManager.bindAccount(getApplication(), "XINGE");
        XGPushManager.setTag(getApplication(),"XINGE");

//        initUpgradeDialog();
        /**
         * 只允许在MainActivity上显示更新弹窗，其他activity上不显示弹窗; 不设置会默认所有activity都可以显示弹窗;
         */
        Beta.canShowUpgradeActs.add(MainActivity.class);
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true
        Bugly.init(getApplication(), "de9b3cd02b", true);


        UMConfigure.init(getApplication(),"5c74d740f1f55636a80003a6"
                ,"umeng", UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        //微信
        PlatformConfig.setWeixin("wxd22da3eb42259071", "dac072c98881a324665bfbaa7f7e7c76");
        //新浪
        PlatformConfig.setSinaWeibo("2062370017", "ed8cf0cbbdb756f002777c3f648e3818","http://sns.whalecloud.com");
        //QQ
        PlatformConfig.setQQZone("1108047427", "LLqcMP1nxKVS18gr");



        /*智能客服*/

        // 在APP启动时进行初始化
        String accessKey = "[NTEwMyM5YTRkMDdhNC05YmNmLTQyZTktODIyOC1lZTY4ZmUwYjczM2EjM2Y1MDdkMTAtZjcxNC00YmQ3LWIzMWUtMWZlNDNlNGM5MWIwI2FkNGMxZGE0MGM1YzVjYzU3NzA5NzVjODllYTA2NDdl]";
//        BotKitClient.getInstance().init(this, accessKey);

        SkinCompatManager.withoutActivity(getApplication())                         // 基础控件换肤初始化
                .addInflater(new SkinMaterialViewInflater())            // material design 控件换肤初始化[可选]
                .addInflater(new SkinConstraintViewInflater())          // ConstraintLayout 控件换肤初始化[可选]
                .addInflater(new SkinCardViewInflater())                // CardView v7 控件换肤初始化[可选]
                .setSkinStatusBarColorEnable(false)                     // 关闭状态栏换肤，默认打开[可选]
                .setSkinWindowBackgroundEnable(false)                   // 关闭windowBackground换肤，默认打开[可选]
                .loadSkin();


    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}