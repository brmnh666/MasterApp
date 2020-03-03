package com.ying.administrator.masterappdemo.v3.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Track;
import com.ying.administrator.masterappdemo.v3.adapter.TrackAdapter;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.TrackPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.TrackContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.TrackModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TicketTrackingActivity extends BaseActivity<TrackPresenter, TrackModel> implements View.OnClickListener, TrackContract.View {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.track_rv)
    RecyclerView mTrackRv;
    private String orderId;
    private List<Track> list=new ArrayList<>();
    private TrackAdapter adapter;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activityt_ticket_tracking;
    }

    @Override
    protected void initData() {
        adapter = new TrackAdapter(R.layout.v3_item_track, list);
        mTrackRv.setLayoutManager(new LinearLayoutManager(mActivity));
        mTrackRv.setAdapter(adapter);
    }

    @Override
    protected void initView() {
        mTvTitle.setText("工单跟踪");
        orderId = getIntent().getStringExtra("id");
        mPresenter.GetOrderRecordByOrderID(orderId);
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

    @Override
    public void GetOrderRecordByOrderID(BaseResult<List<Track>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                list=baseResult.getData();
                adapter.setNewData(list);
                break;
            case 401:
                break;
        }
    }
}
