package com.ying.administrator.masterappdemo.v3.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.v3.bean.GetExpressInfoResult;

import io.reactivex.Observable;

public interface LogisticsContract {
    interface Model extends BaseModel{
        //物流信息
        Observable<GetExpressInfoResult> GetExpressInfo(String ExpressNo);
    }

    interface View extends BaseView{
        void GetExpressInfo(GetExpressInfoResult baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetExpressInfo(String ExpressNo);
    }
}
