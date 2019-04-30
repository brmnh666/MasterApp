package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.QuestBean;
import com.ying.administrator.masterappdemo.mvp.contract.QuestContract;
import com.ying.administrator.masterappdemo.mvp.model.QuestModel;
import com.ying.administrator.masterappdemo.mvp.presenter.QuestPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.AnswerFragment;
import com.ying.administrator.masterappdemo.widget.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnswerActivity extends BaseActivity<QuestPresenter, QuestModel> implements Chronometer.OnChronometerTickListener, View.OnClickListener, QuestContract.View {

    @BindView(R.id.viewPager)
    CustomViewPager vp_answer;
    @BindView(R.id.btn_pre)
    Button btn_previous;
    @BindView(R.id.btn_next)
    Button btn_next;
    private Chronometer chronometer;
    private ArrayList<Fragment> fragmentlists;
    private int minute = 0;
    private int second = 0;
    private AlertDialog.Builder builder;
    private ArrayList<String> a;
    private Button btn_submit;
    private int nowpager = 0;
    private List<QuestBean> messages;
    private String id;//大分类id

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
    @Override
    protected void initView() {
        chronometer =  findViewById(R.id._chro_exam);
        vp_answer = findViewById(R.id.viewPager);
        btn_previous =  findViewById(R.id.btn_pre);
        btn_submit =  findViewById(R.id.btn_submit);
        btn_next =  findViewById(R.id.btn_next);
// 获取传递来的变量
        id = getIntent().getExtras().get("id").toString().trim();

// 联网获取数据
//        initNet(type);

        mPresenter.GetQuestionBycategory(id);

        btn_previous.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        vp_answer.setOnPageChangeListener(new MyOnPageChangeListener());
        setChronometer();
    }

    @Override
    protected void setListener() {

    }

    /**
     * 设置计时器
     */
    private void setChronometer() {
        chronometer.setText(nowtime());
        chronometer.start();
        chronometer.setOnChronometerTickListener(this);
        chronometer.setOnClickListener(this);
    }

    /**
     * 计时器规则
     *
     * @param chronometer
     */
    @Override
    public void onChronometerTick(Chronometer chronometer) {
        second++;
        if (second == 59) {
            minute++;
            second = 00;
        }
    }

    /**
     * 现在时间
     *
     * @return
     */
    private String nowtime() {
        if (second < 10) {
            return (minute + ":0" + second);
        } else {
            return (minute + ":" + second);
        }
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
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<List<QuestBean>> data = baseResult.getData();
                if (data.isItem1()) {
                    messages = data.getItem2();
                    for (int i = 0; i < messages.size(); i++) {
                        fragmentlists.add(AnswerFragment.newInstance(messages.get(i)));
                        vp_answer.setAdapter(new MainAdapter(getSupportFragmentManager()));
                    }
                } else {
                    ToastUtils.showShort("获取题目失败！");
                }
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
// 判断题
//                if (type.equals("2")) {
//                    for (int i = 0; i < messages.size(); i++) {
// 查询
//                        QuestBean questBeenA = LoveDao.queryLove(Integer.parseInt(a.get(i)));
// 判断
//                        if (questBeenA.getAnswer().equals("对") && questBeenA.getMyanswer().equals("A") || questBeenA.getAnswer().equals("错") && questBeenA.getMyanswer().equals("B")) {
//                            grade += 20;
//                        }
//                    }
//                }
// 选择题
//                else {
//                    for (int i = 0; i < messages.size(); i++) {
//                        QuestBean questBeenA = LoveDao.queryLove(Integer.parseInt(a.get(i)));
//                        if (questBeenA.getAnswer().equals(questBeenA.getMyanswer())) {
//                            grade += 20;
//                        }
//                    }
//                }

// 传递分数
                Intent intent = new Intent(AnswerActivity.this, GradeActivity.class);
                intent.putExtra("grade", "" + grade);
// 传递题目列表
                intent.putStringArrayListExtra("timu", a);
                startActivity(intent);
                finish();
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
}