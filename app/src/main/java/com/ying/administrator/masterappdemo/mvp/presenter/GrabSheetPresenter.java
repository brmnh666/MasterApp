package com.ying.administrator.masterappdemo.mvp.presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.mvp.contract.GrabSheetContract;

public class GrabSheetPresenter extends GrabSheetContract.Presenter {
    @Override
    public void AddGrabsheetapply(String OrderID, String UserID) {
        mModel.AddGrabsheetapply(OrderID,UserID)
               .subscribe(new BaseObserver<String>() {
                   @Override
                   protected void onHandleSuccess(BaseResult<String> value) {
                    mView.AddGrabsheetapply(value);

                   }
               });
    }
}
