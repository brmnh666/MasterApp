package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
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

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.AddressList;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.Data2;
import com.ying.administrator.masterappdemo.entity.FAccessory;
import com.ying.administrator.masterappdemo.entity.FService;
import com.ying.administrator.masterappdemo.entity.GAccessory;
import com.ying.administrator.masterappdemo.entity.GService;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.entity.Logistics;
import com.ying.administrator.masterappdemo.entity.SAccessory;
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
import com.ying.administrator.masterappdemo.util.Glide4Engine;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.util.SingleClick;
import com.ying.administrator.masterappdemo.util.imageutil.CompressHelper;
import com.ying.administrator.masterappdemo.widget.ClearEditText;
import com.ying.administrator.masterappdemo.widget.HideSoftInputDialog;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/*预接单详情页*/
public class Order_Add_Accessories_Activity extends BaseActivity<PendingOrderPresenter, PendingOrderModel> implements PendingOrderContract.View, View.OnClickListener {
    private static final String TAG = "Order_Add_Accessories_Activity";
    @BindView(R.id.img_actionbar_return)
    ImageView mImgActionbarReturn;
    @BindView(R.id.tv_actionbar_return)
    TextView mTvActionbarReturn;
    @BindView(R.id.img_actionbar_message)
    ImageView mImgActionbarMessage;
    @BindView(R.id.actionbar_layout)
    RelativeLayout mActionbarLayout;
    @BindView(R.id.tv_order_details_receiving_time)
    TextView mTvOrderDetailsReceivingTime;
    @BindView(R.id.tv_num)
    TextView mTvNum;
    @BindView(R.id.ll_manufacturers)
    LinearLayout mLlManufacturers;
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
    @BindView(R.id.tv_recyclerView_Pre_add_service)
    RecyclerView mTvRecyclerViewPreAddService;
    @BindView(R.id.tv_order_detail_add_service)
    TextView mTvOrderDetailAddService;
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
    @BindView(R.id.ll_add_accessory)
    LinearLayout mLlAddAccessory;
    @BindView(R.id.ll_add_service)
    LinearLayout mLlAddService;
    @BindView(R.id.rb_order_details_no_for_remote_fee)
    RadioButton mRbOrderDetailsNoForRemoteFee;
    @BindView(R.id.rb_order_details_yes_for_remote_fee)
    RadioButton mRbOrderDetailsYesForRemoteFee;
    @BindView(R.id.rg_order_details_for_remote_fee)
    RadioGroup mRgOrderDetailsForRemoteFee;
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
    @BindView(R.id.ll_approve_beyond_money)
    LinearLayout mLlApproveBeyondMoney;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.et_memo)
    EditText mEtMemo;
    @BindView(R.id.tv_payment_method)
    TextView mTvPaymentMethod;

    @BindView(R.id.iv_host)
    ImageView mIvHost;
    @BindView(R.id.ll_host)
    LinearLayout mLlHost;
    @BindView(R.id.iv_accessories)
    ImageView mIvAccessories;
    @BindView(R.id.ll_accessories)
    LinearLayout mLlAccessories;
    @BindView(R.id.ll_memo)
    LinearLayout mLlMemo;
    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.tv_address_return)
    TextView mTvAddressReturn;
    @BindView(R.id.tv_modify)
    TextView mTvModify;
    private String orderID;//工单号
    private WorkOrder.DataBean data = new WorkOrder.DataBean();
    private ArrayList<GAccessory> gAccessories = new ArrayList<>();//获得工厂返回的已选配件
    private ArrayList<GService> gServices = new ArrayList<>(); //获取工厂端返回的已选服务
    private int select_state = -1;  //记录厂家寄件申请（0） 和自购件申请（1） -1为未选中
    @BindView(R.id.tv_actionbar_title)
    TextView tv_actionbar_title;
    @BindView(R.id.ll_return)
    LinearLayout ll_return;
    @BindView(R.id.tv_detail_submit)
    TextView tv_detail_submit;
    @BindView(R.id.iv_manufacturers) //厂家寄件申请
            ImageView iv_manufacturers;
    @BindView(R.id.iv_selfbuying) //自购件
            ImageView iv_selfbuying;
    /*订单属性*/
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
    @BindView(R.id.tv_order_details_state)
    TextView tv_order_details_state;
    /*订单属性*/


    /*服务和配件自定义dialog*/
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
    /*配件*/

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
    /*服务*/
    private STotalAS sTotalAS; //服务配件一起提交
    /*扫码*/
    /*震动*/
    Vibrator vibrator;
    private double Money;
    private SAccessory sAccessory;
    private int type;
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
    private HashMap<Integer, File> files_map_remote = new HashMap<>();//申请远程费图片
    private HashMap<Integer, File> accessories_picture = new HashMap<>();//申请配件图片
    private ArrayList<String> permissions;
    private String FilePath;
    private View popupWindow_view;
    private PopupWindow mPopupWindow;
    private SimpleTarget<Bitmap> simpleTarget;
    private String AccessoryMemo;
    private View customdialog_home_view;
    private AlertDialog customdialog_home_dialog;
    private TextView title;
    private TextView message;
    private Button negtive;
    private Button positive;
    private Uri uri;
    private List<Uri> mSelected;
    private EditText et_accessories_name;
    private Button btn_add1;
    private AlertDialog underReviewDialog;
    private String AddressBack;
    private String userId;
    private List<AddressList> addressList;
    private String returnAddress;
    private LinearLayout ll_host;
    private LinearLayout ll_accessories;
    private ImageView iv_host;
    private ImageView iv_accessories;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_accessories;
    }

    @Override
    protected void initData() {

    }


    @Override
    public void initView() {

        //接收传来的OrderID
        orderID = getIntent().getStringExtra("OrderID");
        type = getIntent().getIntExtra("type", 0);
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetAccountAddress(userId);
        switch (type) {
            case 1:
                tv_actionbar_title.setText("申请配件");
                mLlAddAccessory.setVisibility(View.VISIBLE);
                mLlAddService.setVisibility(View.GONE);
                mLlApproveBeyondMoney.setVisibility(View.GONE);
                break;
            case 2:
                tv_actionbar_title.setText("申请服务");
                mLlAddAccessory.setVisibility(View.GONE);
                mLlAddService.setVisibility(View.VISIBLE);
                mLlApproveBeyondMoney.setVisibility(View.GONE);
                break;
            case 3:
                tv_actionbar_title.setText("申请远程费");
                mLlAddAccessory.setVisibility(View.GONE);
                mLlAddService.setVisibility(View.GONE);
                mLlApproveBeyondMoney.setVisibility(View.VISIBLE);
                break;
            default:
                mLlAddAccessory.setVisibility(View.VISIBLE);
                mLlAddService.setVisibility(View.VISIBLE);
                mLlApproveBeyondMoney.setVisibility(View.VISIBLE);
                break;
        }
        mPre_order_add_ac_adapter = new Pre_order_Add_Ac_Adapter(R.layout.item_pre_order_add_accessories, fAcList, String.valueOf(select_state),select_state);
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
        mPresenter.GetOrderInfo(orderID);
    }

    @Override
    protected void setListener() {
        ll_return.setOnClickListener(this);
        mTvOrderDetailsAddAccessories.setOnClickListener(this);
        mTvOrderDetailAddService.setOnClickListener(this);
       /* img_express_sweep_code.setOnClickListener(this);
        tv_express_sweep_code.setOnClickListener(this);*/

        mLlManufacturers.setOnClickListener(this);
        mLlSelfbuying.setOnClickListener(this);
        mLlSelfbuyingUser.setOnClickListener(this);

        mIvSelfbuyingUser.setOnClickListener(this);
        tv_detail_submit.setOnClickListener(this);


        mIvMap1.setOnClickListener(this);
        mIvMap2.setOnClickListener(this);

        mIvHost.setOnClickListener(this);
        mIvAccessories.setOnClickListener(this);
        mTvModify.setOnClickListener(this);

        mLlOutOfServiceTv.setVisibility(View.VISIBLE);
        mLlOutOfServiceImg.setVisibility(View.VISIBLE);
        mRgOrderDetailsForRemoteFee.setVisibility(View.GONE);
    }

    @SingleClick
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
                   /* Intent intent = new Intent();
                    intent.putExtra("result", "pending_appointment");  //按了返回键返回已接待预约
                    //设置返回数据
                    Order_Add_Accessories_Activity.this.setResult(RESULT_OK,intent);*/
                Order_Add_Accessories_Activity.this.finish();
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
                mPresenter.GetFactoryService( data.getCategoryID() + "");
                break;
            /*提交工单*/
            case R.id.tv_detail_submit:
                if (files_map_remote.size() > 0) {
                    OrderByondImgPicUpload(files_map_remote);
                } else {
                    submit();
                }
                break;

            case R.id.iv_map1:
                showPopupWindow(901, 909);
                break;
            case R.id.iv_map2:
                showPopupWindow(1001, 1002);
                break;
            case R.id.iv_host:
                showPopupWindow(1101, 1102);
                break;
            case R.id.iv_accessories:
                showPopupWindow(1201, 1202);
                break;
            case R.id.tv_modify:
                Intent intent = new Intent(mActivity, ShippingAddressActivity.class);
                intent.putExtra("type", "0");
                startActivityForResult(intent, 100);
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

        ll_host = choose_accessory_view.findViewById(R.id.ll_host);
        ll_accessories = choose_accessory_view.findViewById(R.id.ll_accessories);
        iv_host = choose_accessory_view.findViewById(R.id.iv_host);
        iv_accessories = choose_accessory_view.findViewById(R.id.iv_accessories);
        ll_host.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow(1301,1302);
            }
        });
        ll_accessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow(1401,1402);
            }
        });

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
            et__service_price.setVisibility(View.GONE);
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
        Display display = Order_Add_Accessories_Activity.this.getWindowManager().getDefaultDisplay();
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
                if ("".equals(tv_accessory_name.getText())) {
                    ToastUtils.showShort("请选择配件");
                    return;
                }
                if ("".equals(num)) {
                    ToastUtils.showShort("请输入数量");
                    return;
                }
                if(accessories_picture.size()!=2){
                    ToastUtils.showShort("请添加图片");
                    return;
                }
                ApplyAccessoryphotoUpload(accessories_picture);
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
                        mfService.setFServiceID(mService.getFServiceID());
                        mfService.setFServiceName(mService.getFServiceName());
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

    public void submit() {
        gson = new Gson();
        switch (type) {
            case 1:
                AccessoryMemo = mEtMemo.getText().toString().trim();
                returnAddress = mTvAddressReturn.getText().toString().trim();
                if (select_state == -1) {
                    ToastUtils.showShort("请选择配件类型");
                } else {
                    if (mPre_order_add_ac_adapter.getData().size() == 0) {
                        ToastUtils.showShort("请添加配件");
                    }else if ("".equals(returnAddress)){
                        ToastUtils.showShort("请选择收货地址");
                    } else {
//                        if (accessories_picture.size() > 0) {
                            orderAccessoryStrBean = new FAccessory.OrderAccessoryStrBean();
                            orderAccessoryStrBean.setOrderAccessory(mPre_order_add_ac_adapter.getData());
                            orderAccessoryStrBean.setAccessoryMemo(AccessoryMemo);
                            String s1 = gson.toJson(orderAccessoryStrBean);
                            sAccessory = new SAccessory();
                            sAccessory.setOrderID(orderID);
                            sAccessory.setAccessorySequency(Integer.toString(select_state));
                            sAccessory.setOrderAccessoryStr(s1);
                            String s = gson.toJson(sAccessory);
                            Log.d("添加的配件有", s);
                            body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
                            mPresenter.AddOrderAccessory(body);
                            mPresenter.UpdateOrderAddressByOrderID(orderID,returnAddress);
//                        } else {
//                            ToastUtils.showShort("请添加配件图片");
//                        }

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
                    sService.setOrderID(orderID);
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
                mPresenter.ApplyBeyondMoney(orderID, money + "", distance + "");
                break;
            default:
                break;
        }
    }

    /*获取工单详情*/
    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {

        switch (baseResult.getStatusCode()) {

            case 200:
                data = baseResult.getData();
                mTvName.setText(data.getUserName());
                mEtMemo.setText(data.getAccessoryMemo());
                mTvPhone.setText(data.getPhone());
                mTvAddress.setText(data.getAddress());
                if (("Y").equals(data.getGuaranteeText())) {
                    mTvPaymentMethod.setText("客户付款");
                } else {
                    mTvPaymentMethod.setText("平台代付");
                }

                if ("Y".equals(data.getGuarantee())) {//保内
                    mLlManufacturers.setVisibility(View.VISIBLE);
                    mLlSelfbuying.setVisibility(View.VISIBLE);
                    mLlSelfbuyingUser.setVisibility(View.GONE);
                } else {//保外
                    mLlManufacturers.setVisibility(View.GONE);
                    mLlSelfbuying.setVisibility(View.VISIBLE);
                    mLlSelfbuyingUser.setVisibility(View.VISIBLE);
                }
                tv_order_details_state.setText(data.getStateStr());
                /*判断是选中了那个*/
                if (data.getAccessoryState() == null) {//未选择
                    select_state = -1;
                    iv_manufacturers.setSelected(false);
                    iv_selfbuying.setSelected(false);
                    mIvSelfbuyingUser.setSelected(false);
                } else if (data.getAccessoryState().equals("0")) {//工厂寄件申请
                    select_state = 0;
                    iv_manufacturers.setSelected(true);
                    iv_selfbuying.setSelected(false);
                    mIvSelfbuyingUser.setSelected(false);
                } else if ("1".equals(data.getAccessoryState())) {//师傅自购件
                    select_state = 1;
                    iv_manufacturers.setSelected(false);
                    iv_selfbuying.setSelected(true);
                    mIvSelfbuyingUser.setSelected(false);
                } else {//用户自购
                    select_state = 2;
                    iv_manufacturers.setSelected(false);
                    iv_selfbuying.setSelected(false);
                    mIvSelfbuyingUser.setSelected(true);
                }


                tv_order_details_orderid.setText(data.getOrderID());
                mTvOrderDetailsReceivingTime.setText(data.getAudDate().replace("T", " ")); //将T替换为空格
                tv_order_details_reason.setText(data.getMemo());
                tv_order_details_product_name.setText(data.getCategoryName() + "/" + data.getBrandName() + "/" + data.getSubCategoryName());

                /*获取订单的距离*/
                distance = Double.parseDouble(data.getDistance());
                if (Service_range >= distance) {
                    mTvRemoteKm.setText("0km");
                } else {
                    mTvRemoteKm.setText(String.format("%.2f", distance - Service_range) + "km");
                }

                if ("1".equals(data.getTypeID())) {//维修
                    tv_order_details_status.setText(data.getTypeName() + "/" + data.getGuaranteeText());
                    tv_order_details_status.setBackgroundResource(R.color.color_custom_01);
                } else if ("2".equals(data.getTypeID())) {
                    tv_order_details_status.setText(data.getTypeName() + "/" + data.getGuaranteeText());
                    tv_order_details_status.setBackgroundResource(R.color.color_custom_04);
                } else {
                    tv_order_details_status.setText(data.getTypeName() + "/" + data.getGuaranteeText());
                    tv_order_details_status.setBackgroundResource(R.color.color_custom_01);
                }
                tv_order_details_adress.setText(data.getAddress());


                // Log.d("====>", String.valueOf(data.getOrderAccessroyDetail().size()));
                Money = Double.parseDouble(data.getOrderMoney()) - Double.parseDouble(data.getInitMoney());
                switch (type) {
                    case 1:
//                        accessory();
                        break;
                    case 2:
//                        service();
                        break;
                    default:
                        break;
                }
                tv_total_price.setText("服务金额:¥" + gettotalPrice(mPre_order_add_ac_adapter.getData(), fList_service));
                break;
            default:
                Log.d("detail", baseResult.getData().toString());
                break;
        }
    }

    /**
     * 申请配件
     */
    public void accessory() {
        gAccessories.addAll(data.getOrderAccessroyDetail());
        if (!gAccessories.isEmpty()) {
            for (int i = 0; i < gAccessories.size(); i++) {
                mfAccessory = new FAccessory.OrderAccessoryStrBean.OrderAccessoryBean();
                mfAccessory.setFAccessoryID(String.valueOf(gAccessories.get(i).getFAccessoryID()));
                mfAccessory.setDiscountPrice(gAccessories.get(i).getDiscountPrice());
                mfAccessory.setFAccessoryName(gAccessories.get(i).getFAccessoryName());
                mfAccessory.setQuantity(String.valueOf(gAccessories.get(i).getQuantity()));
                mfAccessory.setSendState(gAccessories.get(i).getSendState());
                mfAccessory.setExpressNo(gAccessories.get(i).getExpressNo());
                mfAccessory.setState(gAccessories.get(i).getState());
                mfAccessory.setRelation("");
                mfAccessory.setPrice(gAccessories.get(i).getPrice());
                mfAccessory.setIsPay("N");
                map.put(gAccessories.get(i).getFAccessoryID() - 1, mfAccessory);
            }
            switch (select_state) {
                case 0:
                    fAcList = new ArrayList<>(map.values());
                    mPre_order_add_ac_adapter.setNewData(fAcList);
                    break;
                case 1:
                    mAcList = new ArrayList<>(map.values());
                    mPre_order_add_ac_adapter.setNewData(mAcList);
                    break;
                case 2:
                    sAcList = new ArrayList<>(map.values());
                    mPre_order_add_ac_adapter.setNewData(sAcList);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 申请服务
     */
    public void service() {
        /*服务*/
        gServices.addAll(data.getOrderServiceDetail());
        if (!gServices.isEmpty()) {
            for (int i = 0; i < gServices.size(); i++) {
                mfService = new FService.OrderServiceStrBean.OrderServiceBean();
                mfService.setFServiceID(String.valueOf(gServices.get(i).getServiceID()));
                mfService.setFServiceName(gServices.get(i).getServiceName());
                mfService.setDiscountPrice(gServices.get(i).getDiscountPrice());
                mfService.setPrice(gServices.get(i).getPrice());
                mfService.setIsPay("N");
                mfService.setRelation("");
                map_service.put(gServices.get(i).getServiceID() - 1, mfService);
            }
            fList_service = new ArrayList<>(map_service.values());
            mPre_order_Add_Service_Adapter.setNewData(fList_service);
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
//                        showToast(mActivity, "无配件，请联系管理员");
                        View under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_add_accessories, null);
                        et_accessories_name = under_review.findViewById(R.id.et_accessories_name);
                        btn_add1 = under_review.findViewById(R.id.btn_add);
                        btn_add1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tv_accessory_name.setText(et_accessories_name.getText());
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
//                Display display = mActivity.getWindowManager().getDefaultDisplay();
//                lp.width = (int) (display.getWidth() * 0.6);
//                lp.height = under_review.getHeight();
//                lp.width = 300;
//                lp.height = 400;

                        window.setAttributes(lp);
//                window.setDimAmount(0.1f);
                        window.setBackgroundDrawable(new ColorDrawable());
                        return;
                    }
                    if (ac_list.size() == 0) {
//                        showToast(mActivity, "无配件，请联系管理员");

                        View under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_add_accessories, null);
                        et_accessories_name = under_review.findViewById(R.id.et_accessories_name);
                        btn_add1 = under_review.findViewById(R.id.btn_add);
                        btn_add1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tv_accessory_name.setText(et_accessories_name.getText());
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
//                Display display = mActivity.getWindowManager().getDefaultDisplay();
//                lp.width = (int) (display.getWidth() * 0.6);
//                lp.height = under_review.getHeight();
//                lp.width = 300;
//                lp.height = 400;

                        window.setAttributes(lp);
//                window.setDimAmount(0.1f);
                        window.setBackgroundDrawable(new ColorDrawable());
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
//                    ApplyAccessoryphotoUpload(accessories_picture);
                    finish();
                } else {
                    if ("您账户余额不足，请尽快充值以免影响配件审核,充值最低金额为：200".equals(baseResult.getData().getItem2())) {
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
                        message.setText(baseResult.getData().getItem2() + "。当工单完结后返还，是否充值？");
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
                    finish();
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
    public void AddOrderAccessoryAndService(BaseResult<Data> baseResult) {

    }

    /*配件和服务都有*/
    @Override
    public void AddOrUpdateAccessoryServiceReturn(BaseResult<Data<String>> baseResult) {

        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    Order_Add_Accessories_Activity.this.finish();
                } else {

                    Toast.makeText(Order_Add_Accessories_Activity.this, (CharSequence) baseResult.getData().getItem2(), Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Override
    public void UpdateSendOrderUpdateTime(BaseResult<Data> baseResult) {

    }

    @Override
    public void GetOrderAccessoryByOrderID(BaseResult<List<GAccessory>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                break;
            default:
                break;
        }
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
                    submit();
                } else {
                    ToastUtils.showShort("远程费图片上传失败");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void ApplyAccessoryphotoUpload(BaseResult<Data2> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (ac_list.size() != 0) {
                    mfAccessory = new FAccessory.OrderAccessoryStrBean.OrderAccessoryBean();
                    mfAccessory.setPhoto1(baseResult.getData().getItem1());//配件照片
                    mfAccessory.setPhoto2(baseResult.getData().getItem2());//整机照片
                    mfAccessory.setFAccessoryID(accessory.getFAccessoryID() + "");//获取id
                    mfAccessory.setFAccessoryName(accessory.getAccessoryName()); //获取名字
                    mfAccessory.setFCategoryID(data.getSubCategoryID()); //分类id
                    mfAccessory.setQuantity(num); //数量 默认数字为1
                    mfAccessory.setPrice(accessory.getAccessoryPrice());//原价
                    mfAccessory.setDiscountPrice(accessory.getAccessoryPrice());//折扣价
                    mfAccessory.setSizeID(accessory.getSizeID());//小修中修大修
                    mfAccessory.setSendState("N");
                    mfAccessory.setRelation("");
                    mfAccessory.setState("0");
                    mfAccessory.setIsPay("N");
                    mfAccessory.setExpressNo("");
                    mfAccessory.setNeedPlatformAuth("N");
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
//                    if ("".equals(servicePrice)) {
//                        ToastUtils.showShort("请输入服务价格");
//                        return;
//                    }
                        mfAccessory.setPrice(Double.parseDouble("0.00"));
                        mfAccessory.setDiscountPrice(Double.parseDouble("0.00"));
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
                } else {
                    mfAccessory = new FAccessory.OrderAccessoryStrBean.OrderAccessoryBean();
                    mfAccessory.setPhoto1(baseResult.getData().getItem1());//配件照片
                    mfAccessory.setPhoto2(baseResult.getData().getItem2());//整机照片
                    mfAccessory.setFAccessoryID("0");//获取id
                    mfAccessory.setFAccessoryName(tv_accessory_name.getText().toString()); //获取名字
                    mfAccessory.setFCategoryID(data.getSubCategoryID()); //分类id
                    mfAccessory.setQuantity(num); //数量 默认数字为1
                    mfAccessory.setPrice(Double.valueOf("0"));//原价
                    mfAccessory.setDiscountPrice(Double.valueOf("0"));//折扣价
                    mfAccessory.setSizeID("1");//小修中修大修
                    mfAccessory.setSendState("N");
                    mfAccessory.setRelation("");
                    mfAccessory.setState("0");
                    mfAccessory.setIsPay("N");
                    mfAccessory.setExpressNo("");
                    mfAccessory.setNeedPlatformAuth("Y");
                    if (select_state == 0) {//厂家自购
                        mfAccessory.setPrice(Double.valueOf("0"));//原价
                        mfAccessory.setDiscountPrice(Double.valueOf("0"));//原价
                    } else if (select_state == 1) {//师傅自购 还要判断保内保外
                        if ("".equals(price)) {
                            ToastUtils.showShort("请输入配件价格");
                            return;
                        }
                        mfAccessory.setPrice(Double.parseDouble(price));
                        mfAccessory.setDiscountPrice(Double.parseDouble(price));
                    } else {//用户自购
//                    if ("".equals(servicePrice)) {
//                        ToastUtils.showShort("请输入服务价格");
//                        return;
//                    }
                        mfAccessory.setPrice(Double.parseDouble("0.00"));
                        mfAccessory.setDiscountPrice(Double.parseDouble("0.00"));
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
                }

                choose_accessory_dialog.dismiss();

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
                    finish();
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
                        ToastUtils.showShort(baseResult.getData().getItem2());
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void PressFactoryAccount(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void AddReturnAccessory(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void UpdateOrderState(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void ConfirmtoFreezeByOrderID(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetExpressInfo(BaseResult<Data<List<Logistics>>> baseResult) {

    }

    @Override
    public void GetAccountAddress(BaseResult<List<AddressList>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                addressList = baseResult.getData();
                if (addressList.size() != 0) {
                    for (int i = 0; i < addressList.size(); i++) {
                        if ("1".equals(addressList.get(i).getIsDefault())) {
                            AddressBack = addressList.get(i).getAddress() + "(" + addressList.get(i).getUserName() + "收)" + addressList.get(i).getPhone();
                            mTvAddressReturn.setText(AddressBack);
                            mTvModify.setText("修改地址");
                        }else {
                            AddressBack = "";
                            mTvAddressReturn.setText(AddressBack);
                            mTvModify.setText("添加地址");
                        }
                    }
                } else {
                    AddressBack = "";
                    mTvAddressReturn.setText(AddressBack);
                    mTvModify.setText("添加地址");
                }
                break;
            default:
                ToastUtils.showShort("获取失败");
                break;
        }
    }

    @Override
    public void UpdateOrderAddressByOrderID(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetFactoryAccessoryMoney(BaseResult<Data<String>> baseResult) {

    }
    //计算价格

    //计算价格
    private double gettotalPrice(List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> list,
                                 List<FService.OrderServiceStrBean.OrderServiceBean> list2) {

        double acprice = 0;
        double servicprice = 0;

        if (list == null && list2 != null) { //无配件有服务内容
            for (int i = 0; i < list2.size(); i++) {
                servicprice = servicprice + list2.get(i).getDiscountPrice();
            }
            return servicprice;
        } else if (list != null && list2 == null)//有配件没服务
        {
            for (int i = 0; i < list.size(); i++) {
                acprice = acprice + list.get(i).getDiscountPrice() * Double.parseDouble(list.get(i).getQuantity());
            }
            return acprice;
        } else if (list != null && list2 != null)//都有
        {
            for (int i = 0; i < list.size(); i++) {
                acprice = acprice + list.get(i).getDiscountPrice() * Double.parseDouble(list.get(i).getQuantity());
            }
            for (int i = 0; i < list2.size(); i++) {
                servicprice = servicprice + list2.get(i).getDiscountPrice();
            }
            return acprice + servicprice;

        } else { //都没有
            return Money;
        }


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
//                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//                    i.addCategory(Intent.CATEGORY_OPENABLE);
//                    i.setType("image/*");
//                    startActivityForResult(Intent.createChooser(i, "test"), code2);
                    Matisse.from(Order_Add_Accessories_Activity.this)
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
            }
        });
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
//            popupWindow.showAsDropDown(tv, 0, 10);
            if(code1==1301||code1==1401){
                mPopupWindow.showAtLocation(choose_accessory_view, Gravity.BOTTOM, 0, 0);
            }else{
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
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    files_map_remote.put(0, newFile);
                }
                break;
            //相册
            case 909:
                if (data != null) {
                    mSelected = Matisse.obtainResult(data);
                    if (mSelected.size() == 1) {
                        uri = mSelected.get(0);
                    }
//                    uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvMap1);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    files_map_remote.put(0, newFile);
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
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    files_map_remote.put(1, newFile);
                }

                break;
            //相册
            case 1002:
                if (data != null) {
                    mSelected = Matisse.obtainResult(data);
                    if (mSelected.size() == 1) {
                        uri = mSelected.get(0);
                    }
//                    uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvMap2);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    files_map_remote.put(1, newFile);
                }
                break;

            //拍照
            case 1101:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(mIvHost);
                    file = new File(FilePath);
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    accessories_picture.put(0, newFile);
                }

                break;
            //相册
            case 1102:
                if (data != null) {
                    mSelected = Matisse.obtainResult(data);
                    if (mSelected.size() == 1) {
                        uri = mSelected.get(0);
                    }
//                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvHost);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    accessories_picture.put(0, newFile);
                }
                break;
            //拍照
            case 1201:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(mIvAccessories);
                    file = new File(FilePath);
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    accessories_picture.put(1, newFile);
                }

                break;
            //相册
            case 1202:
                if (data != null) {
                    mSelected = Matisse.obtainResult(data);
                    if (mSelected.size() == 1) {
                        uri = mSelected.get(0);
                    }
//                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvAccessories);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    accessories_picture.put(1, newFile);
                }
                break;

            //拍照
            case 1301://配件照片
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(iv_host);
                    file = new File(FilePath);
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    accessories_picture.put(0, newFile);
                }

                break;
            //相册
            case 1302://配件照片
                if (data != null) {
                    mSelected = Matisse.obtainResult(data);
                    if (mSelected.size() == 1) {
                        uri = mSelected.get(0);
                    }
//                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_host);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    accessories_picture.put(0, newFile);
                }
                break;
            //拍照
            case 1401://整机照片
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(iv_accessories);
                    file = new File(FilePath);
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    accessories_picture.put(1, newFile);
                }

                break;
            //相册
            case 1402://整机照片
                if (data != null) {
                    mSelected = Matisse.obtainResult(data);
                    if (mSelected.size() == 1) {
                        uri = mSelected.get(0);
                    }
//                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_accessories);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                if (file != null) {
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    accessories_picture.put(1, newFile);
                }
                break;
        }

        if (requestCode == 100) {
            if (data != null) {
                AddressList address = (AddressList) data.getSerializableExtra("address");
                if (address != null) {
                    AddressBack =address.getProvince()+address.getCity()+address.getArea()+address.getDistrict()+ address.getAddress() + "(" + address.getUserName() + "收)" + address.getPhone();
                    mTvAddressReturn.setText(AddressBack);
                }
            }
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
        //  builder.addFormDataPart("img", map.get(1).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(1)));
        builder.addFormDataPart("OrderID", orderID);
        MultipartBody requestBody = builder.build();
        mPresenter.OrderByondImgPicUpload(requestBody);
    }


    public void ApplyAccessoryphotoUpload(HashMap<Integer, File> map) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("img", map.get(0).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(0)));
         builder.addFormDataPart("img", map.get(1).getName(), RequestBody.create(MediaType.parse("img/png"), map.get(1)));
//        builder.addFormDataPart("OrderID", orderID);
        MultipartBody requestBody = builder.build();
//        Log.d(TAG,"啦啦啦"+requestBody);
        mPresenter.ApplyAccessoryphotoUpload(requestBody);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
