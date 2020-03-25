package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
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
        mTvTitle.setText("关于我们");
//        mIvBack.setVisibility(View.INVISIBLE);
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

        mTvVersionNumber.setText("V " + name);
    }

    @Override
    public void initView() {

    }


    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mTvOpinion.setOnClickListener(this);
        mTvUserAgreement.setOnClickListener(this);
        mTvPhoneNumber.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_opinion:
//                startActivity(new Intent(mActivity, Opinion_Activity.class));
                intent = new Intent(mActivity, WebActivity2.class);
                intent.putExtra("Url","https://admin.xigyu.com/Message/yinsi");
                intent.putExtra("Title","隐私政策");
                startActivity(intent);
                break;
            case R.id.tv_user_Agreement:
//                intent = new Intent(mActivity, WebActivity2.class);
//                intent.putExtra("Url", "http://admin.xigyu.com/Agreement");
//                intent.putExtra("Title", "用户协议");
//                startActivity(intent);
                intent = new Intent(mActivity, WebActivity2.class);
                intent.putExtra("Url","https://admin.xigyu.com/Message/service");
                intent.putExtra("Title","服务协议");
                startActivity(intent);
                break;
            case R.id.tv_phone_number:
                call("tel:" + "4006262365");
                break;
        }
    }


}
