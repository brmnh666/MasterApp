package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Message;
import com.ying.administrator.masterappdemo.entity.MessageData;
import com.ying.administrator.masterappdemo.mvp.contract.MyMessageContract;
import com.ying.administrator.masterappdemo.mvp.model.MyMessageModel;
import com.ying.administrator.masterappdemo.mvp.presenter.MyMessagePresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.MessageAdapter;

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
    RecyclerView mRvTransactionmessage;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private String userId;
    private MessageAdapter messageAdapter;
    private List<Message> list = new ArrayList<>();
    private int pageIndex=1;

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

        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetMessageList(userId, "1","0", "10", "1");
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
                pageIndex=1;
                //list.clear();
                mPresenter.GetMessageList(userId, "1","0",  "10", Integer.toString(pageIndex));
                messageAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh();
            }
        });
        //没满屏时禁止上拉
        mRefreshLayout.setEnableLoadmoreWhenContentNotFull(false);
        //上拉加载更多
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageIndex++; //页数加1
                //  mPresenter.WorkerGetOrderList(userID,"1",Integer.toString(pageIndex),"5");
                mPresenter.GetMessageList(userId, "1", "0", "10", Integer.toString(pageIndex));
                messageAdapter.notifyDataSetChanged();
                refreshlayout.finishLoadmore();
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

                if (baseResult.getData().getData()==null){
                    if (pageIndex==1){
                        list.clear();
                        messageAdapter.notifyDataSetChanged();
                    }
                }else {
                    if (pageIndex==1){
                        list.clear();
                        list.addAll(baseResult.getData().getData());
                        messageAdapter.notifyDataSetChanged();
                    }else {
                        list.addAll(baseResult.getData().getData());
                        messageAdapter.setNewData(list);
                    }

                }
                break;
            default:
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
