package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by hackware on 2016/9/10.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    // 定义Tab标题
    private String[] tabTitles;
    private List<Fragment> mViewPagerFragmentList;

    public MyPagerAdapter(FragmentManager fm, String[] tabTitles, List<Fragment> mViewPagerFragmentList) {
        super(fm);
        this.tabTitles = tabTitles;
        this.mViewPagerFragmentList = mViewPagerFragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mViewPagerFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
