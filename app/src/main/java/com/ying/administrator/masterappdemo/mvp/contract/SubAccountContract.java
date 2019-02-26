package com.ying.administrator.masterappdemo.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.SubUserInfo;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import java.util.List;

import io.reactivex.Observable;

public interface SubAccountContract {
    interface Model extends BaseModel {
        /*获取账号信息列表*/
        Observable <BaseResult<UserInfo>> GetUserInfoList(String UserID, String limit);


    }

    interface View extends BaseView {
        /*获取账号信息列表*/
        void GetUserInfoList(BaseResult<UserInfo> baseResult);

    }

    abstract class Presenter extends BasePresenter<SubAccountContract.View,SubAccountContract.Model> {
        /*获取账号信息列表*/
        public abstract void GetUserInfoList(String UserID, String limit);

    }

}
