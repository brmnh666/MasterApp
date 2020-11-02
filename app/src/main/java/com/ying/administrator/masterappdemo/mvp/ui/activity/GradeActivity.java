package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
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
import com.ying.administrator.masterappdemo.entity.QuestBean;
import com.ying.administrator.masterappdemo.entity.QuestResult;
import com.ying.administrator.masterappdemo.mvp.contract.QuestContract;
import com.ying.administrator.masterappdemo.mvp.model.QuestModel;
import com.ying.administrator.masterappdemo.mvp.presenter.QuestPresenter;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;

public class GradeActivity extends BaseActivity<QuestPresenter, QuestModel> implements View.OnClickListener, QuestContract.View {


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
    @BindView(R.id.tv_grade)
    TextView mTvGrade;
    @BindView(R.id.tv_result)
    TextView mTvResult;
    @BindView(R.id.btn_view_the_answer)
    Button mBtnViewTheAnswer;
    @BindView(R.id.btn_restart)
    Button mBtnRestart;
    private int grade;
    private List<QuestBean> list;
    private String name;
    private String id;
    private List<QuestBean> messages;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_grade;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {

        grade = getIntent().getIntExtra("grade",-1);
        name = getIntent().getStringExtra("name");
        id = getIntent().getStringExtra("id");
        list = (List<QuestBean>) getIntent().getSerializableExtra("list");

        mTvActionbarTitle.setText(name);
        mTvGrade.setText(grade+"分");
        if (grade>60){
            mTvResult.setText( "恭喜你通过" + name+"认证考试！");
        }else{
            mTvResult.setText("很遗憾！你未通过" + name+"认证考试！");
        }

    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
        mBtnViewTheAnswer.setOnClickListener(this);
        mBtnRestart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_return:
                finish();
                break;
            case R.id.btn_view_the_answer:
                Intent intent = new Intent(mActivity, ViewTheAnswerActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("list2", (Serializable) list);
                startActivity(intent);
                break;
            case R.id.btn_restart:
                mPresenter.GetQuestionBycategory(id);
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
                        ToastUtils.showShort("获取题目失败！");
                    }else{
                        Intent intent = new Intent(mActivity, AnswerActivity.class);
                        intent.putExtra("name", name);
                        intent.putExtra("id", id);
                        intent.putExtra("list", (Serializable) messages);
                        startActivity(intent);
                        finish();
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

    @Override
    public void Calculate(BaseResult<QuestResult> baseResult) {

    }
}
