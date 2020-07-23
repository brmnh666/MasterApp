package com.ying.administrator.masterappdemo.v3.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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
import android.widget.Button;
import android.widget.EditText;
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
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.AddOrderSignInRecrodResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GetBrandWithCategory;
import com.ying.administrator.masterappdemo.entity.SubUserInfo;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.ui.activity.WebActivity;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Redeploy_Adapter;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.util.calendarutil.CalendarEvent;
import com.ying.administrator.masterappdemo.util.calendarutil.CalendarProviderManager;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.AppointmentDetailsPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.AppointmentDetailsContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.AppointmentDetailsModel;
import com.ying.administrator.masterappdemo.widget.CustomDialog_Redeploy;

import org.feezu.liuli.timeselector.TimeSelector;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

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
/**
 * 工单详情
 */
public class AppointmentDetailsActivity extends BaseActivity<AppointmentDetailsPresenter, AppointmentDetailsModel> implements View.OnClickListener, AppointmentDetailsContract.View,GeocodeSearch.OnGeocodeSearchListener {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
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
    @BindView(R.id.tv_copy)
    TextView mTvCopy;
    @BindView(R.id.tv_description)
    TextView mTvDescription;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_change_address)
    TextView mTvChangeAddress;
    @BindView(R.id.ll_change_address)
    LinearLayout mLlChangeAddress;
    @BindView(R.id.tv_platform_price)
    TextView mTvPlatformPrice;
    @BindView(R.id.ll_platform_price)
    LinearLayout mLlPlatformPrice;
    @BindView(R.id.iv_picture)
    ImageView mIvPicture;
    @BindView(R.id.tv_product_name)
    TextView mTvProductName;
    @BindView(R.id.tv_specifications)
    TextView mTvSpecifications;
    @BindView(R.id.tv_maintenance_information)
    TextView mTvMaintenanceInformation;
    @BindView(R.id.ll_maintenance_information)
    LinearLayout mLlMaintenanceInformation;
    @BindView(R.id.ll_call)
    LinearLayout mLlCall;
    @BindView(R.id.tv_success)
    TextView mTvSuccess;
    @BindView(R.id.tv_cancel)
    TextView mTvCancel;
    @BindView(R.id.ll_reservation)
    LinearLayout mLlReservation;
    @BindView(R.id.tv_reservation)
    TextView mTvReservation;
    @BindView(R.id.ll_telephone)
    LinearLayout mLlTelephone;
    @BindView(R.id.tv_distance)
    TextView mTvDistance;
    @BindView(R.id.tv_brand)
    TextView mTvBrand;
    @BindView(R.id.ll_not_available)
    LinearLayout mLlNotAvailable;
    @BindView(R.id.tv_transfer)
    TextView mTvTransfer;
    @BindView(R.id.RootView)
    FrameLayout mRootView;
    @BindView(R.id.ll_apply_for_remote_fee)
    LinearLayout mLlApplyForRemoteFee;
    private View under_review;
    private AlertDialog underReviewDialog;
    private long recommendedtime;
    private String orderId;
    private WorkOrder.DataBean data;
    private EditText et_message;
    private Button negtive;
    private Button positive;
    private TextView title;
    private AlertDialog cancelDialog;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    private View puchsh_view;
    private AlertDialog push_dialog;
    private SPUtils spUtils;
    private String userID;
    private UserInfo.UserInfoDean userInfo = new UserInfo.UserInfoDean(); //获取当前账号详情
    private ArrayList<SubUserInfo.SubUserInfoDean> subuserlist = new ArrayList<>();//获取子账号列表
    private CustomDialog_Redeploy customDialog_redeploy;//转派dialog
    private RecyclerView recyclerView_custom_redeploy;
    private Redeploy_Adapter redeploy_adapter;
    private String SubUserID;
    private List<GetBrandWithCategory> list;
    private GetBrandWithCategory content;
    private SkeletonScreen skeletonScreen;
    private View callPhoneView;
    private PopupWindow mPopupWindow;
    private String technologyPhone;
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;
    private GeocodeSearch geocoderSearch;
    private LatLng la;
    private ArrayList<String> permissions;
    private int size;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_appointment_details;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        skeletonScreen = Skeleton.bind(mRootView)
                .load(R.layout.v3_activity_serving_detail_skeleton)
                .duration(2000)
                .color(R.color.shimmer_color)
                .angle(10)
                .show();
        mTvTitle.setText("预约详情");
