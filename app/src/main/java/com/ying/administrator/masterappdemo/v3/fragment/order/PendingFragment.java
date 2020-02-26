package com.ying.administrator.masterappdemo.v3.fragment.order;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
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
import com.ying.administrator.masterappdemo.v3.adapter.PendingAdapter;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.OrderPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.OrderContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.OrderModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//待处理
public class PendingFragment extends BaseLazyFragment<OrderPresenter, OrderModel> implements View.OnClickListener, OrderContract.View {
    private static final String ARG_SHOW_TEXT = "text";
    @BindView(R.id.tv_urgently_needed)
    TextView mTvUrgentlyNeeded;
    @BindView(R.id.tv_come_tomorrow)
    TextView mTvComeTomorrow;
    @BindView(R.id.tv_timed_out)
    TextView mTvTimedOut;

    Unbinder unbinder;
    @BindView(R.id.rv_pending)
    RecyclerView mRvPending;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;


    private String mContentText;
    private List<WorkOrder.DataBean> list = new ArrayList<>();
    private PendingAdapter pendingAdapter;
    private String state;
    private String userId;
    private int page = 1;
    private WorkOrder workOrder;

    public PendingFragment() {
        // Required empty public constructor
    }

    public static PendingFragment newInstance() {
        PendingFragment fragment = new PendingFragment();
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
        return R.layout.v3_fragment_pending;
    }

    @Override
    protected void initData() {
        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                list.clear();
                page = 1;
                mPresenter.WorkerGetOrderList(userId, state, page + "", "10");
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
                mPresenter.WorkerGetOrderList(userId, state, page + "", "10");

            }
        });
    }

    @Override
    protected void initView() {
        mTvUrgentlyNeeded.setSelected(true);
        mTvComeTomorrow.setSelected(false);
        mTvTimedOut.setSelected(false);
        state = "13";
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.WorkerGetOrderList(userId, state, page + "", "10");
//        for (int i = 0; i < 10; i++) {
//            list.add(new WorkOrder.DataBean());
//        }
        pendingAdapter = new PendingAdapter(R.layout.v3_item_home, list);
        mRvPending.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvPending.setAdapter(pendingAdapter);
        pendingAdapter.setEmptyView(getHomeEmptyView());
    }

    @Override
    protected void setListener() {
        mTvUrgentlyNeeded.setOnClickListener(this);
        mTvComeTomorrow.setOnClickListener(this);
        mTvTimedOut.setOnClickListener(this);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_urgently_needed:
                mTvUrgentlyNeeded.setSelected(true);
                mTvComeTomorrow.setSelected(false);
                mTvTimedOut.setSelected(false);
                state = "13";
                page=1;
                list.clear();
                mPresenter.WorkerGetOrderList(userId, state, page + "", "10");
                break;
            case R.id.tv_come_tomorrow:
                mTvUrgentlyNeeded.setSelected(false);
                mTvComeTomorrow.setSelected(true);
                mTvTimedOut.setSelected(false);
                state = "14";
                page=1;
                list.clear();
                mPresenter.WorkerGetOrderList(userId, state, page + "", "10");
                break;
            case R.id.tv_timed_out:
                mTvUrgentlyNeeded.setSelected(false);
                mTvComeTomorrow.setSelected(false);
                mTvTimedOut.setSelected(true);
                state = "15";
                page=1;
                list.clear();
                mPresenter.WorkerGetOrderList(userId, state, page + "", "10");
                break;
        }
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
                    pendingAdapter.setNewData(list);

                }else {
                    pendingAdapter.setEmptyView(getHomeEmptyView());
                }

                break;
        }
    }
}
