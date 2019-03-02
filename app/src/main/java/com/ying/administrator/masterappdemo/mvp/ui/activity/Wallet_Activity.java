package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.common.DefineView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Wallet_Activity extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.tv_recharge)
    TextView mTvRecharge;
    @BindView(R.id.tv_withdraw)
    TextView mTvWithdraw;
    @BindView(R.id.tv_unfinished)
    TextView mTvUnfinished;
    @BindView(R.id.tv_free_gift)
    TextView mTvFreeGift;
    @BindView(R.id.rv_income_and_expenditure_details)
    RecyclerView mRvIncomeAndExpenditureDetails;
    @BindView(R.id.iv_bank_no)
    ImageView mIvBankNo;
    @BindView(R.id.tv_bank)
    TextView mTvBank;
    @BindView(R.id.iv_aplipay_no)
    ImageView mIvAplipayNo;
    @BindView(R.id.tv_aplipay)
    TextView mTvAplipay;
    @BindView(R.id.iv_wechat_no)
    ImageView mIvWechatNo;
    @BindView(R.id.tv_wechat)
    TextView mTvWechat;
    @BindView(R.id.rv_withdrawals_record)
    RecyclerView mRvWithdrawalsRecord;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_wallet;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initView() {
        mTvActionbarTitle.setText("我的钱包");
        mImgActionbarMessage.setVisibility(View.INVISIBLE);
    }


    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
        mTvRecharge.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_return:
                finish();
                break;
            case R.id.tv_recharge:
                startActivity(new Intent(mActivity,RechargeActivity.class));
                break;
        }

    }
}
