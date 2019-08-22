package com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment;

import android.Manifest;
import android.app.AlertDialog;
import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.SubUserInfo;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.GetOrderListForMeContract;
import com.ying.administrator.masterappdemo.mvp.model.GetOrderListForMeModel;
import com.ying.administrator.masterappdemo.mvp.presenter.GetOrderListForMePresenter;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Order_details_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.WorkOrderDetailsActivity2;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Pending_Appointment_Adapter;

import com.ying.administrator.masterappdemo.mvp.ui.adapter.Redeploy_Adapter;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseFragment;
import com.ying.administrator.masterappdemo.util.calendarutil.CalendarEvent;
import com.ying.administrator.masterappdemo.util.calendarutil.CalendarProviderManager;
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;
import com.ying.administrator.masterappdemo.widget.CustomDialog_Redeploy;
import com.ying.administrator.masterappdemo.widget.CustomDialog_UnSuccess;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import org.feezu.liuli.timeselector.TimeSelector;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.functions.Consumer;

import static android.widget.Toast.LENGTH_SHORT;

/*待预约*/
public class Pending_appointment_fragment extends BaseFragment<GetOrderListForMePresenter, GetOrderListForMeModel> implements GetOrderListForMeContract.View {
    private View view;
    private RecyclerView recyclerView;
    private Pending_Appointment_Adapter pending_appointment_adapter;
    private ArrayList<WorkOrder.DataBean> list;
    private UserInfo.UserInfoDean userInfo = new UserInfo.UserInfoDean(); //获取当前账号详情
    private ArrayList<SubUserInfo.SubUserInfoDean> subuserlist;//获取子账号列表
    private WorkOrder.DataBean data = new WorkOrder.DataBean();
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
    private int cancleposition;
    private int redeployposition;
    private int successposition;

    private ZLoadingDialog dialog;
    private View under_review;
    private ImageView view_select_time_point;
    private AlertDialog underReviewDialog;
    private Window window;
    private long recommendedtime;//上门预约毫秒数
    private long finishrecomendedtime;//结束时间毫秒数

    public Pending_appointment_fragment() {
        // Required empty public constructor
    }

    public static Pending_appointment_fragment newInstance() {
        Pending_appointment_fragment fragment = new Pending_appointment_fragment();
        return fragment;
    }

   /* @Override
    public void onResume() {
        super.onResume();
        mRefreshLayout.autoRefresh();  //返回的时候刷新页面
    }*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_order_receiving, container, false);
            SPUtils spUtils = SPUtils.getInstance("token");
            userID = spUtils.getString("userName"); //获取用户id
            initView();
            initListener();

        }

        return view;
    }

    public void initView() {
        dialog = new ZLoadingDialog(mActivity);
        customDialog_redeploy = new CustomDialog_Redeploy(mActivity);
        recyclerView = view.findViewById(R.id.recyclerview_order_receiving);
        tv_pending_appointment_redeploy = view.findViewById(R.id.tv_pending_appointment_redeploy);
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        list = new ArrayList<>();
        subuserlist = new ArrayList<>();//获取子账号列表
        mPresenter.GetUserInfoList(userID, "1"); //获取关于自己的信息
        mPresenter.GetChildAccountByParentUserID(userID);//获取自己的子账号 如果没有返回空
        pending_appointment_adapter = new Pending_Appointment_Adapter(R.layout.item_pending_appointment, list, userInfo, subuserlist);
        recyclerView.setAdapter(pending_appointment_adapter);
        pending_appointment_adapter.setEmptyView(getEmptyView());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        showLoading();
        mPresenter.WorkerGetOrderList(userID, "1", Integer.toString(pageIndex), "5");

    }


    /**
     * 选择上门时间
     */
    public void chooseTime(final int position,final String title) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format1 = format.format(date);

