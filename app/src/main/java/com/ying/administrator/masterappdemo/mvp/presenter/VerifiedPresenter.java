package com.ying.administrator.masterappdemo.mvp.presenter;


import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.mvp.contract.VerifiedContract;

import okhttp3.RequestBody;

public class VerifiedPresenter extends VerifiedContract.Presenter {


    @Override
    public void UploadImg(RequestBody json, final int code) {
        mModel.UploadImg(json,code)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.UploadImg(value,code);
                    }
                });
    }

    @Override
    public void UpdateAccountModel(String UserID, String TrueName, String IDCard, String Address, String Skills) {
        mModel.UpdateAccountModel(UserID, TrueName, IDCard, Address, Skills)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.UpdateAccountModel(value);
                    }
                });
    }

    @Override
    public void ApplyAuth(String UserID) {
        mModel.ApplyAuth(UserID)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.ApplyAuth(value);
                    }
                });
    }
}
