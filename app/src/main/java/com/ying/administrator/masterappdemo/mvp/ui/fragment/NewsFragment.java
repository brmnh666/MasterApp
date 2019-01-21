package com.ying.administrator.masterappdemo.mvp.ui.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.MyPagerAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.TabLayoutViewPagerAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseFragment;

import java.util.ArrayList;

import static com.blankj.utilcode.util.CrashUtils.init;


public class NewsFragment extends BaseFragment {

    private TabLayout tab_new_layout;
    private ViewPager news_viewpager;
    private ArrayList<Fragment> fragmentList =new ArrayList<>();
    //tablout的内容
    private  ArrayList<String> title=new ArrayList<>();

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view==null){
            view = inflater.inflate(R.layout.fragment_news, container, false);
            init();

        }




        return view;
    }

    public void init(){

        tab_new_layout=view.findViewById(R.id.tab_new_layout);
        news_viewpager=view.findViewById(R.id.news_viewpager);

        title.add("消息");
        title.add("通知");
        informationFragment=new InformationFragment();
        noticeFragment=new NoticeFragment();

        fragmentList.add(informationFragment);
        fragmentList.add(noticeFragment);
        TabLayoutViewPagerAdapter tabLayoutViewPagerAdapter=new TabLayoutViewPagerAdapter(getFragmentManager(),fragmentList,title);
        tab_new_layout.setTabMode(TabLayout.MODE_FIXED);
        news_viewpager.setAdapter(tabLayoutViewPagerAdapter);
        tab_new_layout.setupWithViewPager(news_viewpager);
        news_viewpager.setCurrentItem(0);
    }





}
