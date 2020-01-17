package com.ying.administrator.masterappdemo.v3.fragment.order;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;
import com.ying.administrator.masterappdemo.v3.adapter.PendingAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//待处理
public class PendingFragment extends BaseLazyFragment implements View.OnClickListener {
    private static final String ARG_SHOW_TEXT = "text";
    @BindView(R.id.tv_urgently_needed)
    TextView mTvUrgentlyNeeded;
    @BindView(R.id.tv_come_tomorrow)
    TextView mTvComeTomorrow;
    @BindView(R.id.tv_timed_out)
    TextView mTvTimedOut;

    Unbinder unbinder;
    @BindView(R.id.rv_pending)
    RecyclerView mRvPending;


    private String mContentText;
    private List<WorkOrder.DataBean> list = new ArrayList<>();
    private PendingAdapter pendingAdapter;

    public PendingFragment() {
        // Required empty public constructor
    }

    public static PendingFragment newInstance() {
        PendingFragment fragment = new PendingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContentText = getArguments().getString(ARG_SHOW_TEXT);
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.v3_fragment_pending;
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 10; i++) {
            list.add(new WorkOrder.DataBean());
        }
        pendingAdapter = new PendingAdapter(R.layout.v3_item_home,list);
        mRvPending.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvPending.setAdapter(pendingAdapter);
    }

    @Override
    protected void initView() {
        mTvUrgentlyNeeded.setSelected(true);
        mTvComeTomorrow.setSelected(false);
        mTvTimedOut.setSelected(false);
    }

    @Override
    protected void setListener() {
        mTvUrgentlyNeeded.setOnClickListener(this);
        mTvComeTomorrow.setOnClickListener(this);
        mTvTimedOut.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_urgently_needed:
                mTvUrgentlyNeeded.setSelected(true);
                mTvComeTomorrow.setSelected(false);
                mTvTimedOut.setSelected(false);
                break;
            case R.id.tv_come_tomorrow:
                mTvUrgentlyNeeded.setSelected(false);
                mTvComeTomorrow.setSelected(true);
                mTvTimedOut.setSelected(false);
                break;
            case R.id.tv_timed_out:
                mTvUrgentlyNeeded.setSelected(false);
                mTvComeTomorrow.setSelected(false);
                mTvTimedOut.setSelected(true);
                break;
        }
    }
}
