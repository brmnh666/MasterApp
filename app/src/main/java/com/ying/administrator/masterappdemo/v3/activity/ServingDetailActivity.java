package com.ying.administrator.masterappdemo.v3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.ServingDetailPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.ServingDetailContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.ServingDetailModel;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServingDetailActivity extends BaseActivity<ServingDetailPresenter, ServingDetailModel> implements View.OnClickListener, ServingDetailContract.View {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.tv_ticket_tracking)
    TextView mTvTicketTracking;
    @BindView(R.id.tv_reservation_again)
    TextView mTvReservationAgain;
    @BindView(R.id.tv_add_record)
    TextView mTvAddRecord;
    @BindView(R.id.tv_sign_in)
    TextView mTvSignIn;
    @BindView(R.id.tv_state)
    TextView mTvState;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.tv_status)
    TextView mTvStatus;
    @BindView(R.id.tv_numbering)
    TextView mTvNumbering;
    @BindView(R.id.tv_payment)
    TextView mTvPayment;
    @BindView(R.id.tv_billing_time)
    TextView mTvBillingTime;
    @BindView(R.id.tv_copy)
    TextView mTvCopy;
    @BindView(R.id.tv_description)
    TextView mTvDescription;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.ll_telephone)
    LinearLayout mLlTelephone;
    @BindView(R.id.tv_change_address)
    TextView mTvChangeAddress;
    @BindView(R.id.ll_change_address)
    LinearLayout mLlChangeAddress;
    @BindView(R.id.tv_platform_price)
    TextView mTvPlatformPrice;
    @BindView(R.id.ll_platform_price)
    LinearLayout mLlPlatformPrice;
    @BindView(R.id.iv_picture)
    ImageView mIvPicture;
    @BindView(R.id.tv_product_name)
    TextView mTvProductName;
    @BindView(R.id.tv_specifications)
    TextView mTvSpecifications;
    @BindView(R.id.tv_maintenance_information)
    TextView mTvMaintenanceInformation;
    @BindView(R.id.ll_maintenance_information)
    LinearLayout mLlMaintenanceInformation;
    @BindView(R.id.ll_call)
    LinearLayout mLlCall;
    @BindView(R.id.tv_success)
    TextView mTvSuccess;
    @BindView(R.id.ll_reservation)
    LinearLayout mLlReservation;
    @BindView(R.id.tv_upload)
    TextView mTvUpload;
    @BindView(R.id.tv_distance)
    TextView mTvDistance;
    @BindView(R.id.ll_accessories_details)
    LinearLayout mLlAccessoriesDetails;
    private String orderId;
    private WorkOrder.DataBean data;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_serving_detail;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("工单详情");
        orderId = getIntent().getStringExtra("id");
        mPresenter.GetOrderInfo(orderId);
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mTvUpload.setOnClickListener(this);
        mLlTelephone.setOnClickListener(this);
        mLlAccessoriesDetails.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_telephone:
                call("tel:" + data.getPhone());
                break;
            case R.id.tv_upload:
                Intent intent = new Intent(mActivity, ApplicationAccessoriesActivity.class);
                intent.putExtra("id", orderId);
                intent.putExtra("SubCategoryID", data.getProductTypeID());
                intent.putExtra("QuaMoney", data.getQuaMoney() + "");
                intent.putExtra("OrderMoney", data.getOrderMoney() + "");
                intent.putExtra("BeyondMoney", data.getBeyondMoney() + "");
                intent.putExtra("BeyondState", data.getBeyondState() + "");
                startActivity(intent);
                break;
            case R.id.ll_accessories_details:
                Intent intent1=new Intent(mActivity,AccessoriesDetailsActivity.class);
                intent1.putExtra("id",orderId);
                startActivity(intent1);
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
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData() != null) {
                    data = baseResult.getData();
                    if ("Y".equals(data.getExtra()) && !"0".equals(data.getExtraTime())) {
                        mTvState.setText(data.getGuaranteeText() + "/" + data.getTypeName() + "/加急");
                    } else {
                        mTvState.setText(data.getGuaranteeText() + "/" + data.getTypeName());
                    }
                    mTvType.setText(data.getTypeName());
                    mTvStatus.setText(data.getStateStr());
                    mTvNumbering.setText(data.getOrderID());
                    if (("Y").equals(data.getGuarantee())) {
                        mTvPayment.setText("平台代付");

                    } else {
                        mTvPayment.setText("客户付款");

                    }

                    mTvBillingTime.setText(data.getCreateDate().replace("T", " "));
                    mTvDescription.setText("描述：" + data.getMemo());
                    mTvName.setText(data.getUserName() + "    " + data.getPhone());
                    mTvAddress.setText(data.getAddress());
                    mTvDistance.setText("线路里程 " + data.getDistance() + "公里");
                    if (data.getOrderServiceDetail().size()>0||data.getOrderAccessroyDetail().size()>0){
                        mLlAccessoriesDetails.setVisibility(View.VISIBLE);
                    }else {
                        mLlAccessoriesDetails.setVisibility(View.GONE);
                    }
                }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        if ("WorkOrderDetailsActivity".equals(name)){
            mLlAccessoriesDetails.setVisibility(View.VISIBLE);
        }
    }
}
