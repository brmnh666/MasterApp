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
import com.lwkandroid.widget.stateframelayout.StateFrameLayout;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.entity.OrderDetail;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.OrderDetailAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderDetailActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.tv_ship)
    TextView mTvShip;
    @BindView(R.id.tv_time_of_receipt)
    TextView mTvTimeOfReceipt;
    @BindView(R.id.iv_logo)
    ImageView mIvLogo;
    @BindView(R.id.tv_logistics_name)
    TextView mTvLogisticsName;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_store_name)
    TextView mTvStoreName;
    @BindView(R.id.ll_store)
    LinearLayout mLlStore;
    @BindView(R.id.rv_order_list)
    RecyclerView mRvOrderList;
    @BindView(R.id.tv_total_price_of_goods)
    TextView mTvTotalPriceOfGoods;
    @BindView(R.id.tv_freight)
    TextView mTvFreight;
    @BindView(R.id.tv_freight_insurance)
    TextView mTvFreightInsurance;
    @BindView(R.id.tv_total_order_price)
    TextView mTvTotalOrderPrice;
    @BindView(R.id.tv_real_payment)
    TextView mTvRealPayment;
    @BindView(R.id.img_shifu1)
    ImageView mImgShifu1;
    @BindView(R.id.tv_yuyue)
    TextView mTvYuyue;
    @BindView(R.id.rl_serach_shifu)
    RelativeLayout mRlSerachShifu;
    @BindView(R.id.tv_order_number)
    TextView mTvOrderNumber;
    @BindView(R.id.tv_copy)
    TextView mTvCopy;
    @BindView(R.id.tv_creation_time)
    TextView mTvCreationTime;
    @BindView(R.id.tv_payment_time)
    TextView mTvPaymentTime;
    @BindView(R.id.tv_delivery_time)
    TextView mTvDeliveryTime;
    @BindView(R.id.tv_delete)
    TextView mTvDelete;
    @BindView(R.id.tv_buy_again)
    TextView mTvBuyAgain;
    @BindView(R.id.ll_close)
    LinearLayout mLlClose;
    @BindView(R.id.tv_delete2)
    TextView mTvDelete2;
    @BindView(R.id.tv_view_logistics)
    TextView mTvViewLogistics;
    @BindView(R.id.tv_evaluation)
    TextView mTvEvaluation;
    @BindView(R.id.ll_all_orders)
    LinearLayout mLlAllOrders;
    @BindView(R.id.tv_see_logistics)
    TextView mTvSeeLogistics;
    @BindView(R.id.tv_extended_receipt)
    TextView mTvExtendedReceipt;
    @BindView(R.id.tv_confirm_receipt)
    TextView mTvConfirmReceipt;
    @BindView(R.id.ll_pending_receipt)
    LinearLayout mLlPendingReceipt;
    @BindView(R.id.tv_delete_order)
    TextView mTvDeleteOrder;
    @BindView(R.id.tv_friend_pay)
    TextView mTvFriendPay;
    @BindView(R.id.tv_payment)
    TextView mTvPayment;
    @BindView(R.id.ll_pending_payment)
    LinearLayout mLlPendingPayment;
    @BindView(R.id.tv_logistics)
    TextView mTvLogistics;
    @BindView(R.id.tv_change_address)
    TextView mTvChangeAddress;
    @BindView(R.id.tv_buy)
    TextView mTvBuy;
    @BindView(R.id.ll_to_be_delivered)
    LinearLayout mLlToBeDelivered;
    private OrderDetailAdapter adapter;
    //    @BindView(R.id.stateLayout)
//    StateFrameLayout mStateLayout;
    private List<OrderDetail.OrderItemBean> orderItemBeans = new ArrayList<>();
    @Override
    protected int setLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvActionbarTitle.setText("订单详情");
        for (int i = 0; i < 1; i++) {
            orderItemBeans.add(new OrderDetail.OrderItemBean());
        }
        adapter = new OrderDetailAdapter(R.layout.item_order_lists, orderItemBeans, "");
        adapter.setEmptyView(getEmptyView());
        mRvOrderList.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvOrderList.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_apply_refund:
                        Intent intent = new Intent(mActivity, AfterSalesTypeActivity.class);
//                        intent.putExtra("storeName", orderBean.getShopName());
//                        intent.putExtra("product", orderItemBeans.get(position));
//                        intent.putExtra("order", orderBean);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });
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
