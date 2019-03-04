package com.ying.administrator.masterappdemo.mvp.presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.mvp.contract.RegisterContract;

public class RegisterPresenter extends RegisterContract.Presenter {
    @Override
    public void Reg(String userName, String code) {
        mModel.Reg(userName,code).subscribe(new BaseObserver<String>() {
            @Override
            protected void onHandleSuccess(BaseResult<String> value) {
                mView.Reg(value);
            }
        });
    }

    @Override
    public void GetCode(String userName) {
        mModel.GetCode(userName).subscribe(new BaseObserver<String>() {
            @Override
            protected void onHandleSuccess(BaseResult<String> value) {
                mView.GetCode(value);
            }
        });
    }

    @Override
    public void Login(String userName, String password) {
        mModel.Login(userName,password).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.Login(value);
            }
        });
    }

    @Override
    public void ValidateUserName(String userName) {
        mModel.ValidateUserName(userName).subscribe(new BaseObserver<String>() {
            @Override
            protected void onHandleSuccess(BaseResult<String> value) {
                mView.ValidateUserName(value);
            }
        });
    }
    @Override
    public void AddAndUpdatePushAccount(String token, String type, String UserID) {

        mModel.AddAndUpdatePushAccount(token,type,UserID)
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<String> value) {
                        mView.AddAndUpdatePushAccount(value);
                    }
                });
    }
}
