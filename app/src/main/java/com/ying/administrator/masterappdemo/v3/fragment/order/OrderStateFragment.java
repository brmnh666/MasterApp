package com.ying.administrator.masterappdemo.v3.fragment.order;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.NavigationBarNumber;
import com.ying.administrator.masterappdemo.entity.NavigationBarNumberSon;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;
import com.ying.administrator.masterappdemo.v3.activity.ServingDetailActivity;
import com.ying.administrator.masterappdemo.v3.adapter.OrderAdapter;
import com.ying.administrator.masterappdemo.v3.bean.OrderListResult;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.OrderPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.OrderContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.OrderModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//待审核
public class OrderStateFragment extends BaseLazyFragment <OrderPresenter, OrderModel> implements OrderContract.View {
    @BindView(R.id.rv_order)
    RecyclerView mRvOrder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    Unbinder unbinder;
    private String userId;
    private int page=1;
    private List<OrderListResult.DataBeanX.DataBean> list = new ArrayList<>();
    private OrderAdapter adapter;
    private OrderListResult.DataBeanX workOrder;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    private String state;
    private OrderListResult.DataBeanX.DataBean dataBean;
    private List<OrderListResult.DataBeanX.DataBean.OrderProductModelsBean> models;
    private OrderListResult.DataBeanX.DataBean.OrderProductModelsBean model;

    public OrderStateFragment() {
        // Required empty public constructor
    }

    public static OrderStateFragment newInstance(String state) {
        OrderStateFragment fragment = new OrderStateFragment();
        Bundle args = new Bundle();
        args.putString("state", state);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            state = getArguments().getString("state");
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
                mPresenter.GetOrderList("", state, page + "", "10");
                EventBus.getDefault().post(20);
                refreshlayout.resetNoMoreData();
                mRefreshLayout.finishRefresh();
                mRefreshLayout.finishLoadmore();
            }
        });


        //没满屏时禁止上拉
        mRefreshLayout.setEnableLoadmoreWhenContentNotFull(false);
        //上拉加载更多
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++; //页数加1
                mPresenter.GetOrderList("", state, page + "", "10");
                mRefreshLayout.finishRefresh();
                mRefreshLayout.finishLoadmore();
            }
        });
    }

    @Override
    protected void initView() {
        mRefreshLayout.autoRefresh(0,0,1);
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetOrderList("", state, page + "", "10");
        adapter = new OrderAdapter(R.layout.v3_item_home, list,"",userId);
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
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.iv_copy:
                        dataBean =list.get(position);
                        models = dataBean.getOrderProductModels();
                        String name="";
                        if (models !=null){
                            for (int i = 0; i < models.size(); i++) {
                                model =models.get(i);
                                name+= model.getBrandName()+"("+ model.getSubCategoryName()+")"+ model.getProdModelName()+"、";
                            }
                        }
                        if (name.contains("、")){
                            name=name.substring(0,name.lastIndexOf("、"));
                        }
                        myClip = ClipData.newPlainText("", "下单厂家："+dataBean.getCompanyName() + "\n"
                                +"工单号："+dataBean.getOrderID() + "\n"
                                +"下单时间："+dataBean.getCreateDate() + "\n"
                                +"用户信息："+dataBean.getUserName()+" "+dataBean.getPhone() + "\n"
                                +"用户地址："+dataBean.getAddress() + "\n"
                                +"产品信息："+name + "\n"
                                +"售后类型："+dataBean.getGuaranteeText() + "\n"
                                +"服务类型："+dataBean.getTypeName()
                        );
                        myClipboard.setPrimaryClip(myClip);
                        ToastUtils.showShort("复制成功");
                        break;
                }
            }
        });
        myClipboard = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
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
    public void GetOrderList(OrderListResult baseResult) {
        try {
            mRefreshLayout.finishRefresh();
            mRefreshLayout.finishLoadmore();
            switch (baseResult.getStatusCode()){
                case 200:
                    if (page!=1&&baseResult.getData().getData().size()==0){
                        mRefreshLayout.finishLoadmoreWithNoMoreData();
                    }
                    workOrder = baseResult.getData();
                    if (workOrder.getData()!=null){
                        list.addAll(workOrder.getData());
                        adapter.setNewData(list);

                    }else {
                        adapter.setEmptyView(getHomeEmptyView());
                    }
                    hideProgress();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void NavigationBarNumber(BaseResult<Data<NavigationBarNumber>> baseResult) {

    }

    @Override
    public void NavigationBarNumberSon(BaseResult<Data<NavigationBarNumberSon>> baseResult) {

    }

    @Override
    public void WorkerGetOrderList(BaseResult<WorkOrder> baseResult) {

    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void Event(Integer num) {
////        mFragments.clear();
//        switch (num) {
//            case 3:
//                list.clear();
//                page=1;
//                mPresenter.WorkerGetOrderList(userId, state, page + "", "10");
//                break;
//
//        }
//    }

    @Override
    protected void onVisible() {
        super.onVisible();
        showProgress();
        list.clear();
        adapter.notifyDataSetChanged();
        page=1;
        mPresenter.GetOrderList("", state, page + "", "10");
    }
}
