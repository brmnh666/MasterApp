package com.ying.administrator.masterappdemo.mvp.ui.fragment;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.CardView;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.searchdemo.MainActivity;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.Article;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.AllWorkOrdersContract;
import com.ying.administrator.masterappdemo.mvp.model.AllWorkOrdersModel;
import com.ying.administrator.masterappdemo.mvp.presenter.AllWorkOrdersPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.activity.ApplyFeeActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Order_Receiving_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Order_details_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Personal_Information_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.QuoteActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.RechargeActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.VerifiedUpdateActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Verified_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Wallet_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.WebActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.WorkOrderDetailsActivity2;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.GrabsheetAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Pending_Adapter;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;
import com.ying.administrator.masterappdemo.util.Glide4Engine;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.util.ZXingUtils;
import com.ying.administrator.masterappdemo.util.calendarutil.CalendarEvent;
import com.ying.administrator.masterappdemo.util.calendarutil.CalendarProviderManager;
import com.ying.administrator.masterappdemo.util.imageutil.CompressHelper;
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;
import com.ying.administrator.masterappdemo.widget.CustomDialog;
import com.ying.administrator.masterappdemo.widget.CustomDialog_UnSuccess;
import com.ying.administrator.masterappdemo.widget.GlideCircleWithBorder_Home;
import com.ying.administrator.masterappdemo.widget.ShareDialog;
import com.ying.administrator.masterappdemo.widget.SwitchView;
import com.ying.administrator.masterappdemo.widget.WrapContentLinearLayoutManager;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import org.feezu.liuli.timeselector.TimeSelector;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static android.widget.Toast.LENGTH_SHORT;

public class Home_Fragment extends BaseLazyFragment<AllWorkOrdersPresenter, AllWorkOrdersModel> implements AllWorkOrdersContract.View, View.OnClickListener, EasyPermissions.PermissionCallbacks {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";
    @BindView(R.id.img_home_customer_service)
    ImageView mImg_home_customer_service;
    @BindView(R.id.img_home_qr_code)
    ImageView mImgHomeQrCode;
    @BindView(R.id.actionbar_layout)
    RelativeLayout mActionbarLayout;
    @BindView(R.id.img_home_head)
    ImageView mImgHomeHead;
    @BindView(R.id.img_home_withdraw)
    ImageView mImgHomeWithdraw;
    @BindView(R.id.tv_home_available_balance)
    TextView mTvHomeAvailableBalance;     //可提现余额 = TotalMoney-FrozenMoney
    @BindView(R.id.img_home_unfinished)
    ImageView mImgHomeUnfinished;
    @BindView(R.id.tv_home_outstanding_amount)
    TextView mTvHomeOutstandingAmount;    //冻结金额   = FrozenMoney
    @BindView(R.id.img_home_free_gold)
    ImageView mImgHomeFreeGold;
    @BindView(R.id.tv_home_giving_money)
    TextView mTvHomeGivingMoney;      //赠送金额
    @BindView(R.id.tv_certification) //实名认证
            TextView mTvCertification;
    @BindView(R.id.img_certification)//实名认证图片
            ImageView mImgCertification;
    @BindView(R.id.img_un_certification) //未实名认证图片
            ImageView mImg_un_certification;
    @BindView(R.id.img_home_location)
    ImageView mImgHomeLocation;
    @BindView(R.id.tv_home_location)
    TextView mTvHomeLocation;
    @BindView(R.id.cv_home_user)
    CardView mCvHomeUser;
    @BindView(R.id.img_new_order)
    ImageView mImgNewOrder;
    @BindView(R.id.tv_new_order)
    TextView mTvNewOrder;
    @BindView(R.id.tv_home_refresh)
    TextView mTvHomeRefresh;
    @BindView(R.id.img_home_refresh)
    ImageView mimg_home_refresh;
    @BindView(R.id.recyclerview_order_receiving)
    RecyclerView mRecyclerviewOrderReceiving;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.ll_money)
    LinearLayout mLlMoney;
    Unbinder unbinder1;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.tv_edemption)
    TextView mTvEdemption;
    @BindView(R.id.tv_finish)
    TextView mTvFinish;
    @BindView(R.id.tv_complaint)
    TextView mTvComplaint;
    @BindView(R.id.ll_finsh)
    LinearLayout mLlFinsh;
    @BindView(R.id.ll_complaint)
    LinearLayout mLlComplaint;
    @BindView(R.id.scrolltv)
    SwitchView mScrolltv;
    @BindView(R.id.ll_switch)
    LinearLayout mLlSwitch;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.ll_infor)
    LinearLayout mLlInfor;
    @BindView(R.id.iv_ads)
    ImageView mIvAds;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    // private WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;
    private String mContentText;
    private View mRootView;

    private GrabsheetAdapter grabsheetAdapter;
    private WorkOrder workOrder;
    private List<WorkOrder.DataBean> list = new ArrayList<>();
    private List<WorkOrder.DataBean> Pendinglist = new ArrayList<>();
    private WorkOrder.DataBean data = new WorkOrder.DataBean();
    private int pageIndex = 1;  //默认当前页数为1
    private String userID;//用户id
    SPUtils spUtils = SPUtils.getInstance("token");
    private int grabposition;
    private UserInfo.UserInfoDean userInfo = new UserInfo.UserInfoDean();
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private int mLocationType;
    private double mLatitude;
    private double mLongitude;
    private float mAccuracy;
    private String mAddress;
    private String mCountry;
    private String mProvince;
    private String mCity;
    private String mDistrict;
    private String mStreet;
    private String mStreetNum;
    private String mCityCode;
    private String mAdCode;
    private String mAoiName;
    private String mBuildingId;
    private String mFloor;
    private int mGpsAccuracyStatus;
    private String mTime;
    private int i = 0;
    private List<Article.DataBean> datalist = new ArrayList<Article.DataBean>();
    private ObjectAnimator animator; //刷新图片属性动画

    private ObjectAnimator animator_order; //刷新图片属性动画


    private ZLoadingDialog dialog;
    private Vibrator vibrator;//震动
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            //定位结果回调
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
//可在其中解析amapLocation获取相应内容。
                    mLocationType = aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    mLatitude = aMapLocation.getLatitude();//获取纬度
                    mLongitude = aMapLocation.getLongitude();//获取经度
                    mAccuracy = aMapLocation.getAccuracy();//获取精度信息
