package com.ying.administrator.masterappdemo.base;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/5/2.
 */
public abstract class BaseObserver<T> implements Observer<BaseResult<T>> {

    private static final String TAG = "BaseObserver";

    protected BaseObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BaseResult<T> value) {
        onHandleSuccess(value);
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "error:" + e.toString());
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete");
    }


    protected abstract void onHandleSuccess(BaseResult<T> value);

    protected void onHandleError(String msg) {
    }
}

