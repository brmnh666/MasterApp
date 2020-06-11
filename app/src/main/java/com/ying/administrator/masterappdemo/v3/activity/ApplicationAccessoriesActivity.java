package com.ying.administrator.masterappdemo.v3.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.AccPicResult;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.AddressList;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.FAccessory;
import com.ying.administrator.masterappdemo.entity.FService;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.entity.SAccessory;
import com.ying.administrator.masterappdemo.entity.Service;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.ui.activity.CompleteWorkOrderActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.RechargeActivity;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Add_Service_Adapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Pre_order_Add_Ac_Adapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Pre_order_Add_Service_Adapter;
import com.ying.administrator.masterappdemo.util.imageutil.ImageCompress;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.ApplicationAccessoriesPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.ApplicationAccessoriesContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.ApplicationAccessoriesModel;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.ying.administrator.masterappdemo.util.MyUtils.showToast;

public class ApplicationAccessoriesActivity extends BaseActivity<ApplicationAccessoriesPresenter, ApplicationAccessoriesModel> implements View.OnClickListener, ApplicationAccessoriesContract.View {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
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
    @BindView(R.id.ll_add_service)
    LinearLayout mLlAddService;
    @BindView(R.id.ll_call)
    LinearLayout mLlCall;
    @BindView(R.id.tv_sure)
    TextView mTvSure;
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.iv_me_addr)
    ImageView mIvMeAddr;
    @BindView(R.id.ll_me_addr)
    LinearLayout mLlMeAddr;
    @BindView(R.id.iv_user_addr)
    ImageView mIvUserAddr;
    @BindView(R.id.ll_user_addr)
    LinearLayout mLlUserAddr;
    @BindView(R.id.ll_addr)
    LinearLayout mLlAddr;
    private String orderId;
    private int state = -1;
    private String productTypeID;
    private FAccessory.OrderAccessoryStrBean.OrderAccessoryBean mfAccessory;
    private List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> fAcList = new ArrayList<>();// 厂家配件集合
    private List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> mAcList = new ArrayList<>();// 师傅自购配件集合
    private List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> sAcList = new ArrayList<>();// 用户自购配件集合
    private List<FService.OrderServiceStrBean.OrderServiceBean> fList_service = new ArrayList<>();// 用于存放预接单页面显示的数据
    private Pre_order_Add_Ac_Adapter mPre_order_add_ac_adapter;
    private List<Service> mList_service = new ArrayList<>(); //获取传来的服务列表
    private Integer sizeId;
    private String orderMoney;
    private String quaMoney;
    private String beyondMoney;
    private Double factorymoney;
    private String beyondState;
    private Add_Service_Adapter mAdd_service_adapter;
    private View add_service_view;
    private AlertDialog add_service_dialog;
    private WindowManager.LayoutParams lp;
    private Service mService;
    private FService.OrderServiceStrBean.OrderServiceBean mfService;
    private Pre_order_Add_Service_Adapter mPre_order_add_service_adapter;
    private Gson gson;
    private String service;
    private FService.OrderServiceStrBean orderServiceStrBean;
    private SAccessory sAccessory;
    private RequestBody body;
    private Intent intent;
    private String userID;
    private String addr_me;
    private String addr_user;
    private ArrayList<String> accImglist = new ArrayList<>();
    private Map<Integer, String> successpiclist = new HashMap<>();
    private FAccessory.OrderAccessoryStrBean orderAccessoryStrBean;
    private AlertDialog customdialog_home_dialog;
    private UserInfo.UserInfoDean userInfo;
    private String terraceMoney;
    private String name;
    private String phone;
    private String addr;
    private List<AddressList> addressList=new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_application_accessories;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mLlAddr.setVisibility(View.GONE);
        mTvTitle.setText("配件与服务");
        orderId = getIntent().getStringExtra("id");
        productTypeID = getIntent().getStringExtra("SubCategoryID");
        orderMoney = getIntent().getStringExtra("OrderMoney");
        quaMoney = getIntent().getStringExtra("QuaMoney");
        beyondMoney = getIntent().getStringExtra("BeyondMoney");
        beyondState = getIntent().getStringExtra("BeyondState");
        terraceMoney = getIntent().getStringExtra("TerraceMoney");

        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        addr = getIntent().getStringExtra("addr");
        addr_user =addr+"("+name+" 收)"+phone;

        mTvMoney.setText("服务金额：¥" + terraceMoney);
        SPUtils spUtils = SPUtils.getInstance("token");
        userID = spUtils.getString("userName");
        mPresenter.GetAccountAddress(userID);
        mPresenter.GetUserInfoList(userID, "1");
        mPre_order_add_ac_adapter = new Pre_order_Add_Ac_Adapter(R.layout.item_pre_order_add_accessories, fAcList, state + "", state);
        mRecyclerViewAddAccessories.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerViewAddAccessories.setAdapter(mPre_order_add_ac_adapter);
        mPre_order_add_ac_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_accessories_delete:
                        mPre_order_add_ac_adapter.remove(position);
