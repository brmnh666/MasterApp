package com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.SubUserInfo;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.GetOrderListForMeContract;
import com.ying.administrator.masterappdemo.mvp.model.GetOrderListForMeModel;
import com.ying.administrator.masterappdemo.mvp.presenter.GetOrderListForMePresenter;
import com.ying.administrator.masterappdemo.mvp.ui.activity.ComplaintActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.CompleteWorkOrderActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Order_Add_Accessories_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Order_details_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.WorkOrderDetailsActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.WorkOrderDetailsActivity2;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.In_Service_Adapter;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseFragment;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import static com.umeng.socialize.utils.ContextUtil.getPackageName;

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
    private int cancelposition;
    private PopupWindow mPopupWindow;
    private View popupWindow_view;
    private ImageView img_cancle;
    private LinearLayout ll_choose_baidumap;
    private LinearLayout ll_choose_gaodemap;
    private ZLoadingDialog dialog;
    private boolean isfristin;
    private AlertDialog complaint_dialog;
    private EditText et_content;
    private EditText et_message;
    private Button negtive;
    private Button positive;
    private AlertDialog cancelDialog;

    public static InService_Fragement newInstance() {
        InService_Fragement fragment = new InService_Fragement();
        return fragment;
    }

    @Nullable
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

    @Override
    public void onResume() {
        super.onResume();
        mRefreshLayout.autoRefresh(0, 0, 1);
    }

    public void initView() {
        dialog = new ZLoadingDialog(mActivity);
        list = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerview_order_receiving);
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        mRefreshLayout.autoRefresh(0, 0, 1);
        in_service_adapter = new In_Service_Adapter(R.layout.item_in_service, list);
        in_service_adapter.setEmptyView(getEmptyView());
        recyclerView.setAdapter(in_service_adapter);
        in_service_adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), WorkOrderDetailsActivity2.class);
                //传递工单号
                intent.putExtra("OrderID", ((WorkOrder.DataBean) adapter.getItem(position)).getOrderID());
                startActivity(intent);
                mPresenter.UpdateOrderIsLook(((WorkOrder.DataBean) adapter.getItem(position)).getOrderID(), "2");
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        /*选择地图*/
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.popwindow_choosemap, null);
        mPopupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void initListener() {

        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex = 1;
                mPresenter.WorkerGetOrderList(userID, "2", Integer.toString(pageIndex), "5");
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
                mPresenter.WorkerGetOrderList(userID, "2", Integer.toString(pageIndex), "5");
            }
        });

        in_service_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()) {
                    case R.id.tv_see_detail://查看详情
                        Intent intent = new Intent(getActivity(), WorkOrderDetailsActivity2.class);
                        //传递工单号
                        intent.putExtra("OrderID", ((WorkOrder.DataBean) adapter.getItem(position)).getOrderID());
                        startActivity(intent);
                        mPresenter.UpdateOrderIsLook(((WorkOrder.DataBean) adapter.getItem(position)).getOrderID(), "2");
                        break;
                    case R.id.tv_in_service_apply_parts: //申请配件
                        Intent intent2 = new Intent(getActivity(), Order_Add_Accessories_Activity.class);
                        intent2.putExtra("OrderID", ((WorkOrder.DataBean) adapter.getItem(position)).getOrderID());
                        startActivity(intent2);

                        break;
                    case R.id.tv_cancel_work_order://取消工单
                        OrderId = ((WorkOrder.DataBean) adapter.getData().get(position)).getOrderID();//获取工单号
                        View Cancelview = LayoutInflater.from(mActivity).inflate(R.layout.dialog_cancel, null);
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
                                String message = et_message.getText().toString();
                                if (message == null || "".equals(message)) {
                                    ToastUtils.showShort("请输入取消工单理由");
                                } else {
//                                    mPresenter.UpdateOrderState(OrderId, "-1",message);
                                    mPresenter.UpdateSendOrderState(OrderId, "-1", message);
                                    cancelDialog.dismiss();
                                }

                            }
                        });

                        cancelDialog = new AlertDialog.Builder(mActivity).setView(Cancelview).create();
                        cancelDialog.show();
                        Window window1 = cancelDialog.getWindow();
                        WindowManager.LayoutParams layoutParams = window1.getAttributes();
                        window1.setAttributes(layoutParams);
                        window1.setBackgroundDrawable(new ColorDrawable());
                        break;
                    case R.id.img_navigation:
                        // goToBaiduMap(((WorkOrder.DataBean)adapter.getData().get(position)).getAddress());
                        //goToGaodeMap(((WorkOrder.DataBean)adapter.getData().get(position)).getAddress());
                        showPopupWindow(((WorkOrder.DataBean) adapter.getData().get(position)).getAddress());
                        break;
                    case R.id.tv_complaint:
                        Intent intent1=new Intent(mActivity, ComplaintActivity.class);
                        intent1.putExtra("orderId",list.get(position).getOrderID());
                        startActivity(intent1);
