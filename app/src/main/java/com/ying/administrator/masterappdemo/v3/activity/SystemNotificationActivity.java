package com.ying.administrator.masterappdemo.v3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.ArticleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SystemNotificationActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.ll_news)
    LinearLayout mLlNews;
    @BindView(R.id.ll_platform_policy)
    LinearLayout mLlPlatformPolicy;
    @BindView(R.id.ll_order_must_read)
    LinearLayout mLlOrderMustRead;
    private Intent intent;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_system_notification;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("系统通知");
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mLlCustomerService.setOnClickListener(this);
        mLlOrderMustRead.setOnClickListener(this);
        mLlPlatformPolicy.setOnClickListener(this);
        mLlNews.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }

        intent = new Intent(mActivity, ArticleActivity.class);
        switch(v.getId()){
            case R.id.ll_platform_policy:
                intent.putExtra("CategoryID","8");
                break;
            case R.id.ll_news:
                intent.putExtra("CategoryID","9");
                break;
            case R.id.ll_order_must_read:
                intent.putExtra("CategoryID","10");
                break;
            default:
                break;
        }
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
