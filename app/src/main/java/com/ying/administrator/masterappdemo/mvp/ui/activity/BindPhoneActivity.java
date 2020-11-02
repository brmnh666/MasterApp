package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WXUserInfoBean;
import com.ying.administrator.masterappdemo.entity.WxRegister;
import com.ying.administrator.masterappdemo.mvp.contract.RegisterContract;
import com.ying.administrator.masterappdemo.mvp.model.RegisterModel;
import com.ying.administrator.masterappdemo.mvp.presenter.RegisterPresenter;
import com.ying.administrator.masterappdemo.v3.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;

public class BindPhoneActivity extends BaseActivity<RegisterPresenter, RegisterModel> implements View.OnClickListener, RegisterContract.View {
    @BindView(R.id.img_register_back)
    ImageView mImgRegisterBack;
    @BindView(R.id.et_register_phone)
    EditText mEtRegisterPhone;
    @BindView(R.id.et_register_yzm)
    EditText mEtRegisterYzm;
    @BindView(R.id.tv_send_yzm)
    TextView mTvSendYzm;
    @BindView(R.id.tv_register)
    TextView mTvRegister;
    private String phone;
    private String code;
    private WXUserInfoBean result;
    private SPUtils spUtils;
    @Override
    protected int setLayoutId() {
        return R.layout.activity_bind_phone;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        spUtils = SPUtils.getInstance("token");
        result = (WXUserInfoBean) getIntent().getSerializableExtra("openid");
    }

    @Override
    protected void setListener() {
        mImgRegisterBack.setOnClickListener(this);
        mTvSendYzm.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_register_back:
                finish();
                break;
            case R.id.tv_send_yzm:
                phone = mEtRegisterPhone.getText().toString();
                if (phone.isEmpty()) {
                    ToastUtils.showShort("请输入手机号");
                    return;
                }
                if (!RegexUtils.isMobileExact(phone)) {
                    ToastUtils.showShort("手机格式不正确！");
                    return;
                }
//                mPresenter.ValidateUserName(phone);
                TimeCount timeCount = new TimeCount(60000, 1000);
                timeCount.start();
                mPresenter.GetCode(phone, "1");
                break;
            case R.id.tv_register:
                showProgress();
                phone = mEtRegisterPhone.getText().toString();
                code = mEtRegisterYzm.getText().toString();

                if (phone.isEmpty()) {
                    hideProgress();
                    ToastUtils.showShort("请输入手机号！");
                    return;
                }
                if (code.isEmpty()) {
                    hideProgress();
                    ToastUtils.showShort("请输入验证码！");
                    return;
                }
                mPresenter.WxReg(phone,code,result.getOpenid(),result.getUnionid());
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void Reg(BaseResult<Data<String>> baseResult) {

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
    public void Login(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void ValidateUserName(BaseResult<String> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if ("true".equals(baseResult.getData())) {
                    TimeCount timeCount = new TimeCount(60000, 1000);
                    timeCount.start();
                    mPresenter.GetCode(phone, "1");
                } else {
                    ToastUtils.showShort("手机号已经注册！");
                }
                break;
            case 401:
                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void AddAndUpdatePushAccount(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void WxReg(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    finish();

                    mPresenter.WxRegister(result.getOpenid(), result.getNickname(), result.getSex(), result.getLanguage(), result.getCity(), result.getProvince(), result.getCountry(), result.getHeadimgurl(), result.getUnionid());
                } else {
//                    Toast.makeText(this, "验证码出错", Toast.LENGTH_SHORT).show();
                    ToastUtils.showShort(baseResult.getData().getItem2());
                }
                break;

            default:
                break;
        }

    }

    @Override
    public void WxRegister(BaseResult<Data<WxRegister>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){
                    WxRegister data = baseResult.getData().getItem2();
                    spUtils.put("adminToken", data.getData());
                    spUtils.put("userName", data.getUserID());
                    spUtils.put("isLogin", true);
                    mPresenter.AddAndUpdatePushAccount(JPushInterface.getRegistrationID(this), "7", data.getUserID());
                    startActivity(new Intent(mActivity, MainActivity.class));
                    finish();
                }else {

                }
//                hideProgress();
                break;
        }
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onTick(long millisUntilFinished) {
            if (mTvSendYzm == null) {
                return;
            }
            mTvSendYzm.setClickable(false);
            mTvSendYzm.setTextColor(R.color.color_custom_06);
            mTvSendYzm.setText(millisUntilFinished / 1000 + "秒后重新获取");
        }

        @Override
        public void onFinish() {
            if (mTvSendYzm == null) {
                return;
            }
            mTvSendYzm.setText("重新获取验证码");
            mTvSendYzm.setClickable(true);
        }
    }

}
