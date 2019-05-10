package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.android.tpush.XGPushConfig;
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
import io.reactivex.functions.Consumer;

public class Login_New_Activity extends BaseActivity<LoginPresenter, LoginModel> implements View.OnClickListener, LoginContract.View {


    @BindView(R.id.et_login_username)
    EditText mEtLoginUsername;
    @BindView(R.id.et_login_password)
    EditText mEtLoginPassword;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.tv_forget_password)
    TextView mTvForgetPassword;
    @BindView(R.id.tv_register)
    TextView mTvRegister;

    @BindView(R.id.tv_note_login)
    TextView mTv_note_login;

    @BindView(R.id.tv_forgetpassword)
    TextView tv_forgetpassword;


    private boolean isLogin;
    private String userName;
    private String passWord;
    private SPUtils spUtils;
    ZLoadingDialog dialog = new ZLoadingDialog(this); //loading

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login_new;
    }

    @Override
    protected void initData() {
        spUtils = SPUtils.getInstance("token");
        String userName = spUtils.getString("userName");
        String password = spUtils.getString("passWord");
        isLogin = spUtils.getBoolean("isLogin");
        if (userName != null && password != null) {
            mEtLoginUsername.setText(userName);
            mEtLoginPassword.setText(password);
        }
    }

    @Override
    protected void initView() {




    }

    @Override
    protected void setListener() {
        mTvLogin.setOnClickListener(this);
        mTvForgetPassword.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);
        mTv_note_login.setOnClickListener(this);
        tv_forgetpassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){

          case R.id.tv_forgetpassword:

              startActivity(new Intent(mActivity,ForgetPasswordActivity.class));

              break;


          case R.id.tv_note_login://验证码登陆
              String phone=mEtLoginUsername.getText().toString();
              Intent intent=new Intent(mActivity, ToteLoginActivity.class);

              if (phone!=null){
                  intent.putExtra("phone",phone);
              }else {
                  intent.putExtra("phone","");
              }
              startActivity(intent);


              break;


          case R.id.tv_forget_password:
                /*  String phone=mEtLoginUsername.getText().toString();
                  Intent intent=new Intent(mActivity, ForgetPasswordActivity.class);

              if (phone!=null){
                  intent.putExtra("phone",phone);
              }else {
                  intent.putExtra("phone","");
              }

              startActivity(intent);*/

              break;
          case R.id.tv_register:
              startActivity(new Intent(mActivity, Register_New_Activity.class));
              break;
          case R.id.tv_login:
              showLoading();
              userName = mEtLoginUsername.getText().toString();
              passWord = mEtLoginPassword.getText().toString();

              if ("".equals(userName)) {
                  ToastUtils.showShort("请输入手机号！");
                  return;
              }
              if (!RegexUtils.isMobileExact(userName)){
                  ToastUtils.showShort("手机号格式不正确！");
                  return;
              }
              if ("".equals(passWord)) {
                  ToastUtils.showShort("请输入密码！");
                  return;
              }

              mPresenter.Login(userName, passWord);

              break;



      }
    }

    @Override
    public void Login(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data=baseResult.getData();
                if (data.isItem1()){
                    spUtils.put("adminToken", data.getItem2());
                    spUtils.put("userName", userName);
                    spUtils.put("passWord", passWord);
                    spUtils.put("isLogin", true);
                    mPresenter.AddAndUpdatePushAccount(XGPushConfig.getToken(this),"7",userName);
                    startActivity(new Intent(mActivity, MainActivity.class));
                    finish();
                }else{
                    ToastUtils.showShort(data.getItem2());
                    cancleLoading();
                }
                break;
        }
    }

    @Override
    public void GetUserInfo(BaseResult<String> baseResult) {

    }

    @Override
    public void AddAndUpdatePushAccount(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetCode(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void ValidateUserName(BaseResult<String> baseResult) {

    }

    @Override
    public void LoginOnMessage(BaseResult<Data<String>> baseResult) {

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

    public void showLoading(){
        dialog.setLoadingBuilder(Z_TYPE.SINGLE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setHintText("登陆中请稍后...")
                .setHintTextSize(14) // 设置字体大小 dp
                .setHintTextColor(Color.BLACK)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setCanceledOnTouchOutside(false)//点击外部无法取消
                .show();
    }

    public void cancleLoading(){
        dialog.dismiss();
    }
}
