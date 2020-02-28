package com.ying.administrator.masterappdemo.v3.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.AddressList;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.entity.Service;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public interface ApplicationAccessoriesContract {
    interface Model extends BaseModel{
        Observable<BaseResult<Data<String>>> GetFactoryAccessoryMoney(String OrderID, String FCategoryID, String SizeID, String Price);
        //获取工厂服务信息
        Observable<BaseResult<GetFactoryData<Service>>> GetFactoryService(String FCategoryID);
        Observable<BaseResult<List<AddressList>>> GetAccountAddress(String UserId);

        Observable<BaseResult<Data<String>>> UpdateOrderAddressByOrderID(String OrderID, String SendAddress);
        Observable<BaseResult<Data>> AddOrderAccessoryAndService(RequestBody json);
    }

    interface View extends BaseView{
        void GetFactoryAccessoryMoney(BaseResult<Data<String>> baseResult);
        //获取工厂服务信息
        void GetFactoryService(BaseResult<GetFactoryData<Service>> baseResult);
        void GetAccountAddress(BaseResult<List<AddressList>> baseResult);

        void UpdateOrderAddressByOrderID(BaseResult<Data<String>> baseResult);
        void AddOrderAccessoryAndService(BaseResult<Data> baseResult);

    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetFactoryAccessoryMoney(String OrderID, String FCategoryID,String SizeID,String Price);
        //获取工厂服务信息
        public abstract void GetFactoryService(String FCategoryID);

        public abstract void GetAccountAddress(String UserId);

        public abstract void UpdateOrderAddressByOrderID(String OrderID, String SendAddress);
        public abstract void AddOrderAccessoryAndService(RequestBody json);
    }
}
