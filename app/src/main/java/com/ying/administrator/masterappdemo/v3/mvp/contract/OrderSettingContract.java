package com.ying.administrator.masterappdemo.v3.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;

import io.reactivex.Observable;

public interface OrderSettingContract {
    interface Model extends BaseModel{
        Observable<BaseResult<Data<String>>> updateTeamNumber(String UserID, String teamNumber);
        Observable<BaseResult<Data<String>>> IsOrNoTruck(String UserID, String IsOrNoTruck);
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserID, String limit);
    }

    interface View extends BaseView{
        void updateTeamNumber(BaseResult<Data<String >> baseObserver);
        void IsOrNoTruck(BaseResult<Data<String >> baseResult);
        void GetUserInfoList(BaseResult<UserInfo> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void updateTeamNumber(String UserID, String teamNumber);
        public abstract void IsOrNoTruck(String UserID, String IsOrNoTruck);
        public abstract void GetUserInfoList(String UserID,String limit);
    }
}
