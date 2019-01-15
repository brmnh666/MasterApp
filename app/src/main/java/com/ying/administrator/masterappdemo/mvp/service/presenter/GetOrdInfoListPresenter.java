package com.ying.administrator.masterappdemo.mvp.service.presenter;

import android.content.Context;
import android.content.Intent;

import com.ying.administrator.masterappdemo.entity.Order_Entity;
import com.ying.administrator.masterappdemo.mvp.service.manager.DataManager;
import com.ying.administrator.masterappdemo.mvp.service.view.GetOrderInfoListView;
import com.ying.administrator.masterappdemo.mvp.service.view.View;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class GetOrdInfoListPresenter implements Presenter {
    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private GetOrderInfoListView mGetOrderInfoListView;
    private Order_Entity mOrder_Entity;

    public GetOrdInfoListPresenter(Context mContext){
        this.mContext=mContext;
    }
    @Override
    public void onCreate() {
        manager=new DataManager(mContext);
        mCompositeSubscription=new CompositeSubscription();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {
        mGetOrderInfoListView= (GetOrderInfoListView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

public void GetOrderInfoList(){
    mCompositeSubscription.add(manager.GetOrdInfoList()
              .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<Order_Entity>() {
                @Override
                public void onCompleted() {
                    if (mGetOrderInfoListView!=null){
                        mGetOrderInfoListView.onSuccess(mOrder_Entity);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    mGetOrderInfoListView.onError("请求失败！！");
                }

                @Override
                public void onNext(Order_Entity order_entity) {
                    mOrder_Entity=order_entity;

                }
            }));

}
}
