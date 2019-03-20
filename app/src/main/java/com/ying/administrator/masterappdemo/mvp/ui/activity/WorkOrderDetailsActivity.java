package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GAccessory;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.entity.Service;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.PendingOrderContract;
import com.ying.administrator.masterappdemo.mvp.model.PendingOrderModel;
import com.ying.administrator.masterappdemo.mvp.presenter.PendingOrderPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.GServiceAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.ReturnAccessoryAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkOrderDetailsActivity extends BaseActivity<PendingOrderPresenter, PendingOrderModel> implements View.OnClickListener, PendingOrderContract.View {


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
    @BindView(R.id.tv_work_order_status)
    TextView mTvWorkOrderStatus;
    @BindView(R.id.tv_payment_method)
    TextView mTvPaymentMethod;
    @BindView(R.id.tv_order_time)
    TextView mTvOrderTime;
    @BindView(R.id.tv_work_order_number)
    TextView mTvWorkOrderNumber;
    @BindView(R.id.tv_cause_of_issue)
    TextView mTvCauseOfIssue;
    @BindView(R.id.tv_number)
    TextView mTvNumber;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.tv_product_type)
    TextView mTvProductType;
    @BindView(R.id.tv_service_address)
    TextView mTvServiceAddress;
    @BindView(R.id.rv_return_information)
    RecyclerView mRvReturnInformation;
    @BindView(R.id.tv_accessory_application)
    TextView mTvAccessoryApplication;
    @BindView(R.id.tv_service_application)
    TextView mTvServiceApplication;
    @BindView(R.id.rv_service)
    RecyclerView mRvService;
    @BindView(R.id.tv_accessory_apply_state)
    TextView mTvAccessoryApplyState;
    @BindView(R.id.ll_accessory)
    LinearLayout mLlAccessory;
    @BindView(R.id.tv_service_apply_state)
    TextView mTvServiceApplyState;
    @BindView(R.id.ll_service)
    LinearLayout mLlService;
    @BindView(R.id.tv_beyond_state)
    TextView mTvBeyondState;
    @BindView(R.id.tv_beyond_application)
    TextView mTvBeyondApplication;
    @BindView(R.id.tv_range)
    TextView mTvRange;
    @BindView(R.id.iv_range_one)
    ImageView mIvRangeOne;
    @BindView(R.id.iv_range_two)
    ImageView mIvRangeTwo;
    @BindView(R.id.ll_approve_beyond_money)
    LinearLayout mLlApproveBeyondMoney;
    private String OrderID;
    private WorkOrder.DataBean data;
    private ReturnAccessoryAdapter returnAccessoryAdapter;
    private GServiceAdapter gServiceAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_work_order_details;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initView() {
        mTvActionbarTitle.setText("详情页");
        OrderID = getIntent().getStringExtra("OrderID");
        mPresenter.GetOrderInfo(OrderID);
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
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {
        switch (baseResult.getStatusCode()) {

            case 200:
                data = baseResult.getData();
                mTvWorkOrderNumber.setText(data.getOrderID());
                mTvOrderTime.setText(data.getAudDate().replace("T", " ")); //将T替换为空格
                mTvCauseOfIssue.setText(data.getMemo());
                mTvProductType.setText(data.getCategoryName() + "/" + data.getBrandName() + "/" + data.getSubCategoryName());

                if (data.getTypeID() == 1) {//维修
                    mTvType.setText(data.getTypeName() + "/" + data.getGuaranteeText());
                    mTvType.setBackgroundResource(R.color.color_custom_01);
//                    mll_return_information.setVisibility(View.VISIBLE);
//                    mll_service_process.setVisibility(View.GONE);

                } else {
                    mTvType.setText(data.getTypeName() + "/" + data.getGuaranteeText());
                    mTvType.setBackgroundResource(R.color.color_custom_04);
//                    mll_return_information.setVisibility(View.GONE);
//                    mll_service_process.setVisibility(View.VISIBLE);

                }
                mTvServiceAddress.setText(data.getAddress());
                mTvNumber.setText(data.getNum());
                if (data.getOrderAccessroyDetail() != null) {
                    returnAccessoryAdapter = new ReturnAccessoryAdapter(R.layout.item_returned, data.getOrderAccessroyDetail());
                    mRvReturnInformation.setLayoutManager(new LinearLayoutManager(mActivity));
                    mRvReturnInformation.setAdapter(returnAccessoryAdapter);
                    mLlAccessory.setVisibility(View.VISIBLE);
                    if ("0".equals(data.getAccessoryApplyState())){
                        mTvAccessoryApplyState.setText("审核中");
                        mTvAccessoryApplication.setVisibility(View.GONE);
                    }else if ("1".equals(data.getAccessoryApplyState())){
                        mTvAccessoryApplyState.setText("审核通过");
                        mTvAccessoryApplication.setVisibility(View.GONE);
                    }else{
                        mTvAccessoryApplyState.setText("被拒");
                        mTvAccessoryApplication.setVisibility(View.VISIBLE);
                    }
                }else{
                    mLlAccessory.setVisibility(View.GONE);
                }
                if (data.getOrderServiceDetail() != null) {
                    gServiceAdapter = new GServiceAdapter(R.layout.item_service, data.getOrderServiceDetail());
                    mRvService.setLayoutManager(new LinearLayoutManager(mActivity));
                    mRvService.setAdapter(gServiceAdapter);
                    mLlService.setVisibility(View.VISIBLE);
                    if ("0".equals(data.getServiceApplyState())){
                        mTvServiceApplyState.setText("审核中");
                        mTvServiceApplication.setVisibility(View.GONE);
                    }else if ("1".equals(data.getServiceApplyState())){
                        mTvServiceApplyState.setText("审核通过");
                        mTvServiceApplication.setVisibility(View.GONE);
                    }else{
                        mTvServiceApplyState.setText("被拒");
                        mTvServiceApplication.setVisibility(View.VISIBLE);
                    }
                }else{
                    mLlService.setVisibility(View.GONE);
                }
                if (data.getBeyondState()==null){
                    mLlApproveBeyondMoney.setVisibility(View.GONE);
                }else{
                    mLlApproveBeyondMoney.setVisibility(View.VISIBLE);
                    if ("0".equals(data.getBeyondState())){
                        mTvBeyondState.setText("审核中");
                        mTvBeyondApplication.setVisibility(View.GONE);
                    }else if ("1".equals(data.getBeyondState())){
                        mTvBeyondState.setText("审核通过");
                        mTvBeyondApplication.setVisibility(View.GONE);
                    }else{
                        mTvBeyondState.setText("被拒");
                        mTvBeyondApplication.setVisibility(View.VISIBLE);
                    }
                }
                break;

            default:
                break;
        }


    }

    @Override
    public void GetFactoryAccessory(BaseResult<GetFactoryData<Accessory>> baseResult) {

    }

    @Override
    public void GetFactoryService(BaseResult<GetFactoryData<Service>> baseResult) {

    }

    @Override
    public void AddOrderAccessory(BaseResult<Data> baseResult) {

    }

    @Override
    public void AddOrderService(BaseResult<Data> baseResult) {

    }

    @Override
    public void AddOrUpdateAccessoryServiceReturn(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void UpdateSendOrderUpdateTime(BaseResult<Data> baseResult) {

    }

    @Override
    public void GetOrderAccessoryByOrderID(BaseResult<List<GAccessory>> baseResult) {

    }

    @Override
    public void ServiceOrderPicUpload(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void ReuturnAccessoryPicUpload(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void FinishOrderPicUpload(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void OrderByondImgPicUpload(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void ApplyBeyondMoney(BaseResult<Data<String>> baseResult) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
