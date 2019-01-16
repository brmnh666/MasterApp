package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.mvp.ui.activity.BaseActivity.BaseActivity;
import com.ying.administrator.masterappdemo.common.DefineView;

public class Order_details_Activity extends BaseActivity implements DefineView {
    private TextView tv_actionbar_title;
    private RadioGroup rg_order_details_for_remote_fee;


    private LinearLayout ll_Out_of_service_tv;
    private LinearLayout ll_Out_of_service_img;
    private LinearLayout ll_return;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_order_details);
        initView();
        initValidata();
        initListener();
    }

    @Override
    public void initView() {
        tv_actionbar_title=findViewById(R.id.tv_actionbar_title);
        rg_order_details_for_remote_fee=findViewById(R.id.rg_order_details_for_remote_fee);
        ll_Out_of_service_tv=findViewById(R.id.ll_Out_of_service_tv);
        ll_Out_of_service_img=findViewById(R.id.ll_Out_of_service_img);
        ll_return=findViewById(R.id.ll_return);
    }

    @Override
    public void initValidata() {
        tv_actionbar_title.setText("预接单");

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
    public class CustomOnclickListnaer implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ll_return:
                    Order_details_Activity.this.finish();
                    break;
            }
        }
    }
}
