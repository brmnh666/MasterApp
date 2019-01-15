package com.ying.administrator.masterappdemo.mvp.service;

import android.content.Context;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*RetrofitHelper主要用于Retrofit的初始化*/
public class RetrofitHelper {
    private Context mContext;
    OkHttpClient client =new OkHttpClient();
    GsonConverterFactory factory=GsonConverterFactory.create(new GsonBuilder().create());
    private static RetrofitHelper instance =null;
    private Retrofit mRetrofit=null;
    public static RetrofitHelper getInstance(Context context){
        /*用于获取自身RetrofitHelper的实例化，并且只会实例化一次。*/
        if (instance==null){
            instance=new RetrofitHelper(context);
        }
        return instance;
    }

    private RetrofitHelper(Context mcontext){
        mContext=mcontext;
        init();
    }

    private void init() {
        resetApp();
    }

    private void resetApp() {
        /*Retrofit的创建*/
        mRetrofit=new Retrofit.Builder()
                .baseUrl("http://47.96.126.145/api/")
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }

    public RetrofitService getServer(){
        /*为了获取RetrofitService接口类的实例化*/
        return mRetrofit.create(RetrofitService.class);
    }

}