//                    mAddress = aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    mCountry = aMapLocation.getCountry();//国家信息
                    mProvince = aMapLocation.getProvince();//省信息
                    mCity = aMapLocation.getCity();//城市信息
                    mDistrict = aMapLocation.getDistrict();//城区信息
                    mStreet = aMapLocation.getStreet();//街道信息
                    mStreetNum = aMapLocation.getStreetNum();//街道门牌号信息
                    mCityCode = aMapLocation.getCityCode();//城市编码
                    mAdCode = aMapLocation.getAdCode();//地区编码
                    mAoiName = aMapLocation.getAoiName();//获取当前定位点的AOI信息
                    mBuildingId = aMapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                    mFloor = aMapLocation.getFloor();//获取当前室内定位的楼层
                    mGpsAccuracyStatus = aMapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
                    //获取定位时间
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(aMapLocation.getTime());
                    mTime = df.format(date);
                    if (mAddress != null) {
                        mTvHomeLocation.setText(mAddress);
                    } else {
                        mTvHomeLocation.setText(aMapLocation.getAddress());
                    }

                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };
    private static final int RC_LOCATION = 1;
    private String[] params;
    private CustomDialog customDialog;
    private ShareDialog shareDialog;
    private View under_review;
    private Button btnConfirm;
    private AlertDialog underReviewDialog;
    private ShareAction mShareAction;
    private CustomShareListener mShareListener;
    private View btn_share_one;
    //    private View btn_share_two;
    private Window window;
    private Button btn_verified_update;
    private ImageView iv_code_one;
    private ImageView iv_close;
    private TextView iv_gotoshop;
    private TextView tv_share;
    private Button btn_go_to_the_mall;
    private TextView content;
    private Pending_Adapter pending_appointment_adapter;
    private int successposition;
    private long recommendedtime;
    private String OrderId;
    private int cancleposition;
    private EditText et_message;
    private Button negtive;
    private Button positive;
    private AlertDialog cancelDialog;
    private Button btn_cancel;
    private Button btn_submit_beyond;
    private TextView tv_remote_km;
    private ImageView iv_map1;
    private EditText et_order_beyond_km;
    private View popupWindow_view;
    private PopupWindow mPopupWindow;
    private ArrayList<Object> permissions;
    private String FilePath;
    private List<Uri> mSelected;
    private Uri uri;
    private String OrderID;
    private HashMap<Integer, File> files_map_remote = new HashMap<>();//申请远程费图片
    private View customdialog_home_view;
    private AlertDialog customdialog_home_dialog;
    private TextView title;
    private TextView message;
    private String BeyondMoney;
    private String Distance;
    private Window window1;
    private WindowManager.LayoutParams lp;


    public Home_Fragment() {
        // Required empty public constructor
    }

    public static Home_Fragment newInstance(String param1) {
        Home_Fragment fragment = new Home_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContentText = getArguments().getString(ARG_SHOW_TEXT);
        }
    }


    @Override
    protected void initView() {
//        Typeface font = Typeface.createFromAsset(mActivity.getAssets(), "iconfont.ttf");
//        mTvTitle.setTypeface(font);
//        mTvTitle.setText(getResources().getString(R.string.tab_code));
//        mTvTitle.setTextColor(Color.WHITE);
        dialog = new ZLoadingDialog(mActivity);
        vibrator = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
        mPresenter.GetUserInfoList(userID, "1");
        mPresenter.GetListCategoryContentByCategoryID("7", "1", "999");

//        RequestOptions options = new RequestOptions()
//                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
//        Glide.with(mActivity).load(R.drawable.dongheiban).apply(options).into(mIvAds);
        AnimationDrawable anim = null;

        Object ob = mIvAds.getBackground();

        anim = (AnimationDrawable) ob;

        anim.stop();

        anim.start();
    }

    @Override
    protected void setListener() {
        //实名认证
        mTvCertification.setOnClickListener(this);
        //实名认证图片
        mImgCertification.setOnClickListener(this);
        mImg_un_certification.setOnClickListener(this);

        //二维码
        mImgHomeQrCode.setOnClickListener(this);
        mTvHomeLocation.setOnClickListener(this);

        //点击刷新
        mTvHomeRefresh.setOnClickListener(this);
        mimg_home_refresh.setOnClickListener(this);
        //客服
        mImg_home_customer_service.setOnClickListener(this);

        mImgHomeHead.setOnClickListener(this);
        mLlMoney.setOnClickListener(this);
        mLlFinsh.setOnClickListener(this);
        mTvName.setOnClickListener(this);
        mIvAds.setOnClickListener(this);

        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex = 1;

                refreshlayout.resetNoMoreData();

                mPresenter.GetUserInfoList(userID, "1");//根据 手机号码获取用户详细信息
                mPresenter.GetListCategoryContentByCategoryID("7", "1", "999");
                animator = ObjectAnimator.ofFloat(mimg_home_refresh, "rotation", 0f, 360f);
                animator.setDuration(2000);
                animator.start();


                animator_order = ObjectAnimator.ofFloat(mImgNewOrder, "rotation", 0f, -30f, 30f, 0f);
                animator_order.setDuration(1000);
                animator_order.start();

//                EventBus.getDefault().post("GetUserInfoList");
            }
        });


        //没满屏时禁止上拉
        mRefreshLayout.setEnableLoadmoreWhenContentNotFull(false);

        //上拉加载更多
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {

            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageIndex++; //页数加1
                Log.d("当前的单数", String.valueOf(list.size()));
                if (userInfo != null) {
                    if (userInfo.getParentUserID() == null || "".equals(userInfo.getParentUserID())) {
                        mPresenter.WorkerGetOrderList(userID, "0", Integer.toString(pageIndex), "10");
                    } else {
                        mPresenter.WorkerGetOrderList(userID, "1", Integer.toString(pageIndex), "5");
                    }
                }
            }
        });


    }


    /*获取订单列表新接口*/
    @Override
    public void WorkerGetOrderList(BaseResult<WorkOrder> baseResult) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult != null) {
                    if (userInfo.getParentUserID() == null || "".equals(userInfo.getParentUserID())) {
                        if (baseResult.getData().getData() == null) {
                            // Toast.makeText(getActivity(),"咱无新工单",Toast.LENGTH_SHORT).show();
                            Log.d("===>", "暂无新工单");
                            if (pageIndex == 1) {
                                list.clear();
//                                grabsheetAdapter.notifyDataSetChanged();
                            } else {
                                mRefreshLayout.finishLoadmoreWithNoMoreData();
                            }

                        } else {
                            if (pageIndex == 1) {
                                list.clear();
                                grabsheetAdapter.notifyDataSetChanged();
                                workOrder = baseResult.getData();
                                list.addAll(workOrder.getData());
                                grabsheetAdapter.setNewData(list);
                            } else {
                                workOrder = baseResult.getData();
                                list.addAll(workOrder.getData());
                                grabsheetAdapter.setNewData(list);
                                mRefreshLayout.finishLoadmore();
                            }

                        }

                    } else {
                        if (baseResult.getData().getData() == null) {
                            if (pageIndex == 1) {
                                Pendinglist.clear();
                            } else {
                                mRefreshLayout.finishLoadmoreWithNoMoreData();
                            }
                            cancleLoading();
                        } else {
                            if (pageIndex == 1) {
                                Pendinglist.clear();
                                pending_appointment_adapter.notifyDataSetChanged();
                                workOrder = baseResult.getData();
                                Pendinglist.addAll(workOrder.getData());
                                pending_appointment_adapter.setNewData(Pendinglist);
                            } else {
                                workOrder = baseResult.getData();
                                Pendinglist.addAll(workOrder.getData());
                                pending_appointment_adapter.setNewData(Pendinglist);
                                mRefreshLayout.finishLoadmore();

                            }

                            cancleLoading();

                        }

                    }

                } else {
                    return;
                }

                break;
            case 401:
                ToastUtils.showShort(baseResult.getInfo());
                break;