//        orderId = getIntent().getStringExtra("id");
        Intent intent = getIntent();
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
        mPresenter.GetOrderInfo(orderId);
        spUtils = SPUtils.getInstance("token");
        userID = spUtils.getString("userName");
//        mPresenter.GetUserInfoList(userID, "1");
        myClipboard = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mTvReservation.setOnClickListener(this);
        mLlTelephone.setOnClickListener(this);
        mTvCancel.setOnClickListener(this);
        mLlCall.setOnClickListener(this);
        mTvCopy.setOnClickListener(this);
        mLlNotAvailable.setOnClickListener(this);
        mTvTransfer.setOnClickListener(this);
        mLlMaintenanceInformation.setOnClickListener(this);
        mLlApplyForRemoteFee.setOnClickListener(this);
        mTvSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_save:
                signType="2";
                if (data==null){
                    return;
                }
                addressChangeLat(data.getAddress());
                break;
            case R.id.tv_reservation:
                if ("9".equals(data.getState())) {
                    puchsh_view = LayoutInflater.from(mActivity).inflate(R.layout.v3_dialog_prompt, null);
                    TextView title = puchsh_view.findViewById(R.id.title);
                    TextView message = puchsh_view.findViewById(R.id.message);
                    Button negtive = puchsh_view.findViewById(R.id.negtive);
                    title.setText("提示");
                    message.setText("您的远程费暂未审核通过，请耐心等待");
                    negtive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            push_dialog.dismiss();
                        }
                    });
                    push_dialog = new AlertDialog.Builder(mActivity)
                            .setView(puchsh_view)
                            .create();
                    push_dialog.show();
                } else {

                    if (data.getIsCall() == null) {
                        under_review = LayoutInflater.from(mActivity).inflate(R.layout.v3_dialog_reservation, null);
                        TextView tv_cancel = under_review.findViewById(R.id.tv_cancel);
                        TextView tv_reservation = under_review.findViewById(R.id.tv_reservation);
                        tv_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                underReviewDialog.dismiss();
                            }
                        });

                        tv_reservation.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                call("tel:" + data.getPhone());
                                mPresenter.OrderIsCall(orderId, "Y");
                                underReviewDialog.dismiss();
                            }
                        });
                        underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review).create();
                        underReviewDialog.show();
                    } else {
                        if (data.getOrderAccessroyDetail().size() > 0) {
                            if ("Y".equals(data.getIsCall())) {
                                under_review = LayoutInflater.from(mActivity).inflate(R.layout.v3_dialog_reservation, null);
                                TextView tv_cancel = under_review.findViewById(R.id.tv_cancel);
                                TextView tv_reservation = under_review.findViewById(R.id.tv_reservation);
                                tv_cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        underReviewDialog.dismiss();
                                    }
                                });

                                tv_reservation.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        call("tel:" + data.getPhone());
                                        mPresenter.OrderIsCall(orderId, "2");
                                        underReviewDialog.dismiss();
                                    }
                                });
                                underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review).create();
                                underReviewDialog.show();
                            } else {
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
                            }
                        } else {
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
                        }

                    }
