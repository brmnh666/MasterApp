package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.common.DefineView;
import com.ying.administrator.masterappdemo.entity.SubAccount;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.SubAccountAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubAccountManagementActivity extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.ll_add_account)
    LinearLayout mLlAddAccount;
    @BindView(R.id.rv_sub_account)
    RecyclerView mRvSubAccount;
    private ArrayList<SubAccount> subAccountArrayList;

    @Override
    protected int setLayoutId() {
        return R.layout.activtity_sub_account_management;
    }

    @Override
    protected void initData() {
        subAccountArrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            subAccountArrayList.add(new SubAccount());
        }
        SubAccountAdapter subAccountAdapter = new SubAccountAdapter(R.layout.item_sub_account, subAccountArrayList);
        mRvSubAccount.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvSubAccount.setAdapter(subAccountAdapter);
    }

    @Override
    public void initView() {
        mTvActionbarTitle.setText("子账号管理");
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
