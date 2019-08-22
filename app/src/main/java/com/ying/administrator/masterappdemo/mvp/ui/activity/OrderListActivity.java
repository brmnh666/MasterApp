package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.entity.OrderList;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.OrderListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderListActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.img_actionbar_return)
    ImageView mImgActionbarReturn;
    @BindView(R.id.tv_actionbar_return)
    TextView mTvActionbarReturn;
    @BindView(R.id.ll_return)
    LinearLayout mLlReturn;
    @BindView(R.id.tv_actionbar_title)
    TextView mTvActionbarTitle;
    @BindView(R.id.img_actionbar_message)
    ImageView mImgActionbarMessage;
    @BindView(R.id.actionbar_layout)
    RelativeLayout mActionbarLayout;
    @BindView(R.id.tv_single_quantity)
    TextView mTvSingleQuantity;
    @BindView(R.id.rv_order_number)
    RecyclerView mRvOrderNumber;
    @BindView(R.id.smartrefresh)
    SmartRefreshLayout mSmartrefresh;
    private List<OrderList> lists=new ArrayList<>();
    @Override
    protected int setLayoutId() {
        return R.layout.activity_order_list;
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 10; i++) {
            lists.add(new OrderList());
        }
        OrderListAdapter adapter=new OrderListAdapter(R.layout.item_order_list,lists);
        mRvOrderNumber.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvOrderNumber.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_detail:
                        Intent intent=new Intent(mActivity, WorkOrderDetailsActivity2.class);
                        intent.putExtra("OrderID","2000001880");
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    protected void initView() {
        mTvActionbarTitle.setVisibility(View.VISIBLE);
        mTvActionbarTitle.setText("订单列表");
    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_return:
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
}
