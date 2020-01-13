package com.ying.administrator.masterappdemo.v3.fragment;

import android.os.Bundle;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.Home_Fragment;

public class HomeFragment extends BaseLazyFragment {
    private static final String ARG_SHOW_TEXT = "text";
    private String mContentText;
    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
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
        return R.layout.v3_fragment_home;
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
