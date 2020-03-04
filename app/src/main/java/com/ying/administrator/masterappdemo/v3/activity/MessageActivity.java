package com.ying.administrator.masterappdemo.v3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Article;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.LeaveMessage;
import com.ying.administrator.masterappdemo.entity.Message;
import com.ying.administrator.masterappdemo.entity.MessageData;
import com.ying.administrator.masterappdemo.mvp.ui.activity.LeaveMessageActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.OrderMessageActivity2;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.MessagePresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.MessageContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.MessageModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageActivity extends BaseActivity<MessagePresenter, MessageModel> implements View.OnClickListener, MessageContract.View {
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
    @BindView(R.id.tv_order_message_number)
    TextView mTvOrderMessageNumber;
    @BindView(R.id.tv_accessories_news_number)
    TextView mTvAccessoriesNewsNumber;
    @BindView(R.id.tv_review_notice_number)
    TextView mTvReviewNoticeNumber;
    @BindView(R.id.tv_transaction_notification_number)
    TextView mTvTransactionNotificationNumber;
    @BindView(R.id.tv_complaint_message_number)
    TextView mTvComplaintMessageNumber;
    private Intent intent;
    private SPUtils spUtils;
    private String userId;

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
        mTvSave.setVisibility(View.GONE);
        mTvSave.setText("全部已读");
        spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetOrderMessageList(userId, "0", "99", "1");
        mPresenter.GetTransactionMessageList(userId, "0", "99", "1");
        mPresenter.GetNewsLeaveMessage(userId, "10", "1");
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
                startActivity(new Intent(mActivity, SystemNotificationActivity.class));
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

    @Override
    public void GetListCategoryContentByCategoryID(BaseResult<Article> baseResult) {

    }


    /*获取工单消息数*/
    @Override
    public void GetOrderMessageList(BaseResult<MessageData<List<Message>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getCount() == 0) {
                    mTvOrderMessageNumber.setVisibility(View.GONE);
                    mTvOrderMessage.setText("暂无新的订单消息");
                    mTvOrderMessageTime.setVisibility(View.GONE);
                    return;
                } else if (baseResult.getData().getCount() >= 99) {
                    mTvOrderMessageNumber.setVisibility(View.VISIBLE);
                    mTvOrderMessageNumber.setText("99");
                    mTvOrderMessageTime.setVisibility(View.VISIBLE);
                    mTvOrderMessage.setText(baseResult.getData().getData().get(0).getContent());
                    mTvOrderMessageTime.setText(baseResult.getData().getData().get(0).getNowtime().substring(0,10));
                } else {
                    mTvOrderMessageNumber.setVisibility(View.VISIBLE);
                    mTvOrderMessageNumber.setText(baseResult.getData().getCount()+"");
                    mTvOrderMessageTime.setVisibility(View.VISIBLE);
                    mTvOrderMessage.setText(baseResult.getData().getData().get(0).getContent());
                    mTvOrderMessageTime.setText(baseResult.getData().getData().get(0).getNowtime().substring(0,10));
                }
                break;

            default:
                break;
        }
    }

    /*获取交易消息数*/
    @Override
    public void GetTransactionMessageList(BaseResult<MessageData<List<Message>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getCount() == 0) {
                    mTvTransactionNotificationNumber.setVisibility(View.INVISIBLE);
                    mTvTransactionNotification.setText("暂无新的交易消息");
                    mTvTransactionNotificationTime.setVisibility(View.GONE);
                    return;
                } else if (baseResult.getData().getCount() >= 99) {
                    mTvTransactionNotificationNumber.setVisibility(View.VISIBLE);
                    mTvTransactionNotificationNumber.setText("99");
                    mTvTransactionNotification.setText(baseResult.getData().getData().get(0).getContent());
                    mTvTransactionNotificationTime.setText(baseResult.getData().getData().get(0).getNowtime().substring(0,10));
                    mTvTransactionNotificationTime.setVisibility(View.VISIBLE);
                } else {
                    mTvTransactionNotificationNumber.setVisibility(View.VISIBLE);
                    mTvTransactionNotificationNumber.setText(baseResult.getData().getCount()+"");
                    mTvTransactionNotification.setText(baseResult.getData().getData().get(0).getContent());
                    mTvTransactionNotificationTime.setText(baseResult.getData().getData().get(0).getNowtime().substring(0,10));
                    mTvTransactionNotificationTime.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void GetNewsLeaveMessage(BaseResult<Data<LeaveMessage>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().getItem2().getNoLeaveMessage()==0) {
                    mTvMessage.setVisibility(View.INVISIBLE);
                    return;
                } else if (baseResult.getData().getItem2().getNoLeaveMessage() >= 99) {
                    mTvMessage.setVisibility(View.VISIBLE);
                    mTvMessage.setText("99");
                } else {
                    mTvMessage.setVisibility(View.VISIBLE);
                    mTvMessage.setText(baseResult.getData().getItem2().getNoLeaveMessage()+"");
                }
                break;
        }
    }
}
