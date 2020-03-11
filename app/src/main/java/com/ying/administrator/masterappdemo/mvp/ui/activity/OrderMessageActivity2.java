package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
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
import com.ying.administrator.masterappdemo.v3.activity.ServingDetailActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*工单消息*/
public class OrderMessageActivity2 extends BaseActivity<MyMessagePresenter, MyMessageModel> implements MyMessageContract.View, View.OnClickListener {


    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_ordermessage)
    RecyclerView mRvOrdermessage;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_all_read)
    TextView mTvAllRead;
    @BindView(R.id.iv_back)
    ImageView mIvBack;

    private MessageAdapter messageAdapter;
    private int pageIndex = 1;

    private String userId;
    private List<Message> list = new ArrayList<>();//未读
    private int pos;
    private int type = 1;//1.交易信息  2.工单消息
    private int subType=0;
    @Override
    protected int setLayoutId() {
        return R.layout.activity_ordermessage2;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initData() {
        mRvOrdermessage.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvOrdermessage.setHasFixedSize(true);
        mRvOrdermessage.setNestedScrollingEnabled(false);
        messageAdapter = new MessageAdapter(R.layout.item_message, list);
        mRvOrdermessage.setAdapter(messageAdapter);
        messageAdapter.setEmptyView(getMessageEmptyView());
        type = getIntent().getIntExtra("type", 1);
        subType=getIntent().getIntExtra("subType",1);
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetMessageList(userId, Integer.toString(type), Integer.toString(subType), "10", Integer.toString(pageIndex));
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mTvAllRead.setOnClickListener(this);
        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex = 1;
                list.clear();
                mPresenter.GetMessageList(userId, Integer.toString(type), Integer.toString(subType), "10", Integer.toString(pageIndex));
                refreshlayout.finishRefresh();
                refreshlayout.resetNoMoreData();
            }
        });

        //上拉加载更多
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageIndex++; //页数加1
                mPresenter.GetMessageList(userId, Integer.toString(type), Integer.toString(subType), "10", Integer.toString(pageIndex));
                refreshlayout.finishLoadmore();
            }
        });
        messageAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                pos = position;
                switch (view.getId()) {
                    case R.id.ll_order_message:
                        mPresenter.AddOrUpdatemessage(((Message) adapter.getData().get(position)).getMessageID(), "2");
                        if (!"0".equals(((Message) adapter.getData().get(position)).getOrderID())) {
                            Intent intent=new Intent(mActivity, ServingDetailActivity.class);
                            intent.putExtra("id",list.get(position).getOrderID());
                            startActivity(intent);
                        }else {
                            return;
                        }
                        break;
                }
            }
        });
    }


    @Override
    public void GetMessageList(BaseResult<MessageData<List<Message>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (pageIndex != 1 && baseResult.getData().getData().size() == 0) {
                    mRefreshLayout.finishLoadmoreWithNoMoreData();
                }
                list.addAll(baseResult.getData().getData());
                messageAdapter.setNewData(list);
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

                } else {

                }
                break;
            default:
                break;
        }

    }

    /*更新消息为已读*/
    @Override
    public void AddOrUpdatemessage(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    list.get(pos).setIsLook("2");
                    messageAdapter.setNewData(list);
                    EventBus.getDefault().post("orderempty");
                    EventBus.getDefault().post("order_num");
                    EventBus.getDefault().post("transaction_num");
//                    mRefreshLayout.autoRefresh();
                    pageIndex = 1;
                    list.clear();
                    mPresenter.GetMessageList(userId, Integer.toString(type), Integer.toString(subType), "10", Integer.toString(pageIndex));
                }
                break;
        }
    }

    @Override
    public void AllRead(BaseResult<MessageData<List<Message>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
//                    if (baseResult.getData().isItem1()) {
                list.get(pos).setIsLook("2");
                messageAdapter.setNewData(list);
                EventBus.getDefault().post("orderempty");
                EventBus.getDefault().post("order_num");
                EventBus.getDefault().post("transaction_num");
//                    }
                hideProgress();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                OrderMessageActivity2.this.finish();
                break;
            case R.id.tv_all_read:
                showProgress();
                mPresenter.AllRead(userId, Integer.toString(type),Integer.toString(subType) );
                break;
        }
    }
}
