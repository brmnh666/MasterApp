package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsDetailActivity2 extends BaseActivity implements View.OnClickListener{
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
    @BindView(R.id.banner_goods)
    Banner mBannerGoods;
    @BindView(R.id.tv_good_money)
    TextView mTvGoodMoney;
    @BindView(R.id.tv_good_money_max)
    TextView mTvGoodMoneyMax;
    @BindView(R.id.tv_good_name)
    TextView mTvGoodName;
    @BindView(R.id.ll_share)
    LinearLayout mLlShare;
    @BindView(R.id.tv_express_delivery)
    TextView mTvExpressDelivery;
    @BindView(R.id.tv_sales_volume)
    TextView mTvSalesVolume;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_comment)
    TextView mTvComment;
    @BindView(R.id.tv_comment_two)
    TextView mTvCommentTwo;
    @BindView(R.id.ll_install_service)
    LinearLayout mLlInstallService;
    @BindView(R.id.tv_select)
    TextView mTvSelect;
    @BindView(R.id.ll_select)
    LinearLayout mLlSelect;
    @BindView(R.id.tv_service)
    TextView mTvService;
    @BindView(R.id.ll_service)
    LinearLayout mLlService;
    @BindView(R.id.tv_specification)
    TextView mTvSpecification;
    @BindView(R.id.ll_specification)
    LinearLayout mLlSpecification;
    @BindView(R.id.tv_parameter)
    TextView mTvParameter;
    @BindView(R.id.ll_parameter)
    LinearLayout mLlParameter;
    @BindView(R.id.one)
    LinearLayout mOne;
    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.three)
    LinearLayout mThree;
    @BindView(R.id.sv)
    ScrollView mSv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_cart)
    TextView mTvCart;
    @BindView(R.id.tv_buy)
    TextView mTvBuy;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_goods_detail2;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
        mTvBuy.setOnClickListener(this);
        mImgActionbarMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_return:
                finish();
                break;
            case R.id.tv_buy:
                startActivity(new Intent(mActivity,ConfirmOrderActivity.class));
                break;
            case R.id.img_actionbar_message:
                startActivity(new Intent(mActivity,CartActivity.class));
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
