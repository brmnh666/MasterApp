package com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.SubUserInfo;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.GetOrderListForMeContract;
import com.ying.administrator.masterappdemo.mvp.model.GetOrderListForMeModel;
import com.ying.administrator.masterappdemo.mvp.presenter.GetOrderListForMePresenter;
import com.ying.administrator.masterappdemo.mvp.ui.activity.WorkOrderDetailsActivity2;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.ConfirmedAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.ConfirmedAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Redeploy_Adapter;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseFragment;
import com.ying.administrator.masterappdemo.widget.CustomDialog_Redeploy;
import com.zyao89.view.zloading.ZLoadingDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/*未确认*/
public class ConfirmedFragement extends BaseFragment<GetOrderListForMePresenter, GetOrderListForMeModel> implements GetOrderListForMeContract.View {
    private View view;
    private RecyclerView recyclerView;
    private ConfirmedAdapter ConfirmedAdapter;
    private ArrayList<WorkOrder.DataBean> list;
    private UserInfo.UserInfoDean userInfo=new UserInfo.UserInfoDean(); //获取当前账号详情
    private ArrayList<SubUserInfo.SubUserInfoDean> subuserlist;//获取子账号列表

    private WorkOrder workOrder;
    private String phoneNuber;
    private Context mContext;
    private RefreshLayout mRefreshLayout;
    private String userID; //用户id
    private int pageIndex = 1;  //默认当前页数为1
    private int cancelposition;
    private TextView tv_pending_appointment_redeploy;//转派
    private CustomDialog_Redeploy customDialog_redeploy;//转派dialog
    private RecyclerView recyclerView_custom_redeploy;//显示子账号的RecyclerView
    private Redeploy_Adapter redeploy_adapter; //转派的adapter
    private String SubUserID; //用于存放主账号将要发送子账号的userid
    private String OrderId;//用于记录当前 工单的id
    private boolean isfristin;
    private ZLoadingDialog dialog;
    private AlertDialog cancelDialog;
    private EditText et_message;
    private Button negtive;
    private Button positive;

    public ConfirmedFragement() {
        // Required empty public constructor
    }

    public static ConfirmedFragement newInstance() {
        ConfirmedFragement fragment = new ConfirmedFragement();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mRefreshLayout.autoRefresh();  //返回的时候刷新页面
    }

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
        customDialog_redeploy=new CustomDialog_Redeploy(mActivity);
        recyclerView=view.findViewById(R.id.recyclerview_order_receiving);
        tv_pending_appointment_redeploy=view.findViewById(R.id.tv_pending_appointment_redeploy);
        mRefreshLayout=view.findViewById(R.id.refreshLayout);
        mRefreshLayout.autoRefresh();
        list=new ArrayList<>();


        subuserlist=new ArrayList<>();//获取子账号列表


//        mPresenter.GetUserInfoList(userID,"1"); //获取关于自己的信息
//        mPresenter.GetChildAccountByParentUserID(userID);//获取自己的子账号 如果没有返回空



        ConfirmedAdapter=new ConfirmedAdapter(R.layout.item_confirmed,list);
        recyclerView.setAdapter(ConfirmedAdapter);
        ConfirmedAdapter.setEmptyView(getEmptyView());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mPresenter.WorkerGetOrderList(userID,"9",Integer.toString(pageIndex),"5");
//        ConfirmedAdapter.notifyDataSetChanged();
//        mRefreshLayout.autoRefresh();
    }




    public void initListener() {


        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
          /*      if (!list.isEmpty()){ //当有数据的时候
                    ll_empty.setVisibility(View.INVISIBLE);//隐藏空的界面
                }*/
                pageIndex=1;
                //list.clear();
                mPresenter.WorkerGetOrderList(userID,"9",Integer.toString(pageIndex),"5");
                ConfirmedAdapter.notifyDataSetChanged();
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
                mPresenter.WorkerGetOrderList(userID,"9",Integer.toString(pageIndex),"5");
                ConfirmedAdapter.notifyDataSetChanged();
                refreshlayout.finishLoadmore();
            }
        });




