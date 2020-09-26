package com.ying.administrator.masterappdemo.v3.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WXpayInfo;
import com.ying.administrator.masterappdemo.v3.bean.GetContractSigningDepositMoneyResult;
import com.ying.administrator.masterappdemo.v3.bean.GetSignContractManageResult;
import com.ying.administrator.masterappdemo.v3.bean.SaveAutographResult;
import com.ying.administrator.masterappdemo.v3.bean.UploadAutographPicUrlResult;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public interface SignContract {
    interface Model extends BaseModel{
        Observable<GetSignContractManageResult> GetSignContractManage ();
        Observable<UploadAutographPicUrlResult> UploadAutographPicUrl (RequestBody json);
        Observable<SaveAutographResult> SaveAutograph (String AutographPicUrl);
        Observable<GetContractSigningDepositMoneyResult> GetContractSigningDepositMoney ();
        Observable<GetSignContractManageResult> GetSigningSuccessContract  ();
        Observable<BaseResult<Data<String>>> GetOrderStr(String userid, String TotalAmount);
        Observable<BaseResult<Data<WXpayInfo>>> GetWXOrderStr(String userid, String TotalAmount);
    }

    interface View extends BaseView{
        void GetSignContractManage(GetSignContractManageResult baseResult);
        void UploadAutographPicUrl(UploadAutographPicUrlResult baseResult);
        void SaveAutograph (SaveAutographResult baseResult);
        void GetContractSigningDepositMoney (GetContractSigningDepositMoneyResult baseResult);
        void GetSigningSuccessContract  (GetSignContractManageResult baseResult);
        void GetOrderStr(BaseResult<Data<String>> baseResult);
        void GetWXOrderStr(BaseResult<Data<WXpayInfo>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetSignContractManage();
        public abstract void UploadAutographPicUrl(RequestBody json);
        public abstract void SaveAutograph (String AutographPicUrl);
        public abstract void GetContractSigningDepositMoney ();
        public abstract void GetSigningSuccessContract  ();
        public abstract void GetOrderStr(String userid,String TotalAmount);
        public abstract void GetWXOrderStr(String userid,String TotalAmount);
    }
}