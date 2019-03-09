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
import com.ying.administrator.masterappdemo.entity.GService;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
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
    private WorkOrder.DataBean data=new WorkOrder.DataBean();
    private ArrayList<GAccessory> gAccessories=new ArrayList<>();//获得工厂返回的已选配件
    private ArrayList<GService> gServices=new ArrayList<>(); //获取工厂端返回的已选服务
    private int select_state=0;  //记录厂家寄件申请（1） 和自购件申请（2） 0为未选中
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
    private List<Accessory> mList=new ArrayList<>();   //存放返回的list
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
    private List<Service> mList_service=new ArrayList<>(); //获取传来的服务列表
    private Map<Integer,FService.OrderServiceStrBean.OrderServiceBean> map_service =new HashMap<>();//用于存放dialog里选择的配件
    private List<FService.OrderServiceStrBean.OrderServiceBean> fList_service =new ArrayList<>();// 用于存放预接单页面显示的数据
    private RecyclerView recyclerView_custom_add_service;
    private RecyclerView recyclerView_Pre_add_service;
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

    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_accessories;
    }

    @Override
    protected void initData() {

    }




    @Override
    public void initView() {
        tv_order_details_state.setText("服 务 中");
        tv_actionbar_title.setText("服务中");
        customDialog_add_accessory=new CustomDialog_Add_Accessory(mActivity);
        customDialog_add_service=new CustomDialog_Add_Service(mActivity);
        tv_order_details_add_accessories=findViewById(R.id.tv_order_details_add_accessories);//添加配件
        recyclerView_Pre_add_accessories=findViewById(R.id.recyclerView_add_accessories); //预接单recyclerview
        tv_order_detail_add_service=findViewById(R.id.tv_order_detail_add_service);
        recyclerView_Pre_add_service=findViewById(R.id.tv_recyclerView_Pre_add_service);//预接单recyclerview
         vibrator = (Vibrator)this.getSystemService(this.VIBRATOR_SERVICE);
        //接收传来的OrderID
        orderID = getIntent().getStringExtra("OrderID");
        mPresenter.GetFactoryAccessory();
        mPresenter.GetFactoryService();
        mPresenter.GetOrderInfo(orderID);



    }

    @Override
    protected void setListener() {
        ll_return.setOnClickListener(new CustomOnclickListnaer());
        tv_order_details_add_accessories.setOnClickListener(new CustomOnclickListnaer());
        tv_order_detail_add_service.setOnClickListener(new CustomOnclickListnaer());
       /* img_express_sweep_code.setOnClickListener(new CustomOnclickListnaer());
        tv_express_sweep_code.setOnClickListener(new CustomOnclickListnaer());*/

        tv_detail_submit.setOnClickListener(new CustomOnclickListnaer());
        iv_manufacturers.setOnClickListener(new CustomOnclickListnaer());
        iv_selfbuying.setOnClickListener(new CustomOnclickListnaer());
        tv_manufacturers.setOnClickListener(new CustomOnclickListnaer());
        tv_selfbuying.setOnClickListener(new CustomOnclickListnaer());
        tv_detail_submit.setOnClickListener(new CustomOnclickListnaer());






    }







    public class CustomOnclickListnaer implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.iv_manufacturers://厂家寄件申请
                case R.id.tv_manufacturers:
                    if (iv_manufacturers.isSelected()){ //如果是选中状态




                            if (!fAcList.isEmpty()){
                                Toast.makeText(Order_Add_Accessories_Activity.this,"菜单仍有配件",Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(Order_Add_Accessories_Activity.this,"菜单仍有配件",Toast.LENGTH_SHORT).show();
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
                   /* Intent intent = new Intent();
                    intent.putExtra("result", "pending_appointment");  //按了返回键返回已接待预约
                    //设置返回数据
                    Order_Add_Accessories_Activity.this.setResult(RESULT_OK,intent);*/
                    Order_Add_Accessories_Activity.this.finish();
                    break;

                case R.id.tv_order_details_add_accessories: //添加配件

                    if (select_state==0){
                        Toast.makeText(Order_Add_Accessories_Activity.this,"请选择配件类型",Toast.LENGTH_SHORT).show();
                    }else {
                        customDialog_add_accessory.getWindow().setBackgroundDrawableResource(R.color.transparent);
                        customDialog_add_accessory.show();
                        Window window=customDialog_add_accessory.getWindow();
                        WindowManager.LayoutParams wlp = window.getAttributes();
                        Display d = window.getWindowManager().getDefaultDisplay();
                        wlp.height=(d.getHeight());
                        wlp.width =(d.getWidth());
                        wlp.gravity = Gravity.CENTER;
                        window.setAttributes(wlp);


                    //*添加配件弹出dialog*//*
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
                                        adderView.setVisibility(View.VISIBLE);
                                        img_ac_unselect.setVisibility(View.INVISIBLE);
                                        img_ac_select.setVisibility(View.VISIBLE);
                                        mList.get(position).setIscheck(true);
                                        //没选选择默认数量为1
                                        mAccessory=(Accessory)adapter.getItem(position);
                                        mfAccessory=new FAccessory.OrderAccessoryStrBean.OrderAccessoryBean();
                                        mfAccessory.setFAccessoryID(mAccessory.getFAccessoryID());
                                        mfAccessory.setFAccessoryName(mAccessory.getAccessoryName());
                                        mfAccessory.setQuantity("1"); //默认数字为1
                                        mfAccessory.setDiscountPrice(mAccessory.getAccessoryPrice());
                                        mfAccessory.setSendState("N");
                                        mfAccessory.setRelation("");
                                        mfAccessory.setIsPay("N");
                                        mList.get(position).setCheckedcount(1);
                                        map.put(position,mfAccessory);
                                        //选择了数量根据输入框中的来
                                        vibrator.vibrate(50);
                                        adderView.setOnValueChangeListene(new adderView.OnValueChangeListener() {
                                            @Override
                                            public void onValueChange(int value) {
                                                vibrator.vibrate(50);
                                                //没选选择时间默认数量为1
                                                mAccessory=(Accessory)adapter.getItem(position);
                                                mfAccessory=new FAccessory.OrderAccessoryStrBean.OrderAccessoryBean();
                                                mfAccessory.setFAccessoryID(mAccessory.getFAccessoryID());
                                                mfAccessory.setFAccessoryName(mAccessory.getAccessoryName());
                                                mfAccessory.setQuantity(String.valueOf(value));
                                                mfAccessory.setDiscountPrice(mAccessory.getAccessoryPrice());
                                                mfAccessory.setSendState("N");
                                                mfAccessory.setRelation("");
                                                mfAccessory.setIsPay("N");
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
                            customDialog_add_accessory.dismiss();
                           tv_total_price.setText("服务金额:¥"+gettotalPrice(fAcList,fList_service));

                            /*删除配件*/
                            mPre_order_add_ac_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                @SuppressLint("MissingPermission")
                                @Override
                                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                    switch (view.getId()){
                                        case R.id.iv_accessories_delete:
                                            String AccessoryID=null; //用于保存配件名称
                                            boolean is_exist=false;// 用于保存是否存在 map
                                            int keymap = 0;   //保存map的箭
                                           for (int i=0;i<mList.size();i++){
                                                //先比较产品名字
                                                if (((FAccessory.OrderAccessoryStrBean.OrderAccessoryBean)adapter.getData().get(position)).getFAccessoryID().equals(mList.get(i).getFAccessoryID())){
                                                    mList.get(i).setIscheck(false);
                                                    AccessoryID=mList.get(i).getFAccessoryID();
                                                    mList.get(i).setCheckedcount(1);
                                                }
                                            }

                                           for (Integer key : map.keySet()) {
                                                System.out.println("key= "+ key + " and value= " + map.get(key));

                                            if (map.get(key).getFAccessoryID().equals(AccessoryID)){
                                                is_exist=true;
                                                keymap=key;
                                            }
                                            }
                                           if (is_exist){
                                                map.remove(keymap);
                                                adapter.remove(position);
                                               vibrator.vibrate(50);
                                            }
                                          Log.d("map里面的值", String.valueOf(map.size()));
                                            tv_total_price.setText("服务金额:¥"+gettotalPrice(fAcList,fList_service));
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

                         /*弹出添加配件dialog*/
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
                                            img_add_service_unselect.setVisibility(View.INVISIBLE);
                                            img_add_service_select.setVisibility(View.VISIBLE);
                                            mList_service.get(position).setIschecked(true);
                                            mService=(Service)adapter.getItem(position);
                                            mfService=new FService.OrderServiceStrBean.OrderServiceBean();
                                            mfService.setServiceID(mService.getFServiceID());
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

                         /*添加服务*/
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
                                                 String ServiceID=null; //用于保存配件名称
                                                 boolean is_exist=false;// 用于保存是否存在 map
                                                 int keymap = 0;   //保存map的箭
                                                 for (int i=0;i<mList_service.size();i++){
                                                     //先比较产品名字
                                                     if (((FService.OrderServiceStrBean.OrderServiceBean)adapter.getData().get(position)).getServiceID().equals(mList_service.get(i).getFServiceID())){
                                                         mList_service.get(i).setIschecked(false);
                                                         ServiceID=mList_service.get(i).getFServiceID();
                                                     }
                                                 }
                                                 for (Integer key : map_service.keySet()) {
                                                     if (map_service.get(key).getServiceID().equals(ServiceID)){
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



                             /*提交工单*/
                             case R.id.tv_detail_submit:
                                 if (fAcList.size()==0&&fList_service.size()==0){ //都没有添加只提交上门时间
                                    Toast.makeText(Order_Add_Accessories_Activity.this,"请选择配件或者服务",Toast.LENGTH_SHORT).show();
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
                                     sTotalAS.setOrderAccessoryStr(s1);
                                     sTotalAS.setOrderServiceStr(s2);
                                     sTotalAS.setReturnAccessoryMsg("1214124125125韵达快递");//模拟数据
                                     String s3 = gson.toJson(sTotalAS);
                                     Log.d("配件和服务有",s3);
                                     RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),s3);
                                     mPresenter.AddOrUpdateAccessoryServiceReturn(body);
                                 }




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
                Money=data.getOrderMoney()-data.getInitMoney();

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
                    recyclerView_Pre_add_accessories.setLayoutManager(new LinearLayoutManager(mActivity));
                    mPre_order_add_ac_adapter=new Pre_order_Add_Ac_Adapter(R.layout.item_pre_order_add_accessories,fAcList);
                    recyclerView_Pre_add_accessories.setAdapter(mPre_order_add_ac_adapter);

                    for (int i=0;i<mList.size();i++){
                          for (int j=0;j<fAcList.size();j++){
                              if (mList.get(i).getFAccessoryID().equals(fAcList.get(j).getFAccessoryID())){
                                  mList.get(i).setIscheck(true);
                                  mList.get(i).setCheckedcount(Integer.parseInt(fAcList.get(j).getQuantity()));
                              }
                          }
                      }

                    tv_total_price.setText("服务金额:¥"+gettotalPrice(fAcList,fList_service));
                    /*删除配件*/
                    mPre_order_add_ac_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @SuppressLint("MissingPermission")
                        @Override
                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                            switch (view.getId()){
                                case R.id.iv_accessories_delete:
                                    String AccessoryID=null; //用于保存配件名称
                                    boolean is_exist=false;// 用于保存是否存在 map
                                    int keymap = 0;   //保存map的箭
                                    for (int i=0;i<mList.size();i++){
                                        //先比较产品名字
                                        if (((FAccessory.OrderAccessoryStrBean.OrderAccessoryBean)adapter.getData().get(position)).getFAccessoryID().equals(mList.get(i).getFAccessoryID())){
                                            mList.get(i).setIscheck(false);
                                            AccessoryID=mList.get(i).getFAccessoryID();
                                            mList.get(i).setCheckedcount(1);
                                        }
                                    }



                                    for (Integer key : map.keySet()) {
                                        System.out.println("key= "+ key + " and value= " + map.get(key));

                                        if (map.get(key).getFAccessoryID().equals(AccessoryID)){
                                            is_exist=true;
                                            keymap=key;
                                        }
                                    }
                                    if (is_exist){
                                        map.remove(keymap);
                                        vibrator.vibrate(50);
                                        adapter.remove(position);
                                    }
                                    Log.d("map里面的值", String.valueOf(map.size()));
                                    tv_total_price.setText("服务金额:¥"+gettotalPrice(fAcList,fList_service));
                                    break;
                            }
                        }
                    });
                    //*删除配件*//*





                    }

                    /*服务*/
                gServices.addAll(data.getOrderServiceDetail());

                if (!gServices.isEmpty()){
                 for (int i=0;i<gServices.size();i++){
                     mfService=new FService.OrderServiceStrBean.OrderServiceBean();
                     mfService.setServiceID(String.valueOf(gServices.get(i).getServiceID()));
                     mfService.setServiceName(gServices.get(i).getServiceName());
                     mfService.setDiscountPrice(gServices.get(i).getDiscountPrice());
                     mfService.setPrice(gServices.get(i).getPrice());
                     map_service.put(gServices.get(i).getServiceID()-1,mfService);
                 }
                    fList_service=new ArrayList<>(map_service.values());
                    recyclerView_Pre_add_service.setLayoutManager(new LinearLayoutManager(mActivity));
                    mPre_order_Add_Service_Adapter=new Pre_order_Add_Service_Adapter(R.layout.item_add_service,fList_service);
                    recyclerView_Pre_add_service.setAdapter(mPre_order_Add_Service_Adapter);

                    for (int i=0;i<mList_service.size();i++){
                        for (int j=0;j<fList_service.size();j++){
                            if (mList_service.get(i).getFServiceID().equals(fList_service.get(j).getServiceID())){
                                mList_service.get(i).setIschecked(true);
                            }
                        }
                    }


                    //*删除服务*//*
                    mPre_order_Add_Service_Adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @SuppressLint("MissingPermission")
                        @Override
                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                            switch (view.getId()){
                                case R.id.iv_service_delete:
                                    String ServiceID=null; //用于保存配件名称
                                    boolean is_exist=false;// 用于保存是否存在 map
                                    int keymap = 0;   //保存map的箭
                                    for (int i=0;i<mList_service.size();i++){
                                        //先比较产品名字
                                        if (((FService.OrderServiceStrBean.OrderServiceBean)adapter.getData().get(position)).getServiceID().equals(mList_service.get(i).getFServiceID())){
                                            mList_service.get(i).setIschecked(false);
                                            ServiceID=mList_service.get(i).getFServiceID();
                                        }
                                    }
                                    for (Integer key : map_service.keySet()) {
                                        if (map_service.get(key).getServiceID().equals(ServiceID)){
                                            is_exist=true;
                                            keymap=key;
                                        }
                                    }
                                    if (is_exist){
                                        map_service.remove(keymap);
                                        vibrator.vibrate(50);
                                        adapter.remove(position);
                                    }

                                    tv_total_price.setText("服务金额:¥"+gettotalPrice(fAcList,fList_service));
                                    break;

                            }

                        }
                    });
                    //*删除服务*//*

                }


                tv_total_price.setText("服务金额:¥"+gettotalPrice(fAcList,fList_service));




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
                if (baseResult.getData().isItem1()){
                 Order_Add_Accessories_Activity.this.finish();
                }else {
                    Toast.makeText(Order_Add_Accessories_Activity.this, (CharSequence) baseResult.getData().getItem2(),Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }


    }
     /**/
    @Override
    public void AddOrderService(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){
                    Order_Add_Accessories_Activity.this.finish();
                }else {
                    Toast.makeText(Order_Add_Accessories_Activity.this, (CharSequence) baseResult.getData().getItem2(),Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /*配件和服务都有*/
    @Override
    public void AddOrUpdateAccessoryServiceReturn(BaseResult<Data> baseResult) {

        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){
                    Order_Add_Accessories_Activity.this.finish();
                }else {

                    Toast.makeText(Order_Add_Accessories_Activity.this, (CharSequence) baseResult.getData().getItem2(),Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Override
    public void UpdateSendOrderUpdateTime(BaseResult<Data> baseResult) {

    }

    @Override
    public void GetOrderAccessoryByOrderID(BaseResult<List<GAccessory>> baseResult) {
        switch (baseResult.getStatusCode()){
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



}