//

                }
                break;
            case R.id.ll_telephone:
                call("tel:" + data.getPhone());
                if (data.getOrderAccessroyDetail().size() > 0) {
                    mPresenter.OrderIsCall(orderId, "2");
                } else {
                    mPresenter.OrderIsCall(orderId, "Y");
                }

                break;
            case R.id.tv_cancel:

                View Cancelview = LayoutInflater.from(mActivity).inflate(R.layout.dialog_cancel, null);
                et_message = Cancelview.findViewById(R.id.et_message);
                negtive = Cancelview.findViewById(R.id.negtive);
                positive = Cancelview.findViewById(R.id.positive);
                title = Cancelview.findViewById(R.id.title);
                title.setText("是否取消工单");
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
                            mPresenter.UpdateSendOrderState(orderId, "-1", message);
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
            case R.id.ll_call:
//                call("tel:" + "4006262365");
                callPhoneView = LayoutInflater.from(mActivity).inflate(R.layout.dialog_call,null);
                LinearLayout ll_customer_service=callPhoneView.findViewById(R.id.ll_customer_service);
                LinearLayout ll_technology=callPhoneView.findViewById(R.id.ll_technology);
                TextView tv_cancel=callPhoneView.findViewById(R.id.tv_cancel);
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
                        if (technologyPhone==null){
                            ToastUtils.showShort("暂无技术电话");
                        }else {
                            call("tel:"+technologyPhone);
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
            case R.id.tv_copy:
                myClip = ClipData.newPlainText("", "下单厂家："+data.getInvoiceName() + "\n"
                        +"工单号："+data.getOrderID() + "\n"
                        +"下单时间："+data.getCreateDate() + "\n"
                        +"用户信息："+data.getUserName()+" "+data.getPhone() + "\n"
                        +"用户地址："+data.getAddress() + "\n"
                        +"产品信息："+data.getProductType() + "\n"
                        +"售后类型："+data.getGuaranteeText() + "\n"
                        +"服务类型："+data.getTypeName()
                );
                myClipboard.setPrimaryClip(myClip);
                ToastUtils.showShort("复制成功");
                break;
            case R.id.ll_not_available:
                Intent intent = new Intent(mActivity, LogisticsActivity.class);
                intent.putExtra("number", data.getExpressNo() + "");
                startActivity(intent);
                break;
            case R.id.tv_transfer:
                customDialog_redeploy = new CustomDialog_Redeploy(mActivity);
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
                            Toast.makeText(mActivity, "您还没选择子账号进行转派", LENGTH_SHORT).show();
                            //  customDialog_redeploy.dismiss(); //没选择人进行选派
                        } else {
                            //转派成功状态恢复原状

                            for (int i = 0; i < subuserlist.size(); i++) {
                                subuserlist.get(i).setIscheck(false);
                            }
                            //转派成功 刷新当前页面
//                            redeployposition = position;
                            mPresenter.ChangeSendOrder(orderId, SubUserID);
                            customDialog_redeploy.dismiss();
                            // mRefreshLayout.autoRefresh(0,0,1);
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
            case R.id.ll_maintenance_information:
                Intent intent2 = new Intent(mActivity, WebActivity.class);
                intent2.putExtra("Url", content.getCourseCount());
                intent2.putExtra("Title", content.getBrandName() + content.getProductTypeName());
                startActivity(intent2);
                break;
            case R.id.ll_apply_for_remote_fee:
                Intent intent1 = new Intent(mActivity, ApplyFeeActivity.class);
                intent1.putExtra("beyond", data.getDistance());
//                intent1.putExtra("position", position);
                intent1.putExtra("orderId", data.getOrderID());
                intent1.putExtra("address_my", userInfo.getAddress());//师傅店铺地址
                intent1.putExtra("address", data.getAddress());//用户地址
                startActivity(intent1);
                break;
        }
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

    private String signType="1"; //1.自动签到  2.手动签到
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
                    MapView mapView=new MapView(mActivity);
                    Circle circle=mapView.getMap().addCircle(option);
                    if(circle.contains(la)){
                        mPresenter.AddOrderSignInRecrod(userID, signType,orderId);
                    }else{
                        if ("2".equals(signType)){
                            MyUtils.showToast(mActivity,"请到用户家附近签到");
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
    private void addressChangeLat(String addr){
        geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(this);
        // name表示地址，第二个参数表示查询城市，中文或者中文全拼，citycode、adcode
        GeocodeQuery query = new GeocodeQuery(addr, "");
        geocoderSearch.getFromLocationNameAsyn(query);
    }
//获得结果

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
        if(i==1000){
            double latitude=geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint().getLatitude();
            double longitude=geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint().getLongitude();
            la =new LatLng(latitude,longitude);
            if (requestLocationPermissions()) {
                Location();
            } else {
                requestPermissions(permissions.toArray(new String[permissions.size()]), 20002);
            }
        }

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
                    MyUtils.showToast(mActivity, "相关权限未开启");
                }
                break;
            default:
                break;

        }
    }
    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData() != null) {
                    data = baseResult.getData();
                    if ("0".equals(data.getIsSignIn())){//未签到
                        mTvSave.setVisibility(View.VISIBLE);
                        mTvSave.setText("签到");
                        signType="1";
                        addressChangeLat(data.getAddress());
                    }else{
                        mTvSave.setVisibility(View.GONE);
                    }
                    technologyPhone = data.getArtisanPhone();
                    mPresenter.GetBrandWithCategory2(data.getUserID(), data.getBrandID(), data.getCategoryID(), data.getSubCategoryID(), data.getProductTypeID(), "1", "999");
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

                    if ("安装".equals(data.getTypeName())) {
                        if ("Y".equals(data.getIsRecevieGoods())) {
                            mLlNotAvailable.setVisibility(View.GONE);
                        } else {
                            mLlNotAvailable.setVisibility(View.VISIBLE);
//                            mLlNumber.setVisibility(View.VISIBLE);
//                            mViewSigning.setVisibility(View.VISIBLE);
//                            mTvSigning.setText("否");
//                            expressType = 1;
//                            if ("".equals(data.getExpressNo()) || data.getExpressNo() == null) {
//                                mTvContent.setText("暂无物流消息");
//                            } else {
//                                mPresenter.GetExpressInfo(data.getExpressNo());
//                            }

                        }
                    } else {
                        mLlNotAvailable.setVisibility(View.GONE);
                    }
                    mTvStatus.setText(data.getStateStr());
                    mTvNumbering.setText(data.getOrderID());
                    if (("Y").equals(data.getGuarantee())) {
                        mTvPayment.setText("平台代付");

                    } else {
                        mTvPayment.setText("客户付款");

                    }

                    if (data.getOrderAccessroyDetail().size() > 0) {
                        mTvTransfer.setVisibility(View.GONE);
                    } else {
                        mPresenter.GetUserInfoList(userID, "1");
                    }

                    mTvBillingTime.setText(data.getCreateDate().replace("T", " "));
                    mTvDescription.setText("描述：" + data.getMemo());
                    mTvName.setText(data.getUserName() + "    " + data.getPhone());
                    mTvAddress.setText(data.getAddress());
                    mTvDistance.setText("线路里程 " + data.getDistance() + "公里");
                    mTvBrand.setText(data.getBrandName() + "  " + data.getProductType());

                    if ("9".equals(data.getState())) {
                        mLlReservation.setVisibility(View.GONE);
                    } else {
                        mLlReservation.setVisibility(View.VISIBLE);
                    }
                    if (data.getOrderAccessroyDetail().size()==0){
                        if (Double.parseDouble(data.getDistance()) > 0 && !"999".equals(data.getDistance())) {
                            mLlApplyForRemoteFee.setVisibility(View.VISIBLE);
                        } else {
                            mLlApplyForRemoteFee.setVisibility(View.GONE);
                        }
                    }else {
                        mLlApplyForRemoteFee.setVisibility(View.GONE);
                    }

                }

                break;
        }
    }

    @Override
    public void OrderIsCall(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    mPresenter.GetOrderInfo(orderId);
                }
                break;
        }
    }

    @Override
    public void UpdateSendOrderState(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    //mRefreshLayout.autoRefresh(0,0,1);
                    finish();
                } else {
                    Toast.makeText(mActivity, "取消失败", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void UpdateSendOrderUpdateTime(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:

                if (baseResult.getData().isItem1()) {
                    mPresenter.AddOrderSuccess(orderId, "1", "预约成功");
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
                        Toast.makeText(mActivity, "已为您添加行程至日历,将提前一小时提醒您！！", LENGTH_SHORT).show();
                    } else if (result == -1) {
                        Toast.makeText(mActivity, "插入失败", LENGTH_SHORT).show();
                    } else if (result == -2) {
                        Toast.makeText(mActivity, "没有权限", LENGTH_SHORT).show();
                    }

                }
        }
    }

    @Override
    public void AddOrderSuccess(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {

            case 200:
                if (baseResult.getData().isItem1()) {
                    ToastUtils.showShort("预约成功");
                    //Toast.makeText(getActivity(),"预约成功请到服务中",Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post(2);//预约成功跳转到服务中
                    EventBus.getDefault().post("待服务");
                    finish();
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
                if (userInfo.getParentUserID() == null || "".equals(userInfo.getParentUserID())) {//如果没有父账号说明自己是父账号 显示 转派
                    //helper.setGone(R.id.tv_pending_appointment_redeploy,true);
                    mPresenter.GetChildAccountByParentUserID(userID);

                } else {
                    mTvTransfer.setVisibility(View.GONE);
                }


                break;
        }
    }

    @Override
    public void GetChildAccountByParentUserID(BaseResult<List<SubUserInfo.SubUserInfoDean>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                subuserlist.addAll(baseResult.getData());
                if (subuserlist.size() != 0) { //有子账号
                    mTvTransfer.setVisibility(View.VISIBLE);
                } else {//没有子账号
                    mTvTransfer.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    public void ChangeSendOrder(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    Toast.makeText(mActivity, "转派成功", LENGTH_SHORT).show();
//                    pending_appointment_adapter.remove(redeployposition);
                    finish();
                } else {

                    Toast.makeText(mActivity, "转派失败", LENGTH_SHORT).show();
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void GetBrandWithCategory2(BaseResult<Data<List<GetBrandWithCategory>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                list = baseResult.getData().getItem2();
                if (list.size() == 0) {
                    mLlMaintenanceInformation.setVisibility(View.GONE);
                } else {

                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getCourseCount() != null) {
                            mLlMaintenanceInformation.setVisibility(View.VISIBLE);
                            mTvProductName.setText(list.get(i).getBrandName() + "  " + list.get(i).getProductTypeName());
                            if (list.get(i).getImge() == null) {
                                Glide.with(mActivity)
                                        .load(R.drawable.v3_zanwu)
                                        .into(mIvPicture);
                            } else {
                                Glide.with(mActivity)
                                        .load(Config.Leave_product_URL + list.get(i).getImge())
                                        .into(mIvPicture);
                            }
                            content = list.get(i);
                            break;
                        } else {
                            mLlMaintenanceInformation.setVisibility(View.GONE);
                        }
                    }

                }
                skeletonScreen.hide();
                break;
        }
    }

    @Override
    public void AddOrderSignInRecrod(AddOrderSignInRecrodResult baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isResult()){
                    if ("2".equals(signType)){
                        MyUtils.showToast(mActivity,"签到成功");
                    }
                    mPresenter.GetOrderInfo(orderId);
                }else{
                    if ("2".equals(signType)){
                        MyUtils.showToast(mActivity,"签到失败"+baseResult.getData().getMsg());
                    }
                }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        if ("deyond".equals(name)) {
            mPresenter.GetOrderInfo(orderId);
        }
    }
}
