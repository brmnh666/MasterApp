package com.ying.administrator.masterappdemo.mvp.presenter;


import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Category;
import com.ying.administrator.masterappdemo.entity.CategoryData;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.Skill;
import com.ying.administrator.masterappdemo.mvp.contract.AddSkillsContract;

import java.util.List;

public class AddSkillsPresenter extends AddSkillsContract.Presenter {


    @Override
    public void GetFactoryCategory() {
        mModel.GetFactoryCategory()
                .subscribe(new BaseObserver<CategoryData>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<CategoryData> value) {
                        mView.GetFactoryCategory(value);
                    }
                });
    }

    @Override
    public void GetChildFactoryCategory(String ParentID) {
        mModel.GetChildFactoryCategory(ParentID)
                .subscribe(new BaseObserver<CategoryData>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<CategoryData> value) {
                        mView.GetChildFactoryCategory(value);
                    }
                });
    }

    @Override
    public void GetAccountSkill(String UserID) {
        mModel.GetAccountSkill(UserID)
                .subscribe(new BaseObserver<List<Skill>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<List<Skill>> value) {
                        mView.GetAccountSkill(value);
                    }
                });
    }

    @Override
    public void UpdateAccountSkillData(String UserID, String NodeIds) {
        mModel.UpdateAccountSkillData(UserID,NodeIds)
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<String> value) {
                        mView.UpdateAccountSkillData(value);
                    }
                });
    }
}
