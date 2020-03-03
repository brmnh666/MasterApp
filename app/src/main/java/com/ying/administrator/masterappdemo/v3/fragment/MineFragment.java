package com.ying.administrator.masterappdemo.v3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.ui.activity.VerifiedActivity2;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseLazyFragment;
import com.ying.administrator.masterappdemo.v3.activity.FeedbackActivity;
import com.ying.administrator.masterappdemo.v3.activity.PersonalInformationActivity;
import com.ying.administrator.masterappdemo.v3.activity.SettingActivity;
import com.ying.administrator.masterappdemo.v3.activity.WalletActivity;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.MinePresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.MineContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.MineModel;
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    private String mContentText;
    private String userID;
    private UserInfo.UserInfoDean userInfo;

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

    }

    @Override
    protected void initView() {
        SPUtils spUtils = SPUtils.getInstance("token");
        userID = spUtils.getString("userName");
        mPresenter.GetUserInfoList(userID, "1");
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
                startActivity(new Intent(mActivity, FeedbackActivity.class));
                break;
            case R.id.ll_person:
                if (userInfo.getTrueName() == null) { //如果为空说明未认证
                    startActivity(new Intent(mActivity, VerifiedActivity2.class));
                } else {
                    startActivity(new Intent(mActivity, PersonalInformationActivity.class));
                }
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
                userInfo = baseResult.getData().getData().get(0);
                if (userInfo.getAvator() == null) {//显示默认头像
                    return;
                } else {
                    Glide.with(mActivity)
                            .load(Config.HEAD_URL + userInfo.getAvator())
                            .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                            .into(mIvAvatar);
                }
                /*真实姓名*/
                if (userInfo.getTrueName() == null) { //如果为空说明未认证
                    mTvCertified.setText("未认证");
                    mTvSuggest.setVisibility(View.VISIBLE);
                    mLlName.setVisibility(View.GONE);
                } else {
                    mTvCertified.setText(userInfo.getTrueName());
                    mTvSuggest.setVisibility(View.GONE);
                    mLlName.setVisibility(View.VISIBLE);
                    mTvPhone.setText(userInfo.getUserID()+"");
                }
                break;
        }
    }

    @Override
    public void UploadAvator(BaseResult<Data<String>> baseResult) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        if ("GetUserInfoList".equals(name)) {
            mPresenter.GetUserInfoList(userID, "1");
        }
    }
}
