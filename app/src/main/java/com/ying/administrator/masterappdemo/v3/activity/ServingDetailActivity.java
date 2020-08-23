package com.ying.administrator.masterappdemo.v3.activity;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.AccessoriesNoEvent;
import com.ying.administrator.masterappdemo.entity.AddOrderSignInRecrodResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GetBrandWithCategory;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.ui.activity.ComplaintActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.CompleteWorkOrderActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.MessageActivity2;
import com.ying.administrator.masterappdemo.mvp.ui.activity.WebActivity;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.util.SingleClick;
import com.ying.administrator.masterappdemo.util.calendarutil.CalendarEvent;
import com.ying.administrator.masterappdemo.util.calendarutil.CalendarProviderManager;
import com.ying.administrator.masterappdemo.v3.adapter.ProdAdapter;
import com.ying.administrator.masterappdemo.v3.adapter.ProductTollAdapter;
import com.ying.administrator.masterappdemo.v3.bean.GetOrderMoneyDetailResult;
import com.ying.administrator.masterappdemo.v3.bean.ProductTollResult;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.ServingDetailPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.ServingDetailContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.ServingDetailModel;

import org.feezu.liuli.timeselector.TimeSelector;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;
import io.reactivex.functions.Consumer;

import static android.widget.Toast.LENGTH_SHORT;
import static com.ying.administrator.masterappdemo.util.MyUtils.showToast;

/**
 * 工单详情
 */