        TimeSelector timeSelector = new TimeSelector(mActivity, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {

                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                try {
                    recommendedtime = format.parse(time).getTime();
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                OrderId= workOrder.getData().get(position).getOrderID();
                mPresenter.UpdateSendOrderUpdateTime(OrderId, time, time);


               Intent intent=new Intent(mActivity, WorkOrderDetailsActivity2.class);
                intent.putExtra("OrderID",OrderId);
                intent.putExtra("time",time);
                startActivity(intent);
                successposition=position;

                mPresenter.AddOrderSuccess(OrderId,"1","预约成功");
            }
        }, format1, "2022-1-1 24:00");

        timeSelector.setTitle(title);
        timeSelector.setNextBtTip("确定");
        timeSelector.show();
    }


    public void initListener() {


        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
          /*      if (!list.isEmpty()){ //当有数据的时候
                    ll_empty.setVisibility(View.INVISIBLE);//隐藏空的界面
                }*/
                pageIndex = 1;
                //list.clear();
                mPresenter.WorkerGetOrderList(userID, "1", Integer.toString(pageIndex), "5");
                pending_appointment_adapter.notifyDataSetChanged();
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
                mPresenter.WorkerGetOrderList(userID, "1", Integer.toString(pageIndex), "5");
                pending_appointment_adapter.notifyDataSetChanged();
                refreshlayout.finishLoadmore();
            }
        });


        pending_appointment_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()) {
                    case R.id.rl_pending_appointment:
                        Intent intent1 = new Intent(getActivity(), Order_details_Activity.class);
                        //传递工单号
                        intent1.putExtra("OrderID", ((WorkOrder.DataBean) adapter.getItem(position)).getOrderID());
                        successposition = position;
                        //startActivity(intent);
                        //  intent1.putExtra("successposition",successposition);
                        // startActivityForResult(intent1,22);
                        startActivityForResult(intent1, 1001);
                        break;
                    /*预约成功*/
                    case R.id.tv_pending_appointment_success:

                        data.setOrderID(((WorkOrder.DataBean) adapter.getItem(position)).getOrderID());
                        data.setTypeName(((WorkOrder.DataBean) adapter.getItem(position)).getTypeName());
                        data.setAddress(((WorkOrder.DataBean) adapter.getItem(position)).getAddress());
                        data.setUserName(((WorkOrder.DataBean) adapter.getItem(position)).getUserName());
                        data.setPhone(((WorkOrder.DataBean) adapter.getItem(position)).getPhone());
                        data.setMemo(((WorkOrder.DataBean) adapter.getItem(position)).getMemo());

                                RxPermissions rxPermissions = new RxPermissions(mActivity);
                                rxPermissions.request(Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_CALENDAR)
                                        .subscribe(new Consumer<Boolean>() {
                                            @Override
                                            public void accept(Boolean aBoolean) throws Exception {
                                                if (aBoolean) {
                                                    // 获取全部权限成功

                                                    chooseTime(position,"请选择上门时间");
                                                } else {
                                                    // 获取全部权限失败
                                                    Log.d("=====>", "权限获取失败");
                                                }
                                            }
                                        });
                        break;
                    case R.id.tv_pending_appointment_failure:

                        /*预约未成功*/
                        final CustomDialog_UnSuccess customDialog_unSuccess = new CustomDialog_UnSuccess(getContext());
                        customDialog_unSuccess.getWindow().setBackgroundDrawableResource(R.color.transparent);
                        customDialog_unSuccess.show();

                        customDialog_unSuccess.setYesOnclickListener("用户取消订单", new CustomDialog_UnSuccess.onYesOnclickListener() {
                            @Override
                            public void onYesClick() {
                                customDialog_unSuccess.dismiss(); //用户取消订单
                                Toast.makeText(getActivity(), "用户订单取消", LENGTH_SHORT).show();

                                /*调用接口移除预约不成功的订单*/
                                mPresenter.AddOrderfailureReason(
                                        ((WorkOrder.DataBean) adapter.getItem(position)).getOrderID(), "-1", "用户取消订单"
                                );
                                pending_appointment_adapter.remove(position);

                            }
                        });

                        customDialog_unSuccess.setNoOnclickListener("电话打不通", new CustomDialog_UnSuccess.onNoOnclickListener() {
                            @Override
                            public void onNoClick() {
                                customDialog_unSuccess.dismiss();//电话打不通
                                Toast.makeText(getActivity(), "电话打不通", LENGTH_SHORT).show();


                                /*调用接口移除预约不成功的订单*/
                                mPresenter.AddOrderfailureReason(
                                        ((WorkOrder.DataBean) adapter.getItem(position)).getOrderID(), "-1", "电话打不通"
                                );
                                pending_appointment_adapter.remove(position);
                            }
                        });
                        /*其他原因*/
                        customDialog_unSuccess.setOtherReasonOnclickListener("其他原因", new CustomDialog_UnSuccess.onOtherReasonListener() {
                            @Override
                            public void onOtherReasonClick() {
                                customDialog_unSuccess.findViewById(R.id.ed_unsuccess_reason).setVisibility(View.VISIBLE);
                                TextView tv_other_reason = customDialog_unSuccess.findViewById(R.id.tv_other_reason);
                                // tv_other_reason.setBackgroundColor(Color.RED);
                                tv_other_reason.setBackgroundResource(R.drawable.tv_unsuccess_reason_submit);
                                tv_other_reason.setText("提交");
                                /*提交*/
                                customDialog_unSuccess.setOtherReasonOnclickListener("提交", new CustomDialog_UnSuccess.onOtherReasonListener() {
                                    @Override
                                    public void onOtherReasonClick() {
                                        customDialog_unSuccess.dismiss();

                                        //未预约成功的原因
                                        String unsuccess_reason = ((EditText) customDialog_unSuccess.findViewById(R.id.ed_unsuccess_reason)).getText().toString();
                                        /*调用接口移除预约不成功的订单*/
                                        mPresenter.AddOrderfailureReason(
                                                ((WorkOrder.DataBean) adapter.getItem(position)).getOrderID(), "-1", unsuccess_reason);
                                        pending_appointment_adapter.remove(position);

                                        Toast.makeText(getActivity(), unsuccess_reason, LENGTH_SHORT).show();
                                    }
                                });


                            }
                        });
                        /*其他原因*/

                        /*叉叉退出*/
                        customDialog_unSuccess.setCancleOnclickListener("退出", new CustomDialog_UnSuccess.onCancleOnclickListener() {
                            @Override
                            public void onCancleClick() {
                                Toast.makeText(getActivity(), "退出", LENGTH_SHORT).show();
                                customDialog_unSuccess.dismiss();
                            }
                        });


                        /*叉叉退出*/

                        break;

                    /*预约未成功*/

                    /*电话预约*/
                    case R.id.img_pending_appointment_phone:
                        call("tel:" + ((WorkOrder.DataBean) adapter.getItem(position)).getPhone());

                        break;
                    /*电话预约*/


                    /*转派订单*/

                    case R.id.tv_pending_appointment_redeploy:
                        OrderId = ((WorkOrder.DataBean) adapter.getData().get(position)).getOrderID();//获取工单号

                        customDialog_redeploy.getWindow().setBackgroundDrawableResource(R.color.transparent);
                        customDialog_redeploy.show();
                        Window window = customDialog_redeploy.getWindow();
                        WindowManager.LayoutParams wlp = window.getAttributes();
                        Display d = window.getWindowManager().getDefaultDisplay();
                        wlp.height = (d.getHeight());
                        wlp.width = (d.getWidth());
                        wlp.gravity = Gravity.CENTER;
                        window.setAttributes(wlp);


                        recyclerView_custom_redeploy = customDialog_redeploy.findViewById(R.id.recyclerView_custom_redeploy);
                        recyclerView_custom_redeploy.setLayoutManager(new LinearLayoutManager(mActivity));
                        redeploy_adapter = new Redeploy_Adapter(R.layout.item_redeploy, subuserlist, mActivity);
                        recyclerView_custom_redeploy.setAdapter(redeploy_adapter);



                        /*选择子账号进行转派*/
                        redeploy_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                                switch (view.getId()) {
                                    case R.id.rl_item_redeploy:
                                        // case R.id.img_redeploy_unselect:
                                        // case R.id.img_redeploy_select:
                                        if (((SubUserInfo.SubUserInfoDean) adapter.getData().get(position)).isIscheck() == false) {//当前选中选中

                                            for (int i = 0; i < subuserlist.size(); i++) {
                                                subuserlist.get(i).setIscheck(false);
                                            }
                                            subuserlist.get(position).setIscheck(true); //点击的为选中状态
                                            SubUserID = subuserlist.get(position).getUserID();
                                            Log.d("====>", SubUserID);
                                            redeploy_adapter.notifyDataSetChanged();

                                        } else { //点击的为已选中

                                            for (int i = 0; i < subuserlist.size(); i++) {
                                                subuserlist.get(i).setIscheck(false);
                                            }
                                            SubUserID = null;
                                            redeploy_adapter.notifyDataSetChanged();
                                        }


                                        break;
                                    default:
                                        break;
                                }
                            }
                        });

                        customDialog_redeploy.setYesOnclickListener("转派订单", new CustomDialog_Redeploy.onYesOnclickListener() {
                            @Override
                            public void onYesClick() {
                                if (SubUserID == null) {
                                    Toast.makeText(getActivity(), "您还没选择子账号进行转派", LENGTH_SHORT).show();
                                    //  customDialog_redeploy.dismiss(); //没选择人进行选派
                                } else {
                                    //转派成功状态恢复原状

                                    for (int i = 0; i < subuserlist.size(); i++) {
                                        subuserlist.get(i).setIscheck(false);
                                    }
                                    //转派成功 刷新当前页面
                                    redeployposition = position;
                                    mPresenter.ChangeSendOrder(OrderId, SubUserID);
                                    customDialog_redeploy.dismiss();
                                    // mRefreshLayout.autoRefresh();
                                    SubUserID = null;
                                }

                            }
                        });

                        customDialog_redeploy.setNoOnclickListener("取消转派", new CustomDialog_Redeploy.onNoOnclickListener() {
                            @Override
                            public void onNoOnclick() {
                                //点击了取消所谓状态恢复原状
                                SubUserID = null;
                                for (int i = 0; i < subuserlist.size(); i++) {
                                    subuserlist.get(i).setIscheck(false);
                                }
                                customDialog_redeploy.dismiss();
                            }
                        });
                        break;
                    /*转派订单*/



                    /*取消订单*/
                    case R.id.tv_cancel_order:
                        OrderId = ((WorkOrder.DataBean) adapter.getData().get(position)).getOrderID();//获取工单号
                        final CommonDialog_Home dialog = new CommonDialog_Home(getActivity());
                        dialog.setMessage("是否取消工单")
                                //.setImageResId(R.mipmap.ic_launcher)
                                .setTitle("提示")
                                .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {//取消订单
                                mPresenter.UpdateSendOrderState(OrderId, "-1");
                                cancleposition = position;
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


    /*获取 自己抢到的订单*/
    @Override
    public void WorkerGetOrderList(BaseResult<WorkOrder> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:

                if (baseResult.getData().getData() == null) {
                    if (pageIndex == 1) {
                        list.clear();
                        pending_appointment_adapter.notifyDataSetChanged();
                    }
                    cancleLoading();
                } else {
                    if (pageIndex == 1) {
                        list.clear();
                        workOrder = baseResult.getData();
                        list.addAll(workOrder.getData());
                        pending_appointment_adapter.notifyDataSetChanged();
                    } else {
                        workOrder = baseResult.getData();
                        list.addAll(workOrder.getData());
                        pending_appointment_adapter.setNewData(list);
                    }

                    cancleLoading();
                    //  pending_appointment_adapter.notifyDataSetChanged();

                }


                break;
            default:
                cancleLoading();
                break;
        }
    }


    /**/
    @Override
    public void AddOrderfailureReason(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                break;
            default:
                break;

        }
    }

    @Override
    public void AddOrderSuccess(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {

            case 200:
                if (baseResult.getData().isItem1()) {
                    pending_appointment_adapter.remove(successposition);
                    //Toast.makeText(getActivity(),"预约成功请到服务中",Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post(2);//预约成功跳转到服务中
                } else {


                }
                break;
            default:
                break;

        }

    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                userInfo = baseResult.getData().getData().get(0);
                pending_appointment_adapter.notifyDataSetChanged();
                break;
            default:
                break;

        }
    }

    /*获取子账号*/
    @Override
    public void GetChildAccountByParentUserID(BaseResult<List<SubUserInfo.SubUserInfoDean>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                subuserlist.addAll(baseResult.getData());
                pending_appointment_adapter.notifyDataSetChanged();

                break;
            default:
                break;
        }
    }

    /*派单操作*/
    @Override
    public void ChangeSendOrder(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    Toast.makeText(getActivity(), "转派成功", LENGTH_SHORT).show();
                    pending_appointment_adapter.remove(redeployposition);
                } else {

                    Toast.makeText(getActivity(), "转派失败", LENGTH_SHORT).show();
                }

                break;
            default:
                break;
        }


    }

    /*取消订单 */
    @Override
    public void UpdateSendOrderState(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    //mRefreshLayout.autoRefresh();
                    pending_appointment_adapter.remove(cancleposition);
                } else {
                    Toast.makeText(getActivity(), "取消失败", Toast.LENGTH_LONG).show();
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
        switch (baseResult.getStatusCode()) {
            case 200:

                if (baseResult.getData().isItem1()) {

                    if (data.getAddress() == null) {
                        Log.d("=====>", "地址为空");
                    } else {
                        Log.d("=====>", data.getAddress());
                    }
                    CalendarEvent calendarEvent = new CalendarEvent(
                            data.getTypeName() + "工单号：" + data.getOrderID(),
                            "客户名:" + data.getUserName() + " 客户手机号:" + data.getPhone() + "故障原因" + data.getMemo(),
                            data.getAddress(),
                            recommendedtime,
                            recommendedtime,
                            60, null    //提前一个小时提醒  单位分钟
                    );
                    // 添加事件
                    int result = CalendarProviderManager.addCalendarEvent(mActivity, calendarEvent);
                    if (result == 0) {
                        Toast.makeText(mActivity, "已为您添加行程至日历,将提前一小时提醒您！！", Toast.LENGTH_SHORT).show();
                    } else if (result == -1) {
                        Toast.makeText(mActivity, "插入失败", LENGTH_SHORT).show();
                    } else if (result == -2) {
                        Toast.makeText(mActivity, "没有权限", LENGTH_SHORT).show();
                    }









                }
        }

        }

    @Override
    public void ApplyAccessoryLate(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void WorkerComplaint(BaseResult<Data<String>> baseResult) {

    }


    @Override
        public void contentLoading () {
        }

        @Override
        public void contentLoadingComplete () {
        }

        @Override
        public void contentLoadingError () {
        }

        @Override
        public void contentLoadingEmpty () {
        }

        @Override
        public void showProgress () {
        }

        @Override
        public void hideProgress () {

        }


        @Override
        public void onActivityResult ( int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == 10001 || resultCode == 10002) {
                if (requestCode == 1001) {
                    pending_appointment_adapter.remove(successposition);
                }

            }
        }

        public void showLoading () {
            dialog.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
                    .setLoadingColor(Color.BLACK)//颜色
                    .setHintText("正在加载工单...")
                    .setHintTextSize(14) // 设置字体大小 dp
                    .setHintTextColor(Color.BLACK)  // 设置字体颜色
                    .setDurationTime(1) // 设置动画时间百分比 - 0.5倍
                    .setCanceledOnTouchOutside(false)//点击外部无法取消
                    .show();
        }

        public void cancleLoading () {
            dialog.dismiss();

        }
        @Subscribe(threadMode = ThreadMode.MAIN)
        public void Event (String message){
            if (!"1".equals(message)) {
                return;
            }
            mPresenter.WorkerGetOrderList(userID, "1", Integer.toString(pageIndex), "5");
        }
    }
