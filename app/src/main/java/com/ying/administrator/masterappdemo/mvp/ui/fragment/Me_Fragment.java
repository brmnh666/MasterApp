package com.ying.administrator.masterappdemo.mvp.ui.fragment;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.paradigm.botkit.BotKitClient;
import com.paradigm.botkit.ChatActivity;
import com.paradigm.botlib.VisitorInfo;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.Message;
import com.ying.administrator.masterappdemo.entity.MessageData;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.MainContract;
import com.ying.administrator.masterappdemo.mvp.model.MainModel;
import com.ying.administrator.masterappdemo.mvp.presenter.MainPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.activity.AboutUsActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Opinion_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Order_Receiving_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Personal_Information_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.RechargeActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.SettingActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.StudyActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.SubAccountManagementActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.VerifiedUpdateActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Verified_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Wallet_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.WithDrawActivity;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;
import com.ying.administrator.masterappdemo.util.ZXingUtils;
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;
import com.ying.administrator.masterappdemo.widget.CustomDialog;
import com.ying.administrator.masterappdemo.widget.GlideCircleWithBorder;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Me_Fragment extends BaseLazyFragment<MainPresenter, MainModel> implements MainContract.View, View.OnClickListener {
    private static final String ARG_SHOW_TEXT = "text";
    @BindView(R.id.img_me_message)
    ImageView mImgMeMessage;
    @BindView(R.id.tv_me_message)
    TextView mTv_me_message;
    @BindView(R.id.img_me_setting)
    ImageView mImgMeSetting;
    @BindView(R.id.img_me_head)
    ImageView mImgMeHead;
    @BindView(R.id.ll_recommended_gift)
    LinearLayout mLlRecommendedGift;
    @BindView(R.id.normal_dfk_ll)
    LinearLayout mNormalDfkLl;
    @BindView(R.id.normal_dfh_ll)
    LinearLayout mNormalDfhLl;
    @BindView(R.id.normal_dsh_ll)
    LinearLayout mNormalDshLl;
    @BindView(R.id.normal_all_ll)
    LinearLayout mNormalAllLl;
    @BindView(R.id.ll_me_information)
    LinearLayout mLlMeInformation;
    @BindView(R.id.ll_me_mywallet)
    LinearLayout mLlMeMywallet;
    @BindView(R.id.ll_sub_account_management)
    LinearLayout mLlSubAccountManagement;
    @BindView(R.id.ll_me_customer_service)
    LinearLayout mLlMeCustomerService;
    @BindView(R.id.ll_online_consultation)
    LinearLayout mLlOnlineConsultation;
    @BindView(R.id.ll_me_opinion)
    LinearLayout mLlMeOpinion;
    @BindView(R.id.ll_me_about_us)
    LinearLayout mLlMeAboutUs;
    @BindView(R.id.tv_me_withdraw)
    TextView mTv_me_withdraw;
    @BindView(R.id.tv_me_withdraw_deposit)
    TextView mTv_me_withdraw_deposit;
    @BindView(R.id.tv_recharge)
    TextView mTvRecharge;
    Unbinder unbinder;
    @BindView(R.id.ll_share_code)
    LinearLayout mLlShareCode;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_certification)
    TextView mTvCertification;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.ll_study)
    LinearLayout mLlStudy;
    @BindView(R.id.ll_setting)
    LinearLayout mLlSetting;
    @BindView(R.id.img_un_certification)
    ImageView mImgUnCertification;
    @BindView(R.id.img_certification)
    ImageView mImgCertification;

    private Home_Fragment.CustomShareListener mShareListener;
    private ShareAction mShareAction;
    private View dialog_share;
    private View btn_share_one;
    private View btn_share_two;
    private AlertDialog dialogShare;
    private Window window;
    private SPUtils spUtils = SPUtils.getInstance("token");
    private UserInfo.UserInfoDean userInfo = new UserInfo.UserInfoDean();
    private String userID; //获取用户id
    private String mContentText;

    int position = 0;
    private Bundle bundle;
    private Intent intent;
    private ImageView iv_code_one;
    private View under_review;
    private Button btnConfirm;
    private Button btn_verified_update;
    private AlertDialog underReviewDialog;
    private CustomDialog customDialog;
    private Button btn_go_to_the_mall;

    public Me_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContentText = getArguments().getString(ARG_SHOW_TEXT);
        }
    }

    public static Me_Fragment newInstance(String param1) {
        Me_Fragment fragment = new Me_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData() {
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(mActivity).setShareConfig(config);
        mShareListener = new Home_Fragment.CustomShareListener(mActivity);
        /*增加自定义按钮的分享面板*/
        mShareAction = new ShareAction(mActivity).setDisplayList(
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
                            UMWeb web = new UMWeb("http://admin.xigyu.com/sign?phone=" + userID + "&type=7");
                            web.setTitle("西瓜鱼");
                            web.setDescription("注册送西瓜币了！！！！！");
                            web.setThumb(new UMImage(mActivity, R.drawable.icon));
                            new ShareAction(mActivity).withMedia(web)
                                    .setPlatform(share_media)
                                    .setCallback(mShareListener)
                                    .share();
                        }
                    }
                });
        userID = spUtils.getString("userName"); //获取用户id
        mPresenter.GetUserInfoList(userID, "1");//根据 手机号码获取用户详细信息
        mRefreshLayout.setEnableLoadmore(false);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.GetUserInfoList(userID, "1");//根据 手机号码获取用户详细信息
                refreshlayout.finishRefresh(1000);
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mTv_me_withdraw_deposit.setOnClickListener(this);//提现
        mLlMeMywallet.setOnClickListener(this);
        mImgMeSetting.setOnClickListener(this);
        mLlMeAboutUs.setOnClickListener(this);
        mLlMeInformation.setOnClickListener(this);
        mLlMeOpinion.setOnClickListener(this);
        mLlMeCustomerService.setOnClickListener(this);
        mLlSubAccountManagement.setOnClickListener(this);
        mLlRecommendedGift.setOnClickListener(this);
        mLlOnlineConsultation.setOnClickListener(this);
        mNormalAllLl.setOnClickListener(this);
        mNormalDfhLl.setOnClickListener(this);
        mNormalDfkLl.setOnClickListener(this);
        mNormalDshLl.setOnClickListener(this);
        mTvRecharge.setOnClickListener(this);
        mImgMeHead.setOnClickListener(this);
        mTv_me_message.setOnClickListener(this);
        mLlShareCode.setOnClickListener(this);
        mTvCertification.setOnClickListener(this);
        mLlStudy.setOnClickListener(this);
        mLlSetting.setOnClickListener(this);

    }

    /*获取用户信息*/
    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200://请求成功
                userInfo = baseResult.getData().getData().get(0);
                /*显示头像*/
                if (userInfo.getAvator() == null) {//显示初始头像
                    return;
                } else {
                    RequestOptions myOptions = new RequestOptions().transform(new GlideCircleWithBorder(this, 1, Color.parseColor("#DCDCDC")));
                    Glide.with(mActivity)
                            .load(Config.HEAD_URL + userInfo.getAvator())
                            .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                            .apply(myOptions)
                            .into(mImgMeHead);
                }
                /*显示余额*/
                String format = String.format("%.2f", userInfo.getTotalMoney() - userInfo.getFrozenMoney());
                // String can_withdraw = Double.toString(userInfo.getTotalMoney() - userInfo.getFrozenMoney());//可提现余额=总金额-冻结金额
                mTv_me_withdraw.setText(format);
                mTvName.setText(userInfo.getNickName());
                /*设置实名认证状态*/
                if (userInfo.getIfAuth() == null) {
                    mTvCertification.setText("未实名认证");
                    mImgUnCertification.setVisibility(View.VISIBLE);
                    mImgCertification.setVisibility(View.INVISIBLE);
                    mTvCertification.setTextColor(Color.rgb(255, 0, 0));

                } else if (userInfo.getIfAuth().equals("0")) {
                    mTvCertification.setText("审核中");
                    mTvCertification.setTextColor(Color.RED);
                    mImgUnCertification.setVisibility(View.VISIBLE);
                    mImgCertification.setVisibility(View.INVISIBLE);

                } else if (userInfo.getIfAuth().equals("-1")) {
                    mTvCertification.setText("审核不通过");
                    mTvCertification.setTextColor(Color.RED);
                    mImgUnCertification.setVisibility(View.VISIBLE);
                    mImgCertification.setVisibility(View.INVISIBLE);

                } else if (userInfo.getIfAuth().equals("1")) {
                    mTvCertification.setText("已实名认证");
                    mImgUnCertification.setVisibility(View.INVISIBLE);
                    mImgCertification.setVisibility(View.VISIBLE);
                }

                if (userInfo.getParentUserID()!=null){

                    mLlSubAccountManagement.setVisibility(View.GONE);

                }




                break;


            default:
                break;

        }

    }

    @Override
    public void GetMessageList(BaseResult<MessageData<List<Message>>> baseResult) {

    }

    @Override
    public void GetTransactionMessageList(BaseResult<MessageData<List<Message>>> baseResult) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_certification:
                if (userInfo.getIfAuth() != null) {
                    if (userInfo.getIfAuth().equals("1")) {
                        under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_successful_review, null);
                        btnConfirm = under_review.findViewById(R.id.btn_confirm);
                        btn_verified_update = under_review.findViewById(R.id.btn_verified_update);
                        btnConfirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                underReviewDialog.dismiss();
                            }
                        });
                        btn_verified_update.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                underReviewDialog.dismiss();
                                startActivity(new Intent(mActivity, VerifiedUpdateActivity.class));
                            }
                        });
                        underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review).create();
                        underReviewDialog.show();
                    } else if (userInfo.getIfAuth().equals("0")) {
                        under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_under_review, null);
                        btnConfirm = under_review.findViewById(R.id.btn_confirm);
                        btnConfirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                underReviewDialog.dismiss();
                            }
                        });
                        underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review).create();
                        underReviewDialog.show();
                    } else if (userInfo.getIfAuth().equals("-1")) {
                        under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_audit_failure, null);
                        btnConfirm = under_review.findViewById(R.id.btn_confirm);
                        btnConfirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                underReviewDialog.dismiss();
                                startActivity(new Intent(mActivity, Verified_Activity.class));
                            }
                        });
                        underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review).create();
                        underReviewDialog.show();
                    } else {
                        showVerifiedDialog();
                    }
                } else {
                    showVerifiedDialog();
                }
                break;
            case R.id.tv_me_withdraw_deposit: //提现
               /*     if (userInfo.getTotalMoney()==0){
                        dialog.setMessage("您暂未实名认证,是否进行认证")
                                //.setImageResId(R.mipmap.ic_launcher)
                                .setTitle("提示")
                                .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {//进行实名认证
                                startActivity(new Intent(getActivity(), Verified_Activity.class));
                                dialog.dismiss();
                            }

                            @Override
                            public void onNegtiveClick() {//取消
                                dialog.dismiss();
                                // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                    }*/
                startActivity(new Intent(getActivity(), WithDrawActivity.class));


                break;
            case R.id.ll_study:  //学习考试
                startActivity(new Intent(getActivity(), StudyActivity.class));
                break;
            case R.id.ll_me_mywallet:  //我的钱包
                startActivity(new Intent(getActivity(), Wallet_Activity.class));
                break;
            case R.id.img_me_head:  //头像
                startActivity(new Intent(getActivity(), Personal_Information_Activity.class));
                break;
            case R.id.ll_setting:
            case R.id.img_me_setting: //设置
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.ll_me_about_us://关于我们
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
                break;
            case R.id.ll_me_information: //个人信息管理
                startActivity(new Intent(getActivity(), Personal_Information_Activity.class));
                break;
            case R.id.ll_me_opinion://意见反馈
                startActivity(new Intent(getActivity(), Opinion_Activity.class));
                break;
            case R.id.ll_me_customer_service: //客服电话
                final CommonDialog_Home dialog = new CommonDialog_Home(getActivity());
                dialog.setMessage("是否拨打电话给客服")
                        //.setImageResId(R.mipmap.ic_launcher)
                        .setTitle("提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {//拨打电话
                        dialog.dismiss();
                        call("tel:" + "4006262365");
                    }

                    @Override
                    public void onNegtiveClick() {//取消
                        dialog.dismiss();
                        // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                    }
                }).show();

                break;
            case R.id.ll_sub_account_management:
                startActivity(new Intent(getActivity(), SubAccountManagementActivity.class));
                break;
            case R.id.ll_recommended_gift:
                dialog_share = LayoutInflater.from(mActivity).inflate(R.layout.dialog_share, null);
                btn_share_one = dialog_share.findViewById(R.id.btn_share_one);
