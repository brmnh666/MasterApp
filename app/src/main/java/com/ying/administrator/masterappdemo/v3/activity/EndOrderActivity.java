package com.ying.administrator.masterappdemo.v3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.v3.adapter.OrderAdapter;
import com.ying.administrator.masterappdemo.v3.bean.OrderListResult;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.EndOrderPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.EndOrderContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.EndOrderModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EndOrderActivity extends BaseActivity<EndOrderPresenter, EndOrderModel> implements View.OnClickListener, EndOrderContract.View {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.rv_order)
    RecyclerView mRvOrder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private String id;
    private int page = 1;
    private List<OrderListResult.DataBeanX.DataBean> list = new ArrayList<>();
    private OrderAdapter adapter;
    private OrderListResult.DataBeanX workOrder;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_end_order;
    }

    @Override
    protected void initData() {
        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                list.clear();
                page=1;
                mPresenter.GetOrderList("", "8", page + "", "10");
                EventBus.getDefault().post(20);
                refreshlayout.resetNoMoreData();
            }
        });


        //没满屏时禁止上拉
        mRefreshLayout.setEnableLoadmoreWhenContentNotFull(false);
        //上拉加载更多
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++; //页数加1
                mPresenter.GetOrderList("", "8", page + "", "10");

            }
        });
    }

    @Override
    protected void initView() {
        mTvTitle.setText("完结工单");
        id = getIntent().getStringExtra("id");
        mPresenter.GetOrderList("", "6", page + "", "10");

        adapter = new OrderAdapter(R.layout.v3_item_home, list, "return",id);
        mRvOrder.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvOrder.setAdapter(adapter);
        adapter.setEmptyView(getHomeEmptyView());
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(mActivity, ServingDetailActivity.class);
                intent.putExtra("id",list.get(position).getOrderID());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
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
    public void GetOrderList(OrderListResult baseResult) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
        switch (baseResult.getStatusCode()){
            case 200:
                workOrder = baseResult.getData();
                if (workOrder.getData()!=null){
                    list.addAll(workOrder.getData());
                    adapter.setNewData(list);

                }else {
                    adapter.setEmptyView(getHomeEmptyView());
                }

                break;
        }
    }
}