public class ServingDetailActivity extends BaseActivity<ServingDetailPresenter, ServingDetailModel> implements View.OnClickListener, ServingDetailContract.View, GeocodeSearch.OnGeocodeSearchListener {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.tv_ticket_tracking)
    TextView mTvTicketTracking;
    @BindView(R.id.tv_reservation_again)
    TextView mTvReservationAgain;
    @BindView(R.id.tv_tickets)
    TextView mTvTickets;
    @BindView(R.id.tv_complaint)
    TextView mTvComplaint;
    @BindView(R.id.tv_add_record)
    TextView mTvAddRecord;
    @BindView(R.id.tv_sign_in)
    TextView mTvSignIn;
    @BindView(R.id.tv_state)
    TextView mTvState;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.tv_status)
    TextView mTvStatus;
    @BindView(R.id.tv_numbering)
    TextView mTvNumbering;
    @BindView(R.id.tv_payment)
    TextView mTvPayment;
    @BindView(R.id.tv_billing_time)
    TextView mTvBillingTime;
    @BindView(R.id.tv_appointment)
    TextView mTvAppointment;
    @BindView(R.id.ll_reservation_time)
    LinearLayout mLlReservationTime;
    @BindView(R.id.tv_copy)
    TextView mTvCopy;
    @BindView(R.id.tv_yn)
    TextView mTvYn;
    @BindView(R.id.tv_addressback)
    TextView mTvAddressback;
    @BindView(R.id.tv_postpaytype)
    TextView mTvPostpaytype;
    @BindView(R.id.tv_post_money)
    TextView mTvPostMoney;
    @BindView(R.id.ll_post_money)
    LinearLayout mLlPostMoney;
    @BindView(R.id.ll_address_info)
    LinearLayout mLlAddressInfo;
    @BindView(R.id.ll_old_accessory)
    LinearLayout mLlOldAccessory;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.ll_telephone)
    LinearLayout mLlTelephone;
    @BindView(R.id.tv_distance)
    TextView mTvDistance;
    @BindView(R.id.tv_change_address)
    TextView mTvChangeAddress;
    @BindView(R.id.ll_change_address)
    LinearLayout mLlChangeAddress;
    @BindView(R.id.ll_not_available)
    LinearLayout mLlNotAvailable;
    @BindView(R.id.rv_prods)
    RecyclerView mRvProds;
    @BindView(R.id.ll_call)
    LinearLayout mLlCall;
    @BindView(R.id.tv_success)
    TextView mTvSuccess;
    @BindView(R.id.ll_reservation)
    LinearLayout mLlReservation;
    @BindView(R.id.tv_upload)
    TextView mTvUpload;
    @BindView(R.id.RootView)
    FrameLayout mRootView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_price_standard)
    TextView mTvPriceStandard;
    @BindView(R.id.tv_total)
    TextView mTvTotal;
    @BindView(R.id.ll_total)
    LinearLayout mLlTotal;

    private String orderId;
    private WorkOrder.DataBean data;
    private Intent intent;
    private long recommendedtime;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    private SkeletonScreen skeletonScreen;
    private View callPhoneView;
    private String technologyPhone;
    private PopupWindow mPopupWindow;
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;
    private GeocodeSearch geocoderSearch;
    private LatLng la;
    private ArrayList<String> permissions;
    private int size;
    private String userId;
    private View popupWindow_view;
    private List<WorkOrder.OrderProductModelsBean> prodList = new ArrayList<>();
    private ProdAdapter prodAdapter;
    private UserInfo.UserInfoDean userInfo;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_serving_detail;
    }

    @Override
    protected void initData() {
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");

    }

    @Override
    protected void initView() {
        skeletonScreen = Skeleton.bind(mRootView)
                .load(R.layout.v3_activity_serving_detail_skeleton)
                .duration(2000)
                .color(R.color.shimmer_color)
                .angle(10)
                .show();
        mTvTitle.setText("工单详情");
        intent = getIntent();
        if (null != intent) {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                String title = null;
                String content = null;
                if (bundle != null) {
                    title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
                    if (title != null) {
                        content = bundle.getString(JPushInterface.EXTRA_ALERT);
                        content = bundle.getString(JPushInterface.EXTRA_EXTRA);
                        try {
                            JSONObject jsonObject = new JSONObject(content);
                            orderId = jsonObject.getString("OrderId");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        orderId = getIntent().getStringExtra("id");
                    }
                }
            } else {
                orderId = getIntent().getStringExtra("id");
            }

        } else {
            orderId = getIntent().getStringExtra("id");
        }

        prodAdapter = new ProdAdapter(R.layout.prod_item, prodList);
        mRvProds.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvProds.setAdapter(prodAdapter);
        prodAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_course:
                        intent = new Intent(mActivity, WebActivity.class);
                        intent.putExtra("Url", prodList.get(position).getExplains());
                        intent.putExtra("Title", "产品资料");
                        startActivity(intent);
                        break;
                }
            }
        });
        mPresenter.GetOrderInfo(orderId);
        mPresenter.GetUserInfoList(userId, "1");
        myClipboard = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.GetOrderInfo(orderId);
            }
        });
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mTvUpload.setOnClickListener(this);
        mLlTelephone.setOnClickListener(this);
        mLlCall.setOnClickListener(this);
        mTvTicketTracking.setOnClickListener(this);
        mTvReservationAgain.setOnClickListener(this);
        mTvCopy.setOnClickListener(this);
        mTvTickets.setOnClickListener(this);
        mLlNotAvailable.setOnClickListener(this);
        mTvComplaint.setOnClickListener(this);
        mTvSave.setOnClickListener(this);
        mTvPriceStandard.setOnClickListener(this);
        mLlTotal.setOnClickListener(this);
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_total://费用明细
                if ("5".equals(data.getState())||"7".equals(data.getState())){
                    intent=new Intent(mActivity,FeeDetailActivity.class);
                    intent.putExtra("orderid",orderId);
                    startActivity(intent);
                }else{
                   MyUtils.showToast(mActivity,"工单尚未完工，费用未生成");
                }
                break;
            case R.id.tv_price_standard://价格标准
                mPresenter.ProductToll(orderId,userId);
                break;
            case R.id.tv_save://手动签到
                signType = "2";
                if (data == null) {
                    return;
                }
                showProgress();
                addressChangeLat(data.getAddress());
                break;
            case R.id.iv_back:
                EventBus.getDefault().post(20);//刷新选项卡数量全局搜case 20:
                finish();
                break;
            case R.id.ll_telephone:
                call("tel:" + data.getPhone());
                break;
            case R.id.tv_upload:
                if ("安装".equals(data.getTypeName()) || "保外".equals(data.getGuaranteeText())) {
                    commonmethod();
                } else {
                    // FIXME: 2020-07-22 isApplicationAccessory判断是否可以申请配件
                    if (!data.isApplicationAccessory()) {
                        commonmethod();
                    } else {
                        showPopupWindow();
                    }

                }
                break;
            case R.id.ll_accessories_details:
                Intent intent1 = new Intent(mActivity, AccessoriesDetailsActivity.class);
                intent1.putExtra("id", orderId);
                startActivity(intent1);
                break;
            case R.id.ll_call:
