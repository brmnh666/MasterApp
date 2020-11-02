package com.ying.administrator.masterappdemo.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.RedPointData;

import io.reactivex.Observable;


/*接单页面*/
public interface ReceivingContract {
    interface Model extends BaseModel{
        Observable<BaseResult<RedPointData>> WorkerGetOrderRed(String UserId);


    }

    interface View extends BaseView{
        void WorkerGetOrderRed(BaseResult<RedPointData> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void WorkerGetOrderRed(String UserId);
    }
}
