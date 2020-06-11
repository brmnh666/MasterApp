package com.ying.administrator.masterappdemo.mvp.contract;


import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Area;
import com.ying.administrator.masterappdemo.entity.City;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.District;
import com.ying.administrator.masterappdemo.entity.MyServiceArea;
import com.ying.administrator.masterappdemo.entity.Province;

import java.util.List;

import io.reactivex.Observable;


public interface AddServiceContract {
    interface Model extends BaseModel {
        Observable<BaseResult<List<Province>>> GetProvince();
        Observable<BaseResult<Data<List<City>>>> GetCity(String parentcode);
        Observable<BaseResult<Data<List<Area>>>> GetArea(String parentcode);
        Observable<BaseResult<Data<List<District>>>> GetDistrict(String parentcode,int code);
        Observable<BaseResult<MyServiceArea>> GetServiceRangeByUserID(String UserID);
        Observable<BaseResult<Data<String>>> AddorUpdateServiceArea(String UserID ,String ServiceAreaJsonStr);
    }

    interface View extends BaseView {
        void GetProvince(BaseResult<List<Province>> baseResult);
        void GetCity(BaseResult<Data<List<City>>> baseResult);
        void GetArea(BaseResult<Data<List<Area>>> baseResult);
        void GetDistrict(BaseResult<Data<List<District>>> baseResult,int code);
        void GetServiceRangeByUserID(BaseResult<MyServiceArea> baseResult);
        void AddorUpdateServiceArea(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetProvince();

        public abstract void GetCity(String parentcode);

        public abstract void GetArea(String parentcode);
        public abstract void GetDistrict(String parentcode,int code);

        public abstract void GetServiceRangeByUserID(String UserID);

        public abstract void AddorUpdateServiceArea(String UserID ,String ServiceAreaJsonStr);
    }


}
