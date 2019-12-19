package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.huawei.android.hms.agent.common.UIUtils;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xiaomi.mipush.sdk.Constants;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.app.MyApplication;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WXAccessTokenBean;
import com.ying.administrator.masterappdemo.entity.WXUserInfoBean;
import com.ying.administrator.masterappdemo.entity.WxRegister;
import com.ying.administrator.masterappdemo.mvp.contract.LoginContract;
import com.ying.administrator.masterappdemo.mvp.model.LoginModel;
import com.ying.administrator.masterappdemo.mvp.presenter.LoginPresenter;
import com.ying.administrator.masterappdemo.wxapi.WXEntryActivity;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Login_New_Activity extends BaseActivity<LoginPresenter, LoginModel> implements View.OnClickListener, LoginContract.View {

    private static final String TAG = "Login_New_Activity";
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
    @BindView(R.id.img_login_username)
    ImageView mImgLoginUsername;
    @BindView(R.id.img_login_password)
    ImageView mImgLoginPassword;
    @BindView(R.id.rl_input_password)
    RelativeLayout mRlInputPassword;
    @BindView(R.id.iv_weixin)
    ImageView mIvWeixin;


    private boolean isLogin;
    private String userName;
    private String passWord;
    private SPUtils spUtils;
    ZLoadingDialog dialog = new ZLoadingDialog(this); //loading
    private String code;
    private WXUserInfoBean result;

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
        mIvWeixin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_forgetpassword:

                startActivity(new Intent(mActivity, ForgetPasswordActivity.class));

                break;


            case R.id.tv_note_login://验证码登陆
                String phone = mEtLoginUsername.getText().toString();
                Intent intent = new Intent(mActivity, ToteLoginActivity.class);

                if (phone != null) {
                    intent.putExtra("phone", phone);
                } else {
                    intent.putExtra("phone", "");
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
                    cancleLoading();
                    ToastUtils.showShort("请输入手机号！");
                    return;
                }
                if (!RegexUtils.isMobileExact(userName)) {
                    cancleLoading();
                    ToastUtils.showShort("手机号格式不正确！");
                    return;
                }
                if ("".equals(passWord)) {
                    cancleLoading();
                    ToastUtils.showShort("请输入密码！");
                    return;
                }

                mPresenter.Login(userName, passWord);

                break;
            case R.id.iv_weixin:
                //先判断是否安装微信APP,按照微信的说法，目前移动应用上微信登录只提供原生的登录方式，需要用户安装微信客户端才能配合使用。
                if (!MyApplication.mWxApi.isWXAppInstalled()) {
                    ToastUtils.showShort("您还未安装微信客户端");
                    return;
                }
                final SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "diandi_wx_login";
                MyApplication.mWxApi.sendReq(req);

                break;


        }
    }

    @Override
    public void Login(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data = baseResult.getData();
                if (data.isItem1()) {
                    spUtils.put("adminToken", data.getItem2());
                    spUtils.put("userName", userName);
                    spUtils.put("passWord", passWord);
                    spUtils.put("isLogin", true);
                    mPresenter.AddAndUpdatePushAccount(XGPushConfig.getToken(this), "7", userName);
                    startActivity(new Intent(mActivity, MainActivity.class));
                    finish();
                } else {
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
    public void WxRegister(BaseResult<Data<WxRegister>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    WxRegister data = baseResult.getData().getItem2();
                    spUtils.put("adminToken", data.getData());
                    spUtils.put("userName", data.getUserID());
                    spUtils.put("isLogin", true);
                    mPresenter.AddAndUpdatePushAccount(XGPushConfig.getToken(this), "7", data.getUserID());
                    startActivity(new Intent(mActivity, MainActivity.class));
                    finish();
                } else {
//                    if ("未绑定手机号".equals(baseResult.getData().getItem2())){
                    Intent intent = new Intent(mActivity, BindPhoneActivity.class);
                    intent.putExtra("openid", result);
                    startActivity(intent);
//                    }else {
//                        ToastUtils.showShort();
//                    }
                }
                cancleLoading();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void showLoading() {
        dialog.setLoadingBuilder(Z_TYPE.SINGLE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setHintText("登录中请稍后...")
                .setHintTextSize(14) // 设置字体大小 dp
                .setHintTextColor(Color.BLACK)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setCanceledOnTouchOutside(false)//点击外部无法取消
                .show();
    }

    public void cancleLoading() {
        dialog.dismiss();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        code = name;
        showLoading();
        getAccessToken(code);
    }

    /**
     * 获取access_token：
     *
     * @param code 用户或取access_token的code，仅在ErrCode为0时有效
     */
    private void getAccessToken(final String code) {
        //接口
        String path = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxd22da3eb42259071&secret=dac072c98881a324665bfbaa7f7e7c76&code=" + code + "&grant_type=authorization_code";

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
        Request request = new Request.Builder()
                .url(path)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
//                System.out.println(str);
//                ToastUtils.showShort(str);
//                Log.i(TAG, "onCompleted:-------->" + str);

                Gson gson = new Gson();
                WXAccessTokenBean result1 = gson.fromJson(str.replaceAll(" ", ""), WXAccessTokenBean.class);
                String access_token = result1.getAccess_token(); //接口调用凭证
                String openid = result1.getOpenid(); //授权用户唯一标识
                //当且仅当该移动应用已获得该用户的userinfo授权时，才会出现该字段
                String unionid = result1.getUnionid();
//                Log.i(TAG, "access_token:----->" + access_token);
//                Log.i(TAG, "openid:----->" + openid);
//                Log.i(TAG, "unionid:----->" + unionid);
                getWXUserInfo(access_token, openid, unionid);
            }
        });
    }


    /**
     * 获取微信登录，用户授权后的个人信息
     *
     * @param access_token
     * @param openid
     * @param unionid
     */
    private void getWXUserInfo(final String access_token, final String openid, final String unionid) {

        //接口
        String path = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid;

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
        final Request request = new Request.Builder()
                .url(path)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
//                System.out.println(str);
//                ToastUtils.showShort(str);
                Log.i(TAG, ":-------->" + str);

                Gson gson = new Gson();
                result = gson.fromJson(str.replaceAll(" ", ""), WXUserInfoBean.class);
                mPresenter.WxRegister(result.getOpenid(), result.getNickname(), result.getSex(), result.getLanguage(), result.getCity(), result.getProvince(), result.getCountry(), result.getHeadimgurl(), result.getUnionid());

            }
        });

    }
}
