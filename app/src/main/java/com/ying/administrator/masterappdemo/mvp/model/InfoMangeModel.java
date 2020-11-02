package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.api.ApiRetrofit;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.IDCard;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.InfoManageContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;


public class InfoMangeModel implements InfoManageContract.Model {


    @Override
    public Observable<BaseResult<UserInfo>> GetUserInfoList(String UserID,String limit) {
        return ApiRetrofit.getDefault().GetUserInfoList(UserID,limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    /*个人信息头像修改*/
    @Override
    public Observable<BaseResult<Data<String>>> UploadAvator(RequestBody json) {
        return ApiRetrofit.getDefault().UploadAvator(json)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<List<IDCard.IDCardBean>>> GetIDCardImg(String UserID) {
        return ApiRetrofit.getDefault().GetIDCardImg(UserID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data>> UpdateAccountNickName(String UserID,String NickName) {
        return ApiRetrofit.getDefault().UpdateAccountNickName(UserID,NickName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data>> UpdatePassword(String UserID, String Password) {
        return ApiRetrofit.getDefault().UpdatePassword(UserID,Password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data>> UpdateSex(String UserID, String Sex) {
        return ApiRetrofit.getDefault().UpdateSex(UserID,Sex)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }


}
