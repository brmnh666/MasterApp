package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
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

import com.blankj.utilcode.util.ToastUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.QuestBean;
import com.ying.administrator.masterappdemo.entity.QuestResult;
import com.ying.administrator.masterappdemo.mvp.contract.QuestContract;
import com.ying.administrator.masterappdemo.mvp.model.QuestModel;
import com.ying.administrator.masterappdemo.mvp.presenter.QuestPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.AnswerFragment;
import com.ying.administrator.masterappdemo.widget.CustomViewPager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;

public class AnswerActivity extends BaseActivity<QuestPresenter, QuestModel> implements View.OnClickListener, QuestContract.View {

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
    @BindView(R.id.countdownview)
    CountdownView mCountdownview;
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
        return R.layout.activity_answer;
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
        messages = (List<QuestBean>) getIntent().getExtras().get("list");
        name = getIntent().getExtras().get("name").toString().trim();
        id = getIntent().getExtras().get("id").toString().trim();
        mTvActionbarTitle.setText(name);
        for (int i = 0; i < messages.size(); i++) {
            fragmentlists.add(AnswerFragment.newInstance(messages.get(i), i));
            vp_answer.setAdapter(new AnswerActivity.MainAdapter(getSupportFragmentManager()));
            vp_answer.setOffscreenPageLimit(messages.size());
        }
// 联网获取数据
//        initNet(type);




    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
        btn_previous.setOnClickListener(this);
        mBtnSubmit.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        vp_answer.setOnPageChangeListener(new MyOnPageChangeListener());
        mCountdownview.start(3600000);
        mCountdownview.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                startActivity(new Intent(mActivity,GradeActivity.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
// 点击上一题按钮
            case R.id.btn_pre:
// 如果是第一题，则谈吐司提醒，否则上移一道题
                if (nowpager == 0) {
                    Toast.makeText(AnswerActivity.this, "已经到头啦!", Toast.LENGTH_SHORT).show();
                } else {
                    vp_answer.setCurrentItem(--nowpager);
                }
                break;
// 点击提交按钮
            case R.id.btn_submit:
// 简答题不进行提交评分
//                if (type.equals("3")) {
//                    Toast.makeText(this, "简答题目前暂不支持评分", Toast.LENGTH_SHORT).show();
//                    return;
//                }
// 否则初始化并展示提交对话框
                initAlertDialog();
                builder.show();
                break;
// 点击下一题按钮
            case R.id.btn_next:
// 如果是最后一题，则谈吐司提醒，否则下移一道题
                if (nowpager == fragmentlists.size()) {
                    Toast.makeText(AnswerActivity.this, "已经是最后一题了!", Toast.LENGTH_SHORT).show();
                } else {
                    vp_answer.setCurrentItem(++nowpager);
                }
                break;
            case R.id.ll_return:
                builder = new AlertDialog.Builder(AnswerActivity.this);
                builder.setTitle("提示");
                builder.setMessage("是否结束考试?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }

                });
                builder.setNegativeButton("取消", null);
                builder.create().show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void GetQuestionBycategory(BaseResult<Data<List<QuestBean>>> baseResult) {

    }

    @Override
    public void Calculate(BaseResult<QuestResult> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                grade = baseResult.getData().getItem1();
                messages = baseResult.getData().getItem2();
                // 传递分数
                Intent intent = new Intent(AnswerActivity.this, GradeActivity.class);
                intent.putExtra("grade", grade);
                intent.putExtra("name", name);
                intent.putExtra("id", id);
// 传递题目列表
                intent.putExtra("list", (Serializable) messages);
                startActivity(intent);
                finish();
                break;
            default:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
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

    // 弹出是否确认交卷的对话框
    private void initAlertDialog() {
//新建对话框
        builder = new AlertDialog.Builder(AnswerActivity.this);
        builder.setTitle("提示");
        builder.setMessage("是否确定交卷?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
// TODO: 2017/6/14 交卷操作
// 计算分数
                int grade = 0;
                for (int i = 0; i < messages.size(); i++) {
                    Answer += messages.get(i).getId()+","+messages.get(i).getUseAnswer()+"|";
                }
                Answer=Answer.substring(0,Answer.lastIndexOf("|"));
                mPresenter.Calculate(Answer);

            }

        });
        builder.setNegativeButton("取消", null);
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
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        builder = new AlertDialog.Builder(AnswerActivity.this);
        builder.setTitle("提示");
        builder.setMessage("是否结束考试?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }

        });
        builder.setNegativeButton("取消", null);
        builder.create().show();
    }
}