package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.Data3;
import com.ying.administrator.masterappdemo.entity.WXOfferQuery;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.QuoteContract;
import com.ying.administrator.masterappdemo.mvp.model.QuoteModel;
import com.ying.administrator.masterappdemo.mvp.presenter.QuotePresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.QuoteAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*报价*/
public class QuoteActivity extends BaseActivity<QuotePresenter, QuoteModel> implements View.OnClickListener, QuoteContract.View {
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
    @BindView(R.id.tv_payment_method)
    TextView mTvPaymentMethod;
    @BindView(R.id.tv_order_time)
    TextView mTvOrderTime;
    @BindView(R.id.tv_work_order_number)
    TextView mTvWorkOrderNumber;
    @BindView(R.id.iv_copy)
    ImageView mIvCopy;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.tv_cause_of_issue)
    TextView mTvCauseOfIssue;
    @BindView(R.id.tv_number)
    TextView mTvNumber;
    @BindView(R.id.tv_classification)
    TextView mTvClassification;
    @BindView(R.id.tv_description)
    TextView mTvDescription;
    @BindView(R.id.tv_product_type)
    TextView mTvProductType;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.rv_picture)
    RecyclerView mRvPicture;
    @BindView(R.id.et_price)
    EditText mEtPrice;
    @BindView(R.id.et_reason)
    EditText mEtReason;
    @BindView(R.id.btn_sure)
    Button mBtnSure;
    @BindView(R.id.tv_service_type)
    TextView mTvServiceType;
    private String orderId;
    private WorkOrder.DataBean data;
    private List<String> prictureList = new ArrayList<>();
    private QuoteAdapter adapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_quote;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvActionbarTitle.setText("报价详情");
        orderId = getIntent().getStringExtra("orderId");
        mPresenter.GetOrderInfo(orderId);

    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
        mBtnSure.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_return:
                finish();
                break;
            case R.id.btn_sure:
                String price = mEtPrice.getText().toString();
                String reason = mEtReason.getText().toString();
                if (price.isEmpty()) {
                    ToastUtils.showShort("请输入维修价格");
                    return;
                } else {
                    mPresenter.WXOrderOffer(data.getSendUser(), price, orderId, reason);
                }
                break;
        }
    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                data = baseResult.getData();
                mTvServiceType.setText(data.getTypeName());
                mTvPaymentMethod.setText("客户付款");
                mTvOrderTime.setText(data.getCreateDate().replace("T", " "));
                mTvWorkOrderNumber.setText(data.getOrderID());
                mTvCauseOfIssue.setText(data.getSubCategoryName());
                mTvNumber.setText(data.getNum());
                mTvProductType.setText(data.getMemo());
                String[] picture = data.getPicture().split(",");
                for (int i = 0; i < picture.length; i++) {
                    prictureList.add(picture[i]);
                }
                adapter = new QuoteAdapter(R.layout.item_quote_picture, prictureList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                mRvPicture.setLayoutManager(linearLayoutManager);
                mRvPicture.setAdapter(adapter);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(mActivity, PhotoViewActivity.class);
                        intent.putExtra("PhotoUrl", Config.Leave_quote_URL + prictureList.get(position));
                        startActivity(intent);
                    }
                });
                mPresenter.WXOfferQuery(data.getSendUser(),"",orderId,"");
                break;
        }
    }

    @Override
    public void WXOrderOffer(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()){
                    ToastUtils.showShort("报价成功");
//                    finish();
                    mPresenter.GetOrderInfo(orderId);
                }else {
                    ToastUtils.showShort(baseResult.getData().getItem2());
                }

                break;
        }
    }

    @Override
    public void WXOfferQuery(BaseResult<Data3<List<WXOfferQuery>>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().getItem2().size()==0){
                    return;
                }else {
                    mEtPrice.setText(baseResult.getData().getItem2().get(0).getPrice()+"");
                    mEtReason.setText(baseResult.getData().getItem2().get(0).getReason());
                    mBtnSure.setVisibility(View.GONE);
                }
                break;
        }
    }
}