//                btn_share_two = dialog_share.findViewById(R.id.btn_share_two);

                iv_code_one = dialog_share.findViewById(R.id.iv_code_one);
                Bitmap bitmap = ZXingUtils.createQRImage("http://admin.xigyu.com/sign?phone=" + userID + "&type=7", 600, 600, BitmapFactory.decodeResource(getResources(), R.drawable.icon));
                iv_code_one.setImageBitmap(bitmap);
                btn_share_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogShare.dismiss();
                        mShareAction.open();
                    }
                });
//                btn_share_two.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialogShare.dismiss();
//                        mShareAction.open();
//                    }
//                });
                dialogShare = new AlertDialog.Builder(mActivity).setView(dialog_share)
                        .create();
                dialogShare.show();
                window = dialogShare.getWindow();
//                window.setContentView(dialog_share);
                WindowManager.LayoutParams lp = window.getAttributes();
//                lp.alpha = 0.5f;
                // 也可按屏幕宽高比例进行设置宽高
//                Display display = mActivity.getWindowManager().getDefaultDisplay();
//                lp.width = (int) (display.getWidth() * 0.6);
//                lp.height = dialog_share.getHeight();
//                lp.width = 300;
//                lp.height = 400;

                window.setAttributes(lp);
//                window.setDimAmount(0.1f);
                window.setBackgroundDrawable(new ColorDrawable());
                break;
            case R.id.ll_share_code:
                dialog_share = LayoutInflater.from(mActivity).inflate(R.layout.dialog_share, null);
                btn_share_one = dialog_share.findViewById(R.id.btn_share_one);
