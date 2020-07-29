package com.ying.administrator.masterappdemo.v3.mvp.Presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseObserver2;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.v3.bean.DeleteAccessoryResult;
import com.ying.administrator.masterappdemo.v3.mvp.contract.AccessoriesDetailsContract;

public class AccessoriesDetailsPresenter extends AccessoriesDetailsContract.Presenter {
    @Override
    public void GetOrderInfo(String OrderID) {
        mModel.GetOrderInfo(OrderID)
                .subscribe(new BaseObserver<WorkOrder.DataBean>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<WorkOrder.DataBean> value) {
                        mView.GetOrderInfo(value);
                    }
                });
    }

    @Override
    public void DeleteAccessory(String AccessoryIDs) {
        mModel.DeleteAccessory(AccessoryIDs)
                .subscribe(new BaseObserver2<DeleteAccessoryResult>() {
                    @Override
                    protected void onHandleSuccess(DeleteAccessoryResult value) {
                        mView.DeleteAccessory(value);
                    }
                });
    }
}
