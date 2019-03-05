package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.entity.FAccessory;
import com.ying.administrator.masterappdemo.entity.GetFactorySeviceData;
import com.ying.administrator.masterappdemo.entity.SAccessory;
import com.ying.administrator.masterappdemo.entity.SService;
import com.ying.administrator.masterappdemo.entity.STotalAS;
import com.ying.administrator.masterappdemo.entity.Service;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.PendingOrderContract;
import com.ying.administrator.masterappdemo.mvp.model.PendingOrderModel;
import com.ying.administrator.masterappdemo.mvp.presenter.PendingOrderPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Add_Ac_Adapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Add_Service_Adapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Pre_order_Add_Ac_Adapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Pre_order_Add_Service_Adapter;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.widget.CustomDialog_Add_Accessory;
import com.ying.administrator.masterappdemo.widget.CustomDialog_Add_Service;
import com.ying.administrator.masterappdemo.widget.ViewExampleDialog;
import com.ying.administrator.masterappdemo.widget.adderView;

import org.feezu.liuli.timeselector.TimeSelector;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.MediaType;
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
    @BindView(R.id.tv_manufacturers)//厂家寄件申请
    TextView tv_manufacturers;
    @BindView(R.id.tv_selfbuying)//自购件
    TextView tv_selfbuying;
     /*申请远程费*/
    @BindView(R.id.tv_remote_km) //超过多少千米显示
    TextView tv_remote_km;
    @BindView(R.id.et_order_beyond_km)//超出多少千米 输入
    EditText et_order_beyond_km;
    @BindView(R.id.iv_map1)
    ImageView mIvMap1;
    @BindView(R.id.iv_map2)
    ImageView mIvMap2;
    /*申请远程费*/


    private RadioGroup rg_order_details_for_remote_fee;
    private WorkOrder.DataBean data = new WorkOrder.DataBean();
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

    /*订单属性*/

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

    /*订单属性*/



    /*服务和配件自定义dialog*/
    private CustomDialog_Add_Accessory customDialog_add_accessory;
    private CustomDialog_Add_Service customDialog_add_service;
    /*服务和配件自定义dialog*/


    /*  配件*/
    @BindView(R.id.tv_order_details_add_accessories)
    TextView tv_order_details_add_accessories;
    private List<Accessory> mList;   //存放返回的list
    private Map<Integer, FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> map; //用于存放dialog里选择的配件
    private List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> fAcList=new ArrayList<>();// 用于存放预接单页面显示的数据
    private FAccessory fAccessory;
    private SAccessory sAccessory;
    private FAccessory.OrderAccessoryStrBean orderAccessoryStrBean;
    private FAccessory.OrderAccessoryStrBean.OrderAccessoryBean mfAccessory;
    private Accessory mAccessory;
    private RecyclerView recyclerView_custom_add_accessory;
    private RecyclerView recyclerView_Pre_add_accessories; //预接单的  RecyclerView
    private Pre_order_Add_Ac_Adapter mPre_order_add_ac_adapter; //预接单 的adater
    private Add_Ac_Adapter mAdd_Ac_Adapter;
    /*配件*/

    /*服务*/
    @BindView(R.id.tv_order_detail_add_service)
    TextView tv_order_detail_add_service;
    private List<Service> mList_service;
    private Map<Integer, FService.OrderServiceStrBean.OrderServiceBean> map_service;
    private List<FService.OrderServiceStrBean.OrderServiceBean> fList_service=new ArrayList<>(); //存放预接单的service
    private RecyclerView recyclerView_custom_add_service;
    private RecyclerView recyclerView_Pre_add_service;


    private Pre_order_Add_Service_Adapter mPre_order_Add_Service_Adapter; //预接单的adpter
    private Service mService;
    private FService.OrderServiceStrBean orderServiceStrBean;
    private FService.OrderServiceStrBean.OrderServiceBean mfService;
    private Add_Service_Adapter mAdd_Service_Adapter;
    private SService sService;
    /*服务*/
     private STotalAS sTotalAS; //服务配件一起提交
    /*扫码*/


    @BindView(R.id.iv_scan_QR)
    ImageView iv_scan_QR;
    @BindView(R.id.tv_scan_QR)
    TextView tv_scan_QR;

    @BindView(R.id.et_single_number)
    EditText et_single_number;
    @BindView(R.id.et_express_name)
    EditText et_express_name;
    /*扫码*/


    /*服务图片和返件图片*/
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



    /*震动*/
    private Vibrator vibrator;
    private double totalPrice; // 配件价格*数量+服务价格   fList_service+
    private View popupWindow_view;
    private String FilePath;
    private PopupWindow mPopupWindow;
    private ArrayList<String> permissions;
    private double Money; //默认的钱为（OrderMoney-InitMoney）
    private String time;//最后传递的时间
    private int select_state=0;//记录厂家寄件申请（1） 和自购件申请（2） 0为未选中
    private String orderID;//工单号
    private double Service_range=15; //正常距离(km)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initValidata();
        //mPresenter.GetOrderInfo();


    }


    @Override
    protected int setLayoutId() {
        return R.layout.activity_order_details;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initView() {
        mList = new ArrayList<>();
        map = new HashMap<>();
        //fList=new ArrayList<>();
        mList_service = new ArrayList<>();
        map_service = new HashMap<>();


        customDialog_add_accessory = new CustomDialog_Add_Accessory(mActivity);
        customDialog_add_service = new CustomDialog_Add_Service(mActivity);
        rg_order_details_for_remote_fee = findViewById(R.id.rg_order_details_for_remote_fee);
        recyclerView_Pre_add_accessories = findViewById(R.id.recyclerView_add_accessories); //预接单recyclerview
        recyclerView_Pre_add_service = findViewById(R.id.tv_recyclerView_Pre_add_service);//预接单recyclerview
        vibrator = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
        //接收传来的OrderID
        orderID = getIntent().getStringExtra("OrderID");
        mPresenter.GetOrderInfo(orderID);
        mPresenter.GetFactoryAccessory();
        mPresenter.GetFactoryService();

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


        iv_manufacturers.setOnClickListener(this);
        iv_selfbuying.setOnClickListener(this);
        tv_manufacturers.setOnClickListener(this);
        tv_selfbuying.setOnClickListener(this);
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
        rg_order_details_for_remote_fee.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {

                    case R.id.rb_order_details_no_for_remote_fee:
                        ll_Out_of_service_tv.setVisibility(View.GONE);
                        ll_Out_of_service_img.setVisibility(View.GONE);
                        break;
                    case R.id.rb_order_details_yes_for_remote_fee:
                        ll_Out_of_service_tv.setVisibility(View.VISIBLE);
                        ll_Out_of_service_img.setVisibility(View.VISIBLE);


                        /*获取订单的距离*/
                        double Distance = Double.parseDouble(data.getDistance());
                        if (Service_range>=Distance){
                            tv_remote_km.setText("0km");
                        }else {
                            tv_remote_km.setText(Distance-Service_range+"km");
                        }

                        break;

                    default:
                        break;
                }

            }
        });


    }


    public void initValidata() {
        tv_actionbar_title.setText("预接单");
        rl_select_time.setOnClickListener(this);




    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.iv_manufacturers://厂家寄件申请
            case R.id.tv_manufacturers:
                if (iv_manufacturers.isSelected()){ //如果是选中状态

                    if (!fAcList.isEmpty()){ //取消时如果还有数据取消不了
                     Toast.makeText(this,"菜单仍有配件",Toast.LENGTH_SHORT).show();
                    }else {
                        iv_manufacturers.setSelected(false);
                        iv_selfbuying.setSelected(false);
                        Log.d("====>","取消了厂家寄件申请");
                        select_state=0;
                    }

                }else {
                    iv_manufacturers.setSelected(true);
                    iv_selfbuying.setSelected(false);
                    Log.d("====>","选中了厂家寄件申请");
                    select_state=1;
                }

                break;
            case R.id. iv_selfbuying://自购件
            case R.id.tv_selfbuying:
                if (iv_selfbuying.isSelected()){

                    if (!fAcList.isEmpty()){
                        Toast.makeText(this,"菜单仍有配件",Toast.LENGTH_SHORT).show();
                    }else {
                        iv_selfbuying.setSelected(false);
                        iv_manufacturers.setSelected(false);
                        Log.d("====>","取消了自购件申请");
                        select_state=0;
                    }


                } else {
                    iv_selfbuying.setSelected(true);
                    iv_manufacturers.setSelected(false);
                    Log.d("====>","选中了自购件申请");
                    select_state=2;
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

                break;


            case R.id.tv_order_details_add_accessories: //添加配件

                if (select_state==0){
                    Toast.makeText(this,"添加配件请选中类型",Toast.LENGTH_SHORT).show();
                }else {
                    customDialog_add_accessory.getWindow().setBackgroundDrawableResource(R.color.transparent);
                    customDialog_add_accessory.show();
                    // 设置宽度为屏宽、靠近屏幕底部。
                    Window window = customDialog_add_accessory.getWindow();
                    //最重要的一句话，一定要加上！要不然怎么设置都不行！
                    // window.setBackgroundDrawableResource(android.R.color.transparent);
                    WindowManager.LayoutParams wlp = window.getAttributes();
                    Display d = window.getWindowManager().getDefaultDisplay();
                    //获取屏幕宽
                    wlp.height = (d.getHeight());
                    wlp.width = (d.getWidth());
                    //宽度按屏幕大小的百分比设置，这里我设置的是全屏显示
                    wlp.gravity = Gravity.CENTER;
/*
                    if (wlp.gravity == Gravity.BOTTOM)
                        wlp.y = 0;
                    //如果是底部显示，则距离底部的距离是0*/
                    window.setAttributes(wlp);

                    /*选择配件*/

                    recyclerView_custom_add_accessory = customDialog_add_accessory.findViewById(R.id.recyclerView_custom_add_accessory);
                    recyclerView_custom_add_accessory.setLayoutManager(new LinearLayoutManager(mActivity));
                    mAdd_Ac_Adapter = new Add_Ac_Adapter(R.layout.item_addaccessory, mList);
                    recyclerView_custom_add_accessory.setAdapter(mAdd_Ac_Adapter);

                    mAdd_Ac_Adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @SuppressLint("MissingPermission")
                        @Override
                        public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                            ImageView img_ac_select = (ImageView) adapter.getViewByPosition(recyclerView_custom_add_accessory, position, R.id.img_ac_select); //选中图片
                            ImageView img_ac_unselect = (ImageView) adapter.getViewByPosition(recyclerView_custom_add_accessory, position, R.id.img_ac_unselect);//未选中图片
                            final adderView adderView = (adderView) adapter.getViewByPosition(recyclerView_custom_add_accessory, position, R.id.adderView);
                            //进入添加配件dialog 判断是否已经选了配件  有数据说明是第二次进入 没数据说明是第一次进入


                            switch (view.getId()) {
                                case R.id.img_ac_unselect:
                                case R.id.img_ac_select:
                                case R.id.tv_accessory_name:
                                    vibrator.vibrate(50);

                                    if (((Accessory) (adapter.getData().get(position))).isIscheck() == false) { //如果是为选中的状态点击  变为红色 选中状态 出现 数量选择器

                                        //viewadd.setVisibility(View.VISIBLE); //数量选择器出现
                                        adderView.setVisibility(View.VISIBLE);
                                        img_ac_unselect.setVisibility(View.INVISIBLE);
                                        img_ac_select.setVisibility(View.VISIBLE);
                                        //ischeck[position]=true;
                                        mList.get(position).setIscheck(true);
                                        //没选选择默认数量为1
                                        mAccessory = (Accessory) adapter.getItem(position);
                                        mfAccessory = new FAccessory.OrderAccessoryStrBean.OrderAccessoryBean();
                                        mfAccessory.setFAccessoryName(mAccessory.getAccessoryName());
                                        mfAccessory.setQuantity("1"); //默认数字为1
                                        mfAccessory.setDiscountPrice(mAccessory.getAccessoryPrice());
                                        mList.get(position).setCheckedcount(1);
                                        map.put(position, mfAccessory);
                                        adderView.setVisibility(View.VISIBLE);
                                        img_ac_unselect.setVisibility(View.INVISIBLE);
                                        img_ac_select.setVisibility(View.VISIBLE);
                                        mList.get(position).setIscheck(true);
                                        //没选选择默认数量为1
                                        mAccessory=(Accessory)adapter.getItem(position);
                                        mfAccessory=new FAccessory.OrderAccessoryStrBean.OrderAccessoryBean();
                                        mfAccessory.setFAccessoryID(mAccessory.getFAccessoryID());//获取id
                                        mfAccessory.setFAccessoryName(mAccessory.getAccessoryName()); //获取名字
                                        mfAccessory.setQuantity("1"); //默认数字为1
                                        mfAccessory.setPrice(mAccessory.getAccessoryPrice());//原价
                                        mfAccessory.setDiscountPrice(mAccessory.getAccessoryPrice());//折扣价
                                        mList.get(position).setCheckedcount(1);
                                        map.put(position,mfAccessory);


                                        adderView.setOnValueChangeListene(new adderView.OnValueChangeListener() {
                                            @Override
                                            public void onValueChange(int value) {
                                                //没选选择时间默认数量为1
                                                vibrator.vibrate(50);
                                                mAccessory=(Accessory)adapter.getItem(position);
                                                mfAccessory=new FAccessory.OrderAccessoryStrBean.OrderAccessoryBean();
                                                mfAccessory.setFAccessoryID(mAccessory.getFAccessoryID());
                                                mfAccessory.setFAccessoryName(mAccessory.getAccessoryName());
                                                mfAccessory.setQuantity(String.valueOf(value));
                                                mfAccessory.setPrice(mAccessory.getAccessoryPrice());//原价
                                                mfAccessory.setDiscountPrice(mAccessory.getAccessoryPrice());
                                                // Log.d("getQuantitys的个数00",mfAccessory.getQuantity());
                                                mList.get(position).setCheckedcount(value);
                                                map.put(position,mfAccessory);
                                            }
                                        });


                                    }else {
                                        adderView.setVisibility(View.INVISIBLE);
                                        img_ac_unselect.setVisibility(View.VISIBLE);
                                        img_ac_select.setVisibility(View.INVISIBLE);
                                        adderView.setValue(1); //但用户取消时将值设置为默认为1
                                        mList.get(position).setIscheck(false);
                                        mList.get(position).setCheckedcount(1);
                                        map.remove(position);
                                        vibrator.vibrate(50);
                                    }

                                    break;


                            }


                        }

                    });
                    /*选择配件*/



                    /*添加*/
                    customDialog_add_accessory.setYesOnclickListener("添加", new CustomDialog_Add_Accessory.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            fAcList = new ArrayList<>(map.values());
                            recyclerView_Pre_add_accessories.setLayoutManager(new LinearLayoutManager(mActivity));
                            mPre_order_add_ac_adapter = new Pre_order_Add_Ac_Adapter(R.layout.item_pre_order_add_accessories, fAcList);
                            recyclerView_Pre_add_accessories.setAdapter(mPre_order_add_ac_adapter);
                            customDialog_add_accessory.dismiss();

                            tv_total_price.setText("服务金额:¥" + gettotalPrice(fAcList, fList_service));

                            /*删除配件*/
                            mPre_order_add_ac_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                @SuppressLint("MissingPermission")
                                @Override
                                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                                    switch (view.getId()) {
                                        case R.id.iv_accessories_delete:
                                            String AccessoryID = null; //用于保存配件id
                                            boolean is_exist = false;// 用于保存是否存在 map
                                            int keymap = 0;   //保存map的键
                                            for (int i = 0; i < mList.size(); i++) {
                                                //先比较产品名字
                                                if (((FAccessory.OrderAccessoryStrBean.OrderAccessoryBean) adapter.getData().get(position)).getFAccessoryName().equals(mList.get(i).getAccessoryName())) {
                                                    mList.get(i).setIscheck(false);
                                                    AccessoryID = mList.get(i).getFAccessoryID();
                                                    mList.get(i).setCheckedcount(1);
                                                }
                                            }


                                            Log.d("Accessoryid的值是", AccessoryID);

                                            for (Integer key : map.keySet()) {
                                                System.out.println("key= " + key + " and value= " + map.get(key));

                                                if (map.get(key).getFAccessoryID().equals(AccessoryID)) {
                                                    is_exist = true;
                                                    keymap = key;
                                                }
                                            }
                                            if (is_exist) {
                                                map.remove(keymap);
                                                adapter.remove(position);
                                            }

                                            Log.d("map里面的值", String.valueOf(map.size()));
                                            tv_total_price.setText("服务金额:¥" + gettotalPrice(fAcList, fList_service));
                                            vibrator.vibrate(50);
                                            break;
                                    }
                                }
                            });


                        }
                    });

                }

                /*添加*/

                break;
            /*添加服务*/
            case R.id.tv_order_detail_add_service:


                customDialog_add_service.getWindow().setBackgroundDrawableResource(R.color.transparent);

                customDialog_add_service.show();

                // 设置宽度为屏宽、靠近屏幕底部。
                Window window1 = customDialog_add_service.getWindow();
                //最重要的一句话，一定要加上！要不然怎么设置都不行！
                // window.setBackgroundDrawableResource(android.R.color.transparent);
                WindowManager.LayoutParams wlp1 = window1.getAttributes();
                Display d1 = window1.getWindowManager().getDefaultDisplay();
                //获取屏幕宽
                wlp1.height = (d1.getHeight());
                wlp1.width = (d1.getWidth());
                //宽度按屏幕大小的百分比设置，这里我设置的是全屏显示
                wlp1.gravity = Gravity.CENTER;
