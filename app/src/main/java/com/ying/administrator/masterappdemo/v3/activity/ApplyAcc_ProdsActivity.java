package com.ying.administrator.masterappdemo.v3.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.AddressList;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.v3.adapter.ProdAdapter;
import com.ying.administrator.masterappdemo.v3.bean.ApplicationResult;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.ApplyAccPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.ApplyAccContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.ApplyAccModel;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 新版申请配件产品列表
 */

public class ApplyAcc_ProdsActivity extends BaseActivity<ApplyAccPresenter, ApplyAccModel> implements View.OnClickListener, ApplyAccContract.View {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.rv_prods)
    RecyclerView mRvProds;

    private String cj_or_zg;
    private SPUtils spUtils;
    private String UserID;
    private List<WorkOrder.OrderProductModelsBean> list_prod=new ArrayList<>();
    private ProdAdapter prodAdapter;
    private String OrderID;
    private Intent intent;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_apply_acc_prods;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        spUtils = SPUtils.getInstance("token");
        UserID = spUtils.getString("userName");
        list_prod= (List<WorkOrder.OrderProductModelsBean>) getIntent().getSerializableExtra("list_prod");
        mRvProds.setLayoutManager(new LinearLayoutManager(mActivity));
        prodAdapter = new ProdAdapter(R.layout.prod_item, list_prod);
        mRvProds.setAdapter(prodAdapter);
        prodAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                intent = new Intent(mActivity, ApplyAccActivity.class);
                intent.putExtra("OrderID", list_prod.get(position).getOrderID()+"");
                intent.putExtra("prodID", list_prod.get(position).getOrderProdcutID()+"");
                intent.putExtra("prodName", list_prod.get(position).getSubCategoryName()+"(编号："+list_prod.get(position).getOrderProdcutID()+")");
                intent.putExtra("SubCategoryID", list_prod.get(position).getProductTypeID()+"");
                intent.putExtra("cj_or_zg", cj_or_zg);
                startActivity(intent);
            }
        });
        cj_or_zg = getIntent().getStringExtra("cj_or_zg");
        OrderID = getIntent().getStringExtra("OrderID");
        if ("厂寄".equals(cj_or_zg)) {
            mTvTitle.setText("厂家寄件申请");
            mPresenter.GetAccountAddress(UserID);
        } else {
            mTvTitle.setText("师傅自购件申请");
        }
        OrderID = getIntent().getStringExtra("OrderID");
    }

    @Override
    public void GetAccountAddress(BaseResult<List<AddressList>> baseResult) {

    }

    @Override
    public void Application(ApplicationResult baseResult) {

    }
    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                list_prod=baseResult.getData().getOrderProductModels();
                prodAdapter.setNewData(list_prod);
                break;
        }
    }
    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(Integer name) {
        switch (name) {
            case 21:
                mPresenter.GetOrderInfo(OrderID);
                break;
        }
    }
}

