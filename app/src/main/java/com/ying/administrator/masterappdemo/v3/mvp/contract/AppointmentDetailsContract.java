package com.ying.administrator.masterappdemo.v3.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import io.reactivex.Observable;

public interface AppointmentDetailsContract {
    interface Model extends BaseModel{
        //根据工单号获取工单详情
        Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID);
        Observable<BaseResult<Data<String>>> OrderIsCall(String OrderID, String IsCall);
        //取消订单
        Observable<BaseResult<Data>> UpdateSendOrderState(String OrderID,String State,String Reason);//State为-1取消订单
        //更新时间
        Observable<BaseResult<Data>> UpdateSendOrderUpdateTime(String OrderID,String UpdateDate,String EndDate);

        //预约成功
        Observable<BaseResult<Data>> AddOrderSuccess(String OrderID, String AppointmentState, String AppointmentMessage);
    }

    interface View extends BaseView{
        //根据工单号获取工单详情
        void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult);
        void OrderIsCall(BaseResult<Data<String>> baseResult);
        //取消订单
        void UpdateSendOrderState(BaseResult<Data> baseResult);
        //更新时间
        void UpdateSendOrderUpdateTime(BaseResult<Data> baseResult);
        //预约成功

        void AddOrderSuccess(BaseResult<Data> baseResult);

    }

    abstract class Persenter extends BasePresenter<View,Model>{
        //根据工单号获取工单详情
        public abstract void GetOrderInfo(String OrderID);
        public abstract void OrderIsCall(String OrderID,String IsCall);
        //取消订单
        public abstract void UpdateSendOrderState(String OrderID,String State,String Reason);
        //更新时间
        public abstract void UpdateSendOrderUpdateTime(String OrderID,String UpdateDate,String EndDate);
        //预约成功
        public abstract void AddOrderSuccess(String OrderID,String AppointmentState,String AppointmentMessage);
    }
}
