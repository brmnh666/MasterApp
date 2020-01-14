package com.ying.administrator.masterappdemo.v3.fragment.order;

import android.os.Bundle;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment.Wait_Return_Fragment;

//待处理
public class PendingFragment extends BaseLazyFragment {
    private static final String ARG_SHOW_TEXT = "text";
    private String mContentText;
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

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }
}
