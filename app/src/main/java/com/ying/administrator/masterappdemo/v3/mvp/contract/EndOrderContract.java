package com.ying.administrator.masterappdemo.v3.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.v3.bean.OrderListResult;

import io.reactivex.Observable;

public interface EndOrderContract {
    interface Model extends BaseModel{
        Observable<OrderListResult> GetOrderList(String Search, String State, String page, String limit);
    }

    interface View extends BaseView{
        void GetOrderList(OrderListResult baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetOrderList(String Search, String State, String page,String limit);
    }
}
