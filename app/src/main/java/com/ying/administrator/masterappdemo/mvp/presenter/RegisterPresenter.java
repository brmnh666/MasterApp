package com.ying.administrator.masterappdemo.mvp.presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WxRegister;
import com.ying.administrator.masterappdemo.mvp.contract.RegisterContract;

public class RegisterPresenter extends RegisterContract.Presenter {
    @Override
    public void Reg(String userName, String code,String password) {
        mModel.Reg(userName,code,password).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.Reg(value);
            }
        });
    }

    @Override
    public void GetCode(String userName,String type) {
        mModel.GetCode(userName,type).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
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
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.AddAndUpdatePushAccount(value);
                    }
                });
    }

    @Override
    public void WxReg(String userName, String code, String openid,String unionid) {
        mModel.WxReg(userName, code, openid,unionid)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.WxReg(value);
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
