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

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.ComplaintList;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.mvp.contract.ComplaintContract;
import com.ying.administrator.masterappdemo.mvp.model.ComplaintModel;
import com.ying.administrator.masterappdemo.mvp.presenter.ComplaintPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.ComplaintDetailAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComplaintDetailActivity extends BaseActivity<ComplaintPresenter, ComplaintModel> implements View.OnClickListener, ComplaintContract.View {

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
    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.actionbar_layout)
    RelativeLayout mActionbarLayout;
    @BindView(R.id.rv_complaint_detail)
    RecyclerView mRvComplaintDetail;
    private String orderId;
    private List<ComplaintList> lists = new ArrayList<>();
    private ComplaintDetailAdapter adapter;
    private String UserId;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_complaint_detail;
    }

    @Override
    protected void initData() {
        adapter = new ComplaintDetailAdapter(R.layout.item_complaint_detail, lists);
        mRvComplaintDetail.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvComplaintDetail.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_image:
                        Intent intent = new Intent(mActivity, PhotoViewActivity.class);
                        intent.putExtra("PhotoUrl", Config.ComPlaint_URL + lists.get(position).getPhoto());
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    protected void initView() {
        mTvActionbarTitle.setText("投诉详情");
        SPUtils spUtils = SPUtils.getInstance("token");
        UserId = spUtils.getString("userName");
        orderId = getIntent().getStringExtra("orderId");
        mPresenter.GetComplaintListByOrderId(orderId, UserId);
    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_return:
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
    public void WorkerComplaint(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void ComPlaintImg(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetComplaintListByOrderId(BaseResult<List<ComplaintList>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData() != null) {
                    lists.addAll(baseResult.getData());
                    adapter.setNewData(lists);
                }
                break;
        }
    }
}
