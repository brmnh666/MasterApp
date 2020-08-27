package com.ying.administrator.masterappdemo.v3.mvp.Presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver2;
import com.ying.administrator.masterappdemo.v3.bean.CourseResult;
import com.ying.administrator.masterappdemo.v3.mvp.contract.CourseContract;

public class CoursePresenter extends CourseContract.Presenter {

    @Override
    public void GetCourse(String SubCategoryID, String ProductTypeID, String BrandID, String TypeID,String ProdModelID, String UserID) {
        mModel.GetCourse(SubCategoryID, ProductTypeID, BrandID, TypeID,ProdModelID, UserID)
                .subscribe(new BaseObserver2<CourseResult>() {
                    @Override
                    protected void onHandleSuccess(CourseResult value) {
                        mView.GetCourse(value);
                    }
                });
    }
}
