package com.ying.administrator.masterappdemo.mvp.presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GAccessory;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.entity.GetFactorySeviceData;
import com.ying.administrator.masterappdemo.entity.Service;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.PendingOrderContract;

import java.util.List;

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
        mModel.AddOrderAccessory(json).subscribe(new BaseObserver<Data>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data> value) {
                mView.AddOrderAccessory(value);
            }
        });
    }

    @Override
    public void AddOrderService(RequestBody json) {
        mModel.AddOrderService(json).subscribe(new BaseObserver<Data>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data> value) {
                mView.AddOrderService(value);
            }
        });
    }

    @Override
    public void AddOrUpdateAccessoryServiceReturn(RequestBody json) {
        mModel.AddOrUpdateAccessoryServiceReturn(json).subscribe(new BaseObserver<Data>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data> value) {
                mView.AddOrUpdateAccessoryServiceReturn(value);
            }
        });
    }

    @Override
    public void UpdateSendOrderUpdateTime(String OrderID, String UpdateDate) {
        mModel.UpdateSendOrderUpdateTime(OrderID,UpdateDate).subscribe(new BaseObserver<Data>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data> value) {
                mView.UpdateSendOrderUpdateTime(value);
            }
        });

    }

    @Override
    public void GetOrderAccessoryByOrderID(String OrderID) {
        mModel.GetOrderAccessoryByOrderID(OrderID).subscribe(new BaseObserver<List<GAccessory>>() {
            @Override
            protected void onHandleSuccess(BaseResult<List<GAccessory>> value) {
                mView.GetOrderAccessoryByOrderID(value);
            }
        });
    }

    @Override
    public void ServiceOrderPicUpload(RequestBody json, final int code) {
        mModel.ServiceOrderPicUpload(json,code).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.ServiceOrderPicUpload(value,code);
            }
        });
    }

    @Override
    public void ReuturnAccessoryPicUpload(RequestBody json, final int code) {
        mModel.ReuturnAccessoryPicUpload(json,code).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.ReuturnAccessoryPicUpload(value,code);
            }
        });
    }

    @Override
    public void FinishOrderPicUpload(RequestBody json, final int code) {
        mModel.FinishOrderPicUpload(json,code).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.FinishOrderPicUpload(value,code);
            }
        });
    }

    @Override
    public void OrderByondImgPicUpload(RequestBody json,final int code) {
        mModel.OrderByondImgPicUpload(json,code).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.OrderByondImgPicUpload(value,code);
            }
        });
    }
}
