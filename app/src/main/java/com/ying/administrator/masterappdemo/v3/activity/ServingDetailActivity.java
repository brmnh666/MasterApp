package com.ying.administrator.masterappdemo.v3.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.AddOrderSignInRecrodResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GetBrandWithCategory;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.ui.activity.ComplaintActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.CompleteWorkOrderActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.MessageActivity2;
import com.ying.administrator.masterappdemo.mvp.ui.activity.ScanActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.WebActivity;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.util.calendarutil.CalendarEvent;
import com.ying.administrator.masterappdemo.util.calendarutil.CalendarProviderManager;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.ServingDetailPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.ServingDetailContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.ServingDetailModel;

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
    @BindView(R.id.tv_copy)
    TextView mTvCopy;
    @BindView(R.id.tv_description)
    TextView mTvDescription;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.ll_telephone)
    LinearLayout mLlTelephone;
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
    @BindView(R.id.ll_reservation)
    LinearLayout mLlReservation;
    @BindView(R.id.tv_upload)
    TextView mTvUpload;
    @BindView(R.id.tv_distance)
    TextView mTvDistance;
    @BindView(R.id.ll_accessories_details)
    LinearLayout mLlAccessoriesDetails;
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
    @BindView(R.id.tv_return)
    TextView mTvReturn;
    @BindView(R.id.tv_tickets)
    TextView mTvTickets;
    @BindView(R.id.tv_band)
    TextView mTvBand;
    @BindView(R.id.ll_band)
    LinearLayout mLlBand;
    @BindView(R.id.tv_appointment)
    TextView mTvAppointment;
    @BindView(R.id.tv_confirm_receipt)
    TextView mTvConfirmReceipt;
    @BindView(R.id.ll_not_available)
    LinearLayout mLlNotAvailable;
    @BindView(R.id.ll_reservation_time)
    LinearLayout mLlReservationTime;
    @BindView(R.id.tv_complaint)
    TextView mTvComplaint;
    @BindView(R.id.RootView)
    FrameLayout mRootView;
    private String orderId;
    private WorkOrder.DataBean data;
    private Intent intent;
    private View puchsh_view;
    private AlertDialog push_dialog;
    private Button btn_negtive;
    private Button btn_positive;
    private TextView tv_title;
    private EditText et_expressno;
    private EditText et_post_money;
    private LinearLayout ll_post_money;
    private LinearLayout ll_scan;
    private TextView tv_remind;
    private String expressno;
    private String post_money;
    private String type;
    private long recommendedtime;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    private View under_review;
    private AlertDialog underReviewDialog;
    private List<GetBrandWithCategory> list;
    private GetBrandWithCategory content;
    private Intent intent2;
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

        type = getIntent().getStringExtra("type");