//                        View complaint_view = LayoutInflater.from(mActivity).inflate(R.layout.customdialog_complaint, null);
//                        TextView title = complaint_view.findViewById(R.id.title);
//                        Button btn_negtive = complaint_view.findViewById(R.id.negtive);
//                        Button btn_positive = complaint_view.findViewById(R.id.positive);
//                        et_content = complaint_view.findViewById(R.id.et_content);
//                        title.setText("投诉");
//                        complaint_dialog = new AlertDialog.Builder(mActivity)
//                                .setView(complaint_view)
//                                .create();
//                        complaint_dialog.show();
//                        btn_negtive.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                complaint_dialog.dismiss();
//                            }
//                        });
//                        btn_positive.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                String content = et_content.getText().toString().trim();
//                                if ("".equals(content)) {
//                                    MyUtils.showToast(mActivity, "请输入投诉原因");
//                                } else {
//                                    mPresenter.WorkerComplaint(workOrder.getData().get(position).getOrderID(), content);
//                                }
//                            }
//                        });
                        break;
                    default:
                        break;

                }

            }
        });

    }

    @Override
    public void WorkerGetOrderList(BaseResult<WorkOrder> baseResult) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
        switch (baseResult.getStatusCode()) {

            case 200:
                if (baseResult.getData().getData() == null) {
                    if (pageIndex != 1) {
                        mRefreshLayout.finishLoadmoreWithNoMoreData();
                    } else {
                        list.clear();
                        in_service_adapter.notifyDataSetChanged();
                    }
                } else {
                    if (pageIndex == 1) {
                        list.clear();
                    }
                    workOrder = baseResult.getData();
                    list.addAll(workOrder.getData());
                    in_service_adapter.notifyDataSetChanged();
                }
                isfristin = true;
                cancleLoading();
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
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    // mRefreshLayout.autoRefresh(0,0,1);
                    in_service_adapter.remove(cancelposition);
                    EventBus.getDefault().post(8);
                } else {
                    Toast.makeText(getActivity(), (CharSequence) baseResult.getData().getItem2(), Toast.LENGTH_SHORT).show();
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

    /*更新工单状态 去掉小红点*/
    @Override
    public void UpdateOrderIsLook(BaseResult<Data<String>> baseResult) {

        switch (baseResult.getStatusCode()) {
            case 200:

                if (baseResult.getData().isItem1()) {
                    EventBus.getDefault().post(Config.ORDER_READ);
                }

                break;
        }
    }

    @Override
    public void UpdateSendOrderUpdateTime(BaseResult<Data> baseResult) {

    }

    @Override
    public void ApplyAccessoryLate(BaseResult<Data<String>> baseResult) {

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


    public void showLoading() {
//        dialog.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
//                .setLoadingColor(Color.BLACK)//颜色
//                .setHintText("正在加载工单...")
//                .setHintTextSize(14) // 设置字体大小 dp
//                .setHintTextColor(Color.BLACK)  // 设置字体颜色
//                .setDurationTime(1) // 设置动画时间百分比 - 0.5倍
//                .setCanceledOnTouchOutside(false)//点击外部无法取消
//                .show();
    }

    public void cancleLoading() {
//        dialog.dismiss();

    }


    /**
     * 弹出Popupwindow
     */
    public void showPopupWindow(final String location) {

        img_cancle = popupWindow_view.findViewById(R.id.img_cancle);
        ll_choose_baidumap = popupWindow_view.findViewById(R.id.ll_choose_baidumap);
        ll_choose_gaodemap = popupWindow_view.findViewById(R.id.ll_choose_gaodemap);


        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources()));
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                MyUtils.setWindowAlpa(mActivity, false);
            }
        });
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(popupWindow_view, Gravity.BOTTOM, 0, 0);
        }
        MyUtils.setWindowAlpa(mActivity, true);

        img_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });


        ll_choose_baidumap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBaiduMap(location);
                mPopupWindow.dismiss();
            }
        });
        ll_choose_gaodemap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGaodeMap(location);
                mPopupWindow.dismiss();
            }
        });


    }


    /**
     * 检测程序是否安装  百度地图高德地图
     *
     * @param packageName
     * @return
     */
    private boolean isInstalled(String packageName) {
        PackageManager manager = mActivity.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> installedPackages = manager.getInstalledPackages(0);
        if (installedPackages != null) {
            for (PackageInfo info : installedPackages) {
                if (info.packageName.equals(packageName))
                    return true;
            }
        }
        return false;
    }

    /**
     * 跳转百度地图
     */
    private void goToBaiduMap(String location) {
        if (!isInstalled("com.baidu.BaiduMap")) {
            Toast.makeText(getActivity(), "请先安装百度地图客户端", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.setData(Uri.parse("baidumap://map/navi?query=" + location + "&src=" + getPackageName()));
        startActivity(intent); // 启动调用
    }


    /**
     * 跳转高德地图
     */
    private void goToGaodeMap(String location) {
        if (!isInstalled("com.autonavi.minimap")) {

            Toast.makeText(getActivity(), "请先安装高德地图客户端", Toast.LENGTH_SHORT).show();
            return;
        }
        //  LatLng endPoint = BD2GCJ(new LatLng(mLat, mLng));//坐标转换
     /*   StringBuffer stringBuffer = new StringBuffer("androidamap://navi?sourceApplication=").append("amap");
        stringBuffer.append("&lat=").append(endPoint.latitude)
                .append("&lon=").append(endPoint.longitude).append("&keywords=" + mAddressStr)
                .append("&dev=").append(0)
                .append("&style=").append(2);*/
        Intent intent = new Intent("android.intent.action.VIEW",
                Uri.parse("androidamap://poi?sourceApplication=softname" +
                        "&keywords=" + location +
                        "&dev=0"));
        intent.setPackage("com.autonavi.minimap");
        startActivity(intent);
    }

    //   @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser){
//       if (isfristin==false){
//           showLoading();
//            }
//            if (mPresenter==null){
//                return;
//            }
//            mPresenter.WorkerGetOrderList(userID,"2",Integer.toString(pageIndex),"5");
//        }
//
//    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String message) {
        if ("服务中".equals(message)) {
            mPresenter.WorkerGetOrderList(userID, "2", Integer.toString(pageIndex), "5");
        }
        if (!"2".equals(message)) {
            return;
        }

        mPresenter.WorkerGetOrderList(userID, "2", Integer.toString(pageIndex), "5");
    }

    /**
     * 跳转腾讯地图
     */
   /* private void goToTencentMap(String location) {
        if (!isInstalled("com.tencent.map")) {
            Toast.makeText(getActivity(),"请先安装腾讯地图客户端",Toast.LENGTH_SHORT).show();
            return;
        }
        *//*LatLng endPoint = BD2GCJ(new LatLng(mLat, mLng));//坐标转换
        StringBuffer stringBuffer = new StringBuffer("qqmap://map/routeplan?type=drive")
                .append("&tocoord=").append(endPoint.latitude).append(",").append(endPoint.longitude).append("&to=" + mAddressStr);*//*

        Intent intent = new Intent("android.intent.action.VIEW",
                        Uri.parse("qqmap://map/routeplan?type=drive" +
                        "&to="+location+"&referer=XRCBZ-64NLD-IF24W-HBWQY-NJDEV-OIFUN")); //终点的显示名称 必要参数
        startActivity(intent);
    }*/
}
