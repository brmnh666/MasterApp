package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddServiceAreaActivity extends BaseActivity implements View.OnClickListener {


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
    @BindView(R.id.ll_province)
    LinearLayout mLlProvince;
    @BindView(R.id.ll_city)
    LinearLayout mLlCity;
    @BindView(R.id.ll_area)
    LinearLayout mLlArea;
    @BindView(R.id.ll_town)
    LinearLayout mLlTown;
    @BindView(R.id.iv_add)
    ImageView mIvAdd;
    @BindView(R.id.rv_region)
    RecyclerView mRvRegion;
    @BindView(R.id.btn_save)
    Button mBtnSave;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_service_area;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initView() {
        mTvActionbarTitle.setText("添加服务区域");
    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
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
            case R.id.ll_return:
                finish();
                break;
        }

    }
}
