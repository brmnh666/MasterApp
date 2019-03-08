package com.ying.administrator.masterappdemo.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public interface CompleteWorkOrderContract {
    interface Model extends BaseModel{
        //根据工单号获取工单详情
        Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID);

        //上传服务图片
        Observable<BaseResult<Data<String>>> ServiceOrderPicUpload(RequestBody json, int code);
        //上传维修图片
        Observable<BaseResult<Data<String>>> FinishOrderPicUpload(RequestBody json,int code);
        //获取返件图片
        Observable<BaseResult<String>> GetReturnAccessoryByOrderID(String OrderID);
    }

    interface View extends BaseView{
        //根据工单号获取工单详情
        void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult);

        //上传服务图片
        void ServiceOrderPicUpload(BaseResult<Data<String>> baseResult, int code);

        //上传维修图片
        void FinishOrderPicUpload(BaseResult<Data<String>> baseResult, int code);

        //获取返件图片
         void GetReturnAccessoryByOrderID(BaseResult<String> baseResult);
    }
   abstract class Presenter extends BasePresenter<View,Model>{
       //根据工单号获取工单详情
       public abstract void GetOrderInfo(String OrderID);

       //上传服务图片
       public abstract void ServiceOrderPicUpload(RequestBody json,int code);
       //上传维修图片
       public abstract void FinishOrderPicUpload(RequestBody json,int code);
       //获取返件图片
       public abstract void GetReturnAccessoryByOrderID(String OrderID);
   }
}
