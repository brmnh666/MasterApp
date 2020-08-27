package com.ying.administrator.masterappdemo.v3.mvp.model;

import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;
import com.ying.administrator.masterappdemo.v3.bean.CourseResult;
import com.ying.administrator.masterappdemo.v3.mvp.contract.CourseContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CourseModel implements CourseContract.Model {

    @Override
    public Observable<CourseResult> GetCourse(String SubCategoryID, String ProductTypeID, String BrandID, String TypeID,String ProdModelID, String UserID) {
        return ApiRetrofit.getDefault().GetCourse(SubCategoryID, ProductTypeID, BrandID, TypeID,ProdModelID, UserID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
