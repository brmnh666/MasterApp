package com.ying.administrator.masterappdemo.v3.mvp.Presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.v3.mvp.contract.SearchOrderContract;

public class SearchOrderPresenter extends SearchOrderContract.Presenter{
    @Override
    public void GetOrderInfoList(String Phone, String OrderID, String UserID, String limit, String page) {
        mModel.GetOrderInfoList(Phone, OrderID, UserID, limit, page)
                .subscribe(new BaseObserver<WorkOrder>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<WorkOrder> value) {
                        mView.GetOrderInfoList(value);
                    }
                });
    }
}
