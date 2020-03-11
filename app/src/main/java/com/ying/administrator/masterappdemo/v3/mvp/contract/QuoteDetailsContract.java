package com.ying.administrator.masterappdemo.v3.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.Data3;
import com.ying.administrator.masterappdemo.entity.WXOfferQuery;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import java.util.List;

import io.reactivex.Observable;

public interface QuoteDetailsContract  {
    interface Model extends BaseModel{
        //根据工单号获取工单详情
        Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID);
        Observable<BaseResult<Data<String>>> WXOrderOffer(String SenUserId, String Price, String OrderId, String Reason);
        Observable<BaseResult<Data3<List<WXOfferQuery>>>> WXOfferQuery(String SenUserId, String Price, String OrderId, String Reason);
    }

    interface View extends BaseView{
        //根据工单号获取工单详情
        void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult);
        void WXOrderOffer(BaseResult<Data<String>> baseResult);
        void WXOfferQuery(BaseResult<Data3<List<WXOfferQuery>>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        //根据工单号获取工单详情
        public abstract void GetOrderInfo(String OrderID);
        public abstract void WXOrderOffer(String SenUserId, String Price, String OrderId, String Reason);
        public abstract void WXOfferQuery(String SenUserId, String Price, String OrderId, String Reason);
    }
}
