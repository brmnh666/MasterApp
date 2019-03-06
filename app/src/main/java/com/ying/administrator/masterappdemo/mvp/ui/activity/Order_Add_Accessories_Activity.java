package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import com.ying.administrator.masterappdemo.widget.CustomDialog_Add_Accessory;
import com.ying.administrator.masterappdemo.widget.CustomDialog_Add_Service;
import com.ying.administrator.masterappdemo.widget.adderView;

import org.feezu.liuli.timeselector.TimeSelector;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.RequestBody;


/*预接单详情页*/
public class Order_Add_Accessories_Activity extends BaseActivity<PendingOrderPresenter, PendingOrderModel> implements  PendingOrderContract.View {
    private String orderID;//工单号
    private RadioGroup rg_order_details_for_remote_fee;
    private WorkOrder.DataBean data=new WorkOrder.DataBean();
    private ArrayList<GAccessory> gAccessories=new ArrayList<>();//获得工厂返回的已选配件
    private int select_state=0;  //记录厂家寄件申请（1） 和自购件申请（2） 0为未选中
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

    @BindView(R.id.iv_manufacturers) //厂家寄件申请
    ImageView iv_manufacturers;
    @BindView(R.id.iv_selfbuying) //自购件
    ImageView iv_selfbuying;
    @BindView(R.id.tv_manufacturers)//厂家寄件申请
    TextView tv_manufacturers;
    @BindView(R.id.tv_selfbuying)//自购件
    TextView tv_selfbuying;
    /*订单属性*/

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
    @BindView(R.id.tv_order_details_state)
    TextView tv_order_details_state;
    /*订单属性*/



    /*服务和配件自定义dialog*/
    private CustomDialog_Add_Accessory customDialog_add_accessory;
    private CustomDialog_Add_Service customDialog_add_service;
    /*服务和配件自定义dialog*/


    /*  配件*/

    private TextView tv_order_details_add_accessories; //添加配件
    private List<Accessory> mList;   //存放返回的list
    private Map<Integer,FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> map=new HashMap<>(); //用于存放dialog里选择的配件
    private List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> fAcList= new ArrayList<>();// 用于存放预接单页面显示的数据
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
    private Map<Integer,FService.OrderServiceStrBean.OrderServiceBean> map_service;
    private List<FService.OrderServiceStrBean.OrderServiceBean> fList_service;
    private RecyclerView recyclerView_custom_add_service;
    private RecyclerView recyclerView_Pre_add_service;
    private Pre_order_Add_Service_Adapter mPre_order_Add_Service_Adapter; //预接单的adpter
    private Service mService;
    private FService.OrderServiceStrBean.OrderServiceBean mfService;
    private Add_Service_Adapter mAdd_Service_Adapter;


    /*服务*/


       /*扫码*/

        @BindView(R.id.et_express_sweep_code)
         EditText et_express_sweep_code;
        @BindView(R.id.img_express_sweep_code)
        ImageView img_express_sweep_code;
        @BindView(R.id.tv_express_sweep_code)
         TextView tv_express_sweep_code;

