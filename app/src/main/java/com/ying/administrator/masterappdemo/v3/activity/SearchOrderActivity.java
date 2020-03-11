package com.ying.administrator.masterappdemo.v3.activity;

import android.content.ClipData;
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
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.v3.adapter.OrderAdapter;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.SearchOrderPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.SearchOrderContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.SearchOrderModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchOrderActivity extends BaseActivity<SearchOrderPresenter, SearchOrderModel> implements View.OnClickListener, SearchOrderContract.View {

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
                    ToastUtils.showShort("请输入用户手机号或者工单号");
                } else {
                    StringBuilder stringBuilder = new StringBuilder(searchname);
                    String name = stringBuilder.substring(0, 1);
                    if ("1".equals(name)) {
                        mPresenter.GetOrderInfoList(searchname, "", userId, "999", "1");
                    } else {
                        mPresenter.GetOrderInfoList("", searchname, userId, "999", "1");
                    }
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
    public void GetOrderInfoList(final BaseResult<WorkOrder> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                adapter = new OrderAdapter(R.layout.v3_item_home, baseResult.getData().getData(),"search",userId);
                mRvOrder.setLayoutManager(new LinearLayoutManager(mActivity));
                mRvOrder.setAdapter(adapter);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        if ("2".equals(baseResult.getData().getData().get(position).getState())){
                            Intent intent=new Intent(mActivity, AppointmentDetailsActivity.class);
                            intent.putExtra("id",baseResult.getData().getData().get(position).getOrderID());
                            startActivity(intent);
                        }else {
                            Intent intent=new Intent(mActivity, ServingDetailActivity.class);
                            intent.putExtra("id",baseResult.getData().getData().get(position).getOrderID());
                            intent.putExtra("type","pedding");
                            startActivity(intent);
                        }

                    }
                });
                break;
        }
    }
}