//                        if (mPre_order_add_ac_adapter.getData().size() > 0) {
//                            mTvSubmitAddAccessories.setBackgroundResource(R.drawable.ed_order_detail_submit);
//                            mTvSubmitAddAccessories.setTextColor(Color.WHITE);
//                        } else {
//                            mTvSubmitAddAccessories.setBackgroundResource(R.drawable.tv_order_detail_btn);
//                            mTvSubmitAddAccessories.setTextColor(Color.parseColor("#6a6a6a"));
//                        }
                        getMoney(mPre_order_add_ac_adapter.getData(), fList_service);
                        break;
                    default:
                        break;
                }
            }
        });
        mPre_order_add_service_adapter = new Pre_order_Add_Service_Adapter(R.layout.item_add_service, fList_service);
        mTvRecyclerViewPreAddService.setLayoutManager(new LinearLayoutManager(mActivity));
        mTvRecyclerViewPreAddService.setAdapter(mPre_order_add_service_adapter);
        mPre_order_add_service_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_service_delete:
                        mPre_order_add_service_adapter.remove(position);
//                        if (mPre_order_add_service_adapter.getData().size() > 0) {
//                            mTvSubmitAddService.setBackgroundResource(R.drawable.ed_order_detail_submit);
//                            mTvSubmitAddService.setTextColor(Color.WHITE);
//                        } else {
//                            mTvSubmitAddService.setBackgroundResource(R.drawable.tv_order_detail_btn);
//                            mTvSubmitAddService.setTextColor(Color.parseColor("#6a6a6a"));
//                        }
                        getMoney(mPre_order_add_ac_adapter.getData(), fList_service);
                        break;
                    default:
                        break;
                }
            }
        });

        mAdd_service_adapter = new Add_Service_Adapter(R.layout.item_addservice, mList_service);
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mLlManufacturers.setOnClickListener(this);
        mLlSelfbuying.setOnClickListener(this);
        mTvOrderDetailsAddAccessories.setOnClickListener(this);
        mTvOrderDetailAddService.setOnClickListener(this);
        mTvSure.setOnClickListener(this);
        mLlMeAddr.setOnClickListener(this);
        mLlUserAddr.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_me_addr:
                mIvMeAddr.setSelected(true);
                mIvUserAddr.setSelected(false);
                break;
            case R.id.ll_user_addr:
                mIvMeAddr.setSelected(false);
                mIvUserAddr.setSelected(true);
                break;
            case R.id.ll_manufacturers:
                state = 0;
                mIvManufacturers.setSelected(true);
                mIvSelfbuying.setSelected(false);
                mLlAddr.setVisibility(View.VISIBLE);
                mPre_order_add_ac_adapter.setNewData(fAcList);
                getMoney(mPre_order_add_ac_adapter.getData(), fList_service);
                break;
            case R.id.ll_selfbuying:
                state = 1;
                mIvManufacturers.setSelected(false);
                mIvSelfbuying.setSelected(true);
                mLlAddr.setVisibility(View.GONE);
                mPre_order_add_ac_adapter.setNewData(mAcList);
                getMoney(mPre_order_add_ac_adapter.getData(), fList_service);
                break;
            case R.id.tv_order_details_add_accessories:
                if (state == -1) {
                    ToastUtils.showShort("添加配件请选中类型");
                    return;
                } else {
                    Intent intent = new Intent(mActivity, NewAddAccessoriesActivity.class);
                    intent.putExtra("SubCategoryID", productTypeID);
                    intent.putExtra("select_state", state + "");
                    intent.putExtra("orderId", orderId);
                    startActivityForResult(intent, Config.APPLY_REQUEST);
                }
                break;
            case R.id.tv_order_detail_add_service:
                mPresenter.GetFactoryService(productTypeID + "");
                break;
            case R.id.tv_sure:

                showProgress();
                gson = new Gson();
                if (state == 0) {
                    select_state(fAcList);
                } else if (state == 1) {
                    select_state(mAcList);
                } else {
                    select_state(sAcList);
                }
                break;
        }
    }

    //厂家寄件，师傅自购，用户自购
    public void select_state(List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> list) {
        if (list.size() > 0 && mPre_order_add_service_adapter.getData().size() == 0) {
            if (state == 0) {
                if (mIvMeAddr.isSelected()){
                    if ("".equals(addr_me)) {
                        ToastUtils.showShort("请去个人中心填写收货地址");
                        hideProgress();
                        return;
                    } else {
                        mPresenter.UpdateOrderAddressByOrderID(orderId, addr_me);
                    }
                }else if (mIvUserAddr.isSelected()){
                    mPresenter.UpdateOrderAddressByOrderID(orderId, addr_user);
                }else{
                    ToastUtils.showShort("请选择寄往自己家还是用户家");
                    hideProgress();
                }
            } else {
                uploadImg(list);
            }

        } else if (list.size() == 0 && mPre_order_add_service_adapter.getData().size() > 0) {
            service = "1";
            orderServiceStrBean = new FService.OrderServiceStrBean();
            orderServiceStrBean.setOrderService(mPre_order_add_service_adapter.getData());
            String s2 = gson.toJson(orderServiceStrBean);
            sAccessory = new SAccessory();
            sAccessory.setOrderID(orderId);
            sAccessory.setMoney(Double.toString(factorymoney));
            sAccessory.setSizeID(String.valueOf(sizeId));
            sAccessory.setAccessorySequency("");
            sAccessory.setOrderAccessoryStr("");
            sAccessory.setOrderServiceStr(s2);
            String s = gson.toJson(sAccessory);
            Log.d("添加的服务有", s);
            body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
            mPresenter.AddOrderAccessoryAndService(body);
        } else if (list.size() > 0 && mPre_order_add_service_adapter.getData().size() > 0) {
            if (state == 0) {
                if (mIvMeAddr.isSelected()){
                    if ("".equals(addr_me)) {
                        ToastUtils.showShort("请去个人中心填写收货地址");
                        hideProgress();
                        return;
                    } else {
                        mPresenter.UpdateOrderAddressByOrderID(orderId, addr_me);
                    }
                }else if (mIvUserAddr.isSelected()){
                    mPresenter.UpdateOrderAddressByOrderID(orderId, addr_user);
                }else{
                    ToastUtils.showShort("请选择寄往自己家还是用户家");
                    hideProgress();
                }
            } else {
                uploadImg(list);
            }

        } else {
            intent = new Intent(mActivity, CompleteWorkOrderActivity.class);
            intent.putExtra("OrderID", orderId);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    //返回图片处理
    @SuppressLint("NewApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            /*获取返回的配件*/
            case Config.APPLY_REQUEST:
                if (resultCode == Config.APPLY_RESULT) {
                    ArrayList<Accessory> list = (ArrayList<Accessory>) data.getSerializableExtra("list_collect");
                    fAcList.clear();
                    /*商城*/
                    for (int i = 0; i < list.size(); i++) {
                        mfAccessory = new FAccessory.OrderAccessoryStrBean.OrderAccessoryBean();
                        mfAccessory.setPhoto1(list.get(i).getImg1());//配件照片
                        mfAccessory.setPhoto2(list.get(i).getImg2());//整机照片
                        mfAccessory.setFAccessoryID(list.get(i).getFAccessoryID());//获取id
                        mfAccessory.setFAccessoryName(list.get(i).getAccessoryName()); //获取名字
                        mfAccessory.setFCategoryID(list.get(i).getFCategoryID() + ""); //分类id
                        mfAccessory.setQuantity(list.get(i).getCount() + ""); //数量 默认数字为1
                        mfAccessory.setPrice(Double.valueOf("0"));//原价
                        mfAccessory.setDiscountPrice(Double.valueOf("0"));//折扣价
                        mfAccessory.setSizeID(list.get(i).getSizeID());//小修中修大修
                        mfAccessory.setSendState("N");
                        mfAccessory.setRelation("");
                        mfAccessory.setState("0");
                        mfAccessory.setIsPay("N");
                        mfAccessory.setExpressNo("");
                        mfAccessory.setNeedPlatformAuth("N");
                        if (state == 0) {//厂家自购
                            fAcList.add(mfAccessory);
                            mPre_order_add_ac_adapter.setNewData(fAcList);
                        } else if (state == 1) {
                            mfAccessory.setPrice(list.get(i).getAccessoryPrice());//原价
                            mfAccessory.setDiscountPrice(list.get(i).getAccessoryPrice());//原价
                            mAcList.add(mfAccessory);
                            mPre_order_add_ac_adapter.setNewData(mAcList);
                        } else {
                            sAcList.add(mfAccessory);
                            mPre_order_add_ac_adapter.setNewData(sAcList);
                        }
                    }
                    mPre_order_add_ac_adapter.notifyDataSetChanged();
//                    if (mPre_order_add_ac_adapter.getData().size() > 0) {
//                        mTvSubmitAddAccessories.setBackgroundResource(R.drawable.ed_order_detail_submit);
//                        mTvSubmitAddAccessories.setTextColor(Color.WHITE);
//                    } else {
//                        mTvSubmitAddAccessories.setBackgroundResource(R.drawable.tv_order_detail_btn);
//                        mTvSubmitAddAccessories.setTextColor(Color.parseColor("#6a6a6a"));
//                    }
                    getMoney(mPre_order_add_ac_adapter.getData(), fList_service);
                }
                break;
        }
    }

    /**
     * 添加完配件和服务之后返回多少钱
     */
    public void getMoney(List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> accessoryList, List<FService.OrderServiceStrBean.OrderServiceBean> serviceList) {
        double price = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < accessoryList.size(); i++) {
            list.add(Integer.parseInt(accessoryList.get(i).getSizeID()));
            price += accessoryList.get(i).getDiscountPrice();
        }
        for (int i = 0; i < serviceList.size(); i++) {
            list.add(Integer.parseInt(serviceList.get(i).getSizeID()));
        }
        if (list.size() > 0) {
            System.out.println(Collections.max(list));
            sizeId = Collections.max(list);
            if (state == 1) {
                mPresenter.GetFactoryAccessoryMoney(orderId, productTypeID, String.valueOf(Collections.max(list)), Double.toString(price));
            } else {
                mPresenter.GetFactoryAccessoryMoney(orderId, productTypeID, String.valueOf(Collections.max(list)), "0");
            }

        } else {
            Double money1 = Double.parseDouble(quaMoney);
            Double money2 = Double.parseDouble(orderMoney + "");
//            if ("3".equals(data.getTypeID())) {
//                String str = "服务金额：¥" + (money1);
//                mTvServiceAmount.setText(str);
//                mTvTotalPrice.setText(str);
////                ToastUtils.showShort(str);
//                factorymoney = money1;
//            } else {
            String str = "服务金额：¥" + (money2);
            mTvMoney.setText(str);
//            mTvServiceAmount.setText(str);
//            mTvTotalPrice.setText(str);
//                ToastUtils.showShort(str);
            factorymoney = money2;
//            }
        }
    }

    @Override
    public void GetFactoryAccessoryMoney(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    Double money1 = Double.parseDouble(quaMoney);
                    Double beyond = Double.parseDouble(beyondMoney);
                    Double money3 = Double.parseDouble(baseResult.getData().getItem2());
//                    if ("3".equals(data.getTypeID())) {
//                        String str = "";
//                        if ("-1".equals(data.getBeyondState())) {
//                            str = "服务金额：¥" + (money1);
//                            factorymoney = money1;
//                        } else {
//                            str = "服务金额：¥" + (money1 + beyond);
//                            factorymoney = money1 + beyond;
//                        }
//                        mTvServiceAmount.setText(str);
//                        mTvTotalPrice.setText(str);
////                        ToastUtils.showShort(str);
//                    } else {
                    String str = "";
//                    if ("-1".equals(beyondState)) {
                    str = "服务金额：¥" + (money3);
                    factorymoney = money3;
//                    } else {
//                        str = "服务金额：¥" + (money3 + beyond);
//                        factorymoney = money3 + beyond;
//                    }
                    mTvMoney.setText(str);
//                    mTvServiceAmount.setText(str);
//                    mTvTotalPrice.setText(str);
//                        ToastUtils.showShort(str);
//                    }

                }
                break;
        }
    }

    @Override
    public void GetFactoryService(BaseResult<GetFactoryData<Service>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if ("0".equals(baseResult.getData().getCode())) {
                    mList_service = baseResult.getData().getData();
                    mAdd_service_adapter.setNewData(mList_service);
                    if (mList_service.size() == 0) {
                        showToast(mActivity, "无服务，请联系管理员");
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

    @Override
    public void GetAccountAddress(BaseResult<List<AddressList>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData()==null){
                    return;
                }
                addressList = baseResult.getData();
                if (addressList.size() > 0) {
                    addr_me = addressList.get(0).getAddrStr() + "(" + addressList.get(0).getUserName() + " 收)" + addressList.get(0).getPhone();
                } else {
                    addr_me = "";
                }
                break;
            default:
                ToastUtils.showShort("获取失败");
                break;
        }
    }

    @Override
    public void UpdateOrderAddressByOrderID(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    gson = new Gson();
                    if (state == 0) {
//                        select_state2(fAcList);
                        uploadImg(fAcList);
                    } else if (state == 1) {
//                        select_state2(mAcList);
                        uploadImg(mAcList);
                    } else {
//                        select_state2(sAcList);
                        uploadImg(sAcList);
                    }
                } else {
                    ToastUtils.showShort("添加寄件地址失败");
                }
                break;
        }
    }

    @Override
    public void AddOrderAccessoryAndService(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    ToastUtils.showShort("提交成功");
                    if (mPre_order_add_ac_adapter.getData().size() > 0) {
                        EventBus.getDefault().post("accessories");
                    }
                    EventBus.getDefault().post(21);
                    if ("1".equals(service)) {
                        finish();
                    } else {
                        EventBus.getDefault().post(3);
                    }
                    mAcList.clear();
                    fAcList.clear();
                    sAcList.clear();
                    fList_service.clear();
                    finish();
//                    mPresenter.GetOrderInfo(OrderID);
//                    ApplyAccessoryphotoUpload(accessories_picture);
                } else {
                    if ("您账户余额不足，请尽快充值以免影响配件审核,充值最低金额为：200".equals(baseResult.getData().getItem2())) {
                        View customdialog_home_view = LayoutInflater.from(mActivity).inflate(R.layout.customdialog_home, null);
                        customdialog_home_dialog = new AlertDialog.Builder(mActivity)
                                .setView(customdialog_home_view)
                                .create();
                        customdialog_home_dialog.show();
                        TextView title = customdialog_home_view.findViewById(R.id.title);
                        TextView message = customdialog_home_view.findViewById(R.id.message);
                        Button negtive = customdialog_home_view.findViewById(R.id.negtive);
                        Button positive = customdialog_home_view.findViewById(R.id.positive);
                        title.setText("温馨提示");
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
                        showToast(mActivity, (String) baseResult.getData().getItem2());
//                        ToastUtils.showShort((String) baseResult.getData().getItem2());
                    }
                }
                hideProgress();
                break;
            default:
                break;
        }
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData() != null) {
                    userInfo = baseResult.getData().getData().get(0);
                    if (userInfo.getParentUserID() == null || "".equals(userInfo.getParentUserID())) {
                        mTvMoney.setVisibility(View.VISIBLE);
                    } else {
                        mTvMoney.setVisibility(View.GONE);
                    }
                }
                break;
        }
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
        Window window = add_service_dialog.getWindow();
        lp = window.getAttributes();
