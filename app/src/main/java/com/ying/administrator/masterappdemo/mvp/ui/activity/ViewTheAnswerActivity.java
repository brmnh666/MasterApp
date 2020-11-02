package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.app.AlertDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.QuestBean;
import com.ying.administrator.masterappdemo.entity.QuestResult;
import com.ying.administrator.masterappdemo.mvp.contract.QuestContract;
import com.ying.administrator.masterappdemo.mvp.model.QuestModel;
import com.ying.administrator.masterappdemo.mvp.presenter.QuestPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.ViewTheAnswerFragment;
import com.ying.administrator.masterappdemo.widget.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ViewTheAnswerActivity extends BaseActivity<QuestPresenter, QuestModel> implements View.OnClickListener, QuestContract.View {

    @BindView(R.id.viewPager)
    CustomViewPager vp_answer;
    @BindView(R.id.btn_pre)
    Button btn_previous;
    @BindView(R.id.btn_next)
    Button btn_next;
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

    @BindView(R.id.btn_submit)
    Button mBtnSubmit;
    private List<Fragment> fragmentlists = new ArrayList<>();
    private int minute = 0;
    private int second = 0;
    private AlertDialog.Builder builder;
    private ArrayList<String> a;
    private int nowpager = 0;
    private List<QuestBean> messages;
    private String id;//大分类id
    private String name;
    private String Answer="";
    private int grade;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_view_the_answer;
    }

    @Override
    protected void initData() {

    }

    /**
     * 初始化布局
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void initView() {
// 获取传递来的变量
        messages = (List<QuestBean>) getIntent().getExtras().get("list2");
        name = getIntent().getExtras().get("name").toString().trim();
        mTvActionbarTitle.setText(name);
        for (int i = 0; i < messages.size(); i++) {
            fragmentlists.add(ViewTheAnswerFragment.newInstance(messages.get(i), i));
            vp_answer.setAdapter(new ViewTheAnswerActivity.MainAdapter(getSupportFragmentManager()));
            vp_answer.setOffscreenPageLimit(messages.size());
        }
    }

    @Override
    protected void setListener() {
        btn_previous.setOnClickListener(this);
        mBtnSubmit.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        mLlReturn.setOnClickListener(this);
        vp_answer.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
// 点击上一题按钮
            case R.id.btn_pre:
// 如果是第一题，则谈吐司提醒，否则上移一道题
                if (nowpager == 0) {
                    Toast.makeText(ViewTheAnswerActivity.this, "已经到头啦!", Toast.LENGTH_SHORT).show();
                } else {
                    vp_answer.setCurrentItem(--nowpager);
                }
                break;
// 点击下一题按钮
            case R.id.btn_next:
// 如果是最后一题，则谈吐司提醒，否则下移一道题
                if (nowpager == fragmentlists.size()) {
                    Toast.makeText(ViewTheAnswerActivity.this, "已经是最后一题了!", Toast.LENGTH_SHORT).show();
                } else {
                    vp_answer.setCurrentItem(++nowpager);
                }
                break;
            case R.id.ll_return:
                finish();
                break;
            default:
                break;
        }
    }


    @Override
    public void GetQuestionBycategory(BaseResult<Data<List<QuestBean>>> baseResult) {

    }

    @Override
    public void Calculate(BaseResult<QuestResult> baseResult) {

    }


    /**
     * viewpager适配器
     */
    class MainAdapter extends FragmentPagerAdapter {

        public MainAdapter(FragmentManager fm) {
            super(fm);
        }

        //获取条目
        @Override
        public Fragment getItem(int position) {
            return fragmentlists.get(position);
        }

        //数目
        @Override
        public int getCount() {
            return fragmentlists.size();
        }
    }

    /**
     * viewpager监听事件
     */
    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            nowpager = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}