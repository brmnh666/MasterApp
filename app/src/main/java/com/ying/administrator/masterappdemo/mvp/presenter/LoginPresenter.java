package com.ying.administrator.masterappdemo.mvp.presenter;


import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.mvp.contract.LoginContract;

import okhttp3.RequestBody;

public class LoginPresenter extends LoginContract.Presenter {
    @Override
    public void Login(String userName, String passWord) {
        mModel.Login(userName, passWord)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.Login(value);
                    }
                });
    }

    @Override
    public void GetUserInfo(RequestBody json) {
        mModel.GetUserInfo(json)
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<String> value) {
                        mView.GetUserInfo(value);
                    }
                });
    }

    @Override
    public void GetUserInfo(String userName) {
        mModel.GetUserInfo(userName)
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<String> value) {
                        mView.GetUserInfo(value);
                    }
                });
    }

    @Override
    public void AddAndUpdatePushAccount(String token, String type, String UserID) {

        mModel.AddAndUpdatePushAccount(token,type,UserID)
                .subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                  mView.AddAndUpdatePushAccount(value);
            }
        });
    }
}
