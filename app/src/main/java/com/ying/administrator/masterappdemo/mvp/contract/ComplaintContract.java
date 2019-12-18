package com.ying.administrator.masterappdemo.mvp.contract;


import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.ComplaintList;
import com.ying.administrator.masterappdemo.entity.Data;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public interface ComplaintContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<String>>> WorkerComplaint(String OrderID, String Content, String ComplaintType, String photo);
        Observable<BaseResult<Data<String>>> ComPlaintImg(RequestBody json);
        Observable<BaseResult<List<ComplaintList>>> GetComplaintListByOrderId(String OrderId, String UserID);
    }

    interface View extends BaseView {
        void WorkerComplaint(BaseResult<Data<String>> baseResult);
        void ComPlaintImg(BaseResult<Data<String>> baseResult);
        void GetComplaintListByOrderId(BaseResult<List<ComplaintList>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void WorkerComplaint(String OrderID,String Content,String ComplaintType,String photo);
        public abstract void ComPlaintImg(RequestBody json);
        public abstract void GetComplaintListByOrderId(String OrderId,String UserID);
    }
}
