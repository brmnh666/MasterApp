package com.ying.administrator.masterappdemo.v3.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vondear.rxui.view.dialog.RxDialogScaleView;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.v3.adapter.AccessoriesAdapter;
import com.ying.administrator.masterappdemo.v3.adapter.AccessoriesPictureAdapter;
import com.ying.administrator.masterappdemo.v3.adapter.ServicesAdapter;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.AccessoriesDetailsPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.AccessoriesDetailsContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.AccessoriesDetailsModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccessoriesDetailsActivity extends BaseActivity<AccessoriesDetailsPresenter, AccessoriesDetailsModel> implements View.OnClickListener, AccessoriesDetailsContract.View {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.qr_pro_pro)
    SeekBar mQrProPro;
    @BindView(R.id.tv_outbound_logistics)
    TextView mTvOutboundLogistics;
    @BindView(R.id.ll_outbound_logistics)
    LinearLayout mLlOutboundLogistics;
    @BindView(R.id.tv_return_logistics)
    TextView mTvReturnLogistics;
    @BindView(R.id.ll_return_logistics)
    LinearLayout mLlReturnLogistics;
    @BindView(R.id.rv_accessories)
    RecyclerView mRvAccessories;
    @BindView(R.id.rv_accessories_picture)
    RecyclerView mRvAccessoriesPicture;
    @BindView(R.id.ll_accessories)
    LinearLayout mLlAccessories;
    @BindView(R.id.rv_services)
    RecyclerView mRvServices;
    @BindView(R.id.ll_services)
    LinearLayout mLlServices;
    @BindView(R.id.ll_accessories_picture)
    LinearLayout mLlAccessoriesPicture;
    private String orderId;
    private WorkOrder.DataBean data;
    private AccessoriesAdapter accessoriesAdapter;
    private ServicesAdapter servicesAdapter;
    private List<String> list=new ArrayList<>();
    private AccessoriesPictureAdapter accessoriesPictureAdapter;
    private Intent intent;
    private SimpleTarget<Bitmap> simpleTarget;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_accessories_details;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("配件详情");
        orderId = getIntent().getStringExtra("id");
        mQrProPro.setClickable(false);
        mQrProPro.setEnabled(false);
//        mSeekBar.setSelected(false);
//        mSeekBar.setFocusable(false);

        mPresenter.GetOrderInfo(orderId);
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mLlOutboundLogistics.setOnClickListener(this);
        mLlReturnLogistics.setOnClickListener(this);
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
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_outbound_logistics:
                intent = new Intent(mActivity,LogisticsActivity.class);
                intent.putExtra("number",data.getExpressNo()+"");
                startActivity(intent);
                break;
            case R.id.ll_return_logistics:
                intent = new Intent(mActivity,LogisticsActivity.class);
                intent.putExtra("number",data.getReturnAccessoryMsg()+"");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData() != null) {
                    data = baseResult.getData();
//                    mTvNumbering.setText(data.getOrderID());
//                    mTvCreationTime.setText(data.getCreateDate().replace("T", " "));
                    if (data.getExpressNo() != null) {
//                        mTvStatus.setText("已发件");
                        mQrProPro.setProgress(25);
                    }
                    if ("8".equals(data.getState())) {
//                        mTvStatus.setText("已签收");
                        mQrProPro.setProgress(50);
                    }
                    if (data.getReturnAccessoryMsg() != null) {
//                        mTvStatus.setText("已回寄");
                        mQrProPro.setProgress(75);
                    }
                    if ("7".equals(data.getState())) {
//                        mTvStatus.setText("已完成");
                        mQrProPro.setProgress(100);
                    }
                    if ("".equals(data.getExpressNo()) || data.getExpressNo() == null) {
                        mTvOutboundLogistics.setText("暂无物流消息");
                    } else {
                        mTvOutboundLogistics.setText(data.getExpressNo());
                    }

                    if ("".equals(data.getReturnAccessoryMsg()) || data.getReturnAccessoryMsg() == null) {
                        mTvReturnLogistics.setText("暂无物流消息");
                    } else {
                        mTvReturnLogistics.setText(data.getReturnAccessoryMsg());
                    }

                    if (data.getOrderAccessroyDetail().size() > 0) {
                        mLlAccessories.setVisibility(View.VISIBLE);
                        mLlAccessoriesPicture.setVisibility(View.VISIBLE);
                        for (int i = 0; i < data.getOrderAccessroyDetail().size(); i++) {
                            if (i==0){
                                list.add(data.getOrderAccessroyDetail().get(i).getPhoto1());
                            }
                            list.add(data.getOrderAccessroyDetail().get(i).getPhoto2());
                        }
                    } else {
                        mLlAccessories.setVisibility(View.GONE);
                        mLlAccessoriesPicture.setVisibility(View.GONE);
                    }

                    if (data.getOrderServiceDetail().size() > 0) {
                        mLlServices.setVisibility(View.VISIBLE);
                    } else {
                        mLlServices.setVisibility(View.GONE);
                    }
                    accessoriesAdapter = new AccessoriesAdapter(R.layout.v3_item_accessories, data.getOrderAccessroyDetail());
                    mRvAccessories.setLayoutManager(new LinearLayoutManager(mActivity));
                    mRvAccessories.setAdapter(accessoriesAdapter);

                    servicesAdapter = new ServicesAdapter(R.layout.v3_item_accessories, data.getOrderServiceDetail());
                    mRvServices.setLayoutManager(new LinearLayoutManager(mActivity));
                    mRvServices.setAdapter(servicesAdapter);

                    accessoriesPictureAdapter = new AccessoriesPictureAdapter(R.layout.v3_item_accessories_picture,list);
                    mRvAccessoriesPicture.setLayoutManager(new GridLayoutManager(mActivity,3));
                    mRvAccessoriesPicture.setAdapter(accessoriesPictureAdapter);
                    accessoriesPictureAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                            switch (view.getId()){
                                case R.id.iv_picture:
                                    scaleview("https://img.xigyu.com/Pics/Accessory/" +  list.get(position));
                                    break;
                            }
                        }
                    });
                }
                break;
        }
    }

    public void scaleview(String url) {
        simpleTarget = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<?
                    super Bitmap> transition) {
                RxDialogScaleView rxDialogScaleView = new RxDialogScaleView(mActivity);
                rxDialogScaleView.setImage(resource);
                rxDialogScaleView.show();
            }
        };

        Glide.with(mActivity)
                .asBitmap()
                .load(url)
                .into(simpleTarget);
    }
}
