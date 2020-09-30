package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WxRegister;
import com.ying.administrator.masterappdemo.mvp.contract.LoginContract;
import com.ying.administrator.masterappdemo.mvp.model.LoginModel;
import com.ying.administrator.masterappdemo.mvp.presenter.LoginPresenter;
import com.ying.administrator.masterappdemo.v3.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToteLoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View, View.OnClickListener {
    @BindView(R.id.et_login_username)
    EditText mEtLoginUsername;
    @BindView(R.id.et_login_code)
    EditText mEtLoginCode;
    @BindView(R.id.tv_yzm)
    TextView mTvYzm;
    @BindView(R.id.tv_username) //账号密码登陆
            TextView mTvUsername;
    @BindView(R.id.tv_login) //登陆
            TextView mTv_login;
    @BindView(R.id.img_login_username)
    ImageView mImgLoginUsername;
    @BindView(R.id.img_login_password)
    ImageView mImgLoginPassword;
    @BindView(R.id.rl_input_password)
    RelativeLayout mRlInputPassword;
    @BindView(R.id.img_agreement)
    ImageView mImgAgreement;
    @BindView(R.id.tv_agreement)
    TextView mTvAgreement;
    @BindView(R.id.tv_privacy_policy)
    TextView mTvPrivacyPolicy;


    private String Phone;
    private SPUtils spUtils;
    private Intent intent;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_notelogin;
    }

    @Override
    protected void initData() {
        spUtils = SPUtils.getInstance("token");


        String phone = getIntent().getStringExtra("phone");
        mEtLoginUsername.setText(phone);

    }

    @Override
    protected void initView() {
        mImgAgreement.setSelected(true);
        mTvUsername.setOnClickListener(this);
        mTvYzm.setOnClickListener(this);
        mTv_login.setOnClickListener(this);
        mImgAgreement.setOnClickListener(this);
        mTvAgreement.setOnClickListener(this);
        mTvPrivacyPolicy.setOnClickListener(this);
    }

    @Override
    protected void setListener() {
        mTvYzm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_yzm:
                Phone = mEtLoginUsername.getText().toString();
                if (Phone.isEmpty()) {
                    ToastUtils.showShort("请输入手机号");
                    return;
                }
                if (!RegexUtils.isMobileExact(Phone)) {
                    ToastUtils.showShort("手机格式不正确！");
                    return;
                }
                mPresenter.ValidateUserName(Phone);
                break;
            case R.id.tv_login:

                if ("".equals(mEtLoginUsername.getText().toString())) {
                    ToastUtils.showShort("请输入手机号！");
                    return;
                }
                if (!RegexUtils.isMobileSimple(mEtLoginUsername.getText().toString())) {
                    ToastUtils.showShort("手机号格式不正确！");
                    return;
                }
                if ("".equals(mEtLoginCode.getText().toString())) {
                    ToastUtils.showShort("请输入验证码！");
                    return;
                }
                if (!mImgAgreement.isSelected()) {
                    ToastUtils.showShort("请阅读并同意服务协议与隐私政策");
                    return;
                }
                showProgress();
                mPresenter.LoginOnMessage(Phone, mEtLoginCode.getText().toString());

                break;

            case R.id.tv_username:
                ToteLoginActivity.this.finish();

                break;
            case R.id.img_agreement:
                if (mImgAgreement.isSelected()) {
                    mImgAgreement.setSelected(false);
                } else {
                    mImgAgreement.setSelected(true);
                }
                break;
            case R.id.tv_agreement:
                intent = new Intent(mActivity, WebActivity2.class);
                intent.putExtra("Url","https://admin.xigyu.com/Message/service");
                intent.putExtra("Title","服务协议");
                startActivity(intent);
                break;
            case R.id.tv_privacy_policy:
                intent = new Intent(mActivity, WebActivity2.class);
                intent.putExtra("Url","https://admin.xigyu.com/Message/yinsi");
                intent.putExtra("Title","隐私政策");
                startActivity(intent);
                break;

        }


    }

    @Override
    public void Login(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetUserInfo(BaseResult<String> baseResult) {

    }

    @Override
    public void AddAndUpdatePushAccount(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetCode(BaseResult<Data<String>> baseResult) {

        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {

                } else {
                    Toast.makeText(this, "频繁请求验证码请稍后再试", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void ValidateUserName(BaseResult<String> baseResult) {

        switch (baseResult.getStatusCode()) {
            case 200:
                if ("true".equals(baseResult.getData())) {

                    ToastUtils.showShort("手机号还未注册！");
                } else {
                    TimeCount timeCount = new TimeCount(60000, 1000);
                    timeCount.start();
                    mPresenter.GetCode(Phone, "3");
                }
                break;
            case 401:
                ToastUtils.showShort(baseResult.getData());
                break;
        }

    }

    @Override
    public void LoginOnMessage(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    hideProgress();
                    Log.d("=====>", "登陆成功");

                    spUtils.put("adminToken", baseResult.getData().getItem2());
                    spUtils.put("userName", mEtLoginUsername.getText().toString());
                    //spUtils.put("passWord", passWord);
                    spUtils.put("isLogin", true);
                    startActivity(new Intent(ToteLoginActivity.this, MainActivity.class));
                    ActivityUtils.finishAllActivities();
                } else {
                    ToastUtils.showShort(baseResult.getData().getItem2());
                }
                break;

            case 400:
                Toast.makeText(ToteLoginActivity.this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
                break;
            default:
                hideProgress();
                break;
        }
    }

    @Override
    public void LoginOut(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void WxRegister(BaseResult<Data<WxRegister>> baseResult) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onTick(long millisUntilFinished) {
            if (mTvYzm == null) {
                return;
            }
            mTvYzm.setClickable(false);
            mTvYzm.setTextColor(R.color.color_custom_06);
            mTvYzm.setText(millisUntilFinished / 1000 + "秒后重新获取");
        }

        @Override
        public void onFinish() {
            if (mTvYzm == null) {
                return;
            }
            mTvYzm.setText("重新获取验证码");
            mTvYzm.setClickable(true);
        }
    }

}
