package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkOrderDetailsActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.img_actionbar_return)
    ImageView mImgActionbarReturn;
    @BindView(R.id.tv_actionbar_return)
    TextView mTvActionbarReturn;
    @BindView(R.id.ll_return)
    LinearLayout mLlReturn;
    @BindView(R.id.tv_actionbar_title)
    TextView mTvActionbarTitle;
    @BindView(R.id.img_actionbar_message)
    ImageView mImgActionbarMessage;
    @BindView(R.id.actionbar_layout)
    RelativeLayout mActionbarLayout;
    @BindView(R.id.tv_work_order_status)
    TextView mTvWorkOrderStatus;
    @BindView(R.id.tv_payment_method)
    TextView mTvPaymentMethod;
    @BindView(R.id.tv_order_time)
    TextView mTvOrderTime;
    @BindView(R.id.tv_work_order_number)
    TextView mTvWorkOrderNumber;
    @BindView(R.id.tv_cause_of_issue)
    TextView mTvCauseOfIssue;
    @BindView(R.id.tv_number)
    TextView mTvNumber;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.tv_product_type)
    TextView mTvProductType;
    @BindView(R.id.tv_service_address)
    TextView mTvServiceAddress;
    @BindView(R.id.rv_return_information)
    RecyclerView mRvReturnInformation;
    @BindView(R.id.rv_return)
    RecyclerView mRvReturn;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_work_order_details;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initView() {
        mTvActionbarTitle.setText("详情页");
//        mImgActionbarMessage.setVisibility(View.INVISIBLE);
    }


    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_return:
                finish();
                break;

        }

    }


}
