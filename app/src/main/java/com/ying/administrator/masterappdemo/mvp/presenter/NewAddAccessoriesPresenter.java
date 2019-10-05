package com.ying.administrator.masterappdemo.mvp.presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.Data2;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.mvp.contract.NewAddAccessoriesContract;

import okhttp3.RequestBody;

public class NewAddAccessoriesPresenter extends NewAddAccessoriesContract.Presenter {

     @Override
     public void GetFactoryAccessory(String FProductTypeID) {
         mModel.GetFactoryAccessory(FProductTypeID).subscribe(new BaseObserver<GetFactoryData<Accessory>>() {
             @Override
             protected void onHandleSuccess(BaseResult<GetFactoryData<Accessory>> value) {
                 mView.GetFactoryAccessory(value);
             }
         });
     }
    @Override
    public void ApplyAccessoryphotoUpload(RequestBody body) {
        mModel.ApplyAccessoryphotoUpload(body).subscribe(new BaseObserver<Data2>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data2> value) {
                mView.ApplyAccessoryphotoUpload(value);
            }
        });
    }
}