//            case 406:
//                final CommonDialog_Home dialog = new CommonDialog_Home(getActivity());
//                dialog.setMessage("账号在别处登录是否重新登录")
//                        //.setImageResId(R.mipmap.ic_launcher)
//                        .setTitle("提示")
//                        .setSingle(true)
//                        /*.setNegtive("重设密码")*/
//                        .setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
//                            @Override
//                            public void onPositiveClick() {//重新登录
//
//                                startActivity(new Intent(getActivity(), Login_New_Activity.class));
//                                getActivity().finish();
//                            }
//
//                            @Override
//                            public void onNegtiveClick() {
//
//                            }
//
//                           /* @Override
//                            public void onNegtiveClick() {//取消
//                                startActivity(new Intent(mActivity, ForgetPasswordActivity.class));
//                                getActivity().finish();
//                                dialog.dismiss();
//                                // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
//                            }*/
//                        }).show();
//
//                break;

        }
    }

    private void showPending() {
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

                                            chooseTime(position, "请选择上门时间");
                                        } else {
                                            // 获取全部权限失败
//                                            Log.d("=====>", "权限获取失败");
                                            ToastUtils.showShort("权限获取失败");
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
                        break;
                    /*转派订单*/



                    /*取消订单*/
                    case R.id.tv_cancel_order:
                        OrderId = ((WorkOrder.DataBean) adapter.getData().get(position)).getOrderID();//获取工单号
                        View Cancelview = LayoutInflater.from(mActivity).inflate(R.layout.dialog_cancel, null);
                        et_message = Cancelview.findViewById(R.id.et_message);
                        negtive = Cancelview.findViewById(R.id.negtive);
                        positive = Cancelview.findViewById(R.id.positive);
                        TextView title = Cancelview.findViewById(R.id.title);
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
                                    mPresenter.UpdateSendOrderState(OrderId, "-1", message);
                                    cancleposition = position;
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

                    default:
                        break;

                }

            }
        });
    }

    private void showGrabsheet() {
        grabsheetAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.img_grabsheet:
                        files_map_remote.clear();
                        vibrator.vibrate(100);
                        if (userInfo.getIfAuth() != null) {
                            if (userInfo.getIfAuth().equals("1")) {
                                final WorkOrder.DataBean item = (WorkOrder.DataBean) adapter.getItem(position);
                                OrderID = item.getOrderID();
                                if ("true".equals(item.getDistanceTureOrFalse())) {
                                    Intent intent = new Intent(mActivity, ApplyFeeActivity.class);
                                    intent.putExtra("position", position);
                                    intent.putExtra("orderId", OrderID);
                                    startActivity(intent);
//                                    startActivity(new Intent(mActivity, ApplyFeeActivity.class));
//                                    under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_apply_beyond, null);
//                                    btn_cancel = under_review.findViewById(R.id.btn_cancel);
//                                    btn_submit_beyond = under_review.findViewById(R.id.btn_submit_beyond);
//                                    tv_remote_km = under_review.findViewById(R.id.tv_remote_km);
//                                    iv_map1 = under_review.findViewById(R.id.iv_map1);
//                                    et_order_beyond_km = under_review.findViewById(R.id.et_order_beyond_km);
////                                    tv_remote_km.setText((Double.parseDouble(item.getDistance()) - 30) + "km");
//
//                                    btn_cancel.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            underReviewDialog.dismiss();
//                                            mPresenter.UpdateSendOrderState(OrderID, "1", "");
//                                        }
//                                    });
//                                    btn_submit_beyond.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            if (files_map_remote.size() == 0) {
//                                                ToastUtils.showShort("请添加远程图片!");
//                                                return;
//                                            }
//                                            Distance = et_order_beyond_km.getText().toString();
//                                            if (!Distance.isEmpty()) {
//                                                BeyondMoney = Double.parseDouble(Distance) + "";
//                                                Distance = BeyondMoney;
//                                            } else {
////                                                BeyondMoney=(Double.parseDouble(item.getDistance())-20)+"";
////                                                Distance=(Double.parseDouble(item.getDistance())-20)+"";
//                                                ToastUtils.showShort("请输入超出公里数！");
//                                                return;
//                                            }
//                                            OrderByondImgPicUpload(files_map_remote);
//                                        }
//                                    });
//                                    iv_map1.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            showPopupWindow(1301, 1302);
//                                        }
//                                    });
//                                    underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review).create();
//                                    underReviewDialog.show();
                                } else {
                                    showLoading();
                                    mPresenter.UpdateSendOrderState(OrderID, "1", "");
                                }

                            } else if (userInfo.getIfAuth().equals("0")) {
                                under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_under_review, null);
                                btnConfirm = under_review.findViewById(R.id.btn_confirm);
                                btnConfirm.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        underReviewDialog.dismiss();
                                    }
                                });
                                underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review).create();
                                underReviewDialog.show();
                            } else if (userInfo.getIfAuth().equals("-1")) {
                                under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_audit_failure, null);
                                btnConfirm = under_review.findViewById(R.id.btn_confirm);
                                content = under_review.findViewById(R.id.tv_content);
                                content.setText(userInfo.getAuthMessage() + ",有疑问请咨询客服电话。");
                                btnConfirm.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        underReviewDialog.dismiss();
                                        startActivity(new Intent(mActivity, Verified_Activity.class));
                                    }
                                });
                                underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review).create();
                                underReviewDialog.show();
                            } else {
                                showVerifiedDialog();
                            }
                        } else {
                            showVerifiedDialog();
                        }
                        break;
                    case R.id.tv_quote:
                        if (userInfo.getIfAuth() != null) {
                            if (userInfo.getIfAuth().equals("1")) {
                                final WorkOrder.DataBean item = (WorkOrder.DataBean) adapter.getItem(position);
                                OrderID = item.getOrderID();
                                Intent intent = new Intent(mActivity, QuoteActivity.class);
                                intent.putExtra("orderId", OrderID);
                                startActivity(intent);
                            } else if (userInfo.getIfAuth().equals("0")) {
                                under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_under_review, null);
                                btnConfirm = under_review.findViewById(R.id.btn_confirm);
                                btnConfirm.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        underReviewDialog.dismiss();
                                    }
                                });
                                underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review).create();
                                underReviewDialog.show();
                            } else if (userInfo.getIfAuth().equals("-1")) {
                                under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_audit_failure, null);
                                btnConfirm = under_review.findViewById(R.id.btn_confirm);
                                content = under_review.findViewById(R.id.tv_content);
                                content.setText(userInfo.getAuthMessage() + ",有疑问请咨询客服电话。");
                                btnConfirm.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        underReviewDialog.dismiss();
                                        startActivity(new Intent(mActivity, Verified_Activity.class));
                                    }
                                });
                                underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review).create();
                                underReviewDialog.show();
                            } else {
                                showVerifiedDialog();
                            }
                        } else {
                            showVerifiedDialog();
                        }
                        break;
                }

            }
        });
    }

    @Override
    public void UpdateSendOrderState(BaseResult<Data> baseResult) {
        Data data = baseResult.getData();
        switch (baseResult.getStatusCode()) {
            case 200://200
                if (data.isItem1()) {//接单成功

                    if (userInfo.getParentUserID() == null || "".equals(userInfo.getParentUserID())) {
                        grabsheetAdapter.remove(grabposition);
                        Toast.makeText(getActivity(), "接单成功", LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), Order_Receiving_Activity.class);
                        intent.putExtra("intent", "pending_appointment");
                        startActivity(intent);
                        cancleLoading();
                    } else {
                        pending_appointment_adapter.remove(cancleposition);
                    }


                } else {
                    Toast.makeText(getActivity(), (CharSequence) data.getItem2(), LENGTH_SHORT).show();
                    cancleLoading();

                }

                break;

            default:
                cancleLoading();
                break;
        }
    }


    @Override
    public void GetUserInfo(BaseResult<String> baseResult) {

    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                userInfo = baseResult.getData().getData().get(0);
                mTvFinish.setText(userInfo.getServiceTotalOrderNum());
                mTvComplaint.setText(userInfo.getServiceComplaintNum());
                if (userInfo.getParentUserID() == null || "".equals(userInfo.getParentUserID())) {
                    mPresenter.WorkerGetOrderList(userID, "0", Integer.toString(pageIndex), "10");
                } else {
                    mPresenter.WorkerGetOrderList(userID, "1", Integer.toString(pageIndex), "5");
                }
                /*设置实名认证状态*/
                if (userInfo.getIfAuth() == null) {
                    mTvCertification.setText("未实名认证");
                    mImg_un_certification.setVisibility(View.VISIBLE);
                    mImgCertification.setVisibility(View.INVISIBLE);
                    mTvCertification.setTextColor(Color.rgb(255, 0, 0));

                } else if (userInfo.getIfAuth().equals("0")) {
                    mTvCertification.setText("审核中");
                    mTvCertification.setTextColor(Color.RED);
                    mImg_un_certification.setVisibility(View.VISIBLE);
                    mImgCertification.setVisibility(View.INVISIBLE);

                } else if (userInfo.getIfAuth().equals("-1")) {
                    mTvCertification.setText("审核不通过");
                    mTvCertification.setTextColor(Color.RED);
                    mImg_un_certification.setVisibility(View.VISIBLE);
                    mImgCertification.setVisibility(View.INVISIBLE);

                } else if (userInfo.getIfAuth().equals("1")) {
                    mTvCertification.setText("已实名认证");
                    mImg_un_certification.setVisibility(View.INVISIBLE);
                    mImgCertification.setVisibility(View.VISIBLE);

                }

                /*设置金额*/
                String TotalMoney = String.valueOf(userInfo.getTotalMoney());
                String FrozenMoney = String.valueOf(userInfo.getFrozenMoney());//冻结金额
                // String can_withdraw = String.valueOf(userInfo.getTotalMoney() - userInfo.getFrozenMoney());//可提现余额=总金额-冻结金额
                String format = String.format("%.2f", userInfo.getTotalMoney() - userInfo.getFrozenMoney());
                mTvHomeAvailableBalance.setText("可提现余额: " + format + "元");
                mTvHomeOutstandingAmount.setText("未完结金额: " + FrozenMoney + "元");
                mTvHomeGivingMoney.setText("赠 送 金 额: 暂无");
                if (userInfo.getUserID().equals(userInfo.getNickName())) {
                    mTvName.setText("未设置昵称");
                } else {
                    mTvName.setText(userInfo.getNickName());
                }
                String xigua = String.format("%.2f", userInfo.getCon());
                mTvMoney.setText(xigua);

                /*设置头像*/
                if (userInfo.getAvator() != null) {//显示默认头像
                    RequestOptions myOptions = new RequestOptions()
                            .bitmapTransform(new CircleCrop())
                            .transform(new GlideCircleWithBorder_Home(this, 1, Color.parseColor("#DCDCDC")))
                            .error(R.drawable.icon);

                    Glide.with(mActivity)
                            .load(Config.HEAD_URL + userInfo.getAvator())
                            .apply(myOptions)
                            .into(mImgHomeHead);

                    if (userInfo.getParentUserID() == null || "".equals(userInfo.getParentUserID())) {
                        mRecyclerviewOrderReceiving.setLayoutManager(new WrapContentLinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
                        grabsheetAdapter = new GrabsheetAdapter(R.layout.item_grabsheet, list);
                        grabsheetAdapter.setEmptyView(getEmptyView());
                        mRecyclerviewOrderReceiving.setAdapter(grabsheetAdapter);
                        showGrabsheet();
                    } else {
                        mRecyclerviewOrderReceiving.setLayoutManager(new WrapContentLinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
                        pending_appointment_adapter = new Pending_Adapter(R.layout.item_pending_appointment, Pendinglist, userInfo);
                        pending_appointment_adapter.setEmptyView(getEmptyView());
                        mRecyclerviewOrderReceiving.setAdapter(pending_appointment_adapter);
                        showPending();
                    }


                } else {
                    return;
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
    public void AddOrderfailureReason(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                break;
            default:
                break;

        }
    }

    public void showVerifiedDialog() {
        customDialog = new CustomDialog(getContext());
        customDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        customDialog.setTitle("实名认证");
        customDialog.show();
        customDialog.setYesOnclickListener("确定", new CustomDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                //Toast.makeText(getContext(), "点击了--去认证--按钮", Toast.LENGTH_LONG).show();
                customDialog.dismiss();
                startActivity(new Intent(mActivity, Verified_Activity.class));
            }
        });

        customDialog.setNoOnclickListener("取消", new CustomDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                //Toast.makeText(getContext(), "点击了--再想想--按钮", Toast.LENGTH_LONG).show();
                customDialog.dismiss();
            }
        });

        customDialog.setNoOnclickListener("取消", new CustomDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                // Toast.makeText(getContext(), "点击了--关闭-按钮", Toast.LENGTH_LONG).show();
                customDialog.dismiss();
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_certification:
                if (userInfo.getIfAuth() != null) {
                    if (userInfo.getIfAuth().equals("1")) {
                        under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_successful_review, null);
                        btnConfirm = under_review.findViewById(R.id.btn_confirm);
                        btn_verified_update = under_review.findViewById(R.id.btn_verified_update);
                        btnConfirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                underReviewDialog.dismiss();
                            }
                        });
                        btn_verified_update.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                underReviewDialog.dismiss();
                                startActivity(new Intent(mActivity, VerifiedUpdateActivity.class));
                            }
                        });
                        underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review).create();
                        underReviewDialog.show();
                    } else if (userInfo.getIfAuth().equals("0")) {
                        under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_under_review, null);
                        btnConfirm = under_review.findViewById(R.id.btn_confirm);
                        btnConfirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                underReviewDialog.dismiss();
                            }
                        });
                        underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review).create();
                        underReviewDialog.show();
                    } else if (userInfo.getIfAuth().equals("-1")) {
                        under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_audit_failure, null);
                        content = under_review.findViewById(R.id.tv_content);
                        content.setText(userInfo.getAuthMessage() + ",有疑问请咨询客服电话。");
                        btnConfirm = under_review.findViewById(R.id.btn_confirm);
                        btnConfirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                underReviewDialog.dismiss();
                                startActivity(new Intent(mActivity, Verified_Activity.class));
                            }
                        });
                        underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review).create();
                        underReviewDialog.show();
                    } else {
                        showVerifiedDialog();
                    }
                } else {
                    showVerifiedDialog();
                }
                break;

            case R.id.img_home_qr_code:
                under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_share, null);
                btn_share_one = under_review.findViewById(R.id.btn_share_one);
                iv_code_one = under_review.findViewById(R.id.iv_code_one);
                btn_go_to_the_mall = under_review.findViewById(R.id.btn_go_to_the_mall);
                Bitmap bitmap = ZXingUtils.createQRImage("http://admin.xigyu.com/NewSign?phone=" + userID + "&type=8", 500, 500, BitmapFactory.decodeResource(getResources(), R.drawable.shop));
                iv_code_one.setImageBitmap(bitmap);
                btn_share_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        underReviewDialog.dismiss();
                        mShareAction.open();
                    }
                });

                btn_go_to_the_mall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openShopApp("com.zhenghaikj.shop");
                        underReviewDialog.dismiss();
                    }
                });
