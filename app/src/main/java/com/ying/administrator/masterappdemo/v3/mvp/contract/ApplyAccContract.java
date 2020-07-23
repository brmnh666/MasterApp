package com.ying.administrator.masterappdemo.v3.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.AddressList;
import com.ying.administrator.masterappdemo.v3.bean.ApplicationResult;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public interface ApplyAccContract {
    interface Model extends BaseModel {
        //提交配件申请
        Observable<ApplicationResult> Application(RequestBody json);
        Observable<BaseResult<List<AddressList>>> GetAccountAddress(String UserId);
    }

    interface View extends BaseView {
        void Application(ApplicationResult baseResult);
        void GetAccountAddress(BaseResult<List<AddressList>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void Application(RequestBody json);
        public abstract void GetAccountAddress(String UserId);
    }
}