//                call("tel:" + "4006262365");
                callPhoneView = LayoutInflater.from(mActivity).inflate(R.layout.dialog_call, null);
                LinearLayout ll_customer_service = callPhoneView.findViewById(R.id.ll_customer_service);
                LinearLayout ll_technology = callPhoneView.findViewById(R.id.ll_technology);
                TextView tv_cancel = callPhoneView.findViewById(R.id.tv_cancel);
                tv_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismiss();
                    }
                });
                ll_customer_service.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        call("tel:" + "4006262365");
                        mPopupWindow.dismiss();
                    }
                });

                ll_technology.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (technologyPhone == null) {
                            ToastUtils.showShort("暂无技术电话");
                        } else {
                            call("tel:" + technologyPhone);
                        }
                        mPopupWindow.dismiss();
                    }
                });

                mPopupWindow = new PopupWindow(callPhoneView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
                mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
                mPopupWindow.setFocusable(true);
                mPopupWindow.setOutsideTouchable(true);
                mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        MyUtils.setWindowAlpa(mActivity, false);
                    }
                });
                if (mPopupWindow != null && !mPopupWindow.isShowing()) {
                    mPopupWindow.showAtLocation(callPhoneView, Gravity.BOTTOM, 0, 0);
                }
                MyUtils.setWindowAlpa(mActivity, true);
                break;
            case R.id.tv_ticket_tracking:
                intent = new Intent(mActivity, TicketTrackingActivity.class);
                intent.putExtra("id", orderId);
                startActivity(intent);
                break;
            case R.id.tv_reservation_again:
                RxPermissions rxPermissions = new RxPermissions(this);
                rxPermissions.request(Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_CALENDAR)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean) {
                                    // 获取全部权限成功

                                    chooseTime("请选择上门时间");
                                } else {
                                    // 获取全部权限失败
//                                                Log.d("=====>", "权限获取失败");
                                    ToastUtils.showShort("权限获取失败");
                                }
                            }
                        });
                break;
            case R.id.tv_copy:
                myClip = ClipData.newPlainText("", "下单厂家：" + data.getInvoiceName() + "\n"
                        + "工单号：" + data.getOrderID() + "\n"
                        + "下单时间：" + data.getCreateDate() + "\n"
                        + "用户信息：" + data.getUserName() + " " + data.getPhone() + "\n"
                        + "用户地址：" + data.getAddress() + "\n"
                        + "产品信息：" + data.getProductType() + "\n"
                        + "售后类型：" + data.getGuaranteeText() + "\n"
                        + "服务类型：" + data.getTypeName()
                );
                myClipboard.setPrimaryClip(myClip);
                ToastUtils.showShort("复制成功");
                break;
            case R.id.tv_tickets:
                intent = new Intent(mActivity, MessageActivity2.class);
                intent.putExtra("orderId", data.getOrderID());
                startActivity(intent);
                break;
            case R.id.ll_not_available:
                intent = new Intent(mActivity, LogisticsActivity.class);
                intent.putExtra("number", data.getExpressNo() + "");
                startActivity(intent);
                break;
            case R.id.tv_complaint:
                intent = new Intent(mActivity, ComplaintActivity.class);
                intent.putExtra("orderId", orderId);
                startActivity(intent);
                break;
        }
    }

    /**
     * 弹出Popupwindow,选择厂家寄件还是自购件
     */
    public void showPopupWindow() {
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.cj_or_zg_layout, null);
        Button btn_cj = popupWindow_view.findViewById(R.id.btn_cj);
        Button btn_zg = popupWindow_view.findViewById(R.id.btn_zg);
        Button btn_beyond = popupWindow_view.findViewById(R.id.btn_beyond);
        Button btn_complete = popupWindow_view.findViewById(R.id.btn_complete);
        Button btn_cancel = popupWindow_view.findViewById(R.id.btn_cancel);
        btn_beyond.setVisibility(View.GONE);