//                btn_share_two.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        underReviewDialog.dismiss();
//                        mShareAction.open();
//                    }
//                });
                underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review)
                        .create();
                underReviewDialog.show();
                window = underReviewDialog.getWindow();
//                window.setContentView(under_review);
                WindowManager.LayoutParams lp = window.getAttributes();
//                lp.alpha = 0.5f;
                // 也可按屏幕宽高比例进行设置宽高
//                Display display = mActivity.getWindowManager().getDefaultDisplay();
//                lp.width = (int) (display.getWidth() * 0.6);
//                lp.height = under_review.getHeight();
//                lp.width = 300;
//                lp.height = 400;

                window.setAttributes(lp);
//                window.setDimAmount(0.1f);
                window.setBackgroundDrawable(new ColorDrawable());

                break;
            case R.id.tv_home_refresh:
            case R.id.img_home_refresh:
                mRefreshLayout.autoRefresh();
                break;
            case R.id.tv_home_location:
                startActivityForResult(new Intent(mActivity, MainActivity.class), 100);
                break;
            case R.id.img_home_customer_service://客服
                final CommonDialog_Home dialog = new CommonDialog_Home(getActivity());
                dialog.setMessage("是否拨打电话给客服")
                        //.setImageResId(R.mipmap.ic_launcher)
                        .setTitle("提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {//拨打电话
                        dialog.dismiss();
                        call("tel:" + "4006262365");
                    }

                    @Override
                    public void onNegtiveClick() {//取消
                        dialog.dismiss();
                        // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                    }
                }).show();

                break;
            case R.id.img_home_head:
                startActivity(new Intent(mActivity, Personal_Information_Activity.class));
                break;
            case R.id.ll_money:
                startActivity(new Intent(mActivity, Wallet_Activity.class));
                break;
            case R.id.ll_finsh:
                Bundle bundle = new Bundle();
                bundle.putString("intent", "completed");
                Intent intent = new Intent(mActivity, Order_Receiving_Activity.class);
                intent.putExtras(bundle);
                ActivityUtils.startActivity(intent);
                mActivity.overridePendingTransition(R.anim.anim_no, R.anim.anim_no);
                break;
            case R.id.tv_name:
                startActivity(new Intent(mActivity, Personal_Information_Activity.class));
                break;
            case R.id.iv_ads:
                showAd();
                break;
            default:
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }

    public static class CustomShareListener implements UMShareListener {
        private Context mContext;

        public CustomShareListener(Context context) {
            mContext = context;
        }

        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {

            if (platform.name().equals("WEIXIN_FAVORITE")) {
//                Toast.makeText(mContext, platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
            } else {
                if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                        && platform != SHARE_MEDIA.EMAIL
                        && platform != SHARE_MEDIA.FLICKR
                        && platform != SHARE_MEDIA.FOURSQUARE
                        && platform != SHARE_MEDIA.TUMBLR
                        && platform != SHARE_MEDIA.POCKET
                        && platform != SHARE_MEDIA.PINTEREST

                        && platform != SHARE_MEDIA.INSTAGRAM
                        && platform != SHARE_MEDIA.GOOGLEPLUS
                        && platform != SHARE_MEDIA.YNOTE
                        && platform != SHARE_MEDIA.EVERNOTE) {
//                    Toast.makeText(mContext, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
                }

            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                    && platform != SHARE_MEDIA.EMAIL
                    && platform != SHARE_MEDIA.FLICKR
                    && platform != SHARE_MEDIA.FOURSQUARE
                    && platform != SHARE_MEDIA.TUMBLR
                    && platform != SHARE_MEDIA.POCKET
                    && platform != SHARE_MEDIA.PINTEREST

                    && platform != SHARE_MEDIA.INSTAGRAM
                    && platform != SHARE_MEDIA.GOOGLEPLUS
                    && platform != SHARE_MEDIA.YNOTE
                    && platform != SHARE_MEDIA.EVERNOTE) {
//                Toast.makeText(mContext, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();

            }

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {

//            Toast.makeText(mContext, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

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

    @AfterPermissionGranted(RC_LOCATION)
    private void methodRequiresPermission() {
        params = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
        if (EasyPermissions.hasPermissions(mActivity, params)) {
            // Already have permission, do the thing
            // ...
            Location();

        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "需要获取定位权限",
                    RC_LOCATION, params);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
        }
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home;
    }

    public void showAd() {
        under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_tixing, null);
        TextView tv_know = under_review.findViewById(R.id.tv_know);
        ImageView iv_close = under_review.findViewById(R.id.iv_close);
        LinearLayout ll_ad = under_review.findViewById(R.id.ll_ad);
        ll_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                underReviewDialog.dismiss();
            }
        });
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                underReviewDialog.dismiss();
            }
        });

        tv_know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                underReviewDialog.dismiss();
            }
        });

        underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review)
                .create();
        underReviewDialog.show();
        window = underReviewDialog.getWindow();