//                btn_share_two = dialog_share.findViewById(R.id.btn_share_two);

                iv_code_one = dialog_share.findViewById(R.id.iv_code_one);
                btn_go_to_the_mall = dialog_share.findViewById(R.id.btn_go_to_the_mall);
                Bitmap bitmap1 = ZXingUtils.createQRImage("http://admin.xigyu.com/sign?phone=" + userID + "&type=7", 600, 600, BitmapFactory.decodeResource(getResources(), R.drawable.icon));
                iv_code_one.setImageBitmap(bitmap1);
                btn_share_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogShare.dismiss();
                        mShareAction.open();
                    }
                });
                btn_go_to_the_mall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openShopApp("com.zhenghaikj.shop");
                        dialogShare.dismiss();
                    }
                });
//                btn_share_two.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialogShare.dismiss();
//                        mShareAction.open();
//                    }
//                });
                dialogShare = new AlertDialog.Builder(mActivity).setView(dialog_share)
                        .create();
                dialogShare.show();
                window = dialogShare.getWindow();
//                window.setContentView(dialog_share);
                WindowManager.LayoutParams lp2 = window.getAttributes();
//                lp.alpha = 0.5f;
                // 也可按屏幕宽高比例进行设置宽高
//                Display display = mActivity.getWindowManager().getDefaultDisplay();
//                lp.width = (int) (display.getWidth() * 0.6);
//                lp.height = dialog_share.getHeight();
//                lp.width = 300;
//                lp.height = 400;

                window.setAttributes(lp2);
