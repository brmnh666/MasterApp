package com.ying.administrator.masterappdemo.v3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.SPUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.NavigationBarNumber;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;
import com.ying.administrator.masterappdemo.v3.activity.SearchOrderActivity;
import com.ying.administrator.masterappdemo.v3.fragment.order.PendingAppointmentFragment;
import com.ying.administrator.masterappdemo.v3.fragment.order.PendingFragment;
import com.ying.administrator.masterappdemo.v3.fragment.order.ReturnedFragment;
import com.ying.administrator.masterappdemo.v3.fragment.order.ServiceFragment;
import com.ying.administrator.masterappdemo.v3.fragment.order.SettlementFragment;
import com.ying.administrator.masterappdemo.v3.fragment.order.ShippingFragment;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.OrderPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.OrderContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.OrderModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OrderFragment extends BaseLazyFragment<OrderPresenter, OrderModel> implements OrderContract.View {
    private static final String ARG_SHOW_TEXT = "text";
    @BindView(R.id.tab_receiving_layout)
    SlidingTabLayout mTabReceivingLayout;
    @BindView(R.id.receiving_viewpager)
    ViewPager mReceivingViewpager;
    Unbinder unbinder;
    @BindView(R.id.ll_search)
    LinearLayout mLlSearch;
    Unbinder unbinder1;
    private String mContentText;
    private ArrayList<Fragment> mFragments = new ArrayList<>();


    private MyPagerAdapter mAdapter;
    private PendingFragment pendingFragment;
    private PendingAppointmentFragment pendingAppointmentFragment;
    private ServiceFragment serviceFragment;
    private ShippingFragment shippingFragment;
    private ReturnedFragment returnedFragment;
    private SettlementFragment settlementFragment;
    private String userId;
    private String[] mTitles;
    private int one;
    private int two;
    private int three;
    private int four;
    private int five;
    private int six;

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

    }

    @Override
    protected void initView() {
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.NavigationBarNumber(userId, "1", "10");
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
        mLlSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, SearchOrderActivity.class));
            }
        });
    }

    @Override
    public void NavigationBarNumber(BaseResult<Data<NavigationBarNumber>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    one = baseResult.getData().getItem2().getCount1();
                    two = baseResult.getData().getItem2().getCount2();
                    three = baseResult.getData().getItem2().getCount3();
                    four = baseResult.getData().getItem2().getCount4();
                    five = baseResult.getData().getItem2().getCount5();
                    six = baseResult.getData().getItem2().getCount6();
                }
                mTitles = new String[]{
                        "待处理(" + one + ")", "待预约(" + two + ")", "待服务(" + three + ")", "待寄件(" + four + ")"
                        , "待返件(" + five + ")", "待结算(" + six + ")"
                };
                pendingFragment = PendingFragment.newInstance();
                pendingAppointmentFragment = PendingAppointmentFragment.newInstance();
                serviceFragment = ServiceFragment.newInstance();
                shippingFragment = ShippingFragment.newInstance();
                returnedFragment = ReturnedFragment.newInstance();
                settlementFragment = SettlementFragment.newInstance();
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
                break;
        }
    }

    @Override
    public void WorkerGetOrderList(BaseResult<WorkOrder> baseResult) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
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
