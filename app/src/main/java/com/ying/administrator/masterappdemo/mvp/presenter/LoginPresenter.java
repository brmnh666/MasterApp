package com.ying.administrator.masterappdemo.mvp.presenter;


import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WxRegister;
import com.ying.administrator.masterappdemo.mvp.contract.LoginContract;

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
    public void GetCode(String userName, String type) {
        mModel.GetCode(userName,type).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.GetCode(value);
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
    public void LoginOnMessage(String mobile, String code) {
        mModel.LoginOnMessage(mobile,code).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.LoginOnMessage(value);
            }
        });
    }
    @Override
    public void LoginOut(String UserID) {
        mModel.LoginOut(UserID).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.LoginOut(value);
            }
        });
    }

    @Override
    public void WxRegister(String openid, String nickname, String sex, String language, String city, String province, String country, String headimgurl, String unionid) {
        mModel.WxRegister(openid, nickname, sex, language, city, province, country, headimgurl, unionid)
                .subscribe(new BaseObserver<Data<WxRegister>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<WxRegister>> value) {
                        mView.WxRegister(value);
                    }
                });
    }
}
