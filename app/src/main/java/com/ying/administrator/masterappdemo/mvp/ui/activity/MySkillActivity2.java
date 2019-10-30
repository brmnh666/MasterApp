package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.entity.MultiItemEntity;
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
import com.ying.administrator.masterappdemo.mvp.ui.adapter.CarCircuitAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.MySkillAdapter;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MySkillActivity2 extends BaseActivity<AddSkillsPresenter, AddSkillsModel> implements View.OnClickListener, AddSkillsContract.View {
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
    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.actionbar_layout)
    RelativeLayout mActionbarLayout;

    @BindView(R.id.btn_skill)
    Button mBtnSkill;
    @BindView(R.id.expandablelistview)
    ExpandableListView mExpandablelistview;

    private List<MySkills> mySkillsList = new ArrayList<>();
    private List<Category> popularList;
    private List<Category> subList;
    ZLoadingDialog dialog = new ZLoadingDialog(this); //loading
    List<MultiItemEntity> detailList = new ArrayList<>();
    private CarCircuitAdapter circuitAdapter;
    private int position;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_my_skill2;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvActionbarTitle.setText("我的技能");
        mPresenter.GetFactoryCategory("999");
    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_return:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
                            if ("999".equals(popularList.get(i).getParentID())) {
                                mySkillsList.add(new MySkills(false, popularList.get(i), popularList));
                            }
                        }
                        for (int i = 0; i < mySkillsList.size(); i++) {
                            subList = new ArrayList<>();
                            for (int j = 0; j < popularList.size(); j++) {
                                if (mySkillsList.get(i).getCategory().getId().equals(popularList.get(j).getParentID())) {
                                    subList.add(popularList.get(j));
                                }
                            }
                            mySkillsList.get(i).setCategoryArrayList(subList);
                        }
//
                        circuitAdapter = new CarCircuitAdapter(this);
                        circuitAdapter.addGroupList(mySkillsList);
                        mExpandablelistview.setGroupIndicator(null);
                        mExpandablelistview.setAdapter(circuitAdapter);
                        //点击父级请求子级数据
                        mExpandablelistview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                            @Override
                            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                                String circuit_id = circuitAdapter.getGroup(groupPosition).getCategory().getFCategoryID();
                                position = groupPosition;
                                mPresenter.GetChildFactoryCategory(circuit_id);
                                return false;
                            }
                        });
                    }
                } else {
                    ToastUtils.showShort("获取分类失败！");
                }
                cancleLoading();
                break;
            case 401:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetChildFactoryCategory(BaseResult<CategoryData> baseResult) {
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
                       circuitAdapter.addAllChild(position,popularList);
                    }
                } else {
                    ToastUtils.showShort("获取分类失败！");
                }
                cancleLoading();
                break;
            case 401:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetAccountSkill(BaseResult<List<Skill>> baseResult) {

    }

    @Override
    public void UpdateAccountSkillData(BaseResult<String> baseResult) {

    }

    public void showLoading() {
        dialog.setLoadingBuilder(Z_TYPE.SINGLE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setHintText("正在加载...")
                .setHintTextSize(14) // 设置字体大小 dp
                .setHintTextColor(Color.BLACK)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setCanceledOnTouchOutside(false)//点击外部无法取消
                .show();
    }

    public void cancleLoading() {
        dialog.dismiss();
    }
}
