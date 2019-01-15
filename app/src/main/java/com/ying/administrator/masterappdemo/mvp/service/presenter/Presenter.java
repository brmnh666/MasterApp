package com.ying.administrator.masterappdemo.mvp.service.presenter;

import android.content.Intent;

import com.ying.administrator.masterappdemo.mvp.service.view.View;

public interface Presenter {
    void onCreate();
    void onStart();//暂时没用到
    void onStop();
    void pause();//暂时没用到
    void attachView(View view);
    void attachIncomingIntent(Intent intent);//暂时没用到
}
