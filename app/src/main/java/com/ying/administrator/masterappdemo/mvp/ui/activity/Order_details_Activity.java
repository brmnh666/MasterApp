package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.FService;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.entity.FAccessory;
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


/*预接单详情页*/
public class Order_details_Activity extends BaseActivity<PendingOrderPresenter, PendingOrderModel> implements  PendingOrderContract.View {
    private TextView tv_actionbar_title;
    private RadioGroup rg_order_details_for_remote_fee;

    private WorkOrder.DataBean data=new WorkOrder.DataBean();
    private LinearLayout ll_Out_of_service_tv;
    private LinearLayout ll_Out_of_service_img;
    private LinearLayout ll_return;
    private LinearLayout rl_select_time; //选择时间
    private TextView tv_select_time; //显示时间
    private TextView tv_order_details_receiving_time; //工单接收时间
    private TextView tv_order_details_orderid; //工单号
    private TextView tv_order_details_reason;//故障原因
    private TextView tv_order_details_product_name;//产品名称
    private TextView tv_order_details_status; //安装维修状态
    private TextView tv_order_details_adress; //地址
    private RadioGroup rg_order_details_add_accessories; //添加配件
    private RadioButton rb_order_details_manufacturer; //厂家寄件
    private RadioButton rb_order_details_oneself; //自购件
    private TextView tv_order_details_add_accessories; //添加配件
    private TextView tv_order_detail_add_service;//添加服务
    private RecyclerView recyclerView_Pre_add_accessories; //预接单的  RecyclerView
    private Pre_order_Add_Ac_Adapter mPre_order_add_ac_adapter; //预接单 的adater
    private Pre_order_Add_Service_Adapter mPre_order_Add_Service_Adapter; //预接单的adpter
    private CustomDialog_Add_Accessory customDialog_add_accessory;

    private CustomDialog_Add_Service customDialog_add_service;
    private Accessory mAccessory;
    private FAccessory mfAccessory;



    private List<Accessory> mList;   //存放返回的list
    private Map<Integer,FAccessory> map; //用于存放dialog里选择的配件
    private List<FAccessory> fList;// 用于存放预接单页面显示的数据
    private boolean[] ischeck; // 用于判断各个item是否被选择
    private RecyclerView recyclerView_custom_add_accessory;


    private List<Service> mList_service;
    private Map<Integer,FService> map_service;
    private List<FService> fList_service;
    private boolean[] ischeck_service;
    private RecyclerView recyclerView_custom_add_service;
    private RecyclerView recyclerView_Pre_add_service;
    private Service mService;
    private FService mfService;

