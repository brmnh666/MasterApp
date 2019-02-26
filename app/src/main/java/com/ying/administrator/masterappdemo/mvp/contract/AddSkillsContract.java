package com.ying.administrator.masterappdemo.mvp.contract;


import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Category;
import com.ying.administrator.masterappdemo.entity.Data;

import java.util.List;

import io.reactivex.Observable;


public interface AddSkillsContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<List<Category>>>> GetFactoryCategory();
       //  Observable<BaseResult<Data<List<Category>>>> GetChildFactoryCategory(String ParentID);
        //获取该账号下的技能
        Observable<BaseResult<String>> GetAccountSkill(String UserID);

    }

    interface View extends BaseView {
        void GetFactoryCategory(BaseResult<Data<List<Category>>> baseResult);
//        void GetChildFactoryCategory(BaseResult<Data<List<Category>>> baseResult);
        //获取该账号下的技能
        void GetAccountSkill(BaseResult<String> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetFactoryCategory();
//        public abstract void GetChildFactoryCategory(String ParentID);
        //获取该账号下的技能
        public abstract void GetAccountSkill(String UserID);
    }
}