//                window.setDimAmount(0.1f);
                window.setBackgroundDrawable(new ColorDrawable());
                break;

            case R.id.ll_online_consultation: //在线咨询
                //startActivity(new Intent(getActivity(), IntelligentCustomerServiceActivity.class));


                VisitorInfo visitorInfo = new VisitorInfo();
                visitorInfo.userName = userInfo.getUserID();

                visitorInfo.nickName = userInfo.getTrueName();
                visitorInfo.phone = userInfo.getPhone();
                BotKitClient.getInstance().setVisitor(visitorInfo);

                Intent intent = new Intent();
                //   intent.setClass(mActivity, ChatActivity.class);
                intent.setClass(mActivity, ChatActivity.class);
                startActivity(intent);

                break;
            case R.id.normal_dfk_ll:
                bundle = new Bundle();
                bundle.putString("intent", "pending_appointment");
                intent = new Intent(mActivity, Order_Receiving_Activity.class);
                intent.putExtras(bundle);
                ActivityUtils.startActivity(intent);
                break;
            case R.id.normal_dfh_ll:   //待返件
                bundle = new Bundle();
                bundle.putString("intent", "wait_return");
                intent = new Intent(mActivity, Order_Receiving_Activity.class);
                intent.putExtras(bundle);
                ActivityUtils.startActivity(intent);
                break;
            case R.id.normal_dsh_ll:
                bundle = new Bundle();
                bundle.putString("intent", "quality");
                intent = new Intent(mActivity, Order_Receiving_Activity.class);
                intent.putExtras(bundle);
                ActivityUtils.startActivity(intent);
                break;
            case R.id.normal_all_ll:
                bundle = new Bundle();
                bundle.putString("intent", "completed");
                intent = new Intent(mActivity, Order_Receiving_Activity.class);
                intent.putExtras(bundle);
                ActivityUtils.startActivity(intent);
                break;
            case R.id.tv_recharge:
                startActivity(new Intent(mActivity, RechargeActivity.class));
                break;
            default:
                break;
        }
    }

    public void showVerifiedDialog() {
        customDialog = new CustomDialog(getContext());
        customDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        customDialog.setTitle("实名认证");
        customDialog.show();
        customDialog.setYesOnclickListener("确定", new CustomDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                //Toast.makeText(getContext(), "点击了--去认证--按钮", Toast.LENGTH_LONG).show();
                customDialog.dismiss();
                startActivity(new Intent(mActivity, Verified_Activity.class));
            }
        });

        customDialog.setNoOnclickListener("取消", new CustomDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                //Toast.makeText(getContext(), "点击了--再想想--按钮", Toast.LENGTH_LONG).show();
                customDialog.dismiss();
            }
        });

        customDialog.setNoOnclickListener("取消", new CustomDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                // Toast.makeText(getContext(), "点击了--关闭-按钮", Toast.LENGTH_LONG).show();
                customDialog.dismiss();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String message) {
        if (!"GetUserInfoList".equals(message)) {
            return;
        }
        mPresenter.GetUserInfoList(userID, "1");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    private boolean isInstalled(String packageName) {
        PackageManager manager = mActivity.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> installedPackages = manager.getInstalledPackages(0);
        if (installedPackages != null) {
            for (PackageInfo info : installedPackages) {
                if (info.packageName.equals(packageName))
                    return true;
            }
        }
        return false;
    }

    private void openShopApp(String packageName) {

        if (isInstalled(packageName)) {
            PackageInfo pi = null;
            try {
                pi = getActivity().getPackageManager().getPackageInfo(packageName, 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
            resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            resolveIntent.setPackage(pi.packageName);

            List<ResolveInfo> apps = getActivity().getPackageManager().queryIntentActivities(resolveIntent, 0);

            ResolveInfo ri = apps.iterator().next();
            if (ri != null) {
                packageName = ri.activityInfo.packageName;
                String className = ri.activityInfo.name;

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);

                ComponentName cn = new ComponentName(packageName, className);
                intent.setComponent(cn);
                startActivity(intent);
            }


        } else {
            final CommonDialog_Home dialog = new CommonDialog_Home(getActivity());
            dialog.setMessage("未安装商城app，是否请前往下载安装")
                    //.setImageResId(R.mipmap.ic_launcher)
                    .setTitle("提示")
                    .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                @Override
                public void onPositiveClick() {//拨打电话
                    dialog.dismiss();
                    openBrowser(mActivity,"http://47.96.126.145:8820/Files/西瓜鱼商城.apk");
                }

                @Override
                public void onNegtiveClick() {//取消
                    dialog.dismiss();
                }
            }).show();
        }
    }

    /**
     * 调用第三方浏览器打开
     *
     * @param context
     * @param url     要浏览的资源地址
     */
    public static void openBrowser(Context context, String url) {
        final Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
        // 注意此处的判断intent.resolveActivity()可以返回显示该Intent的Activity对应的组件名
        // 官方解释 : Name of the component implementing an activity that can display the intent
//        if (intent.resolveActivity(context.getPackageManager()) != null) {
//            final ComponentName componentName = intent.resolveActivity(context.getPackageManager());
//            // 打印Log   ComponentName到底是什么
//            context.startActivity(Intent.createChooser(intent, "请选择浏览器"));
//        } else {
//            Toast.makeText(context.getApplicationContext(), "请下载浏览器", Toast.LENGTH_SHORT).show();
//        }
    }
}