/*
                    if (wlp.gravity == Gravity.BOTTOM)
                        wlp.y = 0;
                    //如果是底部显示，则距离底部的距离是0*/
                window1.setAttributes(wlp1);


                recyclerView_custom_add_service = customDialog_add_service.findViewById(R.id.recyclerView_custom_add_service);
                recyclerView_custom_add_service.setLayoutManager(new LinearLayoutManager(mActivity));
                mAdd_Service_Adapter = new Add_Service_Adapter(R.layout.item_addservice, mList_service);
                recyclerView_custom_add_service.setAdapter(mAdd_Service_Adapter);

                mAdd_Service_Adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                        ImageView img_add_service_select = (ImageView) adapter.getViewByPosition(recyclerView_custom_add_service, position, R.id.img_add_service_select); //选中图片
                        ImageView img_add_service_unselect = (ImageView) adapter.getViewByPosition(recyclerView_custom_add_service, position, R.id.img_add_service_unselect);//未选中图片
                                switch (view.getId()){
                                    case R.id.img_add_service_unselect:
                                    case R.id.img_add_service_select:
                                    case R.id.tv_add_service_name:
                                        vibrator.vibrate(50);
                                        if (((Service)(adapter.getData().get(position))).isIschecked()==false){ //如果是为选中的状态点击  变为红色 选中状态 出现 数量选择器
                                            img_add_service_unselect.setVisibility(View.INVISIBLE);
                                            img_add_service_select.setVisibility(View.VISIBLE);
                                            mList_service.get(position).setIschecked(true);
                                            mService=(Service)adapter.getItem(position);
                                            mfService=new FService.OrderServiceStrBean.OrderServiceBean();
                                            mfService.setServiceID(mService.getFServiceID());
                                            mfService.setServiceName(mService.getFServiceName());
                                            mfService.setPrice(mService.getInitPrice());
                                            mfService.setDiscountPrice(mService.getInitPrice());
                                           
                                            map_service.put(position,mfService);
                                            //vibrator.vibrate(50);
                                        }else {
                                            img_add_service_unselect.setVisibility(View.VISIBLE);
                                            img_add_service_select.setVisibility(View.INVISIBLE);
                                            mList_service.get(position).setIschecked(false);
                                            map_service.remove(position);
                                            //vibrator.vibrate(50);
                                        }

                                break;


                        }


                    }
                });

                /*添加*/
                customDialog_add_service.setYesOnclickListener("添加服务", new CustomDialog_Add_Service.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        fList_service = new ArrayList<>(map_service.values());
                        recyclerView_Pre_add_service.setLayoutManager(new LinearLayoutManager(mActivity));
                        mPre_order_Add_Service_Adapter = new Pre_order_Add_Service_Adapter(R.layout.item_add_service, fList_service);
                        recyclerView_Pre_add_service.setAdapter(mPre_order_Add_Service_Adapter);
                        customDialog_add_service.dismiss();
                        tv_total_price.setText("服务金额:¥" + gettotalPrice(fAcList, fList_service));

                        mPre_order_Add_Service_Adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @SuppressLint("MissingPermission")
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                switch (view.getId()) {
                                    case R.id.iv_service_delete:
                                        String ServiceID = null; //用于保存服务id
                                        boolean is_exist = false;// 用于保存是否存在 map
                                        int keymap = 0;   //保存map的箭
                                        for (int i = 0; i < mList_service.size(); i++) {
                                            //先比较产品名字
                                            if (((FService.OrderServiceStrBean.OrderServiceBean) adapter.getData().get(position)).getServiceName().equals(mList_service.get(i).getFServiceName())) {
                                                mList_service.get(i).setIschecked(false);
                                                ServiceID = mList_service.get(i).getFServiceID();
                                            }
                                        }

                                        Log.d("Serviceid的值是", ServiceID);

                                        for (Integer key : map_service.keySet()) {

                                            if (map_service.get(key).getServiceID().equals(ServiceID)) {
                                                is_exist = true;
                                                keymap = key;
                                            }
                                        }
                                        if (is_exist) {
                                            map_service.remove(keymap);
                                            adapter.remove(position);
                                        }

                                        tv_total_price.setText("服务金额:¥" + gettotalPrice(fAcList, fList_service));
                                        vibrator.vibrate(50);

                                        break;

                                }

                            }
                        });

                    }
                });


                break;


            case R.id.iv_scan_QR:
            case R.id.tv_scan_QR:
                IntentIntegrator integrator = new IntentIntegrator(Order_details_Activity.this);
                // 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
                integrator.setCaptureActivity(ScanActivity.class); //设置打开摄像头的Activity
                integrator.setPrompt("请扫描快递码"); //底部的提示文字，设为""可以置空
                integrator.setCameraId(0); //前置或者后置摄像头
                integrator.setBeepEnabled(true); //扫描成功的「哔哔」声，默认开启
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
                break;
            /*提交工单*/
            case R.id.tv_detail_submit:

                String selecttime = tv_select_time.getText().toString();
                if (selecttime == "") {//未选择时间
                    Toast.makeText(getApplication(), "请选择时间", Toast.LENGTH_SHORT).show();
                    return;

                } else {

                    StringBuilder stringBuilder = new StringBuilder(selecttime);
                     time = "" + stringBuilder.replace(10, 11, "T"); //增加"T"
                   //  time=selecttime;

                   // Log.d("fAcList的长度为", String.valueOf(fAcList.size()));

                    if (fAcList.size()==0&&fList_service.size()==0){ //都没有添加只提交上门时间


                       mPresenter.UpdateSendOrderUpdateTime(orderID,time);


                   }else if (fAcList.size()!=0&&fList_service.size()==0){//只有配件

                       orderAccessoryStrBean = new FAccessory.OrderAccessoryStrBean();
                       orderAccessoryStrBean.setOrderAccessory(fAcList);
                       Gson gson1=new Gson();
                       String s1 = gson1.toJson(orderAccessoryStrBean);
                       sAccessory=new SAccessory();
                       sAccessory.setOrderID(orderID);

                        if (select_state==0){    /*厂家0  自购 1*/
                            return;
                        }else if (select_state==1){
                            sAccessory.setAccessorySequency("0");
                        }else {
                            sAccessory.setAccessorySequency("1");
                        }
                    //   sAccessory.setAccessorySequency("0");//自购件 和工厂配件默认
                       sAccessory.setOrderAccessoryStr(s1);
                       Gson gson=new Gson();
                       String s = gson.toJson(sAccessory);
                       Log.d("添加的配件有",s);
                       RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),s);
                       mPresenter.AddOrderAccessory(body);



                   }else if (fList_service.size()!=0&&fAcList.size()==0){//只有服务
                       orderServiceStrBean=new FService.OrderServiceStrBean();
                       orderServiceStrBean.setOrderService(fList_service);
                       Gson gson3=new Gson();
                       String s1 = gson3.toJson(orderServiceStrBean);
                       sService=new SService();
                       sService.setOrderID(orderID);
                       sService.setOrderServiceStr(s1);
                       Gson gson4=new Gson();
                       String s = gson4.toJson(sService);
                       Log.d("添加的服务有",s);
                       RequestBody body1=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),s);
                       mPresenter.AddOrderService(body1);


                   }else {//都有
                    //*配件*//
                    sTotalAS=new STotalAS();
                    orderAccessoryStrBean = new FAccessory.OrderAccessoryStrBean();
                    orderAccessoryStrBean.setOrderAccessory(fAcList);
                    Gson gson=new Gson();
                    String s1 = gson.toJson(orderAccessoryStrBean);

                    //*服务*//*
                    orderServiceStrBean=new FService.OrderServiceStrBean();
                    orderServiceStrBean.setOrderService(fList_service);
                    String s2 = gson.toJson(orderServiceStrBean);
                    sTotalAS.setOrderID(orderID);

                    if (select_state==0){    /*厂家0  自购 1*/
                        return;
                    }else if (select_state==1){
                        sTotalAS.setAccessorySequency("0");
                    }else {
                        sTotalAS.setAccessorySequency("1");
                    }


                  //  sTotalAS.setAccessorySequency("0");//自购件 和工厂配件默认
                    sTotalAS.setOrderAccessoryStr(s1);
                    sTotalAS.setOrderServiceStr(s2);
                    sTotalAS.setReturnAccessoryMsg("1214124125125韵达快递");//模拟数据
                    String s3 = gson.toJson(sTotalAS);
                    Log.d("配件和服务有",s3);
                    RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),s3);
                    mPresenter.AddOrUpdateAccessoryServiceReturn(body);

                   }




                   }

                                 break;

           //添加维修图片

            case R.id.ll_view_example:  //查看示例
                final ViewExampleDialog viewExampleDialog=new ViewExampleDialog(mActivity);
                viewExampleDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
                viewExampleDialog.setNoOnclickListener("取消", new ViewExampleDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        viewExampleDialog.dismiss();
                    }
                });
                viewExampleDialog.show();
                Window window=viewExampleDialog.getWindow();
                WindowManager.LayoutParams wlp=window.getAttributes();
                Display d=window.getWindowManager().getDefaultDisplay();
                wlp.height=d.getHeight();
                wlp.width=d.getWidth();
                wlp.gravity= Gravity.CENTER;
                window.setAttributes(wlp);
                break;
            case R.id.iv_bar_code:
                showPopupWindow(101,102);
                break;
            case R.id.iv_machine:
                showPopupWindow(201,202);
                break;
            case R.id.iv_fault_location:
                showPopupWindow(301,303);
                break;
            case R.id.iv_new_and_old_accessories:
                showPopupWindow(401,404);
                break;

                /*添加服务过程*/
            case R.id.iv_one:
                showPopupWindow(501,505);
                break;
            case R.id.iv_two:
                showPopupWindow(601,606);
                break;
            case R.id.iv_three:
                showPopupWindow(701,707);
                break;
            case R.id.iv_four:
                showPopupWindow(801,808);
                break;
            case R.id.iv_map1: //选中地图1
                showPopupWindow(901,909);
                break;
            case R.id.iv_map2: //选中地图2
                showPopupWindow(1001,1002);
                break;
                         default:
                          break;

        }



    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {

        switch (baseResult.getStatusCode()) {

            case 200:
                data = baseResult.getData();
                // Log.d("getOrderIDgetOrderID",data.getOrderID()+" "+data.getMemo()+" "+data.getBrandName());
                tv_order_details_orderid.setText(data.getOrderID());
                tv_order_details_receiving_time.setText(data.getAudDate().replace("T", " ")); //将T替换为空格
                tv_order_details_reason.setText(data.getMemo());
                tv_order_details_product_name.setText(data.getCategoryName() + "/" + data.getBrandName() + "/" + data.getProductType());

                if (data.getTypeID()==1) {//维修
                    tv_order_details_status.setText("维修");
                    tv_order_details_status.setBackgroundResource(R.color.color_custom_01);
                    mll_return_information.setVisibility(View.VISIBLE);
                    mll_service_process.setVisibility(View.GONE);
                } else {
                    tv_order_details_status.setText("安装");
                    tv_order_details_status.setBackgroundResource(R.color.color_custom_04);
                    mll_return_information.setVisibility(View.GONE);
                    mll_service_process.setVisibility(View.VISIBLE);
                }
                tv_order_details_adress.setText(data.getAddress());
                mTvNum.setText("数量：" + data.getNum() + "台");
                Money=data.getOrderMoney()-data.getInitMoney();

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
                mList.clear();
                mList.addAll(baseResult.getData().getItem1());
                break;
            default:
                break;


        }
    }

    @Override
    public void GetFactoryService(BaseResult<GetFactorySeviceData<Service>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                mList_service.clear();
                Log.d("getitem1", String.valueOf(baseResult.getData().isItem1()));
                mList_service.addAll(baseResult.getData().getItem2());
                Log.d("ischeck_service", String.valueOf(mList_service.size()));
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
             if (!baseResult.getData().isItem1()){
                 Toast.makeText(this,"余额不足请检查",Toast.LENGTH_LONG).show();
                 return;
             }else {
                   mPresenter.UpdateSendOrderUpdateTime(orderID,time);
             }
                break;
            default:
                break;
        }
    }

    /*提交服务信息*/
    @Override
    public void AddOrderService(BaseResult<Data> baseResult) {

        switch (baseResult.getStatusCode()){
            case 200:
                if (!baseResult.getData().isItem1()){
                    Toast.makeText(this,"服务添加失败工厂端余额不足",Toast.LENGTH_LONG).show();
                }else {

                 mPresenter.UpdateSendOrderUpdateTime(orderID,time);
                }

                break;
                default:
                    break;
        }
    }

    @Override
    public void AddOrUpdateAccessoryServiceReturn(BaseResult<Data> baseResult) {
     switch (baseResult.getStatusCode()){
    case 200:
        if (!baseResult.getData().isItem1()){

            Toast.makeText(this,"提交失败",Toast.LENGTH_LONG).show();
        }else {
            mPresenter.UpdateSendOrderUpdateTime(orderID,time);
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
                if (baseResult.getData().isItem1()) {//请求成功
                 /*   //数据是使用Intent返回
                    Intent intent = new Intent();
                    //把返回数据存入Intent
                    intent.putExtra("result", "in_service");  //请求成功进入服务界面
                    //设置返回数据
                    Order_details_Activity.this.setResult(RESULT_OK, intent);*/
                    Order_details_Activity.this.finish();
                }else {

                    Toast.makeText(this,"未知错误",Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;


        }
    }

/*
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            String result = scanResult.getContents();
            et_express_sweep_code.setText("快递单号:" + result);
        }

    }*/

    //计算价格
    private double gettotalPrice(List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> list,
                                 List<FService.OrderServiceStrBean.OrderServiceBean> list2) {

        double acprice = 0;
        double servicprice = 0;

        if (list == null && list2 != null) { //无配件有服务内容
            for (int i = 0; i < list2.size(); i++) {
                servicprice = servicprice + list2.get(i).getDiscountPrice();
            }
            return servicprice+Money;
        } else if (list != null && list2 == null)//有配件没服务
        {
            for (int i = 0; i < list.size(); i++) {
                acprice = acprice + list.get(i).getDiscountPrice() * Double.parseDouble(list.get(i).getQuantity());
            }
            return acprice+Money;
        } else if (list != null && list2 != null)//都有
        {
            for (int i = 0; i < list.size(); i++) {
                acprice = acprice + list.get(i).getDiscountPrice() * Double.parseDouble(list.get(i).getQuantity());
            }
            for (int i = 0; i < list2.size(); i++) {
                servicprice = servicprice + list2.get(i).getDiscountPrice();
            }
            return acprice + servicprice+Money;

        } else { //都没有
            return Money;
        }


    }





    /**
     * 弹出Popupwindow
     */
    public void showPopupWindow(final int code1,final int code2) {
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.camera_layout, null);
        Button camera_btn= popupWindow_view.findViewById(R.id.camera_btn);
        Button photo_btn= popupWindow_view.findViewById(R.id.photo_btn);
        Button cancel_btn= popupWindow_view.findViewById(R.id.cancel_btn);
        camera_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (requestPermissions()){
                    Intent intent = new Intent();
                    // 指定开启系统相机的Action
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    String f = System.currentTimeMillis()+".jpg";
                    String fileDir= Environment.getExternalStorageDirectory().getAbsolutePath()+"/xgy";
                    FilePath =Environment.getExternalStorageDirectory().getAbsolutePath()+"/xgy/"+f;
                    File dirfile=new File(fileDir);
                    if (!dirfile.exists()){
                        dirfile.mkdirs();
                    }
                    File file=new File(FilePath);
                    Uri fileUri;
                    if (Build.VERSION.SDK_INT >= 24) {
                        fileUri = FileProvider.getUriForFile(mActivity,"com.ying.administrator.masterappdemo.fileProvider", file);
                    } else {
                        fileUri = Uri.fromFile(file);
                    }

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(intent, code1);
                }else{
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10001);
                }
                mPopupWindow.dismiss();
            }
        });
        photo_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (requestPermissions()){
                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("image/*");
                    startActivityForResult(Intent.createChooser(i, "test"), code2);
                    mPopupWindow.dismiss();
                }else{
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
                MyUtils.setWindowAlpa(mActivity,false);
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
    private boolean requestPermissions(){
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
            et_single_number.setText("单号:"+result);
        }
        File file = null;
        switch (requestCode){
            //拍照
            case 101:
                if (resultCode==-1){
                    Glide.with(mActivity).load(FilePath).into(iv_bar_code);
                    file=new File(FilePath);
                }

                break;
            //相册
            case 102:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_bar_code);
                    file=new File(MyUtils.getRealPathFromUri(mActivity,uri));
                }
                break;
            //拍照
            case 201:
                if (resultCode==-1){
                    Glide.with(mActivity).load(FilePath).into(iv_machine);
                    file=new File(FilePath);
                }
                break;
            //相册
            case 202:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_machine);
                    file=new File(MyUtils.getRealPathFromUri(mActivity,uri));
                }
                break;
            //拍照
            case 301:
                if (resultCode==-1){
                    Glide.with(mActivity).load(FilePath).into(iv_fault_location);
                    file=new File(FilePath);
                }
                break;
            //相册
            case 303:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_fault_location);
                    file=new File(MyUtils.getRealPathFromUri(mActivity,uri));
                }
                break;
            //拍照
            case 401:
                if (resultCode==-1){
                    Glide.with(mActivity).load(FilePath).into(iv_new_and_old_accessories);
                    file=new File(FilePath);
                }
                break;
            //相册
            case 404:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_new_and_old_accessories);
                    file=new File(MyUtils.getRealPathFromUri(mActivity,uri));
                }
                break;
            //拍照
            case 501:
                if (resultCode==-1){
                    Glide.with(mActivity).load(FilePath).into(iv_one);
                    file=new File(FilePath);
                }

                break;
            //相册
            case 505:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_one);
                    file=new File(MyUtils.getRealPathFromUri(mActivity,uri));
                }
                break;


            //拍照
            case 601:
                if (resultCode==-1){
                    Glide.with(mActivity).load(FilePath).into(iv_two);
                    file=new File(FilePath);
                }

                break;
            //相册
            case 606:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_two);
                    file=new File(MyUtils.getRealPathFromUri(mActivity,uri));
                }
                break;

            //拍照
            case 701:
                if (resultCode==-1){
                    Glide.with(mActivity).load(FilePath).into(iv_three);
                    file=new File(FilePath);
                }

                break;
            //相册
            case 707:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_three);
                    file=new File(MyUtils.getRealPathFromUri(mActivity,uri));
                }
                break;


            //拍照
            case 801:
                if (resultCode==-1){
                    Glide.with(mActivity).load(FilePath).into(iv_four);
                    file=new File(FilePath);
                }

                break;
            //相册
            case 808:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(iv_four);
                    file=new File(MyUtils.getRealPathFromUri(mActivity,uri));
                }
                break;


                /* 地图1*/
            //拍照
            case 901:
                if (resultCode==-1){
                    Glide.with(mActivity).load(FilePath).into(mIvMap1);
                    file=new File(FilePath);
                }

                break;
            //相册
            case 909:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvMap1);
                    file=new File(MyUtils.getRealPathFromUri(mActivity,uri));
                }
                break;


            /* 地图2*/
            //拍照
            case 1001:
                if (resultCode==-1){
                    Glide.with(mActivity).load(FilePath).into(mIvMap2);
                    file=new File(FilePath);
                }

                break;
            //相册
            case 1002:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvMap2);
                    file=new File(MyUtils.getRealPathFromUri(mActivity,uri));
                }
                break;




        }
//        if (file!=null){
//            uploadImg(file);
//        }
    }
//    public void uploadImg(File f){
//        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
//        builder.addFormDataPart("img", f.getName(), RequestBody.create(MediaType.parse("img/png"), f));
//        MultipartBody requestBody=builder.build();
//        mPresenter.IDCardUpload(requestBody);
//    }
//


}
