package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.mvp.contract.CardContract;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.img_forget_back)
    ImageView mImgForgetBack;
    @BindView(R.id.et_forget_phone)
    EditText mEtForgetPhone;
    @BindView(R.id.et_forget_yzm)
    EditText mEtForgetYzm;
    @BindView(R.id.tv_send_yzm)
    TextView mTvSendYzm;
    @BindView(R.id.et_forget_password)
    EditText mEtForgetPassword;
    @BindView(R.id.et_forget_password_again)
    EditText mEtForgetPasswordAgain;
    @BindView(R.id.tv_login)
    TextView mTvLogin;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_forgetpassword;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mImgForgetBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_forget_back:
                ForgetPasswordActivity.this.finish();
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
