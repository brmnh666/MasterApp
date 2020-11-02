package com.ying.administrator.masterappdemo.api;

import com.ying.administrator.masterappdemo.common.Config;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRetrofit {
    private static ApiService SERVICE;
    public static ApiService getDefault(){
        if (SERVICE == null){
            SERVICE = new Retrofit.Builder()
                    .baseUrl(Config.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(Config.getClient())
                    .build()
                    .create(ApiService.class);
        }
        return SERVICE;
    }
}
