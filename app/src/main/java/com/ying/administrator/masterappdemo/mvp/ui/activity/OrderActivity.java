package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.gyf.barlibrary.ImmersionBar;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.MyPagerAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.OrderFragment;
import com.ying.administrator.masterappdemo.widget.CustomViewPager;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tab_order_layout)
    TabLayout mTabOrderLayout;
    @BindView(R.id.vp_order)
    CustomViewPager mVpOrder;
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

    private String[] mTitleDataList = new String[]{
            "全部", "待付款", "待发货", "待收货", "待评价"
    };
    private String[] mTitleDataList_integral_use = new String[]{
            "全部", "收入", "支出"
    };
    private String[] mTitleDataList_integral_order = new String[]{
            "全部", "待发货", "待收货", "已完成"
    };
    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_order;
    }


    @Override
    protected void initData() {
        for (int i = 0; i < mTitleDataList.length; i++) {
            fragmentList.add(OrderFragment.newInstance(mTitleDataList[i], ""));
        }
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(),mTitleDataList, fragmentList );
        mTabOrderLayout.setTabMode(TabLayout.MODE_FIXED);
        mVpOrder.setAdapter(myPagerAdapter);
        mTabOrderLayout.setupWithViewPager(mVpOrder);
        mVpOrder.setCurrentItem(0);
        mVpOrder.setOffscreenPageLimit(0);

    }

    @Override
    protected void initView() {
        mTvActionbarTitle.setText("我的订单");
        String intent = getIntent().getStringExtra("intent");
        switch (intent) {
            case "全部":
                mVpOrder.setCurrentItem(0);
                break;
            case "待付款":
                mVpOrder.setCurrentItem(1);
                break;
            case "待发货":
                mVpOrder.setCurrentItem(2);
                break;
            case "待收货":
                mVpOrder.setCurrentItem(3);
                break;
            case "待评价":
                mVpOrder.setCurrentItem(4);
                break;

        }
    }

    @Override
    protected void setListener() {
        mImgActionbarReturn.setOnClickListener(this);
        mTvActionbarReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_actionbar_return:
            case R.id.img_actionbar_return:
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
