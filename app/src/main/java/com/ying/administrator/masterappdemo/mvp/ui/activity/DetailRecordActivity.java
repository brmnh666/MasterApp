package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.RecordFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailRecordActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.st_tablayout)
    SegmentTabLayout mSt_tablayout;
    @BindView(R.id.vp_record)
    ViewPager mVp_record;
    @BindView(R.id.img_actionbar_return)
    ImageView mImgActionbarReturn;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"收支", "提现", "充值"};

    @Override
    protected int setLayoutId() {
        return R.layout.activity_detailrecord;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        mFragments.add(RecordFragment.newInstance("收支"));
        mFragments.add(RecordFragment.newInstance("提现"));
        mFragments.add(RecordFragment.newInstance("充值"));


        mVp_record.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mSt_tablayout.setTabData(mTitles);
        mSt_tablayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mVp_record.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        mVp_record.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mSt_tablayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        String openwhich = getIntent().getStringExtra("openwhich");
        switch (openwhich){
                case "1":
                mVp_record.setCurrentItem(0);
                break;
                case "2":
                mVp_record.setCurrentItem(1);
                break;
                case "3":
                mVp_record.setCurrentItem(2);
                break;

        }

    }

    @Override
    protected void setListener() {
        mImgActionbarReturn.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.img_actionbar_return:
              DetailRecordActivity.this.finish();
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
