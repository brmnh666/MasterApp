package com.ying.administrator.masterappdemo.mvp.presenter;


import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Category;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.mvp.contract.AddSkillsContract;

import java.util.List;

public class AddSkillsPresenter extends AddSkillsContract.Presenter {


    @Override
    public void GetFactoryCategory() {
        mModel.GetFactoryCategory()
                .subscribe(new BaseObserver<Data<List<Category>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<Category>>> value) {
                        mView.GetFactoryCategory(value);
                    }
                });
    }

    @Override
    public void GetAccountSkill(String UserID) {
        mModel.GetAccountSkill(UserID)
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<String> value) {
                        mView.GetAccountSkill(value);
                    }
                });
    }

}
