package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WXpayInfo;
import com.ying.administrator.masterappdemo.mvp.contract.RechargeContract;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;

import org.json.JSONArray;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RechargeModel implements RechargeContract.Model {


    @Override
    public Observable<BaseResult<Data<String>>> GetOrderStr(String userid, String TotalAmount) {
        return ApiRetrofit.getDefault().GetOrderStr(userid, "","",TotalAmount,"1",new JSONArray())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<Data<WXpayInfo>>> GetWXOrderStr(String userid, String TotalAmount) {
        return ApiRetrofit.getDefault().GetWXOrderStr(userid, "","",TotalAmount,"1","worker",new JSONArray())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<Data<String>>> WXNotifyManual(String OutTradeNo) {
        return ApiRetrofit.getDefault().WXNotifyManual(OutTradeNo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<UserInfo>> GetUserInfoList(String UserID, String limit) {
        return ApiRetrofit.getDefault().GetUserInfoList(UserID,limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

}
