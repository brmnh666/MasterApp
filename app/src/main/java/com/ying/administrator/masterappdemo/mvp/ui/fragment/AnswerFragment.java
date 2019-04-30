package com.ying.administrator.masterappdemo.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.QuestBean;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;

public class AnswerFragment extends BaseLazyFragment implements RadioGroup.OnCheckedChangeListener {
    private RadioButton rb_option_a;
    private RadioButton rb_option_b;
    private RadioButton rb_option_c;
    private RadioButton rb_option_d;
    private String option = "";
    private RadioGroup rg_base;
    private TextView tv_title;
    QuestBean questBean = null;
    private EditText et_answer;

    public AnswerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            questBean = (QuestBean) getArguments().getSerializable("QuestBean");
        }
    }

    public static AnswerFragment newInstance(QuestBean param1) {
        AnswerFragment fragment = new AnswerFragment();
        Bundle args = new Bundle();
        args.putSerializable("QuestBean", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        View view = View.inflate(mActivity, R.layout.fragment_quest, null);
        tv_title = (TextView) view.findViewById(R.id._tv_title);
        rg_base = (RadioGroup) view.findViewById(R.id._rg_base);
        //如果是选择题，找id,设置监听事件
        if ("1".equals(questBean.getQuesType() + "")) {
            rb_option_a = (RadioButton) view.findViewById(R.id._rb_option_a);
            rb_option_b = (RadioButton) view.findViewById(R.id._rb_option_b);
            rb_option_c = (RadioButton) view.findViewById(R.id._rb_option_c);
            rb_option_d = (RadioButton) view.findViewById(R.id._rb_option_d);
            rg_base.setOnCheckedChangeListener(this);
        }
        //如果是判断题，找id,使C,D选项不可见，设置监听事件
        else if ("2".equals(questBean.getQuesType() + "")) {
            rb_option_a = (RadioButton) view.findViewById(R.id._rb_option_a);
            rb_option_b = (RadioButton) view.findViewById(R.id._rb_option_b);
            rb_option_c = (RadioButton) view.findViewById(R.id._rb_option_c);
            rb_option_d = (RadioButton) view.findViewById(R.id._rb_option_d);
            //CD不可见
            rb_option_c.setVisibility(View.GONE);
            rb_option_d.setVisibility(View.GONE);
            //监听事件
            rg_base.setOnCheckedChangeListener(this);
        }
        //如果是简答题，找id,使选项组不可见，使EditText出现。
        else if ("3".equals(questBean.getQuesType() + "")) {
            et_answer = (EditText) view.findViewById(R.id.et_answer);
            et_answer.setVisibility(View.VISIBLE);
            rg_base.setVisibility(View.GONE);
        }
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_quest;
    }

    @Override
    public void initData() {
        tv_title.setText("" + questBean.getContent());
        //如果没有传递数据，则退出
        if (questBean == null) {
            return;
        }
//        如果是选择题，对应选项赋值
        if ("1".equals(questBean.getQuesType() + "")) {
            rb_option_a.setText("" + questBean.getCase1());
            rb_option_b.setText("" + questBean.getCase2());
            rb_option_c.setText("" + questBean.getCase3());
            rb_option_d.setText("" + questBean.getCase4());
        }
//        如果是判断题，AB设置为对，错。
        else if ("2".equals(questBean.getQuesType() + "")) {
            rb_option_a.setText("对");
            rb_option_b.setText("错");
        }
//        如果是简答题或者其他,不做数据填充
        else {

        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if (checkedId == rb_option_a.getId()) {
            option = "A";
        } else if (checkedId == rb_option_b.getId()) {
            option = "B";
        } else if (checkedId == rb_option_c.getId()) {
            option = "C";
        } else if (checkedId == rb_option_d.getId()) {
            option = "D";
        }
//        设置答案
        questBean.setMyAnswer(option);
    }
}