//        if ("安装".equals(data.getTypeName())||"保外".equals(data.getGuaranteeText())) {
//            btn_cj.setVisibility(View.GONE);
//            btn_zg.setVisibility(View.GONE);
//        } else {
//            if (!data.isApplicationAccessory()) {
//                btn_cj.setVisibility(View.GONE);
//                btn_zg.setVisibility(View.GONE);
//            } else {
//                btn_cj.setVisibility(View.VISIBLE);
//                btn_zg.setVisibility(View.VISIBLE);
//            }
//        }

        btn_cj.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (data.getOrderProductModels() == null) {
                    intent = new Intent(mActivity, ApplyAccActivity.class);
                    intent.putExtra("prodID", "");
                    intent.putExtra("prodName", data.getSubCategoryName());
                    intent.putExtra("SubCategoryID", data.getProductTypeID());

                } else {
                    if (data.getOrderProductModels().size() == 1) {//只有一个产品
                        intent = new Intent(mActivity, ApplyAccActivity.class);
                    } else {
                        intent = new Intent(mActivity, ApplyAcc_ProdsActivity.class);
                        intent.putExtra("data", data);
                    }
                    intent.putExtra("prodID", data.getOrderProductModels().get(0).getOrderProdcutID() + "");
                    intent.putExtra("prodName", data.getOrderProductModels().get(0).getSubCategoryName() + "(编号：" + data.getOrderProductModels().get(0).getOrderProdcutID() + ")");
                    intent.putExtra("SubCategoryID", data.getOrderProductModels().get(0).getProductTypeID() + "");
                }
                intent.putExtra("OrderID", data.getOrderID());
                intent.putExtra("list_prod", (Serializable) data.getOrderProductModels());
                intent.putExtra("cj_or_zg", "厂寄");
                intent.putExtra("order", data);
                startActivity(intent);
                mPopupWindow.dismiss();
            }
        });
        btn_zg.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (data.getOrderProductModels() == null) {
                    intent = new Intent(mActivity, ApplyAccActivity.class);
                    intent.putExtra("prodID", "");
                    intent.putExtra("prodName", data.getSubCategoryName());
                    intent.putExtra("SubCategoryID", data.getProductTypeID());

                } else {
                    if (data.getOrderProductModels().size() == 1) {//只有一个产品
                        intent = new Intent(mActivity, ApplyAccActivity.class);
                    } else {
                        intent = new Intent(mActivity, ApplyAcc_ProdsActivity.class);
                        intent.putExtra("data", data);
                    }
                    intent.putExtra("prodID", data.getOrderProductModels().get(0).getOrderProdcutID() + "");
                    intent.putExtra("prodName", data.getOrderProductModels().get(0).getSubCategoryName() + "(编号：" + data.getOrderProductModels().get(0).getOrderProdcutID() + ")");
                    intent.putExtra("SubCategoryID", data.getOrderProductModels().get(0).getProductTypeID() + "");
                }
                intent.putExtra("OrderID", data.getOrderID());
                intent.putExtra("list_prod", (Serializable) data.getOrderProductModels());
                intent.putExtra("cj_or_zg", "自购");
                intent.putExtra("order", data);
                startActivity(intent);
                mPopupWindow.dismiss();
            }
        });
