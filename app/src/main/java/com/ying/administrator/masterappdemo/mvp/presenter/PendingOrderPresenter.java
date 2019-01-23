package com.ying.administrator.masterappdemo.mvp.presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.PendingOrderContract;

public class PendingOrderPresenter extends PendingOrderContract.Presenter {
    @Override
    public void GetOrderInfo(String OrderID) {
        mModel.GetOrderInfo(OrderID).subscribe(new BaseObserver<WorkOrder>() {
            @Override
            protected void onHandleSuccess(BaseResult<WorkOrder> value) {
                mView.GetOrderInfo(value);
            }
        });
    }
}