       /*扫码*/
       /*震动*/
        Vibrator vibrator;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initValidata();


    }


    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_accessories;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initView() {
        mList=new ArrayList<>();

        //fList=new ArrayList<>();
        mList_service=new ArrayList<>();
        map_service=new HashMap<>();


        customDialog_add_accessory=new CustomDialog_Add_Accessory(mActivity);
        customDialog_add_service=new CustomDialog_Add_Service(mActivity);
        //tv_actionbar_title=findViewById(R.id.tv_actionbar_title);
        rg_order_details_for_remote_fee=findViewById(R.id.rg_order_details_for_remote_fee);
        tv_order_details_add_accessories=findViewById(R.id.tv_order_details_add_accessories);//添加配件
        recyclerView_Pre_add_accessories=findViewById(R.id.recyclerView_add_accessories); //预接单recyclerview
        tv_order_detail_add_service=findViewById(R.id.tv_order_detail_add_service);
        recyclerView_Pre_add_service=findViewById(R.id.tv_recyclerView_Pre_add_service);//预接单recyclerview
         vibrator = (Vibrator)this.getSystemService(this.VIBRATOR_SERVICE);

        //接收传来的OrderID
        orderID = getIntent().getStringExtra("OrderID");
        mPresenter.GetOrderInfo(orderID);
        mPresenter.GetFactoryAccessory();
        mPresenter.GetFactoryService();



        }

    @Override
    protected void setListener() {
        ll_return.setOnClickListener(new CustomOnclickListnaer());
        tv_order_details_add_accessories.setOnClickListener(new CustomOnclickListnaer());
        tv_order_detail_add_service.setOnClickListener(new CustomOnclickListnaer());
        img_express_sweep_code.setOnClickListener(new CustomOnclickListnaer());
        tv_express_sweep_code.setOnClickListener(new CustomOnclickListnaer());
        tv_detail_submit.setOnClickListener(new CustomOnclickListnaer());
/*

        tv_order_details_status.setOnClickListener(new CustomOnclickListnaer());
        tv_order_details_adress.setOnClickListener(new CustomOnclickListnaer());
        tv_total_price.setOnClickListener(new CustomOnclickListnaer());
        tv_order_details_state.setOnClickListener(new CustomOnclickListnaer());
*/

        iv_manufacturers.setOnClickListener(new CustomOnclickListnaer());
        iv_selfbuying.setOnClickListener(new CustomOnclickListnaer());
        tv_manufacturers.setOnClickListener(new CustomOnclickListnaer());
        tv_selfbuying.setOnClickListener(new CustomOnclickListnaer());

        /*申请远程费*/
        rg_order_details_for_remote_fee.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){

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
        tv_actionbar_title.setText("服务中");
        tv_order_details_state.setText("服 务 中");



    }




    public class CustomOnclickListnaer implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.iv_manufacturers://厂家寄件申请
                case R.id.tv_manufacturers:
                    if (iv_manufacturers.isSelected()){ //如果是选中状态


                            iv_manufacturers.setSelected(false);
                            iv_selfbuying.setSelected(false);
                            Log.d("====>","取消了厂家寄件申请");
                            select_state=0;


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

                            iv_selfbuying.setSelected(false);
                            iv_manufacturers.setSelected(false);
                            Log.d("====>","取消了自购件申请");
                            select_state=0;


                    } else {
                        iv_selfbuying.setSelected(true);
                        iv_manufacturers.setSelected(false);
                        Log.d("====>","选中了自购件申请");
                        select_state=2;
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
                  Log.d("mlistmlist", String.valueOf(mList.size()));
                    customDialog_add_accessory.getWindow().setBackgroundDrawableResource(R.color.transparent);
                    customDialog_add_accessory.show();
                    // 设置宽度为屏宽、靠近屏幕底部。
                       Window window=customDialog_add_accessory.getWindow();
                       //最重要的一句话，一定要加上！要不然怎么设置都不行！
                   // window.setBackgroundDrawableResource(android.R.color.transparent);
                    WindowManager.LayoutParams wlp = window.getAttributes();
                    Display d = window.getWindowManager().getDefaultDisplay();
                    //获取屏幕宽
                    wlp.height=(d.getHeight());
                    wlp.width =(d.getWidth());
                    //宽度按屏幕大小的百分比设置，这里我设置的是全屏显示
                    wlp.gravity = Gravity.CENTER;
/*
                    if (wlp.gravity == Gravity.BOTTOM)
                        wlp.y = 0;
                    //如果是底部显示，则距离底部的距离是0*/
                    window.setAttributes(wlp);

                    /*选择配件*/


                    recyclerView_custom_add_accessory=customDialog_add_accessory.findViewById(R.id.recyclerView_custom_add_accessory);
                    recyclerView_custom_add_accessory.setLayoutManager(new LinearLayoutManager(mActivity));
                    mAdd_Ac_Adapter=new Add_Ac_Adapter(R.layout.item_addaccessory,mList);
                    recyclerView_custom_add_accessory.setAdapter(mAdd_Ac_Adapter);



                    mAdd_Ac_Adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @SuppressLint("MissingPermission")
                        @Override
                        public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                            ImageView img_ac_select = (ImageView)adapter.getViewByPosition(recyclerView_custom_add_accessory, position, R.id.img_ac_select); //选中图片
                            ImageView img_ac_unselect = (ImageView)adapter.getViewByPosition(recyclerView_custom_add_accessory, position, R.id.img_ac_unselect);//未选中图片
                            final adderView adderView =(adderView)adapter.getViewByPosition(recyclerView_custom_add_accessory, position, R.id.adderView);
                            //进入添加配件dialog 判断是否已经选了配件  有数据说明是第二次进入 没数据说明是第一次进入


                            switch (view.getId()){
                                case R.id.img_ac_unselect:
                                case R.id.img_ac_select:
                                case R.id.tv_accessory_name:


                                    if(((Accessory)(adapter.getData().get(position))).isIscheck()==false){ //如果是为选中的状态点击  变为红色 选中状态 出现 数量选择器

                                        //viewadd.setVisibility(View.VISIBLE); //数量选择器出现
                                        adderView.setVisibility(View.VISIBLE);
                                        img_ac_unselect.setVisibility(View.INVISIBLE);
                                        img_ac_select.setVisibility(View.VISIBLE);
                                        //ischeck[position]=true;
                                        mList.get(position).setIscheck(true);
                                        //没选选择默认数量为1
                                        mAccessory=(Accessory)adapter.getItem(position);

                                        mfAccessory=new FAccessory.OrderAccessoryStrBean.OrderAccessoryBean();
                                        mfAccessory.setFAccessoryName(mAccessory.getAccessoryName());
                                        mfAccessory.setQuantity("1"); //默认数字为1
                                        mfAccessory.setDiscountPrice(mAccessory.getAccessoryPrice());
                                        mList.get(position).setCheckedcount(1);
                                        map.put(position,mfAccessory);

                                        //选择了数量根据输入框中的来
                                        vibrator.vibrate(50);

                                        adderView.setOnValueChangeListene(new adderView.OnValueChangeListener() {
                                            @Override
                                            public void onValueChange(int value) {
                                                //没选选择时间默认数量为1

                                                mAccessory=(Accessory)adapter.getItem(position);
                                                mfAccessory=new FAccessory.OrderAccessoryStrBean.OrderAccessoryBean();
                                                mfAccessory.setFAccessoryName(mAccessory.getAccessoryName());
                                                mfAccessory.setQuantity(String.valueOf(value));
                                                mfAccessory.setDiscountPrice(mAccessory.getAccessoryPrice());
                                               // Log.d("getQuantitys的个数00",mfAccessory.getQuantity());
                                                mList.get(position).setCheckedcount(value);
                                                map.put(position,mfAccessory);
                                            }
                                        });




                                    }else {
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
                             fAcList=new ArrayList<>(map.values());

                            recyclerView_Pre_add_accessories.setLayoutManager(new LinearLayoutManager(mActivity));
                            mPre_order_add_ac_adapter=new Pre_order_Add_Ac_Adapter(R.layout.item_pre_order_add_accessories,fAcList);
                            recyclerView_Pre_add_accessories.setAdapter(mPre_order_add_ac_adapter);

                               /*   recyclerView_Pre_add_accessories.setLayoutManager(new LinearLayoutManager(mActivity));
                             mPre_order_add_ac_adapter=new Pre_order_Add_Ac_Adapter(R.layout.item_pre_order_add_accessories,fAcList);
                             recyclerView_Pre_add_accessories.setAdapter(mPre_order_add_ac_adapter);*/
                             customDialog_add_accessory.dismiss();
                            //Log.d("所选配件的数量", String.valueOf(fAcList.size()));

                           // for (int i=0;i<fAcList.size();i++){
                           //     Log.d("配件的数量:",fAcList.get(i).getQuantity());
                           // }
                           tv_total_price.setText("服务金额:¥"+gettotalPrice(fAcList,fList_service));

                            /*删除配件*/
                            mPre_order_add_ac_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                @Override
                                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                    switch (view.getId()){
                                        case R.id.iv_accessories_delete:
                                            String AccessoryName=null; //用于保存配件名称
                                            boolean is_exist=false;// 用于保存是否存在 map
                                            int keymap = 0;   //保存map的箭
                                            for (int i=0;i<mList.size();i++){
                                                //先比较产品名字
                                                if (   ((FAccessory.OrderAccessoryStrBean.OrderAccessoryBean)adapter.getData().get(position)).getFAccessoryName().equals(mList.get(i).getAccessoryName())){
                                                   mList.get(i).setIscheck(false);
                                                    AccessoryName=mList.get(i).getAccessoryName();
                                                    mList.get(i).setCheckedcount(1);
                                                }
                                            }


                                            Log.d("AccessoryName的值是",AccessoryName);

                                            for (Integer key : map.keySet()) {
                                                System.out.println("key= "+ key + " and value= " + map.get(key));

                                            if (map.get(key).getFAccessoryName().equals(AccessoryName)){
                                                is_exist=true;
                                                keymap=key;
                                            }
                                            }
                                            if (is_exist){
                                                map.remove(keymap);
                                                adapter.remove(position);
                                            }

                                            Log.d("map里面的值", String.valueOf(map.size()));
                                            tv_total_price.setText("服务金额:¥"+gettotalPrice(fAcList,fList_service));

                                            break;
                                    }
                                }
                            });
                                 for (int key : map.keySet()){
                                     System.out.println("选择了"+map.get(key).getFAccessoryName()+"配件"+"数量"+map.get(key).getQuantity()+"总价格"+map.get(key).getDiscountPrice());
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
                         Window window1=customDialog_add_service.getWindow();
                         //最重要的一句话，一定要加上！要不然怎么设置都不行！
                         // window.setBackgroundDrawableResource(android.R.color.transparent);
                         WindowManager.LayoutParams wlp1 = window1.getAttributes();
                         Display d1 = window1.getWindowManager().getDefaultDisplay();
                         //获取屏幕宽
                         wlp1.height=(d1.getHeight());
                         wlp1.width =(d1.getWidth());
                         //宽度按屏幕大小的百分比设置，这里我设置的是全屏显示
                         wlp1.gravity = Gravity.CENTER;
/*
                    if (wlp.gravity == Gravity.BOTTOM)
                        wlp.y = 0;
                    //如果是底部显示，则距离底部的距离是0*/
                         window1.setAttributes(wlp1);


                         recyclerView_custom_add_service=customDialog_add_service.findViewById(R.id.recyclerView_custom_add_service);
                         recyclerView_custom_add_service.setLayoutManager(new LinearLayoutManager(mActivity));
                         mAdd_Service_Adapter=new Add_Service_Adapter(R.layout.item_addservice,mList_service);
                         recyclerView_custom_add_service.setAdapter(mAdd_Service_Adapter);

                        mAdd_Service_Adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @SuppressLint("MissingPermission")
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                ImageView img_add_service_select = (ImageView)adapter.getViewByPosition(recyclerView_custom_add_service, position, R.id.img_add_service_select); //选中图片
                                ImageView img_add_service_unselect = (ImageView)adapter.getViewByPosition(recyclerView_custom_add_service, position, R.id.img_add_service_unselect);//未选中图片

                                switch (view.getId()){
                                    case R.id.img_add_service_unselect:
                                    case R.id.img_add_service_select:
                                    case R.id.tv_add_service_name:
                                        if (((Service)(adapter.getData().get(position))).isIschecked()==false){ //如果是为选中的状态点击  变为红色 选中状态 出现 数量选择器
                                            //viewadd.setVisibility(View.VISIBLE); //数量选择器出现
                                            img_add_service_unselect.setVisibility(View.INVISIBLE);
                                            img_add_service_select.setVisibility(View.VISIBLE);
                                            mList_service.get(position).setIschecked(true);
                                            mService=(Service)adapter.getItem(position);
                                            mfService=new FService.OrderServiceStrBean.OrderServiceBean();
                                            mfService.setServiceName(mService.getFServiceName());
                                            mfService.setDiscountPrice(mService.getInitPrice());
                                            map_service.put(position,mfService);
                                            vibrator.vibrate(50);
                                        }else {
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
                                 fList_service=new ArrayList<>(map_service.values());
                                 recyclerView_Pre_add_service.setLayoutManager(new LinearLayoutManager(mActivity));
                                 mPre_order_Add_Service_Adapter=new Pre_order_Add_Service_Adapter(R.layout.item_add_service,fList_service);
                                 recyclerView_Pre_add_service.setAdapter(mPre_order_Add_Service_Adapter);
                                 customDialog_add_service.dismiss();
                                 tv_total_price.setText("服务金额:¥"+gettotalPrice(fAcList,fList_service));

                                 mPre_order_Add_Service_Adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                     @Override
                                     public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                         switch (view.getId()){
                                             case R.id.iv_service_delete:
                                                 String ServiceName=null; //用于保存配件名称
                                                 boolean is_exist=false;// 用于保存是否存在 map
                                                 int keymap = 0;   //保存map的箭
                                                 for (int i=0;i<mList_service.size();i++){
                                                     //先比较产品名字
                                                     if (((FService.OrderServiceStrBean.OrderServiceBean)adapter.getData().get(position)).getServiceName().equals(mList_service.get(i).getFServiceName())){
                                                         mList_service.get(i).setIschecked(false);
                                                         ServiceName=mList_service.get(i).getFServiceName();
                                                     }
                                                 }


                                                 Log.d("ServiceName的值是",ServiceName);

                                                 for (Integer key : map_service.keySet()) {

                                                     if (map_service.get(key).getServiceName().equals(ServiceName)){
                                                         is_exist=true;
                                                         keymap=key;
                                                     }
                                                 }
                                                 if (is_exist){
                                                     map_service.remove(keymap);
                                                     adapter.remove(position);
                                                 }

                                                 tv_total_price.setText("服务金额:¥"+gettotalPrice(fAcList,fList_service));


                                                 break;

                                         }

                                     }
                                 });

                             }
                         });


                         break;



                         case R.id.img_express_sweep_code:
                         case R.id.tv_express_sweep_code:
                             IntentIntegrator integrator = new IntentIntegrator(Order_Add_Accessories_Activity.this);
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

                                 break;


                         default:
                          break;

            }
        }
    }

    /*获取工单详情*/
    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {

        switch (baseResult.getStatusCode()){

            case 200:
                data=baseResult.getData();

              /*判断是选中了那个*/
                if (data.getAccessoryState()==null){//未选择
                    select_state=0;
                    iv_manufacturers.setSelected(false);
                    iv_selfbuying.setSelected(false);
                }else if (data.getAccessoryState().equals("0")){//工厂寄件申请
                    select_state=1;
                    iv_manufacturers.setSelected(true);
                    iv_selfbuying.setSelected(false);
                }else {//自购件
                    select_state=2;
                    iv_manufacturers.setSelected(false);
                    iv_selfbuying.setSelected(true);
                }



                tv_order_details_orderid.setText(data.getOrderID());
                tv_order_details_receiving_time.setText(data.getAudDate().replace("T"," ")); //将T替换为空格
                tv_order_details_reason.setText(data.getMemo());
                tv_order_details_product_name.setText(data.getCategoryName()+"/"+data.getBrandName()+"/"+data.getProductType());

                if (data.getTypeID()==1){//维修
                    tv_order_details_status.setText("维修");
                    tv_order_details_status.setBackgroundResource(R.color.color_custom_01);
                }else {
                    tv_order_details_status.setText("安装");
                    tv_order_details_status.setBackgroundResource(R.color.color_custom_04);
                }
                tv_order_details_adress.setText(data.getAddress());


               // Log.d("====>", String.valueOf(data.getOrderAccessroyDetail().size()));
                gAccessories.addAll(data.getOrderAccessroyDetail());

                if (!gAccessories.isEmpty()){


                  for (int i=0;i<gAccessories.size();i++){

                     mfAccessory=new FAccessory.OrderAccessoryStrBean.OrderAccessoryBean();
                     mfAccessory.setFAccessoryID(String.valueOf(gAccessories.get(i).getFAccessoryID()));
                     mfAccessory.setDiscountPrice(gAccessories.get(i).getDiscountPrice());
                     mfAccessory.setFAccessoryName(gAccessories.get(i).getFAccessoryName());
                     mfAccessory.setQuantity(String.valueOf(gAccessories.get(i).getQuantity()));
                      map.put(gAccessories.get(i).getFAccessoryID()-1,mfAccessory);

                    }
                    fAcList=new ArrayList<>(map.values());
                    //mPre_order_add_ac_adapter.notifyDataSetChanged();

                    recyclerView_Pre_add_accessories.setLayoutManager(new LinearLayoutManager(mActivity));
                    mPre_order_add_ac_adapter=new Pre_order_Add_Ac_Adapter(R.layout.item_pre_order_add_accessories,fAcList);
                    recyclerView_Pre_add_accessories.setAdapter(mPre_order_add_ac_adapter);


                }



                break;




            default:
                Log.d("detail",baseResult.getData().toString());
                break;
        }

    }




    /*获取工厂配件*/
    @Override
    public void GetFactoryAccessory(BaseResult<GetFactoryData<Accessory>> baseResult) {
                 switch (baseResult.getStatusCode()){
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
        switch (baseResult.getStatusCode()){
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
        switch (baseResult.getStatusCode()){
            case 200:
                break;
            default:
                break;
        }


    }

    @Override
    public void AddOrderService(BaseResult<Data> baseResult) {

    }

    @Override
    public void AddOrUpdateAccessoryServiceReturn(BaseResult<Data> baseResult) {

    }

    @Override
    public void UpdateSendOrderUpdateTime(BaseResult<Data> baseResult) {

    }

    @Override
    public void GetOrderAccessoryByOrderID(BaseResult<List<GAccessory>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                Log.d("===>","进入成功");
                break;
                default:
                    break;
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            String result = scanResult.getContents();
            et_express_sweep_code.setText("快递单号:"+result);
        }
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
                acprice = acprice + list.get(i).getDiscountPrice()*Double.parseDouble(list.get(i).getQuantity());
            }
            return acprice;
        }
        else if (list != null && list2 != null)//都有
        {
            for (int i = 0; i < list.size(); i++) {
                acprice = acprice + list.get(i).getDiscountPrice()*Double.parseDouble(list.get(i).getQuantity());
            }
            for (int i = 0; i < list2.size(); i++) {
                servicprice = servicprice + list2.get(i).getDiscountPrice();
            }
            return acprice + servicprice;

        } else
        { //都没有
            return 0;
        }


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

      /*  //判断用户是否点击了返回键
        if (keyCode==KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent();
            intent.putExtra("result", "pending_appointment");  //按了返回键返回已接待预约
            //设置返回数据
            Order_Add_Accessories_Activity.this.setResult(RESULT_OK,intent);
            Order_Add_Accessories_Activity.this.finish();
            return true;
        }*/

        return super.onKeyDown(keyCode, event);
    }
}
