package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageSettingsActivity extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.switcher_installation_work_order)
    Switch mSwitcherInstallationWorkOrder;
    @BindView(R.id.switcher_repair_work_order)
    Switch mSwitcherRepairWorkOrder;
    @BindView(R.id.switcher_accept_the_repair_work_order)
    Switch mSwitcherAcceptTheRepairWorkOrder;
    @BindView(R.id.switcher_whether_to_allow_the_order)
    Switch mSwitcherWhetherToAllowTheOrder;
    @BindView(R.id.switcher_allow_notification)
    Switch mSwitcherAllowNotification;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_message_setting;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvActionbarTitle.setText("工单设置");
    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
        mSwitcherAllowNotification.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_return:
                finish();
                break;
            case R.id.switcher_allow_notification:
                toSelfSetting(mActivity);
                break;
        }
    }

    public static void toSelfSetting(Context context) {
        Intent mIntent = new Intent();
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            mIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            mIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            mIntent.setAction(Intent.ACTION_VIEW);
            mIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
            mIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        context.startActivity(mIntent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
