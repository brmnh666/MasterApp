package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vondear.rxui.view.dialog.RxDialogScaleView;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.FAccessory;
import com.ying.administrator.masterappdemo.entity.FService;
import com.ying.administrator.masterappdemo.entity.GAccessory;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.entity.SAccessory;
import com.ying.administrator.masterappdemo.entity.SService;
import com.ying.administrator.masterappdemo.entity.Service;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.PendingOrderContract;
import com.ying.administrator.masterappdemo.mvp.model.PendingOrderModel;
import com.ying.administrator.masterappdemo.mvp.presenter.PendingOrderPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Ac_List_Adapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Add_Ac_Adapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Add_Service_Adapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.GServiceAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Pre_order_Add_Ac_Adapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Pre_order_Add_Service_Adapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.ReturnAccessoryAdapter;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.widget.ClearEditText;
import com.ying.administrator.masterappdemo.widget.HideSoftInputDialog;

import org.feezu.liuli.timeselector.TimeSelector;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class WorkOrderDetailsActivity extends BaseActivity<PendingOrderPresenter, PendingOrderModel> implements View.OnClickListener, PendingOrderContract.View {


    @BindView(R.id.img_actionbar_return)
    ImageView mImgActionbarReturn;
    @BindView(R.id.tv_actionbar_return)
    TextView mTvActionbarReturn;
    @BindView(R.id.ll_return)
    LinearLayout mLlReturn;
    @BindView(R.id.tv_actionbar_title)
    TextView mTvActionbarTitle;
    @BindView(R.id.img_actionbar_message)
    ImageView mImgActionbarMessage;
    @BindView(R.id.actionbar_layout)
    RelativeLayout mActionbarLayout;
    @BindView(R.id.tv_payment_method)
    TextView mTvPaymentMethod;
    @BindView(R.id.tv_order_time)
    TextView mTvOrderTime;
    @BindView(R.id.tv_work_order_number)
    TextView mTvWorkOrderNumber;
    @BindView(R.id.tv_cause_of_issue)
    TextView mTvCauseOfIssue;
    @BindView(R.id.tv_number)
    TextView mTvNumber;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.tv_product_type)
    TextView mTvProductType;
    @BindView(R.id.tv_service_address)
    TextView mTvServiceAddress;
    @BindView(R.id.rv_return_information)
    RecyclerView mRvReturnInformation;
    @BindView(R.id.tv_accessory_application)
    TextView mTvAccessoryApplication;
    @BindView(R.id.tv_service_application)
    TextView mTvServiceApplication;
    @BindView(R.id.rv_service)
    RecyclerView mRvService;
    @BindView(R.id.tv_accessory_apply_state)
    TextView mTvAccessoryApplyState;
    @BindView(R.id.ll_accessory)
    LinearLayout mLlAccessory;
    @BindView(R.id.tv_service_apply_state)
    TextView mTvServiceApplyState;
    @BindView(R.id.ll_service)
    LinearLayout mLlService;
    @BindView(R.id.tv_beyond_state)
    TextView mTvBeyondState;
    @BindView(R.id.tv_beyond_application)
    TextView mTvBeyondApplication;
    @BindView(R.id.tv_range)
    TextView mTvRange;
    @BindView(R.id.iv_range_one)
    ImageView mIvRangeOne;
    @BindView(R.id.iv_range_two)
    ImageView mIvRangeTwo;
    @BindView(R.id.ll_approve_beyond_money)
    LinearLayout mLlApproveBeyondMoney;
    @BindView(R.id.btn_complete_submit)
    Button mBtnCompleteSubmit;
    @BindView(R.id.btn_trial)
    Button mBtnTrial;
    @BindView(R.id.tv_status)
    TextView mTvStatus;
    @BindView(R.id.tv_accessory_information)
    TextView mTvAccessoryInformation;
    @BindView(R.id.tv_service_information)
    TextView mTvServiceInformation;
    @BindView(R.id.tv_remote_fee_information)
    TextView mTvRemoteFeeInformation;
    @BindView(R.id.iv_manufacturers)
    ImageView mIvManufacturers;
    @BindView(R.id.ll_manufacturers)
    LinearLayout mLlManufacturers;
    @BindView(R.id.iv_selfbuying)
    ImageView mIvSelfbuying;
    @BindView(R.id.ll_selfbuying)
    LinearLayout mLlSelfbuying;
    @BindView(R.id.iv_selfbuying_user)
    ImageView mIvSelfbuyingUser;
    @BindView(R.id.ll_selfbuying_user)
    LinearLayout mLlSelfbuyingUser;
    @BindView(R.id.recyclerView_add_accessories)
    RecyclerView mRecyclerViewAddAccessories;
    @BindView(R.id.tv_order_details_add_accessories)
    TextView mTvOrderDetailsAddAccessories;
    @BindView(R.id.tv_submit_add_accessories)
    TextView mTvSubmitAddAccessories;
    @BindView(R.id.ll_add_accessory)
    LinearLayout mLlAddAccessory;
    @BindView(R.id.tv_recyclerView_Pre_add_service)
    RecyclerView mTvRecyclerViewPreAddService;
    @BindView(R.id.tv_order_detail_add_service)
    TextView mTvOrderDetailAddService;
    @BindView(R.id.tv_submit_add_service)
    TextView mTvSubmitAddService;
    @BindView(R.id.ll_add_service)
    LinearLayout mLlAddService;
    @BindView(R.id.tv_remote_km)
    TextView mTvRemoteKm;
    @BindView(R.id.ll_Out_of_service_tv)
    LinearLayout mLlOutOfServiceTv;
    @BindView(R.id.iv_map1)
    ImageView mIvMap1;
    @BindView(R.id.iv_map2)
    ImageView mIvMap2;
    @BindView(R.id.et_order_beyond_km)
    EditText mEtOrderBeyondKm;
    @BindView(R.id.ll_Out_of_service_img)
    LinearLayout mLlOutOfServiceImg;
    @BindView(R.id.ll_apply_beyond)
    LinearLayout mLlApplyBeyond;
    @BindView(R.id.tv_submit_beyond)
    TextView mTvSubmitBeyond;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.et_memo)
    EditText mEtMemo;
    @BindView(R.id.tv_accessory_memo)
    TextView mTvAccessoryMemo;
    @BindView(R.id.tv_accessory_sequency)
    TextView mTvAccessorySequency;
    @BindView(R.id.tv_order_details_select_time)
    TextView mTvOrderDetailsSelectTime;
    @BindView(R.id.tv_select_time)
    TextView mTvSelectTime;
    @BindView(R.id.view_select_time_point)
    ImageView mViewSelectTimePoint;
    @BindView(R.id.rl_select_time)
    LinearLayout mRlSelectTime;
    @BindView(R.id.iv_copy)
    ImageView mIvCopy;
    @BindView(R.id.iv_bar_code)
    ImageView mIvBarCode;
    @BindView(R.id.ll_bar_code)
    LinearLayout mLlBarCode;
    @BindView(R.id.iv_machine)
    ImageView mIvMachine;
    @BindView(R.id.ll_machine)
    LinearLayout mLlMachine;
    @BindView(R.id.iv_fault_location)
    ImageView mIvFaultLocation;
    @BindView(R.id.ll_fault_location)
    LinearLayout mLlFaultLocation;
    @BindView(R.id.iv_new_and_old_accessories)
    ImageView mIvNewAndOldAccessories;
    @BindView(R.id.ll_new_and_old_accessories)
    LinearLayout mLlNewAndOldAccessories;
    @BindView(R.id.ll_return_information)
    LinearLayout mLlReturnInformation;
    @BindView(R.id.tv_total_price)
    TextView mTvTotalPrice;
    @BindView(R.id.tv_detail_submit)
    TextView mTvDetailSubmit;
    @BindView(R.id.Rl_expressno)
    RelativeLayout mRlExpressno;
    @BindView(R.id.tv_select_time2)
    TextView mTvSelectTime2;
    @BindView(R.id.view_select_time_point2)
    ImageView mViewSelectTimePoint2;
    @BindView(R.id.tv_addressback)
    TextView mTvAddressback;
    @BindView(R.id.ll_address_info)
    LinearLayout mLlAddressInfo;
    @BindView(R.id.ll_old_accessory)
    LinearLayout mLlOldAccessory;
    @BindView(R.id.tv_yn)
    TextView mTvYn;
    @BindView(R.id.tv_postpaytype)
    TextView mTvPostpaytype;
    private String OrderID;
    private WorkOrder.DataBean data;
    private ReturnAccessoryAdapter returnAccessoryAdapter;
    private GServiceAdapter gServiceAdapter;
    private Intent intent;
    private SimpleTarget<Bitmap> simpleTarget;
    private String AccessoryApplyState;
    private String ServiceApplyState;
    private String BeyondState;
    private int select_state = -1;

    /*  配件*/
    private List<Accessory> mList = new ArrayList<>();   //存放返回的list
    private Map<Integer, FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> map = new HashMap<>(); //用于存放dialog里选择的配件
    private List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> fAcList = new ArrayList<>();// 用于存放预接单页面显示的数据
    private List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> mAcList = new ArrayList<>();// 用于存放预接单页面显示的数据
    private List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> sAcList = new ArrayList<>();// 用于存放预接单页面显示的数据
    private FAccessory fAccessory;
    private FAccessory.OrderAccessoryStrBean orderAccessoryStrBean;
    private FAccessory.OrderAccessoryStrBean.OrderAccessoryBean mfAccessory;
    private Accessory mAccessory;
    private RecyclerView recyclerView_custom_add_accessory;
    private Pre_order_Add_Ac_Adapter mPre_order_add_ac_adapter; //预接单 的adater
    private Add_Ac_Adapter mAdd_Ac_Adapter;
    /*服务*/
    private List<Service> mList_service = new ArrayList<>(); //获取传来的服务列表
    private Map<Integer, FService.OrderServiceStrBean.OrderServiceBean> map_service = new HashMap<>();//用于存放dialog里选择的配件
    private List<FService.OrderServiceStrBean.OrderServiceBean> fList_service = new ArrayList<>();// 用于存放预接单页面显示的数据
    private RecyclerView recyclerView_custom_add_service;
    private Pre_order_Add_Service_Adapter mPre_order_Add_Service_Adapter; //预接单的adpter
    private Service mService;
    private FService.OrderServiceStrBean.OrderServiceBean mfService;
    private Add_Service_Adapter mAdd_Service_Adapter;
    private FService.OrderServiceStrBean orderServiceStrBean;
    private SService sService;

    private HashMap<Integer, File> files_map_remote = new HashMap<>();//申请远程费图片
    private ArrayList<String> permissions;
    private String FilePath;
    private View popupWindow_view;
    private PopupWindow mPopupWindow;
    private View choose_accessory_view;
    private LinearLayout ll_choose_accessory;
    private TextView tv_accessory_name;
    private ClearEditText et_num;
    private ClearEditText et_price;
    private ClearEditText et__service_price;
    private Button btn_add;
    private HideSoftInputDialog choose_accessory_dialog;
    private Window window;
    private WindowManager.LayoutParams lp;
    private String num;
    private String price;
    private String servicePrice;
    private View add_service_view;
    private AlertDialog add_service_dialog;
    private View tv_add_service_submit;
    private PopupWindow popupWindow;
    private Accessory accessory;
    private List<Accessory> ac_list;
    private Ac_List_Adapter ac_list_adapter;
    private Gson gson;
    private RequestBody body;
    private int remote_fee;
    private double Service_range = 15;//正常距离
    private double distance;
    private double money;
    private SAccessory sAccessory;
    private int type;
    private String AccessoryMemo;
    private String Content;
    private View customdialog_home_view;
    private AlertDialog customdialog_home_dialog;
    private TextView title;
    private TextView message;
    private Button negtive;
    private Button positive;
    private ClipboardManager myClipboard;
    private View puchsh_view;
    private Button btn_negtive;
    private Button btn_positive;
    private TextView tv_title;
    private TextView tv_message;
    private EditText etContent;
    private AlertDialog push_dialog;
    private TextView et_expressno;
    private LinearLayout ll_scan;
    private String expressno;

    private String startTime;
    private String endTime;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_work_order_details;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void initView() {
        mTvActionbarTitle.setText("详情页");
        OrderID = getIntent().getStringExtra("OrderID");
        mPresenter.GetOrderInfo(OrderID);
        mPre_order_add_ac_adapter = new Pre_order_Add_Ac_Adapter(R.layout.item_pre_order_add_accessories, fAcList);
        mRecyclerViewAddAccessories.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerViewAddAccessories.setAdapter(mPre_order_add_ac_adapter);
        mPre_order_add_ac_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_accessories_delete:
                        mPre_order_add_ac_adapter.remove(position);
                        if (mPre_order_add_ac_adapter.getData().size() > 0) {
                            mTvSubmitAddAccessories.setBackgroundResource(R.drawable.ed_order_detail_submit);
                            mTvSubmitAddAccessories.setTextColor(Color.WHITE);
                        } else {
                            mTvSubmitAddAccessories.setBackgroundResource(R.drawable.tv_order_detail_btn);
                            mTvSubmitAddAccessories.setTextColor(Color.BLACK);
                        }
                        break;
                    default:
                        break;
                }
            }
        });

        mPre_order_Add_Service_Adapter = new Pre_order_Add_Service_Adapter(R.layout.item_add_service, fList_service);
        mTvRecyclerViewPreAddService.setLayoutManager(new LinearLayoutManager(mActivity));
        mTvRecyclerViewPreAddService.setAdapter(mPre_order_Add_Service_Adapter);
        mPre_order_Add_Service_Adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_service_delete:
                        mPre_order_Add_Service_Adapter.remove(position);
                        if (mPre_order_Add_Service_Adapter.getData().size() > 0) {
                            mTvSubmitAddService.setBackgroundResource(R.drawable.ed_order_detail_submit);
                            mTvSubmitAddService.setTextColor(Color.WHITE);
                        } else {
                            mTvSubmitAddService.setBackgroundResource(R.drawable.tv_order_detail_btn);
                            mTvSubmitAddService.setTextColor(Color.BLACK);
                        }
                        break;
                    default:
                        break;
                }
            }
        });

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.GetOrderInfo(OrderID);
                mRefreshLayout.finishRefresh(3000);
            }
        });

        mAdd_Service_Adapter = new Add_Service_Adapter(R.layout.item_addservice, mList_service);

        myClipboard = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);

        mEtOrderBeyondKm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if ("".equals(s.toString())) {
                    mTvSubmitBeyond.setBackgroundResource(R.drawable.tv_order_detail_btn);
                    mTvSubmitBeyond.setTextColor(Color.BLACK);
                } else {
                    mTvSubmitBeyond.setBackgroundResource(R.drawable.ed_order_detail_submit);
                    mTvSubmitBeyond.setTextColor(Color.WHITE);
                }
            }
        });
    }


    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);

        mTvAccessoryApplication.setOnClickListener(this);
        mTvServiceApplication.setOnClickListener(this);
        mTvBeyondApplication.setOnClickListener(this);

        mIvRangeOne.setOnClickListener(this);
        mIvRangeTwo.setOnClickListener(this);

        mTvSubmitAddAccessories.setOnClickListener(this);
        mTvSubmitAddService.setOnClickListener(this);
        mTvSubmitBeyond.setOnClickListener(this);

        mTvOrderDetailsAddAccessories.setOnClickListener(this);
        mTvOrderDetailAddService.setOnClickListener(this);

        mLlManufacturers.setOnClickListener(this);
        mLlSelfbuying.setOnClickListener(this);
        mLlSelfbuyingUser.setOnClickListener(this);

        mIvMap1.setOnClickListener(this);
        mIvMap2.setOnClickListener(this);

        mIvBarCode.setOnClickListener(this);
        mIvMachine.setOnClickListener(this);
        mIvFaultLocation.setOnClickListener(this);
        mIvNewAndOldAccessories.setOnClickListener(this);

        mBtnCompleteSubmit.setOnClickListener(this);

        mBtnTrial.setOnClickListener(this);

        mTvAccessoryInformation.setOnClickListener(this);
        mTvServiceInformation.setOnClickListener(this);
        mTvRemoteFeeInformation.setOnClickListener(this);

        mIvCopy.setOnClickListener(this);
        mTvDetailSubmit.setOnClickListener(this);

        mViewSelectTimePoint.setOnClickListener(this);
        mViewSelectTimePoint2.setOnClickListener(this);
    }

    /**
     * 选择上门时间
     */
    public void chooseTime(final TextView tv, final String title) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format1 = format.format(date);

        TimeSelector timeSelector = new TimeSelector(mActivity, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
                if ("请选择结束时间".equals(title)) {
                    Integer i = startTime.compareTo(time);
                    if (i > 0) {
                        MyUtils.showToast(mActivity, "结束时间应大于开始时间");
                        return;
                    }
                    mPresenter.UpdateSendOrderUpdateTime(OrderID, startTime, time);
                } else {
                    mTvSelectTime2.setText("");
                }
                tv.setText(time);
            }
        }, format1, "2022-1-1 24:00");

        timeSelector.setTitle(title);
        timeSelector.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_select_time_point:
                chooseTime(mTvSelectTime, "请选择开始时间");
                break;
            case R.id.view_select_time_point2:
                startTime = mTvSelectTime.getText().toString();
                if ("".equals(startTime)) {
                    MyUtils.showToast(mActivity, "请先选择开始时间");
                    return;
                }
                chooseTime(mTvSelectTime2, "请选择结束时间");
                break;
            case R.id.tv_submit_add_accessories:
                submit(1);
                break;
            case R.id.tv_submit_add_service:
                submit(2);
                break;
            case R.id.tv_submit_beyond:
                if (files_map_remote.size() > 0) {
                    OrderByondImgPicUpload(files_map_remote);
                } else {
                    submit(3);
                }
                break;
            case R.id.iv_map1:
                showPopupWindow(901, 909);
                break;
            case R.id.iv_map2:
                showPopupWindow(1001, 1002);
                break;
            case R.id.tv_order_details_add_accessories: //添加配件
                if (select_state == -1) {
                    Toast.makeText(this, "添加配件请选中类型", Toast.LENGTH_SHORT).show();
                } else {
                    addAccessory();
                }
                break;
            /*添加服务*/
            case R.id.tv_order_detail_add_service:
                mPresenter.GetFactoryService(data.getBrandID() + "", data.getCategoryID() + "");
                break;
            case R.id.ll_manufacturers://厂家寄件申请
                if (select_state != 0) {
                    select_state = 0;
                    mIvManufacturers.setSelected(true);
                    mIvSelfbuying.setSelected(false);
                    mIvSelfbuyingUser.setSelected(false);
                    mPre_order_add_ac_adapter.setNewData(fAcList);
                    mRecyclerViewAddAccessories.setVisibility(View.VISIBLE);
                    if (mPre_order_add_ac_adapter.getData().size() > 0) {
                        mTvSubmitAddAccessories.setBackgroundResource(R.drawable.ed_order_detail_submit);
                        mTvSubmitAddAccessories.setTextColor(Color.WHITE);
                    } else {
                        mTvSubmitAddAccessories.setBackgroundResource(R.drawable.tv_order_detail_btn);
                        mTvSubmitAddAccessories.setTextColor(Color.BLACK);
                    }
                } else {
                    select_state = -1;
                    mIvManufacturers.setSelected(false);
                    mIvSelfbuying.setSelected(false);
                    mIvSelfbuyingUser.setSelected(false);
                    mRecyclerViewAddAccessories.setVisibility(View.GONE);
                    mTvSubmitAddAccessories.setBackgroundResource(R.drawable.tv_order_detail_btn);
                    mTvSubmitAddAccessories.setTextColor(Color.BLACK);
                }
                break;
            case R.id.ll_selfbuying://师傅自购件
                if (select_state != 1) {
                    select_state = 1;
                    mIvManufacturers.setSelected(false);
                    mIvSelfbuying.setSelected(true);
                    mIvSelfbuyingUser.setSelected(false);
                    mPre_order_add_ac_adapter.setNewData(mAcList);
                    mRecyclerViewAddAccessories.setVisibility(View.VISIBLE);
                    if (mPre_order_add_ac_adapter.getData().size() > 0) {
                        mTvSubmitAddAccessories.setBackgroundResource(R.drawable.ed_order_detail_submit);
                        mTvSubmitAddAccessories.setTextColor(Color.WHITE);
                    } else {
                        mTvSubmitAddAccessories.setBackgroundResource(R.drawable.tv_order_detail_btn);
                        mTvSubmitAddAccessories.setTextColor(Color.BLACK);
                    }
                } else {
                    select_state = -1;
                    mIvManufacturers.setSelected(false);
                    mIvSelfbuying.setSelected(false);
                    mIvSelfbuyingUser.setSelected(false);
                    mRecyclerViewAddAccessories.setVisibility(View.GONE);
                    mTvSubmitAddAccessories.setBackgroundResource(R.drawable.tv_order_detail_btn);
                    mTvSubmitAddAccessories.setTextColor(Color.BLACK);
                }
                break;
            case R.id.ll_selfbuying_user://用户自购件
                if (select_state != 2) {
                    select_state = 2;
                    mIvManufacturers.setSelected(false);
                    mIvSelfbuying.setSelected(false);
                    mIvSelfbuyingUser.setSelected(true);
                    mPre_order_add_ac_adapter.setNewData(sAcList);
                    mRecyclerViewAddAccessories.setVisibility(View.VISIBLE);
                    if (mPre_order_add_ac_adapter.getData().size() > 0) {
                        mTvSubmitAddAccessories.setBackgroundResource(R.drawable.ed_order_detail_submit);
                        mTvSubmitAddAccessories.setTextColor(Color.WHITE);
                    } else {
                        mTvSubmitAddAccessories.setBackgroundResource(R.drawable.tv_order_detail_btn);
                        mTvSubmitAddAccessories.setTextColor(Color.BLACK);
                    }
                } else {
                    select_state = -1;
                    mIvManufacturers.setSelected(false);
                    mIvSelfbuying.setSelected(false);
                    mIvSelfbuyingUser.setSelected(false);
                    mRecyclerViewAddAccessories.setVisibility(View.GONE);
                    mTvSubmitAddAccessories.setBackgroundResource(R.drawable.tv_order_detail_btn);
                    mTvSubmitAddAccessories.setTextColor(Color.BLACK);
                }
                break;
            case R.id.ll_return:
                finish();
                break;
            case R.id.tv_accessory_information:
            case R.id.tv_service_information:
            case R.id.tv_remote_fee_information:
            case R.id.btn_trial:
                puchsh_view = LayoutInflater.from(mActivity).inflate(R.layout.customdialog_push, null);
                btn_negtive = puchsh_view.findViewById(R.id.negtive);
                btn_positive = puchsh_view.findViewById(R.id.positive);
                tv_title = puchsh_view.findViewById(R.id.title);
                etContent = puchsh_view.findViewById(R.id.et_content);
                push_dialog = new AlertDialog.Builder(mActivity)
                        .setView(puchsh_view)
                        .create();
                push_dialog.show();
                tv_title.setText("提示");
                btn_negtive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        push_dialog.dismiss();
                    }
                });
                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Content = etContent.getText().toString().trim();
                        mPresenter.PressFactoryAccount(OrderID, Content);
                    }
                });

                break;
            case R.id.tv_detail_submit:
                puchsh_view = LayoutInflater.from(mActivity).inflate(R.layout.customdialog_add_expressno, null);
                btn_negtive = puchsh_view.findViewById(R.id.negtive);
                btn_positive = puchsh_view.findViewById(R.id.positive);
                tv_title = puchsh_view.findViewById(R.id.title);
                et_expressno = puchsh_view.findViewById(R.id.et_expressno);
                ll_scan = puchsh_view.findViewById(R.id.ll_scan);
                push_dialog = new AlertDialog.Builder(mActivity)
                        .setView(puchsh_view)
                        .create();
                push_dialog.show();
                tv_title.setText("填写快递单号");
                btn_negtive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        push_dialog.dismiss();
                    }
                });
                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        expressno = et_expressno.getText().toString().trim();
                        if ("".equals(expressno)) {
                            MyUtils.showToast(mActivity, "请填写快递单号");
                            return;
                        }
                        mPresenter.AddReturnAccessory(OrderID, expressno);
                    }
                });

                break;
            case R.id.tv_accessory_application:
                intent = new Intent(mActivity, Order_Add_Accessories_Activity.class);
                intent.putExtra("OrderID", data.getOrderID());
                intent.putExtra("type", 1);
                startActivity(intent);
                break;
            case R.id.tv_service_application:
                intent = new Intent(mActivity, Order_Add_Accessories_Activity.class);
                intent.putExtra("OrderID", data.getOrderID());
                intent.putExtra("type", 2);
                startActivity(intent);
                break;
            case R.id.tv_beyond_application:
                intent = new Intent(mActivity, Order_Add_Accessories_Activity.class);
                intent.putExtra("OrderID", data.getOrderID());
                intent.putExtra("type", 3);
                startActivity(intent);
                break;
            case R.id.btn_complete_submit:
                intent = new Intent(mActivity, CompleteWorkOrderActivity.class);
                intent.putExtra("OrderID", data.getOrderID());
                startActivity(intent);
                break;
            case R.id.iv_range_one:
                if (data.getOrderBeyondImg() == null) {
                    return;
                }
                if (data.getOrderBeyondImg().size() == 0) {
                    return;
                }
                scaleview("http://47.96.126.145:8820/Pics/OrderByondImg/" + data.getOrderBeyondImg().get(0).getUrl());
                break;
            case R.id.iv_range_two:
                if (data.getOrderBeyondImg() == null) {
                    return;
                }
                if (data.getOrderBeyondImg().size() < 2) {
                    return;
                }
                scaleview("http://47.96.126.145:8820/Pics/OrderByondImg/" + data.getOrderBeyondImg().get(1).getUrl());
                break;
            case R.id.iv_bar_code:
                scaleview("http://47.96.126.145:8820/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(0).getUrl());
                break;
            case R.id.iv_machine:
                scaleview("http://47.96.126.145:8820/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(1).getUrl());
                break;
            case R.id.iv_fault_location:
                scaleview("http://47.96.126.145:8820/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(2).getUrl());
                break;
            case R.id.iv_new_and_old_accessories:
                scaleview("http://47.96.126.145:8820/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(3).getUrl());
                break;
            case R.id.iv_copy:
                String id = data.getOrderID();
                ClipData myClip = ClipData.newPlainText("", id);
                myClipboard.setPrimaryClip(myClip);
                ToastUtils.showShort("复制成功");
        }

    }

    public void scaleview(String url) {
        simpleTarget = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<?
                    super Bitmap> transition) {
                RxDialogScaleView rxDialogScaleView = new RxDialogScaleView(mActivity);
                rxDialogScaleView.setImage(resource);
                rxDialogScaleView.show();
            }
        };

        Glide.with(mActivity)
                .asBitmap()
                .load(url)
                .into(simpleTarget);
    }

    /**
     * 添加配件
     */
    public void addAccessory() {
        choose_accessory_view = LayoutInflater.from(mActivity).inflate(R.layout.activity_choose_accessory, null);
        ll_choose_accessory = choose_accessory_view.findViewById(R.id.ll_choose_accessory);
        tv_accessory_name = choose_accessory_view.findViewById(R.id.tv_accessory_name);
        et_num = choose_accessory_view.findViewById(R.id.et_num);
        et_price = choose_accessory_view.findViewById(R.id.et_price);
        et__service_price = choose_accessory_view.findViewById(R.id.et__service_price);
        if (select_state == 0) {//厂家自购
            et_price.setVisibility(View.GONE);
            et__service_price.setVisibility(View.GONE);
        } else if (select_state == 1) {//师傅自购 还要判断保内保外
            et_price.setVisibility(View.VISIBLE);
            et__service_price.setVisibility(View.GONE);
        } else {//用户自购
            et_price.setVisibility(View.GONE);
            et__service_price.setVisibility(View.VISIBLE);
        }
        btn_add = choose_accessory_view.findViewById(R.id.btn_add);
        choose_accessory_dialog = new HideSoftInputDialog(mActivity);
        choose_accessory_dialog.setContentView(choose_accessory_view);
        choose_accessory_dialog.show();

        window = choose_accessory_dialog.getWindow();
//                window.setContentView(under_review);
        lp = window.getAttributes();
//                lp.alpha = 0.5f;
        // 也可按屏幕宽高比例进行设置宽高
        Display display = WorkOrderDetailsActivity.this.getWindowManager().getDefaultDisplay();
        lp.width = (int) (display.getWidth() * 0.9);
//                lp.height = under_review.getHeight();
//                lp.width = 300;
//                lp.height = (int) (display.getHeight() * 0.5);

        window.setAttributes(lp);
//                window.setDimAmount(0.1f);
        window.setBackgroundDrawable(new ColorDrawable());

        ll_choose_accessory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.GetFactoryAccessory(data.getSubCategoryID() + "");
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = et_num.getText().toString();
                price = et_price.getText().toString();
                servicePrice = et__service_price.getText().toString();
                if (accessory == null) {
                    ToastUtils.showShort("请选择配件");
                    return;
                }
                if ("".equals(num)) {
                    ToastUtils.showShort("请输入数量");
                    return;
                }
                mfAccessory = new FAccessory.OrderAccessoryStrBean.OrderAccessoryBean();
                mfAccessory.setFAccessoryID(accessory.getFAccessoryID() + "");//获取id
                mfAccessory.setFAccessoryName(accessory.getAccessoryName()); //获取名字
                mfAccessory.setQuantity(num); //数量 默认数字为1
                mfAccessory.setPrice(accessory.getAccessoryPrice());//原价
                mfAccessory.setDiscountPrice(accessory.getAccessoryPrice());//折扣价
                mfAccessory.setSendState("N");
                mfAccessory.setRelation("");
                mfAccessory.setState("0");
                mfAccessory.setIsPay("N");
                mfAccessory.setExpressNo("");
                if (select_state == 0) {//厂家自购
                    mfAccessory.setPrice(accessory.getAccessoryPrice());//原价
                    mfAccessory.setDiscountPrice(accessory.getAccessoryPrice());//原价
                } else if (select_state == 1) {//师傅自购 还要判断保内保外
                    if ("".equals(price)) {
                        ToastUtils.showShort("请输入配件价格");
                        return;
                    }
                    mfAccessory.setPrice(Double.parseDouble(price));
                    mfAccessory.setDiscountPrice(Double.parseDouble(price));
                } else {//用户自购
                    if ("".equals(servicePrice)) {
                        ToastUtils.showShort("请输入服务价格");
                        return;
                    }
                    mfAccessory.setPrice(Double.parseDouble(servicePrice));
                    mfAccessory.setDiscountPrice(Double.parseDouble(servicePrice));
                }

                if (select_state == 0) {//厂家自购
                    if (fAcList.size() > 0) {
                        for (int i = 0; i < fAcList.size(); i++) {
                            if (mfAccessory.getFAccessoryName().equals(fAcList.get(i).getFAccessoryName())) {
                                fAcList.remove(i);
                            }
                        }
                    }
                    fAcList.add(mfAccessory);
                    mPre_order_add_ac_adapter.setNewData(fAcList);
                } else if (select_state == 1) {//师傅自购 还要判断保内保外
                    if (mAcList.size() > 0) {
                        for (int i = 0; i < mAcList.size(); i++) {
                            if (mfAccessory.getFAccessoryName().equals(mAcList.get(i).getFAccessoryName())) {
                                mAcList.remove(i);
                            }
                        }
                    }
                    mAcList.add(mfAccessory);
                    mPre_order_add_ac_adapter.setNewData(mAcList);
                } else {//用户自购
                    if (sAcList.size() > 0) {
                        for (int i = 0; i < sAcList.size(); i++) {
                            if (mfAccessory.getFAccessoryName().equals(sAcList.get(i).getFAccessoryName())) {
                                sAcList.remove(i);
                            }
                        }
                    }
                    sAcList.add(mfAccessory);
                    mPre_order_add_ac_adapter.setNewData(sAcList);
                }
                choose_accessory_dialog.dismiss();
            }
        });
        choose_accessory_dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onDismiss(DialogInterface dialog) {
                accessory = null;
                num = "";
                price = "";
                servicePrice = "";
                if (mPre_order_add_ac_adapter.getData().size() > 0) {
                    mTvSubmitAddAccessories.setBackgroundResource(R.drawable.ed_order_detail_submit);
                    mTvSubmitAddAccessories.setTextColor(Color.WHITE);
                } else {
                    mTvSubmitAddAccessories.setBackgroundResource(R.drawable.tv_order_detail_btn);
                    mTvSubmitAddAccessories.setTextColor(Color.BLACK);
                }
            }
        });

    }

    /**
     * 添加服务
     */
    public void addService() {
        add_service_view = LayoutInflater.from(mActivity).inflate(R.layout.customdialog_add_service, null);
        add_service_dialog = new AlertDialog.Builder(mActivity)
                .setView(add_service_view)
                .create();
        add_service_dialog.show();
        tv_add_service_submit = add_service_view.findViewById(R.id.tv_add_service_submit);
        recyclerView_custom_add_service = add_service_view.findViewById(R.id.recyclerView_custom_add_service);
        recyclerView_custom_add_service.setLayoutManager(new LinearLayoutManager(mActivity));

        recyclerView_custom_add_service.setAdapter(mAdd_Service_Adapter);
        mAdd_Service_Adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mList_service.get(position).isIschecked()) {
                    mList_service.get(position).setIschecked(false);
                } else {
                    mList_service.get(position).setIschecked(true);
                }
                mAdd_Service_Adapter.notifyDataSetChanged();
            }
        });
        tv_add_service_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fList_service.clear();
                for (int i = 0; i < mList_service.size(); i++) {
                    mService = mList_service.get(i);
                    if (mService.isIschecked()) {
                        mfService = new FService.OrderServiceStrBean.OrderServiceBean();
                        mfService.setServiceID(mService.getFServiceID());
                        mfService.setServiceName(mService.getFServiceName());
                        mfService.setPrice(mService.getInitPrice());
                        mfService.setDiscountPrice(mService.getInitPrice());
                        mfService.setIsPay("N");
                        mfService.setRelation("");
                        fList_service.add(mfService);
                    }
                }
                mPre_order_Add_Service_Adapter.setNewData(fList_service);
                add_service_dialog.dismiss();
            }
        });
        add_service_dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (mPre_order_Add_Service_Adapter.getData().size() > 0) {
                    mTvSubmitAddService.setBackgroundResource(R.drawable.ed_order_detail_submit);
                    mTvSubmitAddService.setTextColor(Color.WHITE);
                } else {
                    mTvSubmitAddService.setBackgroundResource(R.drawable.tv_order_detail_btn);
                    mTvSubmitAddService.setTextColor(Color.BLACK);
                }
            }
        });
    }

    public void submit(int type) {
        gson = new Gson();
        switch (type) {
            case 1:
                AccessoryMemo = mEtMemo.getText().toString().trim();
                if (select_state == -1) {
                    ToastUtils.showShort("请选择配件类型");
                } else {
                    if (mPre_order_add_ac_adapter.getData().size() == 0) {
                        ToastUtils.showShort("请添加配件");
                    } else {
                        orderAccessoryStrBean = new FAccessory.OrderAccessoryStrBean();
                        orderAccessoryStrBean.setOrderAccessory(mPre_order_add_ac_adapter.getData());
                        orderAccessoryStrBean.setAccessoryMemo(AccessoryMemo);
                        String s1 = gson.toJson(orderAccessoryStrBean);
                        sAccessory = new SAccessory();
                        sAccessory.setOrderID(OrderID);
                        sAccessory.setAccessorySequency(Integer.toString(select_state));
                        sAccessory.setOrderAccessoryStr(s1);
                        String s = gson.toJson(sAccessory);
                        Log.d("添加的配件有", s);
                        body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
                        mPresenter.AddOrderAccessory(body);
                    }
                }
                break;
            case 2:
                if (mPre_order_Add_Service_Adapter.getData().size() == 0) {
                    ToastUtils.showShort("请添加服务");
                } else {
                    orderServiceStrBean = new FService.OrderServiceStrBean();
                    orderServiceStrBean.setOrderService(mPre_order_Add_Service_Adapter.getData());
                    String s1 = gson.toJson(orderServiceStrBean);
                    sService = new SService();
                    sService.setOrderID(OrderID);
                    sService.setOrderServiceStr(s1);
                    String s = gson.toJson(sService);
                    Log.d("添加的服务有", s);
                    body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
                    mPresenter.AddOrderService(body);
                }
                break;
            case 3://申请远程费
                if (mEtOrderBeyondKm.getText().toString().equals("")) {//什么都不输入
                    distance = Double.parseDouble(data.getDistance());
                    money = distance - Service_range;
                    if (money < 0) {
                        MyUtils.showToast(mActivity, "未超出正常服务范围，距离如果有误，请自行填写超出公里数");
                        return;
                    }
                } else {
                    //用户自己输入
                    money = Double.parseDouble(mEtOrderBeyondKm.getText().toString());
                    distance = Double.parseDouble(mEtOrderBeyondKm.getText().toString());
                }
                mPresenter.ApplyBeyondMoney(OrderID, money + "", distance + "");
                break;
            default:
                break;
        }
    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {
        mRefreshLayout.finishRefresh();
        switch (baseResult.getStatusCode()) {

            case 200:
                data = baseResult.getData();
                mTvStatus.setText(data.getStateStr());
                mTvTotalPrice.setText("服务金额：￥" + data.getOrderMoney());
                mTvAccessoryMemo.setText("备注：" + data.getAccessoryMemo());
                mTvAccessorySequency.setText(data.getAccessorySequencyStr());
                mTvName.setText(data.getUserName());
                mTvPhone.setText(data.getPhone());
                mTvAddress.setText(data.getAddress());
                mTvWorkOrderNumber.setText(data.getOrderID());
                mTvOrderTime.setText(data.getAudDate().replace("T", " ")); //将T替换为空格
                mTvCauseOfIssue.setText(data.getMemo());
                mTvProductType.setText(data.getCategoryName() + "/" + data.getBrandName() + "/" + data.getSubCategoryName());

                if (data.getSendOrderList().size() != 0) {
                    mTvSelectTime.setText(data.getSendOrderList().get(0).getServiceDate());
                    mTvSelectTime2.setText(data.getSendOrderList().get(0).getServiceDate2());
                }
                if (data.getTypeID() == 1) {//维修
                    mTvType.setText(data.getTypeName() + "/" + data.getGuaranteeText());
                    mTvType.setBackgroundResource(R.color.color_custom_01);
//                    mll_return_information.setVisibility(View.VISIBLE);
//                    mll_service_process.setVisibility(View.GONE);

                } else {
                    mTvType.setText(data.getTypeName() + "/" + data.getGuaranteeText());
                    mTvType.setBackgroundResource(R.color.color_custom_04);
//                    mll_return_information.setVisibility(View.GONE);
//                    mll_service_process.setVisibility(View.VISIBLE);

                }
                mTvServiceAddress.setText(data.getAddress());
                mTvNumber.setText(data.getNum());

                /*获取订单的距离*/
                distance = Double.parseDouble(data.getDistance());
                if (Service_range >= distance) {
                    mTvRemoteKm.setText("0km");
                    mTvSubmitBeyond.setBackgroundResource(R.drawable.tv_order_detail_btn);
                    mTvSubmitBeyond.setTextColor(Color.BLACK);
                } else {
                    mTvRemoteKm.setText(String.format("%.2f", distance - Service_range) + "km");
                    mTvSubmitBeyond.setBackgroundResource(R.drawable.ed_order_detail_submit);
                    mTvSubmitBeyond.setTextColor(Color.WHITE);
                }
                if (data.getBeyondState() == null) {
                    mLlApproveBeyondMoney.setVisibility(View.GONE);
                    mLlApplyBeyond.setVisibility(View.VISIBLE);
                } else {
                    mTvRange.setText(data.getBeyondDistance());
                    if (data.getOrderBeyondImg() == null) {
                        return;
                    }
                    if (data.getOrderBeyondImg().size() == 1) {
                        Glide.with(mActivity).load("http://47.96.126.145:8820/Pics/OrderByondImg/" + data.getOrderBeyondImg().get(0).getUrl()).into(mIvRangeOne);
//                        Glide.with(mActivity).load("http://47.96.126.145:8820/Pics/OrderByondImg/" + data.getOrderBeyondImg().get(1).getUrl()).into(mIvRangeTwo);
                        mIvRangeTwo.setVisibility(View.GONE);
                    } else {
                        mIvRangeOne.setVisibility(View.GONE);
                        mIvRangeTwo.setVisibility(View.GONE);
                    }
                    mLlApproveBeyondMoney.setVisibility(View.VISIBLE);
                    mLlApplyBeyond.setVisibility(View.GONE);
                    if ("0".equals(data.getBeyondState())) {
                        mTvBeyondState.setText("审核中");
                        mTvBeyondApplication.setVisibility(View.GONE);
                    } else if ("1".equals(data.getBeyondState())) {
                        mTvBeyondState.setText("审核通过");
                        mTvBeyondApplication.setVisibility(View.VISIBLE);
                    } else {
                        mTvBeyondState.setText("被拒");
                        mTvBeyondApplication.setVisibility(View.VISIBLE);
                    }
                }
                AccessoryApplyState = data.getAccessoryApplyState();
                ServiceApplyState = data.getServiceApplyState();
                BeyondState = data.getBeyondState();
                if ("".equals(AccessoryApplyState) && "".equals(ServiceApplyState) && BeyondState == null) {
                    //nnn  配件  服务  远程
                    mBtnCompleteSubmit.setVisibility(View.VISIBLE);
                    mBtnTrial.setVisibility(View.GONE);
                } else if (!"".equals(AccessoryApplyState) && "".equals(ServiceApplyState) && BeyondState == null) {
                    //ynn
                    if ("1".equals(AccessoryApplyState)) {
                        if ("Y".equals(data.getAccessorySendState())) {
                            mBtnCompleteSubmit.setVisibility(View.VISIBLE);
                        } else {
                            mBtnCompleteSubmit.setVisibility(View.VISIBLE);
                        }
                    } else {
                        mBtnCompleteSubmit.setVisibility(View.GONE);
                    }
                } else if ("".equals(AccessoryApplyState) && !"".equals(ServiceApplyState) && BeyondState == null) {
                    //nyn
                    if ("1".equals(ServiceApplyState)) {
                        mBtnCompleteSubmit.setVisibility(View.VISIBLE);
                    } else {
                        mBtnCompleteSubmit.setVisibility(View.GONE);
                    }
                } else if ("".equals(AccessoryApplyState) && "".equals(ServiceApplyState) && BeyondState != null) {
                    //nny
                    if ("1".equals(BeyondState)) {
                        mBtnCompleteSubmit.setVisibility(View.VISIBLE);
                    } else {
                        mBtnCompleteSubmit.setVisibility(View.GONE);
                    }
                } else if (!"".equals(AccessoryApplyState) && !"".equals(ServiceApplyState) && BeyondState == null) {
                    //yyn
                    if ("1".equals(AccessoryApplyState) && "1".equals(ServiceApplyState)) {
                        if ("Y".equals(data.getAccessorySendState())) {
                            mBtnCompleteSubmit.setVisibility(View.VISIBLE);
                        } else {
                            mBtnCompleteSubmit.setVisibility(View.VISIBLE);
                        }
                    } else {
                        mBtnCompleteSubmit.setVisibility(View.GONE);
                    }
                } else if (!"".equals(AccessoryApplyState) && "".equals(ServiceApplyState) && BeyondState != null) {
                    //yny
                    if ("1".equals(AccessoryApplyState) && "1".equals(BeyondState)) {
                        if ("Y".equals(data.getAccessorySendState())) {
                            mBtnCompleteSubmit.setVisibility(View.VISIBLE);
                        } else {
                            mBtnCompleteSubmit.setVisibility(View.VISIBLE);
                        }
                    } else {
                        mBtnCompleteSubmit.setVisibility(View.GONE);
                    }
                } else if ("".equals(AccessoryApplyState) && !"".equals(ServiceApplyState) && BeyondState != null) {
                    //nyy
                    if ("1".equals(ServiceApplyState) && "1".equals(ServiceApplyState)) {
                        mBtnCompleteSubmit.setVisibility(View.VISIBLE);
                    } else {
                        mBtnCompleteSubmit.setVisibility(View.GONE);
                    }
                } else {
                    //yyy
                    if ("1".equals(AccessoryApplyState) && "1".equals(ServiceApplyState) && "1".equals(BeyondState)) {
                        if ("Y".equals(data.getAccessorySendState())) {
                            mBtnCompleteSubmit.setVisibility(View.VISIBLE);
                        } else {
                            mBtnCompleteSubmit.setVisibility(View.VISIBLE);
                        }
                    } else {
                        mBtnCompleteSubmit.setVisibility(View.GONE);
                    }
                }


                if (data.getOrderAccessroyDetail().size() != 0) {
                    returnAccessoryAdapter = new ReturnAccessoryAdapter(R.layout.item_returned, data.getOrderAccessroyDetail(), Integer.parseInt(data.getAccessoryState()));
                    mRvReturnInformation.setLayoutManager(new LinearLayoutManager(mActivity));
                    mRvReturnInformation.setAdapter(returnAccessoryAdapter);
                    mLlAccessory.setVisibility(View.VISIBLE);
                    mLlAddAccessory.setVisibility(View.GONE);
//                    mLlMemo.setVisibility(View.GONE);
                    if ("0".equals(data.getAccessoryApplyState())) {
                        mTvAccessoryApplyState.setText("审核中");
                        mTvAccessoryApplication.setVisibility(View.GONE);
                    } else if ("1".equals(data.getAccessoryApplyState())) {
                        mTvAccessoryApplyState.setText("审核通过");
                        mTvAccessoryApplication.setVisibility(View.VISIBLE);
                    } else {
                        mTvAccessoryApplyState.setText("被拒");
                        mTvAccessoryApplication.setVisibility(View.VISIBLE);
                    }
                } else {
                    mLlAccessory.setVisibility(View.GONE);
                    mLlAddAccessory.setVisibility(View.VISIBLE);
//                    mLlMemo.setVisibility(View.VISIBLE);
                }
                if (data.getOrderServiceDetail().size() != 0) {
                    gServiceAdapter = new GServiceAdapter(R.layout.item_service, data.getOrderServiceDetail());
                    mRvService.setLayoutManager(new LinearLayoutManager(mActivity));
                    mRvService.setAdapter(gServiceAdapter);
                    mLlService.setVisibility(View.VISIBLE);
                    mLlAddService.setVisibility(View.GONE);
                    if ("0".equals(data.getServiceApplyState())) {
                        mTvServiceApplyState.setText("审核中");
                        mTvServiceApplication.setVisibility(View.GONE);
                    } else if ("1".equals(data.getServiceApplyState())) {
                        mTvServiceApplyState.setText("审核通过");
                        mTvServiceApplication.setVisibility(View.VISIBLE);
                    } else {
                        mTvServiceApplyState.setText("被拒");
                        mTvServiceApplication.setVisibility(View.VISIBLE);
                    }
                } else {
                    mLlService.setVisibility(View.GONE);
                    mLlAddService.setVisibility(View.VISIBLE);
                }

                if ("1".equals(data.getAccessoryApplyState())){
                    mLlOldAccessory.setVisibility(View.VISIBLE);
                    if ("1".equals(data.getIsReturn())){
                        mTvYn.setText("是");
                        mLlAddressInfo.setVisibility(View.VISIBLE);
                        mTvAddressback.setText(data.getAddressBack());
                        if ("1".equals(data.getPostPayType())){
                            mTvPostpaytype.setText("厂商到付");
                        }else{
                            mTvPostpaytype.setText("维修商现付");
                        }
                    }else{
                        mTvYn.setText("否");
                        mLlAddressInfo.setVisibility(View.GONE);
                    }
                }else{
                    mLlOldAccessory.setVisibility(View.GONE);
                }

                if ("5".equals(data.getState()) || "6".equals(data.getState()) || "7".equals(data.getState())) {
                    mBtnCompleteSubmit.setVisibility(View.GONE);
                    mBtnTrial.setVisibility(View.GONE);
                    mLlAddAccessory.setVisibility(View.GONE);
                    mLlAddService.setVisibility(View.GONE);
                    mLlApplyBeyond.setVisibility(View.GONE);

                    mTvBeyondState.setVisibility(View.GONE);
                    mTvServiceApplyState.setVisibility(View.GONE);
                    mTvAccessoryApplyState.setVisibility(View.GONE);

                    mTvAccessoryApplication.setVisibility(View.GONE);
                    mTvServiceApplication.setVisibility(View.GONE);
                    mTvBeyondApplication.setVisibility(View.GONE);
                    mTvAccessoryInformation.setVisibility(View.GONE);
                    mTvServiceInformation.setVisibility(View.GONE);
                    mTvRemoteFeeInformation.setVisibility(View.GONE);

                    mViewSelectTimePoint.setVisibility(View.GONE);
                    mViewSelectTimePoint2.setVisibility(View.GONE);
                    if ("5".equals(data.getState())) {
                        if ("".equals(data.getReturnAccessoryMsg()) || data.getReturnAccessoryMsg() == null) {
                            mRlExpressno.setVisibility(View.VISIBLE);
                        } else {
                            mRlExpressno.setVisibility(View.GONE);
                        }
                    } else {
                        mRlExpressno.setVisibility(View.GONE);
                    }
                    mLlReturnInformation.setVisibility(View.VISIBLE);


                    Glide.with(mActivity).load("http://47.96.126.145:8820/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(0).getUrl()).into(mIvBarCode);
                    Glide.with(mActivity).load("http://47.96.126.145:8820/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(1).getUrl()).into(mIvMachine);
                    Glide.with(mActivity).load("http://47.96.126.145:8820/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(2).getUrl()).into(mIvFaultLocation);
                    Glide.with(mActivity).load("http://47.96.126.145:8820/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(3).getUrl()).into(mIvNewAndOldAccessories);
                } else {
                    mLlReturnInformation.setVisibility(View.GONE);
                    mRlExpressno.setVisibility(View.GONE);
                    mViewSelectTimePoint.setVisibility(View.VISIBLE);
                    mViewSelectTimePoint2.setVisibility(View.VISIBLE);
                }
                break;

            default:
                break;
        }


    }

    /*获取工厂配件*/
    @Override
    public void GetFactoryAccessory(BaseResult<GetFactoryData<Accessory>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if ("0".equals(baseResult.getData().getCode())) {
                    ac_list = baseResult.getData().getData();
                    if (ac_list == null) {
                        MyUtils.showToast(mActivity, "无配件，请联系管理员");
                        return;
                    }
                    if (ac_list.size() == 0) {
                        MyUtils.showToast(mActivity, "无配件，请联系管理员");
                        return;
                    }
                    ac_list_adapter = new Ac_List_Adapter(R.layout.item_accessory, ac_list);
                    showPopWindow(tv_accessory_name, ac_list_adapter, ac_list);
                }
                break;
            default:
                ToastUtils.showShort("获取配件失败");
                break;


        }
    }

    @Override
    public void GetFactoryService(BaseResult<GetFactoryData<Service>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if ("0".equals(baseResult.getData().getCode())) {
                    mList_service = baseResult.getData().getData();
                    mAdd_Service_Adapter.setNewData(mList_service);
                    if (mList_service.size() == 0) {
                        MyUtils.showToast(mActivity, "无服务，请联系管理员");
                    } else {
                        addService();
                    }
                } else {

                }
                break;
            default:
                break;


        }
    }

    /*提交配件信息*/
    @Override
    public void AddOrderAccessory(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    ToastUtils.showShort("提交成功");
                    EventBus.getDefault().post("WorkOrderDetailsActivity");
                    EventBus.getDefault().post(5);
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

    /**/
    @Override
    public void AddOrderService(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    ToastUtils.showShort("提交成功");
                    EventBus.getDefault().post("WorkOrderDetailsActivity");
                    EventBus.getDefault().post(5);
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
    public void AddOrUpdateAccessoryServiceReturn(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void UpdateSendOrderUpdateTime(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:

                break;
            default:
                break;
        }
    }

    @Override
    public void GetOrderAccessoryByOrderID(BaseResult<List<GAccessory>> baseResult) {

    }

    @Override
    public void ServiceOrderPicUpload(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void ReuturnAccessoryPicUpload(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void FinishOrderPicUpload(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void OrderByondImgPicUpload(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    submit(3);
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
                    EventBus.getDefault().post("WorkOrderDetailsActivity");
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
    public void PressFactoryAccount(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data = baseResult.getData();
                if (data.isItem1()) {
                    push_dialog.dismiss();
                    ToastUtils.showShort("催审成功");
                } else {
                    ToastUtils.showShort(data.getItem2());
                }
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
                    finish();
                    EventBus.getDefault().post(4);
                } else {
                    ToastUtils.showShort(data.getItem2());
                }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        if (!"WorkOrderDetailsActivity".equals(name)) {
            return;
        }
        mPresenter.GetOrderInfo(OrderID);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 下拉选配件服务
     *
     * @param tv
     * @param adapter
     * @param list
     */
    public void showPopWindow(final TextView tv, BaseQuickAdapter adapter, final List list) {

        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.category_pop, null);
        final RecyclerView rv = contentView.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(mActivity));
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                popupWindow.dismiss();
                if (list.get(position) instanceof Accessory) {
                    accessory = (Accessory) list.get(position);
                    tv_accessory_name.setText(accessory.getAccessoryName());
                }
            }
        });
        popupWindow = new PopupWindow(contentView);
        popupWindow.setWidth(tv.getWidth());
        if (list.size() > 5) {
            popupWindow.setHeight(600);
        } else {
            popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        }
//        popupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
//                MyUtils.setWindowAlpa(mActivity, false);
                lp.alpha = 1f;
                window.setAttributes(lp);
            }
        });
        if (popupWindow != null && !popupWindow.isShowing()) {
            popupWindow.showAsDropDown(tv, 0, 10);
//            popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        }
//        MyUtils.setWindowAlpa(mActivity, true);
        lp.alpha = 0.9f;
        window.setAttributes(lp);
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
                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("image/*");
                    startActivityForResult(Intent.createChooser(i, "test"), code2);
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
            }
        });
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
//            popupWindow.showAsDropDown(tv, 0, 10);
            mPopupWindow.showAtLocation(popupWindow_view, Gravity.BOTTOM, 0, 0);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File file = null;
        switch (requestCode) {
            /* 地图1*/
            //拍照
            case 901:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(mIvMap1);
                    file = new File(FilePath);
                }
                if (file != null) {
                    files_map_remote.put(0, file);
                }
                break;
            //相册
            case 909:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvMap1);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    files_map_remote.put(0, file);
                }
                break;


            /* 地图2*/
            //拍照
            case 1001:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(mIvMap2);
                    file = new File(FilePath);
                }
                if (file != null) {
                    files_map_remote.put(1, file);
                }

                break;
            //相册
            case 1002:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvMap2);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    files_map_remote.put(1, file);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
