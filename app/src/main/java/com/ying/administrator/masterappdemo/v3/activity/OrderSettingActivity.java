package com.ying.administrator.masterappdemo.v3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.AddServiceAreaInfoActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.MyInfoSkillActivity2;
import com.ying.administrator.masterappdemo.v3.adapter.OrderSettingAdapter;
import com.ying.administrator.masterappdemo.v3.bean.OrderSettingBean;
import com.ying.administrator.masterappdemo.v3.weight.AutoLineFeedLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderSettingActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.switcher)
    Switch mSwitcher;
    @BindView(R.id.rv_service)
    RecyclerView mRvService;
    @BindView(R.id.tv_service_product)
    TextView mTvServiceProduct;
    @BindView(R.id.tv_service_area)
    TextView mTvServiceArea;
    @BindView(R.id.tv_person)
    TextView mTvPerson;
    @BindView(R.id.tv_truck)
    TextView mTvTruck;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.ll_service_area)
    LinearLayout mLlServiceArea;
    @BindView(R.id.ll_service_product)
    LinearLayout mLlServiceProduct;
    private List<OrderSettingBean> list = new ArrayList<>();
    private OrderSettingAdapter adapter;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_order_setting;
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 2; i++) {
            list.add(new OrderSettingBean());
        }
        adapter = new OrderSettingAdapter(R.layout.v3_item_service_area, list);
        mRvService.setLayoutManager(new AutoLineFeedLayoutManager());
        mRvService.setAdapter(adapter);
    }

    @Override
    protected void initView() {
        mTvTitle.setText("接单设置");
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mLlServiceArea.setOnClickListener(this);
        mLlServiceProduct.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_service_area:
                startActivity(new Intent(mActivity, AddServiceAreaInfoActivity.class));
                break;
            case R.id.ll_service_product:
//                if (userInfo.getIfAuth() == null) {//未实名认证
//                    ToastUtils.showShort("您暂未实名");
//                    return;
//                } else {
                    startActivity(new Intent(this, MyInfoSkillActivity2.class));
//                }
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
