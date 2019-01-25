package com.ying.administrator.masterappdemo.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.AccessoryData;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import io.reactivex.Observable;

/*预接单*/
public interface PendingOrderContract {
    interface Model extends BaseModel{
        //根据工单号获取工单详情
        Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID);

        //获取工厂配件信息
        Observable<BaseResult<AccessoryData<Accessory>>> GetFactoryAccessory();
    }

    interface View extends BaseView{
        //根据工单号获取工单详情
        void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult);

        //获取工厂配件信息
         void GetFactoryAccessory(BaseResult<AccessoryData<Accessory>> baseResult);
    }

    abstract  class Presenter extends BasePresenter<View,Model>{
        //根据工单号获取工单详情
        public abstract void GetOrderInfo(String OrderID);

        //获取工厂配件信息
        public abstract void GetFactoryAccessory();
    }


}