//        showProgress();
        mPresenter.GetOrderInfo(orderId);
        myClipboard = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mTvUpload.setOnClickListener(this);
        mLlTelephone.setOnClickListener(this);
        mLlAccessoriesDetails.setOnClickListener(this);
        mLlCall.setOnClickListener(this);
        mTvReturn.setOnClickListener(this);
        mTvTicketTracking.setOnClickListener(this);
        mTvReservationAgain.setOnClickListener(this);
        mTvCopy.setOnClickListener(this);
        mTvTickets.setOnClickListener(this);
        mTvConfirmReceipt.setOnClickListener(this);
        mLlNotAvailable.setOnClickListener(this);
        mTvComplaint.setOnClickListener(this);
        mLlMaintenanceInformation.setOnClickListener(this);
        mTvSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_save://手动签到
                signType="2";
                if (data==null){
                    return;
                }
                addressChangeLat(data.getAddress());
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_telephone:
                call("tel:" + data.getPhone());
                break;
            case R.id.tv_upload:
                if ("安装".equals(data.getTypeName())) {
                    intent = new Intent(mActivity, CompleteWorkOrderActivity.class);
                    intent.putExtra("OrderID", data.getOrderID());
                    startActivity(intent);
                } else {
                    if ("0".equals(data.getState())||"31".equals(data.getState())) {//0系统配件待审核 31自定义配件待审核
                        puchsh_view = LayoutInflater.from(mActivity).inflate(R.layout.v3_dialog_prompt, null);
                        TextView title = puchsh_view.findViewById(R.id.title);
                        TextView message = puchsh_view.findViewById(R.id.message);
                        Button negtive = puchsh_view.findViewById(R.id.negtive);
                        title.setText("提示");
                        if ("pedding".equals(type)) {
                            message.setText("您的配件暂未到货，请耐心等待");
                        } else {
                            message.setText("您的配件暂未审核通过，请耐心等待");
                        }

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
                        if ("保外".equals(data.getGuaranteeText())) {
                            intent = new Intent(mActivity, CompleteWorkOrderActivity.class);
                            intent.putExtra("OrderID", data.getOrderID());
                            startActivity(intent);
                        } else {

                            if (data.getOrderAccessroyDetail().size() > 0 || data.getOrderServiceDetail().size() > 0) {
                                intent = new Intent(mActivity, CompleteWorkOrderActivity.class);
                                intent.putExtra("OrderID", data.getOrderID());
                                startActivity(intent);
                            } else {
                                Double money = data.getMasterPrice() + data.getAgainMoney()+ data.getOtherMoney()+ data.getBeyondMoney()+ data.getInitMoney()+ data.getPostMoney();
                                intent = new Intent(mActivity, ApplicationAccessoriesActivity.class);
                                intent.putExtra("id", orderId);
                                intent.putExtra("name", data.getUserName());
                                intent.putExtra("phone", data.getPhone());
                                intent.putExtra("addr", data.getAddress());

                                intent.putExtra("SubCategoryID", data.getProductTypeID());
                                intent.putExtra("total", money);
                                startActivity(intent);
                            }

                        }
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
            case R.id.tv_return:
                puchsh_view = LayoutInflater.from(mActivity).inflate(R.layout.customdialog_add_expressno, null);
                btn_negtive = puchsh_view.findViewById(R.id.negtive);
                btn_positive = puchsh_view.findViewById(R.id.positive);
                tv_title = puchsh_view.findViewById(R.id.title);
                et_expressno = puchsh_view.findViewById(R.id.et_expressno);
                et_post_money = puchsh_view.findViewById(R.id.et_post_money);
                ll_post_money = puchsh_view.findViewById(R.id.ll_post_money);
                ll_scan = puchsh_view.findViewById(R.id.ll_scan);
                tv_remind = puchsh_view.findViewById(R.id.tv_remind);
//                SpannableStringBuilder builder = new SpannableStringBuilder(tv_remind.getText().toString());
//                ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.RED);
//                builder.setSpan(redSpan, 27, 38, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv_remind.setText(Html.fromHtml(mActivity.getResources().getString(R.string.gray_white, "为避免产生不必要的纠纷，请在返件的快递单中填写所完成的", "工单号、用户姓名及电话号码", "，并在下面的输入框中填写正确的快递单号")));
                push_dialog = new AlertDialog.Builder(mActivity)
                        .setView(puchsh_view)
                        .create();
                push_dialog.show();
                tv_title.setText("填写快递单号");
                if ("2".equals(data.getPostPayType())) {
                    ll_post_money.setVisibility(View.VISIBLE);
                } else {
                    ll_post_money.setVisibility(View.GONE);
                }
                btn_negtive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        push_dialog.dismiss();
                    }
                });
                ll_scan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IntentIntegrator integrator = new IntentIntegrator(ServingDetailActivity.this);
                        // 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
                        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
                        integrator.setCaptureActivity(ScanActivity.class); //设置打开摄像头的Activity
                        integrator.setPrompt("请扫描快递码"); //底部的提示文字，设为""可以置空
                        integrator.setCameraId(0); //前置或者后置摄像头
                        integrator.setBeepEnabled(true); //扫描成功的「哔哔」声，默认开启
                        integrator.setBarcodeImageEnabled(true);
                        integrator.initiateScan();
                    }
                });
                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showProgress();
                        expressno = et_expressno.getText().toString().trim();
                        post_money = et_post_money.getText().toString();
                        if ("".equals(expressno)) {
                            showToast(mActivity, "请填写快递单号");
                            hideProgress();
                            return;
                        }
                        if ("2".equals(data.getPostPayType())) {
                            if ("".equals(post_money)) {
                                showToast(mActivity, "请填写邮费");
                                hideProgress();
                                return;
                            } else {
                                mPresenter.AddReturnAccessory(orderId, expressno, post_money);
                            }
                        } else {
                            post_money = "0";
                            mPresenter.AddReturnAccessory(orderId, expressno, post_money);
                        }


                    }
                });

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
            case R.id.tv_tickets:
                intent = new Intent(mActivity, MessageActivity2.class);
                intent.putExtra("orderId", data.getOrderID());
                startActivity(intent);
                break;
            case R.id.tv_confirm_receipt:
                mPresenter.ConfirmReceipt(orderId);
