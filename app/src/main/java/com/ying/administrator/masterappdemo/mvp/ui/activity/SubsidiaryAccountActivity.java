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
import com.ying.administrator.masterappdemo.entity.SubsidiaryAccount;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.SubsidiaryAccountAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubsidiaryAccountActivity extends BaseActivity implements View.OnClickListener{
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
    @BindView(R.id.img_add_sub_account)
    ImageView mImgAddSubAccount;
    @BindView(R.id.tv_add_sub_account)
    TextView mTvAddSubAccount;
    @BindView(R.id.ll_add_account)
    LinearLayout mLlAddAccount;
    @BindView(R.id.rv_sub_account)
    RecyclerView mRvSubAccount;
    @BindView(R.id.iv_wu)
    ImageView mIvWu;
    @BindView(R.id.smartrefresh)
    SmartRefreshLayout mSmartrefresh;
    private List<SubsidiaryAccount> subsidiaryAccountList=new ArrayList<>();
    private SubsidiaryAccountAdapter adapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_subsidiary_account;
    }

    @Override
    protected void initData() {
        for (int i = 0; i <10 ; i++) {
            subsidiaryAccountList.add(new SubsidiaryAccount());
        }
        adapter = new SubsidiaryAccountAdapter(R.layout.item_subsidiary_account,subsidiaryAccountList);
        mRvSubAccount.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvSubAccount.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.ll_subsidiary_account:
                        startActivity(new Intent(mActivity,OrderListActivity.class));
                        break;
                }
            }
        });
    }

    @Override
    protected void initView() {
        mTvActionbarTitle.setText("附属账号管理");
        mImgActionbarMessage.setVisibility(View.INVISIBLE);
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
        switch (v.getId()){
            case R.id.ll_return:
                finish();
                break;
        }
    }
}
