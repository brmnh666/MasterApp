package com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.ying.administrator.masterappdemo.common.Config;
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
import com.ying.administrator.masterappdemo.mvp.ui.activity.WorkOrderDetailsActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.WorkOrderDetailsActivity2;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Return_Sheet_Adapter;

import com.ying.administrator.masterappdemo.mvp.ui.adapter.Redeploy_Adapter;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseFragment;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;
import com.ying.administrator.masterappdemo.widget.CustomDialog_Redeploy;
import com.ying.administrator.masterappdemo.widget.CustomDialog_UnSuccess;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/*配件单*/
public class Returnedparts_Fragement extends BaseFragment<GetOrderListForMePresenter,GetOrderListForMeModel> implements GetOrderListForMeContract.View {
    private View view;
    private RecyclerView recyclerView;
    private Return_Sheet_Adapter Return_Sheet_Adapter;
    private ArrayList<WorkOrder.DataBean> list;
    private UserInfo.UserInfoDean userInfo=new UserInfo.UserInfoDean(); //获取当前账号详情
    private ArrayList<SubUserInfo.SubUserInfoDean> subuserlist;//获取子账号列表

    private WorkOrder workOrder;
    private String phoneNuber;
    private Context mContext;
    private RefreshLayout mRefreshLayout;
    private String userID; //用户id
    private int pageIndex = 1;  //默认当前页数为1
    private TextView tv_pending_appointment_redeploy;//转派
    private CustomDialog_Redeploy customDialog_redeploy;//转派dialog
    private RecyclerView recyclerView_custom_redeploy;//显示子账号的RecyclerView
    private Redeploy_Adapter redeploy_adapter; //转派的adapter
    private String SubUserID; //用于存放主账号将要发送子账号的userid
    private String OrderId;//用于记录当前 工单的id
    private Intent intent;
    private boolean isfristin;
    private ZLoadingDialog dialog;
    private EditText et_content;
    private AlertDialog complaint_dialog;

    public Returnedparts_Fragement() {
        // Required empty public constructor
    }

    public static Returnedparts_Fragement newInstance() {
        Returnedparts_Fragement fragment = new Returnedparts_Fragement();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mRefreshLayout.autoRefresh(0,0,1);  //返回的时候刷新页面
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
        dialog =new ZLoadingDialog(mActivity);
        customDialog_redeploy=new CustomDialog_Redeploy(mActivity);
        recyclerView=view.findViewById(R.id.recyclerview_order_receiving);
        tv_pending_appointment_redeploy=view.findViewById(R.id.tv_pending_appointment_redeploy);
        mRefreshLayout=view.findViewById(R.id.refreshLayout);
        mRefreshLayout.autoRefresh(0,0,1);
        list=new ArrayList<>();


        subuserlist=new ArrayList<>();//获取子账号列表


//        mPresenter.GetUserInfoList(userID,"1"); //获取关于自己的信息
//        mPresenter.GetChildAccountByParentUserID(userID);//获取自己的子账号 如果没有返回空



        Return_Sheet_Adapter=new Return_Sheet_Adapter(R.layout.item_returnedparts,list);
        recyclerView.setAdapter(Return_Sheet_Adapter);
        Return_Sheet_Adapter.setEmptyView(getEmptyView());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        mPresenter.WorkerGetOrderList(userID,"3",Integer.toString(pageIndex),"5");


    }




    public void initListener() {


        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex=1;
                mPresenter.WorkerGetOrderList(userID,"3",Integer.toString(pageIndex),"5");
                refreshlayout.resetNoMoreData();
            }
        });


        //没满屏时禁止上拉
