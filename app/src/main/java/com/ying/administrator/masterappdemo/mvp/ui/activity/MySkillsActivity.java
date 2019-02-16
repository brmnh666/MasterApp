package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.entity.MySkills;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.MySkillAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MySkillsActivity extends BaseActivity {
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

    private MySkillAdapter mySkillAdapter;
    private List<MySkills> mySkillsList=new ArrayList<>();

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
        for(int i=0;i<10;i++){
            mySkillsList.add(new MySkills());
        }
        mySkillAdapter=new MySkillAdapter(R.layout.item_kills,mySkillsList);
        mRvKills.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvKills.setAdapter(mySkillAdapter);
//        mySkillAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                switch (view.getId()){
//                    case R.id.iv_drop_down:
//
//                        break;
//                }
//            }
//        });
    }

    @Override
    protected void initView() {
        tv_actionbar_title = findViewById(R.id.tv_actionbar_title);
        ll_return = findViewById(R.id.ll_return);
        tv_actionbar_title.setText("我的技能");
        ll_return.setOnClickListener(new CustomLister());
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public class CustomLister implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_return:
                    MySkillsActivity.this.finish();
                    break;
            }
        }
    }
}
