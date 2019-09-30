package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentSuccessActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
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
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.tv_check_order)
    TextView mTvCheckOrder;
    @BindView(R.id.tv_return_order)
    TextView mTvReturnOrder;
    private String orderID;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_paymentsuccess;
    }

    @Override
    protected void initData() {
        orderID = getIntent().getStringExtra("OrderID");
//        mPresenter.GetOrderDetail(orderID, userKey);

    }

    @Override
    protected void initView() {
        mTvActionbarTitle.setVisibility(View.VISIBLE);
        mTvActionbarTitle.setText("支付完成");

    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
        mTvCheckOrder.setOnClickListener(this);
        mTvReturnOrder.setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.icon_back:
                PaymentSuccessActivity.this.finish();
                break;

            case R.id.tv_check_order:
                Intent intent1 = new Intent(mActivity, OrderDetailActivity.class);
                intent1.putExtra("orderId", orderID);
                startActivity(intent1);
                finish();
                break;
            case R.id.tv_return_order:
                Bundle bundle = new Bundle();
                bundle.putString("intent", "待发货");
                bundle.putInt("position", 2);
                Intent intent = new Intent(mActivity, OrderActivity.class);
                intent.putExtras(bundle);
                ActivityUtils.startActivity(intent);
                finish();
                break;


        }
    }
//
//    @Override
//    public void GetOrderDetail(OrderDetail result) {
//        if (result.isSuccess()) {
//
//            mTvMoney.setText("¥" + result.getOrder().getRealTotalAmount());
//
//            Glide.with(mActivity).load(result.getOrderItem().get(0).getProductImage())
//                    .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mActivity, 5)))
//                    .into(mImgShop);
//
//            mTvShop.setText(result.getOrderItem().get(0).getProductName());
//
//            mTvcount.setText("数量: " + result.getOrderItem().get(0).getCount());
//        }
//    }
//
//    @Override
//    public void IsMallid(BaseResult<Data<String>> baseResult) {
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == Config.RECEIPT_REQUEST) {
//            if (resultCode == Config.RECEIPT_RESULT) {
//                mTvyuyue.setText("已预约");
//                mTvyuyue.setClickable(false);
//
//            }
//        }
//
//
//    }
}
