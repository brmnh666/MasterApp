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

import com.flyco.tablayout.SlidingTabLayout;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
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

public class Order_Receiving_Activity extends BaseActivity implements View.OnClickListener {
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

    private final String[] mTitles = {
            "已接待预约", "服务中", "配件单"
            , "待返件", "质保单", "完成代取机",
            "已完成","预约不成功"
    };

    private MyPagerAdapter mAdapter;
   /*  private TabLayout tab_Receiving_layout;
     private ViewPager receiving_viewpager;

     private LinearLayout ll_return;
     private ImageView img_actionbar_message;
     //tablout的内容
     private  ArrayList<String> title=new ArrayList<>();
 */

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
/*

        if (!EventBus.getDefault().isRegistered(this))
        {
            EventBus.getDefault().register(this);
        }
*/

        ButterKnife.bind(this);
    }

  /*  @Override
    public void initView() {
        tab_Receiving_layout=findViewById(R.id.tab_receiving_layout);
        receiving_viewpager=findViewById(R.id.receiving_viewpager);
        ll_return=findViewById(R.id.ll_return);
        img_actionbar_message=findViewById(R.id.img_actionbar_message);


    }*/

    /*@Override
    public void initValidata() {

    }
*/
  /*  @Override
    public void initListener() {
      ll_return.setOnClickListener(new CustomOnclickListner());
      img_actionbar_message.setOnClickListener(new CustomOnclickListner());
    }
*/
 /*   @Override
    public void bindData() {
         //为tablayout添加内容
         title.add("已接待预约");
         title.add("服务中");
         title.add("配件单");
         title.add("待返件");
         title.add("质保单");
         title.add("完成待取机");
         title.add("已完成");
         title.add("预约不成功");

        //grabsheet_fragement=new Grabsheet_Fragement();
        pending_appointment_fragment=Pending_appointment_fragment.newInstance(); //已接待预约
        inService_fragement= InService_Fragement.newInstance(); //服务中
        returnedparts_fragement= Returnedparts_Fragement.newInstance();//配件单
        wait_return_fragment= Wait_Return_Fragment.newInstance();//待返件
        quality_sheet_fragement= Quality_sheet_Fragement.newInstance();//质保单
        complete_wait_fetch_fragement= Complete_wait_fetch_Fragement.newInstance();//完成待取机
        completed_fragement= Completed_Fragement.newInstance();//已完成
        appointment_failure_fragment= Appointment_failure_fragment.newInstance();//预约不成功



        //fragmentList.add(grabsheet_fragement);
        fragmentList.add(pending_appointment_fragment);
        fragmentList.add(inService_fragement);
        fragmentList.add(returnedparts_fragement);
        fragmentList.add(wait_return_fragment);
        fragmentList.add(quality_sheet_fragement);
        fragmentList.add(complete_wait_fetch_fragement);
        fragmentList.add(completed_fragement);
        fragmentList.add(appointment_failure_fragment);


        TabLayoutViewPagerAdapter tabLayoutViewPagerAdapter=new TabLayoutViewPagerAdapter(getSupportFragmentManager(),fragmentList,title);
        tab_Receiving_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        receiving_viewpager.setAdapter(tabLayoutViewPagerAdapter);
        receiving_viewpager.setOffscreenPageLimit(fragmentList.size());
        receiving_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                EventBus.getDefault().post(Integer.toString(i+1));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        tab_Receiving_layout.setupWithViewPager(receiving_viewpager);






        receiving_viewpager.setCurrentItem(0);//默认第一个
//        EventBus.getDefault().post("1");
        *//*显示哪一个fragment*//*
        String intent = getIntent().getStringExtra("intent");
        switch (intent){


            case "pending_appointment"://已接待预约
                receiving_viewpager.setCurrentItem(0);
                break;

            case "in_service":  //服务中
               receiving_viewpager.setCurrentItem(1);
            break;

            case "return"://返件单
                receiving_viewpager.setCurrentItem(2);
                break;

            case "wait_return"://待返件

                receiving_viewpager.setCurrentItem(3);
                 break;

            case "quality"://质保单
                receiving_viewpager.setCurrentItem(4);
                break;

            case "complete_wait_fetch"://完成待取机
                receiving_viewpager.setCurrentItem(5);
                break;

            case "completed"://已完成
                receiving_viewpager.setCurrentItem(6);
                break;
            case "appointment_failure"://预约失败
                receiving_viewpager.setCurrentItem(7);
                break;



            default:
             break;
        }




    }*/

 /*   public class CustomOnclickListner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_return:
                Order_Receiving_Activity.this.finish();
                break;
            case R.id.img_actionbar_message:
                setResult(10202);
                Order_Receiving_Activity.this.finish();

                break;


        }
        }
    }
*/
  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

      *//*  if (resultCode==10001){    //到服务中去
            receiving_viewpager.setCurrentItem(1);
            Log.d("====>","10001");
        }else if (resultCode==10002){ //到返件中去
            receiving_viewpager.setCurrentItem(2);
        }*//*
    }*/

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

        mTabReceivingLayout.showDot(1);
        mTabReceivingLayout.showDot(2);
        mTabReceivingLayout.showDot(3);
        mTabReceivingLayout.showDot(4);
        mTabReceivingLayout.showDot(5);

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