//                under_review = LayoutInflater.from(mActivity).inflate(R.layout.v3_dialog_real, null);
//                TextView tv_cancel = under_review.findViewById(R.id.tv_cancel);
//                TextView tv_reservation = under_review.findViewById(R.id.tv_reservation);
//                TextView tv_content = under_review.findViewById(R.id.tv_content);
//                tv_reservation.setText("去预约");
//                tv_content.setText("是否直接预约");
//                tv_cancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        underReviewDialog.dismiss();
//                    }
//                });
//
//                tv_reservation.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        startActivity(new Intent(mActivity, VerifiedActivity2.class));
//                        underReviewDialog.dismiss();
//                    }
//                });
//                underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review).create();
//                underReviewDialog.show();
                break;
            case R.id.ll_not_available:
                intent2 = new Intent(mActivity, LogisticsActivity.class);
                intent2.putExtra("number", data.getExpressNo() + "");
                startActivity(intent2);
                break;
            case R.id.tv_complaint:
                intent2 = new Intent(mActivity, ComplaintActivity.class);
                intent2.putExtra("orderId", orderId);
                startActivity(intent2);
                break;
            case R.id.ll_maintenance_information:
                intent2 = new Intent(mActivity, WebActivity.class);
                intent2.putExtra("Url", content.getCourseCount());
                intent2.putExtra("Title", content.getBrandName() + content.getProductTypeName());
                startActivity(intent2);
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