//                lp.alpha = 0.5f;
        // 也可按屏幕宽高比例进行设置宽高
        Display display = getWindowManager().getDefaultDisplay();
        lp.width = (int) (display.getWidth() * 1);
//                lp.height = under_review.getHeight();
//                lp.width = 300;
//                lp.height = (int) (display.getHeight() * 0.5);

        window.setAttributes(lp);
        TextView tv_add_service_submit = add_service_view.findViewById(R.id.tv_add_service_submit);
        RecyclerView recyclerView_custom_add_service = add_service_view.findViewById(R.id.recyclerView_custom_add_service);
        recyclerView_custom_add_service.setLayoutManager(new LinearLayoutManager(mActivity));

        recyclerView_custom_add_service.setAdapter(mAdd_service_adapter);
        mAdd_service_adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mList_service.get(position).isIschecked()) {
                    mList_service.get(position).setIschecked(false);
                } else {
                    mList_service.get(position).setIschecked(true);
                }
                mAdd_service_adapter.notifyDataSetChanged();
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
//                        mfService.setPrice(mService.getInitPrice());
//                        mfService.setDiscountPrice(mService.getInitPrice());
                        mfService.setSNeedPlatformAuth("N");
                        mfService.setState("0");
                        mfService.setIsPay("N");
                        mfService.setSizeID(mService.getSizeID());
                        mfService.setCategoryID(mService.getFCategoryID() + "");
