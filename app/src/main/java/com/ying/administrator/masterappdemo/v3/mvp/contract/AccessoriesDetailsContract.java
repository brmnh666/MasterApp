package com.ying.administrator.masterappdemo.v3.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.v3.bean.ConfirmReceiptResult;
import com.ying.administrator.masterappdemo.v3.bean.ConfirmReturnResult;
import com.ying.administrator.masterappdemo.v3.bean.DeleteAccessoryResult;
import com.ying.administrator.masterappdemo.v3.bean.UpdateAccessoryResult;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public interface AccessoriesDetailsContract {
    interface Model extends BaseModel{
        //根据工单号获取工单详情
        Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID);
        Observable<DeleteAccessoryResult> DeleteAccessory(String AccessoryIDs);
        Observable<UpdateAccessoryResult> UpdateAccessory(RequestBody json);

        Observable<ConfirmReceiptResult> ConfirmReceipt(String AccessoryID);
        Observable<ConfirmReturnResult> ConfirmReturn(RequestBody json);
    }

    interface View extends BaseView{
        //根据工单号获取工单详情
        void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult);
        void DeleteAccessory(DeleteAccessoryResult baseResult);
        void UpdateAccessory(UpdateAccessoryResult baseResult);

        void ConfirmReceipt(ConfirmReceiptResult baseResult);
        void ConfirmReturn(ConfirmReturnResult baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        //根据工单号获取工单详情
        public abstract void GetOrderInfo(String OrderID);
        public abstract void DeleteAccessory(String AccessoryIDs);
        public abstract void UpdateAccessory(RequestBody json);

        public abstract void ConfirmReceipt(String AccessoryID);
        public abstract void ConfirmReturn(RequestBody json);
    }
}