//        mRefreshLayout.setEnableLoadmoreWhenContentNotFull(false);
        //上拉加载更多
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageIndex++; //页数加1
                mPresenter.WorkerGetOrderList(userID,"3",Integer.toString(pageIndex),"5");
            }
        });





        Return_Sheet_Adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                switch(view.getId()){
                    case R.id.tv_returnedparts_apply_parts://申请配件
                        intent = new Intent(getActivity(), Order_Add_Accessories_Activity.class);
                        intent.putExtra("OrderID",list.get(position).getOrderID());
                        startActivity(intent);
                        break;
                    case R.id.tv_reminder://催件
                        mPresenter.PressFactoryAccount(list.get(position).getUserID(),list.get(position).getOrderID());
                        break;
                    case R.id.tv_continue_service://完成工单
//                        mPresenter.UpdateContinueServiceState(list.get(position).getOrderID());
                        Intent intent=new Intent(getActivity(), CompleteWorkOrderActivity.class);
                        //传递工单号
                        intent.putExtra("OrderID",((WorkOrder.DataBean)adapter.getItem(position)).getOrderID());
                        startActivity(intent);
                        break;
                    case R.id.tv_see_detail://查看详情

                        mPresenter.UpdateOrderIsLook(list.get(position).getOrderID(),"2");
                        intent=new Intent(getActivity(), WorkOrderDetailsActivity2.class);
                        intent.putExtra("OrderID",list.get(position).getOrderID());
                        startActivity(intent);
                        break;
                    case R.id.tv_apply_for_an_extension://申请延期
                        mPresenter.ApplyAccessoryLate(list.get(position).getOrderID());
                        mRefreshLayout.autoRefresh(0,0,1);
                        break;
                    case R.id.tv_complaint:
                        View complaint_view = LayoutInflater.from(mActivity).inflate(R.layout.customdialog_complaint, null);
                        TextView title = complaint_view.findViewById(R.id.title);
                        Button btn_negtive = complaint_view.findViewById(R.id.negtive);
                        Button btn_positive = complaint_view.findViewById(R.id.positive);
                        et_content = complaint_view.findViewById(R.id.et_content);
                        title.setText("投诉");
                        complaint_dialog = new AlertDialog.Builder(mActivity)
                                .setView(complaint_view)
                                .create();
                        complaint_dialog.show();
                        btn_negtive.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                complaint_dialog.dismiss();
                            }
                        });
                        btn_positive.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String content = et_content.getText().toString().trim();
                                if ("".equals(content)){
                                    MyUtils.showToast(mActivity,"请输入投诉原因");
                                }else{
                                    mPresenter.WorkerComplaint(workOrder.getData().get(position).getOrderID(),content);
                                }
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        });
    }


    /*获取 自己抢到的订单*/
    @Override
    public void WorkerGetOrderList(BaseResult<WorkOrder> baseResult) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getData()==null){
                    Log.d("===>","暂无预约工单");
                    if (pageIndex!=1){
                        mRefreshLayout.finishLoadmoreWithNoMoreData();
                    }else{
                        list.clear();
                        Return_Sheet_Adapter.notifyDataSetChanged();
                    }
                }else {
                    if (pageIndex==1){
                        list.clear();
                    }
                    workOrder = baseResult.getData();
                    list.addAll(workOrder.getData());
                    Return_Sheet_Adapter.notifyDataSetChanged();
                }
                isfristin=true;
                cancleLoading();

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
                Return_Sheet_Adapter.notifyDataSetChanged();
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
                Return_Sheet_Adapter.notifyDataSetChanged();

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
                    mRefreshLayout.autoRefresh(0,0,1);
                }else {
                    Toast.makeText(getActivity(),"取消失败",Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
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


    @Override
    public void UpdateContinueServiceState(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data=baseResult.getData();
                if (data.isItem1()){
                    ToastUtils.showShort(data.getItem2());
                    mRefreshLayout.autoRefresh(0,0,1);
                }else{
                    ToastUtils.showShort(data.getItem2());
                }
                break;
        }
    }

    @Override
    public void PressFactoryAccount(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data=baseResult.getData();
                if (data.isItem1()){
                    ToastUtils.showShort("催件成功");
                    mRefreshLayout.autoRefresh(0,0,1);
                }else{
                    ToastUtils.showShort(data.getItem2());
                }
                break;
        }
    }

    @Override
    public void UpdateOrderIsLook(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){
                    EventBus.getDefault().post(Config.ORDER_READ);
                }
                break;
            default:
                break;
        }


    }

    @Override
    public void UpdateSendOrderUpdateTime(BaseResult<Data> baseResult) {

    }

    @Override
    public void ApplyAccessoryLate(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                ToastUtils.showShort("延期成功");
                break;
        }
    }

    @Override
    public void WorkerComplaint(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data = baseResult.getData();
                if (data.isItem1()) {
                    ToastUtils.showShort(data.getItem2());
                    complaint_dialog.dismiss();
                } else {
                    ToastUtils.showShort(data.getItem2());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void UpdateOrderState(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void OrderIsCall(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            if (isfristin==false){
                showLoading();
            }
            if (mPresenter==null){
                return;
            }
            mPresenter.WorkerGetOrderList(userID,"3",Integer.toString(pageIndex),"5");
        }

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String message) {
        if ("WorkOrderDetailsActivity".equals(message)){
            mPresenter.WorkerGetOrderList(userID, "3", Integer.toString(pageIndex), "5");
        }
        if (!"3".equals(message)){
            return;
        }
        mPresenter.WorkerGetOrderList(userID, "3", Integer.toString(pageIndex), "5");
    }



    public void showLoading(){
//        dialog.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
//                .setLoadingColor(Color.BLACK)//颜色
//                .setHintText("正在加载工单...")
//                .setHintTextSize(14) // 设置字体大小 dp
//                .setHintTextColor(Color.BLACK)  // 设置字体颜色
//                .setDurationTime(1) // 设置动画时间百分比 - 0.5倍
//                .setCanceledOnTouchOutside(false)//点击外部无法取消
//                .show();
    }

    public void cancleLoading(){
        dialog.dismiss();

    }


}
