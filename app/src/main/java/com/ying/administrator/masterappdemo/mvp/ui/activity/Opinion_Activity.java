package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.util.MyUtils;

import butterknife.BindView;

public class Opinion_Activity extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.tv_account_problem)
    TextView mTvAccountProblem;
    @BindView(R.id.tv_payment_issues)
    TextView mTvPaymentIssues;
    @BindView(R.id.tv_other_questions)
    TextView mTvOtherQuestions;
    @BindView(R.id.et_opinion)
    EditText mEtOpinion;
    @BindView(R.id.tv_word_count)
    TextView mTvWordCount;
    @BindView(R.id.btn_opinion)
    Button mBtnOpinion;

    private String type="";
    private String content;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_opinion;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvActionbarTitle.setText("意见反馈");
    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
        mTvAccountProblem.setOnClickListener(this);
        mTvPaymentIssues.setOnClickListener(this);
        mTvOtherQuestions.setOnClickListener(this);
        mBtnOpinion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_return:
                Opinion_Activity.this.finish();
                break;
            case R.id.tv_account_problem:
                type="1";
                mTvAccountProblem.setSelected(true);
                mTvPaymentIssues.setSelected(false);
                mTvOtherQuestions.setSelected(false);
                break;
            case R.id.tv_payment_issues:
                type="2";
                mTvAccountProblem.setSelected(false);
                mTvPaymentIssues.setSelected(true);
                mTvOtherQuestions.setSelected(false);
                break;
            case R.id.tv_other_questions:
                type="3";
                mTvAccountProblem.setSelected(false);
                mTvPaymentIssues.setSelected(false);
                mTvOtherQuestions.setSelected(true);
                break;
            case R.id.btn_opinion:
                content =mEtOpinion.getText().toString().trim();
                if ("".equals(content)){
                    MyUtils.showToast(mActivity,"请输入反馈内容");
                    return;
                }

                break;

        }

    }

}
