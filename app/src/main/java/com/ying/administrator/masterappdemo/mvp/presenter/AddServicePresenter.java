package com.ying.administrator.masterappdemo.mvp.presenter;


import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Area;
import com.ying.administrator.masterappdemo.entity.City;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.District;
import com.ying.administrator.masterappdemo.entity.MyServiceArea;
import com.ying.administrator.masterappdemo.entity.Province;
import com.ying.administrator.masterappdemo.mvp.contract.AddServiceContract;

import java.util.List;

public class AddServicePresenter extends AddServiceContract.Presenter {


    @Override
    public void GetProvince() {
        mModel.GetProvince()
                .subscribe(new BaseObserver<List<Province>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<List<Province>> value) {
                        mView.GetProvince(value);
                    }
                });
    }

    @Override
    public void GetCity(String parentcode) {
        mModel.GetCity(parentcode)
                .subscribe(new BaseObserver<Data<List<City>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<City>>> value) {
                        mView.GetCity(value);
                    }
                });
    }

    @Override
    public void GetArea(String parentcode) {
        mModel.GetArea(parentcode)
                .subscribe(new BaseObserver<Data<List<Area>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<Area>>> value) {
                        mView.GetArea(value);
                    }
                });
    }

    @Override
    public void GetDistrict(String parentcode, final int code) {
        mModel.GetDistrict(parentcode,code)
                .subscribe(new BaseObserver<Data<List<District>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<District>>> value) {
                        mView.GetDistrict(value,code);
                    }
                });
    }

    @Override
    public void GetServiceRangeByUserID(String UserID) {
        mModel.GetServiceRangeByUserID(UserID)
                .subscribe(new BaseObserver<MyServiceArea>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<MyServiceArea> value) {
                        mView.GetServiceRangeByUserID(value);
                    }
                });
    }

    @Override
    public void AddorUpdateServiceArea(String UserID,String ServiceAreaJsonStr) {
        mModel.AddorUpdateServiceArea(UserID,ServiceAreaJsonStr)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.AddorUpdateServiceArea(value);
                    }
                });
    }
}
