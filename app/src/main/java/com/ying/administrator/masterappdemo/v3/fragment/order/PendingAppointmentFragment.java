package com.ying.administrator.masterappdemo.v3.fragment.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.NavigationBarNumber;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;
import com.ying.administrator.masterappdemo.v3.activity.AppointmentDetailsActivity;
import com.ying.administrator.masterappdemo.v3.activity.ServingDetailActivity;
import com.ying.administrator.masterappdemo.v3.adapter.PendingAdapter;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.OrderPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.OrderContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.OrderModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//待预约
public class PendingAppointmentFragment extends BaseLazyFragment<OrderPresenter, OrderModel> implements View.OnClickListener, OrderContract.View {
    private static final String ARG_SHOW_TEXT = "text";
    @BindView(R.id.tv_urgently_needed)
    TextView mTvUrgentlyNeeded;
    @BindView(R.id.tv_timed_out)
    TextView mTvTimedOut;
    @BindView(R.id.rv_pending)
    RecyclerView mRvPending;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    Unbinder unbinder;
    private String mContentText;
    private List<WorkOrder.DataBean> list = new ArrayList<>();
    private PendingAdapter pendingAdapter;
    private String state;
    private String userId;
    private int page = 1;
    private WorkOrder workOrder;
    public PendingAppointmentFragment() {
        // Required empty public constructor
    }

    public static PendingAppointmentFragment newInstance() {
        PendingAppointmentFragment fragment = new PendingAppointmentFragment();
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
        return R.layout.v3_fragment_pending_appointment;
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
                EventBus.getDefault().post(20);
                refreshlayout.resetNoMoreData();
                mRefreshLayout.finishRefresh();
            }
        });


        //没满屏时禁止上拉
        mRefreshLayout.setEnableLoadmoreWhenContentNotFull(false);
        //上拉加载更多
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++; //页数加1
                mPresenter.WorkerGetOrderList(userId, state, page + "", "10");
                mRefreshLayout.finishLoadmore();
            }
        });
    }

    @Override
    protected void initView() {
        mTvUrgentlyNeeded.setSelected(true);
        mTvTimedOut.setSelected(false);
        mRefreshLayout.autoRefresh(0,0,1);
        state = "1";
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mRefreshLayout.autoRefresh(0, 0, 1);
        mPresenter.WorkerGetOrderList(userId, state, page + "", "10");
//        for (int i = 0; i < 10; i++) {
//            list.add(new WorkOrder.DataBean());
//        }
        pendingAdapter = new PendingAdapter(R.layout.v3_item_home, list,userId);
        mRvPending.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvPending.setAdapter(pendingAdapter);
        pendingAdapter.setEmptyView(getHomeEmptyView());
        pendingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if ("1".equals(state)){
//                    if (list.get(position).getOrderAccessroyDetail().size()>0){
//                        Intent intent=new Intent(mActivity, ServingDetailActivity.class);
//                        intent.putExtra("id",list.get(position).getOrderID());
//                        startActivity(intent);
//                    }else {
                        Intent intent=new Intent(mActivity, AppointmentDetailsActivity.class);
                        intent.putExtra("id",list.get(position).getOrderID());
                        startActivity(intent);
//                    }
                }else {
                    Intent intent=new Intent(mActivity, ServingDetailActivity.class);
                    intent.putExtra("id",list.get(position).getOrderID());
                    intent.putExtra("type","pedding");
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void setListener() {
        mTvUrgentlyNeeded.setOnClickListener(this);
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
                mTvTimedOut.setSelected(false);
                state = "1";
                page=1;
                list.clear();
                mPresenter.WorkerGetOrderList(userId, state, page + "", "10");
                break;
            case R.id.tv_timed_out:
                mTvUrgentlyNeeded.setSelected(false);
                mTvTimedOut.setSelected(true);
                state = "16";
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
                    if (page==1){
                        list.clear();
                    }
                    list.addAll(workOrder.getData());
                    pendingAdapter.setNewData(list);

                }else {
                    pendingAdapter.setEmptyView(getHomeEmptyView());
                }

                break;
        }
    }

    //任意写一个方法，给这个方法一个@Subscribe注解，参数类型可以自定义，但是一定要与你发出的类型相同
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(Integer num) {
        switch (num) {
            case 1:
                list.clear();
                page = 1;
                mPresenter.WorkerGetOrderList(userId, state, page + "", "10");
                break;
            case 2:
                list.clear();
                page = 1;
                mPresenter.WorkerGetOrderList(userId, state, page + "", "10");
                break;
            case 22:
                mTvUrgentlyNeeded.setSelected(true);
                mTvTimedOut.setSelected(false);
                state = "1";
                list.clear();
                page = 1;
                mPresenter.WorkerGetOrderList(userId, state, page + "", "10");
                break;
            case Config.ORDER_READ:

//                mPresenter.WorkerGetOrderRed(userid);

            default:
                break;
        }
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        list.clear();
        page=1;
        mPresenter.WorkerGetOrderList(userId, state, page + "", "10");
    }
}