//                        mfService.setRelation("");
                        fList_service.add(mfService);
                    }
                }
                mPre_order_add_service_adapter.setNewData(fList_service);
                add_service_dialog.dismiss();
            }
        });
        add_service_dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (mPre_order_add_service_adapter.getData().size() > 0) {
//                    mTvSubmitAddService.setBackgroundResource(R.drawable.ed_order_detail_submit);
//                    mTvSubmitAddService.setTextColor(Color.WHITE);
                    getMoney(mPre_order_add_ac_adapter.getData(), fList_service);
                } else {
//                    mTvSubmitAddService.setBackgroundResource(R.drawable.tv_order_detail_btn);
//                    mTvSubmitAddService.setTextColor(Color.BLACK);
                }
            }
        });
    }


    // <editor-fold defaultstate="collapsed" desc="上传配件图片">
    public void uploadImg(final List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> accesslist) {//accesslist配件集合，图片还未上传
        accImglist.clear();
        successpiclist.clear();
        for (int i = 0; i < accesslist.size(); i++) {
            accImglist.add(ImageCompress.compressImage(accesslist.get(i).getPhoto1(), Environment.getExternalStorageDirectory().getAbsolutePath() + "/xgy/" + System.currentTimeMillis() + ".jpg", 80));
            if (i == accesslist.size() - 1) {
                accImglist.add(ImageCompress.compressImage(accesslist.get(i).getPhoto2(), Environment.getExternalStorageDirectory().getAbsolutePath() + "/xgy/" + System.currentTimeMillis() + ".jpg", 80));
            }
        }
        for (int i = 0; i < accImglist.size(); i++) {
            File file = new File(accImglist.get(i));
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            builder.addFormDataPart("img", file.getName(), RequestBody.create(MediaType.parse("img/png"), file));
            MultipartBody requestBody = builder.build();
            //接口
            String path = Config.BASE_URL + "Upload/ApplyAccessoryphotoUpload";
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .build();
            final Request request = new Request.Builder()
                    .url(path)
                    .post(requestBody)
                    .build();
            Call call = okHttpClient.newCall(request);
            final int finalI = i;
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    hideProgress();
                    ToastUtils.showShort("配件图片上传失败，请稍后重试");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String str = response.body().string();
                    System.out.println(str);
                    Gson gson = new Gson();
                    AccPicResult result = gson.fromJson(str.replaceAll(" ", ""), AccPicResult.class);
                    if (result.getStatusCode() == 200) {
                        successpiclist.put(finalI, result.getData().getItem1());
                        if (successpiclist.size() == accImglist.size()) {
                            for (int i = 0; i < accesslist.size(); i++) {
                                accesslist.get(i).setPhoto1(successpiclist.get(i));
                                accesslist.get(i).setPhoto2(successpiclist.get(successpiclist.size() - 1));
                            }
                            select_state2(accesslist);
                        }
                    } else {
                        hideProgress();
                        ToastUtils.showShort("配件图片上传失败，请稍后重试");
                    }
                }
            });
        }
    }

    public void select_state2(List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> list) {
        if (list.size() > 0 && mPre_order_add_service_adapter.getData().size() == 0) {
            orderAccessoryStrBean = new FAccessory.OrderAccessoryStrBean();
            orderAccessoryStrBean.setOrderAccessory(list);
            orderAccessoryStrBean.setAccessoryMemo("");
            String s1 = gson.toJson(orderAccessoryStrBean);
            sAccessory = new SAccessory();
            sAccessory.setOrderID(orderId);
            sAccessory.setMoney(Double.toString(factorymoney));
            sAccessory.setSizeID(String.valueOf(sizeId));
            sAccessory.setAccessorySequency(Integer.toString(state));
            sAccessory.setOrderAccessoryStr(s1);
            sAccessory.setOrderServiceStr("");
            String s = gson.toJson(sAccessory);
            Log.d("添加的配件有", s);
            body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
            mPresenter.AddOrderAccessoryAndService(body);
        } else if (list.size() == 0 && mPre_order_add_service_adapter.getData().size() > 0) {
            service = "1";
            orderServiceStrBean = new FService.OrderServiceStrBean();
            orderServiceStrBean.setOrderService(mPre_order_add_service_adapter.getData());
            String s2 = gson.toJson(orderServiceStrBean);
            sAccessory = new SAccessory();
            sAccessory.setOrderID(orderId);
            sAccessory.setMoney(Double.toString(factorymoney));
            sAccessory.setSizeID(String.valueOf(sizeId));
            sAccessory.setAccessorySequency("");
            sAccessory.setOrderAccessoryStr("");
            sAccessory.setOrderServiceStr(s2);
            String s = gson.toJson(sAccessory);
            Log.d("添加的服务有", s);
            body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
            mPresenter.AddOrderAccessoryAndService(body);
        } else if (list.size() > 0 && mPre_order_add_service_adapter.getData().size() > 0) {
            orderAccessoryStrBean = new FAccessory.OrderAccessoryStrBean();
            orderAccessoryStrBean.setOrderAccessory(list);
            orderAccessoryStrBean.setAccessoryMemo("");
            String s1 = gson.toJson(orderAccessoryStrBean);
            orderServiceStrBean = new FService.OrderServiceStrBean();
            orderServiceStrBean.setOrderService(mPre_order_add_service_adapter.getData());
            String s2 = gson.toJson(orderServiceStrBean);
            sAccessory = new SAccessory();
            sAccessory.setOrderID(orderId);
            sAccessory.setMoney(Double.toString(factorymoney));
            sAccessory.setSizeID(String.valueOf(sizeId));
            sAccessory.setAccessorySequency(Integer.toString(state));
            sAccessory.setOrderAccessoryStr(s1);
            sAccessory.setOrderServiceStr(s2);
            String s = gson.toJson(sAccessory);
            Log.d("添加的配件有", s);
            body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
            mPresenter.AddOrderAccessoryAndService(body);
        }
    }
}
