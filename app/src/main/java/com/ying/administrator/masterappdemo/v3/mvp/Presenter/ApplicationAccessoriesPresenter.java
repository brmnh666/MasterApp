package com.ying.administrator.masterappdemo.v3.mvp.Presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.AddressList;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.entity.Service;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.v3.mvp.contract.ApplicationAccessoriesContract;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class ApplicationAccessoriesPresenter extends ApplicationAccessoriesContract.Presenter {
    @Override
    public void GetFactoryAccessoryMoney(String OrderID, String FCategoryID, String SizeID, String Price) {
        mModel.GetFactoryAccessoryMoney(OrderID, FCategoryID, SizeID, Price)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.GetFactoryAccessoryMoney(value);
                    }
                });
    }

    @Override
    public void GetFactoryService(String FCategoryID) {
        mModel.GetFactoryService(FCategoryID)
                .subscribe(new BaseObserver<GetFactoryData<Service>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<GetFactoryData<Service>> value) {
                        mView.GetFactoryService(value);
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
    public void UpdateOrderAddressByOrderID(String OrderID, String SendAddress) {
        mModel.UpdateOrderAddressByOrderID(OrderID, SendAddress)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.UpdateOrderAddressByOrderID(value);
                    }
                });
    }

    @Override
    public void AddOrderAccessoryAndService(RequestBody json) {
        mModel.AddOrderAccessoryAndService(json)
                .subscribe(new BaseObserver<Data>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data> value) {
                        mView.AddOrderAccessoryAndService(value);
                    }
                });
    }

    @Override
    public void GetUserInfoList(String UserID, String limit) {
        mModel.GetUserInfoList(UserID, limit)
                .subscribe(new BaseObserver<UserInfo>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<UserInfo> value) {
                        mView.GetUserInfoList(value);
                    }
                });
    }
}