//        btn_beyond.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                intent = new Intent(mActivity, ApplyFeeActivity.class);
//                intent.putExtra("beyond", data.getDistance());
//                intent.putExtra("orderId", data.getOrderID());
//                intent.putExtra("address_my", userInfo.getAddress());//师傅店铺地址
//                intent.putExtra("address", data.getAddress());//用户地址
//                startActivity(intent);
//                mPopupWindow.dismiss();
//            }
//        });
        //直接完结工单
        btn_complete.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                commonmethod();
                mPopupWindow.dismiss();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
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
    }

    /**
     * 价格标准弹框
     */
    public void showPopupWindowOfPriceStandard(List<ProductTollResult.DataBeanX.DataBean> list) {
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.price_dialog, null);
        RecyclerView rv_list = popupWindow_view.findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(mActivity));
        ProductTollAdapter adapter=new ProductTollAdapter(R.layout.price_item,list);
        rv_list.setAdapter(adapter);
        mPopupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
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
    }

    //单个产品直接完结页面，多个产品跳转到产品列表选择产品完结
    private void commonmethod() {
        if (data.getOrderProductModels().size() > 1) {
            intent = new Intent(mActivity, ApplyAcc_ProdsActivity.class);
            intent.putExtra("complete", true);
            intent.putExtra("list_prod", (Serializable) data.getOrderProductModels());
            intent.putExtra("data", data);
        } else {
            intent = new Intent(mActivity, CompleteWorkOrderActivity.class);
            intent.putExtra("prodID", data.getOrderProductModels().get(0).getOrderProdcutID() + "");
            intent.putExtra("TypeID", data.getTypeID());
            intent.putExtra("BarCodeIsNo", data.getBarCodeIsNo());
            intent.putExtra("data", data.getOrderProductModels().get(0));
        }
        intent.putExtra("OrderID", data.getOrderID());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
//        ButterKnife.bind(this);
    }

    /**
     * 选择上门时间
     */
    public void chooseTime(final String title) {
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
                mPresenter.UpdateSendOrderUpdateTime(orderId, time, time);
            }
        }, format1, "2022-1-1 24:00");

        timeSelector.setTitle(title);
        timeSelector.setNextBtTip("确定");
        timeSelector.show();
    }

    private String signType = "1"; //1.自动签到 2.手动签到
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            //定位结果回调
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
//可在其中解析amapLocation获取相应内容。
                    CircleOptions option = new CircleOptions();
                    option.center(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude()));
                    option.radius(500);
                    MapView mapView = new MapView(mActivity);
                    Circle circle = mapView.getMap().addCircle(option);
                    if (circle.contains(la)) {
                        mPresenter.AddOrderSignInRecrod(userId, signType, orderId);
                    } else {
                        if ("2".equals(signType)) {
                            showToast(mActivity, "请到用户家附近签到");
                        }
                    }
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
                mLocationClient.stopLocation();
            }
        }
    };

    public void Location() {
        //初始化定位
        mLocationClient = new AMapLocationClient(mActivity);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
/**
 * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
 */
        /*mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        if (null != mLocationClient) {
            mLocationClient.setLocationOption(mLocationOption);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }*/
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    //地理编码（地址转坐标）
    private void addressChangeLat(String addr) {
        geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(this);
        // name表示地址，第二个参数表示查询城市，中文或者中文全拼，citycode、adcode
        GeocodeQuery query = new GeocodeQuery(addr, "0086");
        geocoderSearch.getFromLocationNameAsyn(query);
    }
//获得结果

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
        if ("2".equals(signType)) {
            showToast(mActivity, "签到失败");
        }
        hideProgress();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
        if (i == 1000) {
            try {
                double latitude = geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint().getLatitude();
                double longitude = geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint().getLongitude();
                la = new LatLng(latitude, longitude);
                if (requestLocationPermissions()) {
                    Location();
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 20002);
                }
            } catch (Exception e) {
                hideProgress();
                if ("2".equals(signType)) {
                    showToast(mActivity, "签到失败");
                }
                e.printStackTrace();
            }
        }
        hideProgress();
    }

    //请求定位权限
    private boolean requestLocationPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            permissions = new ArrayList<>();
            if (mActivity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (permissions.size() == 0) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    //申请相关权限:返回监听
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        size = 0;
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                size++;
            }
        }
        switch (requestCode) {
            case 20002:
                if (size == grantResults.length) {//允许
                    Location();
                } else {//拒绝
                    showToast(mActivity, "相关权限未开启");
                }
                break;
            default:
                break;

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(AccessoriesNoEvent event) {
        Intent intent1 = new Intent(mActivity, AccessoriesDetailsActivity.class);
        intent1.putExtra("acc_data", event.getAdapter().getData().get(event.getPosition()));
        startActivity(intent1);
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                userInfo = baseResult.getData().getData().get(0);
                break;
        }
    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData() != null) {
                    data = baseResult.getData();
                    if (data.getOrderProductModels() != null) {
                        prodList = data.getOrderProductModels();
                        prodAdapter.setNewData(prodList);
                    }
                    if ("0".equals(data.getIsSignIn())) {//未签到
                        mTvSave.setVisibility(View.VISIBLE);
                        mTvSave.setText("签到");
                        signType = "1";
                        addressChangeLat(data.getAddress());
                    } else {
                        mTvSave.setVisibility(View.GONE);
                    }
                    technologyPhone = data.getArtisanPhone();
                    if ("Y".equals(data.getExtra()) && !"0".equals(data.getExtraTime())) {
                        mTvState.setText(data.getGuaranteeText() + "/" + data.getTypeName() + "/加急");
                    } else {
                        mTvState.setText(data.getGuaranteeText() + "/" + data.getTypeName());
                    }

                    if ("1".equals(data.getPartyNo())) {
                        mTvType.setText("用户发单/" + data.getTypeName());
                    } else {
                        if ("Y".equals(data.getExtra()) && !"0".equals(data.getExtraTime())) {
                            mTvType.setText(data.getGuaranteeText() + "/" + data.getTypeName() + "/加急");
                        } else {
                            mTvType.setText(data.getGuaranteeText() + "/" + data.getTypeName());
                        }
                    }

                    mTvStatus.setText(data.getStateStr());
                    mTvNumbering.setText(data.getOrderID());
                    if (("Y").equals(data.getGuarantee())) {
                        mTvPayment.setText("平台代付");

                    } else {
                        mTvPayment.setText("客户付款");
                    }
                    if (data.getSendOrderList().size() == 0) {
                        mLlReservationTime.setVisibility(View.GONE);
                    } else {
                        mLlReservationTime.setVisibility(View.VISIBLE);
                        mTvAppointment.setText(data.getSendOrderList().get(0).getServiceDate());
                    }

                    mTvBillingTime.setText(data.getCreateDate().replace("T", " "));
                    mTvName.setText(data.getUserName() + "    " + data.getPhone());
                    mTvAddress.setText(data.getAddress());
                    mTvDistance.setText("线路里程 " + data.getDistance() + "公里");


                    if ("4".equals(data.getState())) {//服务中
                        mTvUpload.setVisibility(View.VISIBLE);
                        if ("安装".equals(data.getTypeName()) || "保外".equals(data.getGuaranteeText())) {
                            mTvUpload.setText("提交完结信息");
                        } else {
                            if (!data.isApplicationAccessory()) {
                                mTvUpload.setText("提交完结信息");
                            } else {
                                mTvUpload.setText("操作");
                            }
                        }
                    } else {
                        mTvUpload.setVisibility(View.GONE);
                    }

                }
                if ("安装".equals(data.getTypeName())) {
                    if ("Y".equals(data.getIsRecevieGoods())) {
                        mLlNotAvailable.setVisibility(View.GONE);
                    } else {
                        mLlNotAvailable.setVisibility(View.VISIBLE);
                    }
                }
                if ("5".equals(data.getState())||"7".equals(data.getState())){
                    mPresenter.GetOrderMoneyDetail(orderId);
                }else{
                    mTvTotal.setText("¥--");
                }
                skeletonScreen.hide();
                break;
        }
    }

    @Override
    public void AddReturnAccessory(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void UpdateOrderState(BaseResult<Data<String>> baseResult) {

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
                        EventBus.getDefault().post(20);//刷新选项卡数量全局搜case 20:
                        mPresenter.GetOrderInfo(orderId);
                        Toast.makeText(mActivity, "已为您添加行程至日历,将提前一小时提醒您！！", LENGTH_SHORT).show();
                    } else if (result == -1) {
                        Toast.makeText(mActivity, "插入失败", LENGTH_SHORT).show();
                    } else if (result == -2) {
                        Toast.makeText(mActivity, "没有权限", LENGTH_SHORT).show();
                    }

                } else {
                    showToast(mActivity, (String) baseResult.getData().getItem2());
                }
        }
    }

    @Override
    public void GetBrandWithCategory2(BaseResult<Data<List<GetBrandWithCategory>>> baseResult) {
    }

    @Override
    public void AddOrderSignInRecrod(AddOrderSignInRecrodResult baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isResult()) {
                    if ("2".equals(signType)) {
                        showToast(mActivity, "签到成功");
                    }
                    mPresenter.GetOrderInfo(orderId);
                } else {
                    if ("2".equals(signType)) {
                        showToast(mActivity, "签到失败" + baseResult.getData().getMsg());
                    }
                }
                break;
        }
    }

    @Override
    public void ProductToll(ProductTollResult baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getCode()==0) {
                    showPopupWindowOfPriceStandard(baseResult.getData().getData());
                } else {
                    showToast(mActivity, baseResult.getData().getMsg());
                }
                break;
        }
    }

    @Override
    public void GetOrderMoneyDetail(GetOrderMoneyDetailResult baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getCode() == 0) {
                    mTvTotal.setText("¥"+baseResult.getData().getData().getItem3());
                } else {
                    MyUtils.showToast(mActivity, baseResult.getData().getMsg());
                }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(Integer name) {
        switch (name) {
            case 21:
                mPresenter.GetOrderInfo(orderId);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        EventBus.getDefault().post(20);//刷新选项卡数量全局搜case 20:
    }
}
