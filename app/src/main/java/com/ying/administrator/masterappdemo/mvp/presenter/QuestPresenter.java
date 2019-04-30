package com.ying.administrator.masterappdemo.mvp.presenter;


import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.QuestBean;
import com.ying.administrator.masterappdemo.mvp.contract.QuestContract;

import java.util.List;

public class QuestPresenter extends QuestContract.Presenter {

    @Override
    public void GetQuestionBycategory(String id) {
        mModel.GetQuestionBycategory(id)
                .subscribe(new BaseObserver<Data<List<QuestBean>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<QuestBean>>> value) {
                        mView.GetQuestionBycategory(value);
                    }
                });
    }
}
