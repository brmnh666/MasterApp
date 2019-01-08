package com.ying.administrator.masterappdemo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.common.DefineView;
import com.ying.administrator.masterappdemo.fragment.BaseFragment.BaseFragment;

public class Me_Fragment extends BaseFragment implements DefineView {
    private static final String ARG_SHOW_TEXT = "text";
    private String mContentText;
    private View view;
    public Me_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContentText = getArguments().getString(ARG_SHOW_TEXT);
        }


    }
    public static Me_Fragment newInstance(String param1) {
        Me_Fragment fragment = new Me_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view==null){
            view = inflater.inflate(R.layout.fragment_me, container, false);
            Log.d("ying","调用了onCreateView");
            initView();
        }

        return view;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initValidata() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {

    }
}
