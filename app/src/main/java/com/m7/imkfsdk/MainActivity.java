package com.m7.imkfsdk;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import android.support.annotation.NonNull;

import com.m7.imkfsdk.utils.PermissionUtils;
import com.moor.imkf.IMChatManager;
import com.moor.imkf.model.entity.CardInfo;
import com.moor.imkf.utils.MoorUtils;
import com.ying.administrator.masterappdemo.R;

import java.net.URLEncoder;
import java.util.Locale;


public class MainActivity extends Activity {
    public static String accessId = "";
    private String productName;
    private String productPic;
    private String productPrice;
    private String productUrl;
    private String userName;
    private String userId;
    private String userPic;
    public static View.OnClickListener clickListener;

    public void setClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.kf_activity_main);
        productName = getIntent().getStringExtra("goodsName");
        productPic = getIntent().getStringExtra("goodsPricture");
        productPrice = getIntent().getStringExtra("goodsPrice");
        productUrl = getIntent().getStringExtra("goodsURL");
        userName = getIntent().getStringExtra("userName");
        userId = getIntent().getStringExtra("userId");
        userPic = getIntent().getStringExtra("userPic");

        /**
         * 文件写入权限 （初始化需要写入文件，点击在线客服按钮之前需打开文件写入权限）
         * 读取设备 ID 权限 （初始化需要获取用户的设备 ID）
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PermissionUtils.hasAlwaysDeniedPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                PermissionUtils.requestPermissions(this, 0x11, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE}, new PermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                    }

                    @Override
                    public void onPermissionDenied(String[] deniedPermissions) {
                        Toast.makeText(MainActivity.this, R.string.notpermession, Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 2000);
                    }
                });
            }
        }

        /**
         * 第一步：初始化help 文件
         */
        final KfStartHelper helper = new KfStartHelper(MainActivity.this);
        /**
         * 第二步
         * 设置参数
         * 初始化sdk方法，必须先调用该方法进行初始化后才能使用IM相关功能
         * @param accessId       接入id（需后台配置获取）
         * @param userName       用户名
         * @param userId         用户id
         */

//                商品信息示例
        String s = "http://seller.xigyu.com/product/detail/"+productUrl;
        CardInfo ci = new CardInfo(productPic, productName, productName, productPrice, "http://seller.xigyu.com/product/detail/"+productUrl);
        String icon = productPic;
        String title = productName;
        String content = productName;
        String rigth3 = productPrice;
        try {
            ci = new CardInfo(URLEncoder.encode(icon, "utf-8"), URLEncoder.encode(title, "utf-8"), URLEncoder.encode(content, "utf-8"), URLEncoder.encode(rigth3, "utf-8"),
                    URLEncoder.encode(s, "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (productName!=null){
            helper.setCard(ci);
        }
        helper.initSdkChat("87326950-b5a5-11e9-be6e-a515be030f55", userName, userId);//腾讯云正式
        finish();
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /**w
         * 获取未读消息数示例
         */
        findViewById(R.id.setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (MoorUtils.isInitForUnread(MainActivity.this)) {
                    IMChatManager.getInstance().getMsgUnReadCountFromService(new IMChatManager.HttpUnReadListen() {
                        @Override
                        public void getUnRead(int acount) {
                            Toast.makeText(MainActivity.this, "未读消息数为：" + acount, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    //未初始化，消息当然为 ：0
                    Toast.makeText(MainActivity.this, "还没初始化", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * 权限回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            PermissionUtils.onRequestPermissionsResult(this, 0x11, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, grantResults);
        }
    }

    /**
     * 语言切换
     * 中文 language：""
     * 英文 language："en"
     */
    private void initLanguage(String language) {
        Resources resources = getApplicationContext().getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = new Locale(language);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());//更新配置
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    public static void startActivity(Context mContext,Intent intent, View.OnClickListener listener) {
        clickListener=listener;
        mContext.startActivity(intent);
    }
}
