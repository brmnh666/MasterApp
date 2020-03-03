package com.ying.administrator.masterappdemo.v3.mvp.Presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.AddressList;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.v3.mvp.contract.PersonalInformationContract;

import java.util.List;

import okhttp3.RequestBody;

public class PersonalInformationPresenter extends PersonalInformationContract.Presenter {
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
    public void UpdateEmergencyContact(String UserId, String emergencyContact) {
        mModel.UpdateEmergencyContact(UserId, emergencyContact)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.UpdateEmergencyContact(value);
                    }
                });
    }
}
