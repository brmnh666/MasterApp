package com.ying.administrator.masterappdemo.mvp.presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.AddressList;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.Data2;
import com.ying.administrator.masterappdemo.entity.GAccessory;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.entity.Logistics;
import com.ying.administrator.masterappdemo.entity.Service;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.PendingOrderContract;

import java.util.List;

import okhttp3.RequestBody;

public class PendingOrderPresenter extends PendingOrderContract.Presenter {
    @Override
    public void GetOrderInfo(String OrderID) {
        mModel.GetOrderInfo(OrderID).subscribe(new BaseObserver<WorkOrder.DataBean>() {
            @Override
            protected void onHandleSuccess(BaseResult<WorkOrder.DataBean> value) {
                mView.GetOrderInfo(value);
            }
        });
    }

     @Override
     public void GetFactoryAccessory(String FProductTypeID) {
         mModel.GetFactoryAccessory(FProductTypeID).subscribe(new BaseObserver<GetFactoryData<Accessory>>() {
             @Override
             protected void onHandleSuccess(BaseResult<GetFactoryData<Accessory>> value) {
                 mView.GetFactoryAccessory(value);
             }
         });
     }

    @Override
    public void GetFactoryService(String FCategoryID) {

        mModel.GetFactoryService( FCategoryID).subscribe(new BaseObserver<GetFactoryData<Service>>() {
            @Override
            protected void onHandleSuccess(BaseResult<GetFactoryData<Service>> value) {
                mView.GetFactoryService(value);
            }
        });
    }

    @Override
    public void AddOrderAccessory(RequestBody json) {
        mModel.AddOrderAccessory(json).subscribe(new BaseObserver<Data>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data> value) {
                mView.AddOrderAccessory(value);
            }
        });
    }

    @Override
    public void AddOrderService(RequestBody json) {
        mModel.AddOrderService(json).subscribe(new BaseObserver<Data>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data> value) {
                mView.AddOrderService(value);
            }
        });
    }

    @Override
    public void AddOrderAccessoryAndService(RequestBody json) {
        mModel.AddOrderAccessoryAndService(json)
                .subscribe(new BaseObserver<Data>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data> value) {
                        mView.AddOrderAccessoryAndService(value);
                    }
                });
    }

    @Override
    public void AddOrUpdateAccessoryServiceReturn(RequestBody json) {
        mModel.AddOrUpdateAccessoryServiceReturn(json).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.AddOrUpdateAccessoryServiceReturn(value);
            }
        });
    }

    @Override
    public void UpdateSendOrderUpdateTime(String OrderID, String UpdateDate,String EndDate) {
        mModel.UpdateSendOrderUpdateTime(OrderID,UpdateDate,EndDate).subscribe(new BaseObserver<Data>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data> value) {
                mView.UpdateSendOrderUpdateTime(value);
            }
        });

    }

    @Override
    public void GetOrderAccessoryByOrderID(String OrderID) {
        mModel.GetOrderAccessoryByOrderID(OrderID).subscribe(new BaseObserver<List<GAccessory>>() {
            @Override
            protected void onHandleSuccess(BaseResult<List<GAccessory>> value) {
                mView.GetOrderAccessoryByOrderID(value);
            }
        });
    }

    @Override
    public void ServiceOrderPicUpload(RequestBody json) {
        mModel.ServiceOrderPicUpload(json).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.ServiceOrderPicUpload(value);
            }
        });
    }

    @Override
    public void ReuturnAccessoryPicUpload(RequestBody json) {
        mModel.ReuturnAccessoryPicUpload(json).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.ReuturnAccessoryPicUpload(value);
            }
        });
    }

    @Override
    public void FinishOrderPicUpload(RequestBody json) {
        mModel.FinishOrderPicUpload(json).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.FinishOrderPicUpload(value);
            }
        });
    }

    @Override
    public void OrderByondImgPicUpload(RequestBody json) {
        mModel.OrderByondImgPicUpload(json).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.OrderByondImgPicUpload(value);
            }
        });
    }

    @Override
    public void ApplyAccessoryphotoUpload(RequestBody json) {
        mModel.ApplyAccessoryphotoUpload(json).subscribe(new BaseObserver<Data2>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data2> value) {
                mView.ApplyAccessoryphotoUpload(value);
            }
        });
    }

    @Override
    public void ApplyBeyondMoney(String OrderID, String BeyondMoney, String BeyondDistance) {
        mModel.ApplyBeyondMoney(OrderID,BeyondMoney,BeyondDistance).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.ApplyBeyondMoney(value);
            }
        });
    }

    @Override
    public void PressFactoryAccount(String OrderID,String Content) {
        mModel.PressFactoryAccount(OrderID, Content).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.PressFactoryAccount(value);
            }
        });
    }
    @Override
    public void AddReturnAccessory(String OrderID,String ReturnAccessoryMsg,String PostMoney) {
        mModel.AddReturnAccessory(OrderID, ReturnAccessoryMsg, PostMoney).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.AddReturnAccessory(value);
            }
        });
    }
    @Override
    public void UpdateOrderState(String OrderID, String State,String Reason) {
        mModel.UpdateOrderState(OrderID,State,Reason).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.UpdateOrderState(value);
            }
        });
    }
    @Override
    public void ConfirmtoFreezeByOrderID(String OrderID, String type,String AccessoryId) {
        mModel.ConfirmtoFreezeByOrderID(OrderID,type,AccessoryId).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.ConfirmtoFreezeByOrderID(value);
            }
        });
    }

    @Override
    public void GetExpressInfo(String ExpressNo) {
        mModel.GetExpressInfo(ExpressNo)
                .subscribe(new BaseObserver<Data<Logistics>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<Logistics>> value) {
                        mView.GetExpressInfo(value);
                    }
                });
    }

    @Override
    public void GetAccountAddress(String UserId) {
        mModel.GetAccountAddress(UserId)
                .subscribe(new BaseObserver<List<AddressList>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<List<AddressList>> value) {
                        mView.GetAccountAddress(value);
                    }
                });
    }

    @Override
    public void UpdateOrderAddressByOrderID(String OrderID, String SendAddress) {
        mModel.UpdateOrderAddressByOrderID(OrderID, SendAddress)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.UpdateOrderAddressByOrderID(value);
                    }
                });
    }

    @Override
    public void GetFactoryAccessoryMoney(String OrderID, String FCategoryID, String SizeID,String Price) {
        mModel.GetFactoryAccessoryMoney(OrderID, FCategoryID,SizeID,Price)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.GetFactoryAccessoryMoney(value);
                    }
                });
    }
}
