package com.ying.administrator.masterappdemo.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.entity.GetFactorySeviceData;
import com.ying.administrator.masterappdemo.entity.Service;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/*预接单*/
public interface PendingOrderContract {
    interface Model extends BaseModel{
        //根据工单号获取工单详情
        Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID);

        //获取工厂配件信息
        Observable<BaseResult<GetFactoryData<Accessory>>> GetFactoryAccessory();

        //获取工厂服务信息
        Observable<BaseResult<GetFactorySeviceData<Service>>> GetFactoryService();

        //提交需要的配件信息
        Observable<BaseResult<Data>> AddOrderAccessory(RequestBody json);

        //提交需要的服务信息
        Observable<BaseResult<Data>> AddOrderService(RequestBody json);

        //提交配件服务和返件信息
        Observable<BaseResult<Data>> AddOrUpdateAccessoryServiceReturn(RequestBody json);


        //更新时间
        Observable<BaseResult<Data>> UpdateSendOrderUpdateTime(String OrderID,String UpdateDate);
    }

    interface View extends BaseView{
        //根据工单号获取工单详情
        void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult);

        //获取工厂配件信息
         void GetFactoryAccessory(BaseResult<GetFactoryData<Accessory>> baseResult);

        //获取工厂服务信息
        void GetFactoryService(BaseResult<GetFactorySeviceData<Service>> baseResult);

        //提交需要的配件信息
        void AddOrderAccessory(BaseResult<Data> baseResult);
        //提交需要的服务信息
        void AddOrderService(BaseResult<Data> baseResult);

        //提交配件服务和返件信息
        void AddOrUpdateAccessoryServiceReturn(BaseResult<Data> baseResult);

        //更新时间
        void UpdateSendOrderUpdateTime(BaseResult<Data> baseResult);
    }

    abstract  class Presenter extends BasePresenter<View,Model>{
        //根据工单号获取工单详情
        public abstract void GetOrderInfo(String OrderID);
        //获取工厂配件信息
        public abstract void GetFactoryAccessory();
        //获取工厂服务信息
        public abstract void GetFactoryService();
        //提交需要的配件信息
        public abstract void AddOrderAccessory(RequestBody json);
        //提交需要的服务信息
        public abstract void AddOrderService(RequestBody json);
         //提交配件服务和返件信息
        public abstract void AddOrUpdateAccessoryServiceReturn(RequestBody json);
        //更新时间
        public abstract void UpdateSendOrderUpdateTime(String OrderID,String UpdateDate);
    }


}