//                window.setContentView(under_review);
        WindowManager.LayoutParams lp = window.getAttributes();
//                lp.alpha = 0.5f;
        // 也可按屏幕宽高比例进行设置宽高
//        Display display = mActivity.getWindowManager().getDefaultDisplay();
//        lp.width = (int) (display.getWidth() * 1);
//        lp.height = under_review.getHeight();
//        lp.width = 300;
//        lp.height = 400;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
//                window.setDimAmount(0.1f);
        window.setBackgroundDrawable(new ColorDrawable());

//        under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_ad, null);
//        iv_close = under_review.findViewById(R.id.iv_close);
//        tv_share = under_review.findViewById(R.id.tv_share);
//        iv_gotoshop = under_review.findViewById(R.id.tv_go);
//
//        iv_gotoshop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openShopApp("com.zhenghaikj.shop");
//                underReviewDialog.dismiss();
//            }
//        });
//
//
//        iv_close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                underReviewDialog.dismiss();
//            }
//        });
//        tv_share.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                underReviewDialog.dismiss();
//                mShareAction.open();
//            }
//        });
//        underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review)
//                .create();
//        underReviewDialog.show();
//        window = underReviewDialog.getWindow();
////                window.setContentView(under_review);
//        WindowManager.LayoutParams lp = window.getAttributes();
////                lp.alpha = 0.5f;
//        // 也可按屏幕宽高比例进行设置宽高
////                Display display = mActivity.getWindowManager().getDefaultDisplay();
////                lp.width = (int) (display.getWidth() * 0.6);
////                lp.height = under_review.getHeight();
////                lp.width = 300;
////                lp.height = 400;
//        window.setAttributes(lp);
////                window.setDimAmount(0.1f);
//        window.setBackgroundDrawable(new ColorDrawable());
    }

    @Override
    protected void initData() {

        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(mActivity).setShareConfig(config);
        mShareListener = new CustomShareListener(mActivity);
        /*增加自定义按钮的分享面板*/
        mShareAction = new ShareAction(mActivity).setDisplayList(
                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.MORE)
                .addButton("复制文本", "复制文本", "umeng_socialize_copy", "umeng_socialize_copy")
                .addButton("复制链接", "复制链接", "umeng_socialize_copyurl", "umeng_socialize_copyurl")
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, final SHARE_MEDIA share_media) {
                        if (snsPlatform.mShowWord.equals("复制文本")) {
                            Toast.makeText(mActivity, "已复制", Toast.LENGTH_LONG).show();
                        } else if (snsPlatform.mShowWord.equals("复制链接")) {
                            Toast.makeText(mActivity, "已复制", Toast.LENGTH_LONG).show();

                        } else {
                            RxPermissions rxPermissions = new RxPermissions(mActivity);
                            rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                                    .subscribe(new Consumer<Boolean>() {
                                        @Override
                                        public void accept(Boolean aBoolean) throws Exception {
                                            if (aBoolean) {
                                                // 获取全部权限成功

                                                UMWeb web = new UMWeb("http://admin.xigyu.com/NewSign?phone=" + userID + "&type=8");
                                                web.setTitle("西瓜鱼");
                                                web.setDescription("注册送西瓜币了！！！！！");
                                                web.setThumb(new UMImage(mActivity, R.drawable.shop));
                                                new ShareAction(mActivity).withMedia(web)
                                                        .setPlatform(share_media)
                                                        .setCallback(mShareListener)
                                                        .share();
                                            } else {
                                                // 获取全部权限失败
                                                ToastUtils.showShort("权限获取失败");
                                            }
                                        }
                                    });

                        }
                    }
                });

        final CommonDialog_Home dialog = new CommonDialog_Home(getActivity()); //弹出框
        userID = spUtils.getString("userName"); //获取用户id
        mPresenter.GetUserInfoList(userID, "1");//根据 手机号码获取用户详细信息


        methodRequiresPermission();


        if (userInfo.getParentUserID() == null || "".equals(userInfo.getParentUserID())) {
            mPresenter.WorkerGetOrderList(userID, "0", Integer.toString(pageIndex), "10");
        } else {
            mPresenter.WorkerGetOrderList(userID, "1", Integer.toString(pageIndex), "5");
        }
    }


    /**
     * 选择上门时间
     */
    public void chooseTime(final int position, final String title) {
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
                OrderId = workOrder.getData().get(position).getOrderID();
                mPresenter.UpdateSendOrderUpdateTime(OrderId, time, time);


                Intent intent = new Intent(mActivity, WorkOrderDetailsActivity2.class);
                intent.putExtra("OrderID", OrderId);
                intent.putExtra("time", time);
                startActivity(intent);
                successposition = position;

                mPresenter.AddOrderSuccess(OrderId, "1", "预约成功");
            }
        }, format1, "2022-1-1 24:00");

        timeSelector.setTitle(title);
        timeSelector.setNextBtTip("确定");
        timeSelector.show();
    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Location();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            //这个方法有个前提是，用户点击了“不再询问”后，才判断权限没有被获取到
            new AppSettingsDialog.Builder(this)
                    .setRationale("没有该权限，此应用程序可能无法正常工作。打开应用设置界面以修改应用权限")
                    .setTitle("必需权限")
                    .build()
                    .show();
        } else if (!EasyPermissions.hasPermissions(mActivity, params)) {
            //这里响应的是除了AppSettingsDialog这个弹出框，剩下的两个弹出框被拒绝或者取消的效果
//            finish();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String message) {
        if ("GetUserInfoList".equals(message)) {
            mPresenter.GetUserInfoList(userID, "1");
        } else if ("0".equals(message)) {
            if (userInfo.getParentUserID() == null || "".equals(userInfo.getParentUserID())) {
                mPresenter.WorkerGetOrderList(userID, "0", Integer.toString(pageIndex), "10");
            } else {
                mPresenter.WorkerGetOrderList(userID, "1", Integer.toString(pageIndex), "5");
            }
        } else if ("pending_appointment".equals(message)) {
            grabsheetAdapter.remove(grabposition);
        }
    }


    public void showLoading() {
        dialog.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setHintText("接单中请稍后...")
                .setHintTextSize(14) // 设置字体大小 dp
                .setHintTextColor(Color.BLACK)  // 设置字体颜色
                .setDurationTime(1) // 设置动画时间百分比 - 0.5倍
                .setCanceledOnTouchOutside(false)//点击外部无法取消
                .show();
    }

    public void cancleLoading() {
        dialog.dismiss();

    }

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


    private void openShopApp(String packageName) {

        if (isInstalled(packageName)) {
            PackageInfo pi = null;
            try {
                pi = getActivity().getPackageManager().getPackageInfo(packageName, 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
            resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            resolveIntent.setPackage(pi.packageName);

            List<ResolveInfo> apps = getActivity().getPackageManager().queryIntentActivities(resolveIntent, 0);

            ResolveInfo ri = apps.iterator().next();
            if (ri != null) {
                packageName = ri.activityInfo.packageName;
                String className = ri.activityInfo.name;

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);

                ComponentName cn = new ComponentName(packageName, className);
                intent.setComponent(cn);
                startActivity(intent);
            }


        } else {
            final CommonDialog_Home dialog = new CommonDialog_Home(getActivity());
            dialog.setMessage("未安装商城app，是否请前往下载安装")
                    //.setImageResId(R.mipmap.ic_launcher)
                    .setTitle("提示")
                    .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                @Override
                public void onPositiveClick() {//拨打电话
                    dialog.dismiss();
                    openBrowser(mActivity, "https://img.xigyu.com/Files/西瓜鱼商城.apk");
                }

                @Override
                public void onNegtiveClick() {//取消
                    dialog.dismiss();
                }
            }).show();
        }


    }

    /**
     * 调用第三方浏览器打开
     *
     * @param context
     * @param url     要浏览的资源地址
     */
    public static void openBrowser(Context context, String url) {
        final Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
        // 注意此处的判断intent.resolveActivity()可以返回显示该Intent的Activity对应的组件名
        // 官方解释 : Name of the component implementing an activity that can display the intent
//        if (intent.resolveActivity(context.getPackageManager()) != null) {
//            final ComponentName componentName = intent.resolveActivity(context.getPackageManager());
//            // 打印Log   ComponentName到底是什么
//            context.startActivity(Intent.createChooser(intent, "请选择浏览器"));
//        } else {
//            Toast.makeText(context.getApplicationContext(), "请下载浏览器", Toast.LENGTH_SHORT).show();
//        }
    }

    /**
     * 弹出Popupwindow
     */
    public void showPopupWindow(final int code1, final int code2) {
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.camera_layout, null);
        Button camera_btn = popupWindow_view.findViewById(R.id.camera_btn);
        Button photo_btn = popupWindow_view.findViewById(R.id.photo_btn);
        Button cancel_btn = popupWindow_view.findViewById(R.id.cancel_btn);
        camera_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (requestPermissions()) {
                    Intent intent = new Intent();
                    // 指定开启系统相机的Action
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    String f = System.currentTimeMillis() + ".jpg";
                    String fileDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/xgy";
                    FilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/xgy/" + f;
                    File dirfile = new File(fileDir);
                    if (!dirfile.exists()) {
                        dirfile.mkdirs();
                    }
                    File file = new File(FilePath);
                    Uri fileUri;
                    if (Build.VERSION.SDK_INT >= 24) {
                        fileUri = FileProvider.getUriForFile(mActivity, "com.ying.administrator.masterappdemo.fileProvider", file);
                    } else {
                        fileUri = Uri.fromFile(file);
                    }

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(intent, code1);
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10001);
                }
                mPopupWindow.dismiss();
            }
        });
        photo_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (requestPermissions()) {
//                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//                    i.addCategory(Intent.CATEGORY_OPENABLE);
//                    i.setType("image/*");
//                    startActivityForResult(Intent.createChooser(i, "test"), code2);
                    Matisse.from(mActivity)
                            .choose(MimeType.ofImage())
                            .countable(true)
                            .maxSelectable(1)
//                            .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
//                            .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                            .thumbnailScale(0.85f)
                            .imageEngine(new Glide4Engine())
                            .forResult(code2);
                    mPopupWindow.dismiss();
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10002);
                }

            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
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
                lp = window1.getAttributes();
                lp.alpha = 1f;
                window1.setAttributes(lp);
            }
        });
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
//            popupWindow.showAsDropDown(tv, 0, 10);
            if (code1 == 1301 || code1 == 1401) {
                View view = under_review.getRootView();
                LogUtils.eTag("view", view.getLayoutParams());
                mPopupWindow.showAtLocation(under_review.getRootView(), Gravity.BOTTOM, 0, 0);
                window1 = underReviewDialog.getWindow();
                lp = window1.getAttributes();
                lp.alpha = 0.5f;
                window1.setAttributes(lp);
            } else {
                mPopupWindow.showAtLocation(popupWindow_view, Gravity.BOTTOM, 0, 0);
            }

