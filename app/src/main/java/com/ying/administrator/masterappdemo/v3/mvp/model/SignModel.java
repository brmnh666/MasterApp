package com.ying.administrator.masterappdemo.v3.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WXpayInfo;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;
import com.ying.administrator.masterappdemo.v3.bean.GetContractSigningDepositMoneyResult;
import com.ying.administrator.masterappdemo.v3.bean.GetSignContractManageResult;
import com.ying.administrator.masterappdemo.v3.bean.SaveAutographResult;
import com.ying.administrator.masterappdemo.v3.bean.UploadAutographPicUrlResult;
import com.ying.administrator.masterappdemo.v3.mvp.contract.SignContract;

import org.json.JSONArray;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class SignModel implements SignContract.Model {

    @Override
    public Observable<GetSignContractManageResult> GetSignContractManage() {
        return ApiRetrofit.getDefault().GetSignContractManage()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<UploadAutographPicUrlResult> UploadAutographPicUrl(RequestBody json) {
        return ApiRetrofit.getDefault().UploadAutographPicUrl(json)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<SaveAutographResult> SaveAutograph(String AutographPicUrl) {
        return ApiRetrofit.getDefault().SaveAutograph(AutographPicUrl)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<GetContractSigningDepositMoneyResult> GetContractSigningDepositMoney() {
        return null;
    }

    @Override
    public Observable<GetSignContractManageResult> GetSigningSuccessContract() {
        return null;
    }
    @Override
    public Observable<BaseResult<Data<String>>> GetOrderStr(String userid, String TotalAmount) {
        return ApiRetrofit.getDefault().GetOrderStr(userid, "","",TotalAmount,"2",new JSONArray())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<Data<WXpayInfo>>> GetWXOrderStr(String userid, String TotalAmount) {
        return ApiRetrofit.getDefault().GetWXOrderStr(userid, "","",TotalAmount,"2","worker",new JSONArray())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
