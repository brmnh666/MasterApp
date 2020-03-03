package com.ying.administrator.masterappdemo.v3.mvp.Presenter;


import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Track;
import com.ying.administrator.masterappdemo.v3.mvp.contract.TrackContract;

import java.util.List;

public class TrackPresenter extends TrackContract.Presenter {
    @Override
    public void GetOrderRecordByOrderID(String OrderId) {
        mModel.GetOrderRecordByOrderID(OrderId)
                .subscribe(new BaseObserver<List<Track>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<List<Track>> value) {
                        mView.GetOrderRecordByOrderID(value);
                    }
                });
    }
}
