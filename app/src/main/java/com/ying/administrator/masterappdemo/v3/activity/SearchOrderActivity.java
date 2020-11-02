package com.ying.administrator.masterappdemo.v3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.v3.adapter.OrderAdapter;
import com.ying.administrator.masterappdemo.v3.bean.OrderListResult;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.EndOrderPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.EndOrderContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.EndOrderModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchOrderActivity extends BaseActivity<EndOrderPresenter, EndOrderModel> implements View.OnClickListener, EndOrderContract.View {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.ll_search)
    LinearLayout mLlSearch;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.rv_order)
    RecyclerView mRvOrder;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    private String userId;
    private List<OrderListResult.DataBeanX.DataBean> list=new ArrayList<>();
    private OrderAdapter adapter;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_search_order;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("工单搜索");
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        adapter = new OrderAdapter(R.layout.v3_item_home, list,"search",userId);
        mRvOrder.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter.setEmptyView(getHomeEmptyView());
        mRvOrder.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(mActivity, ServingDetailActivity.class);
                intent.putExtra("id",list.get(position).getOrderID());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mTvSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_search:
                String searchname = mEtSearch.getText().toString();
                if (searchname == null || "".equals(searchname)) {
                    MyUtils.showToast(mActivity,"请输入用户手机号或者工单号");
                } else {
                    showProgress();
                    StringBuilder stringBuilder = new StringBuilder(searchname);
                    String name = stringBuilder.substring(0, 1);
                    mPresenter.GetOrderList(searchname, "", "1", "10");
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


    @Override
    public void GetOrderList(OrderListResult baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                hideProgress();
                list=baseResult.getData().getData();
                adapter.setNewData(list);
                break;
        }
    }
}
