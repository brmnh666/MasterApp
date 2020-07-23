package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ethanhua.skeleton.SkeletonScreen;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.widget.AdderView;
import com.ying.administrator.masterappdemo.widget.CircleImageView;
import com.ying.administrator.masterappdemo.widget.GlideImageLoader;
import com.ying.administrator.masterappdemo.widget.IdeaScrollView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;

public class GoodsDetailActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.banner_goods)
    Banner mBannerGoods;
    @BindView(R.id.countdownview)
    CountdownView mCountdownview;
    @BindView(R.id.tv_only_left)
    TextView mTvOnlyLeft;
    @BindView(R.id.ll_limit)
    LinearLayout mLlLimit;
    @BindView(R.id.tv_good_money)
    TextView mTvGoodMoney;
    @BindView(R.id.tv_good_money_max)
    TextView mTvGoodMoneyMax;
    @BindView(R.id.tv_good_name)
    TextView mTvGoodName;
    @BindView(R.id.ll_share)
    LinearLayout mLlShare;
    @BindView(R.id.tv_express_delivery)
    TextView mTvExpressDelivery;
    @BindView(R.id.tv_sales_volume)
    TextView mTvSalesVolume;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_comment)
    TextView mTvComment;
    @BindView(R.id.tv_comment_two)
    TextView mTvCommentTwo;
    @BindView(R.id.ll_install_service)
    LinearLayout mLlInstallService;
    @BindView(R.id.tv_select)
    TextView mTvSelect;
    @BindView(R.id.ll_select)
    LinearLayout mLlSelect;
    @BindView(R.id.tv_service)
    TextView mTvService;
    @BindView(R.id.ll_service)
    LinearLayout mLlService;
    @BindView(R.id.tv_specification)
    TextView mTvSpecification;
    @BindView(R.id.ll_specification)
    LinearLayout mLlSpecification;
    @BindView(R.id.tv_parameter)
    TextView mTvParameter;
    @BindView(R.id.ll_parameter)
    LinearLayout mLlParameter;
    @BindView(R.id.one)
    LinearLayout mOne;
    @BindView(R.id.tv_baby_evaluation)
    TextView mTvBabyEvaluation;
    @BindView(R.id.iv_picture)
    CircleImageView mIvPicture;
    @BindView(R.id.tv_username)
    TextView mTvUsername;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.ll_evaluation)
    LinearLayout mLlEvaluation;
    @BindView(R.id.ll_ask_everyone)
    LinearLayout mLlAskEveryone;
    @BindView(R.id.tv_question)
    TextView mTvQuestion;
    @BindView(R.id.tv_answer)
    TextView mTvAnswer;
    @BindView(R.id.iv_store_picture)
    ImageView mIvStorePicture;
    @BindView(R.id.tv_store_name)
    TextView mTvStoreName;
    @BindView(R.id.tv_store_num)
    TextView mTvStoreNum;
    @BindView(R.id.tv_baby_description_score)
    TextView mTvBabyDescriptionScore;
    @BindView(R.id.tv_baby_description)
    TextView mTvBabyDescription;
    @BindView(R.id.tv_seller_service_score)
    TextView mTvSellerServiceScore;
    @BindView(R.id.tv_seller_service)
    TextView mTvSellerService;
    @BindView(R.id.tv_logistics_services_score)
    TextView mTvLogisticsServicesScore;
    @BindView(R.id.tv_logistics_services)
    TextView mTvLogisticsServices;
    @BindView(R.id.tv_all_goods)
    TextView mTvAllGoods;
    @BindView(R.id.tv_go_shopping)
    TextView mTvGoShopping;
    @BindView(R.id.ll_shop_recommendation)
    LinearLayout mLlShopRecommendation;
    @BindView(R.id.rv_shop_recommendation)
    RecyclerView mRvShopRecommendation;
    @BindView(R.id.two)
    LinearLayout mTwo;
    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.three)
    LinearLayout mThree;
    @BindView(R.id.rv_recommend)
    RecyclerView mRvRecommend;
    @BindView(R.id.four)
    LinearLayout mFour;
    @BindView(R.id.ideaScrollView)
    IdeaScrollView mIdeaScrollView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_obtained)
    TextView mTvObtained;
    @BindView(R.id.img_shop)
    ImageView mImgShop;
    @BindView(R.id.tv_shop)
    TextView mTvShop;
    @BindView(R.id.ll_shop)
    LinearLayout mLlShop;
    @BindView(R.id.img_customer_service)
    ImageView mImgCustomerService;
    @BindView(R.id.tv_customer_service)
    TextView mTvCustomerService;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.img_collect)
    ImageView mImgCollect;
    @BindView(R.id.tv_collection)
    TextView mTvCollection;
    @BindView(R.id.ll_collect)
    LinearLayout mLlCollect;
    @BindView(R.id.tv_addcart)
    TextView mTvAddcart;
    @BindView(R.id.tv_buy)
    TextView mTvBuy;
    @BindView(R.id.ll_normal)
    LinearLayout mLlNormal;
    @BindView(R.id.tv_limit_buy)
    TextView mTvLimitBuy;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.radioGroup)
    RadioGroup mRadioGroup;
    @BindView(R.id.ll_cart)
    LinearLayout mLlCart;
    @BindView(R.id.ll_head_share)
    LinearLayout mLlHeadShare;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.headerParent)
    LinearLayout mHeaderParent;
    @BindView(R.id.RootView)
    FrameLayout mRootView;
    private SkeletonScreen skeletonScreen;
    private AdderView adderView;
    private int getinventory; //库存
    private View popupWindow_view;
    private PopupWindow mPopupWindow;
    //    private ChooseColorAdapter chooseColorAdapter;
//    private ChooseSizeAdapter chooseSizeAdapter;
//    private ChooseVersionAdapter chooseVersionAdapter;
//    private DetailResult result = new DetailResult();
//
//    private List<DetailResult.ProductAttributeInfolistBean> parameterList = new ArrayList<>();
//
//    private List<GetGoodSKu.SkuArrayBean> skuArray = new ArrayList<>();
////    @BindView(R.id.number_indicater)
////    NumberIndicater mNumberIndicater;
//
//    private ArrayList<Product> shopRecommendationList = new ArrayList<>();
    private boolean isStop = false;//线程是否停止
    private static int PAGER_TIOME = 5000;//间隔时间

    private boolean isNeedScrollTo = true;
    private float currentPercentage = 0;
    private PagerAdapter mAdapter;
    private String id;

    private String SkuId = "";
    private String skuId_color = "0";
    private String skuId_size = "0";
    private String skuId_version = "0";
    private String color_name = "";
    private String size_name = "";
    private String version_name = "";
    private String count = "1"; //数量

    private String VShopId = ""; //微店id用于进入店铺详情
    private String ShopId = "";

    private String Photourl;// 查看图片大图的地址
    private RadioGroup.OnCheckedChangeListener radioGroupListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            for (int i = 0; i < mRadioGroup.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) mRadioGroup.getChildAt(i);
                radioButton.setTextColor(radioButton.isChecked() ? getRadioCheckedAlphaColor(currentPercentage) : getRadioAlphaColor(currentPercentage));
                if (radioButton.isChecked() && isNeedScrollTo) {
                    mIdeaScrollView.setPosition(i);
                    if (i != 0) {
                        mRadioGroup.setAlpha(1);
                        mView.getBackground().setAlpha(255);
                        mToolbar.getBackground().setAlpha(255);
                        setRadioButtonTextColor(1);
                        mLlBack.getBackground().setAlpha(0);
                        mLlCart.getBackground().setAlpha(0);
                        mLlHeadShare.getBackground().setAlpha(0);
                    }
                }
            }
        }
    };
    private View view;
    private TextView tv_customersecurity;
    private TextView tv_sevendaynoreasonreturn;
    private TextView tv_timelyship;
    private TextView tv_submit;
    private AlertDialog serviceDialog;
    private LinearLayout ll_customersecurity;
    private LinearLayout ll_sevendaynoreasonreturn;
    private LinearLayout ll_timelyship;
