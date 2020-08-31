package com.ying.administrator.masterappdemo.v3.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.AddOrderSignInRecrodResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GetBrandWithCategory;
import com.ying.administrator.masterappdemo.entity.SubUserInfo;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.util.SingleClick;
import com.ying.administrator.masterappdemo.v3.adapter.PriceBeanAdapter;
import com.ying.administrator.masterappdemo.v3.bean.GetOrderMoneyDetailResult;
import com.ying.administrator.masterappdemo.v3.bean.ProductTollResult;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.ServingDetailPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.ServingDetailContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.ServingDetailModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 工单费用详情
 */
public class FeeDetailActivity extends BaseActivity<ServingDetailPresenter, ServingDetailModel> implements View.OnClickListener, ServingDetailContract.View {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.tv_total)
    TextView mTvTotal;
    private String userId;
    private String orderId;
    private List<GetOrderMoneyDetailResult.DataBeanX.DataBean.Item1Bean> list;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_fee_detail;
    }

    @Override
    protected void initData() {
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
    }

    @Override
    protected void initView() {

        mTvTitle.setText("费用明细");

        orderId = getIntent().getStringExtra("orderid");
        mPresenter.GetOrderMoneyDetail(orderId);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.GetOrderMoneyDetail(orderId);
            }
        });
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                EventBus.getDefault().post(20);//刷新选项卡数量全局搜case 20:
                finish();
                break;
        }
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {

    }

    @Override
    public void AddReturnAccessory(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void UpdateOrderState(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void UpdateSendOrderUpdateTime(BaseResult<Data> baseResult) {
    }

    @Override
    public void GetBrandWithCategory2(BaseResult<Data<List<GetBrandWithCategory>>> baseResult) {
    }

    @Override
    public void AddOrderSignInRecrod(AddOrderSignInRecrodResult baseResult) {
    }

    @Override
    public void ProductToll(ProductTollResult baseResult) {
    }

    @Override
    public void GetOrderMoneyDetail(GetOrderMoneyDetailResult baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getCode() == 0) {
                    mRvList.setLayoutManager(new LinearLayoutManager(mActivity));
                    list =baseResult.getData().getData().getItem1();
                    for (int i = 0; i <list.size() ; i++) {
                        if (list.get(i).getTypeName().contains("平台费")){
                            list.remove(i);
                        }
                    }
                    PriceBeanAdapter adapter = new PriceBeanAdapter(R.layout.price_master_item, list);
                    mRvList.setAdapter(adapter);
                    mTvTotal.setText("¥"+baseResult.getData().getData().getItem3());
                } else {
                    MyUtils.showToast(mActivity, baseResult.getData().getMsg());
                }
                mRefreshLayout.finishRefresh();
                break;
        }
    }

    @Override
    public void OrderIsCall(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetChildAccountByParentUserID(BaseResult<List<SubUserInfo.SubUserInfoDean>> baseResult) {

    }

    @Override
    public void ChangeSendOrder(BaseResult<Data> baseResult) {

    }

    @Override
    public void UpdateSendOrderState(BaseResult<Data> baseResult) {

    }

    @Override
    public void AddOrderSuccess(BaseResult<Data> baseResult) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
