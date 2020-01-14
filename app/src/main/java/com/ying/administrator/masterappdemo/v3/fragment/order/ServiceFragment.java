package com.ying.administrator.masterappdemo.v3.fragment.order;

import android.os.Bundle;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;

//待服务
public class ServiceFragment extends BaseLazyFragment {
    private static final String ARG_SHOW_TEXT = "text";
    private String mContentText;
    public ServiceFragment() {
        // Required empty public constructor
    }

    public static ServiceFragment newInstance() {
        ServiceFragment fragment = new ServiceFragment();
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
        return R.layout.v3_fragment_service;
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
