package com.ying.administrator.masterappdemo.mvp.presenter;


import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.IDCard;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.InfoManageContract;
import com.ying.administrator.masterappdemo.mvp.contract.MainContract;

import java.util.List;

import okhttp3.Cache;
import okhttp3.RequestBody;

public class InfoManagePresenter extends InfoManageContract.Presenter {


    @Override
    public void GetUserInfoList(String UserID,String limit) {
        mModel.GetUserInfoList(UserID,limit)
                .subscribe(new BaseObserver<UserInfo>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<UserInfo> value) {
                        mView.GetUserInfoList(value);
                    }
                });
    }

    @Override
    public void UploadAvator(RequestBody json) {
        mModel.UploadAvator(json)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.UploadAvator(value);
                    }
                });
    }

    @Override
    public void GetIDCardImg(String UserID) {
        mModel.GetIDCardImg(UserID)
                .subscribe(new BaseObserver<List<IDCard.IDCardBean>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<List<IDCard.IDCardBean>> value) {
                        mView.GetIDCardImg(value);
                    }
                });
    }

    @Override
    public void UpdateAccountNickName(String UserID,String NickName) {
        mModel.UpdateAccountNickName(UserID,NickName)
                .subscribe(new BaseObserver<Data>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data> value) {
                        mView.UpdateAccountNickName(value);
                    }
                });
    }

    @Override
    public void UpdatePassword(String UserID, String Password) {
        mModel.UpdatePassword(UserID,Password)
                .subscribe(new BaseObserver<Data>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data> value) {
                        mView.UpdatePassword(value);
                    }
                });
    }

    @Override
    public void UpdateSex(String UserID, String Sex) {
        mModel.UpdateSex(UserID,Sex)
                .subscribe(new BaseObserver<Data>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data> value) {
                        mView.UpdateSex(value);
                    }
                });
    }


}