//            MyUtils.backgroundAlpha(mActivity,0.5f);
        }
        MyUtils.setWindowAlpa(mActivity, true);
    }

    //请求权限
    private boolean requestPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            permissions = new ArrayList<>();
            if (mActivity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (mActivity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (mActivity.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.CAMERA);
            }
            if (permissions.size() == 0) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    //返回图片处理
    @SuppressLint("NewApi")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File file = null;
        switch (requestCode) {
            case 100:
                if (data != null) {
                    mAddress = data.getStringExtra("address");
                    if (mAddress != null) {
                        mTvHomeLocation.setText(mAddress);
                    }
                }
                break;
            case 10001:
            case 10002:
                if (requestCode == 1001) {
                    pending_appointment_adapter.remove(successposition);
                }
                break;

            //拍照
            case 1301://远程费照片
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(iv_map1);
                    file = new File(FilePath);
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(mActivity).compressToFile(file);
                    files_map_remote.put(0, newFile);
                }

                break;
            //相册
            case 1302://远程费照片
                if (data != null) {
                    mSelected = Matisse.obtainResult(data);
                    if (mSelected.size() == 1) {
                        uri = mSelected.get(0);
                    }
//                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_map1);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(mActivity).compressToFile(file);
                    files_map_remote.put(0, newFile);
                }
                break;

        }

    }

    /**
     * 添加远程图片
     *
     * @param map
     */
    public void OrderByondImgPicUpload(HashMap<Integer, File> map) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("img", map.get(0).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(0)));
