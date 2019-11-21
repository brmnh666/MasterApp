package com.ying.administrator.masterappdemo.mvp.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Article;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.LeaveMessage;
import com.ying.administrator.masterappdemo.entity.Message;
import com.ying.administrator.masterappdemo.entity.MessageData;
import com.ying.administrator.masterappdemo.mvp.contract.ArticleContract;
import com.ying.administrator.masterappdemo.mvp.model.ArticleModel;
import com.ying.administrator.masterappdemo.mvp.presenter.ArticlePresenter;
import com.ying.administrator.masterappdemo.mvp.ui.activity.ArticleActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.LeaveMessageActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.OrderMessageActivity2;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import q.rorbin.badgeview.QBadgeView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InformationFragment extends BaseLazyFragment<ArticlePresenter, ArticleModel> implements View.OnClickListener, ArticleContract.View {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";
    @BindView(R.id.tv_work_order_message)
    TextView mTvWorkOrderMessage;
    @BindView(R.id.ll_work_order_message)
    LinearLayout mLlWorkOrderMessage;
    @BindView(R.id.tv_trading_information)
    TextView mTvTradingInformation;
    @BindView(R.id.ll_transaction_news)
    LinearLayout mLlTransactionNews;
    @BindView(R.id.tv_system_information)
    TextView mTvSystemInformation;
    @BindView(R.id.ll_announcement)
    LinearLayout mLlAnnouncement;

    @BindView(R.id.ll_workmessage)
    LinearLayout MLl_workmessage;

    @BindView(R.id.ll_transactionmessage)
    LinearLayout MLl_transactionmessage;
    Unbinder unbinder;
    @BindView(R.id.tv_leave_message)
    TextView mTvLeaveMessage;
    @BindView(R.id.ll_leave)
    LinearLayout mLlLeave;
    @BindView(R.id.ll_leave_message)
    LinearLayout mLlLeaveMessage;

    private QBadgeView workqBadgeView;
    private QBadgeView transactionqBadgeView;
    private QBadgeView LeaveMessageBadgeView;
    private View view;
    private String mContentText;
    private SPUtils spUtils = SPUtils.getInstance("token");
    private String userId = spUtils.getString("userName");

    public InformationFragment() {
        // Required empty public constructor
    }

    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment BlankFragment.
     */
    public static InformationFragment newInstance(String param1, String param2) {
        InformationFragment fragment = new InformationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContentText = getArguments().getString(ARG_SHOW_TEXT);
        }
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_information;
    }

    @Override
    protected void initData() {


        workqBadgeView = new QBadgeView(mActivity);
        workqBadgeView.bindTarget(MLl_workmessage);
        workqBadgeView.setBadgeGravity(Gravity.CENTER | Gravity.END);


        transactionqBadgeView = new QBadgeView(mActivity);
        transactionqBadgeView.bindTarget(MLl_transactionmessage);
        transactionqBadgeView.setBadgeGravity(Gravity.CENTER | Gravity.END);

        LeaveMessageBadgeView = new QBadgeView(mActivity);
        LeaveMessageBadgeView.bindTarget(mLlLeave);
        LeaveMessageBadgeView.setBadgeGravity(Gravity.CENTER | Gravity.END);

        mPresenter.GetOrderMessageList(userId, "0", "99", "1");
        mPresenter.GetTransactionMessageList(userId, "0", "99", "1");
        mPresenter.GetNewsLeaveMessage(userId,"10","1");
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void setListener() {
        mLlWorkOrderMessage.setOnClickListener(this);
        mLlTransactionNews.setOnClickListener(this);
        mLlAnnouncement.setOnClickListener(this);
        mLlLeaveMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.ll_work_order_message://工单消息
                intent = new Intent(getActivity(), OrderMessageActivity2.class);
                intent.putExtra("type", 2);
                startActivity(intent);
                break;
            case R.id.ll_transaction_news://交易信息
                intent = new Intent(getActivity(), OrderMessageActivity2.class);
                intent.putExtra("type", 1);
                startActivity(intent);
                break;
            case R.id.ll_announcement:
                intent = new Intent(mActivity, ArticleActivity.class);
                intent.putExtra("CategoryID", "7");
                startActivity(intent);
                break;
            case R.id.ll_leave_message:
                startActivity(new Intent(mActivity, LeaveMessageActivity.class));
                break;
            default:
                break;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
                    workqBadgeView.setVisibility(View.INVISIBLE);
                    return;
                } else if (baseResult.getData().getCount() >= 99) {
                    workqBadgeView.setVisibility(View.VISIBLE);
                    workqBadgeView.setBadgeNumber(99);
                } else {
                    workqBadgeView.setVisibility(View.VISIBLE);
                    workqBadgeView.setBadgeNumber(baseResult.getData().getCount());
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
                    transactionqBadgeView.setVisibility(View.INVISIBLE);
                    return;
                } else if (baseResult.getData().getCount() >= 99) {
                    transactionqBadgeView.setVisibility(View.VISIBLE);
                    transactionqBadgeView.setBadgeNumber(99);
                } else {
                    transactionqBadgeView.setVisibility(View.VISIBLE);
                    transactionqBadgeView.setBadgeNumber(baseResult.getData().getCount());
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
                    LeaveMessageBadgeView.setVisibility(View.INVISIBLE);
                    return;
                } else if (baseResult.getData().getItem2().getNoLeaveMessage() >= 99) {
                    LeaveMessageBadgeView.setVisibility(View.VISIBLE);
                    LeaveMessageBadgeView.setBadgeNumber(99);
                } else {
                    LeaveMessageBadgeView.setVisibility(View.VISIBLE);
                    LeaveMessageBadgeView.setBadgeNumber(baseResult.getData().getItem2().getNoLeaveMessage());
                }
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Subscribe
    public void Event(String message) {
        switch (message) {
            case "transaction_num":
                mPresenter.GetTransactionMessageList(userId, "0", "99", "1");
                break;
            case "order_num":
                mPresenter.GetOrderMessageList(userId, "0", "99", "1");
                break;
            case "LeaveMessage":
                mPresenter.GetNewsLeaveMessage(userId,"10","1");
                break;

        }

    }


}
