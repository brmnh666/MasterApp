package com.ying.administrator.masterappdemo.mvp.presenter;


import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.mvp.contract.VerifiedContract;

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
    public void ApplyAuthInfo(String UserID, String TrueName,String Sex, String IDCard, String Address, String NodeIds, String Province, String City, String Area, String District, String Longitude, String Dimension,String ServiceAreaJsonStr) {
        mModel.ApplyAuthInfo(UserID, TrueName,Sex, IDCard, Address, NodeIds, Province, City, Area, District, Longitude, Dimension,ServiceAreaJsonStr)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.ApplyAuthInfo(value);
                    }
                });
    }

}
