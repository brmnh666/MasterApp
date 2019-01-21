package com.ying.administrator.masterappdemo.mvp.presenter;


import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.GetOrderListForMeContract;

public class GetOrderListForMePresenter extends GetOrderListForMeContract.Presenter {



    @Override
    public void GetOrderInfoListForMe(String state, String page, String limit, String UserID) {
        mModel.GetOrderInfoListForMe(state,page,limit,UserID).subscribe(new BaseObserver<WorkOrder>() {
            @Override
            protected void onHandleSuccess(BaseResult<WorkOrder> value) {
                mView.GetOrderInfoListForMe(value);
            }
        });

    }


}
