package com.ying.administrator.masterappdemo.v3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Article;
import com.ying.administrator.masterappdemo.mvp.ui.activity.WebActivity;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;
import com.ying.administrator.masterappdemo.widget.SwitchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends BaseLazyFragment {
    private static final String ARG_SHOW_TEXT = "text";
    @BindView(R.id.ll_message)
    LinearLayout mLlMessage;
    @BindView(R.id.scrolltv)
    SwitchView mScrolltv;
    @BindView(R.id.ll_switch)
    LinearLayout mLlSwitch;
    @BindView(R.id.rv_new_order)
    RecyclerView mRvNewOrder;
    Unbinder unbinder;
    private String mContentText;
    private int i = 0;
    private List<String> datalist = new ArrayList<>();
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
        datalist.add("必读！职业道德操守准则");
        mScrolltv.removeAllViews();
        mScrolltv.initView(R.layout.v3_item_switchview, new SwitchView.ViewBuilder() {
            @Override
            public void initView(View view) {
                final TextView tv_name = (TextView) view.findViewById(R.id.tv_content);
//                final TextView tv_url = (TextView) view.findViewById(R.id.tv_url);
                tv_name.setText(datalist.get(i % datalist.size()));
//                tv_url.setText(datalist.get(i % datalist.size()));
                tv_name.setTag(i);

                i++;
                if (i == datalist.size()) {
                    i = 0;
                }

//                tv_name.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String title = tv_name.getText().toString();
//                        String content = tv_url.getText().toString();
//                        Intent intent = new Intent(mActivity, WebActivity.class);
//                        intent.putExtra("Url", content);
//                        intent.putExtra("Title", title);
//                        startActivity(intent);
//                    }
//                });
            }
        });
    }

    @Override
    protected void setListener() {

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
}
