package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntelligentCustomerServiceActivity extends BaseActivity implements View.OnClickListener {


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
    @BindView(R.id.fl_logo)
    FrameLayout mFlLogo;
    @BindView(R.id.rv_chat)
    RecyclerView mRvChat;
    @BindView(R.id.iv_speak)
    ImageView mIvSpeak;
    @BindView(R.id.et_question)
    EditText mEtQuestion;
    @BindView(R.id.iv_smiley_face)
    ImageView mIvSmileyFace;
    @BindView(R.id.iv_choose)
    ImageView mIvChoose;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_intelligent_customer_service;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initView() {
        mTvActionbarTitle.setText("智能客服");
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
        switch (v.getId()) {
            case R.id.ll_return:
                finish();
                break;
        }
    }


}
