package com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SPUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.SubUserInfo;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.GetOrderListForMeContract;
import com.ying.administrator.masterappdemo.mvp.model.GetOrderListForMeModel;
import com.ying.administrator.masterappdemo.mvp.presenter.GetOrderListForMePresenter;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class Wait_Return_Fragment extends BaseFragment<GetOrderListForMePresenter, GetOrderListForMeModel> implements GetOrderListForMeContract.View {

    private View view;
    private String userID; //用户id
    private RefreshLayout mRefreshLayout;
    private RecyclerView recyclerView;
    private WorkOrder workOrder;
    private ArrayList<WorkOrder.DataBean> list;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view==null){
            view=inflater.inflate(R.layout.fragment_order_receiving,container,false);
            SPUtils spUtils = SPUtils.getInstance("token");
            userID = spUtils.getString("userName"); //获取用户id
            initView();
            initListener();
            
        }
        return view;
    }


    private void initView() {
        list=new ArrayList<>();
        recyclerView=view.findViewById(R.id.recyclerview_order_receiving);
        mRefreshLayout=view.findViewById(R.id.refreshLayout);
    }


    private void initListener() {

    }

    @Override
    public void WorkerGetOrderList(BaseResult<WorkOrder> baseResult) {

    }

    @Override
    public void AddOrderfailureReason(BaseResult<Data> baseResult) {

    }

    @Override
    public void AddOrderSuccess(BaseResult<Data> baseResult) {

    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {

    }

    @Override
    public void GetChildAccountByParentUserID(BaseResult<List<SubUserInfo.SubUserInfoDean>> baseResult) {

    }

    @Override
    public void ChangeSendOrder(BaseResult<Data> baseResult) {

    }

    @Override
    public void UpdateSendOrderState(BaseResult<Data> baseResult) {

    }

    @Override
    public void UpdateContinueServiceState(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void PressFactoryAccount(BaseResult<Data<String>> baseResult) {

    }
}
