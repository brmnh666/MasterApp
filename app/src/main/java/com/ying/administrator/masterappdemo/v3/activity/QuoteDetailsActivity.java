package com.ying.administrator.masterappdemo.v3.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.v3.adapter.QuoteAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//报价详情
public class QuoteDetailsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.tv_state)
    TextView mTvState;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.tv_status)
    TextView mTvStatus;
    @BindView(R.id.tv_numbering)
    TextView mTvNumbering;
    @BindView(R.id.tv_payment)
    TextView mTvPayment;
    @BindView(R.id.tv_billing_time)
    TextView mTvBillingTime;
    @BindView(R.id.tv_copy)
    TextView mTvCopy;
    @BindView(R.id.tv_description)
    TextView mTvDescription;
    @BindView(R.id.rc_pivture)
    RecyclerView mRcPivture;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.et_quote_description)
    EditText mEtQuoteDescription;
    @BindView(R.id.ll_call)
    LinearLayout mLlCall;
    @BindView(R.id.tv_know)
    TextView mTvKnow;
    @BindView(R.id.tv_confirm_offer)
    TextView mTvConfirmOffer;
    private List<String> list=new ArrayList<>();
    private QuoteAdapter adapter;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_quote_details;
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 3; i++) {
            list.add("111");
        }
        adapter = new QuoteAdapter(R.layout.v3_item_quote,list);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRcPivture.setLayoutManager(linearLayoutManager);
        mRcPivture.setAdapter(adapter);
    }

    @Override
    protected void initView() {
        mTvTitle.setText("报价详情");
        mLlCustomerService.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
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
}