    private Add_Ac_Adapter mAdd_Ac_Adapter;
    private Add_Service_Adapter mAdd_Service_Adapter;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.layout.activity_order_details);
        initView();
        initValidata();
        //mPresenter.GetOrderInfo();
        initdelete();



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
        mList=new ArrayList<>();
        map=new HashMap<>();
        //fList=new ArrayList<>();
        mList_service=new ArrayList<>();
        map_service=new HashMap<>();


        customDialog_add_accessory=new CustomDialog_Add_Accessory(mActivity);
        customDialog_add_service=new CustomDialog_Add_Service(mActivity);


        tv_actionbar_title=findViewById(R.id.tv_actionbar_title);
        rg_order_details_for_remote_fee=findViewById(R.id.rg_order_details_for_remote_fee);
        ll_Out_of_service_tv=findViewById(R.id.ll_Out_of_service_tv);
        ll_Out_of_service_img=findViewById(R.id.ll_Out_of_service_img);
        ll_return=findViewById(R.id.ll_return);
        rl_select_time=findViewById(R.id.rl_select_time);
        tv_select_time=findViewById(R.id.tv_select_time);
        tv_order_details_receiving_time=findViewById(R.id.tv_order_details_receiving_time);//接单时间
        tv_order_details_orderid=findViewById(R.id.tv_order_details_orderid);//工单号
        tv_order_details_reason=findViewById(R.id.tv_order_details_reason);//故障原因
        tv_order_details_product_name=findViewById(R.id.tv_order_details_product_name);//产品名称
        tv_order_details_status=findViewById(R.id.tv_order_details_status);//安装维修状态
        tv_order_details_adress=findViewById(R.id.tv_order_details_adress); //地址
        rg_order_details_add_accessories=findViewById(R.id.rg_order_details_add_accessories);//添加配件
        rb_order_details_manufacturer=findViewById(R.id.rb_order_details_manufacturer);
        rb_order_details_oneself=findViewById(R.id.rb_order_details_oneself);
        tv_order_details_add_accessories=findViewById(R.id.tv_order_details_add_accessories);//添加配件
        recyclerView_Pre_add_accessories=findViewById(R.id.recyclerView_add_accessories); //预接单recyclerview
        tv_order_detail_add_service=findViewById(R.id.tv_order_detail_add_service);
        recyclerView_Pre_add_service=findViewById(R.id.tv_recyclerView_Pre_add_service);//预接单recyclerview

        //接收传来的OrderID
        String orderID = getIntent().getStringExtra("OrderID");
        mPresenter.GetOrderInfo(orderID);

        mPresenter.GetFactoryAccessory();
        mPresenter.GetFactoryService();

    }

    @Override
    protected void setListener() {
        ll_return.setOnClickListener(new CustomOnclickListnaer());
        rl_select_time.setOnClickListener(new CustomOnclickListnaer());
        tv_order_details_add_accessories.setOnClickListener(new CustomOnclickListnaer());
        tv_order_detail_add_service.setOnClickListener(new CustomOnclickListnaer());
         /*添加配件*/
        rg_order_details_add_accessories.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
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
        tv_actionbar_title.setText("预接单");
        rl_select_time.setOnClickListener(new CustomOnclickListnaer());
       // tv_select_time.setOnClickListener(new CustomOnclickListnaer());

    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {

        switch (baseResult.getStatusCode()){

            case 200:
                data=baseResult.getData();
               // Log.d("getOrderIDgetOrderID",data.getOrderID()+" "+data.getMemo()+" "+data.getBrandName());
                tv_order_details_orderid.setText(data.getOrderID());
                tv_order_details_receiving_time.setText(data.getAudDate().replace("T"," ")); //将T替换为空格
                tv_order_details_reason.setText(data.getMemo());
                tv_order_details_product_name.setText(data.getCategoryName()+"/"+data.getBrandName()+"/"+data.getProductType());

                if (data.getTypeID().equals("1")){//维修
                    tv_order_details_status.setText("维修");
                    tv_order_details_status.setBackgroundResource(R.color.color_custom_01);
                }else {
                    tv_order_details_status.setText("安装");
                    tv_order_details_status.setBackgroundResource(R.color.color_custom_04);
                }
                tv_order_details_adress.setText(data.getAddress());

                break;

                default:
                    Log.d("detail",baseResult.getData().toString());
                  //  data=null;
                    break;
        }

    }




    public class CustomOnclickListnaer implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ll_return:
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

                    if (!map.isEmpty()){
                       fList.clear();
                       map.clear();
                    }

                    tv_order_details_add_accessories.setText("重新添加");
                    //startActivity(new Intent(Order_details_Activity.this,AddAccessoryActivity.class));

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
                        @Override
                        public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                            ImageView img_ac_select = (ImageView)adapter.getViewByPosition(recyclerView_custom_add_accessory, position, R.id.img_ac_select); //选中图片
                            ImageView img_ac_unselect = (ImageView)adapter.getViewByPosition(recyclerView_custom_add_accessory, position, R.id.img_ac_unselect);//未选中图片
                            adderView adderView =(adderView)adapter.getViewByPosition(recyclerView_custom_add_accessory, position, R.id.adderView);



                            //进入添加配件dialog 判断是否已经选了配件  有数据说明是第二次进入 没数据说明是第一次进入


                            switch (view.getId()){
                                case R.id.img_ac_unselect:
                                case R.id.img_ac_select:
                                case R.id.tv_accessory_name:


                                    if (ischeck[position]==false){ //如果是为选中的状态点击  变为红色 选中状态 出现 数量选择器
                                        //viewadd.setVisibility(View.VISIBLE); //数量选择器出现
                                        adderView.setVisibility(View.VISIBLE);
                                        img_ac_unselect.setVisibility(View.INVISIBLE);
                                        img_ac_select.setVisibility(View.VISIBLE);
                                        ischeck[position]=true;

                                        //没选选择时间默认数量为1
                                        mAccessory=(Accessory)adapter.getItem(position);
                                        mfAccessory=new FAccessory();
                                        mfAccessory.setFAccessoryName(mAccessory.getAccessoryName());
                                        mfAccessory.setQuantity("1"); //默认数字为1
                                        mfAccessory.setDiscountPrice(mAccessory.getAccessoryPrice());
                                        map.put(position,mfAccessory);
                                        //选择了时间数量根据输入框中的来
                                        adderView.setOnValueChangeListene(new adderView.OnValueChangeListener() {
                                            @Override
                                            public void onValueChange(int value) {
                                                //没选选择时间默认数量为1
                                                mAccessory=(Accessory)adapter.getItem(position);
                                                mfAccessory=new FAccessory();
                                                mfAccessory.setFAccessoryName(mAccessory.getAccessoryName());
                                                mfAccessory.setQuantity(String.valueOf(value));
                                                mfAccessory.setDiscountPrice(mAccessory.getAccessoryPrice()*value);
                                              map.put(position,mfAccessory);
                                            }
                                        });

                                    }else {
                                        //viewadd.setVisibility(View.INVISIBLE); //数量选择器消失
                                        adderView.setVisibility(View.INVISIBLE);
                                        img_ac_unselect.setVisibility(View.VISIBLE);
                                        img_ac_select.setVisibility(View.INVISIBLE);
                                        adderView.setValue(1); //但用户取消时将值设置为默认为1
                                        ischeck[position]=false;
                                        //map.remove(position);

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

                            fList=new ArrayList<>(map.values());
                            recyclerView_Pre_add_accessories.setLayoutManager(new LinearLayoutManager(mActivity));
                             mPre_order_add_ac_adapter=new Pre_order_Add_Ac_Adapter(R.layout.item_pre_order_add_accessories,fList);
                            recyclerView_Pre_add_accessories.setAdapter(mPre_order_add_ac_adapter);
                            customDialog_add_accessory.dismiss();

                            /*删除配件*/
                            mPre_order_add_ac_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                @Override
                                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                    switch (view.getId()){
                                        case R.id.iv_accessories_delete:

                                            adapter.remove(position);
                                            Log.d("positionposition", String.valueOf(position));
                                            //fList.remove(position);

                                            Log.d("flistflistflist", String.valueOf(fList.size()));
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
                         if (!map_service.isEmpty()){
                             fList_service.clear();
                             map_service.clear();
                         }

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
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                ImageView img_add_service_select = (ImageView)adapter.getViewByPosition(recyclerView_custom_add_service, position, R.id.img_add_service_select); //选中图片
                                ImageView img_add_service_unselect = (ImageView)adapter.getViewByPosition(recyclerView_custom_add_service, position, R.id.img_add_service_unselect);//未选中图片

                                switch (view.getId()){
                                    case R.id.img_add_service_unselect:
                                    case R.id.img_add_service_select:
                                    case R.id.tv_add_service_name:


                                        if (ischeck_service[position]==false){ //如果是为选中的状态点击  变为红色 选中状态 出现 数量选择器
                                            //viewadd.setVisibility(View.VISIBLE); //数量选择器出现
                                            img_add_service_unselect.setVisibility(View.INVISIBLE);
                                            img_add_service_select.setVisibility(View.VISIBLE);
                                            ischeck_service[position]=true;

                                            mService=(Service)adapter.getItem(position);
                                            mfService=new FService();
                                            //服务 模拟
                                            mfService.setOrderServiceStr(mService.getFServiceName());
                                            map_service.put(position,mfService);


                                        }else {
                                            img_add_service_unselect.setVisibility(View.VISIBLE);
                                            img_add_service_select.setVisibility(View.INVISIBLE);
                                            ischeck_service[position]=false;

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

                                 mPre_order_Add_Service_Adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                     @Override
                                     public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                         switch (view.getId()){
                                             case R.id.iv_service_delete:
                                                 adapter.remove(position);
                                                 break;

                                         }

                                     }
                                 });

                             }
                         });













                         break;

                    default:
                        break;

            }
        }
    }


    private void initdelete() {

    }

    /*获取工厂配件*/
    @Override
    public void GetFactoryAccessory(BaseResult<GetFactoryData<Accessory>> baseResult) {
                 switch (baseResult.getStatusCode()){
                     case 200:
                          mList.clear();
                         mList.addAll(baseResult.getData().getItem1());
                         ischeck=new boolean[mList.size()];
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
                ischeck_service=new boolean[mList_service.size()];
                Log.d("ischeck_service", String.valueOf(mList_service.size()));
                break;



            default:
                break;


        }
    }
/*
    @Override
    protected void onDestroy() {
        super.onDestroy();
        customDialog_add_accessory.dismiss();

    }*/
}
