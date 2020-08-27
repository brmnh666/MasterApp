package com.ying.administrator.masterappdemo.v3.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.v3.bean.CourseResult;

import io.reactivex.Observable;

public interface CourseContract {
    interface Model extends BaseModel{
        Observable<CourseResult> GetCourse(String SubCategoryID, String ProductTypeID, String BrandID, String TypeID,String ProdModelID, String UserID);
    }

    interface View extends BaseView{
        void GetCourse(CourseResult baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetCourse(String SubCategoryID, String ProductTypeID, String BrandID, String TypeID,String ProdModelID,String UserID);
    }
}
