package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
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

public class AccountAndSecurityActivity extends BaseActivity implements View.OnClickListener {


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
    @BindView(R.id.ll_login_password)
    LinearLayout mLlLoginPassword;
    @BindView(R.id.ll_payment_password)
    LinearLayout mLlPaymentPassword;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_account_and_security;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvActionbarTitle.setVisibility(View.VISIBLE);
        mTvActionbarTitle.setText("账户与安全");
    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
        mLlLoginPassword.setOnClickListener(this);
        mLlPaymentPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_return:
                finish();
                break;
            case R.id.ll_login_password:
                startActivity(new Intent(mActivity, ChagePasswordActivity.class));
                break;
            case R.id.ll_payment_password:
                startActivity(new Intent(mActivity, SettingPayPasswordActivity.class));
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
