package com.ying.administrator.masterappdemo.v3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.LeaveMessageActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.OrderMessageActivity2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.tv_system_notification)
    TextView mTvSystemNotification;
    @BindView(R.id.ll_system_notification)
    LinearLayout mLlSystemNotification;
    @BindView(R.id.tv_on_site_reminder)
    TextView mTvOnSiteReminder;
    @BindView(R.id.ll_on_site_reminder)
    LinearLayout mLlOnSiteReminder;
    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.ll_message)
    LinearLayout mLlMessage;
    @BindView(R.id.tv_order_message)
    TextView mTvOrderMessage;
    @BindView(R.id.tv_order_message_time)
    TextView mTvOrderMessageTime;
    @BindView(R.id.ll_order_message)
    LinearLayout mLlOrderMessage;
    @BindView(R.id.tv_accessories_news)
    TextView mTvAccessoriesNews;
    @BindView(R.id.tv_accessories_news_time)
    TextView mTvAccessoriesNewsTime;
    @BindView(R.id.ll_accessories_news)
    LinearLayout mLlAccessoriesNews;
    @BindView(R.id.tv_review_notice)
    TextView mTvReviewNotice;
    @BindView(R.id.tv_review_notice_time)
    TextView mTvReviewNoticeTime;
    @BindView(R.id.ll_review_notice)
    LinearLayout mLlReviewNotice;
    @BindView(R.id.tv_transaction_notification)
    TextView mTvTransactionNotification;
    @BindView(R.id.tv_transaction_notification_time)
    TextView mTvTransactionNotificationTime;
    @BindView(R.id.ll_transaction_notification)
    LinearLayout mLlTransactionNotification;
    @BindView(R.id.tv_complaint_message)
    TextView mTvComplaintMessage;
    @BindView(R.id.tv_complaint_message_time)
    TextView mTvComplaintMessageTime;
    @BindView(R.id.ll_complaint_message)
    LinearLayout mLlComplaintMessage;
    private Intent intent;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_mesage;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("消息通知");
        mTvSave.setVisibility(View.VISIBLE);
        mTvSave.setText("全部已读");
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mLlSystemNotification.setOnClickListener(this);
        mLlOrderMessage.setOnClickListener(this);
        mLlTransactionNotification.setOnClickListener(this);
        mLlMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_system_notification:
                startActivity(new Intent(mActivity,SystemNotificationActivity.class));
                break;
            case R.id.ll_order_message:
                intent = new Intent(mActivity, OrderMessageActivity2.class);
                intent.putExtra("type", 2);
                startActivity(intent);
                break;
            case R.id.ll_transaction_notification:
                intent = new Intent(mActivity, OrderMessageActivity2.class);
                intent.putExtra("type", 1);
                startActivity(intent);
                break;
            case R.id.ll_message:
                startActivity(new Intent(mActivity, LeaveMessageActivity.class));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
