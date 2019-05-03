package com.ying.administrator.masterappdemo.mvp.contract;


import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.QuestBean;
import com.ying.administrator.masterappdemo.entity.QuestResult;

import java.util.List;

import io.reactivex.Observable;


public interface QuestContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<List<QuestBean>>>> GetQuestionBycategory(String id);
        Observable<BaseResult<QuestResult>> Calculate(String Answer);

    }

    interface View extends BaseView {
        void GetQuestionBycategory(BaseResult<Data<List<QuestBean>>> baseResult);
        void Calculate(BaseResult<QuestResult> baseResult);

    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetQuestionBycategory(String id);
        public abstract void Calculate(String Answer);

    }


}
