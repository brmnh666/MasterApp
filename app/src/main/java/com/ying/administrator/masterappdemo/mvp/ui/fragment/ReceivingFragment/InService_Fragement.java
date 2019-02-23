package com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.SubUserInfo;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.GetOrderListForMeContract;
import com.ying.administrator.masterappdemo.mvp.model.GetOrderListForMeModel;
import com.ying.administrator.masterappdemo.mvp.presenter.GetOrderListForMePresenter;
import com.ying.administrator.masterappdemo.mvp.ui.activity.CompleteWorkOrderActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Order_Add_Accessories_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Order_details_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.In_Service_Adapter;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseFragment;
import com.ying.administrator.masterappdemo.R;
import java.util.ArrayList;
import java.util.List;

/*
* 服务中页面
* */
  public class InService_Fragement extends BaseFragment<GetOrderListForMePresenter, GetOrderListForMeModel> implements GetOrderListForMeContract.View {
     private View view;
     private RecyclerView recyclerView;
     private In_Service_Adapter in_service_adapter;
     private ArrayList<WorkOrder.DataBean> list;
     private RefreshLayout mRefreshLayout;
     private WorkOrder workOrder;
     private String userID; //用户id
     private int pageIndex = 1;  //默认当前页数为1
     @Nullable
     @Override
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



    public void initView() {
        list=new ArrayList<>();
        recyclerView=view.findViewById(R.id.recyclerview_order_receiving);
        mRefreshLayout=view.findViewById(R.id.refreshLayout);
        in_service_adapter=new In_Service_Adapter(R.layout.item_in_service,list);
        recyclerView.setAdapter(in_service_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mPresenter.GetOrderInfoListForMe("4",Integer.toString(pageIndex),"4",userID);
    }
    private void initListener() {

        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex=1;
                list.clear();
                mPresenter.GetOrderInfoListForMe("4", Integer.toString(pageIndex), "4",userID);
                in_service_adapter.notifyDataSetChanged();
                refreshlayout.finishRefresh();
            }
        });


        //上拉加载更多
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageIndex++; //页数加1
                mPresenter.GetOrderInfoListForMe("4", Integer.toString(pageIndex), "4",userID);
                in_service_adapter.notifyDataSetChanged();
                refreshlayout.finishLoadmore();
            }
        });

        in_service_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_in_service_finish://完成服务
                        Intent intent=new Intent(getActivity(),CompleteWorkOrderActivity.class);
                        //传递工单号
                        intent.putExtra("OrderID",((WorkOrder.DataBean)adapter.getItem(position)).getOrderID());
                        startActivity(intent);
                        break;
                    case R.id.tv_in_service_apply_parts: //申请配件
                        startActivity(new Intent(getActivity(), Order_Add_Accessories_Activity.class));
                        break;
                        default:
                            break;

                }

            }
        });

    }

    @Override
    public void GetOrderInfoListForMe(BaseResult<WorkOrder> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                workOrder = baseResult.getData();
                list.addAll(workOrder.getData());
                in_service_adapter.setNewData(list); //?
                break;
            case 401:
                ToastUtils.showShort(baseResult.getInfo());
                break;
        }
    }

    @Override
    public void AddOrderfailureReason(BaseResult<Data> baseResult) {

    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {

    }

    @Override
    public void GetChildAccountByParentUserID(BaseResult<List<SubUserInfo.SubUserInfoDean>> baseResult) {

    }

    @Override
    public void contentLoading() {

    }

    @Override
    public void contentLoadingComplete() {

    }

    @Override
    public void contentLoadingError() {

    }

    @Override
    public void contentLoadingEmpty() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
