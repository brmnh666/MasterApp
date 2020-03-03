package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.mvp.contract.OpinionContract;
import com.ying.administrator.masterappdemo.mvp.model.OpinionModel;
import com.ying.administrator.masterappdemo.mvp.presenter.OpinionPresenter;
import com.ying.administrator.masterappdemo.util.MyUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Opinion_Activity extends BaseActivity<OpinionPresenter, OpinionModel> implements View.OnClickListener, OpinionContract.View {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
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
    private String type = "";
    private String content;
    private String userId;
    private int MAX_COUNT = 200;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_opinion;
    }

    @Override
    protected void initData() {
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
    }

    @Override
    protected void initView() {
        mTvTitle.setText("意见反馈");
        type = "1";
        mTvAccountProblem.setSelected(true);
        mTvPaymentIssues.setSelected(false);
        mTvOtherQuestions.setSelected(false);
        mEtOpinion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mTvWordCount.setText(editable.length() + "/200");
            }
        });
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mTvAccountProblem.setOnClickListener(this);
        mTvPaymentIssues.setOnClickListener(this);
        mTvOtherQuestions.setOnClickListener(this);
        mBtnOpinion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                Opinion_Activity.this.finish();
                break;
            case R.id.tv_account_problem:
                type = "1";
                mTvAccountProblem.setSelected(true);
                mTvPaymentIssues.setSelected(false);
                mTvOtherQuestions.setSelected(false);
                break;
            case R.id.tv_payment_issues:
                type = "2";
                mTvAccountProblem.setSelected(false);
                mTvPaymentIssues.setSelected(true);
                mTvOtherQuestions.setSelected(false);
                break;
            case R.id.tv_other_questions:
                type = "3";
                mTvAccountProblem.setSelected(false);
                mTvPaymentIssues.setSelected(false);
                mTvOtherQuestions.setSelected(true);
                break;
            case R.id.btn_opinion:
                content = mEtOpinion.getText().toString().trim();
                if ("".equals(type)) {
                    MyUtils.showToast(mActivity, "请选择问题类型");
                    return;
                }
                if ("".equals(content)) {
                    MyUtils.showToast(mActivity, "请输入反馈内容");
                    return;
                }
                mPresenter.AddOpinion(userId, type, content);
                break;

        }

    }

    @Override
    public void AddOpinion(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data = baseResult.getData();
                if (data.isItem1()) {
//                    ToastUtils.showShort(data.getItem2());
                    ToastUtils.showShort("反馈成功");
                    mEtOpinion.setText("");
                    type = "";
                    mTvAccountProblem.setSelected(false);
                    mTvPaymentIssues.setSelected(false);
                    mTvOtherQuestions.setSelected(false);
                } else {
                    ToastUtils.showShort(data.getItem2());
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
