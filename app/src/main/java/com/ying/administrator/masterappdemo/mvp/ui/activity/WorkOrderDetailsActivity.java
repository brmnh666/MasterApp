package com.ying.administrator.masterappdemo.mvp.ui.activity;

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
import com.ying.administrator.masterappdemo.entity.GetFactorySeviceData;
import com.ying.administrator.masterappdemo.entity.Service;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.PendingOrderContract;
import com.ying.administrator.masterappdemo.mvp.model.PendingOrderModel;
import com.ying.administrator.masterappdemo.mvp.presenter.PendingOrderPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.ReturnAccessoryAdapter;

import java.util.List;

import butterknife.BindView;

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
    @BindView(R.id.rv_return)
    RecyclerView mRvReturn;
    private String OrderID;
    private WorkOrder.DataBean data;
    private ReturnAccessoryAdapter returnAccessoryAdapter;

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
        OrderID =getIntent().getStringExtra("OrderID");
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
                mTvProductType.setText(data.getCategoryName() + "/" + data.getBrandName() + "/" + data.getProductType());

                if (data.getTypeID() == 1) {//维修
                    mTvType.setText("维修");
                    mTvType.setBackgroundResource(R.color.color_custom_01);
//                    mll_return_information.setVisibility(View.VISIBLE);
//                    mll_service_process.setVisibility(View.GONE);

                } else {
                    mTvType.setText("安装");
                    mTvType.setBackgroundResource(R.color.color_custom_04);
//                    mll_return_information.setVisibility(View.GONE);
//                    mll_service_process.setVisibility(View.VISIBLE);

                }
                mTvServiceAddress.setText(data.getAddress());
                mTvNumber.setText(data.getNum());
                if (data.getOrderAccessroyDetail() == null) {
                    return;
                }
                returnAccessoryAdapter = new ReturnAccessoryAdapter(R.layout.item_returned,data.getOrderAccessroyDetail());
                mRvReturnInformation.setLayoutManager(new LinearLayoutManager(mActivity));
                mRvReturnInformation.setAdapter(returnAccessoryAdapter);
                break;

            default:
                break;
        }


    }

    @Override
    public void GetFactoryAccessory(BaseResult<GetFactoryData<Accessory>> baseResult) {

    }

    @Override
    public void GetFactoryService(BaseResult<GetFactorySeviceData<Service>> baseResult) {

    }

    @Override
    public void AddOrderAccessory(BaseResult<Data> baseResult) {

    }

    @Override
    public void AddOrderService(BaseResult<Data> baseResult) {

    }

    @Override
    public void AddOrUpdateAccessoryServiceReturn(BaseResult<Data> baseResult) {

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
}
