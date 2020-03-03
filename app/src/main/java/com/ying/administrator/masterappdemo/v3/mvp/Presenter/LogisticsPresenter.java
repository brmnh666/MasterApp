package com.ying.administrator.masterappdemo.v3.mvp.Presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.Logistics;
import com.ying.administrator.masterappdemo.v3.mvp.contract.LogisticsContract;

public class LogisticsPresenter extends LogisticsContract.Presenter {
    @Override
    public void GetExpressInfo(String ExpressNo) {
        mModel.GetExpressInfo(ExpressNo)
                .subscribe(new BaseObserver<Data<Logistics>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<Logistics>> value) {
                        mView.GetExpressInfo(value);
                    }
                });
    }
}
