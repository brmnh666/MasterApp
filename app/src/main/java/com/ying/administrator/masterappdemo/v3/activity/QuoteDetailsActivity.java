package com.ying.administrator.masterappdemo.v3.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.ying.administrator.masterappdemo.mvp.ui.activity.PhotoViewActivity;
import com.ying.administrator.masterappdemo.v3.adapter.QuoteAdapter;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.QuoteDetailsPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.QuoteDetailsContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.QuoteDetailsModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//报价详情
public class QuoteDetailsActivity extends BaseActivity<QuoteDetailsPresenter, QuoteDetailsModel> implements View.OnClickListener, QuoteDetailsContract.View {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
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
    @BindView(R.id.rc_pivture)
    RecyclerView mRcPivture;
    @BindView(R.id.et_quote_description)
    EditText mEtQuoteDescription;
    @BindView(R.id.ll_call)
    LinearLayout mLlCall;
    @BindView(R.id.tv_know)
    TextView mTvKnow;
    @BindView(R.id.tv_confirm_offer)
    TextView mTvConfirmOffer;
    @BindView(R.id.et_price)
    EditText mEtPrice;
    private List<String> list = new ArrayList<>();
    private QuoteAdapter adapter;
    private String orderId;
    private WorkOrder.DataBean data;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_quote_details;
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initView() {
        mTvTitle.setText("报价详情");
        mLlCustomerService.setVisibility(View.VISIBLE);
        orderId = getIntent().getStringExtra("id");

        mPresenter.GetOrderInfo(orderId);
        myClipboard = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mTvConfirmOffer.setOnClickListener(this);
        mTvCopy.setOnClickListener(this);
        mLlCall.setOnClickListener(this);
        mLlCustomerService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_confirm_offer:
                String price = mEtPrice.getText().toString();
                String reason = mEtQuoteDescription.getText().toString();
                if (price.isEmpty()) {
                    ToastUtils.showShort("请输入维修价格");
                    return;
                } else {
                    mPresenter.WXOrderOffer(data.getSendUser(), price, orderId, reason);
                }
                break;
            case R.id.tv_copy:
                myClip = ClipData.newPlainText("", "下单厂家："+data.getInvoiceName() + "\n"
                        +"工单号："+data.getOrderID() + "\n"
                        +"下单时间："+data.getCreateDate() + "\n"
                        +"用户信息："+data.getUserName()+" "+data.getPhone() + "\n"
                        +"用户地址："+data.getAddress() + "\n"
                        +"产品信息："+data.getProductType() + "\n"
                        +"售后类型："+data.getGuaranteeText() + "\n"
                        +"服务类型："+data.getTypeName() + "\n"
                );
                myClipboard.setPrimaryClip(myClip);
                ToastUtils.showShort("复制成功");
                break;
            case R.id.ll_customer_service:
            case R.id.ll_call:
                call("tel:" + "4006262365");
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
                data = baseResult.getData();
                mTvType.setText("用户发单/" + data.getTypeName());
//                mTvPaymentMethod.setText("客户付款");
                mTvNumbering.setText(data.getOrderID());
                mTvBillingTime.setText(data.getCreateDate().replace("T", " "));
                mTvDescription.setText("描述：" + data.getMemo());

                String[] picture = data.getPicture().split(",");
                for (int i = 0; i < picture.length; i++) {
                    list.add(picture[i]);
                }
                adapter = new QuoteAdapter(R.layout.v3_item_quote, list);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                mRcPivture.setLayoutManager(linearLayoutManager);
                mRcPivture.setAdapter(adapter);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(mActivity, PhotoViewActivity.class);
                        intent.putExtra("PhotoUrl", Config.Leave_quote_URL + list.get(position));
                        startActivity(intent);
                    }
                });
                mPresenter.WXOfferQuery(data.getSendUser(), "", orderId, "");
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
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getItem2().size() == 0) {
                    return;
                } else {
                    mEtPrice.setText(baseResult.getData().getItem2().get(0).getPrice() + "");
                    mEtQuoteDescription.setText(baseResult.getData().getItem2().get(0).getReason());
                    mTvConfirmOffer.setVisibility(View.GONE);
                }
                break;
        }
    }
}
