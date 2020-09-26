package com.ying.administrator.masterappdemo.v3.mvp.Presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseObserver2;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WXpayInfo;
import com.ying.administrator.masterappdemo.v3.bean.GetSignContractManageResult;
import com.ying.administrator.masterappdemo.v3.bean.SaveAutographResult;
import com.ying.administrator.masterappdemo.v3.bean.UploadAutographPicUrlResult;
import com.ying.administrator.masterappdemo.v3.mvp.contract.SignContract;

import okhttp3.RequestBody;

public class SignPresenter extends SignContract.Presenter {

    @Override
    public void GetSignContractManage() {
        mModel.GetSignContractManage()
                .subscribe(new BaseObserver2<GetSignContractManageResult>() {
                    @Override
                    protected void onHandleSuccess(GetSignContractManageResult value) {
                        mView.GetSignContractManage(value);
                    }
                });
    }

    @Override
    public void UploadAutographPicUrl(RequestBody json) {
        mModel.UploadAutographPicUrl(json)
                .subscribe(new BaseObserver2<UploadAutographPicUrlResult>() {
                    @Override
                    protected void onHandleSuccess(UploadAutographPicUrlResult value) {
                        mView.UploadAutographPicUrl(value);
                    }
                });
    }

    @Override
    public void SaveAutograph(String AutographPicUrl) {
        mModel.SaveAutograph(AutographPicUrl)
                .subscribe(new BaseObserver2<SaveAutographResult>() {
                    @Override
                    protected void onHandleSuccess(SaveAutographResult value) {
                        mView.SaveAutograph(value);
                    }
                });
    }

    @Override
    public void GetContractSigningDepositMoney() {

    }

    @Override
    public void GetSigningSuccessContract() {

    }
    @Override
    public void GetOrderStr(String userid, String TotalAmount) {
        mModel.GetOrderStr(userid, TotalAmount)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.GetOrderStr(value);
                    }
                });
    }
    @Override
    public void GetWXOrderStr(String userid, String TotalAmount) {
        mModel.GetWXOrderStr(userid, TotalAmount)
                .subscribe(new BaseObserver<Data<WXpayInfo>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<WXpayInfo>> value) {
                        mView.GetWXOrderStr(value);
                    }
                });
    }
}
