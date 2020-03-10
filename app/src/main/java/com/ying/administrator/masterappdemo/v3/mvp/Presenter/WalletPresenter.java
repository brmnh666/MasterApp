package com.ying.administrator.masterappdemo.v3.mvp.Presenter;




import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.BankCard;
import com.ying.administrator.masterappdemo.entity.Bill;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.ToBepresent;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.v3.mvp.contract.WalletContract;

import java.util.List;

public class WalletPresenter extends WalletContract.Presenter {
    @Override
    public void GetUserInfoList(String UserId, String limit) {
        mModel.GetUserInfoList(UserId, limit)
                .subscribe(new BaseObserver<UserInfo>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<UserInfo> value) {
                        mView.GetUserInfoList(value);
                    }
                });
    }

    @Override
    public void AccountBill(String UserID,String state,String page, String limit) {
        mModel.AccountBill(UserID,state,page,limit)
                .subscribe(new BaseObserver<Data<Bill>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<Bill>> value) {
                        mView.AccountBill(value);
                    }
                });
    }

    @Override
    public void GetAccountPayInfoList(String UserId) {
        mModel.GetAccountPayInfoList(UserId)
                .subscribe(new BaseObserver<List<BankCard>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<List<BankCard>> value) {
                        mView.GetAccountPayInfoList(value);
                    }
                });
    }

    @Override
    public void ToBepresent(String UserId, String State, String limit, String page) {
        mModel.ToBepresent(UserId, State, limit, page)
                .subscribe(new BaseObserver<Data<ToBepresent>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<ToBepresent>> value) {
                        mView.ToBepresent(value);
                    }
                });
    }
}
