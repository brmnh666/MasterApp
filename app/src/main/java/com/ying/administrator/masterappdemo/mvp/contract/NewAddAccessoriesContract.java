package com.ying.administrator.masterappdemo.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;

import io.reactivex.Observable;

/*预接单*/
public interface NewAddAccessoriesContract {
    interface Model extends BaseModel{
        //获取工厂配件信息
        Observable<BaseResult<GetFactoryData<Accessory>>> GetFactoryAccessory(String FProductTypeID);
    }

    interface View extends BaseView{
        //获取工厂配件信息
         void GetFactoryAccessory(BaseResult<GetFactoryData<Accessory>> baseResult);
    }

    abstract  class Presenter extends BasePresenter<View,Model>{
        //获取工厂配件信息
        public abstract void GetFactoryAccessory(String FProductTypeID);
    }

}