//        builder.addFormDataPart("img", map.get(1).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(1)));
        builder.addFormDataPart("OrderID", OrderID);
        MultipartBody requestBody = builder.build();
        mPresenter.OrderByondImgPicUpload(requestBody);
    }

    @Override
    public void OrderByondImgPicUpload(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    mPresenter.ApplyBeyondMoney(OrderID, BeyondMoney, Distance);
                } else {
                    ToastUtils.showShort("远程费图片上传失败");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void ApplyBeyondMoney(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    ToastUtils.showShort("提交成功");
                    underReviewDialog.dismiss();
//                    showLoading();
//                    mPresenter.UpdateSendOrderState(OrderID, "9","");
                    grabsheetAdapter.remove(grabposition);
//                    Toast.makeText(getActivity(), "接单成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), Order_Receiving_Activity.class);
                    intent.putExtra("intent", "confirmedFragement");
                    startActivity(intent);
                    cancleLoading();

                } else {
                    if ("支付错误,添加失败".equals(baseResult.getData().getItem2())) {
                        customdialog_home_view = LayoutInflater.from(mActivity).inflate(R.layout.customdialog_home, null);
                        customdialog_home_dialog = new AlertDialog.Builder(mActivity)
                                .setView(customdialog_home_view)
                                .create();
                        customdialog_home_dialog.show();
                        title = customdialog_home_view.findViewById(R.id.title);
                        message = customdialog_home_view.findViewById(R.id.message);
                        negtive = customdialog_home_view.findViewById(R.id.negtive);
                        positive = customdialog_home_view.findViewById(R.id.positive);
                        title.setText("提示");
                        message.setText("余额不足，是否充值？");
                        negtive.setText("否");
                        positive.setText("是");
                        negtive.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customdialog_home_dialog.dismiss();
                            }
                        });
                        positive.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(mActivity, RechargeActivity.class));
                                customdialog_home_dialog.dismiss();
                            }
                        });
                    } else {
                        ToastUtils.showShort((String) baseResult.getData().getItem2());
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void GetListCategoryContentByCategoryID(BaseResult<Article> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:

                datalist = baseResult.getData().getData();
                mScrolltv.removeAllViews();
                mScrolltv.initView(R.layout.item_switchview, new SwitchView.ViewBuilder() {
                    @Override
                    public void initView(View view) {
                        final TextView tv_name = (TextView) view.findViewById(R.id.tv_content);
                        final TextView tv_url = (TextView) view.findViewById(R.id.tv_url);
                        tv_name.setText(datalist.get(i % datalist.size()).getTitle());
                        tv_url.setText(datalist.get(i % datalist.size()).getContent());
                        tv_name.setTag(i);

                        i++;
                        if (i == datalist.size()) {
                            i = 0;
                        }

                        tv_name.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String title = tv_name.getText().toString();
                                String content = tv_url.getText().toString();
                                Intent intent = new Intent(mActivity, WebActivity.class);
                                intent.putExtra("Url", content);
                                intent.putExtra("Title", title);
                                startActivity(intent);
                            }
                        });
                    }
                });
                break;
        }
    }
}
