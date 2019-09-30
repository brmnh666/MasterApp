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
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.entity.Refund;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.ReturnGoodsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReturnActivity extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.rv_order)
    RecyclerView mRvOrder;
    private List<Refund.DataBean> list=new ArrayList<>();
    @Override
    protected int setLayoutId() {
        return R.layout.activity_return;
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 10; i++) {
            list.add(new Refund.DataBean());
        }
        ReturnGoodsAdapter adapter=new ReturnGoodsAdapter(R.layout.item_returngoods,list);
        mRvOrder.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvOrder.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.ll_shop:
                        Intent intent=new Intent(mActivity, AfterSaleDetailActivity.class);
                        intent.putExtra("Id",((Refund.DataBean)adapter.getItem(position)).getId());
                        intent.putExtra("OrderId",((Refund.DataBean)adapter.getItem(position)).getOrderId());
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    protected void initView() {
        mTvActionbarTitle.setText("退换/售后");
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
