package com.ying.administrator.masterappdemo.v3.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.AddressList;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public interface PersonalInformationContract {
    interface Model extends BaseModel{

        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserID, String limit);
        /*个人头像修改*/
        Observable<BaseResult<Data<String>>> UploadAvator(RequestBody json);
        Observable<BaseResult<List<AddressList>>> GetAccountAddress(String UserId);
        Observable<BaseResult<Data<String>>> UpdateEmergencyContact(String UserId, String emergencyContact);

    }

    interface View extends BaseView{
        void GetUserInfoList(BaseResult<UserInfo> baseResult);
        /*个人头像修改*/
        void UploadAvator(BaseResult<Data<String>> baseResult);
        void GetAccountAddress(BaseResult<List<AddressList>> baseResult);
        void UpdateEmergencyContact(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetUserInfoList(String UserID,String limit);
        /*个人头像修改*/
        public abstract void UploadAvator(RequestBody json);
        public abstract void GetAccountAddress(String UserId);
        public abstract void UpdateEmergencyContact(String UserId, String emergencyContact);
    }
}
