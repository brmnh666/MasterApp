package com.ying.administrator.masterappdemo.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
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
import com.ying.administrator.masterappdemo.common.DefineView;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.MainContract;
import com.ying.administrator.masterappdemo.mvp.model.MainModel;
import com.ying.administrator.masterappdemo.mvp.presenter.MainPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.activity.AboutUsActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Opinion_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Order_Receiving_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Personal_Information_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.SettingActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.SubAccountManagementActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Verified_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Wallet_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.WithDrawActivity;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseFragment;
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;
import com.ying.administrator.masterappdemo.widget.ShareDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Me_Fragment extends BaseFragment<MainPresenter, MainModel> implements DefineView, MainContract.View {
    private static final String ARG_SHOW_TEXT = "text";
    private SPUtils spUtils = SPUtils.getInstance("token");
    private UserInfo.UserInfoDean userInfo=new UserInfo.UserInfoDean();
    private String userID; //获取用户id
    private String mContentText;
    private View mRootView;
    private LinearLayout ll_me_mywallet;
    private ImageView img_me_setting;
    private LinearLayout ll_me_about_us;
    private LinearLayout ll_me_information;
    private LinearLayout ll_me_opinion;
    private LinearLayout ll_me_customer_service;//客服
    private LinearLayout ll_sub_account_management;//子账户管理
//     private CommonDialog_Home dialog;
    @BindView(R.id.img_me_head) //我的页面头像
    ImageView mImg_me_message;
    @BindView(R.id.tv_me_withdraw)
    TextView mTv_me_withdraw;
    @BindView(R.id.tv_me_withdraw_deposit)
     TextView mTv_me_withdraw_deposit;
    Unbinder unbinder;
    private LinearLayout ll_recommended_gift;
    private ShareDialog shareDialog;


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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mRootView == null) {

            mRootView = inflater.inflate(R.layout.fragment_me, container, false);
        }

        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, mRootView);
        EventBus.getDefault().register(this);
        initView();
        initValidata();
        initListener();

    }

    @Override
    public void initView() {
        //dialog = new CommonDialog_Home(getActivity()); //弹出框
        mTv_me_withdraw_deposit = mRootView.findViewById(R.id.tv_me_withdraw_deposit); //提现
        ll_me_mywallet = mRootView.findViewById(R.id.ll_me_mywallet);
        img_me_setting = mRootView.findViewById(R.id.img_me_setting);
        ll_me_about_us = mRootView.findViewById(R.id.ll_me_about_us);
        ll_me_information = mRootView.findViewById(R.id.ll_me_information);
        ll_me_opinion = mRootView.findViewById(R.id.ll_me_opinion);
        ll_me_customer_service=mRootView.findViewById(R.id.ll_me_customer_service);
        ll_sub_account_management=mRootView.findViewById(R.id.ll_sub_account_management);
        ll_recommended_gift = mRootView.findViewById(R.id.ll_recommended_gift);
    }

    @Override
    public void initValidata() {
        userID = spUtils.getString("userName"); //获取用户id
        mPresenter.GetUserInfoList(userID,"1");//根据 手机号码获取用户详细信息
    }

    @Override
    public void initListener() {
        mTv_me_withdraw_deposit.setOnClickListener(new CustomOnclickListner());//提现
        ll_me_mywallet.setOnClickListener(new CustomOnclickListner());
        img_me_setting.setOnClickListener(new CustomOnclickListner());
        ll_me_about_us.setOnClickListener(new CustomOnclickListner());
        ll_me_information.setOnClickListener(new CustomOnclickListner());
        ll_me_opinion.setOnClickListener(new CustomOnclickListner());
        ll_me_customer_service.setOnClickListener(new CustomOnclickListner());
        ll_sub_account_management.setOnClickListener(new CustomOnclickListner());
        ll_recommended_gift.setOnClickListener(new CustomOnclickListner());
    }

    @Override
    public void bindData() {

    }

    /*获取用户信息*/
    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200://请求成功
                userInfo = baseResult.getData().getData().get(0);
                /*显示头像*/
                if (userInfo.getAvator()==null){//显示初始头像
                    return;
                }else {
                    Glide.with(this)
                            .load(Config.HEAD_URL+userInfo.getAvator())
                            .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                            .into(mImg_me_message);
                }
                /*显示余额*/
                mTv_me_withdraw.setText("可提现金额(元) "+userInfo.getTotalMoney());


                break;
                default:
                    break;

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();//解除绑定
        EventBus.getDefault().unregister(this);
    }



    @Override
    public void contentLoading() {

    }

    @Override
    public void contentLoadingComplete() {

    }

    @Override
    public void contentLoadingError() {

    }

    @Override
    public void contentLoadingEmpty() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    public class CustomOnclickListner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
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
                case R.id.ll_me_mywallet:  //我的钱包
                    startActivity(new Intent(getActivity(), Wallet_Activity.class));
                    break;
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
                            call("tel:"+"18074208209");
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
                    shareDialog = new ShareDialog(getContext());
                    shareDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
                    shareDialog.show();
                    break;

                default:
                    break;
            }
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String message) {
        mPresenter.GetUserInfoList(userID,"1");
    }

}
