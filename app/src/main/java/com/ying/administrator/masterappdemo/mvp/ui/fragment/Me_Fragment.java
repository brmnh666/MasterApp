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

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.mvp.ui.activity.AboutUsActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Opinion_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Personal_Information_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.SettingActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Wallet_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.WithDrawActivity;
import com.ying.administrator.masterappdemo.common.DefineView;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseFragment;

public class Me_Fragment extends BaseFragment implements DefineView {
    private static final String ARG_SHOW_TEXT = "text";
    private String mContentText;
    private View view;
    private TextView tv_me_withdraw_deposit;
    private LinearLayout ll_me_mywallet;
    private ImageView img_me_setting;
    private LinearLayout ll_me_about_us;
    private LinearLayout ll_me_information;
    private LinearLayout ll_me_opinion;
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
        if (view==null){
            view = inflater.inflate(R.layout.fragment_me, container, false);
            Log.d("ying","调用了onCreateView");
            initView();
            initValidata();
            initListener();
        }

        return view;
    }

    @Override
    public void initView() {
        tv_me_withdraw_deposit=view.findViewById(R.id.tv_me_withdraw_deposit);
        ll_me_mywallet=view.findViewById(R.id.ll_me_mywallet);
        img_me_setting=view.findViewById(R.id.img_me_setting);
        ll_me_about_us=view.findViewById(R.id.ll_me_about_us);
        ll_me_information=view.findViewById(R.id.ll_me_information);
        ll_me_opinion=view.findViewById(R.id.ll_me_opinion);
    }

    @Override
    public void initValidata() {

    }

    @Override
    public void initListener() {
        tv_me_withdraw_deposit.setOnClickListener(new CustomOnclickListner());
        ll_me_mywallet.setOnClickListener(new CustomOnclickListner());
        img_me_setting.setOnClickListener(new CustomOnclickListner());
        ll_me_about_us.setOnClickListener(new CustomOnclickListner());
        ll_me_information.setOnClickListener(new CustomOnclickListner());
        ll_me_opinion.setOnClickListener(new CustomOnclickListner());
    }

    @Override
    public void bindData() {

    }

    public class CustomOnclickListner implements View.OnClickListener{
        @Override
        public void onClick(View v) {
      switch (v.getId()){
          case R.id.tv_me_withdraw_deposit: //提现
              startActivity(new Intent(getActivity(),WithDrawActivity.class));
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
              default:
                  break;
      }
        }
    }
}
