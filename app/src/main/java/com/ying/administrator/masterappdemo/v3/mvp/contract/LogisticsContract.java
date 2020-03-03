package com.ying.administrator.masterappdemo.v3.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.Logistics;

import io.reactivex.Observable;

public interface LogisticsContract {
    interface Model extends BaseModel{
        //物流信息
        Observable<BaseResult<Data<Logistics>>> GetExpressInfo(String ExpressNo);
    }

    interface View extends BaseView{
        void GetExpressInfo(BaseResult<Data<Logistics>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetExpressInfo(String ExpressNo);
    }
}
