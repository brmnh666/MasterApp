package com.ying.administrator.masterappdemo.mvp.presenter;


import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WXpayInfo;
import com.ying.administrator.masterappdemo.mvp.contract.RechargeContract;
import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;

public class RechargePresenter extends RechargeContract.Presenter {


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
    @Override
    public void WXNotifyManual(String OutTradeNo) {
        mModel.WXNotifyManual(OutTradeNo)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.WXNotifyManual(value);
                    }
                });
    }

    @Override
    public void GetUserInfoList(String UserID, String limit) {
        mModel.GetUserInfoList(UserID, limit)
                .subscribe(new BaseObserver<UserInfo>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<UserInfo> value) {
                        mView.GetUserInfoList(value);
                    }
                });
    }
}
