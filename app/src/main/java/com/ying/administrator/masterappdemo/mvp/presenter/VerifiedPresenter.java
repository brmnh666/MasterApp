package com.ying.administrator.masterappdemo.mvp.presenter;


import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.AddressList;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.VerifiedContract;

import java.util.List;

import okhttp3.RequestBody;

public class VerifiedPresenter extends VerifiedContract.Presenter {


    @Override
    public void IDCardUpload(RequestBody json, final int code) {
        mModel.IDCardUpload(json,code)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.IDCardUpload(value,code);
                    }
                });
    }

    @Override
    public void GetUserInfoList(String UserID, String limit) {
        mModel.GetUserInfoList(UserID,limit)
                .subscribe(new BaseObserver<UserInfo>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<UserInfo> value) {
                        mView.GetUserInfoList(value);
                    }
                });
    }

    @Override
    public void ApplyAuthInfo(String UserID, String TrueName,String Sex, String IDCard, String Address, String NodeIds, String Province, String City, String Area, String District, String Longitude, String Dimension,String ServiceAreaJsonStr,String ISwoker) {
        mModel.ApplyAuthInfo(UserID, TrueName,Sex, IDCard, Address, NodeIds, Province, City, Area, District, Longitude, Dimension,ServiceAreaJsonStr,ISwoker)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.ApplyAuthInfo(value);
                    }
                });
    }

    @Override
    public void ApplyAuthInfoBysub(String UserID, String TrueName, String Sex, String IDCard, String Address, String Province, String City, String Area, String District, String Longitude, String Dimension,String ISwoker) {
        mModel.ApplyAuthInfoBysub(UserID, TrueName,Sex, IDCard, Address, Province, City, Area, District, Longitude, Dimension,ISwoker)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.ApplyAuthInfoBysub(value);
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

}
