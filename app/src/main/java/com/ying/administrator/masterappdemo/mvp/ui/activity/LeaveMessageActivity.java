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
import com.ying.administrator.masterappdemo.entity.LeaveMessage;
import com.ying.administrator.masterappdemo.mvp.contract.LeaveMeaasgeContract;
import com.ying.administrator.masterappdemo.mvp.model.LeaveMeaasgeModel;
import com.ying.administrator.masterappdemo.mvp.presenter.LeaveMeaasgePreaenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.MessageAdapter2;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*留言消息*/
public class LeaveMessageActivity extends BaseActivity<LeaveMeaasgePreaenter, LeaveMeaasgeModel> implements LeaveMeaasgeContract.View, View.OnClickListener {


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

    private MessageAdapter2 messageAdapter;
    private int pageIndex = 1;

    private String userId;
    private List<LeaveMessage.DataBean> list = new ArrayList<>();//未读
    private int pos;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_leavemessage;
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
        messageAdapter = new MessageAdapter2(R.layout.item_message, list);
        mRvOrdermessage.setAdapter(messageAdapter);
        messageAdapter.setEmptyView(getMessageEmptyView());
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetNewsLeaveMessage(userId, "10", String.valueOf(pageIndex));
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
                mPresenter.GetNewsLeaveMessage(userId, "10", String.valueOf(pageIndex));
                refreshlayout.finishRefresh();
                refreshlayout.resetNoMoreData();
            }
        });

        //上拉加载更多
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageIndex++; //页数加1
                mPresenter.GetNewsLeaveMessage(userId, "10", String.valueOf(pageIndex));
                refreshlayout.finishLoadmore();
            }
        });
        messageAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                pos = position;
                switch (view.getId()) {
                    case R.id.ll_order_message:
                        mPresenter.LeaveMessageWhetherLook(list.get(position).getOrderId());
                        if (!"0".equals(list.get(position).getOrderId())) {
                            Intent intent = new Intent(mActivity, MessageActivity2.class);
                            intent.putExtra("orderId", list.get(position).getOrderId());
                            startActivity(intent);
                        }
                        break;
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                LeaveMessageActivity.this.finish();
                break;
            case R.id.tv_all_read:
                showProgress();
                break;
        }
    }

    @Override
    public void GetNewsLeaveMessage(BaseResult<Data<LeaveMessage>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (pageIndex != 1 && baseResult.getData().getItem2().getData().size() == 0) {
                    mRefreshLayout.finishLoadmoreWithNoMoreData();
                }
                list.addAll(baseResult.getData().getItem2().getData());
                messageAdapter.setNewData(list);
                break;
        }
    }

    @Override
    public void LeaveMessageWhetherLook(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    list.get(pos).setWorkerIslook("2");
                    messageAdapter.setNewData(list);
                    EventBus.getDefault().post("orderempty");
                    EventBus.getDefault().post("LeaveMessage");
                    mRefreshLayout.autoRefresh();
                }
                break;
        }
    }
}
