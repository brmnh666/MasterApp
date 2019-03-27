package com.ying.administrator.masterappdemo.mvp.contract;


import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;

import io.reactivex.Observable;


public interface OpinionContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<String>>> AddOpinion(String UserID,String BackType,String Content);
    }

    interface View extends BaseView {
        void AddOpinion(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void AddOpinion(String UserID,String BackType,String Content);
    }


}
