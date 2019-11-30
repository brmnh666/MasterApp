package com.ying.administrator.masterappdemo.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.BankCard;
import com.ying.administrator.masterappdemo.entity.Bill;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;

import java.util.List;

import io.reactivex.Observable;


/*添加银行卡页面*/
public interface CardContract {
    interface Model extends BaseModel{
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserId, String limit);
        Observable<BaseResult<Data<String>>> AddorUpdateAccountPayInfo(String UserId,String PayInfoCode ,String PayInfoName,String PayNo,String PayName);
        Observable<BaseResult<List<BankCard>>> GetAccountPayInfoList(String UserId);
        Observable<BaseResult<Data<String>>> GetBankNameByCardNo(String CardNo);
        Observable<BaseResult<Data<String>>> DeleteAccountPayInfo(String UserId,String PayInfoCode ,String PayInfoName,String PayNo,String PayName,String IsUse,String AccountPayID);

    }

    interface View extends BaseView{
        void GetUserInfoList(BaseResult<UserInfo> baseResult);
        void AddorUpdateAccountPayInfo(BaseResult<Data<String>> baseResult);
        void GetAccountPayInfoList(BaseResult<List<BankCard>> baseResult);
        void GetBankNameByCardNo(BaseResult<Data<String>> baseResult);
        void DeleteAccountPayInfo(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetUserInfoList(String UserId,String limit);
        public abstract void AddorUpdateAccountPayInfo(String UserId,String PayInfoCode ,String PayInfoName,String PayNo,String PayName);
        public abstract void GetAccountPayInfoList(String UserId);
        public abstract void GetBankNameByCardNo(String CardNo);
        public abstract void DeleteAccountPayInfo(String UserId,String PayInfoCode ,String PayInfoName,String PayNo,String PayName,String IsUse,String AccountPayID);
    }
}
