package com.ying.administrator.masterappdemo.mvp.ui.fragment;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.searchdemo.MainActivity;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.AllWorkOrdersContract;
import com.ying.administrator.masterappdemo.mvp.model.AllWorkOrdersModel;
import com.ying.administrator.masterappdemo.mvp.presenter.AllWorkOrdersPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Order_Receiving_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Share_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Verified_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.GrabsheetAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseFragment;
import com.ying.administrator.masterappdemo.widget.CustomDialog;
import com.ying.administrator.masterappdemo.widget.ShareDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class Home_Fragment extends BaseFragment<AllWorkOrdersPresenter, AllWorkOrdersModel> implements AllWorkOrdersContract.View, View.OnClickListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";
    @BindView(R.id.img_home_qr_code)
    ImageView mImgHomeQrCode;
    @BindView(R.id.actionbar_layout)
    RelativeLayout mActionbarLayout;
    @BindView(R.id.img_home_head)
    ImageView mImgHomeHead;
    @BindView(R.id.img_home_withdraw)
    ImageView mImgHomeWithdraw;
    @BindView(R.id.tv_home_available_balance)
    TextView mTvHomeAvailableBalance;
    @BindView(R.id.img_home_unfinished)
    ImageView mImgHomeUnfinished;
    @BindView(R.id.tv_home_outstanding_amount)
    TextView mTvHomeOutstandingAmount;
    @BindView(R.id.img_home_free_gold)
    ImageView mImgHomeFreeGold;
    @BindView(R.id.tv_home_giving_money)
    TextView mTvHomeGivingMoney;
    @BindView(R.id.tv_certification)
    TextView mTvCertification;
    @BindView(R.id.img_certification)
    ImageView mImgCertification;
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
    @BindView(R.id.tv_home_empty)
    TextView mTvHomeEmpty;
    @BindView(R.id.ll_empty)
    LinearLayout mLlEmpty;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    Unbinder unbinder;

    // private WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;
    private String mContentText;
    private View mRootView;
    private String userID;//用户id
    private GrabsheetAdapter grabsheetAdapter;
    private WorkOrder workOrder;
    private List<WorkOrder.DataBean> list;
    private int pageIndex = 1;  //默认当前页数为1

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
    private ObjectAnimator animator; //刷新图片属性动画
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
                    if (mAddress!=null){
                        mTvHomeLocation.setText(mAddress);
                    }else{
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
    private static final int RC_LOCATION =1;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_home, container, false);
            Log.d("ying", "调用了onCreateView");

            // Log.d("userID",userID+"13");

        }
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, mRootView);
        initView();
        initListener();
    }

    public void initView() {
        methodRequiresPermission();

        list = new ArrayList<>();
        /*模拟数据*/
        mRecyclerviewOrderReceiving.setLayoutManager(new LinearLayoutManager(mActivity));
        grabsheetAdapter = new GrabsheetAdapter(R.layout.item_grabsheet, list);
        //?? grabsheetAdapter.setEmptyView(getEmptyView());
        mRecyclerviewOrderReceiving.setAdapter(grabsheetAdapter);

        mPresenter.GetOrderInfoList("1", Integer.toString(pageIndex), "100");
      /* if (list.isEmpty()){ //没有数据显示空
           contentLoadingEmpty();

       }else {
           mLlEmpty.setVisibility(View.INVISIBLE);

       }*/
        /*点击抢单按钮*/
        grabsheetAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.img_grabsheet:
                        SPUtils spUtils = SPUtils.getInstance("token");
                        userID = spUtils.getString("userName"); //获取用户id

                        mPresenter.AddGrabsheetapply(((WorkOrder.DataBean) adapter.getItem(position)).getOrderID(), userID);
                        //Log.d("WorkOrder",((WorkOrder.DataBean)adapter.getItem(position)).getOrderID());
                        grabsheetAdapter.remove(position);
                        Intent intent = new Intent(getActivity(), Order_Receiving_Activity.class);
                        intent.putExtra("intent", "pending_appointment");
                        startActivity(intent);
                        if (list.isEmpty()) {  //判断订单是否为空
                            contentLoadingEmpty();

                        }
                        break;
                }

            }
        });


    }

    public void initValidata() {

    }

    public void initListener() {

        //实名认证
        mTvCertification.setOnClickListener(this);
        //二维码
        mImgHomeQrCode.setOnClickListener(this);
        mTvHomeLocation.setOnClickListener(this);

        //点击刷新
        mTvHomeRefresh.setOnClickListener(this);
        mimg_home_refresh.setOnClickListener(this);

        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (!list.isEmpty()) { //当有数据的时候
                    mLlEmpty.setVisibility(View.INVISIBLE);//隐藏空的界面
                }
                pageIndex = 1;
                list.clear();
                mPresenter.GetOrderInfoList("1", Integer.toString(pageIndex), "100");
                grabsheetAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh();
                refreshlayout.setLoadmoreFinished(false);

                animator=ObjectAnimator.ofFloat(mimg_home_refresh,"rotation",0f,360f);
                animator.setDuration(2000);
                animator.start();
            }
        });

            //上拉加载更多
            mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {

                @Override
                public void onLoadmore(RefreshLayout refreshlayout) {
                    pageIndex++; //页数加1
                    Log.d("当前的单数", String.valueOf(list.size()));
                    mPresenter.GetOrderInfoList("1", Integer.toString(pageIndex), "4");
                    grabsheetAdapter.notifyDataSetChanged();
                    refreshlayout.finishLoadmore();
                }
            });






    }

    public void bindData() {

    }

    @Override
    public void GetOrderInfoList(BaseResult<WorkOrder> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                workOrder = baseResult.getData();
                list.addAll(workOrder.getData());
                grabsheetAdapter.setNewData(list); //?
                if (list.isEmpty()) {
                    contentLoadingEmpty();

                } else {
                    mLlEmpty.setVisibility(View.INVISIBLE);

                }
                if (pageIndex!=1&&workOrder.getData().size()==0){
                    mRefreshLayout.finishLoadmoreWithNoMoreData();
                }

                break;
            case 401:
                ToastUtils.showShort(baseResult.getInfo());
                break;
        }

    }

    @Override
    public void AddGrabsheetapply(BaseResult<Data> baseResult) {
        Data data = baseResult.getData();
        Log.d("asas", (String) data.getItem2());

        switch (baseResult.getStatusCode()) {
            case 200://200
                if (data.isItem1()) {//抢单成功
                    Toast.makeText(getActivity(), "抢单成功", Toast.LENGTH_SHORT).show();
                } else if (!data.isItem1()) {
                    Toast.makeText(getActivity(), "订单已经被抢", Toast.LENGTH_SHORT).show();
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
    public void contentLoadingComplete() {

    }

    @Override
    public void contentLoadingError() {

    }

    @Override
    public void contentLoadingEmpty() {
        Log.d("empty", "工单为空");
        mLlEmpty.setVisibility(View.VISIBLE);

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_certification:
                final CustomDialog customDialog = new CustomDialog(getContext());
                customDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
                customDialog.setTitle("实名认证");
                customDialog.show();

                customDialog.setYesOnclickListener("确定", new CustomDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        //Toast.makeText(getContext(), "点击了--去认证--按钮", Toast.LENGTH_LONG).show();
                        customDialog.dismiss();
                        startActivity(new Intent(getActivity(), Verified_Activity.class));
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
                break;
            case R.id.img_home_qr_code:
//                    startActivity(new Intent(getActivity(), Share_Activity.class));
                final ShareDialog shareDialog=new ShareDialog(getContext());
                shareDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
                shareDialog.show();
                break;
            case R.id.tv_home_refresh:
            case R.id.img_home_refresh:
                mRefreshLayout.autoRefresh();
                break;
            case R.id.tv_home_location:
                startActivityForResult(new Intent(mActivity,MainActivity.class),100);
                break;
            default:
                break;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(RC_LOCATION)
    private void methodRequiresPermission() {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
        if (EasyPermissions.hasPermissions(mActivity, perms)) {
            // Already have permission, do the thing
            // ...

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
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "需要获取定位权限",
                    RC_LOCATION, perms);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLocationClient!=null){
            mLocationClient.stopLocation();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 100:
                if (data!=null){
                    mAddress=data.getStringExtra("address");
                    if (mAddress!=null){
                        mTvHomeLocation.setText(mAddress);
                    }
                }
                break;
        }
    }
}
