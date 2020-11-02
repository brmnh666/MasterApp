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

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WxRegister;
import com.ying.administrator.masterappdemo.mvp.contract.RegisterContract;
import com.ying.administrator.masterappdemo.mvp.model.RegisterModel;
import com.ying.administrator.masterappdemo.mvp.presenter.RegisterPresenter;
import com.ying.administrator.masterappdemo.v3.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;

public class Register_New_Activity extends BaseActivity<RegisterPresenter, RegisterModel> implements View.OnClickListener, RegisterContract.View {


    @BindView(R.id.et_register_phone)
    EditText mEtRegisterPhone;  //手机号
    @BindView(R.id.et_register_yzm)
    EditText mEtRegisterYzm;  //输入验证码
    @BindView(R.id.tv_send_yzm)
    TextView mTvSendYzm;   //点击发送验证码
    @BindView(R.id.et_register_password)
    EditText mEtRegisterPassword;  //输入密码
    @BindView(R.id.tv_register)
    TextView mTvRegister;   //点击注册
    @BindView(R.id.img_agreement)
    ImageView mImg_agreement;
    @BindView(R.id.img_register_back)
    ImageView mImg_register_back;
    @BindView(R.id.tv_agreement)
    TextView mTvAgreement;

    private String Phone;
    private String password;
    private String code;
    private SPUtils spUtils;
    private Intent intent;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_register_new;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

/*
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_SMS)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean){
                            // 获取全部权限成功
                             Log.d("=====>","权限获取成功");
                        }else{
                            // 获取全部权限失败
                            Log.d("=====>","权限获取失败");
                        }
                    }
                });*/


    }

    @Override
    protected void setListener() {


        mTvSendYzm.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);
        mImg_agreement.setOnClickListener(this);
        mImg_agreement.setSelected(true);
        mImg_register_back.setOnClickListener(this);
        mTvAgreement.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_send_yzm:
                Phone = mEtRegisterPhone.getText().toString();
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

            case R.id.img_agreement:
                if (mImg_agreement.isSelected()) {
                    mImg_agreement.setSelected(false);
                } else {
                    mImg_agreement.setSelected(true);
                }
                break;
            case R.id.img_register_back:
                Register_New_Activity.this.finish();
                break;

            case R.id.tv_register:
                Phone = mEtRegisterPhone.getText().toString();
                code = mEtRegisterYzm.getText().toString();
                password = mEtRegisterPassword.getText().toString();
                showProgress();
                if (Phone.isEmpty()) {
                    ToastUtils.showShort("请输入手机号！");
                    hideProgress();
                    return;
                }
                if (code.isEmpty()) {
                    ToastUtils.showShort("请输入验证码！");
                    hideProgress();
                    return;
                }

                if (password.isEmpty()) {
                    ToastUtils.showShort("请输入密码");
                    hideProgress();
                    return;
                }

                if (!mImg_agreement.isSelected()) {
                    ToastUtils.showShort("请阅读并同意用户协议");
                    hideProgress();
                    return;

                }

                mPresenter.Reg(Phone, code, password);


                break;

            case R.id.tv_agreement:
                intent = new Intent(mActivity, WebActivity2.class);
                intent.putExtra("Url","http://admin.xigyu.com/Agreement");
                intent.putExtra("Title","用户协议");
                startActivity(intent);
                break;

        }
    }


    @Override
    public void Reg(BaseResult<Data<String>> baseResult) {

        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    Register_New_Activity.this.finish();

                    mPresenter.Login(Phone, password);
                } else {
                    Toast.makeText(this, "验证码出错", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }


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
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data = baseResult.getData();
                if (data.isItem1()) {
                    spUtils = SPUtils.getInstance("token");
                    spUtils.put("adminToken", data.getItem2());
                    spUtils.put("userName", Phone);
                    spUtils.put("passWord", password);
                    spUtils.put("isLogin", true);
                    mPresenter.AddAndUpdatePushAccount(JPushInterface.getRegistrationID(this), "7", Phone);
                    startActivity(new Intent(mActivity, MainActivity.class));
                    ActivityUtils.finishAllActivities();
//                    hideProgress();
                } else {
                }
                break;
        }
    }

    @Override
    public void ValidateUserName(BaseResult<String> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if ("true".equals(baseResult.getData())) {
                    TimeCount timeCount = new TimeCount(60000, 1000);
                    timeCount.start();
                    mPresenter.GetCode(Phone, "1");
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
