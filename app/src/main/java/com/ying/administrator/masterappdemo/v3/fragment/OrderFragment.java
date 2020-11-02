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
import com.ying.administrator.masterappdemo.entity.NavigationBarNumberSon;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;
import com.ying.administrator.masterappdemo.v3.activity.SearchOrderActivity;
import com.ying.administrator.masterappdemo.v3.bean.OrderListResult;
import com.ying.administrator.masterappdemo.v3.fragment.order.OrderStateFragment;
import com.ying.administrator.masterappdemo.v3.fragment.order.PendingAppointmentFragment;
import com.ying.administrator.masterappdemo.v3.fragment.order.PendingFragment;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.OrderPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.OrderContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.OrderModel;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private String[] mTitles;
    private int one;
    private int two;
    private int three;
    private int four;
    private int five;
    private int six;

    private int senven;
    private String userId;

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
        mTitles = new String[]{
                "待处理(0)", "待预约(0)", "待服务(0)", "待审核(0)"
                , "待返件(0)", "待结算(0)","已完结(0)"
        };
        pendingFragment = PendingFragment.newInstance();
        pendingAppointmentFragment = PendingAppointmentFragment.newInstance();
        mFragments.add(pendingFragment);//待处理
        mFragments.add(pendingAppointmentFragment);//待预约
        mFragments.add(OrderStateFragment.newInstance("2"));//服务中
        mFragments.add(OrderStateFragment.newInstance("11"));//待审核
        mFragments.add(OrderStateFragment.newInstance("8"));//待返件
        mFragments.add(OrderStateFragment.newInstance("12"));//待结算
        mFragments.add(OrderStateFragment.newInstance("6"));//已完结
        mAdapter = new MyPagerAdapter(getChildFragmentManager());
        mReceivingViewpager.setAdapter(mAdapter);
        mReceivingViewpager.setOffscreenPageLimit(mFragments.size());
        mTabReceivingLayout.setViewPager(mReceivingViewpager);
        mPresenter.NavigationBarNumber(userId, "1", "10");
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
        try {
            switch (baseResult.getStatusCode()) {
                case 200:
                    if (baseResult.getData().isItem1()) {
                        one = baseResult.getData().getItem2().getCount1();
                        two = baseResult.getData().getItem2().getCount2();
                        three = baseResult.getData().getItem2().getCount3();
                        four = baseResult.getData().getItem2().getCount4();
                        five = baseResult.getData().getItem2().getCount5();
                        six = baseResult.getData().getItem2().getCount6();
                        senven = baseResult.getData().getItem2().getCount7();
                    }
                    mTitles = new String[]{
                            "待处理(" + one + ")", "待预约(" + two + ")", "待服务(" + three + ")", "待审核(" + four + ")"
                            , "待返件(" + five + ")", "待结算(" + six + ")", "已完结(" + senven + ")"
                    };
                    mTabReceivingLayout.notifyDataSetChanged();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void NavigationBarNumberSon(BaseResult<Data<NavigationBarNumberSon>> baseResult) {

    }

    @Override
    public void WorkerGetOrderList(BaseResult<WorkOrder> baseResult) {

    }

    @Override
    public void GetOrderList(OrderListResult baseResult) {

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

    //任意写一个方法，给这个方法一个@Subscribe注解，参数类型可以自定义，但是一定要与你发出的类型相同
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(Integer num) {
        switch (num) {
            case 1:
                mReceivingViewpager.setCurrentItem(1); //待预约 state 1
                mPresenter.NavigationBarNumber(userId, "1", "10");
                break;
            case 2:
                mReceivingViewpager.setCurrentItem(2); //待服务 state 2
                mPresenter.NavigationBarNumber(userId, "1", "10");
                break;
            case 3:
                mReceivingViewpager.setCurrentItem(3);//待审核 state 5
                mPresenter.NavigationBarNumber(userId, "1", "10");
                break;
            case 4:
                mReceivingViewpager.setCurrentItem(4);//待返件 state 5
                mPresenter.NavigationBarNumber(userId, "1", "10");
                break;
            case 5:
                mReceivingViewpager.setCurrentItem(5);//待结算 state 3
                mPresenter.NavigationBarNumber(userId, "1", "10");
                break;
            case 6:
                mReceivingViewpager.setCurrentItem(6);//已完结 state 3
                mPresenter.NavigationBarNumber(userId, "1", "10");
                break;
            case 20:
                mPresenter.NavigationBarNumber(userId, "1", "10");
                break;
            default:
                break;
        }
    }

    @Override
    protected void onVisible() {
        super.onVisible();

        mReceivingViewpager.setCurrentItem(0);

    }
}
