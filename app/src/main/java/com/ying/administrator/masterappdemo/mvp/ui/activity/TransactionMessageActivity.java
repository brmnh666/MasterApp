package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.Message;
import com.ying.administrator.masterappdemo.entity.MessageData;
import com.ying.administrator.masterappdemo.mvp.contract.MyMessageContract;
import com.ying.administrator.masterappdemo.mvp.model.MyMessageModel;
import com.ying.administrator.masterappdemo.mvp.presenter.MyMessagePresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.MessageAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*交易消息页面*/
public class TransactionMessageActivity extends BaseActivity<MyMessagePresenter, MyMessageModel> implements MyMessageContract.View, View.OnClickListener {
    @BindView(R.id.img_actionbar_return)
    ImageView mImgActionbarReturn;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_transactionmessage)
    RecyclerView mRvTransactionmessage;//未读

    @BindView(R.id.rv_transactionmessage_historical)
    RecyclerView mRv_transactionmessage_historical;//已读

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.tv_message_number)
    TextView mTv_message_number;
    @BindView(R.id.textView1)
    TextView mTextView1;
    @BindView(R.id.textView2)
    TextView mTextView2;
    @BindView(R.id.rl_new_message)
    RelativeLayout mRlNewMessage;
    @BindView(R.id.tv_old_number)
    TextView mTvOldNumber;
    @BindView(R.id.textView3)
    TextView mTextView3;
    @BindView(R.id.textView4)
    TextView mTextView4;
    @BindView(R.id.rl_old_message)
    RelativeLayout mRlOldMessage;

    private String userId;
    private MessageAdapter messageAdapter;
    private MessageAdapter messagereadAdapter;
    private List<Message> list = new ArrayList<>();
    private List<Message> readlist = new ArrayList<>();//已读

    private int pageIndex = 1;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_transactionmessage;
    }

    @Override
    protected void initData() {
        mRvTransactionmessage.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvTransactionmessage.setHasFixedSize(true);
        mRvTransactionmessage.setNestedScrollingEnabled(false);
        messageAdapter = new MessageAdapter(R.layout.item_message, list);
        mRvTransactionmessage.setAdapter(messageAdapter);


        mRv_transactionmessage_historical.setLayoutManager(new LinearLayoutManager(mActivity));
        mRv_transactionmessage_historical.setHasFixedSize(true);
        mRv_transactionmessage_historical.setNestedScrollingEnabled(false);
        messagereadAdapter = new MessageAdapter(R.layout.item_message, readlist);
        mRv_transactionmessage_historical.setAdapter(messagereadAdapter);


        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetMessageList(userId, "1", "0", "999", "1");
        mPresenter.GetReadMessageList(userId, "1", "0", "999", "1");


    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mImgActionbarReturn.setOnClickListener(this);

        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
          /*      if (!list.isEmpty()){ //当有数据的时候
                    ll_empty.setVisibility(View.INVISIBLE);//隐藏空的界面
                }*/
                pageIndex = 1;
                //list.clear();
                mPresenter.GetMessageList(userId, "1", "0", "999", Integer.toString(pageIndex));
                messageAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh();
            }
        });
        //没满屏时禁止上拉
        mRefreshLayout.setEnableLoadmoreWhenContentNotFull(false);
        //上拉加载更多
       /* mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageIndex++; //页数加1
                //  mPresenter.WorkerGetOrderList(userID,"1",Integer.toString(pageIndex),"5");
                mPresenter.GetMessageList(userId, "1", "0", "10", Integer.toString(pageIndex));
                messageAdapter.notifyDataSetChanged();
                refreshlayout.finishLoadmore();
            }
        });*/

        messageAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                switch (view.getId()) {
                    case R.id.ll_order_message:
                        mPresenter.AddOrUpdatemessage(((Message) adapter.getData().get(position)).getMessageID(), "2");


                        break;

                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_actionbar_return:
                TransactionMessageActivity.this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void GetMessageList(BaseResult<MessageData<List<Message>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getData() == null) {

                    mTv_message_number.setText("暂无新消息");
                    mRlNewMessage.setVisibility(View.GONE);
                    return;

                } else {
                    list.clear();
                    list.addAll(baseResult.getData().getData());

                    if (baseResult.getData().getData().size() > 99) {
                        mTv_message_number.setText("您有99+条新消息");
                    } else if (baseResult.getData().getData().size() == 0) {
                        mTv_message_number.setText("暂无新消息");
                        mRlNewMessage.setVisibility(View.GONE);
                    } else {
                        mTv_message_number.setText("您有" + baseResult.getData().getData().size() + "条新消息");
                    }

                    messageAdapter.notifyDataSetChanged();
                }
                if (baseResult.getData().getCount() == 0) {

                    EventBus.getDefault().post("transactionempty");

                }


                break;
            default:
                break;
        }
    }

    @Override
    public void GetReadMessageList(BaseResult<MessageData<List<Message>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getData() == null) {
                    return;

                } else {


                    messagereadAdapter.setNewData(baseResult.getData().getData());
                    messagereadAdapter.notifyDataSetChanged();
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void AddOrUpdatemessage(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    EventBus.getDefault().post("transaction_num");
                    mPresenter.GetMessageList(userId, "1", "0", "999", "1");
                    mPresenter.GetReadMessageList(userId, "1", "0", "999", "1");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void AllRead(BaseResult<MessageData<List<Message>>> baseResult) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
