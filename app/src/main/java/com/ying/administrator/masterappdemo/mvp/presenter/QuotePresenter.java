package com.ying.administrator.masterappdemo.mvp.presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.Data3;
import com.ying.administrator.masterappdemo.entity.WXOfferQuery;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.QuoteContract;

import java.util.List;

public class QuotePresenter extends QuoteContract.Presenter {
    @Override
    public void GetOrderInfo(String OrderID) {
        mModel.GetOrderInfo(OrderID)
                .subscribe(new BaseObserver<WorkOrder.DataBean>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<WorkOrder.DataBean> value) {
                        mView.GetOrderInfo(value);
                    }
                });
    }

    @Override
    public void WXOrderOffer(String SenUserId, String Price, String OrderId, String Reason) {
        mModel.WXOrderOffer(SenUserId, Price, OrderId, Reason)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.WXOrderOffer(value);
                    }
                });
    }

    @Override
    public void WXOfferQuery(String SenUserId, String Price, String OrderId, String Reason) {
        mModel.WXOfferQuery(SenUserId, Price, OrderId, Reason)
                .subscribe(new BaseObserver<Data3<List<WXOfferQuery>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data3<List<WXOfferQuery>>> value) {
                        mView.WXOfferQuery(value);
                    }
                });
    }
}
