package com.ying.administrator.masterappdemo.v3.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.entity.accessoryDataBean;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.v3.adapter.AccessoriesPictureAdapter;
import com.ying.administrator.masterappdemo.v3.adapter.V4_AccessoriesAdapter;
import com.ying.administrator.masterappdemo.v3.bean.DeleteAccessoryResult;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.AccessoriesDetailsPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.AccessoriesDetailsContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.AccessoriesDetailsModel;
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    @BindView(R.id.ll_accessories_picture)
    LinearLayout mLlAccessoriesPicture;
    @BindView(R.id.tv_acc_state)
    TextView mTvAccState;
    @BindView(R.id.btn_add)
    Button mBtnAdd;
    private List<String> list = new ArrayList<>();
    private AccessoriesPictureAdapter accessoriesPictureAdapter;
    private Intent intent;
    private SimpleTarget<Bitmap> simpleTarget;
    private V4_AccessoriesAdapter accessoriesAdapter;
    private View popupWindow_view;
    private PopupWindow mPopupWindow;
    private accessoryDataBean data;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_accessories_details;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        data = (accessoryDataBean) getIntent().getSerializableExtra("acc_data");
        mTvTitle.setText("配件单详情（"+data.getAccessoryNo()+"）");
        accessoriesAdapter = new V4_AccessoriesAdapter(R.layout.v3_item_accessories, data.getAccessoryDetailModels());
        mRvAccessories.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvAccessories.setAdapter(accessoriesAdapter);
        accessoriesAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()){
                    case R.id.ll_delete:
                        final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
                        dialog.setMessage("是否确认删除"+ data.getAccessoryDetailModels().get(position).getFAccessoryName())
                                .setTitle("提示")
                                .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {
                                mPresenter.DeleteAccessory(data.getAccessoryDetailModels().get(position).getAccessoryID()+"");
                                dialog.dismiss();
                            }

                            @Override
                            public void onNegtiveClick() {//取消
                                dialog.dismiss();
                            }
                        }).show();
                        break;
                }
            }
        });
        list.clear();
        for (int i = 0; i < data.getOrderAttachments().size(); i++) {
            list.add(Config.Accessory_URL +data.getOrderAttachments().get(i).getUrl());
        }
        accessoriesPictureAdapter=new AccessoriesPictureAdapter(R.layout.v3_item_accessories_picture,list);
        mRvAccessoriesPicture.setLayoutManager(new GridLayoutManager(mActivity,5));
        mRvAccessoriesPicture.setAdapter(accessoriesPictureAdapter);
        mQrProPro.setClickable(false);
        mQrProPro.setEnabled(false);
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mLlOutboundLogistics.setOnClickListener(this);
        mLlReturnLogistics.setOnClickListener(this);
        mBtnAdd.setOnClickListener(this);
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
            case R.id.btn_add:
                showPopupWindow();
                break;
            case R.id.ll_outbound_logistics:
                intent = new Intent(mActivity, LogisticsActivity.class);
//                intent.putExtra("number", data.getExpressNo() + "");
                startActivity(intent);
                break;
            case R.id.ll_return_logistics:
                intent = new Intent(mActivity, LogisticsActivity.class);
//                intent.putExtra("number", data.getReturnAccessoryMsg() + "");
                startActivity(intent);
                break;
        }
    }
    /**
     * 弹出Popupwindow,选择厂家寄件还是自购件
     */
    public void showPopupWindow() {
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.cj_or_zg_layout, null);
        Button btn_cj = popupWindow_view.findViewById(R.id.btn_cj);
        Button btn_zg = popupWindow_view.findViewById(R.id.btn_zg);
        Button btn_complete = popupWindow_view.findViewById(R.id.btn_complete);
        Button btn_cancel = popupWindow_view.findViewById(R.id.btn_cancel);

        btn_cj.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                intent = new Intent(mActivity, ApplyAccActivity.class);
                intent.putExtra("OrderID", data.getOrderID()+"");
//                intent.putExtra("prodID", data.getOrderProdcutID()+"");
                intent.putExtra("list_prod", data);
//                intent.putExtra("prodName", data.getSubCategoryName()+"(编号："+ data.getOrderProdcutID()+")");
//                intent.putExtra("SubCategoryID", data.getProductTypeID()+"");
                intent.putExtra("cj_or_zg", "厂寄");
                startActivity(intent);
                mPopupWindow.dismiss();
            }
        });
        btn_zg.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                intent = new Intent(mActivity, ApplyAccActivity.class);
                intent.putExtra("OrderID", data.getOrderID()+"");
//                intent.putExtra("prodID", data.getOrderProdcutID()+"");
                intent.putExtra("list_prod", data);
//                intent.putExtra("prodName", data.getSubCategoryName()+"(编号："+ data.getOrderProdcutID()+")");
//                intent.putExtra("SubCategoryID", data.getProductTypeID()+"");
                intent.putExtra("cj_or_zg", "自购");
                startActivity(intent);
                mPopupWindow.dismiss();
            }
        });
        //直接完结工单
        btn_complete.setVisibility(View.GONE);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                MyUtils.setWindowAlpa(mActivity, false);
            }
        });
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(popupWindow_view, Gravity.BOTTOM, 0, 0);
        }
        MyUtils.setWindowAlpa(mActivity, true);
    }
    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
//                for (int i = 0; i < baseResult.getData().getOrderProductModels().size(); i++) {
//                    if (baseResult.getData().getOrderProductModels().get(i).getOrderProdcutID()== data.getOrderProdcutID()) {
//                        data = baseResult.getData().getOrderProductModels().get(i);
//                        accessoriesAdapter.setNewData(data.getAccessoryData());
//                    }
//                }
                break;
        }
    }

    @Override
    public void DeleteAccessory(DeleteAccessoryResult baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isStatus()){
                    MyUtils.showToast(mActivity,"删除成功");
                    EventBus.getDefault().post(21);
                }else{
                    MyUtils.showToast(mActivity,baseResult.getData().getMsg());
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
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(Integer name) {
        switch (name) {
            case 21:
                mPresenter.GetOrderInfo(data.getOrderID()+"");
                break;
        }
    }
}