//    private List<ShopCoupResult.CouponBean> couplist = new ArrayList<>();
//    private MineFragment.CustomShareListener mShareListener;
//    private ShareAction mShareAction;
//    private ShopCoupAdapter shopCoupAdapter;
//    private UserInfo.UserInfoDean userInfo;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_goods_detail;
    }

    @Override
    protected void initData() {


//        if (isLogin) {
//            mPresenter.GetUserInfoList(UserID, "1");
//        }
//        for (int i = 0; i < 6; i++) {
//            shopRecommendationList.add(new Product());
//        }
//        ShopRecommendationAdapter shopRecommendationAdapter = new ShopRecommendationAdapter(R.layout.item_shop_recommendation, shopRecommendationList);
//        mRvShopRecommendation.setLayoutManager(new GridLayoutManager(mActivity, 3));
//        mRvShopRecommendation.setAdapter(shopRecommendationAdapter);
//
//        ShopRecommendationAdapter shopRecommendationAdapter1 = new ShopRecommendationAdapter(R.layout.item_recommend, shopRecommendationList);
//        mRvRecommend.setLayoutManager(new GridLayoutManager(mActivity, 2));
//        mRvRecommend.setAdapter(shopRecommendationAdapter1);

        id = getIntent().getStringExtra("id");
        Intent i_getvalue = getIntent();
        String action = i_getvalue.getAction();

        if (Intent.ACTION_VIEW.equals(action)) {
            Uri uri = i_getvalue.getData();
            if (uri != null) {
                id = uri.getQueryParameter("ProductId");
                String age = uri.getQueryParameter("age");
            }
        }

//        mPresenter.GetSKUInfo(id);
//        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshLayout) {
//                mPresenter.GetSKUInfo(id);
//                mRefreshLayout.finishRefresh(1000);
//            }
//        });
//        UMShareConfig config = new UMShareConfig();
//        config.isNeedAuthOnGetUserInfo(true);
//        UMShareAPI.get(mActivity).setShareConfig(config);
//        mShareListener = new MineFragment.CustomShareListener(mActivity);


    }


    @Override
    protected void initView() {
//        skeletonScreen = Skeleton.bind(RootView)
//                .load(R.layout.activity_good_skeleton)
//                .duration(2000)
//                .color(R.color.shimmer_color)
//                .angle(10)
//                .show();

     /*   mStateLayout.changeState(StateFrameLayout.LOADING);
        //是否在展示内容布局的时候开启动画（200ms的Alpha动画）
        mStateLayout.enableContentAnim(false);

        //设置网络错误重试监听【不传netRetryId的话需要在对应布局中设置触发控件的id为android:id="@id/id_sfl_net_error_retry"】
        mStateLayout.setOnNetErrorRetryListener(new StateFrameLayout.OnNetErrorRetryListener() {
            @Override
            public void onNetErrorRetry() {
                //TODO 在这里相应重试操作
                mPresenter.GetSKUInfo(id);
            }
        });
        //设置空数据重试监听【不传emptyRetryId的话需要在对应布局中设置触发控件的id为android:id="@id/id_sfl_empty_retry"】
        mStateLayout.setOnEmptyRetryListener(new StateFrameLayout.OnEmptyRetryListener() {
            @Override
            public void onEmptyRetry() {
                //TODO 在这里相应重试操作
                mPresenter.GetSKUInfo(id);
            }
        });*/


        ArrayList<Integer> images = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            images.add(R.drawable.icon);
        }
        mBannerGoods.setImageLoader(new GlideImageLoader());
        mBannerGoods.setImages(images);
        mBannerGoods.setBannerStyle(BannerConfig.NUM_INDICATOR);
        mBannerGoods.setIndicatorGravity(BannerConfig.CENTER);
        mBannerGoods.setDelayTime(4000);

//        mBannerGoods.setOnBannerListener(new OnBannerListener() {
//            @Override
//            public void OnBannerClick(int position) {
//                Intent intent = new Intent(mActivity, PhotosViewActivity.class);
//                intent.putExtra("photo_list", (Serializable) images);
//                intent.putExtra("photo_position", position);
//                startActivity(intent);
//
//
//            }
//        });

        mBannerGoods.start();


        Rect rectangle = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);
        mIdeaScrollView.setViewPager(mBannerGoods, getMeasureHeight(mHeaderParent) - rectangle.top);
        mRadioGroup.setAlpha(0);
        mToolbar.getBackground().setAlpha(0);
        mView.getBackground().setAlpha(0);
        mRadioGroup.check(mRadioGroup.getChildAt(0).getId());

        ArrayList<Integer> araryDistance = new ArrayList<>();

        araryDistance.add(0);
        araryDistance.add(getMeasureHeight(mOne) - getMeasureHeight(mHeaderParent) + 50);
        araryDistance.add(getMeasureHeight(mOne) + getMeasureHeight(mTwo) - getMeasureHeight(mHeaderParent) + 50);
