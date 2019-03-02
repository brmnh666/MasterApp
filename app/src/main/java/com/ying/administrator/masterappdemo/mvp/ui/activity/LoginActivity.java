package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.tencent.android.tpush.XGPushConfig;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.mvp.contract.LoginContract;
import com.ying.administrator.masterappdemo.mvp.model.LoginModel;
import com.ying.administrator.masterappdemo.mvp.presenter.LoginPresenter;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.widget.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements View.OnClickListener, LoginContract.View {
    //用户名
    @BindView(R.id.et_login_username)
    EditText mEt_login_username;
    //密码
    @BindView(R.id.et_login_password)
    EditText mEt_login_password;
    //登录按钮
    @BindView(R.id.btn_login)
    Button mBtn_login;
    @BindView(R.id.tv_register)
    TextView mTvRegister;
    @BindView(R.id.img_login_username)
    ImageView mImgLoginUsername;
    @BindView(R.id.img_login_username_cancel)
    ImageView mImgLoginUsernameCancel;
    @BindView(R.id.img_login_password)
    ImageView mImgLoginPassword;
    @BindView(R.id.img_login_password_hide)
    ImageView mImgLoginPasswordHide;
    @BindView(R.id.rl_input_password)
    RelativeLayout mRlInputPassword;
    @BindView(R.id.et_verification_code)
    EditText mEtVerificationCode;
    @BindView(R.id.tv_get_verification_code)
    TextView mTvGetVerificationCode;
    @BindView(R.id.ll_code)
    LinearLayout mLlCode;
    @BindView(R.id.tv_change)
    TextView mTvChange;

    private String userName;
    private String passWord;
    private String code;
    private boolean isLogin;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        SPUtils spUtils = SPUtils.getInstance("token");
        String userName = spUtils.getString("userName");
        String password = spUtils.getString("password");
        isLogin = spUtils.getBoolean("isLogin");
        if (userName != null) {
            mEt_login_username.setText(userName);
            mEt_login_password.setText(password);
        }


    }

    @Override
    protected void setListener() {
        mBtn_login.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);
        mTvChange.setOnClickListener(this);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        ButterKnife.bind(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register:
                startActivity(new Intent(mActivity, RegisterActivity.class));
                break;

            case R.id.btn_login:

                userName = mEt_login_username.getText().toString();
                passWord = mEt_login_password.getText().toString();

                if ("".equals(userName)) {
                    ToastUtils.showShort("请输入手机号！");
                    return;
                }
                //                if (!RegexUtils.isMobileExact(userName)){
                //                    ToastUtils.showShort("手机号格式不正确！");
                //                    return;
                //                }
                if ("".equals(passWord)) {
                    ToastUtils.showShort("请输入密码！");
                    return;
                }
                mPresenter.Login(userName, passWord);
                break;
            case R.id.tv_change:
                if (mLlCode.getVisibility()==View.GONE){
                    mLlCode.setVisibility(View.VISIBLE);
                    mRlInputPassword.setVisibility(View.GONE);
                    mTvChange.setText("密码登录>");
                }else{
                    mLlCode.setVisibility(View.GONE);
                    mRlInputPassword.setVisibility(View.VISIBLE);
                    mTvChange.setText("短信验证码登录>");
                }
                break;
        }
    }


    @Override
    public void Login(BaseResult<String> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                SPUtils spUtils = SPUtils.getInstance("token");
                spUtils.put("adminToken", baseResult.getData());
                spUtils.put("userName", userName);
                spUtils.put("password", passWord);
                spUtils.put("isLogin", true);
                //  Log.d("loginlogin",baseResult.getData());
//                GetUserInfo getUserInfo=new GetUserInfo(userName,baseResult.getData(),"","");
//                Gson gson=new Gson();
//                RequestBody json=RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),gson.toJson(getUserInfo));
//                mPresenter.GetUserInfo(json);
//                mPresenter.GetUserInfo(userName);
                //Log.d("TokenTokenToken",Config.TOKEN);


//token在设备卸载重装的时候有可能会变

                mPresenter.AddAndUpdatePushAccount(XGPushConfig.getToken(this), "7", userName);
                startActivity(new Intent(mActivity, MainActivity.class));
                finish();
//                        Config.TOKEN= (String) data;
                //SPUtils spUtils = SPUtils.getInstance("token");
                //spUtils.put("Token", (String) data);
//
                // String token = spUtils.getString("Token");

                // Log.d("Token",token);


                break;
            case 401:
                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    /*获取用户消息*/
    @Override
    public void GetUserInfo(BaseResult<String> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                MyUtils.e("userInfo", baseResult.getData());
                ToastUtils.showShort(baseResult.getData());
                break;
            case 401:
                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void AddAndUpdatePushAccount(BaseResult<String> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                MyUtils.e("AddAndUpdatePushAccount", baseResult.getData());
                break;
            case 401:
                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }
}
