package com.ying.administrator.masterappdemo.base;

import android.util.Log;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.adapter.rxjava2.HttpException;

/**
 * Created by Administrator on 2018/5/2.
 */
public abstract class BaseObserver<T> implements Observer<BaseResult<T>> {

    private static final String TAG = "BaseObserver";
    protected Disposable disposable;
    private String errMsg="接口异常";

    protected BaseObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {
        disposable=d;
    }

    @Override
    public void onNext(BaseResult<T> value) {
        onHandleSuccess(value);
//        switch (value.getStatusCode()){
//            case 406:
//                EventBus.getDefault().post("账号在别处登录");
//                break;
//        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "error:" + this.toString());
        Log.e(TAG, "error:" + e.toString());
        if (!NetworkUtils.isConnected()) {
            errMsg = "网络连接出错";
        } else if (e instanceof JsonSyntaxException) {
            errMsg = "接口异常";
//            EventBus.getDefault().post("账号在别处登录");
        } else if (e instanceof HttpException) {
            errMsg = "网络请求出错";
        } else if (e instanceof IOException) {
            errMsg = "网络出错";
        }

        if (disposable !=null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        onHandleError(errMsg);
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete");
        if (disposable !=null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }


    protected abstract void onHandleSuccess(BaseResult<T> value);

    protected void onHandleError(String msg) {
        ToastUtils.showShort(msg);
        Log.d(TAG, "error"+msg);
    }
}

