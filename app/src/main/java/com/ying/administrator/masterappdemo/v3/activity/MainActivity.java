package com.ying.administrator.masterappdemo.v3.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.LinearLayout;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BlankFragment;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.Home_Fragment;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.Me_Fragment;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.NewsFragment;
import com.ying.administrator.masterappdemo.v3.fragment.HomeFragment;
import com.ying.administrator.masterappdemo.v3.fragment.MineFragment;
import com.ying.administrator.masterappdemo.v3.fragment.OrderFragment;
import com.ying.administrator.masterappdemo.v3.fragment.StudyFragment;
import com.ying.administrator.masterappdemo.widget.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.viewPager)
    CustomViewPager mViewPager;
    @BindView(R.id.ll_home)
    LinearLayout mLlHome;
    @BindView(R.id.ll_order)
    LinearLayout mLlOrder;
    @BindView(R.id.ll_study)
    LinearLayout mLlStudy;
    @BindView(R.id.ll_mine)
    LinearLayout mLlMine;
    @BindView(R.id.rootview)
    LinearLayout mRootview;
    private List<Fragment> fragmentList;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        fragmentList = new ArrayList<>();
        fragmentList.add(HomeFragment.newInstance(""));
        fragmentList.add(OrderFragment.newInstance(""));
        fragmentList.add(StudyFragment.newInstance(""));
        fragmentList.add(MineFragment.newInstance(""));
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        mViewPager.setOffscreenPageLimit(fragmentList.size());
        mViewPager.setScroll(false);
        mViewPager.setCurrentItem(0);
        tabSelected(mLlHome);
    }

    private class MyAdapter extends FragmentPagerAdapter {
        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    @Override
    protected void setListener() {
        mLlHome.setOnClickListener(this);
        mLlOrder.setOnClickListener(this);
        mLlStudy.setOnClickListener(this);
        mLlMine.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_home:
                mViewPager.setCurrentItem(0);
                tabSelected(mLlHome);
                break;
            case R.id.ll_order:
                mViewPager.setCurrentItem(1);
                tabSelected(mLlOrder);
                break;
            case R.id.ll_study:
                mViewPager.setCurrentItem(2);
                tabSelected(mLlStudy);
                break;
            case R.id.ll_mine:
                mViewPager.setCurrentItem(3);
                tabSelected(mLlMine);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void tabSelected(LinearLayout linearLayout) {
        mLlHome.setSelected(false);
        mLlOrder.setSelected(false);
        mLlStudy.setSelected(false);
        mLlMine.setSelected(false);
        linearLayout.setSelected(true);
    }
}
