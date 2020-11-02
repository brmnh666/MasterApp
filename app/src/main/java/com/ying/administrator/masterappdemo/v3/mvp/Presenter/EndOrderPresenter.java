package com.ying.administrator.masterappdemo.v3.mvp.Presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver2;
import com.ying.administrator.masterappdemo.v3.bean.OrderListResult;
import com.ying.administrator.masterappdemo.v3.mvp.contract.EndOrderContract;

public class EndOrderPresenter extends EndOrderContract.Presenter {
    @Override
    public void GetOrderList(String Search, String State, String page, String limit) {
        mModel.GetOrderList(Search, State, page, limit)
                .subscribe(new BaseObserver2<OrderListResult>() {
                    @Override
                    protected void onHandleSuccess(OrderListResult value) {
                        mView.GetOrderList(value);
                    }
                });
    }
}
