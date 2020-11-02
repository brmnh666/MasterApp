package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.api.ApiRetrofit;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.BankCard;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.IDCard;
import com.ying.administrator.masterappdemo.entity.WithDrawMoney;
import com.ying.administrator.masterappdemo.mvp.contract.WithDrawContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WithDrawModel implements WithDrawContract.Model {


    @Override
    public Observable<BaseResult<WithDrawMoney>> GetDepositMoneyDisplay(String UserId) {
        return ApiRetrofit.getDefault().GetDepositMoneyDisplay(UserId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<List<BankCard>>> GetAccountPayInfoList(String UserId) {
        return ApiRetrofit.getDefault().GetAccountPayInfoList(UserId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> WithDraw(String DrawMoney, String CardNo, String UserID,String CardName) {
        return ApiRetrofit.getDefault().WithDraw(DrawMoney,CardNo,UserID,CardName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<List<IDCard.IDCardBean>>> GetIDCardImg(String UserID) {
        return ApiRetrofit.getDefault().GetIDCardImg(UserID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
