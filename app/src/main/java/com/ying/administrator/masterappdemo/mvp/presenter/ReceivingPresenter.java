package com.ying.administrator.masterappdemo.mvp.presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.RedPointData;
import com.ying.administrator.masterappdemo.mvp.contract.ReceivingContract;

public class ReceivingPresenter extends ReceivingContract.Presenter {
    @Override
    public void WorkerGetOrderRed(String UserId) {
        mModel.WorkerGetOrderRed(UserId)
                .subscribe(new BaseObserver<RedPointData>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<RedPointData> value) {
                        mView.WorkerGetOrderRed(value);
                    }
                });
    }
}
