package com.ying.administrator.masterappdemo.mvp.contract;


import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public interface ComplaintContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<String>>> WorkerComplaint(String OrderID, String Content, String ComplaintType, String photo);
        Observable<BaseResult<Data<String>>> ComPlaintImg(RequestBody json);
    }

    interface View extends BaseView {
        void WorkerComplaint(BaseResult<Data<String>> baseResult);
        void ComPlaintImg(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void WorkerComplaint(String OrderID,String Content,String ComplaintType,String photo);
        public abstract void ComPlaintImg(RequestBody json);
    }
}
