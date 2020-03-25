package com.ying.administrator.masterappdemo.mvp.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.BankCard;
import com.ying.administrator.masterappdemo.entity.Bill;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.WalletContract;
import com.ying.administrator.masterappdemo.mvp.model.WalletModel;
import com.ying.administrator.masterappdemo.mvp.presenter.WalletPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Wallet_record_Adapter;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BaseFragment.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecordFragment extends BaseFragment<WalletPresenter, WalletModel>implements WalletContract.View {
    private View view;
    private String title;
    private String userId;

   private RecyclerView recyclerView;
    private Wallet_record_Adapter wallet_record_adapter;
    private List<Bill.DataBean> recharge_list = new ArrayList<>();//充值记录
    private List<Bill.DataBean> withdraw_list = new ArrayList<>();//提现记录
    private List<Bill.DataBean> expend_income_list = new ArrayList<>();//收入支出记录


    public static RecordFragment newInstance(String title) {
        RecordFragment fragment = new RecordFragment();
        fragment.title=title;
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view==null){
            view=inflater.inflate(R.layout.fragment_record,container,false);
            initView();
            initData();
            initListener();
        }

        return view;
    }




    private void initView() {
        recyclerView=view.findViewById(R.id.rv_record);
    }
    private void initData() {
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");

        if (title.equals("收支")){
            mPresenter.AccountBill(userId, "2,5","1","999");
        }
        if (title.equals("提现")){
            mPresenter.AccountBill(userId, "3","1","999");//提现
        }
        if (title.equals("充值")){
            mPresenter.AccountBill(userId, "1","1","999");//充值
        }


    }
    private void initListener() {
    }



    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {

    }

    @Override
    public void AccountBill(BaseResult<Data<Bill>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    if (baseResult.getData().getItem2().getData() != null) {
                        switch (baseResult.getData().getItem2().getData().get(0).getState()) {
                            case "1"://充值
                                recharge_list.addAll(baseResult.getData().getItem2().getData());
                                recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
                              /*  recyclerView.setHasFixedSize(true);
                                recyclerView.setNestedScrollingEnabled(false);*/
                                wallet_record_adapter = new Wallet_record_Adapter(R.layout.item_withdrawals_record, recharge_list);
                                recyclerView.setAdapter(wallet_record_adapter);
                                wallet_record_adapter.setEmptyView(getRecordEmptyView());

                                break;
                            case "2"://支出
                            case "5"://收入
                                expend_income_list.addAll(baseResult.getData().getItem2().getData());
                                recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
                        /*        recyclerView.setHasFixedSize(true);
                                recyclerView.setNestedScrollingEnabled(false);*/
                                wallet_record_adapter = new Wallet_record_Adapter(R.layout.item_withdrawals_record, expend_income_list);
                                recyclerView.setAdapter(wallet_record_adapter);
                                wallet_record_adapter.setEmptyView(getRecordEmptyView());
                                break;
                            case "3"://提现
                                withdraw_list.addAll(baseResult.getData().getItem2().getData());
                                recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
                      /*          recyclerView.setHasFixedSize(true);
                                recyclerView.setNestedScrollingEnabled(false);*/
                                wallet_record_adapter = new Wallet_record_Adapter(R.layout.item_withdrawals_record, withdraw_list);
                                recyclerView.setAdapter(wallet_record_adapter);
                                wallet_record_adapter.setEmptyView(getRecordEmptyView());
                                break;
                            case "4"://待支付
                                break;
                            default:
                                break;

                        }

                    }

                }
                break;
        }
    }

    @Override
    public void GetAccountPayInfoList(BaseResult<List<BankCard>> baseResult) {

    }
}
