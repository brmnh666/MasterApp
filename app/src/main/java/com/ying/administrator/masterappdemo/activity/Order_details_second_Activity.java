package com.ying.administrator.masterappdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.activity.BaseActivity.BaseActivity;
import com.ying.administrator.masterappdemo.common.DefineView;

public class Order_details_second_Activity extends BaseActivity implements DefineView {
    private TextView tv_actionbar_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_order_details_second);

    }

    @Override
    public void initView() {
        tv_actionbar_title=findViewById(R.id.tv_actionbar_title);

    }

    @Override
    public void initValidata() {
        tv_actionbar_title.setText("工单详情");

    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {

    }
}
