package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.FAccessory;
import com.ying.administrator.masterappdemo.entity.FService;
import com.ying.administrator.masterappdemo.entity.GAccessory;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.entity.SService;
import com.ying.administrator.masterappdemo.entity.STotalAS;
import com.ying.administrator.masterappdemo.entity.Service;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.PendingOrderContract;
import com.ying.administrator.masterappdemo.mvp.model.PendingOrderModel;
import com.ying.administrator.masterappdemo.mvp.presenter.PendingOrderPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Ac_List_Adapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Add_Ac_Adapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Add_Service_Adapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Pre_order_Add_Ac_Adapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Pre_order_Add_Service_Adapter;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.widget.HideSoftInputDialog;
import com.ying.administrator.masterappdemo.widget.ViewExampleDialog;

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


/*预接单详情页*/
public class Order_details_Activity extends BaseActivity<PendingOrderPresenter, PendingOrderModel> implements PendingOrderContract.View, View.OnClickListener {

    @BindView(R.id.tv_num)
    TextView mTvNum;
    @BindView(R.id.ll_return_information)
    LinearLayout mll_return_information;
    @BindView(R.id.ll_service_process)
    LinearLayout mll_service_process;
    @BindView(R.id.iv_manufacturers) //厂家寄件申请
            ImageView iv_manufacturers;
    @BindView(R.id.iv_selfbuying) //自购件
            ImageView iv_selfbuying;
    @BindView(R.id.et_order_beyond_km)//超出多少千米 输入
            EditText et_order_beyond_km;
    @BindView(R.id.iv_map1)
    ImageView mIvMap1;
    @BindView(R.id.iv_map2)
    ImageView mIvMap2;
    @BindView(R.id.img_actionbar_return)
    ImageView mImgActionbarReturn;
    @BindView(R.id.tv_actionbar_return)
    TextView mTvActionbarReturn;
    @BindView(R.id.img_actionbar_message)
    ImageView mImgActionbarMessage;
    @BindView(R.id.actionbar_layout)
    RelativeLayout mActionbarLayout;
    @BindView(R.id.tv_order_details_state)
    TextView mTvOrderDetailsState;
    @BindView(R.id.tv_order_details_select_time)
    TextView mTvOrderDetailsSelectTime;
    @BindView(R.id.view_select_time_point)
    ImageView mViewSelectTimePoint;
    @BindView(R.id.rb_order_details_no_for_remote_fee)
    RadioButton mRbOrderDetailsNoForRemoteFee;
    @BindView(R.id.rb_order_details_yes_for_remote_fee)
    RadioButton mRbOrderDetailsYesForRemoteFee;
    @BindView(R.id.rg_whether_to_send_a_repair_order)
    RadioGroup mRgWhetherToSendARepairOrder;
    @BindView(R.id.iv_selfbuying_user)
    ImageView mIvSelfbuyingUser;
    @BindView(R.id.recyclerView_add_accessories)
    RecyclerView mRecyclerViewAddAccessories;
    @BindView(R.id.tv_recyclerView_Pre_add_service)
    RecyclerView mTvRecyclerViewPreAddService;
    @BindView(R.id.rg_order_details_for_remote_fee)
    RadioGroup mRgOrderDetailsForRemoteFee;
    @BindView(R.id.et_express_sweep_code)
    EditText mEtExpressSweepCode;
    @BindView(R.id.img_express_sweep_code)
    ImageView mImgExpressSweepCode;
    @BindView(R.id.tv_express_sweep_code)
    TextView mTvExpressSweepCode;
    @BindView(R.id.iv_item1)
    ImageView mIvItem1;
    @BindView(R.id.iv_item2)
    ImageView mIvItem2;
    @BindView(R.id.iv_item3)
    ImageView mIvItem3;
    @BindView(R.id.ll_scan)
    LinearLayout mLlScan;
    @BindView(R.id.ll_bar_code)
    LinearLayout mLlBarCode;
    @BindView(R.id.ll_machine)
    LinearLayout mLlMachine;
    @BindView(R.id.ll_fault_location)
    LinearLayout mLlFaultLocation;
    @BindView(R.id.ll_new_and_old_accessories)
    LinearLayout mLlNewAndOldAccessories;
    @BindView(R.id.ll_view_example_two)
    LinearLayout mLlViewExampleTwo;
    @BindView(R.id.tv_actionbar_title)
    TextView tv_actionbar_title;
    @BindView(R.id.ll_Out_of_service_tv)
    LinearLayout ll_Out_of_service_tv;
    @BindView(R.id.ll_Out_of_service_img)
    LinearLayout ll_Out_of_service_img;
    @BindView(R.id.ll_return)
    LinearLayout ll_return;
    @BindView(R.id.tv_detail_submit)
    TextView tv_detail_submit;
    @BindView(R.id.rl_select_time)
    LinearLayout rl_select_time;
    @BindView(R.id.tv_select_time)
    TextView tv_select_time;
    @BindView(R.id.tv_order_details_receiving_time)
    TextView tv_order_details_receiving_time;
    @BindView(R.id.tv_order_details_orderid)
    TextView tv_order_details_orderid;
    @BindView(R.id.tv_order_details_reason)
    TextView tv_order_details_reason;
    @BindView(R.id.tv_order_details_product_name)
    TextView tv_order_details_product_name;
    @BindView(R.id.tv_order_details_status)
    TextView tv_order_details_status;
    @BindView(R.id.tv_order_details_adress)
    TextView tv_order_details_adress;
    @BindView(R.id.tv_total_price)
    TextView tv_total_price;
    @BindView(R.id.tv_order_details_add_accessories)
    TextView tv_order_details_add_accessories;
    @BindView(R.id.tv_order_detail_add_service)
    TextView tv_order_detail_add_service;
    @BindView(R.id.iv_scan_QR)
    ImageView iv_scan_QR;
    @BindView(R.id.tv_scan_QR)
    TextView tv_scan_QR;
    @BindView(R.id.et_single_number)
    EditText et_single_number;
    @BindView(R.id.et_express_name)
    EditText et_express_name;
    @BindView(R.id.iv_bar_code)
    ImageView iv_bar_code;
    @BindView(R.id.iv_machine)//整机
            ImageView iv_machine;
    @BindView(R.id.iv_fault_location)//故障位置
            ImageView iv_fault_location;
    @BindView(R.id.iv_new_and_old_accessories)
    ImageView iv_new_and_old_accessories;
    @BindView(R.id.ll_view_example)
    LinearLayout ll_view_example;
    @BindView(R.id.iv_one)
    ImageView iv_one;
    @BindView(R.id.iv_two)
    ImageView iv_two;
    @BindView(R.id.iv_three)
    ImageView iv_three;
    @BindView(R.id.iv_four)
    ImageView iv_four;
    @BindView(R.id.ll_manufacturers)
    LinearLayout mLlManufacturers;
    @BindView(R.id.ll_selfbuying)
    LinearLayout mLlSelfbuying;
    @BindView(R.id.ll_selfbuying_user)
    LinearLayout mLlSelfbuyingUser;
    @BindView(R.id.tv_remote_km)
    TextView mTvRemoteKm;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.ll_add_accessory)
    LinearLayout mLlAddAccessory;
    @BindView(R.id.ll_add_service)
    LinearLayout mLlAddService;
    @BindView(R.id.ll_approve_beyond_money)
    LinearLayout mLlApproveBeyondMoney;
    @BindView(R.id.et_memo)
    EditText mEtMemo;

    private WorkOrder.DataBean data = new WorkOrder.DataBean();
    private List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> fAcList = new ArrayList<>();// 工厂自购件
    private List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> mAcList = new ArrayList<>();// 师傅自购件
    private List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> sAcList = new ArrayList<>();// 用户自购件
    private FAccessory fAccessory;
    private FAccessory.OrderAccessoryStrBean orderAccessoryStrBean;
    private FAccessory.OrderAccessoryStrBean.OrderAccessoryBean mfAccessory;
    private Accessory mAccessory;
    private Pre_order_Add_Ac_Adapter mPre_order_add_ac_adapter; //预接单 的adater
    private Add_Ac_Adapter mAdd_Ac_Adapter;
    private List<Service> mList_service = new ArrayList<>();
    private Map<Integer, FService.OrderServiceStrBean.OrderServiceBean> map_service;
    private List<FService.OrderServiceStrBean.OrderServiceBean> fList_service = new ArrayList<>(); //存放预接单的service
    private RecyclerView recyclerView_custom_add_service;
    private RecyclerView recyclerView_Pre_add_service;
    private Pre_order_Add_Service_Adapter mPre_order_Add_Service_Adapter; //预接单的adpter
    private Service mService;
    private FService.OrderServiceStrBean orderServiceStrBean;
    private FService.OrderServiceStrBean.OrderServiceBean mfService;
    private Add_Service_Adapter mAdd_Service_Adapter;
    private SService sService;
    private STotalAS sTotalAS; //服务配件一起提交
    private double totalPrice; // 配件价格*数量+服务价格   fList_service+
    private View popupWindow_view;
    private String FilePath;
    private PopupWindow mPopupWindow;
    private ArrayList<String> permissions;
    private double Money; //默认的钱为（OrderMoney-InitMoney）
    private String time;//最后传递的时间
    private int select_state = -1;//记录厂家寄件申请（0） 和自购件申请（1） -1为未选中
    private String orderID;//工单号
    private double Service_range = 15; //正常距离(km)
    private int remote_fee = 0; //是否需要远程费
    private ArrayList<File> files_list = new ArrayList<>();
    private HashMap<Integer, File> files_map = new HashMap<>();//返件图片
    private HashMap<Integer, File> files_map_s = new HashMap<>();//服务图片
    private HashMap<Integer, File> files_map_remote = new HashMap<>();//申请远程费图片
    private View choose_accessory_view;
    private HideSoftInputDialog choose_accessory_dialog;
    private LinearLayout ll_choose_accessory;
    private TextView tv_accessory_name;
    private EditText et_num;
    private EditText et_price;
    private EditText et__service_price;
    private Button btn_add;
    private String num;
    private String price;
    private String servicePrice;
    private PopupWindow popupWindow;
    private Accessory accessory;
    private Ac_List_Adapter ac_list_adapter;
    private List<Accessory> ac_list;
    private Window window;
    private WindowManager.LayoutParams lp;
    private View add_service_view;
    private AlertDialog add_service_dialog;
    private RecyclerView recyclerViewCustomAddService;
    private ImageView iv_check;
    private TextView tv_add_service_submit;
    private String AccessoryMemo;
    private View customdialog_home_view;
    private AlertDialog customdialog_home_dialog;
    private TextView title;
    private TextView message;
    private Button negtive;
    private Button positive;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_order_details;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void initView() {
        //接收传来的OrderID
        orderID = getIntent().getStringExtra("OrderID");
        mPresenter.GetOrderInfo(orderID);

        mPre_order_add_ac_adapter = new Pre_order_Add_Ac_Adapter(R.layout.item_pre_order_add_accessories, fAcList);
        mRecyclerViewAddAccessories.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerViewAddAccessories.setAdapter(mPre_order_add_ac_adapter);
        mPre_order_add_ac_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_accessories_delete:
                        mPre_order_add_ac_adapter.remove(position);
                        tv_total_price.setText("服务金额:¥" + gettotalPrice(mPre_order_add_ac_adapter.getData(), fList_service));
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
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_service_delete:
                        mPre_order_Add_Service_Adapter.remove(position);
                        tv_total_price.setText("服务金额:¥" + gettotalPrice(mPre_order_add_ac_adapter.getData(), fList_service));
                        break;
                    default:
                        break;
                }
            }
        });
        mAdd_Service_Adapter = new Add_Service_Adapter(R.layout.item_addservice, mList_service);
    }

    @Override
    protected void setListener() {
        ll_return.setOnClickListener(this);
        rl_select_time.setOnClickListener(this);
        tv_order_details_add_accessories.setOnClickListener(this);
        tv_order_detail_add_service.setOnClickListener(this);

        iv_scan_QR.setOnClickListener(this);
        tv_scan_QR.setOnClickListener(this);
        tv_detail_submit.setOnClickListener(this);
        mIvMap1.setOnClickListener(this);
        mIvMap2.setOnClickListener(this);


        mLlManufacturers.setOnClickListener(this);
        mLlSelfbuying.setOnClickListener(this);
        mLlSelfbuyingUser.setOnClickListener(this);

        ll_view_example.setOnClickListener(this);

        iv_bar_code.setOnClickListener(this);
        iv_machine.setOnClickListener(this);
        iv_fault_location.setOnClickListener(this);
        iv_new_and_old_accessories.setOnClickListener(this);

        iv_one.setOnClickListener(this);
        iv_two.setOnClickListener(this);
        iv_three.setOnClickListener(this);
        iv_four.setOnClickListener(this);




        /*申请远程费*/
        mRgOrderDetailsForRemoteFee.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {

                    case R.id.rb_order_details_no_for_remote_fee: //不要远程费
                        ll_Out_of_service_tv.setVisibility(View.GONE);
                        ll_Out_of_service_img.setVisibility(View.GONE);
                        remote_fee = 0;
                        break;
                    case R.id.rb_order_details_yes_for_remote_fee: //需要远程费
                        ll_Out_of_service_tv.setVisibility(View.VISIBLE);
                        ll_Out_of_service_img.setVisibility(View.VISIBLE);
                        remote_fee = 1;

                        /*获取订单的距离*/
                        double Distance = Double.parseDouble(data.getDistance());
                        if (Service_range >= Distance) {
                            mTvRemoteKm.setText("0km");
                        } else {
                            mTvRemoteKm.setText(Distance - Service_range + "km");
                        }

                        break;

                    default:
                        break;
                }

            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_manufacturers://厂家寄件申请
                if (select_state != 0) {
                    select_state = 0;
                    iv_manufacturers.setSelected(true);
                    iv_selfbuying.setSelected(false);
                    mIvSelfbuyingUser.setSelected(false);
                    mPre_order_add_ac_adapter.setNewData(fAcList);
                    mRecyclerViewAddAccessories.setVisibility(View.VISIBLE);
                    tv_total_price.setText("服务金额:¥" + gettotalPrice(mPre_order_add_ac_adapter.getData(), fList_service));
                } else {
                    select_state = -1;
                    iv_manufacturers.setSelected(false);
                    iv_selfbuying.setSelected(false);
                    mIvSelfbuyingUser.setSelected(false);
                    mRecyclerViewAddAccessories.setVisibility(View.GONE);
                    tv_total_price.setText("服务金额:¥" + gettotalPrice(null, fList_service));
                }
                break;
            case R.id.ll_selfbuying://师傅自购件
                if (select_state != 1) {
                    select_state = 1;
                    iv_manufacturers.setSelected(false);
                    iv_selfbuying.setSelected(true);
                    mIvSelfbuyingUser.setSelected(false);
                    mPre_order_add_ac_adapter.setNewData(mAcList);
                    mRecyclerViewAddAccessories.setVisibility(View.VISIBLE);
                    tv_total_price.setText("服务金额:¥" + gettotalPrice(mPre_order_add_ac_adapter.getData(), fList_service));
                } else {
                    select_state = -1;
                    iv_manufacturers.setSelected(false);
                    iv_selfbuying.setSelected(false);
                    mIvSelfbuyingUser.setSelected(false);
                    mRecyclerViewAddAccessories.setVisibility(View.GONE);
                    tv_total_price.setText("服务金额:¥" + gettotalPrice(null, fList_service));
                }
                break;
            case R.id.ll_selfbuying_user://用户自购件
                if (select_state != 2) {
                    select_state = 2;
                    iv_manufacturers.setSelected(false);
                    iv_selfbuying.setSelected(false);
                    mIvSelfbuyingUser.setSelected(true);
                    mPre_order_add_ac_adapter.setNewData(sAcList);
                    mRecyclerViewAddAccessories.setVisibility(View.VISIBLE);
                    tv_total_price.setText("服务金额:¥" + gettotalPrice(mPre_order_add_ac_adapter.getData(), fList_service));
                } else {
                    select_state = -1;
                    iv_manufacturers.setSelected(false);
                    iv_selfbuying.setSelected(false);
                    mIvSelfbuyingUser.setSelected(false);
                    mRecyclerViewAddAccessories.setVisibility(View.GONE);
                    tv_total_price.setText("服务金额:¥" + gettotalPrice(null, fList_service));
                }
                break;
            case R.id.ll_return:
                Intent intent = new Intent();
                intent.putExtra("result", "pending_appointment");  //按了返回键返回已接待预约
                //设置返回数据
                Order_details_Activity.this.setResult(RESULT_OK, intent);
                Order_details_Activity.this.finish();
                break;
            case R.id.rl_select_time:
                chooseTime();
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

            case R.id.iv_scan_QR:
            case R.id.tv_scan_QR:
                scan();
                break;
            case R.id.tv_detail_submit:
                if (files_map_remote.size() > 0) {
                    OrderByondImgPicUpload(files_map_remote);
                } else {
                    submit();
                }
                break;
            //添加维修图片
            case R.id.ll_view_example:  //查看示例
                seeExample();
                break;
            case R.id.iv_bar_code:
                showPopupWindow(101, 102);
                break;
            case R.id.iv_machine:
                showPopupWindow(201, 202);
                break;
            case R.id.iv_fault_location:
                showPopupWindow(301, 303);
                break;
            case R.id.iv_new_and_old_accessories:
                showPopupWindow(401, 404);
                break;

            /*添加服务过程*/
            case R.id.iv_one:
                showPopupWindow(501, 505);
                break;
            case R.id.iv_two:
                showPopupWindow(601, 606);
                break;
            case R.id.iv_three:
                showPopupWindow(701, 707);
                break;
            case R.id.iv_four:
                showPopupWindow(801, 808);
                break;
            case R.id.iv_map1: //选中地图1
                showPopupWindow(901, 909);
                break;
            case R.id.iv_map2: //选中地图2
                showPopupWindow(1001, 1002);
                break;
            default:
                break;

        }


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
        Display display = Order_details_Activity.this.getWindowManager().getDefaultDisplay();
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
                tv_total_price.setText("服务金额:¥" + gettotalPrice(mPre_order_add_ac_adapter.getData(), fList_service));
                choose_accessory_dialog.dismiss();
            }
        });
        choose_accessory_dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                accessory = null;
                num = "";
                price = "";
                servicePrice = "";
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
                if (select_state == -1) {
                    tv_total_price.setText("服务金额:¥" + gettotalPrice(null, fList_service));
                } else {
                    tv_total_price.setText("服务金额:¥" + gettotalPrice(mPre_order_add_ac_adapter.getData(), fList_service));
                }
                add_service_dialog.dismiss();
            }
        });
    }

    /**
     * 选择上门时间
     */
    public void chooseTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String format1 = format.format(date);

        TimeSelector timeSelector = new TimeSelector(Order_details_Activity.this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
                tv_select_time.setText(time);

            }
        }, format1, "2022-1-1 24:00");

        timeSelector.setTitle("请选择上门时间");
        timeSelector.show();
    }

    /**
     * 扫描二维码
     */
    public void scan() {
        IntentIntegrator integrator = new IntentIntegrator(Order_details_Activity.this);
        // 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setCaptureActivity(ScanActivity.class); //设置打开摄像头的Activity
        integrator.setPrompt("请扫描快递码"); //底部的提示文字，设为""可以置空
        integrator.setCameraId(0); //前置或者后置摄像头
        integrator.setBeepEnabled(true); //扫描成功的「哔哔」声，默认开启
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    /**
     * 提交
     */
    public void submit() {
        AccessoryMemo = mEtMemo.getText().toString().trim();
        sTotalAS = new STotalAS();

        sTotalAS.setOrderID(orderID);

        String selecttime = tv_select_time.getText().toString();//获得提交时间
        //添加时间
        if (!selecttime.equals("")) {
            StringBuilder stringBuilder = new StringBuilder(selecttime);
            time = "" + stringBuilder.replace(10, 11, "T"); //增加"T"
            sTotalAS.setUpdateDate(time);
        } else {
            sTotalAS.setUpdateDate("");
        }




        /*添加远程费*/

        if (remote_fee == 0) {//没有选择远程费
            sTotalAS.setBeyondMoney(0);
            sTotalAS.setBeyondDistance("0");
        } else {
            if (et_order_beyond_km.getText().toString().equals("")) {//什么都不输入

                double Distance = Double.parseDouble(data.getDistance());
                double money = Distance - Service_range;
                if (money < 0) {
                    money = 0;
                }
                sTotalAS.setBeyondMoney(money);
                sTotalAS.setBeyondDistance(String.valueOf(money));

            } else {
                //用户自己输入
                sTotalAS.setBeyondMoney(Double.parseDouble(et_order_beyond_km.getText().toString()));
                sTotalAS.setBeyondDistance(et_order_beyond_km.getText().toString());

            }

        }


        //添加配件
        /*添加厂家还是自购件类型*/
        if (select_state == -1) {    /*厂家0  师傅自购 1 用户自购 2*/
            sTotalAS.setAccessorySequency("");
            sTotalAS.setOrderAccessoryStr("");
        } else if (select_state == 0) {
            sTotalAS.setAccessorySequency("0");//厂家
            orderAccessoryStrBean = new FAccessory.OrderAccessoryStrBean();
            orderAccessoryStrBean.setOrderAccessory(fAcList); //配件
            orderAccessoryStrBean.setAccessoryMemo(AccessoryMemo);
            Gson gson = new Gson();
            String s1 = gson.toJson(orderAccessoryStrBean);
            sTotalAS.setOrderAccessoryStr(s1);
        } else if (select_state == 1) {
            sTotalAS.setAccessorySequency("1");//师傅自购
            orderAccessoryStrBean = new FAccessory.OrderAccessoryStrBean();
            orderAccessoryStrBean.setOrderAccessory(mAcList); //配件
            orderAccessoryStrBean.setAccessoryMemo(AccessoryMemo);
            Gson gson = new Gson();
            String s1 = gson.toJson(orderAccessoryStrBean);
            sTotalAS.setOrderAccessoryStr(s1);
        } else {
            sTotalAS.setAccessorySequency("2");//用户自购件
            orderAccessoryStrBean = new FAccessory.OrderAccessoryStrBean();
            orderAccessoryStrBean.setOrderAccessory(sAcList); //配件
            orderAccessoryStrBean.setAccessoryMemo(AccessoryMemo);
            Gson gson = new Gson();
            String s1 = gson.toJson(orderAccessoryStrBean);
            sTotalAS.setOrderAccessoryStr(s1);
        }
        if (fList_service.isEmpty()) {
            sTotalAS.setOrderServiceStr("");
        } else {
            //*添加服务*//
            orderServiceStrBean = new FService.OrderServiceStrBean();
            orderServiceStrBean.setOrderService(fList_service);//服务
            Gson gson = new Gson();
            String s2 = gson.toJson(orderServiceStrBean);
            sTotalAS.setOrderServiceStr(s2);
        }

        // sTotalAS.setOrderAccessoryStr(s1);
        // sTotalAS.setOrderServiceStr(s2);
        Gson gson = new Gson();
        String s3 = gson.toJson(sTotalAS);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s3);
        mPresenter.AddOrUpdateAccessoryServiceReturn(body);
    }

    /**
     * 查看示例
     */
    public void seeExample() {
        final ViewExampleDialog viewExampleDialog = new ViewExampleDialog(mActivity);
        viewExampleDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        viewExampleDialog.setNoOnclickListener("取消", new ViewExampleDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                viewExampleDialog.dismiss();
            }
        });
        viewExampleDialog.show();
        Window window = viewExampleDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        Display d = window.getWindowManager().getDefaultDisplay();
        wlp.height = d.getHeight();
        wlp.width = d.getWidth();
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
    }

    /**
     * 获取工单详情
     *
     * @param baseResult
     */
    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {

        switch (baseResult.getStatusCode()) {

            case 200:
                data = baseResult.getData();
                // Log.d("getOrderIDgetOrderID",data.getOrderID()+" "+data.getMemo()+" "+data.getBrandName());
                mTvOrderDetailsState.setText(data.getStateStr());
                mTvName.setText(data.getUserName());
                mTvPhone.setText(data.getPhone());
                mTvAddress.setText(data.getAddress());
                tv_order_details_orderid.setText(data.getOrderID());
                tv_order_details_receiving_time.setText(data.getAudDate().replace("T", " ")); //将T替换为空格
                tv_order_details_reason.setText(data.getMemo());
                tv_order_details_product_name.setText(data.getCategoryName() + "/" + data.getBrandName() + "/" + data.getSubCategoryName());

                if (data.getTypeID() == 1) {//维修
                    tv_order_details_status.setText(data.getTypeName() + "/" + data.getGuaranteeText());
                    tv_order_details_status.setBackgroundResource(R.color.color_custom_01);
                 /*   mll_return_information.setVisibility(View.VISIBLE);
                    mll_service_process.setVisibility(View.GONE);*/
                } else if (data.getTypeID() == 2) {
                    tv_order_details_status.setText(data.getTypeName() + "/" + data.getGuaranteeText());
                    tv_order_details_status.setBackgroundResource(R.color.color_custom_04);
                   /* mll_return_information.setVisibility(View.GONE);
                    mll_service_process.setVisibility(View.VISIBLE);*/
                } else {
                    tv_order_details_status.setText(data.getTypeName() + "/" + data.getGuaranteeText());
                    tv_order_details_status.setBackgroundResource(R.color.color_custom_04);
                }
                tv_order_details_adress.setText(data.getAddress());
                mTvNum.setText("数量：" + data.getNum() + "台");
                Money = data.getOrderMoney() - data.getInitMoney();
                tv_total_price.setText("服务金额:¥" + gettotalPrice(mPre_order_add_ac_adapter.getData(), fList_service));
                if ("Y".equals(data.getGuarantee())) {//保内
                    mLlManufacturers.setVisibility(View.VISIBLE);
                    mLlSelfbuying.setVisibility(View.VISIBLE);
                    mLlSelfbuyingUser.setVisibility(View.GONE);
                } else {//保外
                    mLlManufacturers.setVisibility(View.GONE);
                    mLlSelfbuying.setVisibility(View.VISIBLE);
                    mLlSelfbuyingUser.setVisibility(View.VISIBLE);
                }

                break;

            default:
                Log.d("detail", baseResult.getData().toString());
                //  data=null;
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
                        MyUtils.showToast(mActivity,"无配件，请联系管理员");
                        return;
                    }
                    if (ac_list.size() == 0) {
                        MyUtils.showToast(mActivity,"无配件，请联系管理员");
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

    /**
     * 获取工厂服务
     *
     * @param baseResult
     */
    @Override
    public void GetFactoryService(BaseResult<GetFactoryData<Service>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if ("0".equals(baseResult.getData().getCode())) {
                    mList_service = baseResult.getData().getData();
                    mAdd_Service_Adapter.setNewData(mList_service);
                    if (mList_service.size()==0){
                        MyUtils.showToast(mActivity,"无服务，请联系管理员");
                    }else{
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

    }

    /*提交服务信息*/
    @Override
    public void AddOrderService(BaseResult<Data> baseResult) {


    }

    /**
     * 预接单提交信息总接口
     *
     * @param baseResult
     */
    @Override
    public void AddOrUpdateAccessoryServiceReturn(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (!baseResult.getData().isItem1()) {

                    if ("支付出错，添加失败".equals(baseResult.getData().getItem2())){
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
                                startActivity(new Intent(mActivity,RechargeActivity.class));
                                customdialog_home_dialog.dismiss();
                            }
                        });
                    }else{
                        ToastUtils.showShort( baseResult.getData().getItem2());
                    }
                } else {
                    //Intent intent=new Intent();
                    //intent.putExtra("successposition",getIntent().getIntExtra("successposition",0));
                    if (baseResult.getData().getItem2().equals("操作成功2")) {//到服务中去
                        setResult(10001);
                    } else if (baseResult.getData().getItem2().equals("操作成功1")) {//到返件中去
                        setResult(10002);
                    }


                    Order_details_Activity.this.finish();
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
    public void GetOrderAccessoryByOrderID(BaseResult<List<GAccessory>> baseResult) {

    }

    /*上传服务图片*/
    @Override
    public void ServiceOrderPicUpload(BaseResult<Data<String>> baseResult) {

    }

    /*上传返件图片*/
    @Override
    public void ReuturnAccessoryPicUpload(BaseResult<Data<String>> baseResult) {


    }

    @Override
    public void FinishOrderPicUpload(BaseResult<Data<String>> baseResult) {

    }

    /*添加远程费图片*/
    @Override
    public void OrderByondImgPicUpload(BaseResult<Data<String>> baseResult) {


    }

    /*申请远程费*/
    @Override
    public void ApplyBeyondMoney(BaseResult<Data<String>> baseResult) {


    }

    @Override
    public void PressFactoryAccount(BaseResult<Data<String>> baseResult) {

    }

    //计算价格
    private double gettotalPrice(List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> list,
                                 List<FService.OrderServiceStrBean.OrderServiceBean> list2) {

        double acprice = 0;
        double servicprice = 0;

        if (list == null && list2 != null) { //无配件有服务内容
            for (int i = 0; i < list2.size(); i++) {
                servicprice = servicprice + list2.get(i).getDiscountPrice();
            }
            return servicprice + Money;
        } else if (list != null && list2 == null)//有配件没服务
        {
            for (int i = 0; i < list.size(); i++) {
                acprice = acprice + list.get(i).getDiscountPrice() * Double.parseDouble(list.get(i).getQuantity());
            }
            return acprice + Money;
        } else if (list != null && list2 != null)//都有
        {
            for (int i = 0; i < list.size(); i++) {
                acprice = acprice + list.get(i).getDiscountPrice() * Double.parseDouble(list.get(i).getQuantity());
            }
            for (int i = 0; i < list2.size(); i++) {
                servicprice = servicprice + list2.get(i).getDiscountPrice();
            }
            return acprice + servicprice + Money;

        } else { //都没有
            return Money;
        }


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
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            String result = scanResult.getContents();
            et_single_number.setText("单号:" + result);
        }
        File file = null;
        switch (requestCode) {
            //拍照     前四个是维修
            case 101:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(iv_bar_code);
                    file = new File(FilePath);
                }
                if (file != null) {
                    // ReuturnAccessoryPicUpload(file,0);
                    // files_list.add(file);
                    files_map.put(0, file);

                }

                break;
            //相册
            case 102:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_bar_code);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    // ReuturnAccessoryPicUpload(file,0);
                    // files_list.add(file);
                    files_map.put(0, file);
                }
                break;
            //拍照
            case 201:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(iv_machine);
                    file = new File(FilePath);
                }
                if (file != null) {
                    //   ReuturnAccessoryPicUpload(file,1);
                    //  files_list.add(file);
                    files_map.put(1, file);
                }
                break;
            //相册
            case 202:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_machine);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    //  ReuturnAccessoryPicUpload(file,1);
                    //  files_list.add(file);
                    files_map.put(1, file);
                }
                break;
            //拍照
            case 301:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(iv_fault_location);
                    file = new File(FilePath);
                }
                if (file != null) {
                    // ReuturnAccessoryPicUpload(file,2);
                    // files_list.add(file);
                    files_map.put(2, file);
                }
                break;
            //相册
            case 303:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_fault_location);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    //  ReuturnAccessoryPicUpload(file,2);
                    // files_list.add(file);
                    files_map.put(2, file);
                }
                break;
            //拍照
            case 401:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(iv_new_and_old_accessories);
                    file = new File(FilePath);
                }
                if (file != null) {
                    // ReuturnAccessoryPicUpload(file,3);
                    //    files_list.add(file);
                    files_map.put(3, file);
                }
                break;
            //相册
            case 404:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_new_and_old_accessories);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    //  ReuturnAccessoryPicUpload(file,3);
                    //files_list.add(file);
                    files_map.put(3, file);
                }
                break;
            //拍照
            case 501:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(iv_one);
                    file = new File(FilePath);
                }
                if (file != null) {
                    files_map_s.put(0, file);
                }

                break;
            //相册
            case 505:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_one);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    files_map_s.put(0, file);
                }
                break;


            //拍照
            case 601:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(iv_two);
                    file = new File(FilePath);
                }
                if (file != null) {
                    files_map_s.put(1, file);
                }

                break;
            //相册
            case 606:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_two);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    files_map_s.put(1, file);
                }
                break;

            //拍照
            case 701:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(iv_three);
                    file = new File(FilePath);
                }
                if (file != null) {
                    files_map_s.put(2, file);
                }

                break;
            //相册
            case 707:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_three);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    files_map_s.put(2, file);
                }
                break;


            //拍照
            case 801:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(iv_four);
                    file = new File(FilePath);
                }
                if (file != null) {
                    files_map_s.put(3, file);
                }
                break;
            //相册
            case 808:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_four);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    files_map_s.put(3, file);
                }
                break;


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
     * 返件图片
     *
     * @param map
     */
    public void ReuturnAccessoryPicUpload(HashMap<Integer, File> map) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("img", map.get(0).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(0)));
        builder.addFormDataPart("img", map.get(1).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(1)));
        builder.addFormDataPart("img", map.get(2).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(2)));
        builder.addFormDataPart("img", map.get(3).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(3)));
        builder.addFormDataPart("OrderID", orderID);
        MultipartBody requestBody = builder.build();
        mPresenter.ReuturnAccessoryPicUpload(requestBody);
    }

    /**
     * 安装服务图片
     *
     * @param map
     */
    public void ServiceOrderPicUpload(HashMap<Integer, File> map) {

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("img", map.get(0).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(0)));
        builder.addFormDataPart("img", map.get(1).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(1)));
        builder.addFormDataPart("img", map.get(2).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(2)));
        builder.addFormDataPart("img", map.get(3).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(3)));
        builder.addFormDataPart("OrderID", orderID);
        MultipartBody requestBody = builder.build();
        mPresenter.ServiceOrderPicUpload(requestBody);


    }

    /**
     * 添加远程图片
     *
     * @param map
     */
    public void OrderByondImgPicUpload(HashMap<Integer, File> map) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("img", map.get(0).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(0)));
        builder.addFormDataPart("img", map.get(1).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(1)));
        builder.addFormDataPart("OrderID", orderID);
        MultipartBody requestBody = builder.build();
        mPresenter.OrderByondImgPicUpload(requestBody);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        mPresenter.GetOrderInfo(orderID);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
