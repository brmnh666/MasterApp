package com.ying.administrator.masterappdemo.v3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.Article;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.ui.activity.WebActivity;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;
import com.ying.administrator.masterappdemo.v3.activity.ApplyFeeActivity;
import com.ying.administrator.masterappdemo.v3.activity.MessageActivity;
import com.ying.administrator.masterappdemo.v3.activity.QuoteDetailsActivity;
import com.ying.administrator.masterappdemo.v3.adapter.HomeAdapter;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.HomePresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.HomeContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.HomeModel;
import com.ying.administrator.masterappdemo.widget.SwitchView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.widget.Toast.LENGTH_SHORT;

public class HomeFragment extends BaseLazyFragment<HomePresenter, HomeModel> implements View.OnClickListener, HomeContract.View {
    private static final String ARG_SHOW_TEXT = "text";
    @BindView(R.id.ll_message)
    LinearLayout mLlMessage;
    @BindView(R.id.scrolltv)
    SwitchView mScrolltv;
    @BindView(R.id.ll_switch)
    LinearLayout mLlSwitch;
    @BindView(R.id.rv_new_order)
    RecyclerView mRvNewOrder;
    Unbinder unbinder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.iv_red)
    ImageView mIvRed;
    private String mContentText;
    private int i = 0;
    private List<Article.DataBean> datalist = new ArrayList<>();
    private List<WorkOrder.DataBean> list = new ArrayList<>();
    private HomeAdapter adapter;
    private String userId;
    private int page = 1;
    private WorkOrder workOrder;
    private int grabposition;

    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
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
        return R.layout.v3_fragment_home;
    }

    @Override
    protected void initData() {

        adapter = new HomeAdapter(R.layout.v3_item_home, list);
        mRvNewOrder.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvNewOrder.setAdapter(adapter);
        adapter.setEmptyView(getHomeEmptyView());
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_orders:
                        grabposition = position;
                        if ("0".equals(list.get(position).getPartyNo())) {
//                            Intent intent=new Intent(mActivity,QuoteDetailsActivity.class);
//                            intent.putExtra("id",list.get(position).getOrderID());
//                            startActivity(intent);
                            mPresenter.UpdateSendOrderState(list.get(position).getOrderID(), "1", "");
                        } else {
                            if ("true".equals(list.get(position).getDistanceTureOrFalse())) {
                                Intent intent = new Intent(mActivity, ApplyFeeActivity.class);
                                intent.putExtra("position", position);
                                intent.putExtra("orderId", list.get(position).getOrderID());
                                startActivity(intent);
                            } else {
                                mPresenter.UpdateSendOrderState(list.get(position).getOrderID(), "1", "");
                            }

                        }

                        break;
                }
            }
        });
        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                list.clear();
                page = 1;
                mPresenter.WorkerGetOrderList(userId, "0", page + "", "5");
                mPresenter.GetListCategoryContentByCategoryID("7", "1", "999");
                mPresenter.messgIsOrNo(userId, "1", "1");
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
                mPresenter.WorkerGetOrderList(userId, "0", page + "", "5");
                refreshlayout.finishLoadmore();
            }
        });

    }

    @Override
    protected void initView() {
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.WorkerGetOrderList(userId, "0", page + "", "5");
        mPresenter.GetListCategoryContentByCategoryID("7", "1", "999");
        mPresenter.messgIsOrNo(userId, "1", "1");
    }

    @Override
    protected void setListener() {
        mLlMessage.setOnClickListener(this);
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
            case R.id.ll_message:
                startActivity(new Intent(mActivity, MessageActivity.class));
                break;
        }
    }

    @Override
    public void WorkerGetOrderList(BaseResult<WorkOrder> baseResult) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
        switch (baseResult.getStatusCode()) {
            case 200:
                if (page==1){
                    list.clear();
                }
                workOrder = baseResult.getData();
                if (workOrder.getData() != null) {
                    if (workOrder.getData().size() > 0) {
                        list.addAll(workOrder.getData());
                        adapter.setNewData(list);
                    } else {
                        return;
//                        adapter.setEmptyView(getHomeEmptyView());
                    }
                } else {
                    adapter.setEmptyView(getHomeEmptyView());
                }

                break;
        }
    }

    @Override
    public void GetListCategoryContentByCategoryID(BaseResult<Article> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                datalist = baseResult.getData().getData();
                mScrolltv.removeAllViews();
                mScrolltv.initView(R.layout.item_switchview, new SwitchView.ViewBuilder() {
                    @Override
                    public void initView(View view) {
                        final TextView tv_name = (TextView) view.findViewById(R.id.tv_content);
                        final TextView tv_url = (TextView) view.findViewById(R.id.tv_url);
                        tv_name.setText(datalist.get(i % datalist.size()).getTitle());
                        tv_url.setText(datalist.get(i % datalist.size()).getContent());
                        tv_name.setTag(i);

                        i++;
                        if (i == datalist.size()) {
                            i = 0;
                        }

                        tv_name.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String title = tv_name.getText().toString();
                                String content = tv_url.getText().toString();
                                Intent intent = new Intent(mActivity, WebActivity.class);
                                intent.putExtra("Url", content);
                                intent.putExtra("Title", title);
                                startActivity(intent);
                            }
                        });
                    }
                });
                break;
        }
    }

    @Override
    public void UpdateSendOrderState(BaseResult<Data> baseResult) {
        Data data = baseResult.getData();
        switch (baseResult.getStatusCode()) {
            case 200://200
                if (data.isItem1()) {//接单成功

//                    if (userInfo.getParentUserID() == null || "".equals(userInfo.getParentUserID())) {
                    adapter.remove(grabposition);
                    Toast.makeText(getActivity(), "接单成功", LENGTH_SHORT).show();
                    EventBus.getDefault().post(10);
                    EventBus.getDefault().post(1);
                    EventBus.getDefault().post("工单");
//                        Intent intent = new Intent(getActivity(), Order_Receiving_Activity.class);
//                        intent.putExtra("intent", "pending_appointment");
//                        startActivity(intent);

//                    } else {
//                        pending_appointment_adapter.remove(cancleposition);
//                    }


                } else {
                    Toast.makeText(getActivity(), (CharSequence) data.getItem2(), LENGTH_SHORT).show();
                }

                break;

            default:
                break;
        }
    }

    @Override
    public void messgIsOrNo(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){
                    mIvRed.setVisibility(View.VISIBLE);
                }else {
                    mIvRed.setVisibility(View.GONE);
                }
                break;
        }
    }

    //任意写一个方法，给这个方法一个@Subscribe注解，参数类型可以自定义，但是一定要与你发出的类型相同
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(Integer num) {
        switch (num) {
            case 10:
                list.clear();
                page = 1;
                mPresenter.WorkerGetOrderList(userId, "0", page + "", "10");
                break;
            case 0:
                list.clear();
                page = 1;
                mPresenter.WorkerGetOrderList(userId, "0", page + "", "10");
                break;
            case Config.ORDER_READ:

//                mPresenter.WorkerGetOrderRed(userid);

            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        if ("transaction_num".equals(name)) {
            mPresenter.messgIsOrNo(userId, "1", "1");
        }
    }
}
