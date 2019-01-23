package com.ying.administrator.masterappdemo.mvp.presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.AccessoryData;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.PendingOrderContract;

public class PendingOrderPresenter extends PendingOrderContract.Presenter {
    @Override
    public void GetOrderInfo(String OrderID) {
        mModel.GetOrderInfo(OrderID).subscribe(new BaseObserver<WorkOrder.DataBean>() {
            @Override
            protected void onHandleSuccess(BaseResult<WorkOrder.DataBean> value) {
                mView.GetOrderInfo(value);
            }
        });
    }

    @Override
    public void GetFactoryAccessory() {
        mModel.GetFactoryAccessory().subscribe(new BaseObserver<AccessoryData<Accessory>>() {
            @Override
            protected void onHandleSuccess(BaseResult<AccessoryData<Accessory>> value) {
                mView.GetFactoryAccessory(value);
            }
        });
    }
}
