package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Category;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.mvp.contract.AddSkillsContract;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class AddSkillsModel implements AddSkillsContract.Model {


    @Override
    public Observable<BaseResult<Data<List<Category>>>> GetFactoryCategory() {
        return  ApiRetrofit.getDefault().GetFactoryCategory()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<String>> GetAccountSkill(String UserID) {
        return  ApiRetrofit.getDefault().GetAccountSkill(UserID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

}
