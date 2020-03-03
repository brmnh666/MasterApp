package com.ying.administrator.masterappdemo.v3.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import io.reactivex.Observable;

public interface ServingDetailContract {
    interface Model extends BaseModel{
        //根据工单号获取工单详情
        Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID);
        //提交快递信息
        Observable<BaseResult<Data<String>>> AddReturnAccessory(String OrderID, String ReturnAccessoryMsg, String PostMoney);
        //修改订单状态
        Observable<BaseResult<Data<String>>> UpdateOrderState(String OrderID,String State,String Reason);
        //更新时间
        Observable<BaseResult<Data>> UpdateSendOrderUpdateTime(String OrderID,String UpdateDate,String EndDate);
    }

    interface View extends BaseView{
        //根据工单号获取工单详情
        void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult);
        void AddReturnAccessory(BaseResult<Data<String>> baseResult);
        //修改订单状态
        void UpdateOrderState(BaseResult<Data<String>> baseResult);
        //更新时间
        void UpdateSendOrderUpdateTime(BaseResult<Data> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        //根据工单号获取工单详情
        public abstract void GetOrderInfo(String OrderID);
        public abstract void AddReturnAccessory(String OrderID,String ReturnAccessoryMsg,String PostMoney);
        public abstract void UpdateOrderState(String OrderID,String State,String Reason);
        //更新时间
        public abstract void UpdateSendOrderUpdateTime(String OrderID,String UpdateDate,String EndDate);
    }
}
