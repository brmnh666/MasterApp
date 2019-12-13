package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import com.ying.administrator.masterappdemo.mvp.contract.LoginContract;
import com.ying.administrator.masterappdemo.mvp.model.LoginModel;
import com.ying.administrator.masterappdemo.mvp.presenter.LoginPresenter;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

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


    private String Phone;
    private SPUtils spUtils;
    ZLoadingDialog dialog = new ZLoadingDialog(this); //loading


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
        mTvUsername.setOnClickListener(this);
        mTvYzm.setOnClickListener(this);
        mTv_login.setOnClickListener(this);
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
                if (!RegexUtils.isMobileExact(mEtLoginUsername.getText().toString())) {
                    ToastUtils.showShort("手机号格式不正确！");
                    return;
                }
                if ("".equals(mEtLoginCode.getText().toString())) {
                    ToastUtils.showShort("请输入验证码！");
                    return;
                }
                showLoading();
                mPresenter.LoginOnMessage(Phone, mEtLoginCode.getText().toString());

                break;

            case R.id.tv_username:
                ToteLoginActivity.this.finish();

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
                    cancleLoading();
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
                cancleLoading();
                break;
        }
    }

    @Override
    public void LoginOut(BaseResult<Data<String>> baseResult) {

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


    public void showLoading() {
        dialog.setLoadingBuilder(Z_TYPE.SINGLE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setHintText("登陆中请稍后...")
                .setHintTextSize(14) // 设置字体大小 dp
                .setHintTextColor(Color.BLACK)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setCanceledOnTouchOutside(false)//点击外部无法取消
                .show();
    }

    public void cancleLoading() {
        dialog.dismiss();
    }
}
