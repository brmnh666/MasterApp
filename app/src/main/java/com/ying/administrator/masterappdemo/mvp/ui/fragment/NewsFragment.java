package com.ying.administrator.masterappdemo.mvp.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.TabLayoutViewPagerAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class NewsFragment extends BaseLazyFragment {

    private static final String TAG = "NewsFragment";
    @BindView(R.id.tab_new_layout)
    TabLayout mTabNewLayout;
    @BindView(R.id.iv_close)
    ImageView mIvClose;
    @BindView(R.id.tv_open)
    TextView mTvOpen;
    @BindView(R.id.news_viewpager)
    ViewPager mNewsViewpager;
    Unbinder unbinder;
    @BindView(R.id.ll_system_notification)
    LinearLayout mLlSystemNotification;
    Unbinder unbinder1;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    //tablout的内容
    private ArrayList<String> title = new ArrayList<>();

    private InformationFragment informationFragment; //消息fragment
    private NoticeFragment noticeFragment; //通知fragment

    private static final String ARG_SHOW_TEXT = "text";
    private View view;
    private String mContentText;

    public NewsFragment() {
        // Required empty public constructor
    }


    public static NewsFragment newInstance(String param1) {
        NewsFragment fragment = new NewsFragment();
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
        return R.layout.fragment_news;
    }

    @Override
    protected void initData() {
        mTvOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSelfSetting(mActivity);
            }
        });
        mIvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLlSystemNotification.setVisibility(View.GONE);
                Log.d(TAG,"............");
            }
        });
    }

    @Override
    protected void initView() {

        title.add("消息");
        title.add("通知");
        informationFragment = new InformationFragment();
        noticeFragment = new NoticeFragment();

        fragmentList.add(informationFragment);
        fragmentList.add(noticeFragment);
        TabLayoutViewPagerAdapter tabLayoutViewPagerAdapter = new TabLayoutViewPagerAdapter(getFragmentManager(), fragmentList, title);
        mTabNewLayout.setTabMode(TabLayout.MODE_FIXED);
        mNewsViewpager.setAdapter(tabLayoutViewPagerAdapter);
        mTabNewLayout.setupWithViewPager(mNewsViewpager);
        mNewsViewpager.setCurrentItem(0);

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public static void toSelfSetting(Context context) {
        Intent mIntent = new Intent();
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            mIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            mIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            mIntent.setAction(Intent.ACTION_VIEW);
            mIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
            mIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        context.startActivity(mIntent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String message) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
