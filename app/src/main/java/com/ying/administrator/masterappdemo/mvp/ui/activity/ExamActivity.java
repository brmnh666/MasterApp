package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.MySkills;
import com.ying.administrator.masterappdemo.entity.QuestBean;
import com.ying.administrator.masterappdemo.entity.QuestResult;
import com.ying.administrator.masterappdemo.mvp.contract.QuestContract;
import com.ying.administrator.masterappdemo.mvp.model.QuestModel;
import com.ying.administrator.masterappdemo.mvp.presenter.QuestPresenter;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExamActivity extends BaseActivity<QuestPresenter, QuestModel> implements View.OnClickListener, QuestContract.View {

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
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.btn_start)
    Button mBtnStart;
    @BindView(R.id.ll_one)
    LinearLayout mLlOne;
    @BindView(R.id.tv_empty)
    TextView mTvEmpty;
    private TextView tv_actionbar_title;
    private LinearLayout ll_return;
    private MySkills skills;
    private List<QuestBean> messages;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_exam;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        tv_actionbar_title = findViewById(R.id.tv_actionbar_title);
        ll_return = findViewById(R.id.ll_return);

        skills = (MySkills) getIntent().getSerializableExtra("skills");
        tv_actionbar_title.setText(skills.getCategory().getFCategoryName());
        mTvContent.setText(skills.getCategory().getFCategoryName() + "认证包含：" + skills.getDetail());
        mPresenter.GetQuestionBycategory(skills.getCategory().getFCategoryID());
    }

    @Override
    protected void setListener() {
        ll_return.setOnClickListener(this);
        mBtnStart.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_return:
                finish();
                break;
            case R.id.btn_start:
                if (messages.size() > 0) {
                    Intent intent = new Intent(mActivity, AnswerActivity.class);
                    intent.putExtra("name", skills.getCategory().getFCategoryName());
                    intent.putExtra("id", skills.getCategory().getFCategoryID());
                    intent.putExtra("list", (Serializable) messages);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }

    @Override
    public void GetQuestionBycategory(BaseResult<Data<List<QuestBean>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<List<QuestBean>> data = baseResult.getData();
                if (data.isItem1()) {
                    messages = data.getItem2();
                    if (messages.size() == 0) {
                        mLlOne.setVisibility(View.GONE);
                        mTvEmpty.setVisibility(View.VISIBLE);
                    }else{
                        mLlOne.setVisibility(View.VISIBLE);
                        mTvEmpty.setVisibility(View.GONE);
                    }
                } else {
                    ToastUtils.showShort("获取题目失败！");
                    mLlOne.setVisibility(View.GONE);
                    mTvEmpty.setVisibility(View.VISIBLE);
                }
                break;
            default:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void Calculate(BaseResult<QuestResult> baseResult) {

    }
}
