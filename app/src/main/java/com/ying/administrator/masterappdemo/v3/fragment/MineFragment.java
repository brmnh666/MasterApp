package com.ying.administrator.masterappdemo.v3.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.UserInfo2;
import com.ying.administrator.masterappdemo.mvp.ui.activity.AboutUsActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Login_New_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Opinion_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.SubAccountManagementActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.VerifiedActivity2;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;
import com.ying.administrator.masterappdemo.util.ZXingUtils;
import com.ying.administrator.masterappdemo.v3.activity.PersonalInformationActivity;
import com.ying.administrator.masterappdemo.v3.activity.SettingActivity;
import com.ying.administrator.masterappdemo.v3.activity.WalletActivity;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.MinePresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.MineContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.MineModel;
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;
import com.ying.administrator.masterappdemo.widget.GlideCircleWithBorder2;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MineFragment extends BaseLazyFragment<MinePresenter, MineModel> implements View.OnClickListener, MineContract.View {
    private static final String ARG_SHOW_TEXT = "text";
    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    @BindView(R.id.tv_certified)
    TextView mTvCertified;
    @BindView(R.id.tv_suggest)
    TextView mTvSuggest;
    @BindView(R.id.ll_setting)
    LinearLayout mLlSetting;
    @BindView(R.id.tv_efficiency)
    TextView mTvEfficiency;
    @BindView(R.id.tv_quality)
    TextView mTvQuality;
    @BindView(R.id.tv_finish)
    TextView mTvFinish;
    @BindView(R.id.ll_personal_information)
    LinearLayout mLlPersonalInformation;
    @BindView(R.id.ll_wallet)
    LinearLayout mLlWallet;
    @BindView(R.id.ll_sub_account)
    LinearLayout mLlSubAccount;
    @BindView(R.id.ll_price_standard)
    LinearLayout mLlPriceStandard;
    @BindView(R.id.ll_master_code)
    LinearLayout mLlMasterCode;
    @BindView(R.id.ll_feedback)
    LinearLayout mLlFeedback;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.ll_about_us)
    LinearLayout mLlAboutUs;
    Unbinder unbinder;
    @BindView(R.id.ll_person)
    LinearLayout mLlPerson;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.ll_name)
    LinearLayout mLlName;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private String mContentText;
    private String userID;
    //    private UserInfo.UserInfoDean userInfo;
    private UserInfo2.DataBeanX.DataBean userInfo;
    private View dialog_share;
    private AlertDialog dialogShare;
    private ShareAction mShareAction;
    private CustomShareListener mShareListener;
    private SPUtils spUtils;
    private String token;

    public static MineFragment newInstance(String param1) {
        MineFragment fragment = new MineFragment();
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
        return R.layout.v3_fragment_mine;
    }

    @Override
    protected void initData() {
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(mActivity).setShareConfig(config);
        mShareListener = new CustomShareListener(mActivity);
        /*增加自定义按钮的分享面板*/
        mShareAction = new ShareAction(mActivity).setDisplayList(
                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.MORE)
                .addButton("复制文本", "复制文本", "umeng_socialize_copy", "umeng_socialize_copy")
                .addButton("复制链接", "复制链接", "umeng_socialize_copyurl", "umeng_socialize_copyurl")
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, final SHARE_MEDIA share_media) {
                        if (snsPlatform.mShowWord.equals("复制文本")) {
                            Toast.makeText(mActivity, "已复制", Toast.LENGTH_LONG).show();
                        } else if (snsPlatform.mShowWord.equals("复制链接")) {
                            Toast.makeText(mActivity, "已复制", Toast.LENGTH_LONG).show();
                        } else {
                            RxPermissions rxPermissions = new RxPermissions(mActivity);
                            rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                                    .subscribe(new Consumer<Boolean>() {
                                        @Override
                                        public void accept(Boolean aBoolean) throws Exception {
                                            if (aBoolean) {
                                                // 获取全部权限成功

                                                UMWeb web = new UMWeb("http://admin.xigyu.com/sign?phone=" + userID + "&type=7");
                                                web.setTitle("西瓜鱼服务");
                                                web.setDescription("注册即可接单！！！！！");
                                                web.setThumb(new UMImage(mActivity, R.drawable.icon));
                                                new ShareAction(mActivity).withMedia(web)
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
        mRefreshLayout.setEnableLoadmore(false);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                accountInfo();
//                mPresenter.GetUserInfoList(userID, "1");//根据 手机号码获取用户详细信息
                refreshlayout.finishRefresh(1000);
            }
        });

        if (userInfo != null) {
            getInfo();
        } else {
            return;
        }

    }

    @Override
    protected void initView() {
        spUtils = SPUtils.getInstance("token");
        userID = spUtils.getString("userName");
        token = spUtils.getString("adminToken");
//        mPresenter.GetUserInfoList(userID, "1");
        accountInfo();

    }

    @Override
    protected void setListener() {
        mLlWallet.setOnClickListener(this);
        mLlPersonalInformation.setOnClickListener(this);
        mLlSetting.setOnClickListener(this);
        mIvAvatar.setOnClickListener(this);
        mLlCustomerService.setOnClickListener(this);
        mLlFeedback.setOnClickListener(this);
        mLlPerson.setOnClickListener(this);
        mLlAboutUs.setOnClickListener(this);
        mLlMasterCode.setOnClickListener(this);
        mLlSubAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_wallet:
                startActivity(new Intent(mActivity, WalletActivity.class));
                break;
            case R.id.ll_personal_information:
                startActivity(new Intent(mActivity, PersonalInformationActivity.class));
                break;
            case R.id.ll_setting:
                startActivity(new Intent(mActivity, SettingActivity.class));
                break;
            case R.id.iv_avatar:
                startActivity(new Intent(getActivity(), PersonalInformationActivity.class));
                break;
            case R.id.ll_customer_service:
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
            case R.id.ll_feedback:
                startActivity(new Intent(mActivity, Opinion_Activity.class));
                break;
            case R.id.ll_person:
                try {
                    if (userInfo.getTrueName() == null) { //如果为空说明未认证
                        startActivity(new Intent(mActivity, VerifiedActivity2.class));
                    } else {
                        startActivity(new Intent(mActivity, PersonalInformationActivity.class));
                    }
                } catch (Exception e) {
                    return;
                }

                break;
            case R.id.ll_about_us:
                startActivity(new Intent(mActivity, AboutUsActivity.class));
                break;
            case R.id.ll_master_code:
                dialog_share = LayoutInflater.from(mActivity).inflate(R.layout.dialog_share, null);
                Button btn_share_one = dialog_share.findViewById(R.id.btn_share_one);
//                btn_share_two = dialog_share.findViewById(R.id.btn_share_two);
                TextView tv_title = dialog_share.findViewById(R.id.tv_title);
                tv_title.setText("扫描加入西瓜鱼服务");

                ImageView iv_code_one = dialog_share.findViewById(R.id.iv_code_one);
                Button btn_go_to_the_mall = dialog_share.findViewById(R.id.btn_go_to_the_mall);
                Bitmap bitmap1 = ZXingUtils.createQRImage("http://admin.xigyu.com/sign?phone=" + userID + "&type=7", 600, 600, BitmapFactory.decodeResource(getResources(), R.drawable.icon));
                iv_code_one.setImageBitmap(bitmap1);
                btn_share_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogShare.dismiss();
                        mShareAction.open();
                    }
                });
                btn_go_to_the_mall.setVisibility(View.GONE);
                btn_go_to_the_mall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        openShopApp("com.zhenghaikj.shop");
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
                Window window = dialogShare.getWindow();
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
            case R.id.ll_sub_account:
                startActivity(new Intent(getActivity(), SubAccountManagementActivity.class));
                break;
        }
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

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
//                if (baseResult.getData().getData().size() > 0) {
//                    userInfo = baseResult.getData().getData().get(0);
//                    if (userInfo.getAvator() == null) {//显示默认头像
//                        return;
//                    } else {
//                        RequestOptions myOptions = new RequestOptions().transform(new GlideCircleWithBorder2(this, 1, Color.parseColor("#DCDCDC")));
//                        Glide.with(mActivity)
//                                .load(Config.HEAD_URL + userInfo.getAvator())
//                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
//                                .apply(myOptions)
//                                .into(mIvAvatar);
//                    }
//                    mTvFinish.setText("完成量 " + userInfo.getServiceTotalOrderNum());
//                    /*真实姓名*/
//                    if (userInfo.getIfAuth() == null || "".equals(userInfo.getIfAuth())) {
//                        mTvCertified.setText("未认证");
//                        mTvSuggest.setVisibility(View.VISIBLE);
//                        mLlName.setVisibility(View.GONE);
//                    } else if ("0".equals(userInfo.getIfAuth())) {
//                        mTvCertified.setText(userInfo.getTrueName());
//                        mTvSuggest.setVisibility(View.VISIBLE);
//                        mTvSuggest.setText("认证中");
//                        mLlName.setVisibility(View.GONE);
//                    } else if ("-1".equals(userInfo.getIfAuth())) {
//                        mTvCertified.setText(userInfo.getTrueName());
//                        mTvSuggest.setVisibility(View.VISIBLE);
//                        mTvSuggest.setText("拒绝，请查看原因");
//                        mLlName.setVisibility(View.GONE);
//                    } else if ("1".equals(userInfo.getIfAuth())) {
//                        mTvCertified.setText(userInfo.getTrueName());
//                        mTvSuggest.setVisibility(View.GONE);
//                        mLlName.setVisibility(View.VISIBLE);
//                        mTvPhone.setText(userInfo.getUserID() + "");
//                    }
//
//                    if (userInfo.getParentUserID() == null) {
//                        mLlSubAccount.setVisibility(View.VISIBLE);
//                    } else {
//                        mLlSubAccount.setVisibility(View.GONE);
//                    }
//                } else {
//                    startActivity(new Intent(mActivity, Login_New_Activity.class));
//                    spUtils.put("isLogin", false);
//                    mActivity.finish();
//                }
                break;
            case 400:
                ActivityUtils.finishAllActivities();
                startActivity(new Intent(mActivity, Login_New_Activity.class));
                mActivity.finish();
                break;
            default:
                ActivityUtils.finishAllActivities();
                startActivity(new Intent(mActivity, Login_New_Activity.class));
                mActivity.finish();
                break;
        }
    }

    @Override
    public void UploadAvator(BaseResult<Data<String>> baseResult) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        if ("GetUserInfoList".equals(name)) {
//            mPresenter.GetUserInfoList(userID, "1");
            accountInfo();
        } else if ("certification".equals(name)) {
//            mPresenter.GetUserInfoList(userID, "1");
            accountInfo();
        }
    }

    public static class CustomShareListener implements UMShareListener {
        private Context mContext;

        public CustomShareListener(Context context) {
            mContext = context;
        }

        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {

            if (platform.name().equals("WEIXIN_FAVORITE")) {
//                Toast.makeText(mContext, platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
            } else {
                if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                        && platform != SHARE_MEDIA.EMAIL
                        && platform != SHARE_MEDIA.FLICKR
                        && platform != SHARE_MEDIA.FOURSQUARE
                        && platform != SHARE_MEDIA.TUMBLR
                        && platform != SHARE_MEDIA.POCKET
                        && platform != SHARE_MEDIA.PINTEREST

                        && platform != SHARE_MEDIA.INSTAGRAM
                        && platform != SHARE_MEDIA.GOOGLEPLUS
                        && platform != SHARE_MEDIA.YNOTE
                        && platform != SHARE_MEDIA.EVERNOTE) {
//                    Toast.makeText(mContext, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
                }

            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                    && platform != SHARE_MEDIA.EMAIL
                    && platform != SHARE_MEDIA.FLICKR
                    && platform != SHARE_MEDIA.FOURSQUARE
                    && platform != SHARE_MEDIA.TUMBLR
                    && platform != SHARE_MEDIA.POCKET
                    && platform != SHARE_MEDIA.PINTEREST

                    && platform != SHARE_MEDIA.INSTAGRAM
                    && platform != SHARE_MEDIA.GOOGLEPLUS
                    && platform != SHARE_MEDIA.YNOTE
                    && platform != SHARE_MEDIA.EVERNOTE) {
//                Toast.makeText(mContext, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();

            }

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {

//            Toast.makeText(mContext, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    }

    public void accountInfo() {

        FormBody formboby = new FormBody.Builder()
                .add("UserID", userID)
                .add("limit", "1")
                .build();
        //接口
        String path = Config.BASE_URL+"Account/GetUserInfoList";

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
        final Request request = new Request.Builder()
                .url(path)
                .addHeader("userName", userID)
                .addHeader("adminToken",token)
                .post(formboby)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ActivityUtils.finishAllActivities();
                startActivity(new Intent(mActivity, Login_New_Activity.class));
                mActivity.finish();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
//                System.out.println(str);
//                ToastUtils.showShort(str);
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    if (jsonObject.getInt("StatusCode") == 200) {
                        Gson gson = new Gson();
                        UserInfo2 result = gson.fromJson(str.replaceAll(" ", ""), UserInfo2.class);
                        if (result.getData().getData().size() > 0) {
                            userInfo = result.getData().getData().get(0);
                        } else {
                            startActivity(new Intent(mActivity, Login_New_Activity.class));
                            spUtils.put("isLogin", false);
                            mActivity.finish();
                        }
                    } else {
                        startActivity(new Intent(mActivity, Login_New_Activity.class));
                        spUtils.put("isLogin", false);
                        mActivity.finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Gson gson=new Gson();
//                UserInfo2 result=gson.fromJson(str.replaceAll(" ",""),UserInfo2.class);
//                switch (result.getStatusCode()){
//                    case 200:
//                        if (result.getData().getData().size() > 0) {
//                            userInfo = result.getData().getData().get(0);
////                            if (userInfo.getAvator() == null) {//显示默认头像
////                                return;
////                            } else {
//////                                RequestOptions myOptions = new RequestOptions().transform(new GlideCircleWithBorder2(this, 1, Color.parseColor("#DCDCDC")));
//////                                Glide.with(mActivity)
//////                                        .load(Config.HEAD_URL + userInfo.getAvator())
//////                                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
////////                                        .apply(myOptions)
//////                                        .into(mIvAvatar);
////                            }
////                            mTvFinish.setText("完成量 " + userInfo.getServiceTotalOrderNum());
////                            /*真实姓名*/
////                            if (userInfo.getIfAuth() == null || "".equals(userInfo.getIfAuth())) {
////                                mTvCertified.setText("未认证");
////                                mTvSuggest.setVisibility(View.VISIBLE);
////                                mLlName.setVisibility(View.GONE);
////                            } else if ("0".equals(userInfo.getIfAuth())) {
////                                mTvCertified.setText(userInfo.getTrueName());
////                                mTvSuggest.setVisibility(View.VISIBLE);
////                                mTvSuggest.setText("认证中");
////                                mLlName.setVisibility(View.GONE);
////                            } else if ("-1".equals(userInfo.getIfAuth())) {
////                                mTvCertified.setText(userInfo.getTrueName());
////                                mTvSuggest.setVisibility(View.VISIBLE);
////                                mTvSuggest.setText("拒绝，请查看原因");
////                                mLlName.setVisibility(View.GONE);
////                            } else if ("1".equals(userInfo.getIfAuth())) {
////                                mTvCertified.setText(userInfo.getTrueName());
////                                mTvSuggest.setVisibility(View.GONE);
////                                mLlName.setVisibility(View.VISIBLE);
////                                mTvPhone.setText(userInfo.getUserID() + "");
////                            }
////
////                            if (userInfo.getParentUserID() == null) {
////                                mLlSubAccount.setVisibility(View.VISIBLE);
////                            } else {
////                                mLlSubAccount.setVisibility(View.GONE);
////                            }
//                        } else {
//                            startActivity(new Intent(mActivity, Login_New_Activity.class));
//                            spUtils.put("isLogin", false);
//                            mActivity.finish();
//                        }
//                        break;
//                    case 400:
//                        startActivity(new Intent(mActivity, Login_New_Activity.class));
//                        spUtils.put("isLogin", false);
//                        mActivity.finish();
//                        break;
//                        default:
//                            startActivity(new Intent(mActivity, Login_New_Activity.class));
//                            spUtils.put("isLogin", false);
//                            mActivity.finish();
//                            break;
//                }
            }
        });
    }

    private void getInfo() {
        if (userInfo.getAvator() == null) {//显示默认头像
            return;
        } else {
            RequestOptions myOptions = new RequestOptions().transform(new GlideCircleWithBorder2(this, 1, Color.parseColor("#DCDCDC")));
            Glide.with(mActivity)
                    .load(Config.HEAD_URL + userInfo.getAvator())
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .apply(myOptions)
                    .into(mIvAvatar);
        }
        mTvFinish.setText("完成量 " + userInfo.getServiceTotalOrderNum());
        /*真实姓名*/
        if (userInfo.getIfAuth() == null || "".equals(userInfo.getIfAuth())) {
            mTvCertified.setText("未认证");
            mTvSuggest.setVisibility(View.VISIBLE);
            mLlName.setVisibility(View.GONE);
        } else if ("0".equals(userInfo.getIfAuth())) {
            mTvCertified.setText(userInfo.getTrueName());
            mTvSuggest.setVisibility(View.VISIBLE);
            mTvSuggest.setText("认证中");
            mLlName.setVisibility(View.GONE);
        } else if ("-1".equals(userInfo.getIfAuth())) {
            mTvCertified.setText(userInfo.getTrueName());
            mTvSuggest.setVisibility(View.VISIBLE);
            mTvSuggest.setText("拒绝，请查看原因");
            mLlName.setVisibility(View.GONE);
        } else if ("1".equals(userInfo.getIfAuth())) {
            mTvCertified.setText(userInfo.getTrueName());
            mTvSuggest.setVisibility(View.GONE);
            mLlName.setVisibility(View.VISIBLE);
            mTvPhone.setText(userInfo.getUserID() + "");
        }

        if (userInfo.getParentUserID() == null|| "".equals(userInfo.getParentUserID())) {
            mLlSubAccount.setVisibility(View.VISIBLE);
            mLlWallet.setVisibility(View.VISIBLE);
        } else {
            mLlSubAccount.setVisibility(View.GONE);
            mLlWallet.setVisibility(View.GONE );
        }
    }

}
