package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.Manifest;
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
import com.ying.administrator.masterappdemo.entity.GetFactorySeviceData;
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
    @BindView(R.id.iv_item1)
    ImageView mIvItem1;
    @BindView(R.id.iv_item2)
    ImageView mIvItem2;
    @BindView(R.id.iv_item3)
    ImageView mIvItem3;
    @BindView(R.id.iv_map1)
    ImageView mIvMap1;
    @BindView(R.id.iv_map2)
    ImageView mIvMap2;

    private String orderID;//工单号
    private TextView tv_actionbar_title; //title标题
    private RadioGroup rg_order_details_for_remote_fee;

    private WorkOrder.DataBean data = new WorkOrder.DataBean();
    private LinearLayout ll_Out_of_service_tv;
    private LinearLayout ll_Out_of_service_img;
    private LinearLayout ll_return;
    private TextView tv_detail_submit; //提交

    /*订单属性*/
    private LinearLayout rl_select_time; //选择时间
    private TextView tv_select_time; //显示时间
    private TextView tv_order_details_receiving_time; //工单接收时间
    private TextView tv_order_details_orderid; //工单号
    private TextView tv_order_details_reason;//故障原因
    private TextView tv_order_details_product_name;//产品名称
    private TextView tv_order_details_status; //安装维修状态
    private TextView tv_order_details_adress; //地址
    private TextView tv_total_price; // 配件和服务总价
    /*订单属性*/


    /*服务和配件自定义dialog*/
    private CustomDialog_Add_Accessory customDialog_add_accessory;
    private CustomDialog_Add_Service customDialog_add_service;
    /*服务和配件自定义dialog*/


    /*  配件*/
    private RadioGroup rg_order_details_add_accessories; //添加配件
    private RadioButton rb_order_details_manufacturer; //厂家寄件
    private RadioButton rb_order_details_oneself; //自购件
    private TextView tv_order_details_add_accessories; //添加配件
    private List<Accessory> mList;   //存放返回的list
    private Map<Integer, FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> map; //用于存放dialog里选择的配件
    private List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> fAcList;// 用于存放预接单页面显示的数据
    private FAccessory fAccessory;
    private FAccessory.OrderAccessoryStrBean orderAccessoryStrBean;
    private FAccessory.OrderAccessoryStrBean.OrderAccessoryBean mfAccessory;
    private Accessory mAccessory;
    private RecyclerView recyclerView_custom_add_accessory;
    private RecyclerView recyclerView_Pre_add_accessories; //预接单的  RecyclerView
    private Pre_order_Add_Ac_Adapter mPre_order_add_ac_adapter; //预接单 的adater
    private Add_Ac_Adapter mAdd_Ac_Adapter;
    /*配件*/

    /*服务*/
    private TextView tv_order_detail_add_service;//添加服务
    private List<Service> mList_service;
    private Map<Integer, FService.OrderServiceStrBean.OrderServiceBean> map_service;
    private List<FService.OrderServiceStrBean.OrderServiceBean> fList_service;
    private RecyclerView recyclerView_custom_add_service;
    private RecyclerView recyclerView_Pre_add_service;
    private Pre_order_Add_Service_Adapter mPre_order_Add_Service_Adapter; //预接单的adpter
    private Service mService;
    private FService.OrderServiceStrBean.OrderServiceBean mfService;
    private Add_Service_Adapter mAdd_Service_Adapter;
    /*服务*/


    /*扫码*/
    private EditText et_express_sweep_code;//输入的快递单号信息
    private ImageView img_express_sweep_code;
    private TextView tv_express_sweep_code;
    /*扫码*/
    /*震动*/
    Vibrator vibrator;
    private double totalPrice; // 配件价格*数量+服务价格   fList_service+
    private View popupWindow_view;
    private String FilePath;
    private PopupWindow mPopupWindow;
    private ArrayList<String> permissions;
    private int size;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.layout.activity_order_details);
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
        tv_actionbar_title = findViewById(R.id.tv_actionbar_title);
        rg_order_details_for_remote_fee = findViewById(R.id.rg_order_details_for_remote_fee);
        ll_Out_of_service_tv = findViewById(R.id.ll_Out_of_service_tv);
        ll_Out_of_service_img = findViewById(R.id.ll_Out_of_service_img);
        ll_return = findViewById(R.id.ll_return);
        rl_select_time = findViewById(R.id.rl_select_time);
        tv_select_time = findViewById(R.id.tv_select_time);
        tv_order_details_receiving_time = findViewById(R.id.tv_order_details_receiving_time);//接单时间
        tv_order_details_orderid = findViewById(R.id.tv_order_details_orderid);//工单号
        tv_order_details_reason = findViewById(R.id.tv_order_details_reason);//故障原因
        tv_order_details_product_name = findViewById(R.id.tv_order_details_product_name);//产品名称
        tv_order_details_status = findViewById(R.id.tv_order_details_status);//安装维修状态
        tv_order_details_adress = findViewById(R.id.tv_order_details_adress); //地址
        rg_order_details_add_accessories = findViewById(R.id.rg_order_details_add_accessories);//添加配件
        rb_order_details_manufacturer = findViewById(R.id.rb_order_details_manufacturer);
        rb_order_details_oneself = findViewById(R.id.rb_order_details_oneself);
        tv_order_details_add_accessories = findViewById(R.id.tv_order_details_add_accessories);//添加配件
        recyclerView_Pre_add_accessories = findViewById(R.id.recyclerView_add_accessories); //预接单recyclerview
        tv_order_detail_add_service = findViewById(R.id.tv_order_detail_add_service);
        recyclerView_Pre_add_service = findViewById(R.id.tv_recyclerView_Pre_add_service);//预接单recyclerview
        et_express_sweep_code = findViewById(R.id.et_express_sweep_code);
        img_express_sweep_code = findViewById(R.id.img_express_sweep_code);
        tv_express_sweep_code = findViewById(R.id.tv_express_sweep_code);
        tv_total_price = findViewById(R.id.tv_total_price);
        tv_detail_submit = findViewById(R.id.tv_detail_submit);

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
        img_express_sweep_code.setOnClickListener(this);
        tv_express_sweep_code.setOnClickListener(this);
        tv_detail_submit.setOnClickListener(this);

        mIvMap1.setOnClickListener(this);
        mIvMap2.setOnClickListener(this);
        mIvItem1.setOnClickListener(this);
        mIvItem2.setOnClickListener(this);
        mIvItem3.setOnClickListener(this);

        /*添加配件*/
        rg_order_details_add_accessories.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_order_details_manufacturer: //厂家寄件申请

                        break;

                    case R.id.rb_order_details_oneself: //自购件申请

                        break;

                }

            }
        });



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
        // tv_select_time.setOnClickListener(this);
        //tv_total_price.setText("服务金额:¥"+gettotalPrice(fAcList));


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

                if (data.getTypeID().equals("1")) {//维修
                    tv_order_details_status.setText("维修");
                    tv_order_details_status.setBackgroundResource(R.color.color_custom_01);
                } else {
                    tv_order_details_status.setText("安装");
                    tv_order_details_status.setBackgroundResource(R.color.color_custom_04);
                }
                tv_order_details_adress.setText(data.getAddress());
                mTvNum.setText("数量：" + data.getNum() + "台");

                break;

            default:
                Log.d("detail", baseResult.getData().toString());
                //  data=null;
                break;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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

                    /*if (!map.isEmpty()){
                       fAcList.clear();
                       map.clear();
                    }
                          */
                // tv_order_details_add_accessories.setText("重新添加");
                //startActivity(new Intent(Order_details_Activity.this,AddAccessoryActivity.class));

                Log.d("mlistmlist", String.valueOf(mList.size()));
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

                                    //选择了数量根据输入框中的来
                                    vibrator.vibrate(50);

                                    adderView.setOnValueChangeListene(new adderView.OnValueChangeListener() {
                                        @Override
                                        public void onValueChange(int value) {
                                            //没选选择时间默认数量为1

                                            mAccessory = (Accessory) adapter.getItem(position);
                                            mfAccessory = new FAccessory.OrderAccessoryStrBean.OrderAccessoryBean();
                                            mfAccessory.setFAccessoryName(mAccessory.getAccessoryName());
                                            mfAccessory.setQuantity(String.valueOf(value));
                                            mfAccessory.setDiscountPrice(mAccessory.getAccessoryPrice());
                                            // Log.d("getQuantitys的个数00",mfAccessory.getQuantity());
                                            mList.get(position).setCheckedcount(value);
                                            map.put(position, mfAccessory);
                                        }
                                    });


                                } else {
                                    //viewadd.setVisibility(View.INVISIBLE); //数量选择器消失
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
                        //Log.d("所选配件的数量", String.valueOf(fAcList.size()));

                        // for (int i=0;i<fAcList.size();i++){
                        //     Log.d("配件的数量:",fAcList.get(i).getQuantity());
                        // }
                        tv_total_price.setText("服务金额:¥" + gettotalPrice(fAcList, fList_service));

                        /*删除配件*/
                        mPre_order_add_ac_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                switch (view.getId()) {
                                    case R.id.iv_accessories_delete:
                                        String AccessoryName = null; //用于保存配件名称
                                        boolean is_exist = false;// 用于保存是否存在 map
                                        int keymap = 0;   //保存map的箭
                                        for (int i = 0; i < mList.size(); i++) {
                                            //先比较产品名字
                                            if (((FAccessory.OrderAccessoryStrBean.OrderAccessoryBean) adapter.getData().get(position)).getFAccessoryName().equals(mList.get(i).getAccessoryName())) {
                                                mList.get(i).setIscheck(false);
                                                AccessoryName = mList.get(i).getAccessoryName();
                                                mList.get(i).setCheckedcount(1);
                                            }
                                        }


                                        Log.d("AccessoryName的值是", AccessoryName);

                                        for (Integer key : map.keySet()) {
                                            System.out.println("key= " + key + " and value= " + map.get(key));

                                            if (map.get(key).getFAccessoryName().equals(AccessoryName)) {
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

                                        break;
                                }
                            }
                        });
                        for (int key : map.keySet()) {
                            System.out.println("选择了" + map.get(key).getFAccessoryName() + "配件" + "数量" + map.get(key).getQuantity() + "总价格" + map.get(key).getDiscountPrice());
                            System.out.println("选择了-------------------------------------------");
                        }

                    }
                });
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
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        ImageView img_add_service_select = (ImageView) adapter.getViewByPosition(recyclerView_custom_add_service, position, R.id.img_add_service_select); //选中图片
                        ImageView img_add_service_unselect = (ImageView) adapter.getViewByPosition(recyclerView_custom_add_service, position, R.id.img_add_service_unselect);//未选中图片

                        switch (view.getId()) {
                            case R.id.img_add_service_unselect:
                            case R.id.img_add_service_select:
                            case R.id.tv_add_service_name:
                                if (((Service) (adapter.getData().get(position))).isIschecked() == false) { //如果是为选中的状态点击  变为红色 选中状态 出现 数量选择器
                                    //viewadd.setVisibility(View.VISIBLE); //数量选择器出现
                                    img_add_service_unselect.setVisibility(View.INVISIBLE);
                                    img_add_service_select.setVisibility(View.VISIBLE);
                                    mList_service.get(position).setIschecked(true);
                                    mService = (Service) adapter.getItem(position);
                                    mfService = new FService.OrderServiceStrBean.OrderServiceBean();
                                    mfService.setServiceName(mService.getFServiceName());
                                    mfService.setDiscountPrice(mService.getInitPrice());
                                    map_service.put(position, mfService);
                                    vibrator.vibrate(50);
                                } else {
                                    img_add_service_unselect.setVisibility(View.VISIBLE);
                                    img_add_service_select.setVisibility(View.INVISIBLE);
                                    mList_service.get(position).setIschecked(false);
                                    map_service.remove(position);
                                    vibrator.vibrate(50);
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
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                switch (view.getId()) {
                                    case R.id.iv_service_delete:
                                        String ServiceName = null; //用于保存配件名称
                                        boolean is_exist = false;// 用于保存是否存在 map
                                        int keymap = 0;   //保存map的箭
                                        for (int i = 0; i < mList_service.size(); i++) {
                                            //先比较产品名字
                                            if (((FService.OrderServiceStrBean.OrderServiceBean) adapter.getData().get(position)).getServiceName().equals(mList_service.get(i).getFServiceName())) {
                                                mList_service.get(i).setIschecked(false);
                                                ServiceName = mList_service.get(i).getFServiceName();
                                            }
                                        }


                                        Log.d("ServiceName的值是", ServiceName);

                                        for (Integer key : map_service.keySet()) {

                                            if (map_service.get(key).getServiceName().equals(ServiceName)) {
                                                is_exist = true;
                                                keymap = key;
                                            }
                                        }
                                        if (is_exist) {
                                            map_service.remove(keymap);
                                            adapter.remove(position);
                                        }

                                        tv_total_price.setText("服务金额:¥" + gettotalPrice(fAcList, fList_service));


                                        break;

                                }

                            }
                        });

                    }
                });


                break;


            case R.id.img_express_sweep_code:
            case R.id.tv_express_sweep_code:
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
                    String time = "" + stringBuilder.replace(10, 11, "T"); //增加"T"

                    orderAccessoryStrBean = new FAccessory.OrderAccessoryStrBean();
                    orderAccessoryStrBean.setOrderAccessory(fAcList);

                    fAccessory = new FAccessory();
                    fAccessory.setOrderID(orderID);
                    fAccessory.setAccessorySequency("0");
                    fAccessory.setOrderAccessoryStr(orderAccessoryStrBean);

                    Gson gson = new Gson();
                    String s = gson.toJson(fAccessory);
                    Log.d("添加的配件有", s);

                    RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
                    mPresenter.AddOrderAccessory(body);
                    mPresenter.UpdateSendOrderUpdateTime(orderID, time);
                    Log.d("选择的时间是", time);


                }


                break;
            case R.id.iv_item1:
                if (requestPermissions()) {
                    showPopupWindow(101, 102);
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10001);
                }
                break;
            case R.id.iv_item2:
                if (requestPermissions()) {
                    showPopupWindow(201, 202);
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10002);
                }
                break;
            case R.id.iv_item3:
                if (requestPermissions()) {
                    showPopupWindow(301, 302);
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10003);
                }
                break;
            case R.id.iv_map1:
                if (requestPermissions()) {
                    showPopupWindow(401, 402);
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10004);
                }
                break;
            case R.id.iv_map2:
                if (requestPermissions()) {
                    showPopupWindow(501, 502);
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10005);
                }
                break;
            default:
                break;

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
            case 10001:
                if (size == grantResults.length) {//允许
                    showPopupWindow(101, 102);
                } else {//拒绝
                    MyUtils.showToast(mActivity, "相关权限未开启");
                }
                break;
            case 10002:
                if (size == grantResults.length) {//允许
                    showPopupWindow(201, 202);
                } else {//拒绝
                    MyUtils.showToast(mActivity, "相关权限未开启");
                }
                break;
            case 10003:
                if (size == grantResults.length) {//允许
                    showPopupWindow(301, 302);
                } else {//拒绝
                    MyUtils.showToast(mActivity, "相关权限未开启");
                }
                break;
            case 10004:
                if (size == grantResults.length) {//允许
                    showPopupWindow(401, 402);
                } else {//拒绝
                    MyUtils.showToast(mActivity, "相关权限未开启");
                }
                break;
            case 10005:
                if (size == grantResults.length) {//允许
                    showPopupWindow(501, 502);
                } else {//拒绝
                    MyUtils.showToast(mActivity, "相关权限未开启");
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
                mList.clear();
                mList.addAll(baseResult.getData().getItem1());
                Log.d("mlist2", String.valueOf(mList.size()));
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
    public void AddOrderAccessory(BaseResult<String> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Log.d("提交配件信息的结果为", baseResult.getData());
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
                    //数据是使用Intent返回
                    Intent intent = new Intent();
                    //把返回数据存入Intent
                    intent.putExtra("result", "in_service");  //请求成功进入服务界面
                    //设置返回数据
                    Order_details_Activity.this.setResult(RESULT_OK, intent);
                    Order_details_Activity.this.finish();
                }
                break;
            default:
                break;


        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            String result = scanResult.getContents();
            et_express_sweep_code.setText("快递单号:" + result);
        }
        File file = null;
        switch (requestCode) {
            //拍照
            case 101:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(mIvItem1);
                    file = new File(FilePath);
                }

                break;
            //相册
            case 102:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvItem1);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                break;
            //拍照
            case 201:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(mIvItem2);
                    file = new File(FilePath);
                }
                break;
            //相册
            case 202:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvItem2);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                break;
            //拍照
            case 301:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(mIvItem3);
                    file = new File(FilePath);
                }
                break;
            //相册
            case 302:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvItem3);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                break;
            //拍照
            case 401:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(mIvMap1);
                    file = new File(FilePath);
                }
                break;
            //相册
            case 402:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvMap1);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                break;
            //拍照
            case 501:
                if (resultCode == -1) {
                    Glide.with(mActivity).load(FilePath).into(mIvMap2);
                    file = new File(FilePath);
                }
                break;
            //相册
            case 502:
                if (data != null) {
                    Uri uri = data.getData();
                    Glide.with(mActivity).load(uri).into(mIvMap2);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                }
                break;

        }
//        if (file!=null){
//            uploadImg(file);
//        }
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
            return 0;
        }


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        /*  两秒之内再按一下退出*/
        //判断用户是否点击了返回键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.putExtra("result", "pending_appointment");  //按了返回键返回已接待预约
            //设置返回数据
            Order_details_Activity.this.setResult(RESULT_OK, intent);
            Order_details_Activity.this.finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