//
//        ConfirmedAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent=new Intent(getActivity(), WorkOrderDetailsActivity2.class);
//                //传递工单号
//                intent.putExtra("OrderID",((WorkOrder.DataBean)adapter.getItem(position)).getOrderID());
//                startActivity(intent);
//            }
//        });


        ConfirmedAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_detail:
                        Intent intent=new Intent(getActivity(), WorkOrderDetailsActivity2.class);
                        //传递工单号
                        intent.putExtra("OrderID",((WorkOrder.DataBean)adapter.getItem(position)).getOrderID());
                        startActivity(intent);
                        mPresenter.UpdateOrderIsLook(((WorkOrder.DataBean)adapter.getItem(position)).getOrderID(),"2");
                        break;
                    case R.id.tv_cancel_order:
                        OrderId = ((WorkOrder.DataBean) adapter.getData().get(position)).getOrderID();//获取工单号
                        View Cancelview=LayoutInflater.from(mActivity).inflate(R.layout.dialog_cancel,null);
                        et_message = Cancelview.findViewById(R.id.et_message);
                        negtive = Cancelview.findViewById(R.id.negtive);
                        positive = Cancelview.findViewById(R.id.positive);
                        TextView title1 = Cancelview.findViewById(R.id.title);
                        title1.setText("是否取消工单");

                        negtive.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                cancelDialog.dismiss();
                            }
                        });

                        positive.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String message= et_message.getText().toString();
                                if (message==null||"".equals(message)){
                                    ToastUtils.showShort("请输入取消工单理由");
                                }else {
//                                    mPresenter.UpdateOrderState(OrderId, "-1",message);
                                    mPresenter.UpdateSendOrderState(OrderId,"-1",message);
                                    cancelDialog.dismiss();
                                }

                            }
                        });

                        cancelDialog = new AlertDialog.Builder(mActivity).setView(Cancelview).create();
                        cancelDialog.show();
                        Window window1= cancelDialog.getWindow();
                        WindowManager.LayoutParams layoutParams=window1.getAttributes();
                        window1.setAttributes(layoutParams);
                        window1.setBackgroundDrawable(new ColorDrawable());
                        break;
                }
            }
        });


    }


    /*获取 自己抢到的订单*/
    @Override
    public void WorkerGetOrderList(BaseResult<WorkOrder> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getData()==null){
                    Log.d("===>","暂无预约工单");
                    if (pageIndex==1){
                        list.clear();
                        ConfirmedAdapter.notifyDataSetChanged();
                    }

                }else {

                    if (pageIndex==1){
                        list.clear();
                        workOrder = baseResult.getData();
                        list.addAll(workOrder.getData());
                        ConfirmedAdapter.notifyDataSetChanged();
                    }else {
                        workOrder = baseResult.getData();
                        list.addAll(workOrder.getData());
                        ConfirmedAdapter.setNewData(list);
                    }

                }
                isfristin=true;
//                cancleLoading();
                break;
            case 401:
                ToastUtils.showShort(baseResult.getInfo());
                break;
        }
    }




    /**/
    @Override
    public void AddOrderfailureReason(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                Log.d("949559482",baseResult.getData().getItem2().toString());
                break;
            default:
                Log.d("949559482",baseResult.getData().getItem2().toString());
                break;

        }
    }

    @Override
    public void AddOrderSuccess(BaseResult<Data> baseResult) {

    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                userInfo = baseResult.getData().getData().get(0);
                ConfirmedAdapter.notifyDataSetChanged();
                break;
            default:
                break;

        }
    }

    /*获取子账号*/
    @Override
    public void GetChildAccountByParentUserID(BaseResult<List<SubUserInfo.SubUserInfoDean>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                subuserlist.addAll(baseResult.getData());
                ConfirmedAdapter.notifyDataSetChanged();

                break;
            default:
                break;
        }
    }

    /*派单操作*/
    @Override
    public void ChangeSendOrder(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                Toast.makeText(getActivity(),"转派成功",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }


    }

    /*取消订单 */
    @Override
    public void UpdateSendOrderState(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){
                    mRefreshLayout.autoRefresh();

                }else {
                    Toast.makeText(getActivity(),"取消失败",Toast.LENGTH_LONG).show();
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
    public void UpdateOrderIsLook(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void UpdateSendOrderUpdateTime(BaseResult<Data> baseResult) {

    }

    @Override
    public void ApplyAccessoryLate(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void WorkerComplaint(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void UpdateOrderState(BaseResult<Data<String>> baseResult) {

    }


    @Override
    public void contentLoading() {
    }

    @Override
    public void contentLoadingComplete(){
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String message) {
        if (!"9".equals(message)){
            return;
        }
        mPresenter.WorkerGetOrderList(userID, "9", Integer.toString(pageIndex), "5");
    }


//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser){
//            if (isfristin==false){
//                showLoading();
//            }
//            mPresenter.WorkerGetOrderList(userID,"6",Integer.toString(pageIndex),"5");
//        }
//
//    }
//
//    public void showLoading(){
//        dialog.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
//                .setLoadingColor(Color.BLACK)//颜色
//                .setHintText("正在加载工单...")
//                .setHintTextSize(14) // 设置字体大小 dp
//                .setHintTextColor(Color.BLACK)  // 设置字体颜色
//                .setDurationTime(1) // 设置动画时间百分比 - 0.5倍
//                .setCanceledOnTouchOutside(false)//点击外部无法取消
//                .show();
//    }
//
//    public void cancleLoading(){
//        dialog.dismiss();
//
//    }
}
