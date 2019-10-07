package com.ying.administrator.masterappdemo.mvp.presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.AddressList;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.Data2;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.mvp.contract.NewAddAccessoriesContract;

import java.util.List;

import okhttp3.RequestBody;

public class NewAddAccessoriesPresenter extends NewAddAccessoriesContract.Presenter {

     @Override
     public void GetFactoryAccessory(String FProductTypeID) {
         mModel.GetFactoryAccessory(FProductTypeID).subscribe(new BaseObserver<GetFactoryData<Accessory>>() {
             @Override
             protected void onHandleSuccess(BaseResult<GetFactoryData<Accessory>> value) {
                 mView.GetFactoryAccessory(value);
             }
         });
     }
    @Override
    public void ApplyAccessoryphotoUpload(RequestBody body) {
        mModel.ApplyAccessoryphotoUpload(body).subscribe(new BaseObserver<Data2>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data2> value) {
                mView.ApplyAccessoryphotoUpload(value);
            }
        });
    }

    @Override
    public void GetAccountAddress(String UserId) {
        mModel.GetAccountAddress(UserId)
                .subscribe(new BaseObserver<List<AddressList>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<List<AddressList>> value) {
                        mView.GetAccountAddress(value);
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
    public void UpdateOrderAddressByOrderID(String OrderID, String SendAddress) {
        mModel.UpdateOrderAddressByOrderID(OrderID, SendAddress)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.UpdateOrderAddressByOrderID(value);
                    }
                });
    }

}
