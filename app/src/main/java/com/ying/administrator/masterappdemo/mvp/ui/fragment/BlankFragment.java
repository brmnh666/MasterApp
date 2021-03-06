package com.ying.administrator.masterappdemo.mvp.ui.fragment;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.m7.imkfsdk.MainActivity;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Article;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.ProductList;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.AllWorkOrdersContract;
import com.ying.administrator.masterappdemo.mvp.model.AllWorkOrdersModel;
import com.ying.administrator.masterappdemo.mvp.presenter.AllWorkOrdersPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.activity.CartActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.GoodsDetailActivity2;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.MallAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseFragment;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends BaseLazyFragment<AllWorkOrdersPresenter, AllWorkOrdersModel> implements View.OnClickListener, AllWorkOrdersContract.View {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";
    @BindView(R.id.ll_online_consultation)
    LinearLayout mLlOnlineConsultation;
    @BindView(R.id.ll_contact_customer_Service)
    LinearLayout mLlContactCustomerService;
    @BindView(R.id.tv_comprehensive)
    TextView mTvComprehensive;
    @BindView(R.id.tv_sales_volume)
    TextView mTvSalesVolume;
    @BindView(R.id.ll_price)
    LinearLayout mLlPrice;
    @BindView(R.id.tv_new_product)
    TextView mTvNewProduct;
    @BindView(R.id.rv_mall)
    RecyclerView mRvMall;
    Unbinder unbinder;
    @BindView(R.id.main_collapsing)
    CollapsingToolbarLayout mMainCollapsing;
    @BindView(R.id.appbarlayout)
    AppBarLayout mAppbarlayout;
    @BindView(R.id.nested)
    NestedScrollView mNested;
    @BindView(R.id.cdl)
    CoordinatorLayout mCdl;
    @BindView(R.id.img_up)
    ImageView mImgUp;
    @BindView(R.id.cv_up)
    CardView mCvUp;
    @BindView(R.id.iv_cart)
    ImageView mIvCart;

    private String mContentText;
    private UserInfo.UserInfoDean userInfo;
    private List<ProductList> lists = new ArrayList<>();

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment BlankFragment.
     */
    public static BlankFragment newInstance(String param1) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContentText = getArguments().getString(ARG_SHOW_TEXT);
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_blank;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        SPUtils spUtils = SPUtils.getInstance("token");
        String userID = spUtils.getString("userName"); //获取用户id
        mPresenter.GetUserInfoList(userID, "1");
        mCvUp.setVisibility(View.GONE);
//        mNested.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                if (mMainCollapsing.getHeight() - scrollY <= 0) {
//                    mCvUp.setVisibility(View.VISIBLE);
//                } else {
//                    mCvUp.setVisibility(View.GONE);
//                }
//            }
//        });

        for (int i = 0; i < 10; i++) {
            lists.add(new ProductList());
        }
        MallAdapter mallAdapter = new MallAdapter(R.layout.item_mall, lists);
        mRvMall.setLayoutManager(new GridLayoutManager(mActivity, 2));
        mRvMall.setAdapter(mallAdapter);
        mallAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_goods:
                        startActivity(new Intent(mActivity, GoodsDetailActivity2.class));
                        break;
                }
            }
        });
    }

    @Override
    protected void setListener() {
        mLlOnlineConsultation.setOnClickListener(this);
        mLlContactCustomerService.setOnClickListener(this);
        mIvCart.setOnClickListener(this);
        mImgUp.setOnClickListener(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_online_consultation:
//                startActivity(new Intent(getContext(), IntelligentCustomerServiceActivity.class));
                Intent intent1 = new Intent(mActivity, MainActivity.class);
//                intent1.putExtra("goodsName",result.getProduct().getProductName());
//                intent1.putExtra("goodsPricture",result.getProduct().getImagePath().get(0));
//                intent1.putExtra("goodsPrice","￥" + result.getProduct().getMinSalePrice());
//                intent1.putExtra("goodsURL","http://seller.xigyu.com/product/detail/"+result.getProduct().getProductId());
                intent1.putExtra("userName", userInfo.getNickName());
                intent1.putExtra("userId", userInfo.getUserID());
                intent1.putExtra("userPic", userInfo.getAvator());
                startActivity(intent1);
                break;
            case R.id.ll_contact_customer_Service:
                final CommonDialog_Home dialog = new CommonDialog_Home(getActivity());
                dialog.setMessage("是否拨打电话给客服")
                        //.setImageResId(R.mipmap.ic_launcher)
                        .setTitle("提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {//拨打电话
                        dialog.dismiss();
                        call("tel:" + "18074208209");
                    }

                    @Override
                    public void onNegtiveClick() {//取消
                        dialog.dismiss();
                        // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.iv_cart:
            case R.id.img_up:
                startActivity(new Intent(mActivity, CartActivity.class));
                break;
        }

    }

    @Override
    public void WorkerGetOrderList(BaseResult<WorkOrder> baseResult) {

    }

    @Override
    public void UpdateSendOrderState(BaseResult<Data> baseResult) {

    }

    @Override
    public void GetUserInfo(BaseResult<String> baseResult) {

    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                userInfo = baseResult.getData().getData().get(0);
                break;
        }
    }

    @Override
    public void UpdateSendOrderUpdateTime(BaseResult<Data> baseResult) {

    }

    @Override
    public void AddOrderSuccess(BaseResult<Data> baseResult) {

    }

    @Override
    public void AddOrderfailureReason(BaseResult<Data> baseResult) {

    }

    @Override
    public void OrderByondImgPicUpload(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void ApplyBeyondMoney(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetListCategoryContentByCategoryID(BaseResult<Article> baseResult) {

    }
}
