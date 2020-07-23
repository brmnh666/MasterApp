package com.ying.administrator.masterappdemo.v3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.ProductList;
import com.ying.administrator.masterappdemo.mvp.ui.activity.GoodsDetailActivity;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.MallAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShopFragment extends BaseLazyFragment {
    private static final String ARG_SHOW_TEXT = "text";
    @BindView(R.id.iv_cart)
    ImageView mIvCart;
    @BindView(R.id.main_collapsing)
    CollapsingToolbarLayout mMainCollapsing;
    @BindView(R.id.ll_online_consultation)
    LinearLayout mLlOnlineConsultation;
    @BindView(R.id.ll_contact_customer_Service)
    LinearLayout mLlContactCustomerService;
    @BindView(R.id.tv_comprehensive)
    TextView mTvComprehensive;
    @BindView(R.id.tv_sales_volume)
    TextView mTvSalesVolume;
    @BindView(R.id.ll_price)
    LinearLayout mLlPrice;
    @BindView(R.id.tv_new_product)
    TextView mTvNewProduct;
    @BindView(R.id.appbarlayout)
    AppBarLayout mAppbarlayout;
    @BindView(R.id.rv_mall)
    RecyclerView mRvMall;
    @BindView(R.id.nested)
    NestedScrollView mNested;
    @BindView(R.id.cdl)
    CoordinatorLayout mCdl;
    @BindView(R.id.img_up)
    ImageView mImgUp;
    @BindView(R.id.cv_up)
    CardView mCvUp;
    Unbinder unbinder;
    private List<ProductList> lists = new ArrayList<>();
    public static ShopFragment newInstance(String param1) {
        ShopFragment fragment = new ShopFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    private String mContentText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContentText = getArguments().getString(ARG_SHOW_TEXT);
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.v3_fragment_shop;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        for (int i = 0; i < 10; i++) {
            lists.add(new ProductList());
        }
        MallAdapter mallAdapter = new MallAdapter(R.layout.item_mall, lists);
        mRvMall.setLayoutManager(new GridLayoutManager(mActivity, 2));
        mRvMall.setAdapter(mallAdapter);
        mallAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_goods:
                        startActivity(new Intent(mActivity, GoodsDetailActivity.class));
                        break;
                }
            }
        });
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
}
