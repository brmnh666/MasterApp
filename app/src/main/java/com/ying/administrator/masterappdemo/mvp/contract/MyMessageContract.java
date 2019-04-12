package com.ying.administrator.masterappdemo.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.BankCard;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.Message;
import com.ying.administrator.masterappdemo.entity.MessageData;
import com.ying.administrator.masterappdemo.entity.UserInfo;

import java.util.List;

import io.reactivex.Observable;


/*添加银行卡页面*/
public interface MyMessageContract {
    interface Model extends BaseModel{

     Observable<BaseResult<MessageData<List<Message>>>> GetMessageList(String UserID, String Type, String SubType,String limit, String page,String IsLook);

     /*更改为已读*/
     Observable<BaseResult<Data<String>>> AddOrUpdatemessage(String MessageID,String IsLook);


    }

    interface View extends BaseView{
        void GetMessageList(BaseResult<MessageData<List<Message>>> baseResult);
        void AddOrUpdatemessage(BaseResult<Data<String>> baseResult);

    }

    abstract class Presenter extends BasePresenter<View,Model>{

        public abstract void GetMessageList(String UserID,String Type,String SubType,String limit,String page,String IsLook);

        public abstract void AddOrUpdatemessage(String MessageID,String IsLook);

    }
}
