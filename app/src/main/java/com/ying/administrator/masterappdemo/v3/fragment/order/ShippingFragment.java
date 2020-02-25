package com.ying.administrator.masterappdemo.v3.fragment.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.NavigationBarNumber;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;
import com.ying.administrator.masterappdemo.v3.activity.QuoteDetailsActivity;
import com.ying.administrator.masterappdemo.v3.adapter.OrderAdapter;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.OrderPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.OrderContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.OrderModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//待寄件
public class ShippingFragment extends BaseLazyFragment <OrderPresenter, OrderModel> implements OrderContract.View {
    private static final String ARG_SHOW_TEXT = "text";
    @BindView(R.id.rv_order)
    RecyclerView mRvOrder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    Unbinder unbinder;
    private String mContentText;
    private String userId;
    private int page=1;
    private List<WorkOrder.DataBean> list = new ArrayList<>();
    private OrderAdapter adapter;
    private WorkOrder workOrder;
    public ShippingFragment() {
        // Required empty public constructor
    }

    public static ShippingFragment newInstance() {
        ShippingFragment fragment = new ShippingFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContentText = getArguments().getString(ARG_SHOW_TEXT);
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.v3_fragment_shipping;
    }

    @Override
    protected void initData() {
        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                list.clear();
                page=1;
                mPresenter.WorkerGetOrderList(userId, "11", page + "", "10");
                refreshlayout.resetNoMoreData();
            }
        });


        //没满屏时禁止上拉
//        mRefreshLayout.setEnableLoadmoreWhenContentNotFull(false);
        //上拉加载更多
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++; //页数加1
                mPresenter.WorkerGetOrderList(userId, "11", page + "", "10");

            }
        });
    }

    @Override
    protected void initView() {
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.WorkerGetOrderList(userId, "11", page + "", "10");
        adapter = new OrderAdapter(R.layout.v3_item_home, list);
        mRvOrder.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvOrder.setAdapter(adapter);
        adapter.setEmptyView(getHomeEmptyView());
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_orders:
                        startActivity(new Intent(mActivity, QuoteDetailsActivity.class));
                        break;
                }
            }
        });
    }

    @Override
    protected void setListener() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void NavigationBarNumber(BaseResult<Data<NavigationBarNumber>> baseResult) {

    }

    @Override
    public void WorkerGetOrderList(BaseResult<WorkOrder> baseResult) {
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
