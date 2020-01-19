package com.ying.administrator.masterappdemo.v3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_order_setting)
    LinearLayout mLlOrderSetting;
    @BindView(R.id.ll_modify_login_password)
    LinearLayout mLlModifyLoginPassword;
    @BindView(R.id.ll_change_withdrawal_password)
    LinearLayout mLlChangeWithdrawalPassword;
    @BindView(R.id.iv_clear)
    ImageView mIvClear;
    @BindView(R.id.iv_version_number)
    TextView mIvVersionNumber;
    @BindView(R.id.btn_out)
    Button mBtnOut;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_setting;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("设置");
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mLlOrderSetting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_order_setting:
                startActivity(new Intent(mActivity,OrderSettingActivity.class));
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
