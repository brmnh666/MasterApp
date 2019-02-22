package com.ying.administrator.masterappdemo.mvp.contract;


import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.UserInfo;

import io.reactivex.Observable;


/*个人信息管理的接口请求页面*/
public interface InfoManageContract {
    interface Model extends BaseModel {

        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserID, String limit);


    }

    interface View extends BaseView {

        void GetUserInfoList(BaseResult<UserInfo> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {

        public abstract void GetUserInfoList(String UserID,String limit);

    }
}
