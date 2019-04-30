package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.CategoryData;
import com.ying.administrator.masterappdemo.entity.MySkills;
import com.ying.administrator.masterappdemo.entity.Skill;
import com.ying.administrator.masterappdemo.mvp.contract.AddSkillsContract;
import com.ying.administrator.masterappdemo.mvp.model.AddSkillsModel;
import com.ying.administrator.masterappdemo.mvp.presenter.AddSkillsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExamActivity extends BaseActivity<AddSkillsPresenter, AddSkillsModel> implements View.OnClickListener, AddSkillsContract.View {

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
    private TextView tv_actionbar_title;
    private LinearLayout ll_return;
    private MySkills skills;

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
        mTvContent.setText(skills.getCategory().getFCategoryName()+"认证包含："+ skills.getDetail());
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
                Intent intent=new Intent(mActivity,AnswerActivity.class);
                intent.putExtra("id",skills.getCategory().getFCategoryID());
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void GetFactoryCategory(BaseResult<CategoryData> baseResult) {

    }

    @Override
    public void GetAccountSkill(BaseResult<List<Skill>> baseResult) {

    }

    @Override
    public void UpdateAccountSkillData(BaseResult<String> baseResult) {

    }
}
