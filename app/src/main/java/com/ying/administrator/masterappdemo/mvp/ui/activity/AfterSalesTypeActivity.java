package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.entity.OrderDetail;
import com.ying.administrator.masterappdemo.widget.AdderView;
import com.ying.administrator.masterappdemo.widget.GlideUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AfterSalesTypeActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.tv_store_name)
    TextView mTvStoreName;
    @BindView(R.id.iv_goods_picture)
    ImageView mIvGoodsPicture;
    @BindView(R.id.tv_goods_name)
    TextView mTvGoodsName;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_number)
    TextView mTvNumber;
    @BindView(R.id.ll_product)
    LinearLayout mLlProduct;
    @BindView(R.id.adderview)
    AdderView mAdderview;
    @BindView(R.id.ll_exchange)
    LinearLayout mLlExchange;
    @BindView(R.id.ll_contact_customer_service)
    LinearLayout mLlContactCustomerService;
    @BindView(R.id.ll_dial_number)
    LinearLayout mLlDialNumber;
    @BindView(R.id.ll_return_money)
    LinearLayout mLlReturnMoney;
    private OrderDetail.OrderItemBean bean;
    private String storeName;
    private Intent intent;
    private OrderDetail.OrderBean order;
    private String OrderId;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_after_sales_type;
    }

    @Override
    protected void initData() {
//        bean = (OrderDetail.OrderItemBean) getIntent().getSerializableExtra("product");
//        order = (OrderDetail.OrderBean) getIntent().getSerializableExtra("order");
//        OrderId = order.getId();
//
//
//        storeName = getIntent().getStringExtra("storeName");
//        mTvGoodsName.setText(bean.getProductName());
//        GlideUtil.loadImageViewLodingRadius(mActivity, bean.getProductImage(), mIvGoodsPicture, R.drawable.avatar, R.drawable.avatar, 10);
//        mTvPrice.setText("价格：¥" + bean.getPrice());
//        mTvNumber.setText("数量：" + bean.getCount());
//        mTvStoreName.setText(storeName);
    }

    @Override
    protected void initView() {
        mTvActionbarTitle.setText("选择售后类型");
    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
        mLlReturnMoney.setOnClickListener(this);
        mLlExchange.setOnClickListener(this);
        mLlProduct.setOnClickListener(this);
        mAdderview.setOnValueChangeListene(new AdderView.OnValueChangeListener() {
            @Override
            public void onValueChange(int value) {
                if (value > Integer.parseInt(bean.getCount())) {
                    ToastUtils.showShort("申请数量不能大于商品数量!");
                    mAdderview.setValue(Integer.parseInt(bean.getCount()));
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_product:
                intent = new Intent(mActivity, GoodsDetailActivity.class);
                intent.putExtra("id", bean.getProductId() + "");
                startActivity(intent);
                break;
            case R.id.ll_return:
                finish();
                break;
            case R.id.ll_return_money://仅退款
                Intent intent = new Intent(mActivity, ReturnGoodsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", "仅退款");
//                bundle.putString("OrderId", OrderId);
//                bundle.putString("itemid", bean.getItemId());
//                bundle.putString("num", mAdderview.getValue() + "");
//                bundle.putDouble("price", bean.getPrice());
                bundle.putString("RefundType", "1");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.ll_exchange: //退款退货
                Intent intent2 = new Intent(mActivity, ReturnGoodsActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("title", "退款退货");
//                bundle2.putString("OrderId", OrderId);
//                bundle2.putString("itemid", bean.getItemId());
//                bundle2.putString("num", mAdderview.getValue() + "");
//                bundle2.putDouble("price", bean.getPrice());
                bundle2.putString("RefundType", "2");
                intent2.putExtras(bundle2);
                startActivity(intent2);
                break;
//            case R.id.ll_return:
//                startActivity(new Intent(mActivity, ServiceActivity.class));
//                break;
//            case R.id.ll_exchange:
//                startActivity(new Intent(mActivity, ServiceActivity.class));
//                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
