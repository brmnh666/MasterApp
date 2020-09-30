package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Category;
import com.ying.administrator.masterappdemo.entity.CategoryData;
import com.ying.administrator.masterappdemo.entity.MySkills;
import com.ying.administrator.masterappdemo.entity.Skill;
import com.ying.administrator.masterappdemo.mvp.contract.AddSkillsContract;
import com.ying.administrator.masterappdemo.mvp.model.AddSkillsModel;
import com.ying.administrator.masterappdemo.mvp.presenter.AddSkillsPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.StudyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudyActivity extends BaseActivity<AddSkillsPresenter, AddSkillsModel> implements View.OnClickListener, AddSkillsContract.View {
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
    @BindView(R.id.rv_kills)
    RecyclerView mRvKills;
    @BindView(R.id.btn_skill)
    Button mBtnSkill;
    private TextView tv_actionbar_title;
    private LinearLayout ll_return;

    private StudyAdapter studyAdapter;
    private List<MySkills> mySkillsList=new ArrayList<>();
    private List<Category> popularList;
    private List<Category> subList;
    private String skills;
    private String NodeIds="";

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_skills);
//
//
//    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_my_skills;
    }

    @Override
    protected void initData() {
        showProgress();
        mPresenter.GetFactoryCategory("999");
    }
    @Override
    protected void initView() {
        tv_actionbar_title = findViewById(R.id.tv_actionbar_title);
        ll_return = findViewById(R.id.ll_return);
        tv_actionbar_title.setText("学习考试");
        mBtnSkill.setVisibility(View.GONE);
    }

    @Override
    protected void setListener() {
        ll_return.setOnClickListener(this);
        mBtnSkill.setOnClickListener(this);
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
            case R.id.btn_skill:

                break;
        }
    }

    @Override
    public void GetFactoryCategory(BaseResult<CategoryData> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:

                CategoryData data = baseResult.getData();
                if ("0".equals(data.getCode())) {
                    popularList = data.getData();
                    if (popularList.size() == 0) {
                        ToastUtils.showShort("无分类，请联系管理员添加！");
                    } else {
                        for (int i = 0; i < popularList.size(); i++) {
                            if ("999".equals(popularList.get(i).getParentID())){
                                mySkillsList.add(new MySkills(false,popularList.get(i),popularList));
                            }
                        }
                        for (int i = 0; i < mySkillsList.size(); i++) {
                            subList=new ArrayList<>();
                            for (int j = 0; j < popularList.size(); j++) {
                                if (mySkillsList.get(i).getCategory().getId().equals(popularList.get(j).getParentID())){
                                    subList.add(popularList.get(j));
                                }
                            }
                            mySkillsList.get(i).setCategoryArrayList(subList);
                        }
                        studyAdapter=new StudyAdapter(R.layout.item_kills_exam,mySkillsList);
                        mRvKills.setLayoutManager(new LinearLayoutManager(mActivity));
                        mRvKills.setAdapter(studyAdapter);
                        studyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent=new Intent(mActivity,ExamActivity.class);
                                intent.putExtra("skills",mySkillsList.get(position));
                                startActivity(intent);
                            }
                        });
                    }
                } else {
                    ToastUtils.showShort("获取分类失败！");
                }
                hideProgress();
                break;
            case 401:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetChildFactoryCategory(BaseResult<CategoryData> baseResult) {

    }

    @Override
    public void GetAccountSkill(BaseResult<List<Skill>> baseResult) {

    }

    @Override
    public void UpdateAccountSkillData(BaseResult<String> baseResult) {

    }

}
