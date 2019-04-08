package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.tencent.android.tpush.XGPushConfig;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.mvp.contract.RegisterContract;
import com.ying.administrator.masterappdemo.mvp.model.RegisterModel;
import com.ying.administrator.masterappdemo.mvp.presenter.RegisterPresenter;
import com.ying.administrator.masterappdemo.util.MyUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity<RegisterPresenter, RegisterModel> implements RegisterContract.View,View.OnClickListener {


    private static final String TAG = "RegisterActivity";
    @BindView(R.id.img_login_username)
    ImageView mImgLoginUsername;
    @BindView(R.id.et_register_username)
    EditText mEtRegisterUsername;
    @BindView(R.id.img_login_username_cancel)
    ImageView mImgLoginUsernameCancel;
    @BindView(R.id.img_login_password)
    ImageView mImgLoginPassword;
    @BindView(R.id.et_register_code)
    EditText mEtRegisterCode;
    @BindView(R.id.tv_get_verification_code)
    TextView mTvGetVerificationCode;
    @BindView(R.id.img_login_password_hide)
    ImageView mImgLoginPasswordHide;
    @BindView(R.id.btn_register)
    Button mBtnRegister;
    @BindView(R.id.tv_can_not_receive)
    TextView mTvCanNotReceive;
    private String userName;
    private String passWord="888888";
    private String code;
    private SPUtils spUtils;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mTvGetVerificationCode.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_get_verification_code:
                userName = mEtRegisterUsername.getText().toString();
                if (userName.isEmpty()){
                    ToastUtils.showShort("请输入手机号");
                    return;
                }
                if (!RegexUtils.isMobileExact(userName)){
                    ToastUtils.showShort("手机格式不正确！");
                    return;
                }
                mPresenter.ValidateUserName(userName);
                break;
            case R.id.btn_register:
                userName =mEtRegisterUsername.getText().toString();
                code = mEtRegisterCode.getText().toString();
                if (userName.isEmpty()){
                    ToastUtils.showShort("请输入手机号！");
                    return;
                }
                if (code.isEmpty()){
                    ToastUtils.showShort("请输入验证码！");
                    return;
                }
                mPresenter.Reg(userName,code,"");
//                ToastUtils.showShort("注册成功");

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
        switch (baseResult.getStatusCode()){
            case 200:


              if (baseResult.getData().isItem1()){
                  RegisterActivity.this.finish();
                  mPresenter.Login(userName,"888888");
              }else {
                  Toast.makeText(this,"验证码出错",Toast.LENGTH_SHORT).show();
              }


                break;
            default:
                break;
        }
    }

    @Override
    public void GetCode(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){
                    Toast.makeText(this,"验证码已发送",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this,"频繁请求验证码请稍后再试",Toast.LENGTH_SHORT).show();
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
                Data<String> data=baseResult.getData();
                if (data.isItem1()){
                    spUtils = SPUtils.getInstance("token");
                    spUtils.put("adminToken", data.getItem2());
                    spUtils.put("userName", userName);
                    spUtils.put("passWord", passWord);
                    spUtils.put("isLogin", true);
                    mPresenter.AddAndUpdatePushAccount(XGPushConfig.getToken(this),"7",userName);
                    startActivity(new Intent(mActivity, MainActivity.class));
                    finish();
                }else{
                    ToastUtils.showShort(data.getItem2());
                }
                break;
        }
    }

    @Override
    public void ValidateUserName(BaseResult<String> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                MyUtils.e(TAG,baseResult.getData());
                if ("true".equals(baseResult.getData())){
                    TimeCount timeCount=new TimeCount(60000,1000);
                    timeCount.start();
                    mPresenter.GetCode(userName,"Reg");
                }else {
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
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data=baseResult.getData();
                if (data.isItem1()){

                }else{
                    ToastUtils.showShort(data.getItem2());
                }
                break;
        }
    }
    class TimeCount extends CountDownTimer{
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onTick(long millisUntilFinished) {
            if (mTvGetVerificationCode==null){
                return;
            }
            mTvGetVerificationCode.setClickable(false);
            mTvGetVerificationCode.setTextColor(R.color.color_custom_06);
            mTvGetVerificationCode.setText(millisUntilFinished/1000+"秒后重新获取");
        }

        @Override
        public void onFinish() {
            if (mTvGetVerificationCode==null){
                return;
            }
            mTvGetVerificationCode.setText("重新获取验证码");
            mTvGetVerificationCode.setClickable(true);
        }
    }


}
