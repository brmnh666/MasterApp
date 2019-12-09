package com.ying.administrator.masterappdemo.mvp.presenter;
import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.BankCard;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.IDCard;
import com.ying.administrator.masterappdemo.entity.WithDrawMoney;
import com.ying.administrator.masterappdemo.mvp.contract.WithDrawContract;

import java.util.List;

public class WithDrawPresenter extends WithDrawContract.Presenter {
    @Override
    public void GetDepositMoneyDisplay(String UserId) {
        mModel.GetDepositMoneyDisplay(UserId)
                .subscribe(new BaseObserver<WithDrawMoney>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<WithDrawMoney> value) {
                        mView.GetDepositMoneyDisplay(value);
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
    public void WithDraw(String DrawMoney, String CardNo, String UserID,String CardName) {
        mModel.WithDraw(DrawMoney,CardNo,UserID,CardName)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.WithDraw(value);
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

}
