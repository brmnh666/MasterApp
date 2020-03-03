package com.ying.administrator.masterappdemo.v3.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.mvp.ui.activity.ChagePasswordActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Login_New_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.SettingPayPasswordActivity;
import com.ying.administrator.masterappdemo.util.DataCleanManager;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.SettingPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.SettingContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.SettingModel;
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity<SettingPresenter, SettingModel> implements View.OnClickListener, SettingContract.View {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_order_setting)
    LinearLayout mLlOrderSetting;
    @BindView(R.id.ll_modify_login_password)
    LinearLayout mLlModifyLoginPassword;
    @BindView(R.id.ll_change_withdrawal_password)
    LinearLayout mLlChangeWithdrawalPassword;
    @BindView(R.id.iv_clear)
    ImageView mIvClear;
    @BindView(R.id.iv_version_number)
    TextView mIvVersionNumber;
    @BindView(R.id.btn_out)
    Button mBtnOut;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.ll_clean)
    LinearLayout mLlClean;
    @BindView(R.id.ll_update)
    LinearLayout mLlUpdate;
    private SPUtils spUtils;
    private String userID;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_setting;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("设置");
        spUtils = SPUtils.getInstance("token");

        PackageManager manager = mActivity.getPackageManager();
//        int code = 0;
//        try {
//            PackageInfo info = manager.getPackageInfo(mActivity.getPackageName(), 0);
//            code = info.versionCode;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }

        String name = null;
        try {
            PackageInfo info = manager.getPackageInfo(mActivity.getPackageName(), 0);
            name = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        mIvVersionNumber.setText("V "+name);
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mLlOrderSetting.setOnClickListener(this);
        mBtnOut.setOnClickListener(this);
        mIvClear.setOnClickListener(this);
        mLlClean.setOnClickListener(this);
        mIvVersionNumber.setOnClickListener(this);
        mLlUpdate.setOnClickListener(this);
        mLlModifyLoginPassword.setOnClickListener(this);
        mLlChangeWithdrawalPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_order_setting:
                startActivity(new Intent(mActivity, OrderSettingActivity.class));
                break;
            case R.id.btn_out:
                final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
                dialog.setMessage("是否确认退出")
                        //.setImageResId(R.mipmap.ic_launcher)
                        .setTitle("提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        dialog.dismiss();
                        userID = spUtils.getString("userName");
                        mPresenter.LoginOut(userID);
                    }

                    @Override
                    public void onNegtiveClick() {//取消
                        dialog.dismiss();
                        // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.iv_clear:
            case R.id.ll_clean:
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
            case R.id.ll_update:

//                Beta.checkUpgrade(true,true);
                UpgradeInfo upgradeInfo=Beta.getUpgradeInfo();
                if (upgradeInfo==null){
                    ToastUtils.showShort("暂无更新");
                }else {
                    return;
                }
                Beta.checkUpgrade();
                break;
            case R.id.ll_modify_login_password:
                startActivity(new Intent(mActivity, ChagePasswordActivity.class));
                break;
            case R.id.ll_change_withdrawal_password:
                startActivity(new Intent(mActivity, SettingPayPasswordActivity.class));
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
