package com.ying.administrator.masterappdemo.mvp.presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.BankCard;
import com.ying.administrator.masterappdemo.entity.Bill;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.CardContract;
import com.ying.administrator.masterappdemo.mvp.contract.WalletContract;

import java.util.List;

public class CardPresenter extends CardContract.Presenter {
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
    public void AddorUpdateAccountPayInfo(String UserId, String PayInfoCode, String PayInfoName,String PayNo,String PayName) {
        mModel.AddorUpdateAccountPayInfo(UserId, PayInfoCode,PayInfoName,PayNo,PayName)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.AddorUpdateAccountPayInfo(value);
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
    public void GetBankNameByCardNo(String CardNo) {
        mModel.GetBankNameByCardNo(CardNo)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.GetBankNameByCardNo(value);
                    }
                });
    }

    @Override
    public void DeleteAccountPayInfo(String UserId, String PayInfoCode, String PayInfoName, String PayNo, String PayName, String IsUse,String AccountPayID) {
        mModel.DeleteAccountPayInfo(UserId, PayInfoCode, PayInfoName, PayNo, PayName, IsUse,AccountPayID)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.DeleteAccountPayInfo(value);
                    }
                });
    }


}
