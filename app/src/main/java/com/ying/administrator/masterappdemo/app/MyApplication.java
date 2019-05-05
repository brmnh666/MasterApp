package com.ying.administrator.masterappdemo.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.paradigm.botkit.BotKitClient;
import com.paradigm.botkit.ChatActivity;
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
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.UpgradeActivity;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.mvp.ui.activity.MainActivity;

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

//        initUpgradeDialog();
        /**
         * 只允许在MainActivity上显示更新弹窗，其他activity上不显示弹窗; 不设置会默认所有activity都可以显示弹窗;
         */
        Beta.canShowUpgradeActs.add(MainActivity.class);
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



        /*智能客服*/

        // 在APP启动时进行初始化
        String accessKey = "[NTEwMyM5YTRkMDdhNC05YmNmLTQyZTktODIyOC1lZTY4ZmUwYjczM2EjM2Y1MDdkMTAtZjcxNC00YmQ3LWIzMWUtMWZlNDNlNGM5MWIwI2FkNGMxZGE0MGM1YzVjYzU3NzA5NzVjODllYTA2NDdl]";
        BotKitClient.getInstance().init(this, accessKey);

    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);


        // 安装tinker
        Beta.installTinker();
    }
    private void initUpgradeDialog() {
        /**
         * 自定义初始化开关
         */
        Beta.autoInit = true;
        /**
         * true表示初始化时自动检查升级; false表示不会自动检查升级,需要手动调用Beta.checkUpgrade()方法;
         */
        Beta.autoCheckUpgrade = true;
        Beta.enableNotification =true;

        /**
         * 设置升级检查周期为60s(默认检查周期为0s)，60s内SDK不重复向后台请求策略);
         */
//        Beta.upgradeCheckPeriod = 60 * 1000;
        /**
         * 设置启动延时为1s（默认延时3s），APP启动1s后初始化SDK，避免影响APP启动速度;
         */
        Beta.initDelay = 1 * 1000;
        /**
         * 设置通知栏大图标，largeIconId为项目中的图片资源;
         */
        Beta.largeIconId = R.drawable.icon;
        /**
         * 设置状态栏小图标，smallIconId为项目中的图片资源Id;
         */
        Beta.smallIconId = R.drawable.icon;
        /**
         * 设置更新弹窗默认展示的banner，defaultBannerId为项目中的图片资源Id;
         * 当后台配置的banner拉取失败时显示此banner，默认不设置则展示“loading“;
         */
        Beta.defaultBannerId = R.drawable.icon;
        /**
         * 设置sd卡的Download为更新资源保存目录;
         * 后续更新资源会保存在此目录，需要在manifest中添加WRITE_EXTERNAL_STORAGE权限;
         */
        Beta.storageDir = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        /**
         * 已经确认过的弹窗在APP下次启动自动检查更新时会再次显示;
         */
        Beta.showInterruptedStrategy = true;
        /**
         * 只允许在MainActivity上显示更新弹窗，其他activity上不显示弹窗; 不设置会默认所有activity都可以显示弹窗;
         */
        Beta.canShowUpgradeActs.add(MainActivity.class);

        /**
         * 设置Wifi下自动下载
         */
        Beta.autoDownloadOnWifi = true;

        /*在application中初始化时设置监听，监听策略的收取*/
        Beta.upgradeListener = new UpgradeListener() {
            /**
             * 接收到更新策略
             * @param ret  0:正常 －1:请求失败
             * @param strategy 更新策略
             * @param isManual true:手动请求 false:自动请求
             * @param isSilence true:不弹窗 false:弹窗
             * @return 是否放弃SDK处理此策略，true:SDK将不会弹窗，策略交由app自己处理
             */
            @Override
            public void onUpgrade(int ret, UpgradeInfo strategy, boolean isManual, boolean isSilence) {
                if (strategy != null) {
//                    Log.e("bugly", "需要更新,存在更新策略");
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            Intent i = new Intent();
                            i.setClass(getApplicationContext(), UpgradeActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                        }
                    }, 1000);

                } else {
//                    Log.e("bugly", "不需要更新,没有更新策略");
                    if (isManual){
                        ToastUtils.showShort("已经是最新版本");
                    }
                }
            }
        };

        /* 设置更新状态回调接口 */
        Beta.upgradeStateListener = new UpgradeStateListener() {
            @Override
            public void onUpgradeSuccess(boolean isManual) {
//                Toast.makeText(getApplicationContext(),"UPGRADE_SUCCESS",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUpgradeFailed(boolean isManual) {
//                Toast.makeText(getApplicationContext(),"UPGRADE_FAILED",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUpgrading(boolean isManual) {
//                Toast.makeText(getApplicationContext(),"UPGRADE_CHECKING",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadCompleted(boolean b) {

            }

            @Override
            public void onUpgradeNoVersion(boolean isManual) {
//                Toast.makeText(getApplicationContext(),"UPGRADE_NO_VERSION",Toast.LENGTH_SHORT).show();
            }
        };

//        Beta.upgradeDialogLayoutId = R.layout.dialog_update;
////        Beta.tipsDialogLayoutId = R.layout.dialog_update;
//        /*
//         * 已经确认过的弹窗在APP下次启动自动检查更新时会再次显示;
//         */
//        Beta.showInterruptedStrategy = true;
//
//        Beta.enableNotification = false;
//
//        /*
//         * 用于去除弹出的tips这里不是很需要 看你们具体的需求了啊
//         */
//        Beta.strToastYourAreTheLatestVersion = "";
//        Beta.strToastCheckingUpgrade = "";
//
//        /*
//         * 只允许在MainActivity上显示更新弹窗，其他activity上不显示弹窗; 不设置会默认所有activity都可以显示弹窗;
//         */
////        Beta.canShowUpgradeActs.add(MainActivity.class);
//
//        Beta.upgradeDialogLifecycleListener = new UILifecycleListener<UpgradeInfo>() {
//            private String TAG;
//
//            @Override
//            public void onCreate(Context context, View view, UpgradeInfo upgradeInfo) {
//                Log.d(TAG, "onCreate");
//                // 注：可通过这个回调方式获取布局的控件，如果设置了id，可通过findViewById方式获取，如果设置了tag，可以通过findViewWithTag，具体参考下面例子:
//
//                // 通过id方式获取控件，并更改imageview图片
////                ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
////                imageView.setImageResource(R.mipmap.ic_launcher);
//
//                // 通过tag方式获取控件，并更改布局内容
////                TextView textView = (TextView) view.findViewWithTag("textview");
////                textView.setText("my custom text");
//
//                // 更多的操作：比如设置控件的点击事件
////                imageView.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        Intent intent = new Intent(getApplicationContext(), OtherActivity.class);
////                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                        startActivity(intent);
////                    }
////                });
//            }
//
//            @Override
//            public void onStart(Context context, View view, UpgradeInfo upgradeInfo) {
//                Log.d(TAG, "onStart");
//            }
//
//            @Override
//            public void onResume(Context context, View view, UpgradeInfo upgradeInfo) {
//                Log.d(TAG, "onResume");
//            }
//
//            @Override
//            public void onPause(Context context, View view, UpgradeInfo upgradeInfo) {
//                Log.d(TAG, "onPause");
//            }
//
//            @Override
//            public void onStop(Context context, View view, UpgradeInfo upgradeInfo) {
//                Log.d(TAG, "onStop");
//            }
//
//            @Override
//            public void onDestroy(Context context, View view, UpgradeInfo upgradeInfo) {
//                Log.d(TAG, "onDestory");
//            }
//        };

    }
}
