package com.ying.administrator.masterappdemo.mvp.service.manager;

import android.content.Context;

import com.ying.administrator.masterappdemo.entity.Order_Entity;
import com.ying.administrator.masterappdemo.mvp.service.RetrofitHelper;
import com.ying.administrator.masterappdemo.mvp.service.RetrofitService;

import rx.Observable;

public class DataManager {
    private RetrofitService mRetrofitService;
    public DataManager(Context context){
        this.mRetrofitService= RetrofitHelper.getInstance(context).getServer();
    }

    public Observable<Order_Entity> GetOrdInfoList(){
        return mRetrofitService.GetOrderInfoList();
    }
}

/*
 * 可以看到，在它的构造方法中，我们得到了RetrofitService 的实例化，
 * 然后定义了一个和RetrofitService 中同名的方法，里面其实就是调用RetrofitService 中的这个方法。
 * 这样，把RetrofitService 中定义的方法都封装到DataManager 中，
 * 以后无论在哪个要调用方法时直接在DataManager 中调用就可以了，
 * 而不是重复建立RetrofitService 的实例化，再调用其中的方法。
 */