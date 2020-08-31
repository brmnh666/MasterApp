package com.ying.administrator.masterappdemo.v3.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.ui.activity.WebActivity;
import com.ying.administrator.masterappdemo.v3.adapter.CourseAdapter;
import com.ying.administrator.masterappdemo.v3.bean.CourseResult;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.CoursePresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.CourseContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.CourseModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

//维修资料
public class CourseActivity extends BaseActivity<CoursePresenter, CourseModel> implements View.OnClickListener, CourseContract.View {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.line_factory)
    View mLineFactory;
    @BindView(R.id.ll_factory)
    LinearLayout mLlFactory;
    @BindView(R.id.line_reference)
    View mLineReference;
    @BindView(R.id.ll_reference)
    LinearLayout mLlReference;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private List<CourseResult.DataBeanX.DataBean.FactoryDataBean> list = new ArrayList<>();
    private CourseAdapter adapter;
    private Intent intent;
    private CourseResult.DataBeanX.DataBean data;
    private WorkOrder.DataBean order;
    private WorkOrder.OrderProductModelsBean prod;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_course;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("资料");
        order = (WorkOrder.DataBean) getIntent().getSerializableExtra("data");
        prod = (WorkOrder.OrderProductModelsBean) getIntent().getSerializableExtra("prod");

        mPresenter.GetCourse(prod.getSubCategoryID()+"",prod.getProductTypeID()+"",prod.getBrandID()+"",order.getTypeID(),prod.getProdModelID()+"",order.getUserID());
        adapter = new CourseAdapter(R.layout.v3_item_course, list);
        mRvList.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvList.setAdapter(adapter);
        adapter.setEmptyView(getEmptyView());
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                intent = new Intent(mActivity, WebActivity.class);
                intent.putExtra("Url", list.get(position).getContent());
                intent.putExtra("Title", list.get(position).getTitle());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mLlFactory.setOnClickListener(this);
        mLlReference.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_factory:
                mLineFactory.setVisibility(View.VISIBLE);
                mLineReference.setVisibility(View.INVISIBLE);
                list=data.getFactoryData();
                adapter.setNewData(list);
                break;
            case R.id.ll_reference:
                mLineFactory.setVisibility(View.INVISIBLE);
                mLineReference.setVisibility(View.VISIBLE);
                list=data.getReferenceData();
                adapter.setNewData(list);
                break;
        }
    }

    @Override
    public void GetCourse(CourseResult baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                data =baseResult.getData().getData();
                if (baseResult.getData().getCode()==0){
                    list=data.getFactoryData();
                    adapter.setNewData(list);
                }
                break;
        }
    }
}
