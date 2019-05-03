package com.ying.administrator.masterappdemo.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.QuestBean;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ViewTheAnswerFragment extends BaseLazyFragment {
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    QuestBean questBean = null;
    @BindView(R.id.rb_option_a)
    TextView mRbOptionA;
    @BindView(R.id.rb_option_b)
    TextView mRbOptionB;
    @BindView(R.id.rb_option_c)
    TextView mRbOptionC;
    @BindView(R.id.rb_option_d)
    TextView mRbOptionD;
    @BindView(R.id.tv_true_answer)
    TextView mTvTrueAnswer;
    Unbinder unbinder;
    private int num;

    public ViewTheAnswerFragment() {
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

    public static ViewTheAnswerFragment newInstance(QuestBean param1, int position) {
        ViewTheAnswerFragment fragment = new ViewTheAnswerFragment();
        Bundle args = new Bundle();
        args.putSerializable("QuestBean", param1);
        args.putInt("num", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_view_the_answer;
    }

    @Override
    public void initData() {
        mTvTitle.setText((num + 1) + "、" + questBean.getContent()+"("+questBean.getUseAnswer()+")");
        mTvTrueAnswer.setText("正确答案："+questBean.getAnswer());
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
            mRbOptionC.setVisibility(View.GONE);
            mRbOptionD.setVisibility(View.GONE);
        }
//        如果是简答题或者其他,不做数据填充
        else {

        }
    }

}
