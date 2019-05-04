package com.ying.administrator.masterappdemo.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.QuestBean;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;

import butterknife.BindView;

public class AnswerFragment extends BaseLazyFragment implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rb_option_a)
    RadioButton mRbOptionA;
    @BindView(R.id.rb_option_b)
    RadioButton mRbOptionB;
    @BindView(R.id.rb_option_c)
    RadioButton mRbOptionC;
    @BindView(R.id.rb_option_d)
    RadioButton mRbOptionD;
    @BindView(R.id.rg_base)
    RadioGroup mRgBase;
    @BindView(R.id.et_answer)
    EditText mEtAnswer;
    @BindView(R.id.iv_picture)
    ImageView mIvPicture;
    private String option = "";
    QuestBean questBean = null;
    private int num;

    public AnswerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            questBean = (QuestBean) getArguments().getSerializable("QuestBean");
            num = getArguments().getInt("num");
        }
    }

    public static AnswerFragment newInstance(QuestBean param1,int position) {
        AnswerFragment fragment = new AnswerFragment();
        Bundle args = new Bundle();
        args.putSerializable("QuestBean", param1);
        args.putInt("num", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        //如果是选择题，找id,设置监听事件
        if ("1".equals(questBean.getQuesType() + "")) {
            mRgBase.setOnCheckedChangeListener(this);
        }
        //如果是判断题，找id,使C,D选项不可见，设置监听事件
        else if ("2".equals(questBean.getQuesType() + "")) {
            //CD不可见
            mRbOptionC.setVisibility(View.GONE);
            mRbOptionD.setVisibility(View.GONE);
            //监听事件
            mRgBase.setOnCheckedChangeListener(this);
        }
        //如果是简答题，找id,使选项组不可见，使EditText出现。
//        else if ("3".equals(questBean.getQuesType() + "")) {
//            mEtAnswer.setVisibility(View.VISIBLE);
//            mRgBase.setVisibility(View.GONE);
//        }
        mTvTitle.setText((num+1)+"、" + questBean.getContent());
        //如果没有传递数据，则退出
        if (questBean == null) {
            return;
        }
//        如果是选择题，对应选项赋值
        if ("1".equals(questBean.getQuesType() + "")) {
            mRbOptionA.setText("A、" + questBean.getCase1());
            mRbOptionB.setText("B、" + questBean.getCase2());
            mRbOptionC.setText("C、" + questBean.getCase3());
            mRbOptionD.setText("D、" + questBean.getCase4());
        }
//        如果是判断题，AB设置为对，错。
        else if ("2".equals(questBean.getQuesType() + "")) {
            mRbOptionA.setText("对");
            mRbOptionB.setText("错");
        }
//        如果是简答题或者其他,不做数据填充
        else {

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

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        //        如果是选择题，对应选项赋值
        if ("1".equals(questBean.getQuesType() + "")) {
            if (checkedId == mRbOptionA.getId()) {
                option = "A";
            } else if (checkedId == mRbOptionB.getId()) {
                option = "B";
            } else if (checkedId == mRbOptionC.getId()) {
                option = "C";
            } else if (checkedId == mRbOptionD.getId()) {
                option = "D";
            }
        }
//        如果是判断题，AB设置为对，错。
        else if ("2".equals(questBean.getQuesType() + "")) {
            if (checkedId == mRbOptionA.getId()) {
                option = "对";
            } else if (checkedId == mRbOptionB.getId()) {
                option = "错";
            }
        }

//        设置答案
        questBean.setUseAnswer(option);
    }

}
