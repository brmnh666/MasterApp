package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AfterSaleDetailActivity extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.tv_status)
    TextView mTvStatus;
    @BindView(R.id.tv_status_two)
    TextView mTvStatusTwo;
    @BindView(R.id.tv_reason)
    TextView mTvReason;
    @BindView(R.id.tv_appeal)
    TextView mTvAppeal;
    @BindView(R.id.tv_appeal_reason)
    TextView mTvAppealReason;
    @BindView(R.id.tv_platform_intervention)
    TextView mTvPlatformIntervention;
    @BindView(R.id.ll_appeal)
    LinearLayout mLlAppeal;
    @BindView(R.id.ll_refuse)
    LinearLayout mLlRefuse;
    @BindView(R.id.iv_location)
    ImageView mIvLocation;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.ll_address)
    LinearLayout mLlAddress;
    @BindView(R.id.img_shop)
    ImageView mImgShop;
    @BindView(R.id.tv_shop)
    TextView mTvShop;
    @BindView(R.id.tv_specification)
    TextView mTvSpecification;
    @BindView(R.id.tv_count)
    TextView mTvCount;
    @BindView(R.id.tv_state)
    TextView mTvState;
    @BindView(R.id.tv_return_type)
    TextView mTvReturnType;
    @BindView(R.id.tv_return)
    TextView mTvReturn;
    @BindView(R.id.tv_dispose_time_type)
    TextView mTvDisposeTimeType;
    @BindView(R.id.tv_dispose_time)
    TextView mTvDisposeTime;
    @BindView(R.id.tv_process)
    TextView mTvProcess;
    @BindView(R.id.rl_process)
    RelativeLayout mRlProcess;
    @BindView(R.id.tv_shouhoulaixing)
    TextView mTvShouhoulaixing;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.tv_tuikuanjine)
    TextView mTvTuikuanjine;
    @BindView(R.id.tv_return_money)
    TextView mTvReturnMoney;
    @BindView(R.id.tv_tuikuanreason)
    TextView mTvTuikuanreason;
    @BindView(R.id.tv_return_money_reason)
    TextView mTvReturnMoneyReason;
    @BindView(R.id.tv_shouhouid)
    TextView mTvShouhouid;
    @BindView(R.id.tv_item_id)
    TextView mTvItemId;
    @BindView(R.id.tv_shenqingshijian)
    TextView mTvShenqingshijian;
    @BindView(R.id.tv_apply_time)
    TextView mTvApplyTime;
    @BindView(R.id.tv_merchant_complaint)
    TextView mTvMerchantComplaint;
    @BindView(R.id.tv_complaint)
    TextView mTvComplaint;
    @BindView(R.id.rl_complaint)
    RelativeLayout mRlComplaint;
    @BindView(R.id.tv_sendgood)
    LinearLayout mTvSendgood;
    @BindView(R.id.ll_return_goods)
    LinearLayout mLlReturnGoods;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_after_sale_detail;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvActionbarTitle.setText("退款详情");
    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_return:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
