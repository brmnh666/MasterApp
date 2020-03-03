package com.ying.administrator.masterappdemo.v3.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Track;

import java.util.List;

import io.reactivex.Observable;

public interface TrackContract {
    interface Model extends BaseModel {
        Observable<BaseResult<List<Track>>> GetOrderRecordByOrderID(String OrderId);
    }

    interface View extends BaseView {
        void GetOrderRecordByOrderID(BaseResult<List<Track>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetOrderRecordByOrderID(String OrderId);
    }
}
