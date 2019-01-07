package com.ying.administrator.masterappdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class TabLayoutViewPagerAdapter extends FragmentPagerAdapter{
    ArrayList<Fragment> fragmentlist;
    ArrayList<String> title;

    public TabLayoutViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentlist, ArrayList<String> title) {
        super(fm);
        this.fragmentlist=fragmentlist;
        this.title=title;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentlist.get(position);
    }

    @Override
    public int getCount() {
        return fragmentlist.size();
    }

    //tablayout的title标题内容设置

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
