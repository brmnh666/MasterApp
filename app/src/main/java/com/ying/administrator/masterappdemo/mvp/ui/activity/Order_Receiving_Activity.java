package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.RedPointData;
import com.ying.administrator.masterappdemo.mvp.contract.ReceivingContract;
import com.ying.administrator.masterappdemo.mvp.model.ReceivingModel;
import com.ying.administrator.masterappdemo.mvp.presenter.MyMessagePresenter;
import com.ying.administrator.masterappdemo.mvp.presenter.ReceivingPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment.Appointment_failure_fragment;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment.Complete_wait_fetch_Fragement;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment.Completed_Fragement;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment.InService_Fragement;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment.Pending_appointment_fragment;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment.Quality_sheet_Fragement;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment.Returnedparts_Fragement;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment.Wait_Return_Fragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Order_Receiving_Activity extends BaseActivity<ReceivingPresenter, ReceivingModel> implements View.OnClickListener, ReceivingContract.View {
    @BindView(R.id.img_actionbar_return)
    ImageView mImgActionbarReturn;
    @BindView(R.id.tv_actionbar_return)
    TextView mTvActionbarReturn;
    @BindView(R.id.ll_return)
    LinearLayout mLlReturn;
    @BindView(R.id.tv_actionbar_title)
    TextView mTvActionbarTitle;
    @BindView(R.id.img_actionbar_message)
    ImageView mImgActionbarMessage;
    @BindView(R.id.actionbar_layout)
    RelativeLayout mActionbarLayout;
    @BindView(R.id.tab_receiving_layout)
    SlidingTabLayout mTabReceivingLayout;
    @BindView(R.id.receiving_viewpager)
    ViewPager mReceivingViewpager;
    private ArrayList<Fragment> mFragments =new ArrayList<>();
    SPUtils spUtils = SPUtils.getInstance("token");
    private String userid;
    private final String[] mTitles = {
            "已接待预约", "服务中", "配件单"
            , "待返件", "质保单", "完成待取机",
            "已完成","预约不成功"
    };

    private MyPagerAdapter mAdapter;


    private Pending_appointment_fragment pending_appointment_fragment; //已接订单待预约
    private InService_Fragement inService_fragement; //服务中
    private Quality_sheet_Fragement quality_sheet_fragement; //质保单
    private Wait_Return_Fragment wait_return_fragment;//待返件

    private Returnedparts_Fragement returnedparts_fragement;//配件单
    private Complete_wait_fetch_Fragement complete_wait_fetch_fragement;//完成待取机
    private Completed_Fragement completed_fragement; //已完成
    private Appointment_failure_fragment appointment_failure_fragment;//预约失败


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_order_receiving;
    }

    @Override
    protected void initData() {

        userid=spUtils.getString("userName");
        mPresenter.WorkerGetOrderRed(userid);


        pending_appointment_fragment=Pending_appointment_fragment.newInstance(); //已接待预约
        inService_fragement= InService_Fragement.newInstance(); //服务中
        returnedparts_fragement= Returnedparts_Fragement.newInstance();//配件单
        wait_return_fragment= Wait_Return_Fragment.newInstance();//待返件
        quality_sheet_fragement= Quality_sheet_Fragement.newInstance();//质保单
        complete_wait_fetch_fragement= Complete_wait_fetch_Fragement.newInstance();//完成待取机
        completed_fragement= Completed_Fragement.newInstance();//已完成
        appointment_failure_fragment= Appointment_failure_fragment.newInstance();//预约不成功


        mFragments.add(pending_appointment_fragment);
        mFragments.add(inService_fragement);
        mFragments.add(returnedparts_fragement);
        mFragments.add(wait_return_fragment);
        mFragments.add(quality_sheet_fragement);
        mFragments.add(complete_wait_fetch_fragement);
        mFragments.add(completed_fragement);
        mFragments.add(appointment_failure_fragment);

        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mReceivingViewpager.setAdapter(mAdapter);
        mReceivingViewpager.setOffscreenPageLimit(mFragments.size());
        mTabReceivingLayout.setViewPager(mReceivingViewpager);

      //  mTabReceivingLayout.showDot(2);

        String intent = getIntent().getStringExtra("intent");
        switch (intent){
            case "pending_appointment"://已接待预约
                mReceivingViewpager.setCurrentItem(0);
                break;

            case "in_service":  //服务中
                mReceivingViewpager.setCurrentItem(1);
                break;

            case "return"://配件单
                mReceivingViewpager.setCurrentItem(2);
                break;

            case "wait_return"://待返件

                mReceivingViewpager.setCurrentItem(3);
                break;

            case "quality"://质保单
                mReceivingViewpager.setCurrentItem(4);
                break;

            case "complete_wait_fetch"://完成待取机
                mReceivingViewpager.setCurrentItem(5);
                break;

            case "completed"://已完成
                mReceivingViewpager.setCurrentItem(6);
                break;
            case "appointment_failure"://预约失败
                mReceivingViewpager.setCurrentItem(7);
                break;

            default:
                mReceivingViewpager.setCurrentItem(0);
                break;
        }



    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);

    }




    //任意写一个方法，给这个方法一个@Subscribe注解，参数类型可以自定义，但是一定要与你发出的类型相同
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEventBus(Integer num) {
        switch (num) {
            case 2:
                mReceivingViewpager.setCurrentItem(1); //服务中 state 4
                break;
            case 3:
                mReceivingViewpager.setCurrentItem(3);//待返件 state 5
                break;
            case 4:
                mReceivingViewpager.setCurrentItem(6);//已完成 state 7
                break;
            case 5:
                mReceivingViewpager.setCurrentItem(2);//配件单 state 4
                break;

            case Config.ORDER_READ:

                mPresenter.WorkerGetOrderRed(userid);

            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_return:
                Order_Receiving_Activity.this.finish();
                break;
       default:
           break;

        }
    }

    @Override
    public void WorkerGetOrderRed(BaseResult<RedPointData> baseResult) {

        switch (baseResult.getStatusCode()){
            case 200:
                 if (baseResult.getData()!=null){
                     String data=baseResult.getData().getData();
                     for (int i =0;i<data.length();i++){
                         char c=baseResult.getData().getData().charAt(i);
                          if (c!='n'){
                             mTabReceivingLayout.showDot(i);
                          }else {
                              mTabReceivingLayout.hideMsg(i);
                          }
                     }
                 }
                 else {
                        return;
                 }
                break;
                default:
                    break;
        }
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