//               Intent intent=new Intent(mActivity, WorkOrderDetailsActivity2.class);
//                intent.putExtra("OrderID",OrderId);
//                intent.putExtra("time",time);
//                startActivity(intent);
//                successposition=position;


            }
        }, format1, "2022-1-1 24:00");

        timeSelector.setTitle(title);
        timeSelector.setNextBtTip("确定");
        timeSelector.show();
    }

    private String signType="1"; //1.自动签到 2.手动签到
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
                        mPresenter.AddOrderSignInRecrod(userId, signType,orderId);
                    }else{
                        if ("2".equals(signType)){
                            MyUtils.showToast("请到用户家附近签到");
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
        GeocodeQuery query = new GeocodeQuery(addr, "0086");
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
//                    mPresenter.GetBrandWithCategory2("17777777777","285","304","1043","1044","1","99");
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
//                    mTvAppointment.setText(data.getIsExtraTime().replace("T"," "));
                    if (data.getSendOrderList().size() == 0) {
                        mLlReservationTime.setVisibility(View.GONE);
                    } else {
                        mLlReservationTime.setVisibility(View.VISIBLE);
                        mTvAppointment.setText(data.getSendOrderList().get(0).getServiceDate());
                    }

                    mTvBillingTime.setText(data.getCreateDate().replace("T", " "));
                    mTvDescription.setText("描述：" + data.getMemo());
                    mTvName.setText(data.getUserName() + "    " + data.getPhone());
                    mTvAddress.setText(data.getAddress());
                    mTvDistance.setText("线路里程 " + data.getDistance() + "公里");
                    if (data.getOrderServiceDetail().size() > 0 || data.getOrderAccessroyDetail().size() > 0) {
                        mLlAccessoriesDetails.setVisibility(View.VISIBLE);
                    } else {
                        mLlAccessoriesDetails.setVisibility(View.GONE);
                    }


                    if ("2".equals(data.getTypeID())) {
                        mLlOldAccessory.setVisibility(View.GONE);
                    } else {
                        if ("1".equals(data.getAccessoryAndServiceApplyState()) || "2".equals(data.getAccessoryAndServiceApplyState()) || "".equals(data.getAccessoryAndServiceApplyState())) {
                            mLlOldAccessory.setVisibility(View.VISIBLE);
                            if (data.getOrderAccessroyDetail().size() > 0) {
                                if ("1".equals(data.getIsReturn())) {
                                    mTvYn.setText("是");
                                    mLlAddressInfo.setVisibility(View.VISIBLE);
                                    mTvAddressback.setText(data.getAddressBack());
                                    if ("1".equals(data.getPostPayType())) {
                                        mTvPostpaytype.setText("厂商到付");
                                    } else {
                                        mTvPostpaytype.setText("维修商现付");
                                    }
                                } else {
                                    mTvYn.setText("否");
                                    mLlAddressInfo.setVisibility(View.GONE);
                                }
                            } else {
                                mLlOldAccessory.setVisibility(View.GONE);
                            }
                        } else {
                            mLlOldAccessory.setVisibility(View.GONE);
                        }
                    }

                    if ("5".equals(data.getState()) || "6".equals(data.getState()) || "7".equals(data.getState()) || "-4".equals(data.getState()) || "-1".equals(data.getState())) {
                        mTvUpload.setVisibility(View.INVISIBLE);
                        mTvReservationAgain.setBackgroundResource(R.drawable.v3_gray_shape);
                        mTvReservationAgain.setEnabled(false);
                        mTvConfirmReceipt.setVisibility(View.GONE);
                    } else {
                        if ("8".equals(data.getState())) {
                            mTvUpload.setVisibility(View.GONE);
                            mTvReturn.setVisibility(View.VISIBLE);
                            mTvReservationAgain.setBackgroundResource(R.drawable.v3_gray_shape);
                            mTvReservationAgain.setEnabled(false);
                            mTvConfirmReceipt.setVisibility(View.GONE);
                        } else if ("11".equals(data.getState())) {
                            mTvUpload.setVisibility(View.GONE);
                            mTvReturn.setVisibility(View.GONE);
                            mTvReservationAgain.setBackgroundResource(R.drawable.v3_blue_white_shape);
                            mTvReservationAgain.setEnabled(true);
                            mTvConfirmReceipt.setVisibility(View.VISIBLE);
                        } else {
                            mTvUpload.setVisibility(View.VISIBLE);
                            mTvReturn.setVisibility(View.GONE);
                            mTvReservationAgain.setBackgroundResource(R.drawable.v3_blue_white_shape);
                            mTvReservationAgain.setEnabled(true);
                            mTvConfirmReceipt.setVisibility(View.GONE);
                        }
                    }

                    mTvBand.setText(data.getBrandName() + "  " + data.getProductType());

                    if ("安装".equals(data.getTypeName())) {
                        mTvUpload.setText("提交完结信息");
                        mTvUpload.setBackgroundResource(R.drawable.v3_copy_bg_shape);
                    } else {
                        if ("0".equals(data.getState())||"31".equals(data.getState())) {//0系统配件待审核 31自定义配件待审核
//                            mTvUpload.setText("提交完结信息");
                            if ("pedding".equals(type)) {
                                mTvUpload.setText("配件未到货");
                            } else {
                                mTvUpload.setText("配件审核中");
                            }
                            mTvUpload.setBackgroundResource(R.drawable.v3_copy_bg_shape2);
                        } else {
                            if (data.getOrderAccessroyDetail().size() > 0 || data.getOrderServiceDetail().size() > 0) {
                                mTvUpload.setText("提交完结信息");
                            } else {
                                mTvUpload.setText("添加服务内容");
                            }
                            mTvUpload.setBackgroundResource(R.drawable.v3_copy_bg_shape);
                        }

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
                }
//                hideProgress();
                break;
        }
    }

    @Override
    public void AddReturnAccessory(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data = baseResult.getData();
                if (data.isItem1()) {
                    push_dialog.dismiss();
                    ToastUtils.showShort("提交成功");
                    mPresenter.UpdateOrderState(orderId, "5", "");
                    EventBus.getDefault().post(5);
                    finish();
                } else {
                    ToastUtils.showShort(data.getItem2());
                }
//                hideProgress();
                break;
        }
    }

    @Override
    public void UpdateOrderState(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    finish();
                    EventBus.getDefault().post("WorkOrderDetailsActivity");
                    EventBus.getDefault().post(5);
                }
                break;

        }
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
    public void ConfirmReceipt(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                ToastUtils.showShort("收货成功");
                EventBus.getDefault().post(22);
                finish();
                break;
        }
    }

    @Override
    public void GetBrandWithCategory2(BaseResult<Data<List<GetBrandWithCategory>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                try{
                    list = baseResult.getData().getItem2();
                    if (list.size() == 0) {
                        mLlMaintenanceInformation.setVisibility(View.GONE);
                    } else {
//                    mLlMaintenanceInformation.setVisibility(View.VISIBLE);
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
                }catch (Exception ex){
                    return;
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
                        MyUtils.showToast("签到成功");
                    }
                    mPresenter.GetOrderInfo(orderId);
                }else{
                    if ("2".equals(signType)){
                        MyUtils.showToast("签到失败"+baseResult.getData().getMsg());
                    }
                }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(Integer name) {
//        if ("WorkOrderDetailsActivity".equals(name)) {
//            mLlAccessoriesDetails.setVisibility(View.VISIBLE);
//        }
        switch (name) {
            case 21:
                mLlAccessoriesDetails.setVisibility(View.VISIBLE);
                finish();
                break;
            case 5:
            case 4:
                finish();
                break;
        }
    }


    //返回图片处理
    @SuppressLint("NewApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            String result = scanResult.getContents();
            if (result == null) {
                return;
            } else {
                et_expressno.setText(result);
            }

        }
    }
}
