package com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;

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
    private String OrderId;//记录当前工单号
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

    @Override
    public void onResume() {
        super.onResume();
        mRefreshLayout.autoRefresh();
    }

    public void initView() {
        list=new ArrayList<>();
        recyclerView=view.findViewById(R.id.recyclerview_order_receiving);
        mRefreshLayout=view.findViewById(R.id.refreshLayout);
        in_service_adapter=new In_Service_Adapter(R.layout.item_in_service,list);
        in_service_adapter.setEmptyView(getEmptyView());
        recyclerView.setAdapter(in_service_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mPresenter.WorkerGetOrderList(userID,"2",Integer.toString(pageIndex),"5");
    }
    private void initListener() {

        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex=1;
              //  list.clear();
                mPresenter.WorkerGetOrderList(userID,"2",Integer.toString(pageIndex),"5");
                in_service_adapter.notifyDataSetChanged();
                refreshlayout.finishRefresh();
            }
        });

        //没满屏时禁止上拉
        mRefreshLayout.setEnableLoadmoreWhenContentNotFull(false);
        //上拉加载更多
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageIndex++; //页数加1
                mPresenter.WorkerGetOrderList(userID,"2",Integer.toString(pageIndex),"5");
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
                        Intent intent2=new Intent(getActivity(),Order_Add_Accessories_Activity.class);
                        intent2.putExtra("OrderID",((WorkOrder.DataBean)adapter.getItem(position)).getOrderID());
                        startActivity(intent2);

                        break;
                    case R.id.tv_cancel_work_order://取消工单
                        OrderId=((WorkOrder.DataBean)adapter.getData().get(position)).getOrderID();//获取工单号
                        final CommonDialog_Home dialog = new CommonDialog_Home(getActivity());
                        dialog.setMessage("是否取消工单")
                                //.setImageResId(R.mipmap.ic_launcher)
                                .setTitle("提示")
                                .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {//取消订单
                                mPresenter.UpdateSendOrderState(OrderId,"-1");
                                dialog.dismiss();
                            }

                            @Override
                            public void onNegtiveClick() {//放弃取消
                                dialog.dismiss();
                                // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                            }
                        }).show();



                        break;

                        default:
                            break;

                }

            }
        });

    }

    @Override
    public void WorkerGetOrderList(BaseResult<WorkOrder> baseResult) {
        switch (baseResult.getStatusCode()) {

            case 200:
                if (baseResult.getData().getData()==null){
                    Log.d("==>","暂无服务工单");
                    list.clear();
                    in_service_adapter.notifyDataSetChanged();
                }else {
                    if (pageIndex==1){
                        list.clear();
                        workOrder = baseResult.getData();
                        list.addAll(workOrder.getData());
                        in_service_adapter.notifyDataSetChanged();
                    }else {
                        workOrder = baseResult.getData();
                        list.addAll(workOrder.getData());
                        in_service_adapter.setNewData(list);
                    }
                }

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
    public void ChangeSendOrder(BaseResult<Data> baseResult) {

    }

    @Override
    public void UpdateSendOrderState(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){
                    mRefreshLayout.autoRefresh();

                }else {
                    Toast.makeText(getActivity(), (CharSequence) baseResult.getData().getItem2(),Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void UpdateContinueServiceState(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void PressFactoryAccount(BaseResult<Data<String>> baseResult) {

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
