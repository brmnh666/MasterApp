package com.ying.administrator.masterappdemo.v3.mvp.Presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver2;
import com.ying.administrator.masterappdemo.v3.bean.GetExpressInfoResult;
import com.ying.administrator.masterappdemo.v3.mvp.contract.LogisticsContract;

public class LogisticsPresenter extends LogisticsContract.Presenter {
    @Override
    public void GetExpressInfo(String ExpressNo) {
        mModel.GetExpressInfo(ExpressNo)
                .subscribe(new BaseObserver2<GetExpressInfoResult>() {
                    @Override
                    protected void onHandleSuccess(GetExpressInfoResult value) {
                        mView.GetExpressInfo(value);
                    }
                });
    }
}
