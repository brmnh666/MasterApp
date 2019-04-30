package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.QuestBean;
import com.ying.administrator.masterappdemo.mvp.contract.QuestContract;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class QuestModel implements QuestContract.Model {

    @Override
    public Observable<BaseResult<Data<List<QuestBean>>>> GetQuestionBycategory(String id) {
        return  ApiRetrofit.getDefault().GetQuestionBycategory(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
