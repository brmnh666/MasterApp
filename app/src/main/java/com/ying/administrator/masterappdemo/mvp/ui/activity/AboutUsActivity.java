package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.common.DefineView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsActivity extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.actionbar_layout)
    RelativeLayout mActionbarLayout;
    @BindView(R.id.tv_version_number)
    TextView mTvVersionNumber;
    @BindView(R.id.tv_user_Agreement)
    TextView mTvUserAgreement;
    @BindView(R.id.tv_about_app)
    TextView mTvAboutApp;
    @BindView(R.id.tv_opinion)
    TextView mTvOpinion;
    @BindView(R.id.tv_net)
    TextView mTvNet;
    @BindView(R.id.tv_phone_number)
    TextView mTvPhoneNumber;
    @BindView(R.id.tv_company_english)
    TextView mTvCompanyEnglish;
    private Intent intent;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initData() {
        mTvActionbarTitle.setText("关于我们");
        mImgActionbarMessage.setVisibility(View.INVISIBLE);
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

        mTvVersionNumber.setText("V "+name);
    }

    @Override
    public void initView() {

    }


    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
        mTvOpinion.setOnClickListener(this);
        mTvUserAgreement.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_return:
                finish();
                break;
            case R.id.tv_opinion:
                startActivity(new Intent(mActivity,Opinion_Activity.class));
                break;
            case R.id.tv_user_Agreement:
                intent =new Intent(mActivity,WebActivity.class);
                intent.putExtra("Url","http://admin.xigyu.com/Agreement");
                intent.putExtra("Title","用户协议");
                startActivity(intent);
                break;
        }
    }


}