//        araryDistance.add(getMeasureHeight(mOne) + getMeasureHeight(mTwo) + getMeasureHeight(mThree) - getMeasureHeight(mHeaderParent));

        mIdeaScrollView.setArrayDistance(araryDistance);

        mIdeaScrollView.setOnScrollChangedColorListener(new IdeaScrollView.OnScrollChangedColorListener() {
            @Override
            public void onChanged(float percentage) {
//                int color = getAlphaColor(percentage > 0.5f ? 1.0f : percentage);
//                mHeaderParent.setBackgroundDrawable(new ColorDrawable(color));
                mRadioGroup.setAlpha((percentage > 0.5f ? 1.0f : percentage) * 255);
                mView.getBackground().setAlpha((int) ((percentage > 0.5f ? 1.0f : percentage) * 255));
                mToolbar.getBackground().setAlpha((int) ((percentage > 0.5f ? 1.0f : percentage) * 255));
                setRadioButtonTextColor(percentage);
                mLlBack.getBackground().setAlpha(255 - (int) ((percentage > 0.5f ? 1.0f : percentage) * 255));
                mLlCart.getBackground().setAlpha(255 - (int) ((percentage > 0.5f ? 1.0f : percentage) * 255));
                mLlHeadShare.getBackground().setAlpha(255 - (int) ((percentage > 0.5f ? 1.0f : percentage) * 255));
            }

            @Override
            public void onChangedFirstColor(float percentage) {

            }

            @Override
            public void onChangedSecondColor(float percentage) {

            }
        });

        mIdeaScrollView.setOnSelectedIndicateChangedListener(new IdeaScrollView.OnSelectedIndicateChangedListener() {
            @Override
            public void onSelectedChanged(int position) {
                isNeedScrollTo = false;
                mRadioGroup.check(mRadioGroup.getChildAt(position).getId());
                isNeedScrollTo = true;
            }
        });

        mRadioGroup.setOnCheckedChangeListener(radioGroupListener);
    }

    @Override
    protected void setListener() {
        mLlBack.setOnClickListener(this);
        // mTvcollection.setOnClickListener(this);
        mLlShop.setOnClickListener(this);
        mLlCollect.setOnClickListener(this);
        mLlCustomerService.setOnClickListener(this);
        mTvAddcart.setOnClickListener(this);
        mLlHeadShare.setOnClickListener(this);
        mTvBuy.setOnClickListener(this);
        mTvLimitBuy.setOnClickListener(this);
//        mLlEvaluation.setOnClickListener(this);
        mLlService.setOnClickListener(this);
        mLlSelect.setOnClickListener(this);
        mLlCart.setOnClickListener(this);
        mLlSpecification.setOnClickListener(this);
        mTvAllGoods.setOnClickListener(this);
        mTvGoShopping.setOnClickListener(this);
        mLlParameter.setOnClickListener(this);
//        mLlEvaluation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mActivity, EvaluationDetailsActivity.class);
//                intent.putExtra("productId", String.valueOf(result.getProduct().getProductId()));
//                startActivity(intent);
//            }
//        });
    }

    public void setRadioButtonTextColor(float percentage) {
        if (Math.abs(percentage - currentPercentage) >= 0.1f) {
            for (int i = 0; i < mRadioGroup.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) mRadioGroup.getChildAt(i);
                radioButton.setTextColor(radioButton.isChecked() ? getRadioCheckedAlphaColor(percentage) : getRadioAlphaColor(percentage));
            }
            this.currentPercentage = percentage;
        }
    }


    public int getMeasureHeight(View view) {
        int width = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        return view.getMeasuredHeight();
    }

    //    #FFD700  orange
    public int getRadioCheckedAlphaColor(float f) {
        return Color.argb((int) (f * 255), 0x00, 0x00, 0x00);
//        return Color.argb((int) (f * 255), 0xFF, 0xD7, 0x00);
    }

    public int getRadioAlphaColor(float f) {
        return Color.argb((int) (f * 255), 0x80, 0x80, 0x80);
//        return Color.argb((int) (f * 255), 0xff, 0xff, 0xff);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.ll_head_share:
//                mShareAction.open();
//                break;
//            case R.id.ll_specification:
//                mPresenter.GetShopCouponList(Integer.toString(result.getShop().getId()));
//                break;
            case R.id.ll_back:
                finish();
                break;
//            case R.id.ll_customer_service:
//                Intent intent1 = new Intent(mActivity, MainActivity.class);
//
//                intent1.putExtra("goodsName", result.getProduct().getProductName());
//                intent1.putExtra("goodsPricture", result.getProduct().getImagePath().get(0));
//                intent1.putExtra("goodsPrice", "¥" + result.getProduct().getMinSalePrice());
//                intent1.putExtra("goodsURL", "" + result.getProduct().getProductId());
//                if (isLogin) {
//                    intent1.putExtra("userName", userInfo.getNickName());
//                    intent1.putExtra("userId", userInfo.getUserID());
//                    intent1.putExtra("userPic", userInfo.getAvator());
//                } else {
//                    intent1.putExtra("userName", "游客");
//                    intent1.putExtra("userId", "123456789");
//                    intent1.putExtra("userPic", R.drawable.default_avator);
//                }
//
//                startActivity(intent1);
            /*    break;
            case R.id.ll_collect:
                if (!isLogin) {
                    startActivity(new Intent(mActivity, LoginActivity.class));
                    return;
                }
                Log.d("=====>", String.valueOf(id));

                if (mImgCollect.isSelected()) {
                    mImgCollect.setSelected(false);
                    mPresenter.PostAddFavoriteProduct(id, userKey);
                    mTvCollection.setText("收藏");
                } else {
                    mImgCollect.setSelected(true);
                    mPresenter.PostAddFavoriteProduct(id, userKey);
                    mTvCollection.setText("已收藏");
                }

                break;

            case R.id.tv_addcart:
                if (!isLogin) {
                    startActivity(new Intent(mActivity, LoginActivity.class));
                    return;
                }
                showPopupWindow(1); //1为购物车  2为购买
                break;
            case R.id.ll_select:
            case R.id.tv_buy:
            case R.id.tv_limit_buy:
                if (!isLogin) {
                    startActivity(new Intent(mActivity, LoginActivity.class));
                    return;
                }
                showPopupWindow(2);
                break;
            case R.id.ll_service:
                Service();
                break;
            case R.id.ll_parameter:
                Parameter();
                break;
//            case R.id.ll_evaluation:
//                startActivity(new Intent(mActivity,EvaluationDetailsActivity.class));
//                break;

            case R.id.ll_cart:
                if (!isLogin) {
                    startActivity(new Intent(mActivity, LoginActivity.class));
                } else {
                    startActivity(new Intent(mActivity, CartActivity.class));
                }

                break;


            case R.id.tv_all_goods:
            case R.id.tv_go_shopping:
            case R.id.ll_shop:
                if ("-1".equals(VShopId)) {
                    ToastUtils.showShort("未开设微店");
                    return;
                }
                Intent intent = new Intent(mActivity, StoreDetailActivity.class);
                intent.putExtra("VShopId", VShopId);
                startActivity(intent);
                break;
*/
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

        ButterKnife.bind(this);
    }

   /* private void Parameter() {
        view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_goods_parameter, null);
        RecyclerView rl_parameter = view.findViewById(R.id.rl_parameter);
        ParameterAdapter adapter = new ParameterAdapter(R.layout.item_parameter, result.getProductAttributeInfolist());
        rl_parameter.setLayoutManager(new LinearLayoutManager(mActivity));
        rl_parameter.setAdapter(adapter);
        TextView tv_submit = view.findViewById(R.id.tv_submit);
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceDialog.dismiss();
            }
        });

        serviceDialog = new AlertDialog.Builder(mActivity).setView(view).create();
        serviceDialog.show();
        Window window = serviceDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM;
//        Display display = mActivity.getManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        window.setBackgroundDrawable(new ColorDrawable());
    }

    public void Service() {
        view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_goods_service, null);
        tv_submit = view.findViewById(R.id.tv_submit);
        RecyclerView rv_service = view.findViewById(R.id.rv_service);
        ServicesAdapter servicesAdapter = new ServicesAdapter(R.layout.item_services, result.getCashDepositsServerName());
        rv_service.setLayoutManager(new LinearLayoutManager(mActivity));
        rv_service.setAdapter(servicesAdapter);
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceDialog.dismiss();
            }
        });

        serviceDialog = new AlertDialog.Builder(mActivity).setView(view).create();
        serviceDialog.show();
        Window window = serviceDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM;
//        Display display = mActivity.getManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        window.setBackgroundDrawable(new ColorDrawable());
    }


    *//**
     * 弹出Popupwindow
     *//*
    public void showPopupWindow(int type) {
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.popwindow_chooseproperty, null);
        mPopupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        adderView = popupWindow_view.findViewById(R.id.adderview);


        //   img_bankcancle = popupWindow_view.findViewById(R.id.img_bankcancle);
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources()));
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                MyUtils.setWindowAlpa(mActivity, false);

                ((TextView) popupWindow_view.findViewById(R.id.tv_rmb)).setText("");
                ((TextView) popupWindow_view.findViewById(R.id.tv_repertory)).setText("库存:");
                ((TextView) popupWindow_view.findViewById(R.id.tv_choose)).setText("已选：");
                SkuId = "";
                skuId_color = "0";
                skuId_size = "0";
                skuId_version = "0";
                color_name = "";
                size_name = "";
                version_name = "";
                adderView.setValue(1);
            }
        });


        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(popupWindow_view, Gravity.BOTTOM, 0, 0);
        }
        MyUtils.setWindowAlpa(mActivity, true);

        *//*glide图片*//*
        if (!result.getProduct().getImagePath().isEmpty()) {
            Glide.with(mActivity)
                    .load(result.getProduct().getImagePath().get(0))
                    .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mActivity, 5)))
                    .into((ImageView) popupWindow_view.findViewById(R.id.img_shop));
            Photourl = result.getProduct().getImagePath().get(0);
            Log.d("=====>", Photourl);
        }



        *//*用于显示没有颜色没尺寸时候的价格和库存*//*
        if (result.getColor().isEmpty() && result.getSize().isEmpty()) {
            SpannableString spannableString = new SpannableString(skuArray.get(0).getPrice());
            if (skuArray.get(0).getPrice().contains(".")) {
                spannableString.setSpan(new RelativeSizeSpan(0.6f), skuArray.get(0).getPrice().indexOf("."), skuArray.get(0).getPrice().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            spannableString.setSpan(new RelativeSizeSpan(0.5f), item.getMinSalePrice().indexOf("."), item.getMinSalePrice().length(), USIVE_EXCLUSIVE);
            }
            ((TextView) popupWindow_view.findViewById(R.id.tv_rmb)).setText(spannableString);
            ((TextView) popupWindow_view.findViewById(R.id.tv_repertory)).setText("库存:" + skuArray.get(0).getStock() + "件");
            getinventory = skuArray.get(0).getStock();

            *//*计算数量*//*
            adderView.setMaxValue(getinventory);//默认没标签选择
            adderView.setOnValueChangeListene(new AdderView.OnValueChangeListener() {
                @Override
                public void onValueChange(int value) {
                    count = String.valueOf(value);
                }
            });
        }


        if (!result.getColor().isEmpty()) {
            popupWindow_view.findViewById(R.id.ll_cloose_color).setVisibility(View.VISIBLE);
            TextView tv_color = popupWindow_view.findViewById(R.id.tv_color);
            tv_color.setText(result.getColorAlias());
            RecyclerView rv_color = popupWindow_view.findViewById(R.id.rv_color);
            rv_color.setLayoutManager(new AutoLineFeedLayoutManager());
            chooseColorAdapter = new ChooseColorAdapter(R.layout.item_color, result.getColor(), mActivity);
            rv_color.setAdapter(chooseColorAdapter);
            ChooseColor(rv_color, result.getColor());
        } else {
            popupWindow_view.findViewById(R.id.ll_cloose_color).setVisibility(View.GONE);
        }

        if (!result.getSize().isEmpty()) {
            popupWindow_view.findViewById(R.id.ll_cloose_size).setVisibility(View.VISIBLE);
            TextView tv_size = popupWindow_view.findViewById(R.id.tv_size);
            tv_size.setText(result.getSizeAlias());
            RecyclerView rv_size = popupWindow_view.findViewById(R.id.rv_size);
            rv_size.setLayoutManager(new AutoLineFeedLayoutManager());
            chooseSizeAdapter = new ChooseSizeAdapter(R.layout.item_size, result.getSize());
            rv_size.setAdapter(chooseSizeAdapter);
            ChooseSize(rv_size, result.getSize());
        } else {
            popupWindow_view.findViewById(R.id.ll_cloose_size).setVisibility(View.GONE);
        }


        if (!result.getVersion().isEmpty()) {
            popupWindow_view.findViewById(R.id.ll_cloose_version).setVisibility(View.VISIBLE);
            TextView tv_specification = popupWindow_view.findViewById(R.id.tv_specification);
            tv_specification.setText(result.getVersionAlias());
            RecyclerView rv_version = popupWindow_view.findViewById(R.id.rv_version);
            rv_version.setLayoutManager(new AutoLineFeedLayoutManager());
            chooseVersionAdapter = new ChooseVersionAdapter(R.layout.item_version, result.getVersion());
            rv_version.setAdapter(chooseVersionAdapter);
            ChooseVerSion(rv_version, result.getVersion());

        } else {
            popupWindow_view.findViewById(R.id.ll_cloose_version).setVisibility(View.GONE);
        }


        *//*提交*//*
        popupWindow_view.findViewById(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tv_submit:
                        *//*有颜色没尺寸没规格*//*
                        if (!result.getColor().isEmpty() && result.getSize().isEmpty() && result.getVersion().isEmpty()) {
                            if (skuId_color.equals("0")) {
                                Toast.makeText(GoodsDetailActivity.this, "请选择颜色", Toast.LENGTH_SHORT).show();
                            } else if (getinventory == 0) {
                                Toast.makeText(mActivity, "对不起！该商品暂无库存！！", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                if (type == 1) {
                                    mPresenter.PostAddProductToCart(id + "_" + skuId_color + "_0_0", count, userKey);
                                } else {
                                    //List<StoreBean> list = GetCheckShopList(result, id + "_" + skuId_color + "_0_0", count, getPrice(id + "_" + skuId_color + "_0_0"), skuId_color, "");
                                    Intent intent = new Intent(mActivity, ConfirmOrderActivity.class);
                                    //intent.putExtra("checkshop", (Serializable) (list));//传递集合
                                    Bundle bundle = new Bundle();
                                    bundle.putString("TYPE", "1");//TYPE 为购买类型  1为直接购买  2为购物购买
                                    bundle.putString("skuid", id + "_" + skuId_color + "_0_0");
                                    bundle.putString("count", count);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            }
                        }
                        *//*有尺寸没颜色没规格*//*
                        if (!result.getSize().isEmpty() && result.getColor().isEmpty() && result.getVersion().isEmpty()) {
                            if (skuId_size.equals("0")) {
                                Toast.makeText(GoodsDetailActivity.this, "请选择尺寸", Toast.LENGTH_SHORT).show();
                            } else if (getinventory == 0) {
                                Toast.makeText(mActivity, "对不起！该商品暂无库存！！", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                if (type == 1) {
                                    mPresenter.PostAddProductToCart(id + "_0" + "_" + skuId_size + "_0", count, userKey);
                                } else {
                                    //List<StoreBean> list = GetCheckShopList(result, id + "_0" + "_" + skuId_size + "_0", count, getPrice(id + "_0" + "_" + skuId_size + "_0"), "", skuId_size);
                                    Intent intent = new Intent(mActivity, ConfirmOrderActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("TYPE", "1");//TYPE 为购买类型  1为直接购买  2为购物购买
                                    bundle.putString("skuid", id + "_0" + "_" + skuId_size + "_0");
                                    bundle.putString("count", count);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }

                            }
                        }

                        *//*有颜色有尺寸没规格*//*
                        if (!result.getSize().isEmpty() && !result.getColor().isEmpty() & result.getVersion().isEmpty()) {
                            if (skuId_size.equals("0") || skuId_color.equals("0")) {
                                Toast.makeText(GoodsDetailActivity.this, "请选择尺寸和颜色", Toast.LENGTH_SHORT).show();
                            } else if (getinventory == 0) {
                                Toast.makeText(mActivity, "对不起！该商品暂无库存！！", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                if (type == 1) {
                                    mPresenter.PostAddProductToCart(id + "_" + skuId_color + "_" + skuId_size + "_0", count, userKey);
                                } else {
                                    //List<StoreBean> list = GetCheckShopList(result, id + "_" + skuId_color + "_" + skuId_size + "_0", count, getPrice(id + "_" + skuId_color + "_" + skuId_size + "_0"), skuId_color, skuId_size);
                                    Intent intent = new Intent(mActivity, ConfirmOrderActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("TYPE", "1");//TYPE 为购买类型  1为直接购买  2为购物购买
                                    bundle.putString("skuid", id + "_" + skuId_color + "_" + skuId_size + "_0");
                                    bundle.putString("count", count);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }

                            }
                        }

                        *//*有颜色有尺寸有规格*//*
                        if (!result.getSize().isEmpty() && !result.getColor().isEmpty() & !result.getVersion().isEmpty()) {
                            if (skuId_size.equals("0") || skuId_color.equals("0") || skuId_version.equals("0")) {
                                Toast.makeText(GoodsDetailActivity.this, "请选择尺寸颜色规格", Toast.LENGTH_SHORT).show();
                            } else if (getinventory == 0) {
                                Toast.makeText(mActivity, "对不起！该商品暂无库存！！", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                if (type == 1) {
                                    mPresenter.PostAddProductToCart(id + "_" + skuId_color + "_" + skuId_size + "_" + skuId_version, count, userKey);
                                } else {
                                    Intent intent = new Intent(mActivity, ConfirmOrderActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("TYPE", "1");//TYPE 为购买类型  1为直接购买  2为购物购买
                                    bundle.putString("skuid", id + "_" + skuId_color + "_" + skuId_size + "_" + skuId_version);
                                    bundle.putString("count", count);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }

                            }
                        }
                        *//*有颜色没尺寸有规格*//*
                        if (result.getSize().isEmpty() && !result.getColor().isEmpty() & !result.getVersion().isEmpty()) {
                            if (skuId_color.equals("0") || skuId_version.equals("0")) {
                                Toast.makeText(GoodsDetailActivity.this, "请选择颜色规格", Toast.LENGTH_SHORT).show();
                            } else if (getinventory == 0) {
                                Toast.makeText(mActivity, "对不起！该商品暂无库存！！", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                if (type == 1) {
                                    mPresenter.PostAddProductToCart(id + "_" + skuId_color + "_" + "0" + "_" + skuId_version, count, userKey);
                                } else {
                                    Intent intent = new Intent(mActivity, ConfirmOrderActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("TYPE", "1");//TYPE 为购买类型  1为直接购买  2为购物购买
                                    bundle.putString("skuid", id + "_" + skuId_color + "_" + "0" + "_" + skuId_version);
                                    bundle.putString("count", count);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }

                            }
                        }
                        *//*有尺寸没颜色有规格*//*

                        if (!result.getSize().isEmpty() && result.getColor().isEmpty() & !result.getVersion().isEmpty()) {
                            if (skuId_size.equals("0") || skuId_version.equals("0")) {
                                Toast.makeText(GoodsDetailActivity.this, "请选择尺寸规格", Toast.LENGTH_SHORT).show();
                            } else if (getinventory == 0) {
                                Toast.makeText(mActivity, "对不起！该商品暂无库存！！", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                if (type == 1) {
                                    mPresenter.PostAddProductToCart(id + "_" + "0" + "_" + skuId_size + "_" + skuId_version, count, userKey);
                                } else {
                                    Intent intent = new Intent(mActivity, ConfirmOrderActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("TYPE", "1");//TYPE 为购买类型  1为直接购买  2为购物购买
                                    bundle.putString("skuid", id + "_" + "0" + "_" + skuId_size + "_" + skuId_version);
                                    bundle.putString("count", count);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }

                            }
                        }
                        *//*没尺寸没颜色有规格*//*
                        if (result.getSize().isEmpty() && result.getColor().isEmpty() & !result.getVersion().isEmpty()) {
                            if (skuId_version.equals("0")) {
                                Toast.makeText(GoodsDetailActivity.this, "请选择规格", Toast.LENGTH_SHORT).show();
                            } else if (getinventory == 0) {
                                Toast.makeText(mActivity, "对不起！该商品暂无库存！！", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                if (type == 1) {
                                    mPresenter.PostAddProductToCart(id + "_" + "0" + "_" + "0" + "_" + skuId_version, count, userKey);
                                } else {
                                    Intent intent = new Intent(mActivity, ConfirmOrderActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("TYPE", "1");//TYPE 为购买类型  1为直接购买  2为购物购买
                                    bundle.putString("skuid", id + "_" + "0" + "_" + "0" + "_" + skuId_version);
                                    bundle.putString("count", count);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }

                            }
                        }

                        *//*没尺寸没颜色没规格*//*
                        if (result.getColor().isEmpty() && result.getSize().isEmpty() && result.getVersion().isEmpty()) {

                            if (type == 1) {
                                mPresenter.PostAddProductToCart(id + "_0_0_0", count, userKey);
                            } else {
                                // List<StoreBean> list = GetCheckShopList(result, id + "_0_0_0", count, getPrice(id + "_0_0_0"), "", "");
                                Intent intent = new Intent(mActivity, ConfirmOrderActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("TYPE", "1");//TYPE 为购买类型  1为直接购买  2为购物购买
                                bundle.putString("skuid", id + "_0" + "_0" + "_0");
                                bundle.putString("count", count);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }

                        }
                        break;
                }
            }
        });

        popupWindow_view.findViewById(R.id.img_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });


        popupWindow_view.findViewById(R.id.img_shop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, PhotoViewActivity2.class);
                intent.putExtra("PhotoUrl", Photourl);
                startActivity(intent);
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(Throwable e) {
        // mStateLayout.changeState(StateFrameLayout.NET_ERROR);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        if ("更新登录信息".equals(name)) {
            getLoginMsg();
            mPresenter.GetSKUInfo(id);
        }
    }

    @Override
    public void GetShopCouponList(ShopCoupResult Result) {
        if ("true".equals(Result.getSuccess())) {
            couplist.clear();
            couplist.addAll(Result.getCoupon());
            if (mPopupWindow == null) {
                showPopupWindow(Result.getCoupon().get(0).getShopName());
            } else {
                if (mPopupWindow.isShowing()) {
                    shopCoupAdapter.setNewData(couplist);
                } else {
                    showPopupWindow(Result.getCoupon().get(0).getShopName());
                }
            }
        } else {
//            mLlSpecification.setVisibility(View.GONE);
            Toast.makeText(mActivity, Result.getErrorMsg(), Toast.LENGTH_SHORT).show();

        }

    }

    *//*获取优惠券*//*
    @Override
    public void PostAcceptCoupon(GetShopCoupResult Result) {

        if ("true".equals(Result.getSuccess())) {
            mPresenter.GetShopCouponList(Integer.toString(result.getShop().getId()));
            Toast.makeText(mActivity, "领取成功", Toast.LENGTH_SHORT).show();
            EventBus.getDefault().post("UpdateOrderCount");//更新个人中心数量
        } else {
            Toast.makeText(mActivity, Result.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> Result) {
        switch (Result.getStatusCode()) {
            case 200:
                userInfo = Result.getData().getData().get(0);
                break;
        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void GetProductDetail(DetailResult Result) {

        result = Result;
        if ("true".equals(Result.getSuccess())) {
            VShopId = String.valueOf(result.getShop().getVShopId());
            ShopId = String.valueOf(result.getShop().getId());
            if (Result.getIsOnLimitBuy()) {
//                mLlLimit.setVisibility(View.VISIBLE);
//                mLlNormal.setVisibility(View.GONE);
                mCountdownview.start(Result.getSecond() * 1000);
                mTvOnlyLeft.setText("仅剩" + skuArray.get(0).getStock() + "件");
            } else {
                mLlLimit.setVisibility(View.GONE);
                mLlNormal.setVisibility(View.VISIBLE);
            }
            String timestamp = TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            *//*ImagePath顶部图片轮播*//*
            ArrayList<String> images = new ArrayList<>();
            for (int i = 0; i < Result.getProduct().getImagePath().size(); i++) {
                images.add(Result.getProduct().getImagePath().get(i) + "?" + timestamp);
            }
            mBannerGoods.setImageLoader(new GlideImageLoader());
            mBannerGoods.setImages(images);
            mBannerGoods.setBannerStyle(BannerConfig.NUM_INDICATOR);
            mBannerGoods.setIndicatorGravity(BannerConfig.CENTER);
            mBannerGoods.setDelayTime(4000);

            mBannerGoods.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Intent intent = new Intent(mActivity, PhotosViewActivity.class);
                    intent.putExtra("photo_list", (Serializable) images);
                    intent.putExtra("photo_position", position);
                    startActivity(intent);


                }
            });

            mBannerGoods.start();

            if (result.getProduct().isInstall()) {
                mLlInstallService.setVisibility(View.VISIBLE);
            } else {
                mLlInstallService.setVisibility(View.GONE);
            }


            if (result.getProductAttributeInfos() != null) {
                if (result.getProductAttributeInfos().size() == 1) {
                    mTvComment.setText(result.getProductAttributeInfos().get(0));
                    mTvCommentTwo.setVisibility(View.GONE);
                } else if (result.getProductAttributeInfos().size() >= 2) {
                    mTvComment.setText(result.getProductAttributeInfos().get(0));
                    mTvCommentTwo.setText(result.getProductAttributeInfos().get(1));
                }
            } else {
                mTvComment.setVisibility(View.GONE);
                mTvCommentTwo.setVisibility(View.GONE);
            }

            *//*判断商品是否下架*//*
            if (Result.getProduct().getProductSaleStatus()==2){
                mTvObtained.setVisibility(View.VISIBLE);
                mTvBuy.setBackground(getResources().getDrawable(R.drawable.red__goods_transparent));
                mTvAddcart.setBackground(getResources().getDrawable(R.drawable.yellow__goods_transparent));
                mTvAddcart.setEnabled(false);
                mTvBuy.setEnabled(false);
            }else {
                mTvObtained.setVisibility(View.GONE);
            }

            *//*判断是否收藏*//*
            if (Result.getProduct().isIsFavorite()) {
                mImgCollect.setSelected(true);
                mTvCollection.setText("已收藏");
            } else {
                mImgCollect.setSelected(false);
                mTvCollection.setText("收藏");
            }

            *//*显示价格暂时取范围*//*
            SpannableString spannableString = new SpannableString(String.valueOf(Result.getProduct().getMinSalePrice()));
            if (String.valueOf(Result.getProduct().getMinSalePrice()).contains(".")) {
                spannableString.setSpan(new RelativeSizeSpan(0.6f), String.valueOf(Result.getProduct().getMinSalePrice()).indexOf("."), String.valueOf(Result.getProduct().getMinSalePrice()).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            mTvGoodMoney.setText(spannableString);
            String string = Result.getProduct().getMarketPrice() + "";
            SpannableString sp = new SpannableString(string);
            sp.setSpan(new StrikethroughSpan(), 0, string.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            mTvGoodMoneyMax.setText(sp);

            *//*判断是否免运费*//*

            if ("免运费".equals(Result.getFree())) {
                mTvExpressDelivery.setText("快递：0.00");
            } else {
                mTvExpressDelivery.setText("快递：" + Result.getFreightFee());
            }
            *//*月销量暂未总销量*//*
            mTvSalesVolume.setText("月销" + Result.getMaxSaleCount());
            //商品名称
            mTvGoodName.setText(Result.getProduct().getProductName());

            *//*地址*//*
            mTvAddress.setText(Result.getProductAddress());



              *//*服务
            "CashDepositsServer":{
            "IsSevenDayNoReasonReturn":false,   七天无理由
            "IsTimelyShip":false,           及时发货
            "IsCustomerSecurity":true,   消费者保障
            "CanSelfTake":false
            },*//*

            String server = "";

            if (Result.getCashDepositsServerName().size() >= 3) {
                server = Result.getCashDepositsServerName().get(0).getCashServiceName() + " " + Result.getCashDepositsServerName().get(1).getCashServiceName() + " " + Result.getCashDepositsServerName().get(2).getCashServiceName();
            } else if (Result.getCashDepositsServerName().size() == 2) {
                server = Result.getCashDepositsServerName().get(0).getCashServiceName() + " " + Result.getCashDepositsServerName().get(1).getCashServiceName();
            } else if (Result.getCashDepositsServerName().size() == 1) {
                server = Result.getCashDepositsServerName().get(0).getCashServiceName();
            } else {
                mLlService.setVisibility(View.GONE);
            }
            mTvService.setText(server);


            if (Result.getProductAttributeInfolist() == null) {
                mLlParameter.setVisibility(View.GONE);
            } else {
                mTvParameter.setText(Result.getProductAttributeInfolist().get(0).getKey());
            }

            *//*添加颜色item*//*
            // mRvcolor.setLayoutManager(new LinearLayoutManager(mActivity,LinearLayoutManager.HORIZONTAL,false));
            String html = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "\t<meta charset=\"utf-8\">\n" +
                    "\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "\t\n" +
                    "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                    "\n" +
                    "\t<title>detail</title>\n" +
                    "\n" +
                    "\t<style>body{border:0;padding:0;margin:0;}img{max-width:100%;border:0;display:block;vertical-align: middle;padding:0;margin:0;}p{border:0;padding:0;margin:0;}div{border:0;padding:0;margin:0;}</style>\n" +
                    "</head>"
                    + "<body>"
                    + result.getProduct().getProductDescription() + "</body>" + "</html>";

            mWebview.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
            mWebview.getSettings().setJavaScriptEnabled(true);
            mWebview.setWebChromeClient(new WebChromeClient());
            GlideUtil.loadImageViewLoding(mActivity, result.getVShopLog(), mIvStorePicture, R.drawable.dianpumore, R.drawable.dianpumore);
            mTvStoreName.setText(result.getShop().getName());

            mTvstore_num.setText("关注人数:" + result.getShop().getFavoriteShopCount() + "人                  " + "  宝贝数量:" + result.getShop().getProductNum() + "件");


            mTvSellerServiceScore.setText(result.getShop().getServiceMark() + "");
            mTvBabyDescriptionScore.setText(result.getShop().getProductMark() + "");
            mTvLogisticsServicesScore.setText(result.getShop().getPackMark() + "");

            *//*增加自定义按钮的分享面板*//*
            mShareAction = new ShareAction((Activity) mActivity).setDisplayList(
                    SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                    SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.MORE)
                    .addButton("复制文本", "复制文本", "umeng_socialize_copy", "umeng_socialize_copy")
                    .addButton("复制链接", "复制链接", "umeng_socialize_copyurl", "umeng_socialize_copyurl")
                    .setShareboardclickCallback(new ShareBoardlistener() {
                        @Override
                        public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                            if (snsPlatform.mShowWord.equals("复制文本")) {
                                Toast.makeText(mActivity, "已复制", Toast.LENGTH_LONG).show();
                            } else if (snsPlatform.mShowWord.equals("复制链接")) {
                                Toast.makeText(mActivity, "已复制", Toast.LENGTH_LONG).show();

                            } else {
                                RxPermissions rxPermissions = new RxPermissions((Activity) mActivity);
                                rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                                        .subscribe(new Consumer<Boolean>() {
                                            @Override
                                            public void accept(Boolean aBoolean) throws Exception {
                                                if (aBoolean) {
                                                    // 获取全部权限成功

                                                    UMWeb web = new UMWeb("http://mall.xigyu.com/product/detail/" + result.getProduct().getProductId());
                                                    web.setTitle(result.getProduct().getProductName());
                                                    web.setDescription(result.getProduct().getProductName());
                                                    web.setThumb(new UMImage(mActivity, result.getProduct().getImagePath().get(0)));
                                                    new ShareAction((Activity) mActivity).withMedia(web)
                                                            .setPlatform(share_media)
                                                            .setCallback(mShareListener)
                                                            .share();
                                                } else {
                                                    // 获取全部权限失败
                                                    ToastUtils.showShort("权限获取失败");
                                                }
                                            }
                                        });

                            }
                        }
                    });
            skeletonScreen.hide();
            mPresenter.ProductComment(id, String.valueOf(1), "10", "0");
        } else {
            // mStateLayout.changeState(StateFrameLayout.EMPTY);
        }


    }


    *//*选择颜色*//*
    private void ChooseColor(RecyclerView rv_color, List<ShopColor> list) {

        chooseColorAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                switch (view.getId()) {
                    case R.id.rl_choose:
                        for (int i = 0; i < list.size(); i++) {
                            adapter.getViewByPosition(rv_color, i, R.id.rl_choose).setSelected(false);
                        }
                        if (adapter.getViewByPosition(rv_color, position, R.id.rl_choose).isSelected()) {
                            adapter.getViewByPosition(rv_color, position, R.id.rl_choose).setSelected(false);
                        } else {
                            adapter.getViewByPosition(rv_color, position, R.id.rl_choose).setSelected(true);

                            *//*获取color的sku判断其他是否存在*//*
                            String colorskuId = ((ShopColor) adapter.getData().get(position)).getSkuId();


                            if ("".equals(result.getColor().get(position).getImg())) {

                            } else {
                                Glide.with(mActivity)
                                        .load(result.getColor().get(position).getImg())
                                        .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mActivity, 5)))
                                        .into((ImageView) popupWindow_view.findViewById(R.id.img_shop));
                                Photourl = result.getColor().get(position).getImg();
                                Log.d("=====>", Photourl);
                            }


                            skuId_color = ((ShopColor) adapter.getItem(position)).getSkuId();
                            color_name = ((ShopColor) adapter.getItem(position)).getValue();

                            if (!size_name.equals("") || !color_name.equals("") || !version_name.equals("")) {
                                ((TextView) popupWindow_view.findViewById(R.id.tv_choose)).setText("已选：" + size_name + " " + color_name + " " + version_name);

                            }
                            //库存

                            if (skuId_version.equals("0")) {
                                SkuId = id + "_" + skuId_color + "_" + skuId_size + "_" + "0";  //最后一项先默认为0
                                getinventory = getinventory(SkuId);//获取库存
                                ((TextView) popupWindow_view.findViewById(R.id.tv_repertory)).setText("库存:" + getinventory + "件");
                            } else {
                                SkuId = id + "_" + skuId_color + "_" + skuId_size + "_" + skuId_version;
                                getinventory = getinventory(SkuId);//获取库存
                                ((TextView) popupWindow_view.findViewById(R.id.tv_repertory)).setText("库存:" + getinventory + "件");
                            }

                            *//*计算数量*//*
                            adderView.setMaxValue(getinventory);//默认没标签选择
                            adderView.setOnValueChangeListene(new AdderView.OnValueChangeListener() {
                                @Override
                                public void onValueChange(int value) {
                                    count = String.valueOf(value);
                                }
                            });


                            //价格
                            String price = getPrice(SkuId);
                            SpannableString spannableString = new SpannableString(price);
                            if (price.contains(".")) {
                                spannableString.setSpan(new RelativeSizeSpan(0.6f), price.indexOf("."), price.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            spannableString.setSpan(new RelativeSizeSpan(0.5f), item.getMinSalePrice().indexOf("."), item.getMinSalePrice().length(), USIVE_EXCLUSIVE);
                            }
                            ((TextView) popupWindow_view.findViewById(R.id.tv_rmb)).setText(spannableString);


                        }

                        break;
                }
            }
        });

    }

    *//*选择尺寸*//*
    private void ChooseSize(RecyclerView rv_size, List<ShopSize> list) {

        chooseSizeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                switch (view.getId()) {
                    case R.id.rl_choose:
                        for (int i = 0; i < list.size(); i++) {
                            adapter.getViewByPosition(rv_size, i, R.id.rl_choose).setSelected(false);
                        }
                        if (adapter.getViewByPosition(rv_size, position, R.id.rl_choose).isSelected()) {
                            adapter.getViewByPosition(rv_size, position, R.id.rl_choose).setSelected(false);
                        } else {
                            adapter.getViewByPosition(rv_size, position, R.id.rl_choose).setSelected(true);
                            skuId_size = ((ShopSize) adapter.getItem(position)).getSkuId();
                            size_name = ((ShopSize) adapter.getItem(position)).getValue();

                            if (!size_name.equals("") || !color_name.equals("") || !version_name.equals("")) {
                                ((TextView) popupWindow_view.findViewById(R.id.tv_choose)).setText("已选：" + size_name + " " + color_name + " " + version_name);
                            }


                            if (skuId_version.equals("0")) {
                                SkuId = id + "_" + skuId_color + "_" + skuId_size + "_" + "0";  //最后一项先默认为0
                                //库存
                                getinventory = getinventory(SkuId);//获取库存
                                ((TextView) popupWindow_view.findViewById(R.id.tv_repertory)).setText("库存:" + getinventory + "件");
                            } else {
                                SkuId = id + "_" + skuId_color + "_" + skuId_size + "_" + skuId_version;  //最后一项先默认为0
                                //库存
                                getinventory = getinventory(SkuId);//获取库存
                                ((TextView) popupWindow_view.findViewById(R.id.tv_repertory)).setText("库存:" + getinventory + "件");
                            }

                            *//*计算数量*//*
                            adderView.setMaxValue(getinventory);//默认没标签选择
                            adderView.setOnValueChangeListene(new AdderView.OnValueChangeListener() {
                                @Override
                                public void onValueChange(int value) {
                                    count = String.valueOf(value);
                                }
                            });

                            //价格
                            String price = getPrice(SkuId);
                            SpannableString spannableString = new SpannableString(price);
                            if (price.contains(".")) {
                                spannableString.setSpan(new RelativeSizeSpan(0.6f), price.indexOf("."), price.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            spannableString.setSpan(new RelativeSizeSpan(0.5f), item.getMinSalePrice().indexOf("."), item.getMinSalePrice().length(), USIVE_EXCLUSIVE);
                            }
                            ((TextView) popupWindow_view.findViewById(R.id.tv_rmb)).setText(spannableString);
                        }
                        break;
                }
            }
        });
    }

    *//*选择版本*//*
    private void ChooseVerSion(RecyclerView rv_version, List<ShopVersion> list) {

        chooseVersionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                switch (view.getId()) {
                    case R.id.rl_choose:
                        for (int i = 0; i < list.size(); i++) {
                            adapter.getViewByPosition(rv_version, i, R.id.rl_choose).setSelected(false);
                        }
                        if (adapter.getViewByPosition(rv_version, position, R.id.rl_choose).isSelected()) {
                            adapter.getViewByPosition(rv_version, position, R.id.rl_choose).setSelected(false);
                        } else {
                            adapter.getViewByPosition(rv_version, position, R.id.rl_choose).setSelected(true);

                            skuId_version = ((ShopVersion) adapter.getItem(position)).getSkuId();
                            version_name = ((ShopVersion) adapter.getItem(position)).getValue();

                            if (!size_name.equals("") || !color_name.equals("") || !version_name.equals("")) {
                                ((TextView) popupWindow_view.findViewById(R.id.tv_choose)).setText("已选：" + size_name + " " + color_name + " " + version_name);
                            }

                            if (skuId_version.equals("0")) {
                                SkuId = id + "_" + skuId_color + "_" + skuId_size + "_" + "0";  //最后一项先默认为0
                                //库存
                                getinventory = getinventory(SkuId);//获取库存
                                ((TextView) popupWindow_view.findViewById(R.id.tv_repertory)).setText("库存:" + getinventory + "件");

                            } else {
                                SkuId = id + "_" + skuId_color + "_" + skuId_size + "_" + skuId_version;
                                //库存
                                getinventory = getinventory(SkuId);//获取库存
                                ((TextView) popupWindow_view.findViewById(R.id.tv_repertory)).setText("库存:" + getinventory + "件");

                            }

                            *//*计算数量*//*
                            adderView.setMaxValue(getinventory);//默认没标签选择
                            adderView.setOnValueChangeListene(new AdderView.OnValueChangeListener() {
                                @Override
                                public void onValueChange(int value) {
                                    count = String.valueOf(value);
                                }
                            });

                            //价格
                            String price = getPrice(SkuId);
                            SpannableString spannableString = new SpannableString(price);
                            if (price.contains(".")) {
                                spannableString.setSpan(new RelativeSizeSpan(0.6f), price.indexOf("."), price.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            spannableString.setSpan(new RelativeSizeSpan(0.5f), item.getMinSalePrice().indexOf("."), item.getMinSalePrice().length(), USIVE_EXCLUSIVE);
                            }
                            ((TextView) popupWindow_view.findViewById(R.id.tv_rmb)).setText(spannableString);

                        }

                        break;
                }
            }
        });


    }


    *//*获取商品库存*//*
    public int getinventory(String skuId) {
        int inventory = 0;
        for (int i = 0; i < skuArray.size(); i++) {
            if (skuId.equals(skuArray.get(i).getSkuId())) {
                inventory = skuArray.get(i).getStock();
                Log.d("inventory======>", String.valueOf(inventory));
                return inventory;
            }

        }
        return inventory;
    }

    *//*获取产品价格*//*
    public String getPrice(String skuId) {
        String price = "";
        for (int i = 0; i < skuArray.size(); i++) {
            if (skuId.equals(skuArray.get(i).getSkuId())) {
                price = skuArray.get(i).getPrice();
                return price;
            }
        }
        return price;
    }


    *//*添加到购物车*//*
    @Override
    public void PostAddProductToCart(AddtoCartResult Result) {
        if (("true").equals(Result.getSuccess())) {
            Toast.makeText(this, "已添加至购物车", Toast.LENGTH_SHORT).show();
            EventBus.getDefault().post("cart");
            mPopupWindow.dismiss();
        }

    }

    @Override
    public void GetSKUInfo(GetGoodSKu Result) {
        if (("true").equals(Result.getSuccess())) {
            // skuArray.addAll(Result.getSkuArray());
            skuArray.addAll(Result.getSkuArray());
            mPresenter.GetProductDetail(id, userKey);
        }
    }

    *//*收藏*//*
    @Override
    public void PostAddFavoriteProduct(CollectResult Result) {
        EventBus.getDefault().post("UpdateOrderCount");//更新个人中心收藏数量
    }

    *//*获取商品第一条评论*//*
    @Override
    public void GetProductCommentShow(GetCommentResult result) {
        if (result.isSuccess()) {
//            GlideUtil.loadImageViewLoding(mActivity,result.getData().get(0).getAppendImages(),mIvPicture,R.drawable.image_loading,R.drawable.image_loading);
            mTvUsername.setText(result.getData().get(0).getUserName());
            mTvContent.setText(result.getData().get(0).getReviewContent());
        } else {
            mTvUsername.setVisibility(View.GONE);
            mTvContent.setText(result.getErrorMsg());
        }
        // mStateLayout.changeState(StateFrameLayout.SUCCESS);
        EventBus.getDefault().post("UpdateOrderCount");//更新个人中心足迹数量
    }

    @Override
    public void ProductComment(Comment Result) {
        if (Result.getAllCommentCount() != null) {
            mTvBabyEvaluation.setText("宝贝评价(" + Result.getAllCommentCount() + ")");
            mPresenter.GetProductCommentShow(id, userKey);
        } else {
            return;
        }

    }


    *//*查询选择后其他类型是否存在*//*
    public void CheckOtherNum(String colorskuid, String size_skuid, String version_skuid,
                              ChooseColorAdapter chooseColorAdapter, ChooseSizeAdapter chooseSizeAdapter,
                              ChooseVersionAdapter chooseVersionAdapter) {
        for (int i = 0; i < skuArray.size(); i++) {

        }


    }

    public void showPopupWindow(String shopname) {
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.popwindow_shopcoups, null);
        mPopupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources()));
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

//        popupWindow_view.findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPopupWindow.dismiss();
//            }
//        });

        popupWindow_view.findViewById(R.id.img_cha).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });


        ((TextView) popupWindow_view.findViewById(R.id.tv_coup)).setText(shopname);
        RecyclerView rv = popupWindow_view.findViewById(R.id.rv_coup);
        rv.setLayoutManager(new LinearLayoutManager(mActivity));
        shopCoupAdapter = new ShopCoupAdapter(R.layout.item_shopcoup, couplist);
        rv.setAdapter(shopCoupAdapter);

        shopCoupAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_getcoup:
                        mPresenter.PostAcceptCoupon(((ShopCoupResult.CouponBean) adapter.getData().get(position)).getVShopId(), ((ShopCoupResult.CouponBean) adapter.getData().get(position)).getCouponId(), userKey);
                        break;
                }
            }
        });
    }
*/
}
