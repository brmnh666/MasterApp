package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.AccessoryData;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.FAccessory;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.PendingOrderContract;
import com.ying.administrator.masterappdemo.mvp.model.PendingOrderModel;
import com.ying.administrator.masterappdemo.mvp.presenter.PendingOrderPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.MyRecyclerAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Pre_order_Add_Ac_Adapter;
import com.ying.administrator.masterappdemo.widget.CustomDialog_Add_Accessory;

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
    private RecyclerView recyclerView_add_accessories;
    private Pre_order_Add_Ac_Adapter mPre_order_add_ac_adapter;
    private ArrayList<FAccessory> fList;

    CustomDialog_Add_Accessory customDialog_add_accessory=new CustomDialog_Add_Accessory(mActivity);
    private  Accessory accessory; //获取服务端 返回的数据 的model
    private  FAccessory fAccessory;//提交选择结果提交数据到服务端的model

    private List<Accessory> mList;   //存放返回的list
    private Map<Integer,FAccessory> map; //同
    private RecyclerView mrecyclerview; //添加配件弹窗的recyclerview
  //  private MyRecyclerAdapter adapter ; //添加配件弹窗的适配器
    private boolean[] ischeck; // 用于判断各个item是否被选择

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.layout.activity_order_details);
        initView();
        initValidata();
        //mPresenter.GetOrderInfo();
       // mPresenter.GetFactoryAccessory();

     /*   mrecyclerview=customDialog_add_accessory.findViewById(R.id.recyclerView_custom_add_accessory);
        adapter=new MyRecyclerAdapter(R.layout.item_addaccessory,mList);
        mrecyclerview.setLayoutManager(new LinearLayoutManager(mActivity));
        mrecyclerview.setAdapter(adapter);*/
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_order_details;
    }

    @Override
    protected void initData() {

       //mList= new ArrayList<>();
       //map=new HashMap<>();


        recyclerView_add_accessories=findViewById(R.id.recyclerView_add_accessories);
        /*将添加配件获取的数据进行绑定*/
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("BUNDLE");
        //获取返回的bundle
        if (bundle==null){
            return;
        }else {
             fList = (ArrayList<FAccessory>) bundle.getSerializable("ARRAYLIST");
            //数据不为空的时候
            recyclerView_add_accessories.setLayoutManager(new LinearLayoutManager(mActivity));
            mPre_order_add_ac_adapter=new Pre_order_Add_Ac_Adapter(R.layout.item_pre_order_add_accessories,fList);
            recyclerView_add_accessories.setAdapter(mPre_order_add_ac_adapter);

            mPre_order_add_ac_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (view.getId()){
                        case R.id.iv_accessories_delete:
                            adapter.remove(position);
                            break;


                    }
                }
            });


        }

    }

    @Override
    public void initView() {
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

        //接收传来的OrderID
        String orderID = getIntent().getStringExtra("OrderID");
        mPresenter.GetOrderInfo(orderID);
        mPresenter.GetFactoryAccessory();

    }

    @Override
    protected void setListener() {
        ll_return.setOnClickListener(new CustomOnclickListnaer());
        rl_select_time.setOnClickListener(new CustomOnclickListnaer());
        tv_order_details_add_accessories.setOnClickListener(new CustomOnclickListnaer());
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

    @Override
    public void GetFactoryAccessory(BaseResult<AccessoryData<Accessory>> baseResult) {

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
                    startActivity(new Intent(Order_details_Activity.this,AddAccessoryActivity.class));

                    //customDialog_add_accessory.show();


                    break;


                    default:
                        break;

            }
        }
    }
}
