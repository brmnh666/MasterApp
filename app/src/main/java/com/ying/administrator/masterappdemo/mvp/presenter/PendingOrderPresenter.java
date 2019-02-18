package com.ying.administrator.masterappdemo.mvp.presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.entity.GetFactorySeviceData;
import com.ying.administrator.masterappdemo.entity.Service;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.PendingOrderContract;

import okhttp3.RequestBody;

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
        mModel.GetFactoryAccessory().subscribe(new BaseObserver<GetFactoryData<Accessory>>() {
            @Override
            protected void onHandleSuccess(BaseResult<GetFactoryData<Accessory>> value) {
                mView.GetFactoryAccessory(value);
            }
        });
    }

    @Override
    public void GetFactoryService() {

        mModel.GetFactoryService().subscribe(new BaseObserver<GetFactorySeviceData<Service>>() {
            @Override
            protected void onHandleSuccess(BaseResult<GetFactorySeviceData<Service>> value) {
                mView.GetFactoryService(value);
            }
        });
    }

    @Override
    public void AddOrderAccessory(RequestBody json) {
        mModel.AddOrderAccessory(json).subscribe(new BaseObserver<String>() {
            @Override
            protected void onHandleSuccess(BaseResult<String> value) {
                mView.AddOrderAccessory(value);
            }
        });
    }
}
