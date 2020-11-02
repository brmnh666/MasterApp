package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.tencent.bugly.beta.Beta;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WxRegister;
import com.ying.administrator.masterappdemo.mvp.contract.LoginContract;
import com.ying.administrator.masterappdemo.mvp.model.LoginModel;
import com.ying.administrator.masterappdemo.mvp.presenter.LoginPresenter;
import com.ying.administrator.masterappdemo.util.DataCleanManager;
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {

    @BindView(R.id.img_actionbar_return)
    ImageView mImgActionbarReturn;
    @BindView(R.id.tv_actionbar_return)
    TextView mTvActionbarReturn;
    @BindView(R.id.ll_return)
    LinearLayout mLlReturn;
    @BindView(R.id.tv_actionbar_title)
    TextView mTvActionbarTitle;
    @BindView(R.id.img_actionbar_message)
    ImageView mImgActionbarMessage;
    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.actionbar_layout)
    RelativeLayout mActionbarLayout;
    @BindView(R.id.ll_message)
    LinearLayout mLlMessage;
    @BindView(R.id.ll_safety)
    LinearLayout mLlSafety;
    @BindView(R.id.img_clean_cache)
    ImageView mImgCleanCache;
    @BindView(R.id.ll_clean)
    LinearLayout mLlClean;
    @BindView(R.id.ll_update)
    LinearLayout mLlUpdate;
    @BindView(R.id.btn_sign_out_of_your_account)
    Button mBtnSignOutOfYourAccount;
    private SPUtils spUtils;
    private String UserID;


//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_setting);
//    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_setting2;
    }

    @Override
    protected void initData() {
        mTvActionbarTitle.setText("设置");
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initView() {
        spUtils = SPUtils.getInstance("token");
    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(new CustomOnclickLister());
        mBtnSignOutOfYourAccount.setOnClickListener(new CustomOnclickLister());
        mLlUpdate.setOnClickListener(new CustomOnclickLister());
        mLlSafety.setOnClickListener(new CustomOnclickLister());
        mLlMessage.setOnClickListener(new CustomOnclickLister());
        mLlClean.setOnClickListener(new CustomOnclickLister());
        mImgCleanCache.setOnClickListener(new CustomOnclickLister());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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

    }

    @Override
    public void ValidateUserName(BaseResult<String> baseResult) {

    }

    @Override
    public void LoginOnMessage(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void LoginOut(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    spUtils.put("isLogin", false);
                    startActivity(new Intent(mActivity, Login_New_Activity.class));
                    ActivityUtils.finishAllActivities();
                } else {
                    ToastUtils.showShort("退出失败");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void WxRegister(BaseResult<Data<WxRegister>> baseResult) {

    }


    public class CustomOnclickLister implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_return:
                    SettingActivity.this.finish();
                    break;
                case R.id.btn_sign_out_of_your_account:
                    final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
                    dialog.setMessage("是否确认退出")
                            //.setImageResId(R.mipmap.ic_launcher)
                            .setTitle("提示")
                            .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                        @Override
                        public void onPositiveClick() {
                            dialog.dismiss();
                            UserID = spUtils.getString("userName");
                            mPresenter.LoginOut(UserID);
                        }

                        @Override
                        public void onNegtiveClick() {//取消
                            dialog.dismiss();
                            // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                    break;
                case R.id.ll_update:

                    Beta.checkUpgrade();
                    break;
                case R.id.ll_clean:
                case R.id.img_clean_cache:

                    final CommonDialog_Home clean_dialog = new CommonDialog_Home(mActivity);
                    clean_dialog.setMessage("当前缓存大小" + getCacheSize())
                            //.setImageResId(R.mipmap.ic_launcher)
                            .setTitle("是否清除缓存")
                            .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                        @Override
                        public void onPositiveClick() {//清除缓存
                            cleanCache();
                            clean_dialog.dismiss();
                        }

                        @Override
                        public void onNegtiveClick() {//取消
                            clean_dialog.dismiss();
                        }
                    }).show();


                    break;
                case R.id.ll_safety:
                    startActivity(new Intent(mActivity,AccountAndSecurityActivity.class));
                    break;
                case R.id.ll_message:
                    startActivity(new Intent(mActivity,MessageSettingsActivity.class));
                    break;

            }

        }
    }



    //获取缓存大小
    private String getCacheSize() {
        String str = "";
        try {
            str = DataCleanManager.getTotalCacheSize(mActivity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }


    //清空缓存
    private void cleanCache() {
        DataCleanManager.clearAllCache(mActivity);
    }

}
