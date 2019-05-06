package com.ying.administrator.masterappdemo.common;

import com.blankj.utilcode.util.SPUtils;
import com.ying.administrator.masterappdemo.util.MyUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/*
* 常量配置
* */
public class Config {
//    public static final String BASE_URL = "http://192.168.101.11:8810/api/";//服务端地址
    public static final String BASE_URL = "http://47.96.126.145:8001/api/";//服务端地址
//    public static final String BASE_URL = "http://admin.xigyu.com/api/";//服务端地址
   public static final String HEAD_URL="http://47.96.126.145:8820/Pics/Avator/";//头像地址
   public static final String SUB_ACCOUNT_QRCODE="http://admin.xigyu.com/api/VerifyCode/GetQrCodeToReg?ParentUserID=";//二维码
    public static final String RETURN_IMG="http://47.96.126.145:8820/Pics/OldAccessory/";//返回返件路径
    public static final String Service_IMG="http://47.96.126.145:8820/Pics/FinishOrder/";//服务过程图片

   public static  final int ORDER_READ=99;


    public static boolean IS_DEBUG = true;
    public static String TOKEN = null;


    static HttpLoggingInterceptor loggingInterceptor;
    private static SPUtils spUtils;

    public static HttpLoggingInterceptor getLoggingInterceptor() {
        if (null == loggingInterceptor) {
            loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    MyUtils.e("okhttp", message );
                }
            });
        }
        if (IS_DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return loggingInterceptor;
    }

    static Interceptor interceptor;

    public static Interceptor getInterceptor() {
        interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request.Builder builder=chain.request().newBuilder();
                return   chain.proceed(builder
                        .addHeader("userName", spUtils.getString("userName"))
                        .addHeader("adminToken", spUtils.getString("adminToken"))
                        .build());
            }
        };
        return interceptor;
    }




    static OkHttpClient client;

    public static OkHttpClient getClient() {

        spUtils = SPUtils.getInstance("token");
        if (spUtils.getString("userName")==null){
            client = new OkHttpClient.Builder()
                    .addInterceptor(getLoggingInterceptor())
                    .build();
        }else{
            client = new OkHttpClient.Builder()
                    .addInterceptor(getInterceptor())
                    .addInterceptor(getLoggingInterceptor())
                    .build();
        }

        return client;
    }

}
