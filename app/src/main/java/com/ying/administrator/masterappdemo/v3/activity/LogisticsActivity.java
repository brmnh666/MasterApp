package com.ying.administrator.masterappdemo.v3.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.v3.adapter.LogisticsAdapter;
import com.ying.administrator.masterappdemo.v3.bean.GetExpressInfoResult;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.LogisticsPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.LogisticsContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.LogisticsModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogisticsActivity extends BaseActivity<LogisticsPresenter, LogisticsModel> implements View.OnClickListener, LogisticsContract.View {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.courier_company_tv)
    TextView mCourierCompanyTv;
    @BindView(R.id.tv_number)
    TextView mTvNumber;
    @BindView(R.id.iv_copy)
    ImageView mIvCopy;
    @BindView(R.id.return_rv)
    RecyclerView mReturnRv;
    @BindView(R.id.sv_logistics)
    ScrollView mSvLogistics;
    private String number;
    private List<GetExpressInfoResult.DataBeanX.DataBean.ListBean> list = new ArrayList<>();
    private LogisticsAdapter adapter;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_logistics;
    }

    @Override
    protected void initData() {
        adapter = new LogisticsAdapter(R.layout.v3_logistics_recycle_item, list);
        mReturnRv.setLayoutManager(new LinearLayoutManager(mActivity));
        mReturnRv.setAdapter(adapter);
        myClipboard = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @Override
    protected void initView() {
        mTvTitle.setText("物流详情");
        number = getIntent().getStringExtra("number");
        mTvNumber.setText(number);
        mPresenter.GetExpressInfo(number);
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mIvCopy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_copy:
                String id = mTvNumber.getText().toString();
                myClip = ClipData.newPlainText("", id);
                myClipboard.setPrimaryClip(myClip);
                ToastUtils.showShort("复制成功");
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
    public void GetExpressInfo(GetExpressInfoResult baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getData().getList() != null) {
                    list.addAll(baseResult.getData().getData().getList());
                    adapter.setNewData(list);
                }

                break;
        }
    }
}
