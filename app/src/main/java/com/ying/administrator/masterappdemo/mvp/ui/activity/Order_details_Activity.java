package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.DefineView;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.PendingOrderContract;
import com.ying.administrator.masterappdemo.mvp.model.PendingOrderModel;
import com.ying.administrator.masterappdemo.mvp.presenter.PendingOrderPresenter;

import org.feezu.liuli.timeselector.TimeSelector;

import java.text.SimpleDateFormat;
import java.util.Date;


/*预接单详情页*/
public class Order_details_Activity extends BaseActivity<PendingOrderPresenter, PendingOrderModel> implements DefineView, PendingOrderContract.View {
    private TextView tv_actionbar_title;
    private RadioGroup rg_order_details_for_remote_fee;

    private WorkOrder workOrder;
    private LinearLayout ll_Out_of_service_tv;
    private LinearLayout ll_Out_of_service_img;
    private LinearLayout ll_return;
    private RelativeLayout rl_select_time; //选择时间
    private TextView tv_select_time; //显示时间
    private TextView tv_order_details_receiving_time; //工单接收时间
    private TextView tv_order_details_orderid; //工单号
    private TextView tv_order_details_reason;//故障原因
    private TextView tv_order_details_product_name;//产品名称
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.layout.activity_order_details);
        initView();
        initValidata();
        initListener();
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
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void initValidata() {
        tv_actionbar_title.setText("预接单");
        rl_select_time.setOnClickListener(new CustomOnclickListnaer());
       // tv_select_time.setOnClickListener(new CustomOnclickListnaer());
        //接收传来的OrderID
        String orderID = getIntent().getStringExtra("OrderID");
        mPresenter.GetOrderInfo(orderID);

 /*       tv_order_details_orderid.setText(workOrder.getData().get(0).getOrderID()); //工单号
        tv_order_details_receiving_time.setText(workOrder.getData().get(0).getAudDate());//接单时间
        tv_order_details_reason.setText(workOrder.getData().get(0).getMemo()); //故障原因
        tv_order_details_product_name.setText(workOrder.getData().get(0).getCategoryName()+"/"+ //产品名称
                workOrder.getData().get(0).getBrandName()+"/"+
                workOrder.getData().get(0).getProductType());*/
    }

    @Override
    public void initListener() {
        ll_return.setOnClickListener(new CustomOnclickListnaer());
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

    @Override
    public void bindData() {

    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder> baseResult) {

        switch (baseResult.getStatusCode()){

            case 200:
                Log.d("detail",baseResult.getData().toString());
                workOrder= baseResult.getData();
                //  Log.d("getOrderID",workOrder.getData().get(0).getOrderID());
                break;

                default:
                    Log.d("detail",baseResult.getData().toString());
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

                    default:
                        break;

            }
        }
    }
}
