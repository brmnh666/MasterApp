package com.ying.administrator.masterappdemo.v3.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Order_Receiving_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.MyPagerAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;
import com.ying.administrator.masterappdemo.v3.fragment.order.PendingAppointmentFragment;
import com.ying.administrator.masterappdemo.v3.fragment.order.PendingFragment;
import com.ying.administrator.masterappdemo.v3.fragment.order.ReturnedFragment;
import com.ying.administrator.masterappdemo.v3.fragment.order.ServiceFragment;
import com.ying.administrator.masterappdemo.v3.fragment.order.SettlementFragment;
import com.ying.administrator.masterappdemo.v3.fragment.order.ShippingFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OrderFragment extends BaseLazyFragment {
    private static final String ARG_SHOW_TEXT = "text";
    @BindView(R.id.tab_receiving_layout)
    SlidingTabLayout mTabReceivingLayout;
    @BindView(R.id.receiving_viewpager)
    ViewPager mReceivingViewpager;
    Unbinder unbinder;
    private String mContentText;
    private ArrayList<Fragment> mFragments =new ArrayList<>();
    private final String[] mTitles = {
            "(0)待处理","(0)待预约", "(0)待服务", "(0)待寄件"
            , "(0)待返件", "(0)待结算"
    };

    private MyPagerAdapter mAdapter;
    private PendingFragment pendingFragment;
    private PendingAppointmentFragment pendingAppointmentFragment;
    private ServiceFragment serviceFragment;
    private ShippingFragment shippingFragment;
    private ReturnedFragment returnedFragment;
    private SettlementFragment settlementFragment;

    public static OrderFragment newInstance(String param1) {
        OrderFragment fragment = new OrderFragment();
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
        return R.layout.v3_fragment_order;
    }

    @Override
    protected void initData() {
        pendingFragment=PendingFragment.newInstance();
        pendingAppointmentFragment=PendingAppointmentFragment.newInstance();
        serviceFragment=ServiceFragment.newInstance();
        shippingFragment=ShippingFragment.newInstance();
        returnedFragment=ReturnedFragment.newInstance();
        settlementFragment=SettlementFragment.newInstance();
        mFragments.add(pendingFragment);
        mFragments.add(pendingAppointmentFragment);
        mFragments.add(serviceFragment);
        mFragments.add(shippingFragment);
        mFragments.add(returnedFragment);
        mFragments.add(settlementFragment);
//        mFragments.add(PendingFragment.newInstance(""));
//        mFragments.add(PendingAppointmentFragment.newInstance(""));
//        mFragments.add(ServiceFragment.newInstance(""));
//        mFragments.add(ShippingFragment.newInstance(""));
//        mFragments.add(ReturnedFragment.newInstance(""));
//        mFragments.add(SettlementFragment.newInstance(""));
//        mAdapter = new MyPagerAdapter(getFragmentManager());
//        mReceivingViewpager.setAdapter(mAdapter);
//        mReceivingViewpager.setOffscreenPageLimit(mFragments.size());
//        mTabReceivingLayout.setViewPager(mReceivingViewpager);
        mAdapter = new MyPagerAdapter(getFragmentManager());
        mReceivingViewpager.setAdapter(mAdapter);
        mReceivingViewpager.setOffscreenPageLimit(mFragments.size());
        mTabReceivingLayout.setViewPager(mReceivingViewpager);

    }

    @Override
    protected void initView() {
//        pendingFragment = new PendingFragment();
//        pendingAppointmentFragment = new PendingAppointmentFragment();
//        serviceFragment = new ServiceFragment();
//        shippingFragment = new ShippingFragment();
//        returnedFragment = new ReturnedFragment();
//        settlementFragment = new SettlementFragment();
//

    }

    @Override
    protected void setListener() {

    }



    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
